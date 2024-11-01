import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {

        Util util = new Util();
        // import data
        // check if contains +
        List<String> numbers = util.readStringsFromFile(Paths.get("src/main/resources/personalNumbers.txt").toString());

        List<PersonalNumber> personalNumbers = numbers.stream().map(PersonalNumber::new).toList();
        personalNumbers.forEach(PersonalNumber::initializePersonalNumber);



    }
}