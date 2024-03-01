package sg.edu.nus.iss.sdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FileService {
    public List<String> ReadCSV(String fullPathFilename) {
        // Task 1 - your code here
        List<String> record = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(fullPathFilename))) {
            sc.useDelimiter(",");
            while (sc.hasNext()) {
                record.add(sc.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("csv file for reading not found.");
        }

        // System.out.println(record.toString());
        return record;
    }

    public void writeAsCSV(String pokemons, String fullPathFilename) {
        // Task 1 - your code here
        File f = new File(fullPathFilename);
        if (f.exists() && !f.isDirectory()) {

            // add existing to string list first
            List<String> record = new ArrayList<>();
            record = ReadCSV(fullPathFilename);

            // add to string list
            Scanner sc = new Scanner(pokemons);
            sc.useDelimiter(",");
            while (sc.hasNext()) {
                record.add(sc.next());
            }

            // write string list to csv
            System.out.println(record.toString());

            try (FileWriter fw = new FileWriter(fullPathFilename)) {
                fw.write(record.stream().collect(Collectors.joining(",")));
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
