package Assignment;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        ReaderWriter util = new ReaderWriter();
        NumberValidator numberValidator = new NumberValidator();

        List<String> numbers = util.readStringsFromFile(Paths.get("src/main/resources/allNumbers.txt").toString());

        List<IdentificationNumber> identificationNumbers = numbers.stream().map(IdentificationNumber::new).toList();
        identificationNumbers.forEach(IdentificationNumber::initializeIdentificationNumber);

        List<String> validPersonalNumbers = numberValidator.getValidNumbers(identificationNumbers, IdType.PERSONAL_NUMBER);
        List<String> notValidPersonalNumbers = numberValidator.getNotValidNumbers(identificationNumbers, IdType.PERSONAL_NUMBER);

        List<String> validSamordningsNumbers = numberValidator.getValidNumbers(identificationNumbers, IdType.SAMORDNINGS_NUMBER);
        List<String> notValidSamordningsNumbers = numberValidator.getNotValidNumbers(identificationNumbers, IdType.SAMORDNINGS_NUMBER);

        List<String> validOrganisationsNumbers = numberValidator.getValidNumbers(identificationNumbers, IdType.ORGANISATIONS_NUMBER);
        List<String> notValidOrganisationsNumbers = numberValidator.getNotValidNumbers(identificationNumbers, IdType.ORGANISATIONS_NUMBER);


        util.txtWriter(validPersonalNumbers, "src/main/resources/validPersonalNumbersFile.txt");
        util.txtWriter(notValidPersonalNumbers, "src/main/resources/notValidPersonalNumbersFile.txt");
        util.txtWriter(validSamordningsNumbers, "src/main/resources/validSamordningsNumbersFile.txt");
        util.txtWriter(notValidSamordningsNumbers, "src/main/resources/notValidSamordningsNumbersFile.txt");
        util.txtWriter(validOrganisationsNumbers, "src/main/resources/validOrganisationsNumbersFile.txt");
        util.txtWriter(notValidOrganisationsNumbers, "src/main/resources/notValidOrganisationsNumbersFile.txt");

    }
}