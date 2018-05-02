import javax.swing.*;
import java.awt.*;

public class SpriteAnimation {

    private Graphics picture;

    public SpriteAnimation(Graphics g){
        picture = g;
    }

    public void makePictures(){
        picture.fill3DRect(100,100,100,100,true);
    }

    public Graphics getPicture(){return picture;}
}
