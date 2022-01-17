package nglinjie;

import java.util.ArrayList;

public class Card {

    int cardValue;
    String cardNumber;


    public Card(String cardNumber) {
        this.cardNumber = cardNumber;
        this.cardValue = setCardValue(cardNumber);
    }

    public Card() {

    }

    public int getCardValue(){
        return cardValue;
    }

    public int setCardValue(String cardNumber) {
        char[] charArray = cardNumber.toCharArray();

        switch (charArray[0]) {
            case '2' -> cardValue = 2;
            case '3' -> cardValue = 3;
            case '4' -> cardValue = 4;
            case '5' -> cardValue = 5;
            case '6' -> cardValue = 6;
            case '7' -> cardValue = 7;
            case '8' -> cardValue = 8;
            case '9' -> cardValue = 9;
            case '1' -> cardValue = 10;
            case 'J' -> cardValue = 10;
            case 'Q' -> cardValue = 10;
            case 'K' -> cardValue = 10;
            case 'A' -> cardValue = 11;
        }
        return cardValue;
    }

    private ArrayList<String> cardSuitList(){
        return new ArrayList<>(){{
            add("Spade");
            add("Clubs");
            add("Hearts");
            add("Diamonds");
        }};
    }

    private ArrayList<String> cardNumberList(){
        return new ArrayList<>(){{
            add("2");
            add("3");
            add("4");
            add("5");
            add("6");
            add("7");
            add("8");
            add("9");
            add("10");
            add("J");
            add("Q");
            add("K");
            add("A");
        }};
    }

    public boolean aceTest(){
        char[] charArray = cardNumber.toCharArray();
        return charArray[0] == 'A';
    }

    public ArrayList<Card> addToDeck(){
        return new ArrayList<>(){{
            for (String number: cardNumberList()){
                for (String suit : cardSuitList()){
                    String card = number + "-" + suit;
                    add(new Card(card));
                }
            }
        }};
    }

    @Override
    public String toString() {
        return
//                "cardValue=" + cardValue +
                 cardNumber;
    }
}
