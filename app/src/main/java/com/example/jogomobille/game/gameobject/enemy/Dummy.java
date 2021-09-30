package com.example.jogomobille.game.gameobject.enemy;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.jogomobille.R;
import com.example.jogomobille.game.GameDisplay;
import com.example.jogomobille.game.Gameloop;
import com.example.jogomobille.game.gameobject.Circle;
import com.example.jogomobille.game.gameobject.GameObject;
import com.example.jogomobille.game.gameobject.player.Player;
import com.example.jogomobille.game.map.Mechanics.Colision;
import com.example.jogomobille.game.map.Mechanics.MapConfig;
import com.example.jogomobille.utils.Coordenada;

public class Dummy extends Circle {

    private static final double SPEED_PIXELS_PER_SECOND = 300;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / Gameloop.MAX_UPS;

    //private final MapConfig mapConfig;
    private final Player player;
    private final MapConfig mapConfig;

    public Dummy(Context context, double positionX, double positionY, Colision colision, MapConfig mapConfig, Player player) {
        super(context, ContextCompat.getColor(context, R.color.Aquamarine), positionX, positionY, 32, colision);

        this.mapConfig = mapConfig;
        this.player = player;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        super.draw(canvas, gameDisplay);
    }

    @Override
    public void update() {

        Coordenada target = mapConfig.getCoordinates(new Coordenada((int)positionX, (int)positionY), new Coordenada((int)player.getPositionX(), (int)player.getPositionY()));

        //calcular o vetor do inimigo para o player(em x e y)
        double directionToPlayerX = (target.getX() - positionX);
        double directionToPlayerY = (target.getY() - positionY);
        double auxX = directionToPlayerX;
        double auxY = directionToPlayerY;

        if(getDistanceBetweenObjects(this, player)<15)
            return;

        if(auxX<0)
            auxX=-auxX;
        if(auxY<0)
            auxY=-auxY;

        if (auxX > auxY) {
            updateVelocity(((int) ((directionToPlayerX < 0 ? -1.0 : 1.0) * MAX_SPEED)), 0);
            Log.d("Dummy.java", "update():\n" +
                    "vx:   " + velocityX + ";\n" +
                    "vy:   " + velocityY + ";\n" +
                    "dx:   " + directionToPlayerX + ";\n" +
                    "dx?:" + (directionToPlayerX < 0 ? -1.0 : 1.0) + ";\n");
        } else {
            updateVelocity(0, ((int) ((directionToPlayerY < 0 ? -1.0 : 1.0) * MAX_SPEED)));
            Log.d("Dummy.java", "update():\n" +
                    "vx:   " + velocityX + ";\n" +
                    "vy:   " + velocityY + ";\n" +
                    "dy:   " + directionToPlayerY + ";\n" +
                    "dy?:" + (directionToPlayerY < 0 ? -1.0 : 1.0) + ";\n");
        }

    }
}
