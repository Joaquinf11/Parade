package ar.unlu.edu.mvc.vista.botones;


import javax.swing.*;
import java.awt.*;


public class CartaButton extends JButton {
    private static final int WIDTH_CARNAVAL= 110;
    private static final int HEIGHT_CARNAVAL= 135;

    private static final int WIDTH_MANO= 90;
    private static  final int HEIGHT_MANO= 115;

    private static final int WIDTH_DORSO_VERTICAL=60;
    private static final int  HEIGHT_DORSO_VERTICAL=85;



    private  final int WIDTH_NUMERO_HORIZONTAL=85;
    private final int  HEIGHT_NUMERO_HORIZONTAL=60;

    private final int WIDTH_NUMERO_VERTICAL=200;
    private final int HEIGHT_NUMERO_VERTICAL=56;

    ImageIcon imageIcon;


    public CartaButton(String path, String tipo)  {
        this.imageIcon= new ImageIcon(path);

        switch (tipo) {
            case "carnaval" -> {
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH_CARNAVAL, HEIGHT_CARNAVAL, Image.SCALE_SMOOTH));
                setIcon(imageIcon);
                this.setSize(WIDTH_CARNAVAL, HEIGHT_CARNAVAL);
            }
            case "mano" -> {
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH_MANO, HEIGHT_MANO, Image.SCALE_SMOOTH));
                setIcon(imageIcon);
                this.setSize(WIDTH_MANO, HEIGHT_MANO);
            }
            case "dorso vertical" -> {
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH_DORSO_VERTICAL, HEIGHT_DORSO_VERTICAL, Image.SCALE_SMOOTH));
                setIcon(imageIcon);
                this.setSize(WIDTH_DORSO_VERTICAL, HEIGHT_DORSO_VERTICAL);
            }
        }

//        else if(tipo.equals("numero horizonal")){
//            // Cargar la imagen completa desde el archivo
//            BufferedImage originalImage = ImageIO.read(new File(path));
//
//            // Recortar la imagen a la región deseada
//            BufferedImage croppedImage = originalImage.getSubimage(0, 0, WIDTH_NUMERO_HORIZONTAL,HEIGHT_NUMERO_HORIZONTAL);
//
//            // Escalar la imagen recortada al tamaño del botón (opcional)
//            Image scaledImage = croppedImage.getScaledInstance(WIDTH_NUMERO_HORIZONTAL,HEIGHT_NUMERO_HORIZONTAL, Image.SCALE_SMOOTH);
//            this.setIcon(new ImageIcon(scaledImage));
//        }
//        else if(tipo.equals("numero vertical")){
//            // Cargar la imagen completa desde el archivo
//            BufferedImage originalImage = ImageIO.read(new File(path));
//
//            // Recortar la imagen a la región deseada
//            BufferedImage croppedImage = originalImage.getSubimage(0, 0, WIDTH_NUMERO_VERTICAL,HEIGHT_NUMERO_VERTICAL);
//
//            // Escalar la imagen recortada al tamaño del botón (opcional)
//            Image scaledImage = croppedImage.getScaledInstance(WIDTH_NUMERO_VERTICAL,HEIGHT_NUMERO_VERTICAL, Image.SCALE_SMOOTH);
//            this.setIcon(new ImageIcon(scaledImage));
//        }
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
    }


}
