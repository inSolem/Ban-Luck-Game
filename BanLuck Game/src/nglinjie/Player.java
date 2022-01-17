package nglinjie;

import java.util.ArrayList;

public class Player {

    ArrayList<Card> cardsOnHand = new ArrayList<>();
    String name;

    public Player(String name) {
        this.name = name;
    }

    public void addCard(Card card){
        cardsOnHand.add(card);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCardsOnHand() {
        return cardsOnHand;
    }

    public int getTotalValue(){
        int total = 0;

        for(Card card: cardsOnHand){
            total += card.getCardValue();
            if(card.aceTest()){
                total += aceFactoring(total);
            }
        }

        return total;
    }

    @Override
    public String toString() {
        return "{" + cardsOnHand + '}';
    }

    private int aceFactoring(int total){
        int minus = 0;
        if (cardsOnHand.size() > 3)
            minus -= 10;
        else if (cardsOnHand.size() == 3)
            if (total > 21)
                minus -= 9;
            else
                minus -= 1;
        return minus;
    }
}
