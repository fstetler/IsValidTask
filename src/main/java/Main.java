import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        Util util = new Util();

        List<String> numbers = util.readStringsFromFile(Paths.get("src/main/resources/allNumbers.txt").toString());

        List<IdentificationNumber> identificationNumbers = numbers.stream().map(IdentificationNumber::new).toList();
        identificationNumbers.forEach(IdentificationNumber::initializeIdentificationNumber);

        List<String> validPersonalNumbers = identificationNumbers.stream()
                .filter(pn -> pn.idType == IdType.PERSONAL_NUMBER)
                .filter(pn -> pn.isCorrectControlNumber)
                .map(pn -> pn.fullString)
                .toList();
        List<String> notValidPersonalNumbers = identificationNumbers.stream()
                .filter(pn -> pn.idType == IdType.PERSONAL_NUMBER)
                .filter(pn -> !pn.isCorrectControlNumber)
                .map(pn -> pn.fullString)
                .toList();

        List<String> validSamordningsNumbers = identificationNumbers.stream()
                .filter(pn -> pn.idType == IdType.SAMORDNINGS_NUMBER)
                .filter(pn -> pn.isCorrectControlNumber)
                .map(pn -> pn.fullString).toList();
        List<String> notValidSamordningsNumbers = identificationNumbers.stream()
                .filter(pn -> pn.idType == IdType.SAMORDNINGS_NUMBER)
                .filter(pn -> !pn.isCorrectControlNumber)
                .map(pn -> pn.fullString)
                .toList();

        List<String> validOrganisationsNumbers = identificationNumbers.stream()
                .filter(pn -> pn.idType == IdType.ORGANISATIONS_NUMBER)
                .filter(pn -> pn.isCorrectControlNumber)
                .map(pn -> pn.fullString)
                .toList();
        List<String> notValidOrganisationsNumbers = identificationNumbers.stream()
                .filter(pn -> pn.idType == IdType.ORGANISATIONS_NUMBER)
                .filter(pn -> !pn.isCorrectControlNumber)
                .map(pn -> pn.fullString)
                .toList();


        util.txtWriter(validPersonalNumbers, "src/main/resources/validPersonalNumbersFile.txt");
        util.txtWriter(notValidPersonalNumbers, "src/main/resources/notValidPersonalNumbersFile.txt");
        util.txtWriter(validSamordningsNumbers, "src/main/resources/validSamordningsNumbersFile.txt");
        util.txtWriter(notValidSamordningsNumbers, "src/main/resources/notValidSamordningsNumbersFile.txt");
        util.txtWriter(validOrganisationsNumbers, "src/main/resources/validOrganisationsNumbersFile.txt");
        util.txtWriter(notValidOrganisationsNumbers, "src/main/resources/notValidOrganisationsNumbersFile.txt");

    }
}