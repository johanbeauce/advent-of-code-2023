package be.beauce.adventofcode2023.day7.part1;

import java.util.ArrayList;
import java.util.List;

import static be.beauce.adventofcode2023.day7.part1.Input.TEXT;

public class CamelCards {
    private final List<Hand> hands;

    public List<Hand> getHands() {
        return hands;
    }

    public CamelCards(String input) {
        this.hands = new ArrayList<>();
        var lines = input.split("\n");
        for (var line : lines) {
            var split = line.split(" ");
            var card = split[0].trim();
            var bid = split[1].trim();
            hands.add(new Hand(transformToListOfString(card), Long.parseLong(bid)));
        }
    }

    private List<String> transformToListOfString(String cards) {
        var cardList = new ArrayList<String>();
        for (var card : cards.toCharArray()) {
            cardList.add(String.valueOf(card));
        }
        return cardList;
    }

    public long getTotalWinnings() {
        var sortedHands = hands.stream()
                .sorted(Hand::compareTo)
                .toList();

        long sum = 0;
        long rank = sortedHands.size();
        for (int i = 0; i < sortedHands.size(); i++) {
            sum += sortedHands.get(i).bid() * rank;
            rank--;
        }
        return sum;
    }

    public static void main(String[] args) {
        var camelCards = new CamelCards(TEXT);
        System.out.println(camelCards.getTotalWinnings());
    }


}
