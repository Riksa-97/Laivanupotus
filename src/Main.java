import java.util.Scanner;

public class Main {
    public static void main(String[] args){


        //Defining attributes
        Tiedosto.teeTiedosto();
        Board board = new Board();
        Opponent ai = new Opponent();
        Scanner sc = new Scanner(System.in);
        char[] ships = {'C','B','R','S','D'};
        String[] shipNames = {"Carrier - R - 5", "Battleship - B - 4", "Cruiser - R - 3", "Submarine - S - 2", "Destroyer - D - 1"};
        int x;
        int y;
        int orientation;
        int turn=0;
        boolean winner = true;


        //Entering anything else than enter creates InputMismatchException
        //Start screen
        System.out.println("Press enter for new game \n");
        System.out.println("At the start you'll place your ships one by one");
        System.out.println("To place a ship insert x-coord, y-coord and then orientation");
        System.out.println("1 = orientation downwards from the coordination");
        System.out.println("2 = orientation to right from the coordination \n");
        System.out.println("Highscore: "+ Tiedosto.lueEnnatys());
        if(sc.hasNextLine()){
            board.formatBoard();
            board.printBoard();
        }


        //Missing invalid data insert checks

        //Player inserts coordinates and orientation. Board is tested if it's a valid move.
        //If yes, places and prints. If not asks again until valid values are entered
        for(int i = 0; i<5; i++){
            System.out.println(shipNames[i]+ ": choose coordinates(x,y) and orientation");
            x = sc.nextInt();
            y = sc.nextInt();
            orientation = sc.nextInt();
            while(!board.fitShip(new Ship(5-i,ships[i]), x, y, orientation)) {
                System.out.println("The ship won't fit there!");
                x = sc.nextInt();
                y = sc.nextInt();
                orientation = sc.nextInt();
            }
            board.placeShip(new Ship(5-i, ships[i]), x, y, orientation);
            board.printBoard();
        }

        //Printing is just for testing purposes
        ai.placeShips();
        //ai.getOboard().printBoard();

        while(!board.gameWin()){
            System.out.println("Turn "+ ++turn + ": Select coordinates to shoot at");
            x = sc.nextInt();
            y = sc.nextInt();
            ai.getOboard().shoot(x, y);
            ai.shootAt(board);
            ai.getOboard().printShootBoard(board);
            if (ai.getOboard().gameWin()){
                winner = false;
                break;
            }
        }
        if (winner){
            System.out.println("You beat the game!");
        }else{
            System.out.println("You lost to Java rng");
        }



        Tiedosto.uusiEnnatys(Integer.toString(turn));
    }
}