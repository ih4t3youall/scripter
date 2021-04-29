package ar.com.sourcesistemas.utils;

import java.awt.*;

public class ScreenPositionUtil {

    public static Point getCenter(){
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height/2;
        int width = pantalla.width/2;
        Point point = new Point(height,width);
        return point;
    }
}
