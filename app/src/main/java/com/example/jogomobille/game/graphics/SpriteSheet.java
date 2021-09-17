package com.example.jogomobille.game.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.jogomobille.R;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 64;
    private static final int SPRITE_HEIGHT_PIXELS = 64;
    private Bitmap bitmap;


    public SpriteSheet(Context context){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheet, bitmapOptions);
    }

    public Sprite[] getPlayerSpriteArray(){
        Sprite[] spriteArray = new Sprite[3];
        spriteArray[0]= new Sprite(this, new Rect(0, 0, 64, 64));
        spriteArray[1]= new Sprite(this, new Rect(64, 0, 64*2, 64));
        spriteArray[2]= new Sprite(this, new Rect(64*2, 0, 64*3, 64));
        return spriteArray;
    }

    public Sprite getEnemySprite(){
        return new Sprite(this, new Rect(0, 64, 64, 128));
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Sprite getGroundSprite() {
        return new Sprite(this, new Rect(0, 128, 64, 192 ));
    }

//    public Sprite getGroundSprite() {
//        return getSpriteByIndex(2, 1);
//    }

    private Sprite getSpriteByIndex(int idxRow, int idxCol) {
        return new Sprite(this, new Rect(
              idxCol*SPRITE_WIDTH_PIXELS,
              idxRow*SPRITE_HEIGHT_PIXELS,
              (idxCol+1)*SPRITE_WIDTH_PIXELS,
              (idxCol+1)*SPRITE_HEIGHT_PIXELS
        ));
    }
}
