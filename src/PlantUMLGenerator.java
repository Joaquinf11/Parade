import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simple PlantUML generator for Java sources.
 *
 * Scans a source directory, extracts packages, classes/interfaces/enums,
 * inheritance/implementation relations, and fields, then writes a PlantUML
 * class diagram file.
 *
 * Limitations: This is a lightweight parser based on regexes and heuristics.
 * It focuses on common cases and may skip complex Java constructs. Methods are
 * intentionally omitted to reduce noise in the diagram.
 */
public class PlantUMLGenerator {

    private static final Pattern PACKAGE_PATTERN = Pattern.compile("(?m)^\\s*package\\s+([a-zA-Z0-9_\\.]+)\\s*;\\s*$");
    private static final Pattern TYPE_DECL_PATTERN = Pattern.compile(
            "(?s)(?m)(?:^|\\s)(?:@\\S+\\s+)*?(?:public|protected|private)?\\s*(?:abstract\\s+|final\\s+)?(class|interface|enum)\\s+([A-Za-z0-9_]+)\\s*(?:extends\\s+([A-Za-z0-9_\\.]+))?\\s*(?:implements\\s+([A-Za-z0-9_\\.,\\s]+))?\\s*\\{");
    private static final Pattern FIELD_PATTERN = Pattern.compile(
            "^\\s*(?:public|protected|private)?\\s*(?:static\\s+)?(?:final\\s+)?([A-Za-z0-9_\\.<>,\\[\\]\\s]+?)\\s+([A-Za-z0-9_]+)\\s*(?:=.*)?;\\s*$");

    private static class ClassInfo {
        String packageName;
        String simpleName;
        String fullName;
        String kind; // class | interface | enum
        String extendsType; // fully qualified or simple
        List<String> implementsTypes = new ArrayList<>();
        List<FieldInfo> fields = new ArrayList<>();
        List<MethodInfo> methods = new ArrayList<>();
    }

    private static class FieldInfo {
        String type;
        String name;
    }

    private static class MethodInfo {
        String visibility; // public/protected/private or null (package)
        boolean isStatic;
        boolean isAbstract;
        String returnType; // "" for constructors
        String name;
        String params; // as written in source (lightly sanitized)
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("Uso: java PlantUMLGenerator <ruta_src> <salida_puml>");
            System.exit(1);
        }
        Path srcRoot = Paths.get(args[0]).toAbsolutePath().normalize();
        Path outFile = Paths.get(args[1]).toAbsolutePath().normalize();

        if (!Files.isDirectory(srcRoot)) {
            System.err.println("Directorio de fuentes no válido: " + srcRoot);
            System.exit(2);
        }

        List<Path> javaFiles = listJavaFiles(srcRoot);
        Map<String, ClassInfo> nameToClass = new HashMap<>();

        // First pass: collect class/interface/enum declarations with packages
        for (Path file : javaFiles) {
            try {
                parseTypeDeclaration(file, nameToClass);
            } catch (IOException e) {
                System.err.println("Aviso: no se pudo leer " + file + ": " + e.getMessage());
            }
        }

        // Second pass: collect fields
        for (Path file : javaFiles) {
            try {
                parseFields(file, nameToClass);
            } catch (IOException e) {
                System.err.println("Aviso: no se pudieron leer campos de " + file + ": " + e.getMessage());
            }
        }

        // Third pass: collect methods
        for (Path file : javaFiles) {
            try {
                parseMethods(file, nameToClass);
            } catch (IOException e) {
                System.err.println("Aviso: no se pudieron leer métodos de " + file + ": " + e.getMessage());
            }
        }

        writePlantUML(outFile, nameToClass);
        System.out.println("Generado: " + outFile);
    }

    private static List<Path> listJavaFiles(Path root) throws IOException {
        List<Path> result = new ArrayList<>();
        Files.walk(root).filter(p -> p.toString().endsWith(".java")).forEach(result::add);
        return result;
    }

    private static void parseTypeDeclaration(Path javaFile, Map<String, ClassInfo> nameToClass) throws IOException {
        List<String> lines = Files.readAllLines(javaFile, StandardCharsets.UTF_8);
        String pkg = null;
        for (String line : lines) {
            Matcher pm = PACKAGE_PATTERN.matcher(line);
            if (pm.matches()) {
                pkg = pm.group(1);
                break;
            }
        }

        // Join lines to handle declarations that may span multiple lines
        String joined = String.join("\n", lines).replaceAll("/\\*.*?\\*/", " ") // strip block comments
                .replaceAll("//.*", " "); // strip line comments

        // Heuristic: find the first top-level type declaration
        Matcher tm = TYPE_DECL_PATTERN.matcher(joined);
        if (tm.find()) {
            String kind = tm.group(1);
            String simpleName = tm.group(2);
            String extendsType = tm.group(3);
            String implementsRaw = tm.group(4);

            ClassInfo ci = new ClassInfo();
            ci.packageName = pkg == null ? "" : pkg;
            ci.simpleName = simpleName;
            ci.fullName = (ci.packageName.isEmpty() ? simpleName : ci.packageName + "." + simpleName);
            ci.kind = kind;
            ci.extendsType = trimOrNull(extendsType);
            if (implementsRaw != null) {
                for (String part : implementsRaw.split(",")) {
                    String t = part.trim();
                    if (!t.isEmpty()) ci.implementsTypes.add(t);
                }
            }
            nameToClass.put(ci.fullName, ci);
        }
    }

    private static void parseFields(Path javaFile, Map<String, ClassInfo> nameToClass) throws IOException {
        // Determine which class this file defines
        String content = Files.readString(javaFile, StandardCharsets.UTF_8)
                .replaceAll("/\\*.*?\\*/", " ")
                .replaceAll("//.*", " ");

        Matcher pkgM = PACKAGE_PATTERN.matcher(content);
        String pkg = null;
        if (pkgM.find()) pkg = pkgM.group(1);

        Matcher tm = TYPE_DECL_PATTERN.matcher(content);
        if (!tm.find()) return;
        String simpleName = tm.group(2);
        String fullName = (pkg == null || pkg.isEmpty()) ? simpleName : pkg + "." + simpleName;
        ClassInfo ci = nameToClass.get(fullName);
        if (ci == null) return;

        // Try to extract fields only at top level inside the first type block
        int typeStart = content.indexOf('{', tm.start());
        if (typeStart < 0) return;
        int typeEnd = findMatchingBrace(content, typeStart);
        if (typeEnd < 0) typeEnd = content.length();
        String body = content.substring(typeStart + 1, typeEnd);

        // Remove method bodies to avoid matching local variables
        String bodyNoMethods = stripMethodBodies(body);
        List<String> lines = Arrays.asList(bodyNoMethods.split("\n"));
        for (String line : lines) {
            if (line.contains("(")) continue; // skip possible method signatures
            Matcher fm = FIELD_PATTERN.matcher(line);
            if (fm.matches()) {
                FieldInfo fi = new FieldInfo();
                fi.type = fm.group(1).trim().replaceAll("\\s+", " ");
                fi.name = fm.group(2).trim();
                // Filter out obviously non-field lines
                if (!fi.type.isEmpty() && !fi.name.isEmpty()) {
                    ci.fields.add(fi);
                }
            }
        }
    }

    private static void parseMethods(Path javaFile, Map<String, ClassInfo> nameToClass) throws IOException {
        String content = Files.readString(javaFile, StandardCharsets.UTF_8)
                .replaceAll("/\\*.*?\\*/", " ")
                .replaceAll("//.*", " ");

        Matcher pkgM = PACKAGE_PATTERN.matcher(content);
        String pkg = null;
        if (pkgM.find()) pkg = pkgM.group(1);

        Matcher tm = TYPE_DECL_PATTERN.matcher(content);
        if (!tm.find()) return;
        String kind = tm.group(1);
        String simpleName = tm.group(2);
        String fullName = (pkg == null || pkg.isEmpty()) ? simpleName : pkg + "." + simpleName;
        ClassInfo ci = nameToClass.get(fullName);
        if (ci == null) return;

        int typeStart = content.indexOf('{', tm.start());
        if (typeStart < 0) return;
        int typeEnd = findMatchingBrace(content, typeStart);
        if (typeEnd < 0) typeEnd = content.length();
        String body = content.substring(typeStart + 1, typeEnd);

        // Remove inner class/enum/interface bodies first (to avoid capturing their methods)
        String pruned = removeNestedTypes(body);
        // We need signatures; strip method bodies but keep signature lines
        String bodyNoBodies = keepMethodSignaturesOnly(pruned);

        // Regex for methods (includes constructors, abstract/interface methods)
        Pattern methodPattern = Pattern.compile(
                "(?m)^\\s*(?:@\\S+\\s+)*" +
                "(?:(public|protected|private)\\s+)?" +
                "(?:(static)\\s+)?" +
                "(?:(abstract)\\s+)?" +
                "(?:(?<ret>[A-Za-z0-9_\\.<>,\\[\\]\\s]+)\\s+)?" +
                "(?<name>" + Pattern.quote(simpleName) + "|[A-Za-z0-9_]+)\\s*" +
                "\\((?<params>[^)]*)\\)" +
                "(?:\\s*throws\\s+[^{;]+)?\\s*[;{]\s*$");

        for (String line : Arrays.asList(bodyNoBodies.split("\n"))) {
            Matcher mm = methodPattern.matcher(line);
            if (!mm.find()) continue;
            MethodInfo mi = new MethodInfo();
            mi.visibility = mm.group(1);
            mi.isStatic = mm.group(2) != null;
            mi.isAbstract = mm.group(3) != null || "interface".equals(kind);
            String ret = mm.group("ret");
            String methodName = mm.group("name");
            String params = mm.group("params") == null ? "" : mm.group("params").trim();

            boolean isConstructor = methodName.equals(simpleName) && (ret == null || ret.trim().isEmpty());
            mi.returnType = isConstructor ? "" : (ret == null ? "void" : ret.trim().replaceAll("\\s+", " "));
            mi.name = methodName;
            mi.params = sanitizeParams(params);

            // Skip obvious non-methods (e.g., control structures)
            if (mi.name.matches("if|for|while|switch|catch|try")) continue;

            ci.methods.add(mi);
        }
    }

    private static int findMatchingBrace(String s, int openIdx) {
        int depth = 0;
        for (int i = openIdx; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{') depth++;
            else if (c == '}') {
                depth--;
                if (depth == 0) return i;
            }
        }
        return -1;
    }

    private static String stripMethodBodies(String body) {
        StringBuilder out = new StringBuilder();
        int i = 0;
        int length = body.length();
        while (i < length) {
            int paren = body.indexOf('(', i);
            int semi = body.indexOf(';', i);
            if (paren == -1 || (semi != -1 && semi < paren)) {
                // Next is a statement ending with ;, copy it
                if (semi == -1) {
                    out.append(body.substring(i));
                    break;
                } else {
                    out.append(body, i, semi + 1);
                    i = semi + 1;
                }
            } else {
                // Potential method or constructor signature. Find the next '{'
                int brace = body.indexOf('{', paren);
                int endSemiBeforeBrace = semi;
                if (brace == -1) {
                    // Not a method body we can strip; copy rest and break
                    out.append(body.substring(i));
                    break;
                }
                // Copy the signature line up to '{' as a placeholder newline
                out.append('\n');
                int endBrace = findMatchingBrace(body, brace);
                if (endBrace == -1) {
                    i = brace + 1;
                } else {
                    i = endBrace + 1;
                }
            }
        }
        return out.toString();
    }

    // Removes nested type blocks (class/interface/enum) to avoid parsing their methods/fields
    private static String removeNestedTypes(String body) {
        StringBuilder out = new StringBuilder();
        int i = 0;
        while (i < body.length()) {
            int idx = body.indexOf("class ", i);
            int idxI = body.indexOf("interface ", i);
            int idxE = body.indexOf("enum ", i);
            int next = minPos(idx, minPos(idxI, idxE));
            if (next == -1) {
                out.append(body.substring(i));
                break;
            }
            // copy up to next nested type
            out.append(body, i, next);
            int brace = body.indexOf('{', next);
            if (brace == -1) {
                // no body, stop
                break;
            }
            int end = findMatchingBrace(body, brace);
            if (end == -1) {
                // malformed, stop
                break;
            }
            // skip nested block
            i = end + 1;
        }
        return out.toString();
    }

    // Keeps only lines with method signatures; strips method bodies
    private static String keepMethodSignaturesOnly(String body) {
        StringBuilder out = new StringBuilder();
        int i = 0;
        while (i < body.length()) {
            int paren = body.indexOf('(', i);
            int semi = body.indexOf(';', i);
            if (paren == -1 && semi == -1) {
                break;
            }
            if (paren != -1 && (semi == -1 || paren < semi)) {
                int brace = body.indexOf('{', paren);
                int endSemi = body.indexOf(';', paren);
                if (brace == -1 && endSemi == -1) {
                    break;
                }
                int sigEnd = brace != -1 ? brace : endSemi;
                if (sigEnd == -1) sigEnd = paren;
                // backtrack to line start
                int lineStart = body.lastIndexOf('\n', Math.max(i, sigEnd - 1));
                lineStart = (lineStart == -1) ? i : lineStart + 1;
                String sig = body.substring(lineStart, sigEnd + 1);
                out.append(sig).append('\n');
                if (brace != -1) {
                    int endBrace = findMatchingBrace(body, brace);
                    i = (endBrace == -1) ? (brace + 1) : (endBrace + 1);
                } else {
                    i = sigEnd + 1;
                }
            } else {
                // just a statement ending with ;, skip
                i = semi + 1;
            }
        }
        return out.toString();
    }

    private static int minPos(int a, int b) {
        if (a == -1) return b;
        if (b == -1) return a;
        return Math.min(a, b);
    }

    private static void writePlantUML(Path outFile, Map<String, ClassInfo> nameToClass) throws IOException {
        // Build package to classes index and a set for quick FQN membership lookup
        Map<String, List<ClassInfo>> pkgIndex = new HashMap<>();
        Set<String> fqns = new HashSet<>(nameToClass.keySet());
        for (ClassInfo ci : nameToClass.values()) {
            pkgIndex.computeIfAbsent(ci.packageName, k -> new ArrayList<>()).add(ci);
        }

        try (BufferedWriter w = new BufferedWriter(new FileWriter(outFile.toFile(), StandardCharsets.UTF_8))) {
            w.write("@startuml\n");
            w.write("hide empty members\n");
            w.write("set namespaceSeparator .\n");
            w.write("skinparam packageStyle rectangle\n\n");

            // Emit packages and classes
            for (Map.Entry<String, List<ClassInfo>> e : pkgIndex.entrySet()) {
                String pkg = e.getKey();
                List<ClassInfo> classes = e.getValue();
                String pkgLabel = pkg == null || pkg.isEmpty() ? "(default)" : pkg;
                if (pkg != null && !pkg.isEmpty()) {
                    w.write("package \"" + pkgLabel + "\" {\n");
                }
                for (ClassInfo ci : classes) {
                    String keyword = ci.kind == null ? "class" : ci.kind;
                    String alias = ci.simpleName;
                    String fqn = ci.fullName;
                    w.write(keyword + " \"" + fqn + "\" as " + alias + " {\n");
                    for (FieldInfo fi : ci.fields) {
                        w.write("  - " + sanitize(fi.type) + " " + fi.name + "\n");
                    }
                    for (MethodInfo mi : ci.methods) {
                        String vis = visibilitySymbol(mi.visibility);
                        String staticMark = mi.isStatic ? " {static}" : "";
                        String ret = mi.returnType == null ? "" : mi.returnType;
                        String sig = mi.name + "(" + mi.params + ")";
                        if (ret.isEmpty()) {
                            w.write("  " + vis + " " + sig + staticMark + "\n");
                        } else {
                            w.write("  " + vis + " " + sig + " : " + sanitize(ret) + staticMark + "\n");
                        }
                    }
                    w.write("}\n");
                }
                if (pkg != null && !pkg.isEmpty()) {
                    w.write("}\n\n");
                } else {
                    w.write("\n");
                }
            }

            // Emit extends and implements relations
            for (ClassInfo ci : nameToClass.values()) {
                String from = ci.simpleName;
                if (ci.extendsType != null) {
                    String target = resolveType(ci.extendsType, ci.packageName, fqns);
                    if (target != null) {
                        String targetAlias = nameToClass.get(target).simpleName;
                        w.write(from + " --|> " + targetAlias + "\n");
                    }
                }
                for (String itf : ci.implementsTypes) {
                    String target = resolveType(itf, ci.packageName, fqns);
                    if (target != null) {
                        String targetAlias = nameToClass.get(target).simpleName;
                        w.write(from + " ..|> " + targetAlias + "\n");
                    }
                }
            }

            // Emit field associations (only to known project types)
            for (ClassInfo ci : nameToClass.values()) {
                String from = ci.simpleName;
                for (FieldInfo fi : ci.fields) {
                    String baseType = baseTypeOf(fi.type);
                    String target = resolveType(baseType, ci.packageName, fqns);
                    if (target != null && !target.equals(ci.fullName)) {
                        String targetAlias = nameToClass.get(target).simpleName;
                        w.write(from + " --> " + targetAlias + " : " + fi.name + "\n");
                    }
                }
            }

            w.write("\n@enduml\n");
        }
    }

    private static String resolveType(String type, String currentPackage, Set<String> fqns) {
        String cleaned = type.trim();
        cleaned = baseTypeOf(cleaned);
        if (cleaned.contains(".")) {
            // Fully qualified candidate
            if (fqns.contains(cleaned)) return cleaned;
            return null;
        }
        // Try same package
        String samePkg = (currentPackage == null || currentPackage.isEmpty()) ? cleaned : currentPackage + "." + cleaned;
        if (fqns.contains(samePkg)) return samePkg;
        // Try to match by simple name (unique match)
        String match = null;
        for (String fqn : fqns) {
            if (fqn.endsWith("." + cleaned) || fqn.equals(cleaned)) {
                if (match != null && !match.equals(fqn)) {
                    // ambiguous
                    return null;
                }
                match = fqn;
            }
        }
        return match;
    }

    private static String baseTypeOf(String raw) {
        String t = raw.trim();
        // Remove generics
        t = t.replaceAll("<.*>", "");
        // Take last token if array or varargs
        t = t.replace("[]", "");
        t = t.replace("...", "");
        // Remove qualifiers like ? extends
        t = t.replace("?", "").replace("extends", "").replace("super", "").trim();
        // Keep simple type name
        String[] parts = t.split("\\s+");
        if (parts.length > 0) t = parts[parts.length - 1];
        return t;
    }

    private static String sanitize(String s) {
        return s.replace("\n", " ").replace("\r", " ");
    }

    private static String sanitizeParams(String params) {
        String p = params.trim();
        if (p.isEmpty()) return "";
        // compress spaces and remove annotations in params to keep it readable
        p = p.replaceAll("@\\S+", "");
        p = p.replaceAll("\\s+", " ").trim();
        return p;
    }

    private static String visibilitySymbol(String visibility) {
        if ("public".equals(visibility)) return "+";
        if ("protected".equals(visibility)) return "#";
        if ("private".equals(visibility)) return "-";
        return "~"; // package
    }

    private static String trimOrNull(String s) {
        if (s == null) return null;
        String t = s.trim();
        return t.isEmpty() ? null : t;
    }
}


