package ar.unlu.edu.mvc.vista.botones;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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



    private  final int WIDTH_NUMERO_HORIZONTAL=85;
    private final int  HEIGHT_NUMERO_HORIZONTAL=60;

    private final int WIDTH_NUMERO_VERTICAL=60;
    private final int HEIGHT_NUMERO_VERTICAL=30;

    ImageIcon imageIcon;


    public CartaButton(String path, String tipo)  {
        this.imageIcon= new ImageIcon(path);

        switch (tipo) {
            case "carnaval" -> {
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH_CARNAVAL, HEIGHT_CARNAVAL, Image.SCALE_SMOOTH));
                setIcon(imageIcon);
                this.setSize(WIDTH_CARNAVAL, HEIGHT_CARNAVAL);
                setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
            }
            case "mano" -> {
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH_MANO, HEIGHT_MANO, Image.SCALE_SMOOTH));
                setIcon(imageIcon);
                this.setSize(WIDTH_MANO, HEIGHT_MANO);
                setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
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
                setBorder(BorderFactory.createEmptyBorder(-1,0,-1,0));
            }

            case "ultima area vertical" -> {
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH_NUMERO_VERTICAL, 80, Image.SCALE_SMOOTH));
                setBorder(BorderFactory.createEmptyBorder(-1,0,0,0));
                setIcon(imageIcon);
                this.setSize(60, 85);
            }
        }

////        else if(tipo.equals("numero horizonal")){
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

        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
    }


}
