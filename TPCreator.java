/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appguru;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author lars
 */
public class TPCreator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File[] mods=new File("magicctf/mods").listFiles();
        float REDUCE=8;
        for (File mod:mods) {
            File tex=new File(mod.getCanonicalPath()+File.separator+"textures");
            if (!tex.exists()) {
                continue;
            }
            
            for (File img:tex.listFiles()) {
                BufferedImage image=ImageIO.read(img);
                if (image==null) { System.out.println(img);continue; }
                String ext="";

                for (int i=img.getName().length()-1; i > -1; i--) {
                    if (img.getName().charAt(i) == '.') {
                        break;
                    }
                    ext=img.getName().charAt(i)+ext;
                }
                BufferedImage result=null;
                if (ext.equals("png")) {
                result=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_ARGB);
                }
                else {
             result=new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
                }
                try {
                for (int x=0; x < image.getWidth(); x++) {
                    for (int y=0; y < image.getHeight(); y++) {
                        Color col=new Color(image.getRGB(x, y),true);
                        float r=Math.round(col.getRed()/255.0f*REDUCE)/REDUCE;
                        float g=Math.round(col.getGreen()/255.0f*REDUCE)/REDUCE;
                        float b=Math.round(col.getBlue()/255.0f*REDUCE)/REDUCE;
                        col=new Color(r,g,b,col.getAlpha()/255.0f);
                        result.setRGB(x,y,col.getRGB());
                    }
                }
                } catch (Exception e) {System.out.println(img.getName());continue;}
                try {
                if (!ImageIO.write(result, ext, new File("tp/"+img.getName()))) { System.out.println("FAIL"); }
                
                } catch (Exception e) {System.out.println(img.getName());}
            }
        }
    }
    
}
