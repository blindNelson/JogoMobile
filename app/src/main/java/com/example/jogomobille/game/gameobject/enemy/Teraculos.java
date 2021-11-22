package com.example.jogomobille.game.gameobject.enemy;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.example.jogomobille.game.GameDisplay;
import com.example.jogomobille.game.gameobject.player.Player;
import com.example.jogomobille.game.map.TileMap;
import com.example.jogomobille.utils.Coordenada;

public class Teraculos extends Bot {

    private final Player player;

    public Teraculos(Context context, int color, double positionX, double positionY, double radius, TileMap tileMap, Player player) {
        super(context, color, positionX, positionY, radius, tileMap, 200);

        this.player = player;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        super.draw(canvas, gameDisplay);
    }

    @Override
    public void update() {
        super.update(new Coordenada((int)player.getPositionX(), (int) player.getPositionY()));
    }
}
