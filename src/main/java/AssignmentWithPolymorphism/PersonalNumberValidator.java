package AssignmentWithPolymorphism;

public class PersonalNumberValidator extends GeneralNumberCalculations implements Validator {

    @Override
    public boolean isValid(String value) {
        return personNumberIsValid(value);
    }

    private boolean personNumberIsValid(String value) {
        if (value.length() == 7) {
            return false;
        }
        String fullString = addDashIfNeeded(value);
        int controlNumber = getControlNumber(fullString);
        String lastThree = getLastThree(fullString);
        String yyMmDd = getYyMmDd(fullString);
        if (isPersonalNumber(yyMmDd)) {
            String century = getCentury(fullString);
            if (!checkIsValidDate(century, yyMmDd)) {
                return false;
            }
        }

        return isPersonalNumber(yyMmDd) && controlNumber == luhnsAlgorithm(yyMmDd, lastThree);
    }

    private boolean isPersonalNumber(String yyMmDd) {
        return Integer.parseInt(yyMmDd.substring(4,6)) <= 31 && Integer.parseInt(yyMmDd.substring(2,4)) <= 12;
    }

    @Override
    public String toString() {
        return "personnummer";
    }
}
