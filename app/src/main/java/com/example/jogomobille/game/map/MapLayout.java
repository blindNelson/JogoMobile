package com.example.jogomobille.game.map;

import com.example.jogomobille.GameActivity;
import com.example.jogomobille.LevelDifficultyActivity;
import com.example.jogomobille.game.map.Mechanics.Labirinto;
import com.example.jogomobille.utils.LevelDifficulty;

public class MapLayout {
    public static final int TILE_WIDTH_PIXELS = 128;
    public static final int TILE_HEIGHT_PIXELS = 128;
    public static int DIFFICULT = 6;
    public static int LEVEL = 10;
    public static int NUMBER_OF_ROW_TILES = 2 * LEVEL + 1;
    public static int NUMBER_OF_COLUMN_TILES = 2 * LEVEL + 1;
    public static Labirinto lab;
    private static byte[][] layout;

    public MapLayout(LevelDifficulty levelDifficulty) {
        LEVEL = 2*(levelDifficulty.getLevel()/2) + 3;
        DIFFICULT = levelDifficulty.getDifficulty() + 3;
        NUMBER_OF_ROW_TILES = 2 * LEVEL + 1;
        NUMBER_OF_COLUMN_TILES = 2 * LEVEL + 1;
        initializeLayout();
    }

    public static boolean isWall(int x, int y) {
        return layout[x][y] == 1;
    }

    public byte[][] getLayout() {
        return layout;
    }

    private void initializeLayout() {
        try {
            lab = new Labirinto(LEVEL, DIFFICULT, "randomico");
            layout = lab.getLabirinto();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int iRow = 0; iRow < NUMBER_OF_ROW_TILES-1; iRow++) {
            for (int iCol = 0; iCol < NUMBER_OF_COLUMN_TILES; iCol++) {
                if (layout[iRow][iCol] == 1 && layout[iRow+1][iCol] == 1) {
                    layout[iRow][iCol] = 2;
                }
            }
        }
    }
}
