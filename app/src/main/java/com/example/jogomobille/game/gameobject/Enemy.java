package com.example.jogomobille.game.gameobject;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.example.jogomobille.game.GameDisplay;
import com.example.jogomobille.game.Gameloop;
import com.example.jogomobille.R;
import com.example.jogomobille.game.gameobject.player.Player;
import com.example.jogomobille.game.graphics.Animator;
import com.example.jogomobille.game.graphics.EnemyAnimator;
import com.example.jogomobille.game.graphics.SpriteSheet;
import com.example.jogomobille.game.map.Mechanics.Colision;

public class Enemy extends Circle {
    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND * 0.6;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / Gameloop.MAX_UPS;
    private static final double SPAWNS_PER_MINUTE = 10;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE / 60.0;
    private static final double UPDATES_PER_SPAWN = Gameloop.MAX_UPS / SPAWNS_PER_SECOND;
    private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;
    private final Player player;
    private EnemyAnimator animator;
    private double directionX, directionY;
    private Player.Direction direction = Player.Direction.DOWN;
    private SpriteSheet spriteSheet;
    private Colision tilemap;

    public Enemy(Context context, Player player, double positionX, double positionY, double radius, Colision tilemap, EnemyAnimator animator) {
        super(context, ContextCompat.getColor(context, R.color.enemy), positionX, positionY, radius, tilemap);
        this.player = player;
        this.animator = animator;
        this.spriteSheet = new SpriteSheet(context);
        this.tilemap = tilemap;
    }

    public Enemy(Context context, Player player, Colision tilemap, EnemyAnimator animator) {
        super(
                context,
                ContextCompat.getColor(context, R.color.enemy),
                Math.random() * 1000,
                Math.random() * 1000,
                32,
                tilemap
        );

        this.animator = animator;
        this.player = player;
    }

    public static boolean readyToSpawn() {
        if (updatesUntilNextSpawn <= 0) {
            updatesUntilNextSpawn += UPDATES_PER_SPAWN;
            return true;
        } else {
            updatesUntilNextSpawn--;
            return false;
        }
    }

    @Override
    public void update() {
        //--------------------------------
        // Update velocity of the enemy so that the velocity is in the direction of the player
        //--------------------------------
        // Calculate vector from enemy to player (in x and y)
        double distanceToPlayerX = player.getPositionX() - positionX;
        double distanceToPlayerY = player.getPositionY() - positionY;

        // Calculate (absolute) distance between enemy (this) and player
        double distanceToPlayer = GameObject.getDistanceBetweenObjects(this, player);

        // Calculate direction from enemy to player
        this.directionX = distanceToPlayerX / distanceToPlayer;
        this.directionY = distanceToPlayerY / distanceToPlayer;

        // Set velocity in the direction to the player
        if (distanceToPlayer > 0) { // Avoid division by zero
            velocityX = this.directionX * MAX_SPEED;
            velocityY = this.directionY * MAX_SPEED;
        } else {
            velocityX = 0;
            velocityY = 0;
        }

        // Update the position of the enemy
        positionX += velocityX;
        positionY += velocityY;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        //super.draw(canvas, gameDisplay);

        boolean differenceY = Math.abs(directionY) > Math.abs(directionX);
        boolean differenceX = Math.abs(directionX) > Math.abs(directionY);

        if (directionY < 0 && differenceY) direction = Player.Direction.UP;
        if (directionY > 0 && differenceY) direction = Player.Direction.DOWN;
        if (directionX < 0 && differenceX) direction = Player.Direction.LEFT;
        if (directionX > 0 && differenceX) direction = Player.Direction.RIGHT;

        switch (direction) {
            case UP: animator.setEnemySpriteArray(spriteSheet.getEnemySpriteArrayUp());              break;
            default: case DOWN: animator.setEnemySpriteArray(spriteSheet.getEnemySpriteArrayDown()); break;
            case LEFT: animator.setEnemySpriteArray(spriteSheet.getEnemySpriteArrayLeft());          break;
            case RIGHT: animator.setEnemySpriteArray(spriteSheet.getEnemySpriteArrayRight());        break;
        }

        if (directionX == 0 && directionY == 0) {
            switch (direction) {
                case UP:
                    animator.setEnemySpriteArray(spriteSheet.getEnemySpriteArrayUp());
                    break;
                default:
                case DOWN:
                    animator.setEnemySpriteArray(spriteSheet.getEnemySpriteArrayDown());
                    break;
                case LEFT:
                    animator.setEnemySpriteArray(spriteSheet.getEnemySpriteArrayLeft());
                    break;
                case RIGHT:
                    animator.setEnemySpriteArray(spriteSheet.getEnemySpriteArrayRight());
                    break;
            }
        }

        animator.draw(canvas, gameDisplay, this);

    }
}
