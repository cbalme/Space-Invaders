/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package space.invaders;

import environment.Environment;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Oliver
 */
public class Space extends Environment {

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
            System.out.println("up");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("down");
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("right");
        }

    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            System.out.println("Key Released - UP");
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("Key Released - Left");
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            System.out.println("Key Released - Right");
            
          
            @Override
            public void environmentMouseClicked
            (MouseEvent e
        
            
                ) {
        System.out.println("Mouse clicked at " + e.getPoint());
                System.out.println("Mouse clicked in cell" + grid.getCellLocationFromSystemCoordinate);
            }

            @Override
            public void paintEnvironment
            (Graphics graphics
        
            
                ) {
        if (grid != null) {
                    grid.paintComponent(graphics);
                }
            }

        }
