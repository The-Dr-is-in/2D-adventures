import java.awt.*;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

public class BootsUpGame {

    public static void main (String[] args){

        GameWindow gameWindow = new GameWindow();
        SpriteAnimation animator = new SpriteAnimation(gameWindow.getGraphics());

        animator.makePictures();

        gameWindow.paint(animator.getPicture());



    }
}
