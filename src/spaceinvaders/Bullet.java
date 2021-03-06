/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import environment.Direction;
import environment.Velocity;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Oliver
 */
public class Bullet {

    public Bullet(int x, int y, Velocity velocity) {
        this.x = x;
        this.y = y;
        this.velocity = velocity;
    }

    public void move() {
        x += velocity.x;
        y += velocity.y;
    }

    public void draw(Graphics graphics) {
        graphics.fillRect(x, y, 4, 4);
    }

//<editor-fold defaultstate="collapsed" desc="Properties">
    private int x;
    private int y;
    private int speed;
    private final Velocity velocity;

    /**
     * @return the location
     */
    public Rectangle getHitBox() {
        return new Rectangle(x, y, 4, 4);
    }

    /**
     * @return the location
     */
    public Point getLocation() {
        return new Point(x, y);
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
//</editor-fold>

}
