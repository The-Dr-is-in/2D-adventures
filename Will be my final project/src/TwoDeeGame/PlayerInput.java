package TwoDeeGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerInput implements KeyListener {

    //constructor that adds in key listeners
    public PlayerInput(TwoDeeAdventures game){
       game.addKeyListener(this);
       game.requestFocus();
    }

    //Defines what a key on the keyboard is
    public class Key{
        private int timesPressed=0;
        private Boolean pressed=false;

        //getter for isItPressed
        public boolean isItPressed(){
            return pressed;
        }

        public void toggle(Boolean isPressed){
            pressed=isPressed;
        }
    }

    //accessible list of keys
    public Key up=new Key();
    public Key down=new Key();
    public Key left=new Key();
    public Key right=new Key();

    //Key listener methods
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        toggleKey(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        toggleKey(e.getKeyCode(), false);
    }

    public void toggleKey(int keyCode, boolean isPressed){
        if(keyCode==KeyEvent.VK_W || keyCode==KeyEvent.VK_UP) {up.toggle(isPressed);}
        if(keyCode==KeyEvent.VK_S || keyCode==KeyEvent.VK_DOWN) {down.toggle(isPressed);}
        if(keyCode==KeyEvent.VK_D || keyCode==KeyEvent.VK_RIGHT) {right.toggle(isPressed);}
        if(keyCode==KeyEvent.VK_A || keyCode==KeyEvent.VK_LEFT) {left.toggle(isPressed);}
    }
}
