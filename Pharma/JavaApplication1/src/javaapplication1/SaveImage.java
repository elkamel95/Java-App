/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author 2
 */
//modifier dimension d'une image et enregistre dans un racin pre défini
public class SaveImage {
      File f ;
      private static BufferedImage resizeImage(BufferedImage originalImage, int type){
	BufferedImage resizedImage = new BufferedImage(100 , 100, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, 100, 100, null);
	g.dispose();
		
	return resizedImage;
    }
      public   File save(String ImageName,String name){
        try{
             File imgPath = new File(ImageName);
             BufferedImage img = new BufferedImage( 500 , 500, BufferedImage.TYPE_INT_RGB );

img= ImageIO.read(imgPath);

             f = new File("image/Produit/"+name+".jpg");
 
            ImageIO.write(resizeImage(img,img.getType()), "JPG", f);
        }
        catch(Exception e){
            e.printStackTrace();
        }
             return f;
}}
