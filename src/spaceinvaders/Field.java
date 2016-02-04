/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import audio.AudioPlayer;
import environment.Direction;
import environment.Environment;
import environment.Velocity;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import static java.lang.Math.random;
import java.util.ArrayList;
import path.TrigonometryCalculator;

/**
 *
 * @author Oliver
 */
public class Field extends Environment implements CellDataProviderIntf, MoveValidatorIntf {

    Grid grid;
    Bullet bullet;
    FarmerJohn john;
    Image image;
    Image background;
    int x;
    int y;
    private ArrayList<Barrier> barriers;
    private ArrayList<Bullet> bullets;
    private ArrayList<Item> items;
    private int score;

    public Field() {
        x = 30;
        y = 40;
        grid = new Grid(39, 22, 25, 25, new Point(5, 5), new Color(0, 102, 0, 0));
//        image = ResourceTools.loadImageFromResource("spaceinvaders/FarmerJohn.png").getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        image = ResourceTools.loadImageFromResource("spaceinvaders/FarmerJohn.png");
        this.setBackground(background = ResourceTools.loadImageFromResource("spaceinvaders/Old-barn-in-field.jpg"));
        john = new FarmerJohn(image, 100, 300, grid, this);
        barriers = new ArrayList<>();
        createBarrierRange(0, 13, 38, 21, new Color(154, 205, 50), true);
        createBarrierRange(-1, -1, -1, 22, new Color(0, 0, 128, 255), true);
        createBarrierRange(-1, -1, 39, -1, new Color(0, 0, 128, 255), true);
        createBarrierRange(-1, 22, 39, 22, new Color(0, 0, 128, 255), true);
        createBarrierRange(39, 0, 39, 22, new Color(0, 0, 128, 255), true);
        bullets = new ArrayList<>();

        items = new ArrayList<>();
        items.add(new Item(140, 310, Item.ITEM_TYPE_COW, this));
        items.add(new Item(280, 310, Item.ITEM_TYPE_COW, this));
        items.add(new Item(450, 310, Item.ITEM_TYPE_SHEEP, this));
        items.add(new Item(500, 310, Item.ITEM_TYPE_SHEEP, this));
        items.add(new Item(-260, 100, Item.ITEM_TYPE_ENEMY, this));
        items.add(new Item(100, 1000, Item.ITEM_TYPE_ENEMY, this));
//        items.add(new Item (140, 280, Item.ITEM_TYPE_COW, ResourceTools.loadImageFromResource("spaceinvaders/cow_1.png"), this));
//        items.add(new Item (100, 100, Item.ITEM_TYPE_ENEMY, ResourceTools.loadImageFromResource("spaceinvaders/tiefighter_2.png"), this));
    }

    public void createBarrierRange(int startX, int startY, int endX, int endY, Color color, boolean breakable) {
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                barriers.add(new Barrier(x, y, color, this, breakable));
            }
        }
    }

    @Override
    public void initializeEnvironment() {
//        AudioPlayer.play("/spaceinvaders/RoosterCrowin.wav");
    }
    int counter;

    @Override
    public void timerTaskHandler() {
        if (bullets != null) {
            for (Bullet bullet : bullets) {
                bullet.move();
            }
        }

        if (items != null) {
            for (Item item : getItems()) {
                item.move();
            }
        }

        if (Math.random() < .01) {
            Item.playSound(Item.ITEM_TYPE_ENEMY);
        }
        if (Math.random() < .01) {
            Item.playSound(Item.ITEM_TYPE_SHEEP);
        }
        if (Math.random() < .01) {
            Item.playSound(Item.ITEM_TYPE_COW);
        }
        if (Math.random() < .001) {
            AudioPlayer.play("/spaceinvaders/TIE-Fly7.wav");
        }
        if ((bullets != null) && (Math.random() < .01)) {
            bullets.add(new Bullet(getTieFighterLocation().x + 34, getTieFighterLocation().y + 61, new Velocity(0, 20)));
            AudioPlayer.play("/spaceinvaders/TIE-Fire.wav");
        }
        if ((bullets != null) && (Math.random() < .01)) {
            bullets.add(new Bullet(getTieFighterLocation().x + 49, getTieFighterLocation().y + 61, new Velocity(0, 20)));
            AudioPlayer.play("/spaceinvaders/TIE-Fire.wav");
        }
//        Math.random();
//        if (random() < .001) {
//            AudioPlayer.play("/spaceinvaders/TIE-Fly1.wav");
//        }
//        Math.random();
//        if (random() < .001) {
//            AudioPlayer.play("/spaceinvaders/TIE-Fly7.wav");
//      }
    }

    private Point getTieFighterLocation() {
        if (items != null) {
            for (Item item : items) {
                if (item.getType().equals(Item.ITEM_TYPE_ENEMY)) {
                    return item.getLocation();
                }
            }
        }
        return new Point();
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            moveFarmer(5, Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            moveFarmer(5, Direction.RIGHT);
        }
    }

    private void moveFarmer(int speed, Direction direction) {
        if (checkBarriers(grid.getCellLocationFromSystemCoordinate(john.getCalculatedLocation(speed, direction)))) {
            System.out.println("HIT BARRIER");
            java.awt.Toolkit.getDefaultToolkit().beep();
        } else {
            john.setDirection(direction);
            john.move(speed);
        }
    }

    private boolean checkBarriers(Point location) {
        for (Barrier barrier : barriers) {
            if (barrier.getLocation().equals(location)) {
                return true;
            }
        }
        return false;
    }

    private void checkIntersections() {
        Point johnLocation = grid.getCellLocationFromSystemCoordinate(john.getCenterOfMass());
        System.out.println("John Location " + johnLocation);

        for (Barrier barrier : barriers) {
            if (barrier.getLocation().equals(johnLocation)) {
                System.out.println("HIT BARRIER");
            }
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {

    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked at " + e.getPoint());

        bullets.add(new Bullet(john.getX() + 11, john.getY() + 22, TrigonometryCalculator.calculateVelocity(john.getLocation(), e.getPoint(), 15)));
        AudioPlayer.play("/spaceinvaders/Gun_sound.wav");
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (grid != null) {
            grid.paintComponent(graphics);
        }

        if (barriers != null) {
            for (int i = 0; i < barriers.size(); i++) {
                barriers.get(i).draw(graphics);
            }
        }

        if (john != null) {
            john.draw(graphics);
        }

        if (items != null) {
            for (Item item : getItems()) {
                item.draw(graphics);
            }
        }
        if (bullets != null) {
            for (Bullet bullet : bullets) {
                graphics.setColor(Color.BLACK);
                bullet.draw(graphics);
            }
        }

        if (bullets != null) {
            for (Bullet bullet : bullets) {
                graphics.setColor(Color.GREEN);
                bullet.draw(graphics);
            }
        }
        if (bullets != null) {
            for (Bullet bullet : bullets) {
                graphics.setColor(Color.GREEN);
                bullet.draw(graphics);
            }
        }
        graphics.setFont(new Font("Calibri", Font.BOLD, 24));
        graphics.setColor(Color.RED);
        graphics.drawString("SCORE: " + score, 7, 25);
    }

//<editor-fold defaultstate="collapsed" desc="CellDataProviderIntf">
    @Override
    public int getCellWidth() {
        return grid.getCellWidth();
    }

    @Override
    public int getCellHeight() {
        return grid.getCellHeight();
    }

    @Override
    public int getSystemCoordX(int x, int y
    ) {
        return grid.getCellSystemCoordinate(x, y).x;
    }

    @Override
    public int getSystemCoordY(int x, int y
    ) {
        return grid.getCellSystemCoordinate(x, y).y;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="MoveValidatorIntf">
    @Override
    public Point validateMove(Point proposedLocation
    ) {
        if (proposedLocation.x < 0) {
            proposedLocation.x = grid.getColumns() - 1;
            System.out.println("OUT OF BOUNDS!!");
        } else if (proposedLocation.x > grid.getColumns() - 1) {
            proposedLocation.x = 0;
        }
        if (proposedLocation.y < 0) {
            proposedLocation.y = grid.getRows() - 1;
        }

        return proposedLocation;
    }
    //</editor-fold>

    /**
     * @return the items
     */
    public ArrayList<Item> getItems() {
        return (ArrayList<Item>) items.clone();
    }
}
