package Assignment;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IdentificationNumber {

    private String fullString;

    private String century;

    private String yyMmDd;

    private String lastThree;

    private int controlNumber;

    private boolean isCorrectControlNumber;

    private IdType idType;

    public IdentificationNumber(String number) {
        this.fullString = number;
    }

    public void initializeIdentificationNumber() {
        addDashIfNeeded();
        setControlNumber();
        setLastThree();
        setYyMmDd();
        setTypeOfNumber();
        isCorrectControlNumber = luhnsAlgorithm() == controlNumber;
        if (idType == IdType.PERSONAL_NUMBER) {
            century = setCentury();
            checkIsValidDate();
        }
    }

    public String setCentury() {
        LocalDate currentDate = LocalDate.now(ZoneId.of("Europe/Stockholm"));
        LocalDate birthDate = LocalDate.parse(yyMmDd, DateTimeFormatter.ofPattern("yyMMdd"));

        if (fullString.length() == 13) {
            return fullString.substring(0,2);
        }

        if (fullString.contains("+")) {
            if (birthDate.isAfter(currentDate)) {
                return "18";
            }
        } else {
            if (birthDate.isBefore(currentDate)) {
                return "20";
            }
        }
        return "19";
    }

    public int luhnsAlgorithm() {
        String numberToEvaluate = yyMmDd + lastThree;
        List<Integer> numbers = numbersFromString(numberToEvaluate);
        List<Integer> numbersMultipliedByOneOrTwo = multiplyEveryOtherNumberByTwo(numbers);
        String joinedNumbers = joinNumbersFromList(numbersMultipliedByOneOrTwo);
        int totalSum = sumAllNumbersFromString(joinedNumbers);

        return luhnsCalculation(totalSum);
    }

    public String getCentury() {
        return century;
    }

    public String getFullString() {
        return fullString;
    }

    public int getControlNumber() {
        return controlNumber;
    }

    public boolean isCorrectControlNumber() {
        return isCorrectControlNumber;
    }

    public IdType getIdType() {
        return idType;
    }

    private void setTypeOfNumber() {
        if (Integer.parseInt(yyMmDd.substring(4,6)) > 31) {
            idType = IdType.SAMORDNINGS_NUMBER;
        } else if (Integer.parseInt(yyMmDd.substring(2,4)) >= 20) {
            idType = IdType.ORGANISATIONS_NUMBER;
        } else {
            idType = IdType.PERSONAL_NUMBER;
        }
    }

    private void addDashIfNeeded() {
        if (!(fullString.contains("-") || fullString.contains("+"))) {
            StringBuilder stringBuilder = new StringBuilder(fullString);
            stringBuilder.insert(fullString.length() - 4, "-");
            fullString = stringBuilder.toString();
        }
    }

    private void checkIsValidDate() {
        int year = Integer.parseInt(century + yyMmDd.substring(0,2));
        int month = Integer.parseInt(yyMmDd.substring(2,4));
        int day = idType == IdType.SAMORDNINGS_NUMBER ? Integer.parseInt(yyMmDd.substring(4,6)) - 60 : Integer.parseInt(yyMmDd.substring(4,6));
        try {
            LocalDate.of(year, month, day);
        } catch (DateTimeException dte) {
            isCorrectControlNumber = false;
        }
    }

    private void setYyMmDd() {
        int index;
        if (fullString.contains("-")) {
            index = fullString.indexOf("-");
        } else {
            index = fullString.indexOf("+");
        }
        yyMmDd = fullString.substring(index - 6, index);
    }

    private void setLastThree() {
        lastThree = fullString.substring(fullString.length() - 4, fullString.length() - 1);
    }

    private void setControlNumber() {
        controlNumber = Integer.parseInt(fullString.substring(fullString.length() - 1));
    }

    private List<Integer> numbersFromString(String numbers) {
        return numbers.chars().map(Character::getNumericValue).boxed().toList();
    }

    private List<Integer> multiplyEveryOtherNumberByTwo(List<Integer> numbers) {
        return IntStream.range(0, numbers.size())
                .map(n -> n % 2 == 0 ? numbers.get(n) * 2 : numbers.get(n))
                .boxed()
                .toList();
    }

    private String joinNumbersFromList(List<Integer> numbers) {
        return numbers.stream().map(String::valueOf).collect(Collectors.joining());
    }

    private int sumAllNumbersFromString(String numbers) {
        return numbers.chars().map(Character::getNumericValue).sum();
    }

    private int luhnsCalculation(int sum) {
        return (10 - (sum % 10)) % 10;
    }
}
