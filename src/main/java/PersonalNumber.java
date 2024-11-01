import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PersonalNumber {

    String fullString;

    LocalDate birthDate;

    String century;

    String firstSix;

    String lastThree;

    int controlNumber;

    boolean valid;

    public PersonalNumber(String number) {
        this.fullString = number;
    }

    public void initializePersonalNumber() {
        addDashIfNeeded();
        setControlNumber();
        setLastThree();
        setFirstSix();
        century = getCentury();
        setBirthDate();
        valid = luhnsAlgorithm() == controlNumber;
    }

    public void addDashIfNeeded() {
        if (!(fullString.contains("-") || fullString.contains("+"))) {
            StringBuilder stringBuilder = new StringBuilder(fullString);
            stringBuilder.insert(fullString.length() - 4, "-");
            fullString = stringBuilder.toString();
        }
    }

    public void setBirthDate() {
        int year = Integer.parseInt(century + firstSix.substring(0,2));
        int month = Integer.parseInt(firstSix.substring(2,4));
        int day = Integer.parseInt(firstSix.substring(4,6));
        birthDate = LocalDate.of(year, month, day);
    }

    public void setFirstSix() {
        int index;
        if (fullString.contains("-")) {
            index = fullString.indexOf("-");
        } else {
            index = fullString.indexOf("+");
        }
        firstSix = fullString.substring(index - 6, index);
    }

    public void setLastThree() {
        lastThree = fullString.substring(fullString.length() - 4, fullString.length() - 1);
    }

    public void setControlNumber() {
        controlNumber = Integer.parseInt(fullString.substring(fullString.length()-1));
    }

    public String getCentury() {

        LocalDate localDate = LocalDate.now(ZoneId.of("Europe/Stockholm"));
        LocalDate birthDate = LocalDate.parse(firstSix, DateTimeFormatter.ofPattern("yyMMdd"));

        if (fullString.length() == 13) {
            return fullString.substring(0,2);
        }

        if (fullString.contains("+")) {
            if (birthDate.isAfter(localDate)) {
                return "18";
            } else {
                return "19";
            }
        } else {
            if (birthDate.isBefore(localDate)) {
                return "20";
            } else {
                return "19";
            }
        }
    }

    public int luhnsAlgorithm() {
        String numberToEvaluate = firstSix + lastThree;

        List<Integer> numbers = numberToEvaluate.chars().map(Character::getNumericValue).boxed().toList();

        List<Integer> numbersMultipliedByOneOrTwo = IntStream.range(0, numbers.size())
                .map(n -> n % 2 == 0 ? numbers.get(n) * 2 : numbers.get(n))
                .boxed()
                .toList();

        String joinedNumbers = numbersMultipliedByOneOrTwo.stream().map(String::valueOf).collect(Collectors.joining());

        int totalSum = joinedNumbers.chars().map(Character::getNumericValue).sum();

        return (10 - (totalSum % 10)) % 10;
    }
}
