package com.example.jogomobille.utils;

public class Utils {

    /**
     * getDistanceBetweenPoints returns the distance between 2d points p1 and p2
     * @param p1x
     * @param p1y
     * @param p2x
     * @param p2y
     * @return
     */
    public static double getDistanceBetweenPoints(double p1x, int p1y, double p2x, double p2y) {
        return Math.sqrt(
            Math.pow(p1x - p2x, 2) +
            Math.pow(p1y - p2y, 2)
        );
    }

    public static double pitagoras(double a, double b) {
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }
}
