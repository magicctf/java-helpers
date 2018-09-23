/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generateletters;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author lars
 */
public class GenerateLetters {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        JFrame frame=new JFrame("Fonts");
        JLabel label=new JLabel("The quick brown fox jumps over the lazy dog.");
        label.setFont(new Font("FreeMono",0,64));
        frame.add(label);
        frame.pack();
        for (char c=0; c < 256; c++) {
            BufferedImage img=new BufferedImage(48,64,BufferedImage.TYPE_INT_ARGB);
            label.setText(c+"");
            label.paint(img.createGraphics());
            new File("freemono_"+(int)c+".png").createNewFile();
            ImageIO.write(img, "PNG", new File("freemono_"+(int)c+".png"));
            
        }
        frame.setVisible(true);
    }
    
}

class GenerateLetter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        JLabel label=new JLabel(args[0]);
        label.setFont(new Font("FreeMono",0,64));
        BufferedImage img=new BufferedImage(48*args[0].length(),64,BufferedImage.TYPE_INT_ARGB);
        label.paint(img.createGraphics());
        ImageIO.write(img, "PNG", new File(args[1]));
    }
    
}
