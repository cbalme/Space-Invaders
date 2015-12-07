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
        grid = new Grid(28, 22, 25, 25, new Point(20, 10), new Color(0, 102, 0));
//        image = ResourceTools.loadImageFromResource("spaceinvaders/FarmerJohn.png").getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        image = ResourceTools.loadImageFromResource("spaceinvaders/FarmerJohn.png");
        this.setBackground(new Color(0, 153, 153));
        john = new FarmerJohn(image, 140, 270, grid, this);

//        myBarrier = new Barrier(10, 15, Color.green, this, true);
        barriers = new ArrayList<>();
        createBarrierRange(0, 12, 27, 21, Color.GREEN, true);
        createBarrierRange(-1, -1, -1, 22, Color.WHITE, true);
        createBarrierRange(-1, -1, 28, -1, Color.YELLOW, true);
        createBarrierRange(-1, 22, 28, 22, Color.YELLOW, true);
        createBarrierRange(28, 0, 28, 22, Color.yellow, true);
        bullets = new ArrayList<>();
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

    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (grid != null) {
            grid.paintComponent(graphics);
        }
        if (bullets != null) {
            for (Bullet bullet : bullets) {
                graphics.setColor(Color.BLACK);
                bullet.draw(graphics);
            }
        }

        if (barriers != null) {
            for (int i = 0; i < barriers.size(); i++) {
                barriers.get(i).draw(graphics);
            }
        }

        if (john != null) {
            john.draw(graphics);
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
