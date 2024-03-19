import java.util.ArrayList;
import java.util.Scanner;
public class Game {
    private ArrayList<Board> game;
    private int players;

    public Game(int players) {
        this.players = players;
        game = new ArrayList<Board>();
        for (int i = 0; i < players; i++) {
            game.add(new Board());
        }
    }

    public int roll() {
        return (int) (Math.random() * 10) + 1;
    }

    public void printAllBoards() {
        for (int i = 1; i <= game.size(); i++) {
            System.out.println("Player " + i + "'s Board");
            int[][] board = game.get(i).getBoard();
            for (int[] row : board) {
                System.out.format("%15s%15s%15s%n", (Object) row);
            }
            System.out.println();
        }
    }

    //Different return type? *Unfinished*
    public void takeInput(int player) {
        Scanner scan = new Scanner(System.in);
        boolean rowValid = false;
        int row = -1;
        while (!rowValid) {
            System.out.println("Player " + player + ", select the row you want place the number in(0-" + (game.get(player - 1).getBoard().length - 1) + "): ");
            if (row < 0 || row > (game.get(player - 1).getBoard().length - 1)) {
                rowValid = true;
            }
        }

    }

    public void playerWon() {
        ArrayList<Integer> scores = new ArrayList<Integer>();
        for (int i = 0; i < game.size(); i++) {
            scores.add(game.get(i).calcScore());
        }
        ArrayList<Integer> winners = new ArrayList<Integer>();
        int winnerScore = -1;
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i) > winnerScore) {
                winnerScore = scores.get(i);
                winners.clear();
                winners.set(1, i + 1);
            } else if (scores.get(i) == winnerScore) {
                winners.add(i + 1);
            }
        }
        String endingStatement = "";
        if (winners.size() > 1) {
            endingStatement += "Players ";
        } else {
            endingStatement += "Player " + winners.get(0) + " won the game with a score of " + scores.get(winners.get(0) - 1) + " points!";
        }
        System.out.println(endingStatement);
    }

    public void play() {
        for (int i = 0; i < game.get(0).getBoardSize(); i++) {
            printAllBoards();
            int randomNum = roll();
            System.out.println("The dice rolled a " + randomNum);
            for (int p = 1; p <= players; p++) {
                takeInput(i);

            }
            clearScreen();
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
