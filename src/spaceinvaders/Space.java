/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import environment.Direction;
import environment.Environment;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Oliver
 */
public class Space extends Environment implements CellDataProviderIntf, MoveValidatorIntf {

    Grid grid;
    FarmerJohn john;
    Image image;
    int x;
    int y;
    private ArrayList<Barrier> barriers;

    public Space() {
        x = 30;
        y = 40;
        grid = new Grid(20, 20, 30, 30, new Point(20, 50), new Color(0, 102, 0));
//        image = ResourceTools.loadImageFromResource("spaceinvaders/FarmerJohn.png").getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        image = ResourceTools.loadImageFromResource("spaceinvaders/FarmerJohn.png");
        this.setBackground(new Color(0, 153, 153));
        john = new FarmerJohn(image, 200, 140, grid, this);

//        myBarrier = new Barrier(10, 15, Color.green, this, true);
        barriers = new ArrayList<>();
        barriers.add(new Barrier(0, 0, Color.green, this, false));
        barriers.add(new Barrier(1, 0, Color.green, this, false));
        barriers.add(new Barrier(2, 0, Color.green, this, false));
        barriers.add(new Barrier(3, 0, Color.green, this, false));
        barriers.add(new Barrier(5, 7, Color.green, this, false));
        barriers.add(new Barrier(3, 4, Color.green, this, false));
    }

    @Override
    public void initializeEnvironment() {

    }
    int counter;

    @Override
    public void timerTaskHandler() {
//        System.out.println("Hey dude.." + ++counter);
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
//        System.out.println("Key Event" + e.getKeyChar());
//        System.out.println("Key Event" + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            john.setDirection(Direction.LEFT);
            john.move(100);
            System.out.println("GO LEFT");
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("GO UP");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("GO DOWN");
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            john.setDirection(Direction.RIGHT);
            john.move(100);
            System.out.println("GO RIGHT");
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("SPACE BAR");
        }

    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            System.out.println("Key Released - UP");
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("Key Released - LEFT");
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            System.out.println("Key Released - RIGHT");
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            System.out.println("Key Released - DOWN");
        }
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
        System.out.println("Mouse clicked at " + e.getPoint());
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (grid != null) {
            grid.paintComponent(graphics);
        }
        if (john != null) {
            john.draw(graphics);
        }

        if(barriers != null){
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
