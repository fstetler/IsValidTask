import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UnitTests {

    Util util = new Util();

    @Test
    public void verifyReadingTextFromFile() throws IOException {
        List<String> personalNumbers = util.readStringsFromFile(Paths.get("src/main/resources/personalNumbers.txt").toString());

        String firstValueInPersonalNumbers = "201701102384";
        int personalNumbersSize = personalNumbers.size();

        assertEquals(14, personalNumbersSize);
        assertEquals(firstValueInPersonalNumbers, personalNumbers.getFirst());
    }

    @Test
    public void addPersonOlderThan100() {
        PersonalNumber firstPersonalNumber = new PersonalNumber("900118+9811");
        PersonalNumber secondPersonalNumber = new PersonalNumber("050217+5633");

        firstPersonalNumber.addCenturyOnPersonNumber();
        secondPersonalNumber.addCenturyOnPersonNumber();
        assertEquals(LocalDate.of(1890, 1, 18), firstPersonalNumber.getLocalDateBirthday());
        assertEquals(LocalDate.of(1905, 2, 17), secondPersonalNumber.getLocalDateBirthday());
    }


    @Test
    public void addCenturyForPersonBornIn2000s() {
        PersonalNumber personalNumber = new PersonalNumber("041104-5312");

        personalNumber.addCenturyOnPersonNumber();
    }

}
