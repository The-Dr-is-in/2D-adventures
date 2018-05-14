import TwoDeeGraphics.gfx.Scren;
import TwoDeeGraphics.gfx.SpriteSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class TwoDeeAdventures extends Canvas implements Runnable {

    private static final long serialVersionUID=1;

    //Window constants
    public static final int FRAME_WIDTH =160; //width of window
    public static final int FRAME_HEIGHT =(3* FRAME_WIDTH)/4; //height of window, scaled to width
    public static final int SCALE=4; //will be for scaling stuff up and down later?
    public static final String NAME="Two-Dee Adventures"; //titlebar text

    //fields
    private JFrame frame;
    public boolean isGameOn=false; //tells me if the game is running
    public int refreshCount=0; //how many times has the screen refreshed?
    private BufferedImage image= new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT,BufferedImage.TYPE_INT_RGB); //an image
    private int[] pixels=((DataBufferInt)image.getRaster().getDataBuffer()).getData(); //TODO learn what this is? Ik vaguely it's "pixels in image"

    private Scren screen;

    //Class constructor, sets up frame
    public TwoDeeAdventures(){
        //These three lines are setting the window size
        setMinimumSize(new Dimension(FRAME_WIDTH *SCALE, FRAME_HEIGHT *SCALE));
        setMaximumSize(new Dimension(FRAME_WIDTH *SCALE, FRAME_HEIGHT *SCALE));
        setPreferredSize(new Dimension(FRAME_WIDTH *SCALE, FRAME_HEIGHT *SCALE));

        frame=new JFrame(NAME); //Frame initialized

        //Describes behaviour of window
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //shuts down on clicking "x"
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER); //makes frame exist, and move to center of monitor
        frame.pack();
        frame.setResizable(false); //Window is now at preferred size and won't change size
        frame.setLocationRelativeTo(null); //Window will not move based on other
        frame.setVisible(true);
    }

    //initialize my scren
    public void screenInit(){
        screen=new Scren(FRAME_WIDTH,FRAME_HEIGHT, new SpriteSheet("/8x8SpriteSheet.png"));
    }

    //makes thread of game, so that it has a place to run without interfering with main thread
    public synchronized void start(){
        isGameOn=true;
        new Thread(this).start();
    }

    //Makes game stop running
    public synchronized void stop(){
        isGameOn=false;
    }


    //Primary game loop (frame animation, key input checking, etc.) go here
    @Override
    public void run(){
        long timeCheckOld=System.nanoTime();
        double nanosPerTick=1000000000D/60D; //how many nano seconds are in one tick at 60fps

        int refreshes=0; //how many frames have happened
        int fps=0; //current FPS

        long timeCheckNew=System.currentTimeMillis(); //last time we updated
        double nanosPassed=0;

        screenInit();

        while(isGameOn){
            //limits frame rate     tutorial didn't explain how this works, //TODO ask seth/shai about the logic here
            long currentTime=System.nanoTime();
            nanosPassed+=(currentTime-timeCheckOld)/nanosPerTick;
            timeCheckOld=currentTime;
            boolean shouldRender=false;

            while(nanosPassed>=1) {
                refreshes++;
                refresh();
                nanosPassed-=1;
                shouldRender=true;
            }
            //end of frame rate limiter
            if(shouldRender) {
                fps++;
                render();
            }

                if(System.currentTimeMillis()-timeCheckNew>=1000){
                    timeCheckNew+=1000;
                    System.out.println(fps + " , " + refreshes);
                    fps=0;
                    refreshes=0;
                }
        }
    }

        //"tick" method. Refreshes the game-logic
    public void refresh(){
        refreshCount++;

        for(int i=0; i<pixels.length; i++){
            pixels[i]=i+refreshCount;
        }
    }

    //Renders new frames based on what refresh puts out
    public void render(){

        BufferStrategy bs=getBufferStrategy(); //object that organizes data on my canvas
        if(bs==null) {
            createBufferStrategy(3);
            return;
        }

        screen.render(pixels,0,FRAME_WIDTH);

        //draws stuff on screen
        Graphics g=bs.getDrawGraphics();
        //For now draws moving images
        g.drawImage(image,0,0,getWidth(),getHeight(),null);
        //Frees up memory since g isn't using it for anything
        g.dispose();
        bs.show();
    }

    public static void main (String[] args){
        new TwoDeeAdventures().start();
    }
}
