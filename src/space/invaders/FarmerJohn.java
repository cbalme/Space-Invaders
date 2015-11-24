/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.invaders;

import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Oliver
 */
public class FarmerJohn {
    public FarmerJohn(Image image, int x, int y){
       this.john = image;
       this.x = x;
       this.y = y;
       
    }
    
    Image john;
    int x;
    int y;
    
    public void draw(Graphics graphics){
        
        if (john !=null) {
            graphics.drawImage(john, x, y, null);
            
        }
    }
}
