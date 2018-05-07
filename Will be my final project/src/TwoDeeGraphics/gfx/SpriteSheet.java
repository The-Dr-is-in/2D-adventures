package TwoDeeGraphics.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

    //fields of class
    public String path; //file path to image
    //dimensions of sprite sheet
    public int width;
    public int height;
    public int[] pixels; //pixel-data of sprite sheet


    public SpriteSheet(String path){
        //Makes image, reads image from file
        BufferedImage image= null;
        try {
            image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path)); //TODO fix the damn null pointer
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (image==null){ return; } //redundant fail safe in case catch works weird

        //fields get initialized
        this.path=path;
        this.width=image.getWidth();
        this.height=image.getHeight();
        pixels=image.getRGB(0,0,width,height,null,0,width);

        //based on the color-data in pixels, colors get assigned to corresponding pixels
        for(int i=0;i<pixels.length;i++){
            pixels[i]=(pixels[i] & 0xff/64); //0xff is a hexadecimal thing, removes alpha channel, divide by 64 is to limit it to 4 colors
        }

        for(int i=0;i<8;i++){
            System.out.println(pixels[i]);
        }
    }
}
