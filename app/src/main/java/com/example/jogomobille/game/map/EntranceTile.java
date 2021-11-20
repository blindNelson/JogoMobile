package com.example.jogomobille.game.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.jogomobille.game.graphics.Sprite;
import com.example.jogomobille.game.graphics.SpriteSheet;

public class EntranceTile extends Tile {
    private final Sprite sprite;

    public EntranceTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect, false);
        sprite = spriteSheet.getEntranceSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
