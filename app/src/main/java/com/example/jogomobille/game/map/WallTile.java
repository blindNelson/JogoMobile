package com.example.jogomobille.game.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.jogomobille.game.graphics.Sprite;
import com.example.jogomobille.game.graphics.SpriteSheet;

public class WallTile extends Tile {
    private final Sprite sprite;

    public WallTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect, true);
        sprite = spriteSheet.getWallSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
