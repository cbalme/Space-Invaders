/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import audio.AudioPlayer;
import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author Oliver
 */
public class Item {

    public void draw(Graphics graphics) {
        graphics.drawImage(getImage(), x, y, width, height, null);
    }

    {
        setDirection(Direction.RIGHT);
        minY = -100;
        maxY = 400;

        minX = -100;
        maxX = 1000; 

    }
    public Item(int x, int y, String type, CellDataProviderIntf cellData) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.image_left = image_left;
        this.cellData = cellData;

        if (type.equals(ITEM_TYPE_COW)) {
            image_left = ResourceTools.loadImageFromResource("spaceinvaders/cow_left.png");
            image_right = ResourceTools.loadImageFromResource("spaceinvaders/cow_right.png");
            width = 75;
            height = 75;
            speed = (int) getRandom(0, 2);

            minX = 10;
            maxX = 800;

        } else if (type.equals(ITEM_TYPE_ENEMY)) {
            image_right = ResourceTools.loadImageFromResource("spaceinvaders/tiefighter_right.png");
            image_left = ResourceTools.loadImageFromResource("spaceinvaders/tiefighter_left.png");
            width = 100;
            height = 100;
            speed = getRandom(2, 6);
            minX = -250;
            maxX = 1200;
        } else if (type.equals(ITEM_TYPE_SHEEP)) {
            image_left = ResourceTools.loadImageFromResource("spaceinvaders/sheepleft.png");
            image_right = ResourceTools.loadImageFromResource("spaceinvaders/sheepright.png");
            width = 35;
            height = 35;
            speed = (int) getRandom(0, 2);

            minX = 10;
            maxX = 950;

    }
    }
    private int getRandom(int min, int max) {
        return (int) (min + (max - min + 1) * (Math.random()));
    }

    public void move() {
        if (direction == Direction.LEFT) {
            x -= speed;
        } else {
            x += speed;
        }

        if (type.equals(Item.ITEM_TYPE_COW)) {
            if (x > maxX) {
                direction = Direction.LEFT;
            } else if (x < minX) {
                direction = Direction.RIGHT;
            }
        }
        if (type.equals(Item.ITEM_TYPE_SHEEP)) {
            if (x > maxX) {
                direction = Direction.LEFT;
            } else if (x < minX) {
                direction = Direction.RIGHT;
            }
        }
        if (type.equals(Item.ITEM_TYPE_ENEMY)) {
            if (x > maxX) {
                x = minX;
            } else if (x < minX) {
                x = maxX;
            }

            if (y >= maxY) {
                direction = Direction.UP;
            } else if (y <= minY) {
                direction = Direction.DOWN;
            }

            if (direction == Direction.UP) {
                y -= speed;
            } else {
                y += speed;
            }
        }
    }

    public static void playSound(String type) {
        if (type.equals(Item.ITEM_TYPE_COW)) {
            AudioPlayer.play("/spaceinvaders/Moo.wav");
        } else if (type.equals(Item.ITEM_TYPE_ENEMY)) {
            AudioPlayer.play("/spaceinvaders/TIE-Fly1.wav");
        } else if (type.equals(Item.ITEM_TYPE_SHEEP)) {
            AudioPlayer.play("/spaceinvaders/Bleat.wav");
        }
    }
//<editor-fold defaultstate="collapsed" desc="Properties">
    public static final String ITEM_TYPE_POISON_BOTTLE = "POISON";
    public static final String ITEM_TYPE_SUPERCHARGED_GUN = "SUPERGUN";
    public static final String ITEM_TYPE_RESTORE_HEALTH = "HEALTH";
    public static final String ITEM_TYPE_EXPLOSIVE_GUN = "BOOMGUN";
    public static final String ITEM_TYPE_ENEMY = "ENEMY";
    public static final String ITEM_TYPE_COW = "COW";
    public static final String ITEM_TYPE_SHEEP = "SHEEP";
    
    private int x, y, width, height, minY, maxY, minX, maxX;
    private String type;
    private Image image_left, image_right;
    private CellDataProviderIntf cellData;
    private Direction direction;

    private int speed;

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
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the image_left
     */
    public Image getImage() {
        if (direction == Direction.LEFT) {
            return image_left;
        } else {
            return image_right;
        }

    }

    /**
     * @param image the image_left to set
     */
    public void setImage(Image image) {
        this.image_left = image;
    }

    /**
     * @return the CellData
     */
    public CellDataProviderIntf getCellData() {
        return cellData;
    }

    /**
     * @param CellData the CellData to set
     */
    public void setCellData(CellDataProviderIntf CellData) {
        this.cellData = CellData;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Direction">
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
//</editor-fold>
