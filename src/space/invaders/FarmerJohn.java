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
    
    Image john;
    public void draw(Graphics graphics){
        
    john=ResourceTools.loadImageFromResource(Farmer John (1).png);
    }
}
