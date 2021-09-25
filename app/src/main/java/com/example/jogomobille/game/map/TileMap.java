package com.example.jogomobille.game.map;

import static com.example.jogomobille.game.map.MapLayout.NUMBER_OF_COLUMN_TILES;
import static com.example.jogomobille.game.map.MapLayout.NUMBER_OF_ROW_TILES;
import static com.example.jogomobille.game.map.MapLayout.TILE_HEIGHT_PIXELS;
import static com.example.jogomobille.game.map.MapLayout.TILE_WIDTH_PIXELS;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

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
                (idxCol + 1)*TILE_WIDTH_PIXELS,
                (idxRow + 1)*TILE_HEIGHT_PIXELS,
                (idxCol + 1)*TILE_WIDTH_PIXELS,
                (idxRow + 1)*TILE_HEIGHT_PIXELS
        );
    }


    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawBitmap(mapBitmap, gameDisplay.getGameRect(), gameDisplay.DISPLAY_RECT, null);
    }


    public double colisionX(Player player, double velocityX) {
        if(player.getPositionX()+velocityX<0)
            velocityX = -player.getPositionX();
        if(player.getPositionX()+velocityX+ player.getWidth()>=NUMBER_OF_COLUMN_TILES*TILE_WIDTH_PIXELS)
            velocityX = NUMBER_OF_COLUMN_TILES*TILE_WIDTH_PIXELS - (player.getPositionX()+ player.getWidth());


        return velocityX;
    }

    public double colisionY(Player player, double velocityY) {
        if(player.getPositionY()+velocityY<=0)
            velocityY = -player.getPositionY();
        if(player.getPositionY()+velocityY+player.getHeight()>=NUMBER_OF_ROW_TILES*TILE_HEIGHT_PIXELS)
            velocityY=NUMBER_OF_ROW_TILES*TILE_HEIGHT_PIXELS - (player.getPositionY()+ player.getHeight());
        return velocityY;
    }
}
