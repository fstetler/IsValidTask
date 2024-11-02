import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        Util util = new Util();
        Main main = new Main();

        List<String> numbers = util.readStringsFromFile(Paths.get("src/main/resources/allNumbers.txt").toString());

        List<IdentificationNumber> identificationNumbers = numbers.stream().map(IdentificationNumber::new).toList();
        identificationNumbers.forEach(IdentificationNumber::initializeIdentificationNumber);

        List<String> validPersonalNumbers = main.getValidNumbers(identificationNumbers, IdType.PERSONAL_NUMBER);
        List<String> notValidPersonalNumbers = main.getNotValidNumbers(identificationNumbers, IdType.PERSONAL_NUMBER);

        List<String> validSamordningsNumbers = main.getValidNumbers(identificationNumbers, IdType.SAMORDNINGS_NUMBER);
        List<String> notValidSamordningsNumbers = main.getNotValidNumbers(identificationNumbers, IdType.SAMORDNINGS_NUMBER);

        List<String> validOrganisationsNumbers = main.getValidNumbers(identificationNumbers, IdType.ORGANISATIONS_NUMBER);
        List<String> notValidOrganisationsNumbers = main.getNotValidNumbers(identificationNumbers, IdType.ORGANISATIONS_NUMBER);


        util.txtWriter(validPersonalNumbers, "src/main/resources/validPersonalNumbersFile.txt");
        util.txtWriter(notValidPersonalNumbers, "src/main/resources/notValidPersonalNumbersFile.txt");
        util.txtWriter(validSamordningsNumbers, "src/main/resources/validSamordningsNumbersFile.txt");
        util.txtWriter(notValidSamordningsNumbers, "src/main/resources/notValidSamordningsNumbersFile.txt");
        util.txtWriter(validOrganisationsNumbers, "src/main/resources/validOrganisationsNumbersFile.txt");
        util.txtWriter(notValidOrganisationsNumbers, "src/main/resources/notValidOrganisationsNumbersFile.txt");

    }

    private List<String> getValidNumbers(List<IdentificationNumber> numbers, IdType idType) {
        return numbers.stream()
                .filter(pn -> pn.getIdType() == idType)
                .filter(IdentificationNumber::isCorrectControlNumber)
                .map(IdentificationNumber::getFullString)
                .toList();
    }

    private List<String> getNotValidNumbers(List<IdentificationNumber> numbers, IdType idType) {
        return numbers.stream()
                .filter(pn -> pn.getIdType() == idType)
                .filter(pn -> !pn.isCorrectControlNumber())
                .map(IdentificationNumber::getFullString)
                .toList();
    }
}