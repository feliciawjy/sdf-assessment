package sg.edu.nus.iss.sdf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileService {

    final String UTF8_BOM = "\uFEFF";

    public List<String> ReadCSV(String fullPathFilename) {
        // Task 1 - your code here
        List<String> pokemons = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fullPathFilename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(UTF8_BOM)) {
                    line = line.substring(1);
                }
                String[] values = line.split(",");
                pokemons.addAll(Arrays.asList(values));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("csv file for reading not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pokemons;
    }

    public void writeAsCSV(String pokemons, String fullPathFilename) {
        // Task 1 - your code here
        File f = new File(fullPathFilename);
        if (f.exists() && !f.isDirectory()) {

            // add existing to string list first
            List<String> pokemonsList = new ArrayList<>();

            pokemonsList = ReadCSV(fullPathFilename);

            try (// add to string list
            Scanner sc = new Scanner(pokemons)) {
                sc.useDelimiter(",");
                while (sc.hasNext()) {
                    pokemonsList.add(sc.next());
                }
            }

            try (FileWriter fw = new FileWriter(fullPathFilename)) {
                int partitionSize = 50;
                List<List<String>> partitions = new ArrayList<>();

                for (int i = 0; i < pokemonsList.size(); i += partitionSize) {
                    partitions.add(pokemonsList.subList(i, Math.min(i + partitionSize, pokemonsList.size())));
                }

                for (List<String> list : partitions) {
                    fw.write(list.stream().collect(Collectors.joining(",")));
                    fw.write("\n");
                }

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            try (FileWriter fw = new FileWriter(fullPathFilename)) {
                fw.write(pokemons);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
