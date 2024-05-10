package doNotModify;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static String[] read(String path) {
        String[] result;
        Scanner scanner = null;
        
        try {
            scanner = new Scanner(new File(path));
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int count = 0;
        while(scanner.hasNextLine()) {
            scanner.nextLine();
            count++;
        }
        
        scanner.close();
        result = new String[count];
        try {
            scanner = new Scanner(new File(path));
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < count; i++) {
            result[i] = scanner.nextLine().toUpperCase();
        }
        scanner.close();
        return result;
    } 
}
