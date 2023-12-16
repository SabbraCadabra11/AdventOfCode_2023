import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Day10.solution();
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

    public static char[][] transformInputTo2dArray(List<String> input) {
        final int rows = input.size();
        final int columns = input.get(0).length();
        char[][] array = new char[rows][columns];

        for (int r = 0; r < array.length; r++) {
            for (int c = 0; c < columns; c++) {
                array[r][c] = input.get(r).charAt(c);
            }
        }
        return array;
    }
}