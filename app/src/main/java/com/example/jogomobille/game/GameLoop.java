package com.example.jogomobille.game;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameLoop extends Thread{

    /* =============================================================================================
     * ================================Declaração de variaveis=======================================
     * */

    public static final double MAX_UPS = 60.0;
    public static final double UPS_PERIOD = 1E+3/MAX_UPS;

    private final Game game;
    private final SurfaceHolder surfaceHolder;

    private boolean isRuning = false;
    private double avarageUPS;
    private  double avarageFPS;

    /* ============================================================================================
     * */

    //contrutor
    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;
    }

    //começa o loop
    public void startLoop(){
        Log.d("GameLoop.java", "startLoop()");
        isRuning = true;
        start();
    }


    //pausa o loop
    public void stopLoop(){
        Log.d("GameLoop.java", "stopLoop()");
        isRuning = false;
        try{
            join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        Log.d("GameLoop.java", "run()");
        super.run();
        int updateCount = 0;
        int frameCount = 0;

        long startTime;     //tempo que o loop começou
        long elapsedTime;
        long sleepTime;

        Canvas canvas = null;
        startTime = System.currentTimeMillis();


        while(isRuning){    //o loop do jogo
            try{
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    game.update();
                    updateCount++;
                    game.draw(canvas);
                }
            }
            catch (IllegalArgumentException e){
                e.printStackTrace();
            }
            finally {
                try {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        frameCount++;
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }

            updateCount++;
            frameCount++;

            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long) (updateCount*UPS_PERIOD-elapsedTime);
            if(sleepTime > 0){
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while(sleepTime < 0 && updateCount < MAX_UPS-1){
                game.update();
                updateCount++;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long)(updateCount*UPS_PERIOD-elapsedTime);
            }

            elapsedTime = System.currentTimeMillis() - startTime;
            while(elapsedTime < 1000){
                avarageUPS = updateCount/(1E-3*elapsedTime);
                avarageFPS = frameCount/(1E-3*elapsedTime);
                updateCount=0;
                frameCount=0;
                startTime = System.currentTimeMillis();
            }
        }
    }

    /**
        * ==========================================================================================
        * ==========================================================================================
    */

    public double getAvarageUPS() {
        return avarageUPS;
    }

    public double getAvarageFPS() {
        return avarageFPS;
    }

}
