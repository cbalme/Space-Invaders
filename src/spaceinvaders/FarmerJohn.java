/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import environment.Direction;
import grid.Grid;
import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author Oliver
 */
public class FarmerJohn {

    private final MoveValidatorIntf validator;
    private Grid grid;

    /**
     *
     * @param image
     * @param x
     * @param y
     * @param validator
     */
    public FarmerJohn(Image image, int x, int y, Grid grid, MoveValidatorIntf validator){
       this.john = image;
       this.x = x;
       this.y = y;
       this.grid = grid;
       this.validator = validator;
       
    }
    
    Image john;
    private Direction direction;
    int x;
    int y;
    
    public void draw(Graphics graphics){
        
        if (john !=null) {
            graphics.drawImage(john, x, y, null);
            
        }
    }
   public void move(int move){
        if (getDirection() == Direction.RIGHT) {
           x++;
       }
        if (getDirection() == Direction.LEFT) {
           x--;
       }
    }

    /**
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    
}
