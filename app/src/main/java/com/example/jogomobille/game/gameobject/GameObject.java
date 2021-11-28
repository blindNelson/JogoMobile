package com.example.jogomobille.game.gameobject;

import static com.example.jogomobille.game.map.MapLayout.TILE_HEIGHT_PIXELS;
import static com.example.jogomobille.game.map.MapLayout.TILE_WIDTH_PIXELS;

import android.graphics.Canvas;
import android.util.Log;

import com.example.jogomobille.game.GameDisplay;
import com.example.jogomobille.game.map.TileMap;

/**
 * GameObject is an abstract class which is the foundation of all world objects in the game.
 */
public abstract class GameObject {

    protected TileMap tileMap;
    protected double width;
    protected double height;
    protected double positionX;
    protected double positionY;
    protected double velocityX = 0;
    protected double velocityY = 0;
    protected double directionX = 1;
    protected double directionY = 0;

    public GameObject(double positionX, double positionY, double width, double height, TileMap tileMap) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
        this.tileMap  = tileMap;
    }

    protected static double getDistanceBetweenObjects(GameObject obj1, GameObject obj2) {
        return Math.sqrt(
            Math.pow(obj2.getPositionX() - obj1.getPositionX(), 2) +
            Math.pow(obj2.getPositionY() - obj1.getPositionY(), 2)
        );
    }

    public abstract void draw(Canvas canvas, GameDisplay gameDisplay);

    public void update(){
        positionX += colisionX(this, (int)velocityX);
        positionY += colisionY(this, (int)velocityY);
    }

    public double getPositionX() { return positionX; }

    public double getPositionY() {
        return positionY;
    }

    protected double getDirectionX() {
        return directionX;
    }

    protected double getDirectionY() {
        return directionY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }


    //colisione


    public double colisionX(GameObject object, int velocityX) {
        int x = (int)object.getPositionX();
        int y = (int)object.getPositionY();
        int w = (int)object.getWidth();
        int h = (int)object.getHeight();
        int v = velocityX;
        int TwPw = (TILE_WIDTH_PIXELS-w);
        int XtoTWP = ((x / TILE_WIDTH_PIXELS) * TILE_WIDTH_PIXELS);


        //colisão com paredes
        try {

            if(tileMap.isPixelColiding(x+v, y)||tileMap.isPixelColiding(x+v, y+h)) {
                int positionF = XtoTWP+TwPw;
                velocityX = -(x%TILE_WIDTH_PIXELS);


                Log.d("TileMap.java", "colisionX():Left of object {"+ object +"} is colliding;\n"+//message+
                        //"x+v/Tw = " + ((x+v)/TILE_WIDTH_PIXELS) + ";"+
                        "fp = " + positionF + ";\n" +
                        "fv = " + velocityX + ";");
                return velocityX;
            }

            int xIn = x+v+w;

            if(tileMap.isPixelColiding(x+v+w, y)||tileMap.isPixelColiding(x+v+w,y+h)) {
                int positionF = XtoTWP+TwPw;
                velocityX = positionF-x;

                Log.d("Colision.java", "colisionX():Right of object {"+ object +"} is colliding;\n"+
                        //"x+v+w/Tw = " + ((x+v+w)/TILE_WIDTH_PIXELS) + ";\n" +
                        "xIn = "+ xIn + ";xIn/64 = "+(xIn/64)+"\n" +
                        "fp = " + positionF + ";\n" +
                        "fv = " + velocityX + ";");

                return velocityX-1;
            }


        }
        catch(ArrayIndexOutOfBoundsException e){
            Log.wtf("TileMap.java","colisionX():object {"+object+"} out of the map;");
        }

        return velocityX;
    }

    public int colisionY(GameObject object, int velocityY) {
        int x = (int) object.getPositionX();
        int y = (int) object.getPositionY();
        int w = (int) object.getWidth();
        int h = (int) object.getHeight();
        int v = velocityY;
        int ThPh = (TILE_HEIGHT_PIXELS-w);
        int YtoTHP = ((y / TILE_HEIGHT_PIXELS) * TILE_HEIGHT_PIXELS);

        try {

            if(tileMap.isPixelColiding(x,y+v)||tileMap.isPixelColiding(x+w,y+v)) {
                velocityY = -(y%TILE_HEIGHT_PIXELS);

                velocityY = (((y / TILE_HEIGHT_PIXELS) * TILE_HEIGHT_PIXELS) - y);

                Log.d("TileMap.java", "colisionY():top of object {"+ object +"} is colliding;\n"+//message +
                        "fv = " + (velocityY) + ";");
                return (int)velocityY;

            }


            if(tileMap.isPixelColiding(x, y+v+h)||tileMap.isPixelColiding(x+w,y+v+h)) {
                int positionF = YtoTHP+ThPh;
                velocityY = (positionF -y)-1;

                Log.d("TileMap.java", "colisionY():bottom of object {"+ object +"} is colliding;\n"+//message +
                        "fv = " + (velocityY) + ";");
                return velocityY;

            }


        }
        catch(ArrayIndexOutOfBoundsException e){
            Log.wtf("TileMap.java","colisionY():object {"+ object +"} out of the map;\n");
        }

        return velocityY;
    }

}
