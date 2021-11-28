package com.example.jogomobille.game.gameobject.player;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.jogomobille.game.Game;
import com.example.jogomobille.game.GameDisplay;
import com.example.jogomobille.game.Gameloop;
import com.example.jogomobille.game.gameobject.Circle;
import com.example.jogomobille.game.graphics.Animator;
import com.example.jogomobille.game.graphics.SpriteSheet;
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
    private final PlayerState playerState ;
    private Animator animator;
    private SpriteSheet spriteSheet;
    private Direction direction = Direction.DOWN;


    public Player(Context context, Joystick joystick, double positionX, double positionY, double radius, TileMap tileMap, Animator animator) {
        super(context, ContextCompat.getColor(context, R.color.player), positionX, positionY, radius, tileMap);

        this.joystick = joystick;
        this.animator = animator;
        playerState = new PlayerState(this);
        this.spriteSheet = new SpriteSheet(context);
    }

    public void update() {

        // Update velocity based on actuator of joystick
        velocityX = joystick.getActuatorX() * MAX_SPEED;
        velocityY = joystick.getActuatorY() * MAX_SPEED;


        super.update();

        if (velocityX != 0 || velocityY != 0) {
            // Normalize velocity to get direction (unit vector of velocity)
            double distance = Utils.getDistanceBetweenPoints(0, 0, velocityX, velocityY);
            directionX = velocityX / distance;
            directionY = velocityY / distance;
        }

        playerState.update();

//        if(tileMap.isExit((int)positionX, (int)positionY)){
//
//        }
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        //super.draw(canvas, gameDisplay);

        double directionX = joystick.getActuatorX();
        double directionY = joystick.getActuatorY();

        boolean differenceY = Math.abs(directionY) > Math.abs(directionX);
        boolean differenceX = Math.abs(directionX) > Math.abs(directionY);

        if (directionY < 0 && differenceY) direction = Direction.UP;
        if (directionY > 0 && differenceY) direction = Direction.DOWN;
        if (directionX < 0 && differenceX) direction = Direction.LEFT;
        if (directionX > 0 && differenceX) direction = Direction.RIGHT;

        switch (direction) {
            case UP: animator.setPlayerSpriteArray(spriteSheet.getPlayerSpriteArrayUp());              break;
            default: case DOWN: animator.setPlayerSpriteArray(spriteSheet.getPlayerSpriteArrayDown()); break;
            case LEFT: animator.setPlayerSpriteArray(spriteSheet.getPlayerSpriteArrayLeft());          break;
            case RIGHT: animator.setPlayerSpriteArray(spriteSheet.getPlayerSpriteArrayRight());        break;
        }

        if (directionX == 0 && directionY == 0) {
            switch (direction) {
                case UP:
                    animator.setPlayerSpriteArray(spriteSheet.getStaticPlayerSpriteArrayUp());
                    break;
                default:
                    case DOWN:
                        animator.setPlayerSpriteArray(spriteSheet.getStaticPlayerSpriteArrayDown());
                        break;
                case LEFT:
                    animator.setPlayerSpriteArray(spriteSheet.getStaticPlayerSpriteArrayLeft());
                    break;
                case RIGHT:
                    animator.setPlayerSpriteArray(spriteSheet.getStaticPlayerSpriteArrayRight());
                    break;
            }
        }

        animator.draw(canvas, gameDisplay, this);

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
