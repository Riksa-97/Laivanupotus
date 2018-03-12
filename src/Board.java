public class Board {
    private char[][] board;


    public Board() {
        board = new char[10][10];
    }

    //returns the value of the tile, used in Opponent class
    public char getTile(int x, int y){
        try {
            return board[x][y];
        } catch(IndexOutOfBoundsException e) {
            return ' ';
        }
    }


    //Using a-j in x-axis, player still inserts 1-10
    public void printBoard() {
        System.out.println();
        System.out.println("    1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10");
        System.out.println("  -----------------------------------------");
        for (int i = 0; i < 10; i++) {
            if (i < 9) {
                System.out.print(i + 1 + " | ");
            } else {
                System.out.print(i + 1 + "| ");
            }
            for (int j = 0; j < 10; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("  -----------------------------------------");
        }
    }
    public void printShootBoard(Board Pboard){
        System.out.println();
        System.out.print("    1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10               ");
        System.out.println("      1 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10");
        System.out.print("  -----------------------------------------               ");
        System.out.println("   -----------------------------------------");
        //Player
        for (int i = 0; i < 10; i++) {
            if (i < 9) {
                System.out.print(i + 1 + " | ");
            } else {
                System.out.print(i + 1 + "| ");
            }
            for (int j = 0; j < 10; j++) {
                System.out.print(Pboard.getTile(i,j) + " | ");
            }
            System.out.print("               ");
            //Opponent
            if (i < 9) {
                System.out.print(i + 1 + " | ");
            } else {
                System.out.print(i + 1 + "| ");
            }
            for (int j = 0; j < 10; j++) {
                if(board[i][j] == '-'){
                    System.out.print(board[i][j] + " | ");
                }else if(board[i][j] == 'O'){
                    System.out.print(board[i][j] + " | ");
                }else if(board[i][j] == 'M'){
                    System.out.print(board[i][j] + " | ");
                }else if(board[i][j] == 'X'){
                    System.out.print(board[i][j] + " | ");
                }else {
                    System.out.print('-' + " | ");
                }
            }
            System.out.println();
            System.out.print("  -----------------------------------------               ");
            System.out.println("   -----------------------------------------");
        }
    }



    public void formatBoard() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = '-';
            }
        }
    }

    //checks if the selected values form a valid move
    public boolean fitShip(Ship ship, int x, int y, int orientation){
        if (orientation == 1) {                                           //vertical
            for (int i = 0; i < ship.getLength(); i++) {
                try {
                    if (board[y+i-1][x-1] != '-') {
                        return false;
                    }
                } catch (IndexOutOfBoundsException e) {
                    return false;
                }
            }
        }else {                                                           //horizontal
            for (int i = 0; i < ship.getLength(); i++) {
                try {
                    if (board[y-1][x+i-1] != '-') {
                        return false;
                    }
                } catch (IndexOutOfBoundsException e) {
                    return false;
                }
            }
        }
        return true;
    }



    public void placeShip(Ship ship, int x, int y, int orientation) {
        if (orientation == 1){                                              //vertical
            for (int i = 0; i < ship.getLength(); i++) {
                board[y+i-1][x-1] = ship.getSign();
            }
        } else {                                                           //horizontal
            for (int i = 0; i < ship.getLength(); i++) {
                board[y-1][x+i-1] = ship.getSign();
            }
        }
    }

    public void shoot(int x, int y){
        if(x <= 10 && y <= 10){
            if (board[y - 1][x - 1] == '-') {
                board[y - 1][x - 1] = 'M';
            } else if (board[y - 1][x - 1] == 'M' || board[y - 1][x - 1] == 'O' || board[y - 1][x - 1] == 'X') {
                System.out.println("You have already shot there!");
            } else {
                char a = board[y - 1][x - 1];
                board[y - 1][x - 1] = 'O';
                if (upposiko(a)) {
                    board[y - 1][x - 1] = 'X';
                }
            }
        }
        else{
            System.out.println("x and y must be between 0-10!");
        }


    }
    public boolean upposiko(char a){
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(board[i][j] == a){
                    return false;
                }
            }
        }
        return true;
        }

        public boolean gameWin(){
        //char[] accepted = {'-', 'M', 'X'};
            int laskuri=0;
            for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                //for(char a: accepted){
                    //if (board[i][j] != a){
                    if(board[i][j]=='X') {
                        laskuri++;
                        //}
                }
            }
        }
        if(laskuri >= 5){
            return true;
        }
        else{
                return false;
        }

    }
    }