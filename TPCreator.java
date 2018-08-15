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
import java.util.HashMap;
import javax.imageio.ImageIO;

/**
 *
 * @author lars
 */
public class TPCreator {

    public static HashMap<String,Color> overlays=new HashMap();
    static {
        overlays.put("default_fence_overlay.png",new Color(255,126,126));
        overlays.put("farming_cotton.png",new Color(0,0,0));
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        File[] mods=new File(System.getProperty("user.home")+"/.minetest/games/magicctf/mods").listFiles();
        float REDUCE=8;
        for (File mod:mods) {
            File tex=new File(mod.getCanonicalPath()+File.separator+"textures");
            if (!tex.exists()) {
                continue;
            }
            
            for (File img:tex.listFiles()) {
                Color c=overlays.get(img.getName());
                BufferedImage image=ImageIO.read(img);
                if (image==null) { System.out.println(img);continue; }
                String ext="";

                for (int i=img.getName().length()-1; i > -1; i--) {
                    if (img.getName().charAt(i) == '.') {
                        break;
                    }
                    ext=img.getName().charAt(i)+ext;
                }
                BufferedImage result;
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
                        if (!(c != null && c.getRed() == col.getRed() && c.getBlue() == col.getBlue() && c.getGreen() == col.getGreen()) && col.getAlpha() != 0) {
                        float r=Math.round(col.getRed()/255.0f*REDUCE)/REDUCE;
                        float g=Math.round(col.getGreen()/255.0f*REDUCE)/REDUCE;
                        float b=Math.round(col.getBlue()/255.0f*REDUCE)/REDUCE;
                        col=new Color(r,g,b,col.getAlpha()/255.0f);
                        }
                        else { col=new Color(0,0,0,0); }
                        result.setRGB(x,y,col.getRGB());
                    }
                }
                } catch (Exception e) {System.out.println(img.getName());continue;}
                try {
                if (!ImageIO.write(result, ext, new File(System.getProperty("user.home")+"/.minetest/textures/High Contrast/"+img.getName()))) { System.out.println("FAIL"); }
                
                } catch (Exception e) {System.out.println(img.getName());}
            }
        }
    }
    
}
