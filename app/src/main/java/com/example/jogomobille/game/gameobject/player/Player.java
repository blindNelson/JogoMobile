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
import com.example.jogomobille.game.map.MapLayout;
import com.example.jogomobille.utils.Utils;
import com.example.jogomobille.game.gamepanel.Joystick;
import com.example.jogomobille.R;

import java.util.Map;

/**
 * Player is the main character of the game, which the user can control with a touch joystick.
 * The player class is an extension of a Circle, which is an extension of GameObject
 */
public class Player extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / Gameloop.MAX_UPS;

    private final Joystick joystick;
    private final PlayerState playerState;

    public Player(Context context, Joystick joystick, double positionX, double positionY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.player), positionX, positionY, radius);
        this.joystick = joystick;

        playerState = new PlayerState(this);
    }

    public void update() {
        // Update velocity based on actuator of joystick
        velocityX = joystick.getActuatorX() * MAX_SPEED;
        velocityY = joystick.getActuatorY() * MAX_SPEED;

        Log.d("Player.java", "update():velocityX=" + velocityX);
        Log.d("Player.java", "update():velocityY=" + velocityY);

        if (MapLayout.isWall(playerPositionXToMapCoordinates(this.positionX+velocityX), playerPositionYToMapCoordinates(this.positionY))) {
            double difference = (positionX+velocityX) % 32.0;
            if (velocityX < 0) difference = -difference;
            positionX += velocityX - difference - 1;
        } else {
            positionX += velocityX;
        }

        if (MapLayout.isWall(playerPositionXToMapCoordinates(this.positionX), playerPositionYToMapCoordinates(this.positionY+velocityY))) {
            double difference = (positionY+velocityY) % 32.0;
            if (velocityY < 0) difference = -difference;
            positionY += velocityY - difference - 1;
        } else {
            positionY += velocityY;
        }



        // Update direction
        if (velocityX != 0 || velocityY != 0) {
            // Normalize velocity to get direction (unit vector of velocity)
            double distance = Utils.getDistanceBetweenPoints(0, 0, velocityX, velocityY);
            directionX = velocityX / distance;
            directionY = velocityY / distance;
        }

    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawCircle(
                (int)gameDisplay.gameToDisplayCoordinatesX(positionX),
                (int)gameDisplay.gameToDisplayCoordinatesY(positionY),
                16,
                paint
        );

        Paint paint2 = new Paint();
        paint2.setTextSize(25);
        paint2.setColor(Color.WHITE);

        canvas.drawText("x: " + positionX + "  y: " + positionY, 200, 400, paint2);
        canvas.drawText("["+playerPositionXToMapCoordinates(this.positionX)+"], [" + playerPositionYToMapCoordinates(this.positionY) + "]", 200, 200, paint2);
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

    public int playerPositionXToMapCoordinates(double positionX) { return (int)(positionX / MapLayout.TILE_WIDTH_PIXELS); }
    public int playerPositionYToMapCoordinates(double positionY) { return (int)(positionY / MapLayout.TILE_HEIGHT_PIXELS); }
}
