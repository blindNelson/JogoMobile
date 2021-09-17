package com.example.jogomobille.game.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.jogomobille.game.graphics.Sprite;
import com.example.jogomobille.game.graphics.SpriteSheet;

public class Wall2Tile extends Tile {
    private final Sprite sprite;

    public Wall2Tile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getWall2Sprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
