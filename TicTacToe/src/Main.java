import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println((int) 'a');
        System.out.println("Welcome to tic tac toe!");
        System.out.println("The format to place a tile is [0-0][0-0]. \nFor example, [0][0] is top left. The first input" +
                " is the row, and the second input is the column.");
        System.out.println("This is a two player game, X will go first, then O will go after.");
        char[][] ticTacToe = createEmptyBoard();
        playingTicTacToe(ticTacToe);
    }

    public static void printingArray(char[][] array) {
        for (char[] row : array) {
            System.out.print("| ");
            for (char row2 : row) {
                System.out.print(row2 + " | ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static char[][] createEmptyBoard() {
        return new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    }

    public static void playingTicTacToe(char[][] array) {
        int counter = 0;
        boolean gameWon = false;
        while (!gameWon) {
            printingArray(array);
            ArrayList<Integer> arrayList = placingPos();
            int row = arrayList.get(0);
            int column = arrayList.get(1);

            while (array[row][column] != ' ') {
                System.out.println("Spot already occupied. Please input new values. \n");
                arrayList = placingPos();
                row = arrayList.get(0);
                column = arrayList.get(1);
            }
            char player = currentPlayer(counter);
            array[row][column] = player;
            counter++;

            if (didWin(array)) {
                gameWon = true;
                printingArray(array);
                System.out.println(player + " has won! \nWould you like to play again? Type y if yes, n if no.");
                Scanner scanner = new Scanner(System.in);
                String response;
                do{
                    response = scanner.next();
                }while(!response.equals("y") && !response.equals("Y") && !response.equals("n") && !response.equals("N"));

                if(response.equals("y") || response.equals("Y")){
                    gameWon = false;
                    counter =0;
                    array = createEmptyBoard();
                }
            }

        }

    }

    public static char currentPlayer(int counter) {
        //determines which player's turn it is (X or O)
        char player = ' ';
        if (counter % 2 == 0) {
            player = 'X';
        } else if (counter % 2 == 1) {
            player = 'O';
        }
        return player;
    }
    public static ArrayList<Integer> placingPos() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input a row, then column, separated by a space. (ex: 1 0)");
        String[] parts;
        int count = 0;
        do {
            if(count > 0){
                System.out.println("Invalid input, please input again.");
            }
            String answer = scanner.nextLine();
            parts = answer.split(" ");
            count++;
        } while (parts.length != 2 || Integer.parseInt(parts[0]) > 2 || Integer.parseInt(parts[0]) < 0 || Integer.parseInt(parts[1]) > 2 || Integer.parseInt(parts[1]) < 0 );
        ArrayList<Integer> array = new ArrayList<>();
        array.add(Integer.valueOf(parts[0]));
        array.add(Integer.valueOf(parts[1]));

        return array;
    }

    public static boolean didWin(char[][] array) {
        for (int i = 0; i < 3; i++) {
            if (array[i][0] == array[i][1] && array[1][1] == array[i][2] && array[i][1] != ' ') {
                return true;
            }
            if (array[0][i] == array[1][i] && array[1][i] == array[2][i] && array[1][i] != ' ') {
                return true;
            }
        }
        if (array[0][0] == array[1][1] && array[1][1] == array[2][2] && array[1][1] != ' ') {
            return true;
        }
        if (array[0][2] == array[1][1] && array[1][1] == array[2][0] && array[1][1] != ' ') {
            return true;
        }
        return false;
    }
}