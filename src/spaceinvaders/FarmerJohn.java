/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import components.Health;
import environment.Direction;
import grid.Grid;
import images.Animator;
import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import static spaceinvaders.AnimatedImageManager.RUN_LEFT_IMAGE_NAMES;
import static spaceinvaders.AnimatedImageManager.RUN_RIGHT_IMAGE_NAMES;
import static spaceinvaders.AnimatedImageManager.STAND_LEFT_IMAGE_NAMES;
import static spaceinvaders.AnimatedImageManager.STAND_RIGHT_IMAGE_NAMES;

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
//    private FarmerJohnState state = FarmerJohnState.Right;
    private Image image;
//    private Animator animator;

    public FarmerJohn(Image image, int x, int y, Grid grid, MoveValidatorIntf validator) {
        this.john = image;
        this.x = x;
        this.y = y;
        this.grid = grid;
        this.validator = validator;
        loadImages();
        animator = new Animator(new AnimatedImageManager(), AnimatedImageManager.RUN_LEFT_IMAGE_NAMES, 100);
        this.health = 100;
    }
    private Animator animator;

    private void loadImages() {
        this.image = ResourceTools.loadImageFromResource("spaceinvaders/FarmerJohn.png");
    }

    public void draw(Graphics graphics) {

        graphics.drawImage(getImage(), getX(), getY(), null);

    }
    private static String STAND_LEFT = "STAND_LEFT";
    private static String STAND_RIGHT = "STAND_RIGHT";
    private static String RUN_RIGHT = "STAND_RIGHT";
    private static String RUN_LEFT = "STAND_LEFT";

//    public void runRight(){
//        setState(FarmerJohnState.Run_Right);
//    }
//    public void runLeft(){
//        setState(FarmerJohnState.Run_Left);
//    }
//    public void stopRight(){
//        setState(FarmerJohnState.stop_Right);
//    }
//    public void stopLeft(){
//        setState(FarmerJohnState.stop_Left);
//    }
//    public void runUp(){
//        setState(FarmerJohnState.Run_Up);
//    }
//    public void runDown(){
//        setState(FarmerJohnState.Run_Down);
//    }
    private Image getImage() {
        if (animator != null) {
            return animator.getCurrentImage();
        } else {

            return image;
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
    private int health;
    private AnimationState moveState;

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

    /**
     * @return the Point that is JOhn's location
     */
    public Point getCenterOfMass() {
        return new Point(x + (image.getWidth(null) / 2), y + (image.getHeight(null) / 2));
    }

    public Point getCalculatedLocation(int distance, Direction direction) {
        Point com = getCenterOfMass();

        if (direction == Direction.LEFT) {
            com.x -= distance;
        } else if (direction == Direction.RIGHT) {
            com.x += distance;
        } else if (direction == Direction.UP) {
            com.y -= distance;
        } else {
            com.y += distance;
        }
        return com;
    }
//</editor-fold>

    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return the moveState
     */
    public AnimationState getMoveState() {
        return moveState;
    }

    /**
     * @param moveState the moveState to set
     */
    public void setMoveState(AnimationState moveState) {
        this.moveState = moveState;

        switch (moveState) {
            default:
            case RUN_LEFT:
                animator.setImageNames(RUN_LEFT_IMAGE_NAMES);
                break;
            case RUN_RIGHT:
                animator.setImageNames(RUN_RIGHT_IMAGE_NAMES);
                break;
            case STAND_LEFT:
                animator.setImageNames(STAND_LEFT_IMAGE_NAMES);
                break;
            case STAND_RIGHT:
                animator.setImageNames(STAND_RIGHT_IMAGE_NAMES);
                break;
        }
    }
}
