/*
Project Description:
User enters 3 different pets and treat (name, type of pet, location)
User is prompted to enter commands to lead pet to treat
The winner is whichever pet reaches the treat first! 
 */
import java.util.Scanner;
public class PetTreatGame {
    public static void main(String [] args) {
        final int WIDTH = 10;
        char[][] grid = new char[WIDTH][WIDTH];

        for (int i = 0; i < WIDTH; i++)
            for (int j = 0; j < WIDTH; j++)
                grid[i][j] = '.';

        Pet myTreat = new Pet();
        myTreat.input(grid);
        myTreat.output();

        Pet [] myPets = new Pet[3];
        for (int i=0; i<myPets.length; i++) {
            myPets[i] = new Pet();
            myPets[i].input(grid);
            myPets[i].output();
        }

        displayGrid(grid);
        Scanner in = new Scanner(System.in);
        int move = 1;
        while (move != 0) {
            for (int i = 0; i < myPets.length; i++) {
                System.out.print("Enter move (1: j--, 2: i++, 3: i--, 4: j++) for pet " + i + ": ");
                move = in.nextInt();
                if (move == 0)
                    break;
                myPets[i].move(move, grid);
            }
            displayGrid(grid);
            boolean flag = false;
            for (int i=0; i<myPets.length; i++) {
                flag = myPets[i].checkLocation(myTreat);
                if (flag) {
                    break;
                }
            }
            if (flag)
                break;
        }
    }

    public static void displayGrid(char g[][]) {
        System.out.println("Current grid:");
        System.out.println("0123456789");
        for (int i=0; i<g.length; i++) {
            for (int j = 0; j < g[i].length; j++) {
                System.out.print(g[i][j]);
            }
            System.out.println();
        }
    }
}

class Pet {
    private String name;
    private String type;
    private char symbol;
    private int i, j;
    private int width;
    private int numMoves;

    Pet() {
        name = "";
        type = "";
        symbol = '?';
        i = 0;
        j = 0;
        width = 10;
        numMoves = 0;
    }
    Pet(String newName, String newType, char newSym, int newI, int newJ, int w) {
        name = newName;
        type = newType;
        symbol = newSym;
        width = w;
        setIJ(newI, newJ);
    }

    Pet(String newName, String newType, char newSym, int w) {
        name = newName;
        type = newType;
        symbol = newSym;
        i = 5;
        j = 3;
        width = w;
    }

    void input(char [][] g) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter type of pet: ");
        type = in.nextLine();
        if (type.equals("puppy"))
            symbol = 'P';
        else if (type.equals("treat"))
            symbol = 'T';
        if (!type.equals("treat")) {
            System.out.print("Enter name of pet: ");
            name = in.nextLine();
        }
        System.out.print("Enter (i, j) location of pet: ");
        int newI = in.nextInt();
        int newJ = in.nextInt();
        setIJ(newI, newJ);
        g[i][j] = symbol;

    }
    void output() {
        if (type.equals("treat"))
            System.out.println("The treat is at " + i + " " + j);
        else
            System.out.println(name + " the " + type + " is at "
                    + i + " " + j);
    }

    void setIJ(int newI, int newJ) {
        if (newI < 0) {
            System.out.println("Invalid i; " + name + "'s i set to 0");
            i = 0;
        } else if (newI >= width) {
            System.out.println("Invalid i; " + name + "'s i set to " + (width-1));
            i = width - 1;
        } else
            i = newI;
        if (newJ < 0) {
            System.out.println("Invalid j; " + name + "'s j set to 0");
            j = 0;
        } else if (newJ >= width) {
            System.out.println("Invalid j; " + name + "'s j set to " + (width-1));
            j = width - 1;
        } else
            j = newJ;

    }

    int [] getIJ() {
        int [] res = new int[2];
        res[0] = i;
        res[1] = j;
        return res;
    }

    void move(int move, char [][] g) {
        if (move == 1)
            setIJ(i, j-1);
        else if (move == 2)
            setIJ(i+1, j);
        else if (move == 3)
            setIJ(i-1, j);
        else if (move == 4)
            setIJ(i, j+1);
        numMoves++;
        g[i][j] = symbol;
    }

    String getType() {
        return type;
    }
    boolean checkLocation(Pet otherPet) {
        int [] otherIJ = otherPet.getIJ();
        if (i == otherIJ[0] && j == otherIJ[1]) {
            System.out.println(name + " found the " + otherPet.getType() + " in "
                    + numMoves + " moves!");
            return true;
        }
        else
            return false;
    }
}
