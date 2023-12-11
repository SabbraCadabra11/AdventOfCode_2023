import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Day7.solution();
    }

    public static List<String> getInput(String inputPath) {
        var input = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            reader.lines().forEach(input::add);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return input;
    }
}