import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderWriter {

    public List<String> readStringsFromFile(String filePath) throws IOException {
        List<String> stringList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                stringList.add(line);
            }
        }
        return stringList;
    }

    public void txtWriter(List<String> strings, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : strings) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
