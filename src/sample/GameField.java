package sample;

import java.util.Arrays;

public class GameField {

    private int score = 0;

    public void setScore(int score) {
        this.score = score;
    }

    public String getScoreString() {
        return "" + score;
    }

    private void increaseScore (int a, int b) {
        if (a == b) score += a+b;
    }

    private int[][] gameField = new int[4][4];

    public int[][] getGameField() {
        return gameField;
    }

    public void resetField() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                gameField[i][j] = 0;
            }
        }
    }

    private void write(int[][] field) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(field[i][j] + "   ");
            }
            System.out.println("");
        }
    }

    void addRandom() {
        while (true) {
            int x = (int) (Math.random() * 4);
            int y = (int) (Math.random() * 4);
            if (gameField[x][y] == 0) {
                gameField[x][y] = 2;
                break;
            }
        }
    }

    private boolean isPowerOfTwo(int x) {
        return ((x != 0) && ((x & (~x + 1)) == x));
    }

    private int checkNotNullRow(int row) {
        int notNull = 0;
        for (int j = 0; j < 4; j++) {
            if (gameField[row][j] != 0) notNull++;
        }
        return notNull;
    }

    private int checkNotNullColumn(int column) {
        int notNull = 0;
        for (int i = 0; i < 4; i++) {
            if (gameField[i][column] != 0) notNull++;
        }
        return notNull;
    }

    private void moveColumnUp(int column) {
            for (int i = 1; i < 4; i++) {
                if (gameField[i][column] != 0 && isPowerOfTwo(gameField[i - 1][column] + gameField[i][column])) {
                    increaseScore(gameField[i - 1][column], gameField[i][column]);
                    gameField[i - 1][column] += gameField[i][column];
                    gameField[i][column] = 0;
                }
            }
    }

    private void moveColumnDown(int column) {
            for (int i = 2; i > -1; i--) {
                if (gameField[i][column] != 0 && isPowerOfTwo(gameField[i + 1][column] + gameField[i][column])) {
                    increaseScore(gameField[i + 1][column], gameField[i][column]);
                    gameField[i + 1][column] += gameField[i][column];
                    gameField[i][column] = 0;
                }
            }
    }

    private void moveRowLeft(int row) {
            for (int j = 1; j < 4; j++) {
                if (gameField[row][j] != 0 && isPowerOfTwo(gameField[row][j - 1] + gameField[row][j])) {
                    increaseScore(gameField[row][j - 1], gameField[row][j]);
                    gameField[row][j - 1] += gameField[row][j];
                    gameField[row][j] = 0;
                }
            }
    }

    private void moveRowRight(int row) {
            for (int j = 2; j > -1; j--) {
                if (gameField[row][j] != 0 && isPowerOfTwo(gameField[row][j + 1] + gameField[row][j])) {
                    increaseScore(gameField[row][j + 1], gameField[row][j]);
                    gameField[row][j + 1] += gameField[row][j];
                    gameField[row][j] = 0;
                }
            }
    }

    private int[][] setPreviousField(int[][] original) {
        int[][] checkField = new int[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(original[i], 0, checkField[i], 0, 4);
        }
        return checkField;
    }

    public void action(String direction) {
        int[][] previousField = setPreviousField(gameField);
        switch (direction) {
            case "UP": {
                for (int i = 0; i < 4; i++) {
                    for (int a = 0; a < 4; a++) {
                        int notNull = checkNotNullColumn(i);
                        moveColumnUp(i);
                        if (notNull != checkNotNullColumn(i)) break;
                    }
                }
            }
            break;
            case "DOWN": {
                for (int i = 0; i < 4; i++) {
                    for (int a = 0; a < 4; a++) {
                        int notNull = checkNotNullColumn(i);
                        moveColumnDown(i);
                        if (notNull != checkNotNullColumn(i)) break;
                    }
                }
            }
            break;
            case "LEFT": {
                for (int i = 0; i < 4; i++) {
                    for (int a = 0; a < 4; a++) {
                        int notNull = checkNotNullRow(i);
                        moveRowLeft(i);
                        if (notNull != checkNotNullRow(i)) break;
                    }
                }
            }
            break;
            case "RIGHT": {
                for (int i = 0; i < 4; i++) {
                    for (int a = 0; a < 4; a++) {
                        int notNull = checkNotNullRow(i);
                        moveRowRight(i);
                        if (notNull != checkNotNullRow(i)) break;
                    }
                }
            }
            break;
        }
        if (!Arrays.deepEquals(gameField, previousField)) addRandom();
    }
}