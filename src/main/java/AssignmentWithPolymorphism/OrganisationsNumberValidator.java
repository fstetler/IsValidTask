package AssignmentWithPolymorphism;

public class OrganisationsNumberValidator extends GeneralNumberCalculations implements Validator {

    @Override
    public boolean isValid(String value) {
        return organisationsNumberIsValid(value);
    }

    private boolean organisationsNumberIsValid(String value) {
        if (value.length() == 7) {
            return false;
        }
        String fullString = addDashIfNeeded(value);
        int controlNumber = getControlNumber(fullString);
        String lastThree = getLastThree(fullString);
        String yyMmDd = getYyMmDd(fullString);
        return isOrganisationsNumber(yyMmDd) && controlNumber == luhnsAlgorithm(yyMmDd, lastThree);
    }

    private boolean isOrganisationsNumber(String yyMmDd) {
        return Integer.parseInt(yyMmDd.substring(2,4)) >= 20;
    }

    @Override
    public String toString() {
        return "organisationsnummer";
    }
}
