import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        Util util = new Util();

        List<String> numbers = util.readStringsFromFile(Paths.get("src/main/resources/personalNumbers.txt").toString());

        List<PersonalNumber> personalNumbers = numbers.stream().map(PersonalNumber::new).toList();
        personalNumbers.forEach(PersonalNumber::initializePersonalNumber);

        List<String> validPersonalNumbers = personalNumbers.stream().filter(pn -> pn.isValid).map(pn -> pn.fullString).toList();
        List<String> notValidPersonalNumbers = personalNumbers.stream().filter(pn -> !pn.isValid).map(pn -> pn.fullString).toList();

        util.txtWriter(validPersonalNumbers, "src/main/resources/validPersonalNumbersFile.txt");
        util.txtWriter(notValidPersonalNumbers, "src/main/resources/notValidPersonalNumbersFile.txt");

    }
}