package ar.unlu.edu.mvc.vista.botones;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CartaButton extends JButton {
    private final int WIDTH_CARNAVAL= 110;
    private final int HEIGHT_CARNAVAL= 135;

    private final int WIDTH_MANO= 90;
    private final int HEIGHT_MANO= 115;

    private  final int WIDTH_DORSO_VERTICAL=60;
    private final int  HEIGHT_DORSO_VERTICAL=85;


    private  final int WIDTH_DORSO_HORIZONTAL=85;
    private final int  HEIGHT_DORSO_HORIZONTAL=60;


    private  final int WIDTH_NUMERO_HORIZONTAL=85;
    private final int  HEIGHT_NUMERO_HORIZONTAL=60;

    private final int WIDTH_NUMERO_VERTICAL=200;
    private final int HEIGHT_NUMERO_VERTICAL=56;

    ImageIcon imageIcon;


    public CartaButton(String path, String tipo)  {
        this.imageIcon= new ImageIcon(path);

        if (tipo.equals("carnaval")) {
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH_CARNAVAL, HEIGHT_CARNAVAL, Image.SCALE_SMOOTH));
            setIcon(imageIcon);
            this.setSize(WIDTH_CARNAVAL, HEIGHT_CARNAVAL);
        } else if (tipo.equals("mano")) {
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH_MANO, HEIGHT_MANO, Image.SCALE_DEFAULT));
            setIcon(imageIcon);
            this.setSize(WIDTH_MANO, HEIGHT_MANO);
        }
        else if(tipo.equals("dorso vertical")){
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH_DORSO_VERTICAL, HEIGHT_DORSO_VERTICAL, Image.SCALE_DEFAULT));
            setIcon(imageIcon);
            this.setSize(WIDTH_DORSO_VERTICAL, HEIGHT_DORSO_VERTICAL);
        }
        else if(tipo.equals("dorso horizontal")){
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(WIDTH_DORSO_HORIZONTAL, HEIGHT_DORSO_HORIZONTAL, Image.SCALE_DEFAULT));
            setIcon(imageIcon);
            this.setSize(WIDTH_DORSO_HORIZONTAL, HEIGHT_DORSO_HORIZONTAL);
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
