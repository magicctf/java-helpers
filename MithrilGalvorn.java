/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mithrilgalvorn;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author lars
 */
public class MithrilGalvorn {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String[][] parts = {{"3d_armor", "chestplate", "leggings", "boots", "helmet"}, {"shields", "shield"}};
        Object[][] colors = {{"mithril", 300, 300, 255}, {"galvorn", 33, 33, 33}};
        for (String[] pa: parts) {
            for (int i = 1; i < pa.length; i++) {
                String p=pa[i];
                for (String suffix : new String[]{".png", "_preview.png"}) {
                    String file = pa[0]+"_" + p + "_steel" + suffix;
                    System.out.println(System.getProperty("user.home") + "/.minetest/games/magicctf/mods/"+pa[0]+"/textures/" + file);
                    BufferedImage im = ImageIO.read(new File(System.getProperty("user.home") + "/.minetest/games/magicctf/mods/"+pa[0]+"/textures/" + file));
                    for (Object[] o : colors) {
                        BufferedImage res = new BufferedImage(im.getWidth(), im.getHeight(), BufferedImage.TYPE_INT_ARGB);
                        for (int x = 0; x < im.getWidth(); x++) {
                            for (int y = 0; y < im.getHeight(); y++) {
                                Color col = new Color(im.getRGB(x, y), true);
                                float r = Math.min(1, ((int) (o[1]) / 255.0f) * (float) col.getRed() / 255.0f);
                                float g = Math.min(1, ((int) (o[2]) / 255.0f) * (float) col.getGreen() / 255.0f);
                                float b = Math.min(1, ((int) (o[3]) / 255.0f) * (float) col.getBlue() / 255.0f);
                                if (col.getAlpha() != 0 && col.getRed() != 0 && col.getBlue() != 0 && col.getGreen() != 0) {
                                    col = new Color(r, g, b, col.getAlpha() / 255.0f);
                                } else {
                                    col = new Color(0, 0, 0, 0);
                                }
                                res.setRGB(x, y, col.getRGB());
                            }
                        }
                        ImageIO.write(res, "PNG", new File(System.getProperty("user.home") + "/.minetest/games/magicctf/mods/"+pa[0]+"/textures/"+pa[0]+"_" + p + "_" + o[0].toString() + suffix));
                    }
                }
            }
        }
    }

}
