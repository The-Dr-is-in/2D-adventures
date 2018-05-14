package TwoDeeGraphics.gfx;

public class Scren {

    //setting up my map size constants
    public static final int MAP_WIDTH=64;
    public static final int MAP_WIDTH_MASK=MAP_WIDTH-1;

    public int[] tiles=new int[MAP_WIDTH*MAP_WIDTH]; //says to computer where tiles are
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


        //fills colors[] with actual data on my colors
        for(int i=0; i<MAP_WIDTH*MAP_WIDTH; i++){
            colors[i*4/* +0 */]=0xff00ff; //each of the 4 colors are assigned something
            colors[i*4+1]=0x00ffff;
            colors[i*4+2]=0xffff00;
            colors[i*4+3]=0xffffff;
        }
    }

    public void render(int[] pixels, int offset, int row) {

        //TODO Figure this out: no clue what the logic here is, or what the ">>" symbol does
        for (int yTile = yOffset >> 3; yTile <= (yOffset + height) >> 3; yTile++) {

            //Sets minimum and maximum tile
            int yMin = yTile * 8 - yOffset;
            int yMax = yMin + 8;

            //simple "stay in bounds" statement
            if (yMin < 0)
                yMin = 0;

            if (yMax > height)
                yMax = height;


            //Same comment as last loop
            for (int xTile = xOffset >> 3; xTile <= (xOffset + width) >> 3; xTile++) {

                //Sets minimum and maximum tile
                int xMin = xTile * 8 - xOffset;
                int xMax = xMin + 8;

                //simple "stay in bounds" statement
                if (xMin < 0)
                    xMin = 0;

                if (xMax > width)
                    xMax = width;


                int tileIndex = (xTile & (MAP_WIDTH_MASK)) + (yTile & (MAP_WIDTH_MASK)) * MAP_WIDTH;

                for(int y=yMin; y<yMax; y++){
                    int sheetCurrentPixel=((y+yOffset)&7) * sheet.width+((xMin+xOffset)&7); //where am I in the sprite sheet?
                    int tileCurrentPixel=offset + xMin + y * row; //Where am I in the game?

                    for(int x=xMin; x<xMax; x++){
                        int color = tileIndex*4+sheet.pixels[sheetCurrentPixel++]; //what color is my tile?
                        pixels[tileCurrentPixel++]=colors[color]; //sets color
                    }
                }

            }

        }

    }

}
