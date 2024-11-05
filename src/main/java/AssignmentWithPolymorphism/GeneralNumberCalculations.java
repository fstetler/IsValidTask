package AssignmentWithPolymorphism;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GeneralNumberCalculations {

    public String getCentury(String fullString) {
        String yyMmDd = getYyMmDd(fullString);
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

    public String getYyMmDd(String fullString) {
        int index;
        if (fullString.contains("-")) {
            index = fullString.indexOf("-");
        } else {
            index = fullString.indexOf("+");
        }
        return fullString.substring(index - 6, index);
    }

    public String getLastThree(String fullString) {
        return fullString.substring(fullString.length() - 4, fullString.length() - 1);
    }

    public String addDashIfNeeded(String fullString) {
        StringBuilder stringBuilder = new StringBuilder(fullString);
        if (!(fullString.contains("-") || fullString.contains("+"))) {
            stringBuilder.insert(fullString.length() - 4, "-");
        }
        return stringBuilder.toString();
    }

    public boolean checkIsValidDate(String century, String yyMmDd) {
        int year = Integer.parseInt(century + yyMmDd.substring(0,2));
        int month = Integer.parseInt(yyMmDd.substring(2,4));
        int day = Integer.parseInt(yyMmDd.substring(4,6));
        try {
            LocalDate.of(year, month, day);
        } catch (DateTimeException dte) {
            return false;
        }
        return true;
    }

    public int getControlNumber(String fullString) {
        return Integer.parseInt(fullString.substring(fullString.length() - 1));
    }

    public int luhnsAlgorithm(String yyMmDd, String lastThree) {
        String numberToEvaluate = yyMmDd + lastThree;
        List<Integer> numbers = numbersFromString(numberToEvaluate);
        List<Integer> numbersMultipliedByOneOrTwo = multiplyEveryOtherNumberByTwo(numbers);
        String joinedNumbers = joinNumbersFromList(numbersMultipliedByOneOrTwo);
        int totalSum = sumAllNumbersFromString(joinedNumbers);

        return luhnsCalculation(totalSum);
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
