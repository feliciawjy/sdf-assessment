package sg.edu.nus.iss.sdf;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    // class level string array
    List<String> record = new ArrayList<>();
    Map<Integer, List<String>> stackMap = new HashMap<>();

    public static void main(String[] args) throws Exception {

        // Run Your Code here
        // args

        boolean stop = false;
        Console cons = System.console();
        FileService fs = new FileService();

        //tests 
        //fs.writeAsCSV("hello,test", "src/Rush3.csv");

        while (!stop) {
            printHeader();
            String input = cons.readLine("Enter your selection >");

            switch (input) {
                case "1":
                    System.out.println("Selection 1");
                    break;
                case "2":
                    System.out.println("Selection 2");
                    break;
                case "3":
                    System.out.println("Selection 3");
                    break;
                case "4":
                    System.out.println("Selection 4");
                    break;

                case "q":
                    stop = true;
                    break;

                default:
                    System.out.println("Please enter the correct selection.");
                    break;
            }

        }

        printExitMessage();

    }

    public static void clearConsole() throws IOException {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Task 1
    public static void pressAnyKeyToContinue() {
        // your code here
    }

    // Task 1
    public static void printHeader() {
        // Task 1 - your code here
        System.out.println("Welcome to Pokemon Gaole Legend 4 Rush 2");
        System.out.println();
        System.out.println("(1) View the list of Pokemon in the selected stack");
        System.out.println("(2) View unique list of Pokemon in the selected stack");
        System.out.println("(3) Fine next 5 stars Pokemon occurence");
        System.out.println("(4) Create new Pokemon stack and save (append) to csv file");
        System.out.println("(q) to exit the program");

    }

    // Task 1
    public static void printExitMessage() {
        // Task 1 - your code here
        System.out.println("Thank you for using the program...");
        System.out.println("Hope to see you again soon...");
    }

    // Task 1
    public static void savePokemonStack(String pokemonStack, String filename) {

        // Task 1 - your code here
    }

    // Task 2
    public static void printUniquePokemonStack(Integer stack) {
        // Task 2 - your code here
    }

    // Task 2
    public static void printNext5StarsPokemon(String enteredPokemon) {
        // Task 2 - your code here

    }

    // Task 2
    public static void printPokemonCardCount() {
        // Task 2 - your code here
    }

}
