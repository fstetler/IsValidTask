import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class PersonalNumber {

    String fullNumber;

    LocalDate LocalDateBirthday;

    String century;

    String firstSix;

    String lastThree;

    int controlNumber;

    boolean valid;

    public PersonalNumber(String number) {
        this.fullNumber = number;
    }

    public void initializePersonalNumber() {

//        // remove dash
//        fullNumber = removeDashFromNumber();

        // set control number

        // set lastThree
        // set first six
        // set century

        setControlNumber();
        setLastThree();

        setFirstSix();

        System.out.println("hello");
    }

    public void setFirstSix() {
        if (fullNumber.contains("-")) {
            int index = fullNumber.indexOf("-");
            firstSix = fullNumber.substring(index - 6, index);
        } else {
            int length = fullNumber.length();
            firstSix = fullNumber.substring(length - 10, length - 4);
        }
    }

    public void setLastThree() {
        lastThree = fullNumber.substring(fullNumber.length() - 4, fullNumber.length() - 1);
    }

    public void setControlNumber() {
        controlNumber = Integer.parseInt(fullNumber.substring(fullNumber.length()-1));
    }


    public void addCenturyOnPersonNumber() {

        LocalDate localDate = LocalDate.now(ZoneId.of("Europe/Stockholm"));
        LocalDate birthDate = LocalDate.parse(fullNumber.substring(0,6), DateTimeFormatter.ofPattern("yyMMdd"));

        if (fullNumber.contains("+")) {
            if (birthDate.isAfter(localDate)) {
                LocalDateBirthday = birthDate.minusYears(200);
            } else {
                LocalDateBirthday = birthDate.minusYears(100);
            }
        } else {
            if (birthDate.isBefore(localDate)) {
                LocalDateBirthday = birthDate.minusYears(100);
            }
        }

    }

    public String removeDashFromNumber() {
        return fullNumber.replace("-", "");
    }

    public LocalDate getLocalDateBirthday() {
        return LocalDateBirthday;
    }
}
