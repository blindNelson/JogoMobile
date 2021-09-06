package com.example.jogomobille.game;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;

import com.example.jogomobille.MainActivity;

public class Game extends SurfaceView implements SurfaceHolder.Callback{


    /* =============================================================================================
    * ================================Declaração de variaveis=======================================
    * */

    private GameLoop gameLoop;


     /* ============================================================================================
     * */


    //contrutor
    public Game(Context context) {
        super(context);
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        gameLoop = new GameLoop(this, surfaceHolder);
    }

    //metodos do jogo

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);


    }

    public void update() {

    }

    //listenner do jogo
    @Override
    public boolean onTouchEvent(MotionEvent event){
        return true;
    }


    //metodos Surface View
    //começa o loop
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        if(gameLoop.getState().equals(Thread.State.TERMINATED)){
            gameLoop = new GameLoop(this, holder);
        }
        gameLoop.startLoop();
    }

    //pausa o loop
    public void pause() {
        gameLoop.stopLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

}
