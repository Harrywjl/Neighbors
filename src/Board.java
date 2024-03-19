class Board {
    private int[][] board;

    public Board() {
        board = new int[5][5];
    }

    public int[][] getBoard() {
        return board;
    }

    public int getBoardSize() {
        return board.length * board[0].length;
    }

    public void setPos(int x, int y, int value) {
        board[x][y] = value;
    }

    public boolean isValid(int x, int y) {
        if (x < 0 || x > board.length || y < 0 || y > board[0].length) {
            return false;
        }
        return board[x][y] == 0;
    }

    public int calcScore() {
        int score = 0;
        int consecutives = 0;
        int last = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != last) {
                    if (consecutives > 1) {
                        score += consecutives * last;
                    }
                    consecutives = 0;
                } else {
                    consecutives++;
                }
                last = board[i][j];
            }
            consecutives = 0;
            last = 0;
        }
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] != last) {
                    if (consecutives > 1) {
                        score += consecutives * last;
                    }
                    consecutives = 0;
                } else {
                    consecutives++;
                }
                last = board[j][i];
            }
            consecutives = 0;
            last = 0;
        }
        return score;
    }

}