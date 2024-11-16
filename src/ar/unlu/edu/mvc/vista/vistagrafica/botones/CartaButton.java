package ar.unlu.edu.mvc.vista.vistagrafica.botones;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class CartaButton extends JButton {
    private static final int WIDTH_CARNAVAL= 95;
    private static final int HEIGHT_CARNAVAL= 125;

    private static final int WIDTH_MANO= 90;
    private static  final int HEIGHT_MANO= 115;

    private static final int WIDTH_DORSO_VERTICAL=60;
    private static final int  HEIGHT_DORSO_VERTICAL=85;



    private static final int WIDTH_NUMERO_HORIZONTAL=60;
    private static  final int  HEIGHT_NUMERO_HORIZONTAL=30;

    private static final int WIDTH_NUMERO_VERTICAL=60;
    private static final int HEIGHT_NUMERO_VERTICAL=30;

    private static final int WIDTH_ULTIMA_NUMERO_VERTICAL=60;
    private static final int HEIGHT_ULTIMA_NUMERO_VERTICAL=80;

    private static final int WIDTH_ULTIMA_NUMERO_HORIZONTAL=60;
    private static final int HEIGHT_ULTIMA_NUMERO_HORIZONTAL=80;

    ImageIcon imageIcon;


    public CartaButton(String path, String tipo)  {
        this.imageIcon= new ImageIcon(path);
        switch (tipo) {
            case "carnaval" -> {
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH_CARNAVAL, HEIGHT_CARNAVAL, Image.SCALE_SMOOTH));
                setIcon(imageIcon);
                this.setSize(WIDTH_CARNAVAL, HEIGHT_CARNAVAL);
                setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            }
            case "mano" -> {
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH_MANO, HEIGHT_MANO, Image.SCALE_SMOOTH));
                setIcon(imageIcon);
                this.setSize(WIDTH_MANO, HEIGHT_MANO);
                setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
            }
            case "dorso vertical" -> {
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH_DORSO_VERTICAL, HEIGHT_DORSO_VERTICAL, Image.SCALE_SMOOTH));
                setIcon(imageIcon);
                this.setSize(WIDTH_DORSO_VERTICAL, HEIGHT_DORSO_VERTICAL);
            }
            case "numero area vertical" -> {

                // Cargar la imagen completa desde el archivo
                BufferedImage originalImage = null;
                try {
                    originalImage = ImageIO.read(new File(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Recortar la imagen a la región deseada
                BufferedImage croppedImage = originalImage.getSubimage(0, 1, 200, 56);

                // Escalar la imagen recortada al tamaño del botón (opcional)
                Image scaledImage = croppedImage.getScaledInstance(WIDTH_NUMERO_VERTICAL, HEIGHT_NUMERO_VERTICAL, Image.SCALE_SMOOTH);
                this.setIcon(new ImageIcon(scaledImage));
                setBorder(BorderFactory.createEmptyBorder(-1, 0, -1, 0));
            }

            case "ultima area vertical" -> {
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH_ULTIMA_NUMERO_VERTICAL, HEIGHT_ULTIMA_NUMERO_VERTICAL, Image.SCALE_SMOOTH));
                setBorder(BorderFactory.createEmptyBorder(-1, 0, 0, 0));
                setIcon(imageIcon);
                this.setSize(WIDTH_ULTIMA_NUMERO_VERTICAL, HEIGHT_ULTIMA_NUMERO_VERTICAL);
            }
            case "numero area horizontal izquierda" , "numero area horizontal derecha"-> { // tengo que divir entre izquierda y derecha agrego un || y despues pregunto si es izquierda o derecha para pasarle el angulo de rotacion

                int angulo=0;
                if(tipo.equals("numero area horizontal izquierda")){
                    angulo=90;
                }
                else if (tipo.equals("numero area horizontal derecha")){
                    angulo= -90;
                }

                // Cargar la imagen completa desde el archivo
                BufferedImage originalImage = null;
                try {
                    originalImage = ImageIO.read(new File(path));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Recortar la imagen a la región deseada
                BufferedImage croppedImage = originalImage.getSubimage(0, 0, 200, 56);

                // Escalar la imagen recortada al tamaño del botón (opcional)
                Image scaledImage = croppedImage.getScaledInstance(WIDTH_NUMERO_HORIZONTAL, HEIGHT_NUMERO_HORIZONTAL, Image.SCALE_SMOOTH);
                ImageIcon imagenCortada= new ImageIcon(scaledImage);

                Image image =rotarImagen(imagenCortada,angulo);
                setIcon(new ImageIcon(image));
                setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            }
            case "ultima area horizontal derecha" , "ultima area horizontal izquierda" ->{
                int angulo;
                if (tipo.equals("ultima area horizontal derecha")){
                    angulo=-90;
                }
                else {
                    angulo=90;
                }
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH_ULTIMA_NUMERO_HORIZONTAL, HEIGHT_ULTIMA_NUMERO_HORIZONTAL, Image.SCALE_SMOOTH));
                setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
                Image image=rotarImagen(imageIcon,angulo);
                setIcon(new ImageIcon(image));
                this.setSize(WIDTH_ULTIMA_NUMERO_HORIZONTAL, HEIGHT_ULTIMA_NUMERO_HORIZONTAL);

            }

        }

       // this.setDisabledIcon(imageIcon); //USAR DESPUES es para que cuando use setDisable no se pone gris
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
    }


    private Image rotarImagen(ImageIcon icono, int angulo) {
        int w = icono.getIconWidth();
        int h = icono.getIconHeight();

        // Crear un BufferedImage más grande para evitar que se corte
        BufferedImage bufferedImage = new BufferedImage(h, w, BufferedImage.TYPE_INT_ARGB);

        // Crear la transformación de rotación
        AffineTransform transform = new AffineTransform();
        transform.translate(h / 2.0, w / 2.0);
        transform.rotate(Math.toRadians(angulo));
        transform.translate(-w / 2.0, -h / 2.0);

        // Aplicar la rotación
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        bufferedImage = op.filter(toBufferedImage(icono.getImage()), bufferedImage);

        return bufferedImage;
    }

    //  convertir Image a BufferedImage
    private BufferedImage toBufferedImage(Image img) {
        BufferedImage bufferedImage = new BufferedImage(
                img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bufferedImage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bufferedImage;
    }
}
