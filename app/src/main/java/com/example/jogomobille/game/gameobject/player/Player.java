package com.example.jogomobille.game.gameobject.player;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.jogomobille.game.GameDisplay;
import com.example.jogomobille.game.Gameloop;
import com.example.jogomobille.game.gameobject.Circle;
import com.example.jogomobille.game.map.Mechanics.Colision;
import com.example.jogomobille.game.map.TileMap;
import com.example.jogomobille.utils.Utils;
import com.example.jogomobille.game.gamepanel.Joystick;
import com.example.jogomobille.R;

import java.util.Map;

/**
 * Player is the main character of the game, which the user can control with a touch joystick.
 * The player class is an extension of a Circle, which is an extension of GameObject
 */
public class Player extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 500.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / Gameloop.MAX_UPS;

    private final Joystick joystick;
    private final PlayerState playerState;

    public Player(Context context, Joystick joystick, double positionX, double positionY, double radius, Colision tilemap) {
        super(context, ContextCompat.getColor(context, R.color.player), positionX, positionY, radius, tilemap);
        this.joystick = joystick;

        playerState = new PlayerState(this);
    }

    public void update() {
        // Update velocity based on actuator of joystick

        updateVelocity((int)(joystick.getActuatorX() * MAX_SPEED), (int)(joystick.getActuatorY() * MAX_SPEED));
//        updateVelocity(-5, 0);

        // Update direction
        if (velocityX != 0 || velocityY != 0) {
            // Normalize velocity to get direction (unit vector of velocity)
            double distance = Utils.getDistanceBetweenPoints(0, 0, velocityX, velocityY);
            directionX = velocityX / distance;
            directionY = velocityY / distance;
        }

    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        super.draw(canvas, gameDisplay);
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public double getVelocityX() {
        return velocityX;
    }
    public double getVelocityY() {
        return velocityY;
    }
}
