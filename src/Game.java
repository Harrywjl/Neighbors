import java.util.ArrayList;
import java.util.Arrays;
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
            int[][] board = game.get(i - 1).getBoard();
            for (int[] row : board) {
                System.out.format("%s", (Object) Arrays.toString(row));
                System.out.println();
            }
            System.out.println();
        }
    }

    public void takeInput(int player, int diceRoll) {
        Scanner scan = new Scanner(System.in);
        boolean inputValid = false;
        int row = -1;
        int col = -1;
        System.out.print("Player " + (player + 1) + ", select the position you want place the number in\nUse the format \"(row, column)\" where row is(0-" + (game.get(player).getBoard().length - 1) + ") and column is (0-" + (game.get(player).getBoard()[0].length - 1) + "): ");
        String choice = scan.nextLine();
        if (choice.contains("(") && choice.contains(",") && choice.contains(")")) {
            choice = choice.replace(" ", "");
            row = Integer.parseInt(choice.substring(choice.indexOf("(") + 1, choice.indexOf(",")));
            col = Integer.parseInt(choice.substring(choice.indexOf(",") + 1, choice.indexOf(")")));
            if (game.get(player).isValid(row, col)) {
                inputValid = true;
                game.get(player).setPos(row, col, diceRoll);
            }
        }
        while (!inputValid) {
            System.out.println("Invalid input. Try again");
            System.out.print("Player " + (player + 1) + ", select the position you want place the number in\nUse the format \"(row, column)\" where row is(0-" + (game.get(player).getBoard().length - 1) + ") and column is (0-" + (game.get(player).getBoard()[0].length - 1) + "): ");
            choice = scan.nextLine();
            if (choice.contains("(") && choice.contains(",") && choice.contains(")")) {
                choice = choice.replace(" ", "");
                row = Integer.parseInt(choice.substring(choice.indexOf("(") + 1, choice.indexOf(",")));
                col = Integer.parseInt(choice.substring(choice.indexOf(",") + 1, choice.indexOf(")")));
                if (game.get(player).isValid(row, col)) {
                    inputValid = true;
                    game.get(player).setPos(row, col, diceRoll);
                }
            }
        }
        System.out.println();
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
                takeInput(p - 1, randomNum);
                clearScreen();
                printAllBoards();
            }
            if (i + 1 < game.get(0).getBoardSize()) {
                clearScreen();
            }
        }
        playerWon();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
