package be.beauce.adventofcode2023.day3.part1;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Symbols {
    private final List<SymbolInfo> symbols;

    public Symbols(int lineIndex, String line) {
        this.symbols = findSymbols(lineIndex, line);
    }

    public int size() {
        return symbols.size();
    }

    private List<SymbolInfo> findSymbols(int lineIndex, String line) {
        var symbolInfos = new ArrayList<SymbolInfo>();
        Pattern pattern = Pattern.compile("[^\\d.]");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            int startIndex = matcher.start();
            String symbol = matcher.group();
            symbolInfos.add(new SymbolInfo(symbol, lineIndex, startIndex));
        }
        return symbolInfos;
    }

    public List<SymbolInfo> asList() {
        return symbols;
    }
}
