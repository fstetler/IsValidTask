import java.util.List;

public class NumberValidator {

    List<String> getValidNumbers(List<IdentificationNumber> numbers, IdType idType) {
        return numbers.stream()
                .filter(pn -> pn.getIdType() == idType)
                .filter(IdentificationNumber::isCorrectControlNumber)
                .map(IdentificationNumber::getFullString)
                .toList();
    }

    List<String> getNotValidNumbers(List<IdentificationNumber> numbers, IdType idType) {
        return numbers.stream()
                .filter(pn -> pn.getIdType() == idType)
                .filter(pn -> !pn.isCorrectControlNumber())
                .map(IdentificationNumber::getFullString)
                .toList();
    }
}
