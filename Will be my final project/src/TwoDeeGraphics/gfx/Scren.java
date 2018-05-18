package TwoDeeGraphics.gfx;

public class Scren {

    //setting up my map size constants
    public static final int MAP_WIDTH=64;
    public static final int MAP_WIDTH_MASK=MAP_WIDTH-1;

    public int[] pixel; //is my pixel data
    public int[] colors=new int[MAP_WIDTH*MAP_WIDTH*4]; //says to computer what color the tile is

    //variables that tell the screen to take up the whole window
    public int xOffset =0;
    public int yOffset =0;

    //How big is sprite sheet?
    public int width;
    public int height;

    //my spritesheet
    public SpriteSheet sheet;

    //constructor
    public Scren(int width, int height, SpriteSheet sheet){
        //initializaing some variables
        this.width=width;
        this.height=height;
        this.sheet=sheet;


        pixel=new int[width*height];//init pixel data array
        }

    public void render(int xPosition, int yPosition, int currentTile, int color){
    xPosition -= xOffset;
    yPosition -= yOffset;

    // %8 gives me tile location on an 8x8 sprite sheet
    int xTile=currentTile%8;
    int yTile=currentTile/32;
    int tileOffset=(xTile<<3)+(yTile<<3)*sheet.width;

    for(int y=0; y<8; y++){

        if(y+yPosition<0 || y+yPosition>=height) continue;
        int ySheet=y;

        for(int x=0; x<8; x++){
            if(x+xPosition<0 || x+xPosition>=width) continue;
            int xSheet=x;
            int localColor=(color>>(sheet.pixels[xSheet+ySheet*sheet.width+tileOffset]*8))&255; //what color is it looking at right now?
            if(localColor<255) pixel[(x+xPosition)+(y+yPosition)*width]=localColor; //TODO this is my null pointer line?
        }
    }

    }

}
