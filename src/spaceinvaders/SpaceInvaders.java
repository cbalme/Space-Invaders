/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import audio.AudioPlayer;
import environment.ApplicationStarter;

/**
 *
 * @author Oliver
 */
public class SpaceInvaders {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ApplicationStarter.run("The Field", new Field());
        AudioPlayer.play("/spaceinvaders/RoosterCrowin.wav");
    }

}
