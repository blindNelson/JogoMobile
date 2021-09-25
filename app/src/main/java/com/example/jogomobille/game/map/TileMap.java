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
import com.example.jogomobille.game.gameobject.player.Player;
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

    private boolean isRetColiding(int x, int y, int w, int h ){
        if (isColiding(x, y)||isColiding(x,y + h)){
            return true;
        }

        if (isColiding(x+w,y)|| isColiding(x+w,y + h)){
            return true;
        }
        return false;
    }



    public double colisionX(Player player, double velocityX) {

        //colisão com bordas do mapa
        if (player.getPositionX() + velocityX < 0)
            return -player.getPositionX();
        if (player.getPositionX() + velocityX + player.getWidth() >= NUMBER_OF_COLUMN_TILES * TILE_WIDTH_PIXELS)
            return (NUMBER_OF_COLUMN_TILES * TILE_WIDTH_PIXELS - (player.getPositionX() + player.getWidth()))-1;



        //colisão com paredes
        try {
            int x = (int)player.getPositionX();
            int y = (int)player.getPositionY();
            int w = (int)player.getWidth();
            int h = (int)player.getHeight();
            int v = (int)velocityX;

            if(isColiding(x+v, y)||isColiding(x+v, y+h))
                return ((double)((((x + v) / TILE_WIDTH_PIXELS)+1) * TILE_WIDTH_PIXELS) - player.getPositionX());
            if(isColiding(x+v+w, y)||isColiding(x+v+w,y+h)) {
                return ((double)(((x + v) / TILE_WIDTH_PIXELS) * TILE_WIDTH_PIXELS) - player.getPositionX())-1;
            }
        }
        catch(ArrayIndexOutOfBoundsException e){
            return 0;
        }

        return velocityX;
    }

    public double colisionY(Player player, double velocityY) {
        if(player.getPositionY()+velocityY<=0)
            return  -player.getPositionY();
        if(player.getPositionY()+velocityY+player.getHeight()>=NUMBER_OF_ROW_TILES*TILE_HEIGHT_PIXELS)
            return (NUMBER_OF_ROW_TILES*TILE_HEIGHT_PIXELS - (player.getPositionY()+ player.getHeight()))-1;

        try {
            int x = (int)player.getPositionX();
            int y = (int)player.getPositionY();
            int w = (int)player.getWidth();
            int h = (int)player.getHeight();
            int v = (int)velocityY;

            if(isColiding(x, y+v)||isColiding(x+w, y+v))
                return ((double)((((y + v) / TILE_HEIGHT_PIXELS)+1) * TILE_HEIGHT_PIXELS) - player.getPositionY());
            if(isColiding(x, y+v+h)||isColiding(x+w,y+v+h)) {
                return ((double)(((y + v) / TILE_HEIGHT_PIXELS) * TILE_HEIGHT_PIXELS) - player.getPositionY())-1;
            }

        }
        catch(ArrayIndexOutOfBoundsException e){
            return 0;
        }

        return velocityY;
    }
}
