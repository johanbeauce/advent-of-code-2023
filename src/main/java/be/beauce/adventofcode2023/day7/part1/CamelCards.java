package be.beauce.adventofcode2023.day7.part1;

import be.beauce.adventofcode2023.day7.Hand;
import be.beauce.adventofcode2023.day7.Input;

import java.util.ArrayList;
import java.util.List;

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

    protected List<String> transformToListOfString(String cards) {
        var cardList = new ArrayList<String>();
        for (var card : cards.toCharArray()) {
            cardList.add(String.valueOf(card));
        }
        return cardList;
    }

    public long getTotalWinnings() {
        var sortedHands = getHandList();

        long sum = 0;
        long rank = sortedHands.size();
        for (int i = 0; i < sortedHands.size(); i++) {
            sum += sortedHands.get(i).bid() * rank;
            rank--;
        }
        return sum;
    }

    private List<Hand> getHandList() {
        return hands.stream()
                .sorted(Hand::compareTo)
                .toList();
    }

    public static void main(String[] args) {
        // 249390788
        var camelCards = new CamelCards(Input.TEXT);
        System.out.println(camelCards.getTotalWinnings());
    }
}
