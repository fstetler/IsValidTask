package AssignmentWithPolymorphism;

public class CarLicensePlateValidator implements Validator {

    @Override
    public boolean isValid(String value) {
        if (value.length() == 7) {
            return value.substring(0,3).matches("[a-zA-Z]+") && value.substring(3,4).matches("-") && value.substring(4,7).matches("\\d+");
        }
        return false;
    }

    @Override
    public String toString() {
        return "car license plate number";
    }
}
