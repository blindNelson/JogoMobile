package com.example.jogomobille.game;

import android.content.Context;
import android.graphics.Canvas;

import com.example.jogomobille.game.gameobject.player.Player;
import com.example.jogomobille.game.gamepanel.Performance;
import com.example.jogomobille.game.gamepanel.PlayerPosition;

public class GamePainels {

    private final Gameloop gameloop;
    private final Context context;
    private final Performance performance;
    private final Player player;
    private final PlayerPosition playerPosition;

    public GamePainels(Gameloop gameloop, Context context, Player player){
        this.gameloop = gameloop;
        this.context = context;
        this.player = player;

        this.performance = new Performance(gameloop, context);
        this.playerPosition = new PlayerPosition(player, context);
    }

    public void draw(Canvas canvas){
        performance.draw(canvas);
        playerPosition.draw(canvas);
    }

    public void update(){
    }
}
