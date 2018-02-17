package de.nihas101.pictureFilter.filters.utils;

import javafx.scene.paint.Color;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class ColorSum {
    private double red = 0;
    private double blue = 0;

    private double green = 0;
    private int nrOfAdditions = 0;

    public void add(Color color){
        red   += color.getRed();
        blue  += color.getBlue();
        green += color.getGreen();
        nrOfAdditions++;
    }

    public void avarageColors(){
        red = min(max(red/nrOfAdditions,0),1);
        green = min(max(green/nrOfAdditions,0),1);
        blue = min(max(blue/nrOfAdditions,0),1);
    }

    public double getRed() {
        return red;
    }

    public double getBlue() {
        return blue;
    }

    public double getGreen() {
        return green;
    }
}
