package be.beauce.adventofcode2023.day7.part2;

import be.beauce.adventofcode2023.day7.Input;
import be.beauce.adventofcode2023.day7.JokerHand;

import java.util.ArrayList;
import java.util.List;

public class CamelCardsPlus{
    private final List<JokerHand> hands;

    public List<JokerHand> getHands() {
        return hands;
    }

    public CamelCardsPlus(String input) {
        this.hands = new ArrayList<>();
        var lines = input.split("\n");
        for (var line : lines) {
            var split = line.split(" ");
            var card = split[0].trim();
            var bid = split[1].trim();
            hands.add(new JokerHand(transformToListOfString(card), Long.parseLong(bid)));
        }
    }

    protected List<String> transformToListOfString(String cards) {
        var cardList = new ArrayList<String>();
        for (var card : cards.toCharArray()) {
            cardList.add(String.valueOf(card));
        }
        return cardList;
    }

    public long getTotalWinnings() {
        var sortedHands = getSortedHands();

        long sum = 0;
        long rank = sortedHands.size();
        for (int i = 0; i < sortedHands.size(); i++) {
            sum += sortedHands.get(i).getBid() * rank;
            rank--;
        }
        return sum;
    }

    public List<JokerHand> getSortedHands() {
        return hands.stream()
                .sorted(JokerHand::compareTo)
                .toList();
    }

    public static void main(String[] args) {
        var camelCardsPlus = new CamelCardsPlus(Input.TEXT);
        System.out.println(camelCardsPlus.getTotalWinnings());
    }
}
