package AssignmentWithPolymorphism;

import Assignment.ReaderWriter;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class MyMain {

    public static void main(String[] args) throws IOException {

        ReaderWriter readerWriter = new ReaderWriter();
        List<String> strings = readerWriter.readStringsFromFile(Paths.get("src/main/resources/newNumbers.txt").toString());

        List<Validator> validators = List.of(
                new PersonalNumberValidator(),
                new SamordningsNumberValidator(),
                new OrganisationsNumberValidator());

        for (Validator validator : validators) {
            for (String string : strings) {
                if (validator.isValid(string)) {
                    System.out.println(string + " is a valid " + validator);
                }
            }
        }
    }
}
