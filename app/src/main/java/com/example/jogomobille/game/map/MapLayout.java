package com.example.jogomobille.game.map;

import com.example.jogomobille.game.map.Mechanics.Labirinto;

public class MapLayout {
    public static final int TILE_WIDTH_PIXELS = 128;
    public static final int TILE_HEIGHT_PIXELS = 128;
    public static final int DIFICULDADE = 6;
    public static final int FASE = 10;
    public static final int NUMBER_OF_ROW_TILES = 2 * FASE + 1;
    public static final int NUMBER_OF_COLUMN_TILES = 2 * FASE + 1;

    private static byte[][] layout;

    public MapLayout() {
        initializeLayout();
    }

    public static boolean isWall(int x, int y) {
        return layout[x][y] == 1;
    }

    public byte[][] getLayout() {
        return layout;
    }

    private void initializeLayout() {
        /*layout = new byte[][] {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };*/

        try {
            Labirinto lab = new Labirinto(FASE, DIFICULDADE, "fixo");
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
