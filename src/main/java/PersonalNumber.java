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

    String yyMmDd;

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
        setYyMmDd();
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
        int year = Integer.parseInt(century + yyMmDd.substring(0,2));
        int month = Integer.parseInt(yyMmDd.substring(2,4));
        int day = Integer.parseInt(yyMmDd.substring(4,6));
        birthDate = LocalDate.of(year, month, day);
    }

    public void setYyMmDd() {
        int index;
        if (fullString.contains("-")) {
            index = fullString.indexOf("-");
        } else {
            index = fullString.indexOf("+");
        }
        yyMmDd = fullString.substring(index - 6, index);
    }

    public void setLastThree() {
        lastThree = fullString.substring(fullString.length() - 4, fullString.length() - 1);
    }

    public void setControlNumber() {
        controlNumber = Integer.parseInt(fullString.substring(fullString.length() - 1));
    }

    public String getCentury() {
        LocalDate currentDate = LocalDate.now(ZoneId.of("Europe/Stockholm"));
        LocalDate birthDate = LocalDate.parse(yyMmDd, DateTimeFormatter.ofPattern("yyMMdd"));

        if (fullString.length() == 13) {
            return fullString.substring(0,2);
        }

        if (fullString.contains("+")) {
            if (birthDate.isAfter(currentDate)) {
                return "18";
            } else {
                return "19";
            }
        } else {
            if (birthDate.isBefore(currentDate)) {
                return "20";
            } else {
                return "19";
            }
        }
    }

    public int luhnsAlgorithm() {
        String numberToEvaluate = yyMmDd + lastThree;

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
