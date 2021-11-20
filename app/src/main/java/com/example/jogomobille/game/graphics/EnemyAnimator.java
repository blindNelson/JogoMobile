package com.example.jogomobille.game.graphics;

import android.graphics.Canvas;
import android.util.Log;

import com.example.jogomobille.game.GameDisplay;
import com.example.jogomobille.game.gameobject.Enemy;
import com.example.jogomobille.game.gameobject.player.Player;

public class EnemyAnimator {
    private Sprite[] enemySpriteArray;
    private int idxNotMovingFrame = 0;
    private int idxMovingFrame = 1;
    private int updatesBeforeNMF = 1;
    private static final int MAX_UPD_BEFORE_NMF = 5;

    public EnemyAnimator(Sprite[] enemySpriteArray) {
        this.enemySpriteArray = enemySpriteArray;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay, Enemy enemy) {
        updatesBeforeNMF--;
        if(updatesBeforeNMF == 0){

            updatesBeforeNMF = MAX_UPD_BEFORE_NMF;
            toggleIMF();
        }
        drawFrame(canvas, gameDisplay, enemy, enemySpriteArray[idxMovingFrame]);
    }

    private void toggleIMF() {

        idxMovingFrame++;
        if (idxMovingFrame > 3) idxMovingFrame = 0;
    }

    public void drawFrame(Canvas canvas, GameDisplay gameDisplay, Enemy enemy, Sprite sprite){
        sprite.draw(
                canvas,
                (int)gameDisplay.gameToDisplayCoordinatesX(enemy.getPositionX() - 32),
                (int)gameDisplay.gameToDisplayCoordinatesY(enemy.getPositionY() - 32)
        );
    }

    public void setEnemySpriteArray(Sprite[] enemySpriteArray) {
        this.enemySpriteArray = enemySpriteArray;
    }
}