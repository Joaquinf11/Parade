param(
    [string]$SrcPath = "src",
    [string]$OutPuml = "diagram.puml"
)

Write-Host "[UML] Compilando generador..."
$ErrorActionPreference = "Stop"

$ScriptDir = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $ScriptDir

$BuildDir = Join-Path $ScriptDir "out\umlgen"
New-Item -ItemType Directory -Force -Path $BuildDir | Out-Null

if (-not (Test-Path (Join-Path $ScriptDir "src\PlantUMLGenerator.java"))) {
    throw "No se encontró src\PlantUMLGenerator.java"
}

javac -d "$BuildDir" "src\PlantUMLGenerator.java"

Write-Host "[UML] Generando $OutPuml desde '$SrcPath'..."
java -cp "$BuildDir" PlantUMLGenerator "$SrcPath" "$OutPuml"

# Intentar renderizar con PlantUML si está disponible
$PlantUmlJar = Join-Path $ScriptDir "lib\plantuml.jar"
$PumlCmd = $null
if (Test-Path $PlantUmlJar) {
    $PumlCmd = "java -jar `"$PlantUmlJar`" `"$OutPuml`""
} elseif (Get-Command plantuml -ErrorAction SilentlyContinue) {
    $PumlCmd = "plantuml `"$OutPuml`""
}

if ($PumlCmd) {
    Write-Host "[UML] Renderizando PNG con PlantUML..."
    powershell -NoProfile -Command $PumlCmd | Out-Null
    if (Test-Path (Join-Path $ScriptDir ([System.IO.Path]::ChangeExtension($OutPuml, ".png")))) {
        Write-Host "[UML] Renderizado: " (Join-Path $ScriptDir ([System.IO.Path]::ChangeExtension($OutPuml, ".png")))
    } else {
        Write-Host "[UML] No se generó PNG. Verifique instalación de PlantUML/Graphviz."
    }
} else {
    Write-Host "[UML] PlantUML no encontrado. Se generó solo el archivo .puml."
}

Write-Host "[UML] Listo."


