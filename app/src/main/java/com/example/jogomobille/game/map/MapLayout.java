package com.example.jogomobille.game.map;

import com.example.jogomobille.game.map.Mechanics.Labirinto;

public class MapLayout {
    public static final int TILE_WIDTH_PIXELS = 128;
    public static final int TILE_HEIGHT_PIXELS = 128;
    //public static final int DIFICULDADE = 10;
    public static final int NUMBER_OF_ROW_TILES = 15;//2 * DIFICULDADE + 1;
    public static final int NUMBER_OF_COLUMN_TILES = 15;//2 * DIFICULDADE + 1;

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
        layout = new byte[][] {
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

//        try {
//            Labirinto lab = new Labirinto(DIFICULDADE, "fixo");
//            layout = lab.getLabirinto();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
