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

        assertEquals(21, personalNumbersSize);
        assertEquals(firstValueInPersonalNumbers, personalNumbers.getFirst());
    }

    @Test
    public void verifyCentury() {
        IdentificationNumber first = new IdentificationNumber("900118+9811");
        IdentificationNumber second = new IdentificationNumber("050217+5633");
        IdentificationNumber third = new IdentificationNumber("201701102384");

        first.initializeIdentificationNumber();
        second.initializeIdentificationNumber();
        third.initializeIdentificationNumber();

        assertEquals("18", first.getCentury());
        assertEquals("19", second.getCentury());
        assertEquals("20", third.getCentury());
    }

    @Test
    public void assertLuhnsAlgorithm() {

        IdentificationNumber personalNumber = new IdentificationNumber("811218-9876");
        personalNumber.initializeIdentificationNumber();

        int luhnsValue = personalNumber.luhnsAlgorithm();

        assertEquals(6, luhnsValue);
    }

    @Test
    public void assertFailingPersonalNumberLuhns() {
        IdentificationNumber personalNumber = new IdentificationNumber("201701272394");
        personalNumber.initializeIdentificationNumber();

        assertFalse(personalNumber.isCorrectControlNumber);
    }

    @Test
    public void assertSuccessPersonalNumberLuhns() {
        IdentificationNumber personalNumber = new IdentificationNumber("189912299816");
        personalNumber.initializeIdentificationNumber();

        assertTrue(personalNumber.isCorrectControlNumber);
    }

    @Test
    public void verifyValidLuhnsForSamordningsNummer() {
        IdentificationNumber personalNumber = new IdentificationNumber("190910799824");
        personalNumber.initializeIdentificationNumber();
        assertEquals(4, personalNumber.controlNumber);
    }

    @Test
    public void verifyOrganisationNumberLuhnsValue() {
        IdentificationNumber personalNumber = new IdentificationNumber("556614-3185");
        personalNumber.initializeIdentificationNumber();
        assertEquals(5, personalNumber.luhnsAlgorithm());
        assertTrue(personalNumber.isCorrectControlNumber);
    }
}
