package AssignmentWithPolymorphism;

public class SamordningsNumberValidator extends GeneralNumberCalculations implements Validator {

    @Override
    public boolean isValid(String value) {
        return samordningsNumberisValid(value);
    }

    private boolean samordningsNumberisValid(String value) {
        String fullString = addDashIfNeeded(value);
        int controlNumber = getControlNumber(fullString);
        String lastThree = getLastThree(fullString);
        String yyMmDd = getYyMmDd(fullString);
        return isSamordningsNumber(yyMmDd) && controlNumber == luhnsAlgorithm(yyMmDd, lastThree);
    }

    private boolean isSamordningsNumber(String yyMmDd) {
        return Integer.parseInt(yyMmDd.substring(4,6)) >= 61 && Integer.parseInt(yyMmDd.substring(4,6)) <= 91;
    }

    @Override
    public String toString() {
        return "samordningsnummer";
    }
}
