import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class IdNumberTests implements I_TestReader{


    @Test
    public void verifyReadingTextFromFile() throws IOException {
        List<String> personalNumbers = readStringsFromFile("fakeFilePath");
        int personalNumbersSize = personalNumbers.size();
        String firstValueInPersonalNumbers = "201701102384";

        assertEquals(3, personalNumbersSize);
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
    public void verifyLuhnsAlgorithm() {

        IdentificationNumber personalNumber = new IdentificationNumber("811218-9876");
        personalNumber.initializeIdentificationNumber();

        int luhnsValue = personalNumber.luhnsAlgorithm();

        assertEquals(6, luhnsValue);
    }

    @Test
    public void verifyControlNumber() {
        IdentificationNumber person1 = new IdentificationNumber("201701272394");
        IdentificationNumber person2 = new IdentificationNumber("189912299816");
        person1.initializeIdentificationNumber();
        person2.initializeIdentificationNumber();

        assertFalse(person1.isCorrectControlNumber());
        assertTrue(person2.isCorrectControlNumber());
    }

    @Test
    public void verifyLuhnsForSamordningsNumber() {
        IdentificationNumber samordningsNumber = new IdentificationNumber("190910799824");
        samordningsNumber.initializeIdentificationNumber();
        assertEquals(4, samordningsNumber.getControlNumber());
        assertTrue(samordningsNumber.isCorrectControlNumber());
    }

    @Test
    public void verifyLuhnsForOrganisationsNumber() {
        IdentificationNumber organisationsNumber = new IdentificationNumber("556614-3185");
        organisationsNumber.initializeIdentificationNumber();
        assertEquals(5, organisationsNumber.luhnsAlgorithm());
        assertTrue(organisationsNumber.isCorrectControlNumber());
    }

    @Override
    public List<String> readStringsFromFile(String filePath) throws IOException {
        return List.of(
                "201701102384",
                "141206-2380",
                "20080903-2386");
    }
}
