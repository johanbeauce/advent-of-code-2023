package be.beauce.adventofcode2023.day3.part1;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Numbers {
    private final List<NumberInfo> numbers;

    public Numbers(int lineIndex, String line) {
        this.numbers = findNumbers(lineIndex, line);
    }

    public int size() {
        return numbers.size();
    }

    private List<NumberInfo> findNumbers(int lineIndex, String line) {
        var numberInfos = new ArrayList<NumberInfo>();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            int startIndex = matcher.start();
            int number = Integer.parseInt(matcher.group());
            numberInfos.add(new NumberInfo(number, lineIndex, startIndex));
        }
        return numberInfos;
    }

    public List<NumberInfo> asList() {
        return numbers;
    }

}
