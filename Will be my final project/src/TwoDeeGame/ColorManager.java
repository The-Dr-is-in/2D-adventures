package TwoDeeGame;

public class ColorManager {

    public static int get(int darkestColor, int darkColor, int lightColor, int lightestColor){
      return (get(lightestColor)<<24)+(get(lightColor)<<16)+(get(darkColor)<<8)+(get(darkestColor));
    }

    private static int get(int color) {
    if(color<0) return 255;
    int red=color/100%10;
    int green=color/10;
    int blue=color;
    return red*36 + green*6 + blue;
    }

}
