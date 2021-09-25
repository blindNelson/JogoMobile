package com.example.jogomobille.game.map;

public class MapLayout {
    public static final int TILE_WIDTH_PIXELS = 64;
    public static final int TILE_HEIGHT_PIXELS = 64;
    public static final int NUMBER_OF_ROW_TILES = 15;
    public static final int NUMBER_OF_COLUMN_TILES = 15;

    private static int[][] layout;

    public MapLayout() {
        initializeLayout();
    }

    public static boolean isWall(int x, int y) {
        return layout[x][y] == 1;
    }

    public int[][] getLayout() {
        return layout;
    }

    private void initializeLayout() {
        layout = new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
    }
}
