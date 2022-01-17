package nglinjie;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {

    ArrayList<Card> cards;
    Card card = new Card();

    public CardDeck() {

        this.cards = card.addToDeck();
    }

    public Card drawCard(){
        int min = 0;
        int max = this.cards.size() - 1;
        int i = min + (int)(Math.random() * ((max - min) + 1));
        Card card = cards.get(i);
        removeCard(i);
        return card;
    }

    public void addToDeck(List<Card> returnCards){
        cards.addAll(returnCards);
    }

    private void removeCard(int i){
        cards.remove(i);
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
