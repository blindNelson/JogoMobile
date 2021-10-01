package com.example.jogomobille.game.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.jogomobille.R;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 128;
    private static final int SPRITE_HEIGHT_PIXELS = 128;

    private Bitmap bitmap;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        this.bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheet, bitmapOptions);
    }

    public Sprite[] getPlayerSpriteArrayDown() {
        return getPlayerSpriteByIndex(0, 0);
    }

    public Sprite[] getPlayerSpriteArrayLeft() {
        return getPlayerSpriteByIndex(0, 1);
    }

    public Sprite[] getPlayerSpriteArrayRight() {
        return getPlayerSpriteByIndex(0, 2);
    }

    public Sprite[] getPlayerSpriteArrayUp() {
        return getPlayerSpriteByIndex(0, 3);
    }

    // ----------------------------------------- \\

    public Sprite[] getStaticPlayerSpriteArrayDown() {
        return getPlayerSpriteByIndex(0, 4);
    }

    public Sprite[] getStaticPlayerSpriteArrayLeft() {
        return getPlayerSpriteByIndex(0, 5);
    }

    public Sprite[] getStaticPlayerSpriteArrayRight() {
        return getPlayerSpriteByIndex(0, 6);
    }

    public Sprite[] getStaticPlayerSpriteArrayUp() {
        return getPlayerSpriteByIndex(0, 7);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Sprite getGroundSprite() {
        return getSpriteByIndex(2, 0);
    }
    public Sprite getWallSprite() {
        return getSpriteByIndex(2, 1);
    }
    public Sprite getWall2Sprite() {
        return getSpriteByIndex(2, 2);
    }
    public Sprite getEntranceSprite() {
        return getSpriteByIndex(2, 3);
    }
    public Sprite getExitSprite() { return getSpriteByIndex(2, 4); }

    private Sprite getSpriteByIndex(int idxRow, int idxCol) {
        return new Sprite(this, new Rect(
                idxCol*SPRITE_WIDTH_PIXELS,
                idxRow*SPRITE_HEIGHT_PIXELS,
                (idxCol + 1)*SPRITE_WIDTH_PIXELS,
                (idxRow + 1)*SPRITE_HEIGHT_PIXELS
        ));
    }

    private Sprite[] getPlayerSpriteByIndex(int idxRow, int idxCol) {
        Sprite[] ret = new Sprite[4];
         ret[0] = new Sprite(this, new Rect(
                idxCol*64,
                idxRow*64,
                (idxCol + 1)*64,
                (idxRow + 1)*64
        ));

        ret[1] = new Sprite(this, new Rect(
                idxCol*64,
                (idxRow + 1)*64,
                (idxCol + 1)*64,
                (idxRow + 2)*64
        ));

        ret[2] = new Sprite(this, new Rect(
                idxCol*64,
                (idxRow + 2)*64,
                (idxCol + 1)*64,
                (idxRow + 3)*64
        ));

        ret[3] = new Sprite(this, new Rect(
                idxCol*64,
                (idxRow + 3)*64,
                (idxCol + 1)*64,
                (idxRow + 4)*64
        ));

        return ret;
    }
}
