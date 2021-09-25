package com.example.jogomobille.game.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.jogomobille.game.graphics.SpriteSheet;

abstract class Tile {

    public final boolean collide;
    protected final Rect mapLocationRect;

    public Tile(Rect mapLocationRect, boolean colide) {
        this.mapLocationRect = mapLocationRect;
        this.collide = colide;
    }

    public enum TileType {
        GROUND_TILE,
        WALL_TILE,
        WALL_2_TILE
    }

    public static Tile getTile(int idxTileType, SpriteSheet spriteSheet, Rect mapLocationRect) {
        switch (TileType.values()[idxTileType]) {
            case GROUND_TILE:
                return new GroundTile(spriteSheet, mapLocationRect);
            case WALL_TILE:
                return new WallTile(spriteSheet, mapLocationRect);
            case WALL_2_TILE:
                return new Wall2Tile(spriteSheet, mapLocationRect);
            default:
                return null;
        }
    }

    public abstract void draw(Canvas canvas);
}