package com.example.jogomobille.game.gameobject.enemy;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.jogomobille.R;
import com.example.jogomobille.game.GameDisplay;
import com.example.jogomobille.game.Gameloop;
import com.example.jogomobille.game.gameobject.player.Player;
import com.example.jogomobille.game.graphics.EnemyAnimator;
import com.example.jogomobille.game.graphics.SpriteSheet;
import com.example.jogomobille.game.map.Mechanics.Colision;
import com.example.jogomobille.game.map.TileMap;
import com.example.jogomobille.utils.Coordenada;

public class Teraculos extends Bot {

    private static final double SPAWNS_PER_MINUTE = 10;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE / 60.0;
    private static final double UPDATES_PER_SPAWN = Gameloop.MAX_UPS / SPAWNS_PER_SECOND;
    private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;
    private final Player player;
    private final EnemyAnimator animator    ;
    private final SpriteSheet spriteSheet;
    private Player.Direction direction;

    public Teraculos(Context context, int color, double positionX, double positionY, double radius, TileMap tileMap, Player player, EnemyAnimator animator) {
        super(context, color, positionX, positionY, radius, tileMap, 200);

        this.player = player;
        this.animator = animator;
        this.spriteSheet = new SpriteSheet(context);
    }



    public Teraculos(Context context, Player player, TileMap tilemap, EnemyAnimator animator) {
        super(
                context,
                ContextCompat.getColor(context, R.color.enemy),
                Math.random() * 1000,
                Math.random() * 1000,
                32,
                tilemap,
                200
        );

        this.animator = animator;
        this.player = player;
        this.spriteSheet = new SpriteSheet(context);
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

    @Override
    public void update() {
        super.update(new Coordenada((int)player.getPositionX(), (int) player.getPositionY()));
    }
}
