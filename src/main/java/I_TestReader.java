import java.io.IOException;
import java.util.List;

public interface I_TestReader {

    public List<String> readStringsFromFile(String filePath) throws IOException;
}
