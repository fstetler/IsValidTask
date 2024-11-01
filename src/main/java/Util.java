import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Util {

    public List<String> readStringsFromFile(String filePath) throws IOException {
        List<String> stringList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                stringList.add(line);
            }
        }
        return stringList;
    }

    public int luhnsAlgorithm(PersonalNumber personalNumber) {
        String numberToEvaluate = personalNumber.firstSix + personalNumber.lastThree;

        List<Integer> numbers = numberToEvaluate.chars().map(Character::getNumericValue).boxed().toList();

        List<Integer> numbersMultipliedByOneOrTwo = IntStream.range(0, numbers.size())
                .map(n -> n % 2 == 0 ? numbers.get(n) * 2 : numbers.get(n))
                .boxed()
                .toList();

        String joinedNumbers = numbersMultipliedByOneOrTwo.stream().map(String::valueOf).collect(Collectors.joining());

        int totalSum = joinedNumbers.chars().map(Character::getNumericValue).sum();

        return (10 - (totalSum % 10)) % 10;
    }
}
