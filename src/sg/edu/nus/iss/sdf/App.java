package sg.edu.nus.iss.sdf;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

            int partitionSize = 50;
            List<List<String>> partitions = new ArrayList<>();

            for (int i = 0; i < pokemons.size(); i += partitionSize) {
                partitions.add(pokemons.subList(i, Math.min(i + partitionSize, pokemons.size())));
            }

            for (int i = 0; i < partitions.size(); i++) {
                stackMap.put(i + 1, partitions.get(i));
            }

            // System.out.println(stackMap.toString());

        } else {
            throw new IllegalArgumentException("Pokemon stack file required.");
        }

        // tests
        // printNext5StarsPokemon("5* Kyurem");
        // fs.writeAsCSV("hello,test", "src/Rush3.csv");

        while (!stop) {
            printHeader();
            String input = cons.readLine("Enter your selection >");

            switch (input) {
                case "1":
                    boolean pass = false;
                    while (!pass) {
                        String stackInput = cons.readLine("Display the list of unique Pokemon in stack (1-8) >\n");
                        Integer stackNo = 0;
                        try {
                            stackNo = Integer.parseInt(stackInput);
                        } catch (NumberFormatException e) {
                            System.err.println("Input is not an integer.");
                        }
                        if (stackNo >= 1 && stackNo <= 8) {
                            printUniquePokemonStack(stackNo);
                            pass = true;
                        } else {
                            System.err.println("Invalid stack number.");
                        }
                    }
                    pressAnyKeyToContinue();
                    break;
                case "2":
                    String pokemonInput = cons.readLine(
                            "Search for the next occurence of 5 stars Pokemon in all stacks base on entered Pokemon >\n");
                    printNext5StarsPokemon(pokemonInput.trim());
                    pressAnyKeyToContinue();
                    break;
                case "3":
                    String pokemonStack = cons.readLine("Create a new Pokemon stack and save to a new file >\n");
                    String filename = cons.readLine("Enter filename to save (e.g. path/filename.csv) >\n");
                    savePokemonStack(pokemonStack, filename);
                    pressAnyKeyToContinue();
                    break;
                case "4":
                    printPokemonCardCount();
                    pressAnyKeyToContinue();
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
        Console cons = System.console();
        String anyKey = cons.readLine("Press any key to continue...");
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
        List<String> list = stackMap.get(stack);
        Set<String> set = new HashSet<>(list);
        List<?> uniqueList = new ArrayList<>(set);
        for (int i = 0; i < uniqueList.size(); i++) {
            System.out.printf("%d ==> %s\n", i + 1, uniqueList.get(i));
        }

    }

    // Task 2
    public static void printNext5StarsPokemon(String enteredPokemon) {
        // Task 2 - your code here
        for (Map.Entry<Integer, List<String>> entry : stackMap.entrySet()) {
            Integer stackNo = entry.getKey();
            List<String> value = entry.getValue();
            boolean found = false;

            System.out.println("Set " + stackNo);

            for (int i = 0; i < value.size(); i++) {
                if (value.get(i).contains(enteredPokemon)) {
                    found = true;
                    boolean found5 = false;
                    for (int j = i; j < value.size(); j++) {
                        if (value.get(j).contains("5*")) {
                            found5 = true;
                            System.out.println(value.get(j) + ">>>" + (j - i) + " cards to go");
                            break;
                        }
                    }
                    if (!found5) {
                        System.out.println("No 5 stars Pokemon found subsequently in the stack");
                    }
                    break;
                }
            }

            if (!found) {
                System.out.println(enteredPokemon + " not found in this set.");

            }

        }

    }

    // Task 2
    public static void printPokemonCardCount() {
        // Task 2 - your code here
        Map<String, Integer> countMap = new HashMap<>();
        List<String> allPokemon = new ArrayList<>();
        for (List<String> value : stackMap.values()) {
            for (String pokemon : value) {
                allPokemon.add(pokemon);
            }
        }
        // System.out.println(allPokemon.toString());

        for (String pokemon : allPokemon) {
            if (countMap.containsKey(pokemon)) {
                countMap.put(pokemon, countMap.get(pokemon) + 1);

            } else {
                countMap.put(pokemon, 1);
            }
        }

        // get highest count
        // Map<String, Integer> sortedMap = countMap.entrySet().stream()
        // .sorted(Entry.comparingByValue())
        // .collect(Collectors.toMap(Entry::getKey, Entry::getValue,
        // (e1, e2) -> e1, LinkedHashMap::new));

        List<Entry<String, Integer>> sortedEntries = new ArrayList<Entry<String, Integer>>(countMap.entrySet());

        Collections.sort(sortedEntries,
                new Comparator<Entry<String, Integer>>() {
                    @Override
                    public int compare(Entry<String, Integer> e1, Entry<String, Integer> e2) {
                        return e2.getValue().compareTo(e1.getValue());
                    }
                });

        for (int i = 0; i < 10 ; i++) {
            System.out.printf("Pokemon %d : %s, Cards Count: %d\n",i+1,sortedEntries.get(i).getKey(),sortedEntries.get(i).getValue());

        }

    }

}
