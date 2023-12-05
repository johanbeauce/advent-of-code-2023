package be.beauce.adventofcode2023.day4.part1;

import be.beauce.adventofcode2023.day4.Card;
import be.beauce.adventofcode2023.day4.Input;

import java.util.ArrayList;
import java.util.List;

public class Scratchcards {
    private final List<Card> cards;
    public Scratchcards(String input) {
        cards = new ArrayList<>();
        String[] lines = input.split("\n");
        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
            String line = lines[lineIndex];
            var cardNumber = Integer.parseInt(line.split(":")[0].substring(4).replaceAll(" ", ""));
            var winningNumbers = line.split(":")[1].split("\\|")[0].trim();
            var numbers = line.split(":")[1].split("\\|")[1].trim();
            cards.add(new Card(lineIndex, cardNumber, winningNumbers, numbers));
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getTotal() {
        return cards.stream()
                .mapToInt(Card::total)
                .sum();
    }

    public static void main(String[] args) {
        Scratchcards scratchcards = new Scratchcards(Input.TEXT);
        System.out.println(scratchcards.getTotal());
    }


}
