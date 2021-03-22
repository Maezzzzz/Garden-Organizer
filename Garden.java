import java.io.*;
import java.util.*;

public class Garden {
    private static String[][] garden;
    private static Scanner keyboard;
    private static int drops = 0;
    private static int droughts = 0;

    public static void main(String[] args) throws IOException {
        int selection;
        keyboard = new Scanner(System.in);

        System.out.println("Welcome to the garden planner! Select an option below to get started/continue.");
        do {
            System.out.println("1)  Load plant data");
            System.out.println("2)  Create size of garden");
            System.out.println("3)  Add plant");
            System.out.println("4)  Delete plant");
            System.out.println("5)  Move plant");
            System.out.println("6)  Print plants and their locations");
            System.out.println("7)  Save garden");
            System.out.println("8)  Load saved garden ");
            System.out.println("9)  Exit");
            System.out.println("10)  Let it rain!");
            System.out.println("11) Drought!");
            System.out.println("12) View empty cells");
            System.out.println("13) View specific plant");
            System.out.println("14) Print garden data");
            selection = keyboard.nextInt();

            switch (selection) {
                case 1:
                    plantData();
                   break;
                case 2:
                    createGarden();
                    break;
                case 3:
                    addPlant();
                    break;
                case 4:
                    deletePlant();
                    break;
                case 5:
                    movePlant();
                    break;
                case 6:
                    printGarden();
                    break;
                case 7:
                    saveGarden();
                    break;
                case 8:
                    loadGarden();
                    break;
                case 9:
                    System.out.println("Are you sure you would like to exit?(y/n)");
                    break;
                case 10:
                    letItRain();
                    break;
                case 11:
                    drought();
                    break;
                case 12:
                    printOnlyEmpty();
                    break;
                case 13:
                    printOnlySpecificPlant();
                    break;
                case 14:
                    gardenData();
                    break;
                default:
                    System.out.println("Please enter a valid selection.");
                    break;
            }
        }while(selection !=  9);
    }

    private static void plantData() {
        System.out.println("             Plant                Abbreviation");
        System.out.println("Autumn Brilliance Serviceberry:         A");
        System.out.println("             Flowering Dogwood:         F");
        System.out.println("               Purple Milkweed:         P");
        System.out.println("     Moonbeam Whorled Tickseed:         M");
        System.out.println("                Butterfly Weed:         B");
        System.out.println("                    Yellowroot:         Y");
        System.out.println("                 Dusty Zenobia:         D");
        System.out.println("              Twisted Trillium:         T");
        System.out.println("         Southern Red Trillium:         S");
        System.out.println("       Large-Flowered Bellwort:         L");
    }

    private static void createGarden() {

        if (garden != null) {
            System.out.println("Garden already created! to start a new garden restart the program.");
        } else {
            System.out.print("Enter the number of rows you would like:");
            int rows = keyboard.nextInt();

            System.out.print("Enter the number of columns you would like in each row:");
            int columns = keyboard.nextInt();
            garden = new String[rows][columns];

            System.out.println("Garden created!");

            for (int i = 0; i < getRowLength(); i++) {
                for (int j = 0; j < getColumnLength(); j++) {
                    System.out.print("[-]");
                }
                System.out.print("\n");
            }
        }
    }

    private static void addPlant() {
        int plantRow,
            plantColumn;
        String plant;

        if(garden == null) {
            System.out.println("You can not add a plant without first creating a garden!");
            createGarden();
            addPlant();
        }else {
            System.out.print("Select the row you would like to add the plant (1 - " + getRowLength() + "): ");
            plantRow = keyboard.nextInt();

            System.out.print("Select the column you would like to add the plant (1 - " + getColumnLength() + "): ");
            plantColumn = keyboard.nextInt();

            if(garden[plantRow - 1][plantColumn - 1] != null) {
                System.out.println("There is already a plant in this location!");
                printGarden();
            }else {
                plantData();
                System.out.print("Enter what pant you would like to add: ");
                plant = keyboard.next().toUpperCase();

                garden[plantRow - 1][plantColumn - 1] = plant;
                printGarden();
            }
        }
    }

    private static void deletePlant() {
        int deleteRow,
            deleteColumn;

        if(garden == null) {
            System.out.println("You cannot delete a plant if you have not made a garden yet!");
        }else {
            printGarden();
            System.out.print("Select the row you would like to delete the plant (1 - " + getRowLength() + "): ");
            deleteRow = keyboard.nextInt();

            System.out.print("Select the column you would like to delete the plant (1 - " + getColumnLength() + "): ");
            deleteColumn = keyboard.nextInt();

            if (garden[deleteRow - 1][deleteColumn - 1] == null) {
                System.out.println("No plant round in this location!");
                printGarden();
            }else {
                garden[deleteRow - 1][deleteColumn - 1] = null;
                printGarden();
            }
        }
    }

    private static void movePlant() {
        String temp;
        int openSpaces = 0;
        int moveRow,
                moveColumn,
                newMoveRow,
                newMoveColumn;

        for (int i = 0; i < getRowLength(); i++) {
            for (int j = 0; j < getColumnLength(); j++) {
                if (garden[i][j] == null) {
                    openSpaces++;
                }
            }
        }

        if (openSpaces == 0) {
            System.out.println("There are no empty spaces!");
        } else {
            printGarden();
            System.out.print("Select the row of the plant you would like to move (1 - " + getRowLength() + "): ");
            moveRow = keyboard.nextInt();

            System.out.print("Select the column of the plant you would like to move (1 - " + getColumnLength() + "): ");
            moveColumn = keyboard.nextInt();

            System.out.print("Select the row where you would like to move this plant to (1 - " + getRowLength() + "): ");
            newMoveRow = keyboard.nextInt();

            System.out.print("Select the column where you would like to move this plant to (1 - " + getColumnLength() + "): ");
            newMoveColumn = keyboard.nextInt();

            if (garden[newMoveRow - 1][newMoveColumn - 1] != null) {
                System.out.println("This space is currently occupied!");
                movePlant();
            } else {
                temp = garden[moveRow - 1][moveColumn - 1];
                garden[moveRow - 1][moveColumn - 1] = null;
                garden[newMoveRow - 1][newMoveColumn - 1] = temp;
                System.out.println("plant moved!");
            }
            printGarden();
        }
    }

    private static void saveGarden() {
        BufferedWriter bw = null;

        try {
            StringBuilder gardenFile = new StringBuilder();
            String name;

            gardenFile.append(getRowLength()).append("x").append(getColumnLength()).append("\n");
            for(int i = 0; i < getRowLength(); i++) {
                for(int j = 0; j < getColumnLength(); j++) {
                    if(garden[i][j] == null) {
                        gardenFile.append("-");
                    }else {
                        gardenFile.append(garden[i][j]);
                    }
                }
                gardenFile.append("\n");
            }
            System.out.print("Enter file name: ");
            name = keyboard.next();

            File file = new File("C:/Users/manny/Desktop/" + name + ".txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            bw.write(gardenFile.toString());
            System.out.println("File written Successfully");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally
        {
            try{
                if(bw!=null)
                    bw.close();
            }catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter"+ex);
            }
        }
    }

    private static void loadGarden() throws IOException {
        String name;
        String[] lines;
        int numLines = -1;
        int temp = 0;
        garden = null;

        System.out.print("Enter file name(include .txt): ");
        name = keyboard.next();
        File file = new File("C:/Users/manny/Desktop/" + name);

        // create a Buffered Reader object instance with a FileReader
        BufferedReader br = new BufferedReader(new FileReader(file));

        // read the first line from the text file
        String fileRead = br.readLine();

        int i = 0;
        int rows;
        int columns = 0;

        // loop until all lines are read
        while (fileRead != null)
        {

            // use string.split to load a string array with the values from each line of
            // the file, using a comma as the delimiter
            String[] tokenize = fileRead.split("");

            if(i == 0) {
                rows = Integer.parseInt(tokenize[0]);
                columns = Integer.parseInt(tokenize[2]);
                garden = new String[rows][columns];
            } else {
                for(int j = 0; j < columns; j++) {
                    if(tokenize[j].equals("-")) {
                        garden[i - 1][j] = null;
                    }else {
                        garden[i - 1][j] = tokenize[j];
                    }
                }
            }
            // read next line before looping
            // if end of file reached
            fileRead = br.readLine();
            i++;
        }

        // close file stream
        br.close();
    }

    private static void letItRain() {
        Random random = new Random();
        int upperboundRows = getRowLength();
        int upperboundColumns = getColumnLength();
        int emptySpaces = 0;

        for(int i = 0; i < getRowLength(); i++) {
            for(int j = 0; j < getColumnLength(); j++) {
                if(garden[i][j] == null) {
                    emptySpaces++;
                }
            }
        }
        if(drops == 5) {
            System.out.println("Complete!");
        }else if(emptySpaces == 0) {
            System.out.println("No more empty spaces!");
        }else {
            int ranDropRow = random.nextInt(upperboundRows);
            int ranDropCol = random.nextInt(upperboundColumns);

            if(garden[ranDropRow][ranDropCol] == null) {
                letItRain();
            }else {
                String temp = garden[ranDropRow][ranDropCol];
                int ranDupRow = random.nextInt(upperboundRows);
                int ranDupCol = random.nextInt(upperboundColumns);

                if(garden[ranDupRow][ranDupCol] != null) {
                    letItRain();
                }else {
                    garden[ranDupRow][ranDupCol] = temp;
                    printGarden();
                    System.out.println();
                    drops++;
                    letItRain();
                }
            }
        }
    }

    private static void drought() {
        Random random = new Random();
        int upperboundRows = getRowLength();
        int upperboundColumns = getColumnLength();
        int fullSpaces = 0;

        for(int i = 0; i < getRowLength(); i++) {
            for(int j = 0; j < getColumnLength(); j++) {
                if(garden[i][j] != null) {
                    fullSpaces++;
                }
            }
        }
        if(fullSpaces == 0) {
            System.out.println("No more plants are left!");
        }else if(droughts == 5) {
            System.out.println("Complete!");
        }else {
            int ranDroughtRow = random.nextInt(upperboundRows);
            int ranDroughtCol = random.nextInt(upperboundColumns);

            if(garden[ranDroughtRow][ranDroughtCol] == null) {
                drought();
            }else {
                garden[ranDroughtRow][ranDroughtCol] = null;
                printGarden();
                System.out.println();
                droughts++;
                drought();
            }
        }
    }

    private static void gardenData() {
        int numA = 0;
        int numF = 0;
        int numP = 0;
        int numM = 0;
        int numB = 0;
        int numY = 0;
        int numD = 0;
        int numT = 0;
        int numS = 0;
        int numL = 0;
        int numEmpty = 0;

        for(int i = 0; i < getRowLength(); i++) {
            for(int j = 0; j < getColumnLength(); j++) {
                if(garden[i][j] == null) {
                    numEmpty++;
                }else if(garden[i][j].equals("A"))  {
                    numA++;
                }else if(garden[i][j].equals("F"))  {
                    numF++;
                }else if(garden[i][j].equals("P"))  {
                    numP++;
                }else if(garden[i][j].equals("M"))  {
                    numM++;
                }else if(garden[i][j].equals("B"))  {
                    numB++;
                }else if(garden[i][j].equals("Y"))  {
                    numY++;
                }else if(garden[i][j].equals("D"))  {
                    numD++;
                }else if(garden[i][j].equals("T"))  {
                    numT++;
                }else if(garden[i][j].equals("S"))  {
                    numS++;
                }else if(garden[i][j].equals("L"))  {
                    numL++;
                }
            }
        }

        System.out.println("Plant     Amount");
        System.out.println("Empty        " + numEmpty);
        System.out.println("  A          " + numA);
        System.out.println("  F          " + numF);
        System.out.println("  P          " + numP);
        System.out.println("  M          " + numM);
        System.out.println("  B          " + numB);
        System.out.println("  Y          " + numY);
        System.out.println("  D          " + numD);
        System.out.println("  T          " + numT);
        System.out.println("  S          " + numS);
        System.out.println("  L          " + numL);
    }

    private static int getRowLength() {
        return garden.length;
    }

    private static int getColumnLength() {
        return garden[0].length;
    }

    private static void printGarden() {
        for(int i = 0; i < getRowLength(); i++) {
            for(int j = 0; j < getColumnLength(); j++) {
                if(garden[i][j] == null) {
                   System.out.print("[-]") ;
                }else {
                    System.out.print("[" + garden[i][j] + "]");
                }
            }
            System.out.print("\n");
        }

    }

    private static void printOnlySpecificPlant() {
        keyboard = new Scanner(System.in);
        System.out.print("Enter the letter for the plant you are looking for: ");
        String selection;
        StringBuilder specificPlant = new StringBuilder();

        selection = keyboard.next().toUpperCase();

        for(int i = 0; i < getRowLength(); i++) {
            for(int j = 0; j < getColumnLength(); j++) {
                if(garden[i][j] != null) {
                    if(garden[i][j].equals(selection)) {
                        specificPlant.append("[").append(garden[i][j]).append("]");
                    }
                }else {
                    specificPlant.append("   ");
                }
            }
            specificPlant.append("\n");
        }
        System.out.println(specificPlant);
    }

    private static void printOnlyEmpty() {
        StringBuilder emptyGrid = new StringBuilder();
        if(garden == null) {
            System.out.println("Garden must be created first!");
        }else {
            for (int i = 0; i < getRowLength(); i++) {
                for (int j = 0; j < getColumnLength(); j++) {
                    if (garden[i][j] == null) {
                        emptyGrid.append("[-]");
                    } else {
                        emptyGrid.append("   ");
                    }
                }
                emptyGrid.append("\n");
            }
            System.out.println(emptyGrid);
        }
    }
}