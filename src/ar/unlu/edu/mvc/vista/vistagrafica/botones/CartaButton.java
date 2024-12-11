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

    public CartaButton(String path, TipoCarta tipo)  {
        this.imageIcon= new ImageIcon(path);
        switch (tipo) {
            case CARNAVAL -> configurarBoton( tipo,WIDTH_CARNAVAL, HEIGHT_CARNAVAL, 0, 0, 0, 0 );
            case MANO -> configurarBoton(tipo, WIDTH_MANO, HEIGHT_MANO, 3, 3, 3, 3);
            case DORSO_VERTICAL -> configurarBoton(tipo, WIDTH_DORSO_VERTICAL, HEIGHT_DORSO_VERTICAL, 0, 0, 0, 0);
            case NUM_AREA_VERTICAL -> recortarImagen( path);
            case ULTIMA_AREA_VERTICAL -> configurarBoton(tipo, WIDTH_ULTIMA_NUMERO_VERTICAL, HEIGHT_ULTIMA_NUMERO_VERTICAL, -1, 0, 0, 0);
            case NUM_AREA_HORIZONTAL_IZQ-> recortarConRotacion(path, 90);
            case NUM_AREA_HORIZONTAL_DER -> recortarConRotacion(path, -90);
            case ULTIMA_AREA_HORIZONTAL_DER-> configurarConRotacion(90);
            case ULTIMA_AREA_HORIZONTAL_IZQ -> configurarConRotacion(-90);
        }

        this.setDisabledIcon(imageIcon);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
    }

    private void configurarBoton(TipoCarta tipo,int width, int height, int top, int left, int bottom, int right) {
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        setIcon(imageIcon);
        this.setSize(width, height);
        setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
        if (tipo.equals(TipoCarta.CARNAVAL) || tipo.equals(TipoCarta.MANO)) {
            setBorder(BorderFactory.createLineBorder(new Color(201, 217, 5), 5));
        }
    }

    private void recortarImagen(String path) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(path));
            BufferedImage croppedImage = originalImage.getSubimage(0, 1, 200, 56);
            Image scaledImage = croppedImage.getScaledInstance(CartaButton.WIDTH_NUMERO_VERTICAL, CartaButton.HEIGHT_NUMERO_VERTICAL, Image.SCALE_SMOOTH);
            setIcon(new ImageIcon(scaledImage));
            setBorder(BorderFactory.createEmptyBorder(-1, 0, -1, 0));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void recortarConRotacion(String path, int angulo) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(path));
            BufferedImage croppedImage = originalImage.getSubimage(0, 0, 200, 56);
            Image scaledImage = croppedImage.getScaledInstance(CartaButton.WIDTH_NUMERO_HORIZONTAL, CartaButton.HEIGHT_NUMERO_HORIZONTAL, Image.SCALE_SMOOTH);
            ImageIcon imagenCortada = new ImageIcon(scaledImage);
            Image image = rotarImagen(imagenCortada, angulo);
            setIcon(new ImageIcon(image));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }

    private void configurarConRotacion(int angulo) {
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(CartaButton.WIDTH_ULTIMA_NUMERO_HORIZONTAL, CartaButton.HEIGHT_ULTIMA_NUMERO_HORIZONTAL, Image.SCALE_SMOOTH));
        Image image = rotarImagen(imageIcon, angulo);
        setIcon(new ImageIcon(image));
        this.setSize(CartaButton.WIDTH_ULTIMA_NUMERO_HORIZONTAL, CartaButton.HEIGHT_ULTIMA_NUMERO_HORIZONTAL);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
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



