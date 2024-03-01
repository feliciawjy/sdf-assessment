package sg.edu.nus.iss.sdf;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    // class level string array
    List<String> record = new ArrayList<>();
    static Map<Integer, List<String>> stackMap = new HashMap<>();

    public static void main(String[] args) throws Exception {

        // Run Your Code here
        boolean stop = false;
        Console cons = System.console();
        FileService fs = new FileService();

        // args
        if (args.length != 0) {
            String fileName = args[0];
            List<String> pokemons = fs.ReadCSV(fileName);
            // for (int i = 0; i < pokemons.size(); i++) {
            // System.out.printf("id%s: %s\n", String.valueOf(i), pokemons.get(i));
            // if ((i != 0) && (i%50) == 0 ) {
            // stackMap.put(((int) 50/i), pokemons.subList(i-50, i-1));
            // }
            // }
            int partitionSize = 50;
            List<List<String>> partitions = new ArrayList<>();

            for (int i = 0; i < pokemons.size(); i += partitionSize) {
                partitions.add(pokemons.subList(i, Math.min(i + partitionSize, pokemons.size())));
            }

            for (int i = 0; i < partitions.size(); i++) {
                stackMap.put(i + 1, partitions.get(i));
            }

            System.out.println(stackMap.toString());

        } else {
            throw new IllegalArgumentException("Pokemon stack file required.");
        }

        // tests

        // fs.writeAsCSV("hello,test", "src/Rush3.csv");

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
                    String pokemonStack = cons.readLine("Create a new Pokemon stack and save to a new file >\n");
                    String filename = cons.readLine("Enter filename to save (e.g. path/filename.csv) >\n");
                    savePokemonStack(pokemonStack, filename);

                    break;
                case "4":

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
        // figure out how to continue
    }

    // Task 1
    public static void printHeader() {
        // Task 1 - your code here
        System.out.println("Welcome to Pokemon Gaole Legend 4 Rush 2");
        System.out.println();
        System.out.println("(1) View the unique list of Pokemon in the selected stack");
        System.out.println("(2) Find next 5 stars Pokemon occurence");
        System.out.println("(3) Create new Pokemon stack and save (append) to csv file");
        System.out.println("(4) Print distinct Pokemon and cards count");
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
        // file io
        FileService fs = new FileService();
        fs.writeAsCSV(pokemonStack, filename);

        // map
        stackMap.put(stackMap.size(), new ArrayList<String>(Arrays.asList(pokemonStack.split(","))));
        System.out.println(stackMap.toString());
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
