package com.example.jogomobille.game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.jogomobille.R;
import com.example.jogomobille.game.gameobject.enemy.Teraculos;
import com.example.jogomobille.GameActivity;
import com.example.jogomobille.game.gameobject.Circle;
import com.example.jogomobille.game.gameobject.player.Player;
import com.example.jogomobille.game.gamepanel.Joystick;
import com.example.jogomobille.game.graphics.Animator;
import com.example.jogomobille.game.graphics.EnemyAnimator;
import com.example.jogomobille.game.graphics.SpriteSheet;
import com.example.jogomobille.game.map.MapLayout;
import com.example.jogomobille.game.map.TileMap;
import com.example.jogomobille.utils.Coordenada;
import com.example.jogomobille.utils.LevelDifficulty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Game manages all objects in the game and its responsible for updating all states and render all
 * objects to the screen
 */

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private final SpriteSheet spriteSheet;
    private final Teraculos enemy;
    private GamePanels gamePanels;
    private Context context;
    private Player player;
    private Joystick joystick;
    private TileMap tileMap;
    private List<Teraculos> enemyList = new ArrayList<Teraculos>();
    private final TileMap tilemap;
    private Canvas canvas;
    private LevelDifficulty levelDifficulty;

    public void setActivity2(GameActivity activity2) {
        this.activity2 = activity2;
    }

    private GameActivity activity2;

    private Gameloop gameLoop;
    private int joystickPointerId = 0;
    private GameDisplay gameDisplay;

    public Game(Context context,LevelDifficulty _levelDifficulty) {
        super(context);
        this.context = context;
        this.levelDifficulty = _levelDifficulty;

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new Gameloop(this, surfaceHolder);

        // Initialize game objects

        //tileMap = new TileMap(spriteSheet);
        //player = new Player(context, joystick, 128, 128, 32, tileMap);
        //enemegus = new Teraculos(context, ContextCompat.getColor(context, R.color.enemy), 200, 200, 30, tileMap, player);

        joystick = new Joystick(300, 800, 70, 40);
        spriteSheet = new SpriteSheet(context);
        tilemap = new TileMap(spriteSheet, levelDifficulty);
        Coordenada cordStart = MapLayout.lab.getEntrada();
        Animator animator = new Animator(spriteSheet.getPlayerSpriteArrayDown());
        player = new Player(context, joystick, cordStart.getX() * 128 + 32, 128 + 32, 32, tilemap, animator);
        EnemyAnimator enemyAnimator = new EnemyAnimator(spriteSheet.getEnemySpriteArrayDown());
        enemy = new Teraculos(context, ContextCompat.getColor(context, R.color.enemy), 1200, 1000, 32, tilemap, player, enemyAnimator);
        enemyList.add(enemy);

        // Initialize game panels
        gamePanels = new GamePanels(gameLoop, getContext(), player);

        // Initialize gameDisplay and center it around the player
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels/2, displayMetrics.heightPixels/2, player);

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (joystick == null) return true;

        // Handle touch event actions
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (joystick.isPressed((double) event.getX(), (double) event.getY())) {
                    // Joystick is pressed in this event -> setIsPressed(true)
                    joystickPointerId = event.getPointerId(event.getActionIndex());
                    joystick.setIsPressed(true);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                // Joystick was pressed previously and is now moved
                if (joystick.getIsPressed()) {
                    joystick.setActuator((double) event.getX(), (double) event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (joystickPointerId == event.getPointerId(event.getActionIndex())) {
                    // Joystick was let go of -> setIsPressed (false) and resetActuator
                    joystick.setIsPressed(false);
                    joystick.resetActuator();
                }
                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            SurfaceHolder surfaceHolder = getHolder();
            surfaceHolder.addCallback(this);
            gameLoop = new Gameloop(this, surfaceHolder);
        }
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.canvas = canvas;

        //gameScene.draw(canvas, gameDisplay);
        tileMap.draw(canvas, gameDisplay);
        player.draw(canvas, gameDisplay);

        for (Teraculos enemy : enemyList) {
            enemy.draw(canvas, gameDisplay);
        }

        gamePanels.draw(canvas);

        joystick.draw(canvas);

    }

    public void update() {
        // Update game stateplayer.update();
        player.update();

        if (gamePanels != null) {
            gamePanels.update();
        }


        player.update();

        if(tileMap.isExit((int) player.getPositionX(), (int)player.getPositionY())){
            ganhou();
        }

        //gameScene.update();

        /*if (Enemy.readyToSpawn()) {
            EnemyAnimator enemyAnimator = new EnemyAnimator(spriteSheet.getEnemySpriteArrayDown());
            enemyList.add(new Enemy(getContext(), player, tilemap.getColision(), enemyAnimator));
        }*/

        for (Teraculos enemy : enemyList) {
            enemy.update();
        }

        Iterator<Teraculos> iteratorEnemy = enemyList.iterator();
        while (iteratorEnemy.hasNext()) {
            Circle enemy = iteratorEnemy.next();
            if (Circle.isColliding(enemy, player)) {
                // Remove enemy if it collides with the player
                iteratorEnemy.remove();
                // player.setHealthPoints(player.getHealthPoints() - 1);
                morreu();
                continue;
            }
        }

        /*if (Circle.isColliding(enemy, player)) {
            enemy = null;
        }*/

        gameDisplay.update();

        if (joystick != null) {
            joystick.update();
        }
    }



    public void pause() {
        gameLoop.stopLoop();
    }

    public void morreu() {
        activity2.fugiu();
    }

    public void ganhou(){
        activity2.morreu();
    }

    public void fugiu() {
        gameLoop.stopLoop();
    }

    public int getScore() {
        return (int) (levelDifficulty.getLevel() * levelDifficulty.getDifficulty() / 0.3); //0.3 deve ser substituido pelo tempo em horas;
    }
}
