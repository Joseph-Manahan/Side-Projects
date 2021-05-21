import java.util.Scanner;
public class CellularAutomation {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the 1D cellular automation simulation!");
        System.out.println("Enter number of cells (<=80): ");
        int C = in.nextInt(); // number of cells
        int[] cells = new int[C];
        System.out.println("Enter number of time steps: ");
        int N = in.nextInt(); // number of time steps
        System.out.println("Enter index of occupied cells (negative index to end): ");
        int index = in.nextInt();
        while (index>=0) {
            cells[index]=1;
            index=in.nextInt();
        }
        // for loop to make header
        int count=0;
        for (int num=0; num<cells.length; num++) {
            System.out.print(num);
            if (num==(N-1)||num==9)
                num=-1;
            if (count==(C-1))
                break;
            count++;
        }
        // for 1 to N time steps:
        for (int i=0; i<=N; i++) {
            System.out.println(" ");
            displayCells(cells); // displays the new cells
            cells=updateCells(cells); // calculates new cell array using rules
        }
    }

    // data[] is a list that contains integers
    // displayCells takes the value of a cell and prints either a # or blank space
    public static void displayCells(int data[]) {
        for (int i = 1; i < data.length; i++) {
            if (data[i] == 1) {
                System.out.print("#");
            }
            if (data[i] == 0) {
                System.out.print(" ");
            }
        }
    }

    // data[] is a list that contains integers
    // updateCells uses the rules to assign a value to the new cell,
    //                        and returns the result (an integer)
    public static int[] updateCells(int data[]) {
        int[] results = new int[data.length];
        for (int i = 1; i < data.length-1; i++) {
            if (data[i]==1 && data[i-1]==1 && data[i+1]==1) {
                results[i]=0;
            }
            if (data[i]==1 && data[i-1]==1 && data[i+1]==0) {
                results[i]=1;
            }
            if (data[i]==0 && data[i-1]==1 && data[i+1]==1) {
                results[i]=1;
            }
            if (data[i]==0 && data[i-1]==1 && data[i+1]==0) {
                results[i]=1;
            }
            if (data[i]==1 && data[i-1]==0 && data[i+1]==1) {
                results[i]=0;
            }
            if (data[i]==1 && data[i-1]==0 && data[i+1]==0) {
                results[i]=1;
            }
            if (data[i]==0 && data[i-1]==0 && data[i+1]==1) {
                results[i]=1;
            }
            if (data[i]==0 && data[i-1]==0 && data[i+1]==0) {
                results[i]=0;
            }
        }
        return results;
    }
}

