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
import java.awt.Point;

/**
 *
 * @author Oliver
 */
public class FarmerJohn {

    /**
     *
     * @param image
     * @param x
     * @param y
     * @param validator
     */
    public FarmerJohn(Image image, int x, int y, Grid grid, MoveValidatorIntf validator) {
        this.john = image;
        this.x = x;
        this.y = y;
        this.grid = grid;
        this.validator = validator;

    }

    public void draw(Graphics graphics) {

        if (john != null) {
            graphics.drawImage(john, getX(), getY(), null);

        }
    }

    public void move(int move) {
        if (getDirection() == Direction.RIGHT) {
            setX(getX() + move);
        }
        if (getDirection() == Direction.LEFT) {
            setX(getX() - move);
        }
        if (getDirection() == Direction.UP) {
            setY(getY() - move);
        }
        if (getDirection() == Direction.DOWN) {
            setY(getY() + move);
        }
    }


//<editor-fold defaultstate="collapsed" desc="Properties">
    private final MoveValidatorIntf validator;
    private Grid grid;
    
    Image john;
    private Direction direction;
    private int speed;
    private int x;
    private int y;

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
    
    /**
     * @return the speed
     */
    public int getSpeed() {
        return speed;
    }
    
    /**
     * @param speed the speed to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }
    
    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * @return the y
     */
    public int getY() {
        return y;
    }
    
    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    /**
     * @return the Point that is JOhn's location
     */
    public Point getLocation() {
        return new Point(x, y);
    }
    
//</editor-fold>
}
