/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import environment.Direction;
import environment.Environment;
import environment.Velocity;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
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
    int x;
    int y;
    private ArrayList<Barrier> barriers;
    private ArrayList<Bullet> bullets;

    public Field() {
        x = 30;
        y = 40;
        grid = new Grid(20, 20, 25, 25, new Point(20, 50), new Color(0, 102, 0));
//        image = ResourceTools.loadImageFromResource("spaceinvaders/FarmerJohn.png").getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        image = ResourceTools.loadImageFromResource("spaceinvaders/FarmerJohn.png");
        this.setBackground(new Color(0, 153, 153));
        john = new FarmerJohn(image, 140, 200, grid, this);

//        myBarrier = new Barrier(10, 15, Color.green, this, true);
        barriers = new ArrayList<>();
        barriers.add(new Barrier(0, 19, Color.green, this, false));
        barriers.add(new Barrier(1, 19, Color.green, this, false));
        barriers.add(new Barrier(2, 19, Color.green, this, false));
        barriers.add(new Barrier(3, 19, Color.green, this, false));
        barriers.add(new Barrier(4, 19, Color.green, this, false));
        barriers.add(new Barrier(5, 19, Color.green, this, false));

        bullets = new ArrayList<>();
    }

    @Override
    public void initializeEnvironment() {

    }
    int counter;

    @Override
    public void timerTaskHandler() {
        if (bullets != null) {
            for (Bullet bullet : bullets) {
                bullet.move();
            }
        }
//        System.out.println("Hey dude.." + ++counter);
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            moveFarmer(5, Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            moveFarmer(5, Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            moveFarmer(5, Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            moveFarmer(5, Direction.RIGHT);
        }
    }

    private void moveFarmer(int speed, Direction direction){
        john.setDirection(direction);
        john.move(speed);
        
        //check grid location
    }
    
    
    @Override
    public void keyReleasedHandler(KeyEvent e) {

    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked at " + e.getPoint());

        bullets.add(new Bullet(john.getX() + 11, john.getY() + 22, TrigonometryCalculator.calculateVelocity(john.getLocation(), e.getPoint(), 15)));

    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (grid != null) {
            grid.paintComponent(graphics);
        }
        if (bullets != null) {
            for (Bullet bullet : bullets) {
                bullet.draw(graphics);
            }
        }

        if (john != null) {
            john.draw(graphics);
        }

        if (barriers != null) {
            for (int i = 0; i < barriers.size(); i++) {
                barriers.get(i).draw(graphics);
            }
        }
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
    public int getSystemCoordX(int x, int y) {
        return grid.getCellSystemCoordinate(x, y).x;
    }

    @Override
    public int getSystemCoordY(int x, int y) {
        return grid.getCellSystemCoordinate(x, y).y;
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="MoveValidatorIntf">
    @Override
    public Point validateMove(Point proposedLocation) {
        if (proposedLocation.x < 0) {
            System.out.println("OUT OF BOUNDS!!");
        }

        return proposedLocation;
    }
//</editor-fold>
}
