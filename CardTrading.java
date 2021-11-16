import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

class Card {
    String cardType;
    // (buy + sell) * no. of cards.
    int weightedValue;
    int buyValue;
    int sellValue;

    public Card(String cardType, int weightedValue, int buyValue, int sellValue) {
        this.cardType = cardType;
        this.weightedValue = weightedValue;
        this.buyValue = buyValue;
        this.sellValue = sellValue;
    }

    @Override
    public String toString() {
        return cardType;
    }
}

public class CardTrading {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)))) {
            String[] firstLine = br.readLine().split(" ");
            int deckSize = Integer.parseInt(firstLine[0]);
            String[] deck = br.readLine().split(" ");
            HashMap<String, Integer> countMap = countCards(deck, deckSize);
            ArrayList<Card> arr = new ArrayList<>();
            int numOfTypes = Integer.parseInt(firstLine[1]);

            for (int i = 1; i <= numOfTypes; i++) {
                String[] buySell = br.readLine().split(" ");
                int buyVal = Integer.parseInt(buySell[0]);
                int sellVal = Integer.parseInt(buySell[1]);
                String type = String.format("%d", i);
                if (countMap.get(type) == null) {
                    countMap.put(type, 0);
                    int weightedVal = (buyVal * 2);
                    Card ommitedCard = new Card(type, weightedVal, buyVal, sellVal);
                    arr.add(ommitedCard);
                } else if (countMap.get(type) == 2) {
                    int weightedVal = sellVal * 2;
                    Card card = new Card(type, weightedVal, buyVal, sellVal);
                    arr.add(card);
                } else {
                    int weightedVal = (buyVal + sellVal) * countMap.get(type);
                    Card card = new Card(type, weightedVal, buyVal, sellVal);
                    arr.add(card);
                }
            }

            Collections.sort(arr, (x, y) -> {
                if (x.weightedValue < y.weightedValue) {
                    return -1;
                } else {
                    return 1;
                }
            });

            long result = 0;
            long numOfCombos = Long.parseLong(firstLine[2]);
            for (int i = 0; i < arr.size(); i++) {
                if (numOfCombos > 0) {
                    int j = countMap.get(arr.get(i).cardType);
                    int k = 2 - j;
                    result -= arr.get(i).buyValue * k;
                    numOfCombos--;
                } else {
                    result += arr.get(i).sellValue * countMap.get(arr.get(i).cardType);
                }
            }

            out.println(String.format("%d", result));
        } catch (IOException e) {
            System.out.println("error");
        }
    }

    public static HashMap<String, Integer> countCards(String[] deck, int deckSize) {
        HashMap<String, Integer> countMap = new HashMap<>();
        for (int i = 0; i < deckSize; i++) {
            String key = deck[i];
            Integer value = countMap.get(key);
            if (value == null) {
                countMap.put(key, 1);
            } else {
                countMap.put(key, value + 1);
            }
        }
        return countMap;
    }
}