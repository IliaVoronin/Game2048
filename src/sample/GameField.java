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

    void addRandom() {
        while (true) {
            int x = (int) (Math.random() * 4);
            int y = (int) (Math.random() * 4);
            if (gameField[x][y] == 0) {
                double chance = Math.random();
                if (chance < 0.9) {
                    gameField[x][y] = 2;
                } else gameField[x][y] = 4;
                break;
            }
        }
    }

    private boolean isPowerOfTwo(int x) {
        return ((x != 0) && ((x & (~x + 1)) == x));
    }


    private void moveLeft(int row) {
        for (int y = 1; y < 4; y++) {
            int a = y;
            if (gameField[row][a] != 0) {
                while (a != 0) {
                    if (gameField[row][a - 1] == 0) {
                        gameField[row][a - 1] = gameField[row][a];
                        gameField[row][a] = 0;
                    }
                    a--;
                }
            }
        }
    }
    private void sumLeft(int row) {
        for (int y = 0; y < 3; y++) {
            if (gameField[row][y] != 0 && isPowerOfTwo(gameField[row][y + 1] + gameField[row][y])) {
                increaseScore(gameField[row][y + 1], gameField[row][y]);
                gameField[row][y] += gameField[row][y + 1];
                gameField[row][y + 1] = 0;
            }
        }
    }

    private void moveRight(int row) {
        for (int y = 2; y > -1; y--) {
            int a = y;
            if (gameField[row][a] != 0) {
                while (a != 3) {
                    if (gameField[row][a + 1] == 0) {
                        gameField[row][a + 1] = gameField[row][a];
                        gameField[row][a] = 0;
                    }
                    a++;
                }
            }
        }
    }
    private void sumRight(int row) {
        for (int y = 3; y > 0; y--) {
            if (gameField[row][y] != 0 && isPowerOfTwo(gameField[row][y - 1] + gameField[row][y])) {
                increaseScore(gameField[row][y - 1], gameField[row][y]);
                gameField[row][y] += gameField[row][y - 1];
                gameField[row][y - 1] = 0;
            }
        }
    }

    private void moveUp(int column) {
        for (int x = 1; x < 4; x++) {
            int a = x;
            if (gameField[a][column] != 0) {
                while (a != 0) {
                    if (gameField[a - 1][column] == 0) {
                        gameField[a - 1][column] = gameField[a][column];
                        gameField[a][column] = 0;
                    }
                    a--;
                }
            }
        }
    }
    private void sumUp(int column) {
        for (int x = 0; x < 3; x++) {
            if (gameField[x][column] != 0 && isPowerOfTwo(gameField[x + 1][column] + gameField[x][column])) {
                increaseScore(gameField[x + 1][column], gameField[x][column]);
                gameField[x][column] += gameField[x + 1][column];
                gameField[x + 1][column] = 0;
            }
        }
    }

    private void moveDown(int column) {
        for (int x = 3; x > 0; x--) {
            if (gameField[x][column] != 0 && isPowerOfTwo(gameField[x - 1][column] + gameField[x][column])) {
                increaseScore(gameField[x - 1][column], gameField[x][column]);
                gameField[x][column] += gameField[x - 1][column];
                gameField[x - 1][column] = 0;
            }
        }
    }

    private void sumDown(int column) {
        for (int x = 2; x > -1; x--) {
            int a = x;
            if (gameField[a][column] != 0) {
                while (a != 3) {
                    if (gameField[a + 1][column] == 0) {
                        gameField[a + 1][column] = gameField[a][column];
                        gameField[a][column] = 0;
                    }
                    a++;
                }
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
                    moveUp(i);
                    sumUp(i);
                    moveUp(i);
                }
            }
            break;
            case "DOWN": {
                for (int i = 0; i < 4; i++) {
                    sumDown(i);
                    moveDown(i);
                    sumDown(i);
                }
            }
            break;
            case "LEFT": {
                for (int i = 0; i < 4; i++) {
                    moveLeft(i);
                    sumLeft(i);
                    moveLeft(i);
                }
            }
            break;
            case "RIGHT": {
                for (int i = 0; i < 4; i++) {
                    moveRight(i);
                    sumRight(i);
                    moveRight(i);
                }
            }
            break;
        }
        if (!Arrays.deepEquals(gameField, previousField)) addRandom();
    }
}