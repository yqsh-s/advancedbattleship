import java.util.Scanner;

class Main {
  public static void main(String[] args) {

    // Game setup

    System.out.println("\nWelcome to Battleship! Let's start by setting up the game.");
    Scanner in = new Scanner(System.in);
    System.out.print("How tall should the board be? ");
    int N = in.nextInt();
    System.out.print("How wide should the board be? ");
    int M = in.nextInt();
    int totalSquares = 0;

    int[] numShips = new int[3];
    System.out.print("How many two-unit ships should each player have? ");
    numShips[0] = in.nextInt();
    System.out.print("How many three-unit ships should each player have? ");
    numShips[1] = in.nextInt();
    System.out.print("How many four-unit ships should each player have? ");
    numShips[2] = in.nextInt();
    totalSquares = 2*numShips[0] + 3*numShips[1] + 4*numShips[2];


    // Player setup

    System.out.println("Okay, it's time for Player 1 to set up their ships! You will be asked to type in coordinates for each of your ships.");
    boolean[][] ships1 = setupGrid(N, M, numShips);
    System.out.println("Okay, it's time for Player 2 to set up their ships! You will be asked to type in coordinates for each of your ships.");
    boolean[][] ships2 = setupGrid(N, M, numShips);


    // Gameplay

    boolean[][] guesses1 = new boolean[N][M];
    boolean[][] guesses2 = new boolean[N][M];
    int numHits1 = 0;
    int numHits2 = 0;

    System.out.println("The game begins!");
    while (true) {
      System.out.println("Player 1, here's your board:");
      printGrid(guesses1);
      
      System.out.print("Player 1, pick a row: ");
      int row = in.nextInt();
      System.out.print("Player 1, pick a column: ");
      int col = in.nextInt();
      
      if (row < 0 || row >= N || col < 0 || col >= M) {
          System.out.println("These coordinates are out of bounds!");
      } else if (ships2[row][col] && !guesses1[row][col]) {
        System.out.println("It\'s a hit!");
        guesses1[row][col] = true;
        numHits1++;
      } else if (guesses1[row][col]) {
        System.out.println("You already found a ship here!");
      } else {
        System.out.println("It\'s a miss!");
      }

      if (numHits1 == totalSquares) {
        System.out.println("Player 1 sank all the ships! Player 1 wins!");
        break;
      }

      System.out.println("Player 2, here's your board:");
      printGrid(guesses2);
      
      System.out.print("Player 2, pick a row: ");
      row = in.nextInt();
      System.out.print("Player 2, pick a column: ");
      col = in.nextInt();
      
      if (row < 0 || row >= N || col < 0 || col >= M) {
          System.out.println("These coordinates are out of bounds!");
      } else if (ships1[row][col] && !guesses2[row][col]) {
        System.out.println("It\'s a hit!");
        guesses2[row][col] = true;
        numHits2++;
      } else if (guesses2[row][col]) {
        System.out.println("You already found a ship here!");
      } else {
        System.out.println("It\'s a miss!");
      }

      if (numHits2 == totalSquares) {
        System.out.println("Player 2 sank all the ships! Player 2 wins!");
        break;
      }
    }
  }

  public static boolean[][] setupGrid(int N, int M, int[] numShips) {
    boolean[][] grid = new boolean[N][M];
    Scanner in = new Scanner(System.in);

    for (int i = 0; i < numShips.length; i++) {
      System.out.println("Now entering coordinates for your " + numShips[i] + " " + (i+2) + "-unit ships.");
      int coordsRemaining = numShips[i] * (i+2);
      while (coordsRemaining > 0) {
        System.out.print("Pick a row: ");
        int row = in.nextInt();
        System.out.print("Pick a column: ");
        int col = in.nextInt();
        if (row < 0 || row >= N || col < 0 || col >= M) {
          System.out.println("These coordinates are out of bounds!");
        } else if (grid[row][col]) {
          System.out.println("You've already placed a ship here!");
        } else {
          grid[row][col] = true;
          coordsRemaining--;
        }
      }
      System.out.println("You've completed placing your " + (i+2) + "-unit ships. Here is what your board currently looks like:");
      printGrid(grid);
    }

    return grid;
  }

  public static void printGrid(boolean[][] grid) {

    // print col numbers

    System.out.print("  ");
    for (int i = 0; i < grid[0].length; i++) {
      System.out.print(" " + i + " ");
    }
    System.out.println();


    // print each row, with a number at the beginning
    
    for (int i = 0; i < grid.length; i++) {
      System.out.print(i + " ");
      for (int j = 0; j < grid[0].length; j++){
        if (grid[i][j]) {
          System.out.print(" x ");
        } else {
          System.out.print(" - ");
        }
      }
      System.out.println();
    }
  }
}
