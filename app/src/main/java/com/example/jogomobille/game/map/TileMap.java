package com.example.jogomobille.game.map;

import static com.example.jogomobille.game.map.MapLayout.NUMBER_OF_COLUMN_TILES;
import static com.example.jogomobille.game.map.MapLayout.NUMBER_OF_ROW_TILES;
import static com.example.jogomobille.game.map.MapLayout.TILE_HEIGHT_PIXELS;
import static com.example.jogomobille.game.map.MapLayout.TILE_WIDTH_PIXELS;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

import com.example.jogomobille.game.GameDisplay;
import com.example.jogomobille.game.gameobject.GameObject;
import com.example.jogomobille.game.graphics.SpriteSheet;

public class TileMap {
    private final MapLayout mapLayout;
    private Tile[][] tilemap;
    private SpriteSheet spriteSheet;
    private Bitmap mapBitmap;

    public TileMap(SpriteSheet spriteSheet) {
        mapLayout = new MapLayout();
        this.spriteSheet = spriteSheet;
        initializeTileMap();
    }

    private void initializeTileMap() {
        int[][] layout = mapLayout.getLayout();
        tilemap = new Tile[NUMBER_OF_ROW_TILES][NUMBER_OF_COLUMN_TILES];
        for (int iRow = 0; iRow < NUMBER_OF_ROW_TILES; iRow++) {
            for (int iCol = 0; iCol < NUMBER_OF_COLUMN_TILES; iCol++) {
                tilemap[iRow][iCol] = Tile.getTile(layout[iRow][iCol], spriteSheet, getRectByIndex(iRow, iCol));
            }
        }

        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        mapBitmap = Bitmap.createBitmap(
                NUMBER_OF_COLUMN_TILES*TILE_WIDTH_PIXELS, NUMBER_OF_ROW_TILES*TILE_HEIGHT_PIXELS, config
        );

        Canvas mapCanvas = new Canvas(mapBitmap);

        for (int iRow = 0; iRow < NUMBER_OF_ROW_TILES; iRow++) {
            for (int iCol = 0; iCol < NUMBER_OF_COLUMN_TILES; iCol++) {
                tilemap[iRow][iCol].draw(mapCanvas);
            }
        }

        System.out.println();
    }

    private Rect getRectByIndex(int idxRow, int idxCol) {
        return new Rect(
                (idxCol)*TILE_WIDTH_PIXELS,
                (idxRow)*TILE_HEIGHT_PIXELS,
                (idxCol + 1)*TILE_WIDTH_PIXELS,
                (idxRow + 1)*TILE_HEIGHT_PIXELS
        );
    }


    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawBitmap(mapBitmap, gameDisplay.getGameRect(), gameDisplay.DISPLAY_RECT, null);
    }



    private boolean isColiding(int x, int y){
        return tilemap[y/TILE_HEIGHT_PIXELS][x/TILE_WIDTH_PIXELS].collide;
    }



    public double colisionX(GameObject object, int velocityX) {
        int x = (int)object.getPositionX();
        int y = (int)object.getPositionY();
        int w = (int)object.getWidth();
        int h = (int)object.getHeight();
        int v = velocityX;


        //colisão com paredes
        try {

            String message = "x = " + x + ";\n" +
                    "y = " + y + ";\n" +
                    "xw = " + (x+w) + ";\n" +
                    "yh = " + (y+h) + ";\n" +
                    "v = " + v + ";\n" +
                    "xv = " + (x+v) + ";\n" +
                    "xwv = " + (x+w+v) + ";\n";


            if(isColiding(x+v, y)||isColiding(x+v, y+h)) {

                velocityX = ( ((x / TILE_WIDTH_PIXELS) * TILE_WIDTH_PIXELS) - x);

                Log.d("TileMap.java", "colisionX():Left of object is colliding;\n"+message +
                        "fv = " + (velocityX) + ";");
                return velocityX;
            }


            if(isColiding(x+v+w, y)||isColiding(x+v+w,y+h)) {

                velocityX = ((((x / TILE_WIDTH_PIXELS)+1) * TILE_WIDTH_PIXELS) - x)-1;

                Log.d("TileMap.java", "colisionX():Right of object is colliding;\n"+message +
                        "fv = " + (velocityX) + ";");
                return velocityX;
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

        String message = "x = " + x + ";\n" +
                "y = " + y + ";\n" +
                "xw = " + (x+w) + ";\n" +
                "yh = " + (y+h) + ";\n" +
                "v = " + v + ";\n" +
                "yv = " + (y+v) + ";\n" +
                "yhv = " + (y+h+v) + ";\n";

        try {

            if(isColiding(x,y+v)||isColiding(x+w,y+v)) {

                velocityY = (((y / TILE_HEIGHT_PIXELS) * TILE_HEIGHT_PIXELS) - y);

                Log.d("TileMap.java", "colisionY():top of object is colliding;\n"+message +
                        "fv = " + (velocityY) + ";");
                return (int)velocityY;

            }


            if(isColiding(x, y+v+h)||isColiding(x+w,y+v+h)) {

                velocityY = ((((y / TILE_HEIGHT_PIXELS)+1) * TILE_HEIGHT_PIXELS) -y)-1;

                Log.d("TileMap.java", "colisionY():bottom of object is colliding;\n"+message +
                        "fv = " + (velocityY) + ";");
                return velocityY;

            }


        }
        catch(ArrayIndexOutOfBoundsException e){
            Log.wtf("TileMap.java","colisionY():object {"+ object +"} out of the map;\n"+message);
        }

        return velocityY;
    }
}
