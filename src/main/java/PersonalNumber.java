import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PersonalNumber {

    String fullNumber;

    LocalDate birthDate;

    String century;

    String firstSix;

    String lastThree;

    int controlNumber;

    boolean valid;

    public PersonalNumber(String number) {
        this.fullNumber = number;
    }

    public void initializePersonalNumber() {
        addDashIfNeeded();
        setControlNumber();
        setLastThree();
        setFirstSix();
        century = getCentury();
        setBirthDate();
    }

    public void addDashIfNeeded() {
        if (!(fullNumber.contains("-") || fullNumber.contains("+"))) {
            StringBuilder stringBuilder = new StringBuilder(fullNumber);
            stringBuilder.insert(fullNumber.length() - 4, "-");
            fullNumber = stringBuilder.toString();
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
        if (fullNumber.contains("-")) {
            index = fullNumber.indexOf("-");
        } else {
            index = fullNumber.indexOf("+");
        }
        firstSix = fullNumber.substring(index - 6, index);
    }

    public void setLastThree() {
        lastThree = fullNumber.substring(fullNumber.length() - 4, fullNumber.length() - 1);
    }

    public void setControlNumber() {
        controlNumber = Integer.parseInt(fullNumber.substring(fullNumber.length()-1));
    }

    public String getCentury() {

        LocalDate localDate = LocalDate.now(ZoneId.of("Europe/Stockholm"));
        LocalDate birthDate = LocalDate.parse(firstSix, DateTimeFormatter.ofPattern("yyMMdd"));

        if (fullNumber.length() == 13) {
            return fullNumber.substring(0,2);
        }

        if (fullNumber.contains("+")) {
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
}
