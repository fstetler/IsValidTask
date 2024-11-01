import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class PersonalNumberTests {

    Util util = new Util();

    @Test
    public void verifyReadingTextFromFile() throws IOException {
        List<String> personalNumbers = util.readStringsFromFile(Paths.get("src/main/resources/allNumbers.txt").toString());
        int personalNumbersSize = personalNumbers.size();
        String firstValueInPersonalNumbers = "201701102384";

        assertEquals(17, personalNumbersSize);
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

        assertFalse(personalNumber.isValid);
    }

    @Test
    public void assertSuccessPersonalNumberLuhns() {
        PersonalNumber personalNumber = new PersonalNumber("189912299816");
        personalNumber.initializePersonalNumber();

        assertTrue(personalNumber.isValid);
    }

    @Test
    public void verifyValidLuhnsForSamordningsNummer() {
        PersonalNumber personalNumber = new PersonalNumber("190910799824");
        personalNumber.initializePersonalNumber();
        assertEquals(4, personalNumber.controlNumber);
    }

    @Test
    public void verifyOrganisationNumberLuhnsValue() {
        PersonalNumber personalNumber = new PersonalNumber("556614-3185");
        personalNumber.initializePersonalNumber();
        assertEquals(5, personalNumber.luhnsAlgorithm());
        assertTrue(personalNumber.isValid);
    }
}
