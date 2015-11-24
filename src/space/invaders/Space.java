/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.invaders;

import environment.Environment;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Oliver
 */
public class Space extends Environment implements CellDataProviderIntf {

    Grid grid;
    FarmerJohn john;
    Image image;
    int x;
    int y;
    private Barrier myBarrier;

    public Space() {
        x = 30;
        y = 40;
        grid = new Grid(20, 20, 30, 30, new Point(20, 50), Color.yellow);
        image = ResourceTools.loadImageFromResource("space.invaders/FarmerJohn.png");
        this.setBackground(Color.blue);
        john = new FarmerJohn(image, x, y);
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
            System.out.println("GO LEFT");
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("GO UP");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("GO DOWN");
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
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
}