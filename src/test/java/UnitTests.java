import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class UnitTests {

    Util util = new Util();

    @Test
    public void verifyReadingTextFromFile() throws IOException {
        List<String> personalNumbers = util.readStringsFromFile(Paths.get("src/main/resources/personalNumbers.txt").toString());
        int personalNumbersSize = personalNumbers.size();
        String firstValueInPersonalNumbers = "201701102384";

        assertEquals(14, personalNumbersSize);
        assertEquals(firstValueInPersonalNumbers, personalNumbers.getFirst());
    }

    @Test
    public void verifyCentury() {
        PersonalNumber first = new PersonalNumber("900118+9811");
        PersonalNumber second = new PersonalNumber("050217+5633");
        PersonalNumber third = new PersonalNumber("201701102384");

        first.initializePersonalNumber();
        second.initializePersonalNumber();
        third.initializePersonalNumber();

        assertEquals("18", first.getCentury());
        assertEquals("19", second.getCentury());
        assertEquals("20", third.getCentury());
    }

    @Test
    public void verifyBirthDate() {
        PersonalNumber first = new PersonalNumber("201701102384");
        LocalDate localDate = LocalDate.of(2017, 1, 10);

        first.initializePersonalNumber();

        assertEquals(localDate, first.birthDate);
    }

    @Test
    public void assertLuhnsAlgorithm() {

        PersonalNumber personalNumber = new PersonalNumber("811218-9876");
        personalNumber.initializePersonalNumber();

        int luhnsValue = personalNumber.luhnsAlgorithm();

        assertEquals(6, luhnsValue);
    }

    @Test
    public void assertFailingPersonalNumberLuhns() {
        PersonalNumber personalNumber = new PersonalNumber("201701272394");
        personalNumber.initializePersonalNumber();

        assertFalse(personalNumber.valid);
    }

    @Test
    public void assertSuccessPersonalNumberLuhns() {
        PersonalNumber personalNumber = new PersonalNumber("189912299816");
        personalNumber.initializePersonalNumber();

        assertTrue(personalNumber.valid);
    }
}
