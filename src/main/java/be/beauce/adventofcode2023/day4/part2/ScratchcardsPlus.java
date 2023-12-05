package be.beauce.adventofcode2023.day4.part2;

import be.beauce.adventofcode2023.day4.Input;
import be.beauce.adventofcode2023.day4.part1.Scratchcards;

public class ScratchcardsPlus extends Scratchcards {
    public ScratchcardsPlus(String input) {
        super(input);
    }

    public int getTotal() {
        var cardInstance = 0;
        for (int i = 0; i < getCards().size(); i++) {
            cardInstance += getTotalWithChildren(i);
        }
        return cardInstance;
    }

    private int getTotalWithChildren(int index) {
        var card = getCards().get(index);
        var cardInstance = 1;
        var match = card.match();
        for (int i = match; i > 0; i--) {
            var childrenIndex = index + i;
            cardInstance = cardInstance + getTotalWithChildren(childrenIndex);
        }
        return cardInstance;
    }

    public static void main(String[] args) {
        ScratchcardsPlus scratchcards = new ScratchcardsPlus(Input.TEXT);
        System.out.println(scratchcards.getTotal());
    }
}
