package com.mygdx.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEventDemo implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e);
        if (e.getKeyCode() == KeyEvent.KEY_LOCATION_LEFT) {
            System.out.println("left");
        } else if (e.getKeyCode() == KeyEvent.KEY_LOCATION_RIGHT) {
            System.out.println("right");        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
