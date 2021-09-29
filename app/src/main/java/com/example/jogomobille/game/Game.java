package com.example.jogomobille.game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import com.example.jogomobille.game.gameobject.player.Player;
import com.example.jogomobille.game.gamepanel.Joystick;
import com.example.jogomobille.game.graphics.SpriteSheet;
import com.example.jogomobille.game.map.TileMap;

/**
 * Game manages all objects in the game and its responsible for updating all states and render all
 * objects to the screen
 */

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    //private final GameScene gameScene;
    private GamePanels gamePanels;
    private Context context;
    private Player player;
    private Joystick joystick;
    private final TileMap tilemap;

    private Gameloop gameLoop;
    private int joystickPointerId = 0;
    private GameDisplay gameDisplay;

    public Game(Context context) {
        super(context);
        this.context = context;

        // Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new Gameloop(this, surfaceHolder);


        // Initialize game objects
        joystick = new Joystick(100, 600, 70, 40);
        SpriteSheet spriteSheet = new SpriteSheet(context);
        tilemap = new TileMap(spriteSheet);
        player = new Player(context, joystick, 100, 300, 16, tilemap.getColision());

        //gameScene = new GameScene(getContext(), player);

        // Initialize game panels
        gamePanels = new GamePanels(gameLoop, getContext(), player);


        // Initialize gameDisplay and center it around the player
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);

        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

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

        //gameScene.draw(canvas, gameDisplay);
        tilemap.draw(canvas, gameDisplay);
        player.draw(canvas, gameDisplay);
        gamePanels.draw(canvas);

        joystick.draw(canvas);

    }

    public void update() {
        // Update game stateplayer.update();


        player.update();
        gamePanels.update();
        //gameScene.update();

        gameDisplay.update();

        joystick.update();

    }

    public void pause() {
        gameLoop.stopLoop();
    }

}
