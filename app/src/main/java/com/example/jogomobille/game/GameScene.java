package com.example.jogomobille.game;

import android.content.Context;
import android.graphics.Canvas;

import com.example.jogomobille.game.gameobject.player.Player;
import com.example.jogomobille.game.graphics.SpriteSheet;
import com.example.jogomobille.game.map.TileMap;

public class GameScene {

    private final TileMap tilemap;

    public GameScene(Context context, Player player){
        SpriteSheet spriteSheet = new SpriteSheet(context);
        tilemap = new TileMap(spriteSheet);
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        tilemap.draw(canvas, gameDisplay);
    }

    public void update(){

    }

}
