package com.example.jogomobille.game.map.Mechanics;

import static com.example.jogomobille.game.map.MapLayout.TILE_HEIGHT_PIXELS;
import static com.example.jogomobille.game.map.MapLayout.TILE_WIDTH_PIXELS;

import android.util.Log;

import com.example.jogomobille.game.gameobject.GameObject;
import com.example.jogomobille.game.map.TileMap;

public class Colision {

    private final TileMap tileMap;

    public Colision(TileMap tileMap){
        this.tileMap = tileMap;
    }

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

            if(tileMap.isColiding(x+v, y)||tileMap.isColiding(x+v, y+h)) {
                int positionF = XtoTWP+TwPw;
                velocityX = -(x%TILE_WIDTH_PIXELS);


                Log.d("TileMap.java", "colisionX():Left of object {"+ object +"} is colliding;\n"+//message+
                        //"x+v/Tw = " + ((x+v)/TILE_WIDTH_PIXELS) + ";"+
                        "fp = " + positionF + ";\n" +
                        "fv = " + velocityX + ";");
                return velocityX;
            }

            int xIn = x+v+w;

            if(tileMap.isColiding(x+v+w, y)||tileMap.isColiding(x+v+w,y+h)) {
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

            if(tileMap.isColiding(x,y+v)||tileMap.isColiding(x+w,y+v)) {
                velocityY = -(y%TILE_HEIGHT_PIXELS);

                velocityY = (((y / TILE_HEIGHT_PIXELS) * TILE_HEIGHT_PIXELS) - y);

                Log.d("TileMap.java", "colisionY():top of object {"+ object +"} is colliding;\n"+//message +
                        "fv = " + (velocityY) + ";");
                return (int)velocityY;

            }


            if(tileMap.isColiding(x, y+v+h)||tileMap.isColiding(x+w,y+v+h)) {
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
