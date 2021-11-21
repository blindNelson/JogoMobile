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
import com.example.jogomobille.game.map.Mechanics.Colision;
import com.example.jogomobille.utils.LevelDifficulty;

public class TileMap {
    private final MapLayout mapLayout;
    private final Colision colision;
    private Tile[][] tilemap;
    private SpriteSheet spriteSheet;
    private Bitmap mapBitmap;

    public TileMap(SpriteSheet spriteSheet, LevelDifficulty levelDifficulty) {
        mapLayout = new MapLayout(levelDifficulty);
        this.spriteSheet = spriteSheet;
        initializeTileMap();
        colision = new Colision(this);
    }

    private void initializeTileMap() {
        byte[][] layout = mapLayout.getLayout();
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
        canvas.scale((float)2, (float)2);
        canvas.drawBitmap(mapBitmap, gameDisplay.getGameRect(), gameDisplay.DISPLAY_RECT, null);
    }



    public boolean isColiding(int x, int y){
        return tilemap[y/TILE_HEIGHT_PIXELS][x/TILE_WIDTH_PIXELS].collide;
    }

    public Colision getColision() {
        return colision;
    }
    public MapLayout getMapLayout() {return  mapLayout;}
}
