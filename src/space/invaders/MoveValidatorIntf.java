/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.invaders;

import java.awt.Point;

/**
 *
 * @author Oliver
 */
public interface MoveValidatorIntf {
    public Point validateMove(Point proposedLocation);
    
}
