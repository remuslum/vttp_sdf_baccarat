package src.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> deck;
    private final int numOfDecks;

    public Deck(int numOfDecks) {
        this.deck = new ArrayList<>();
        this.numOfDecks = numOfDecks;
    }

    public void makeDeck(){
        for(int i  = 0; i < numOfDecks; i++){
            for(Rank rank:Rank.values()) {
                for (Suit suit:Suit.values()) {
                    Card card = new Card(suit, rank);
                    deck.add(card);
                }
            }
        }
        
    }

    public List<Card> getDeck(){
        return this.deck;
    }

    public void shuffleDeck(){
        Collections.shuffle(deck);
    }


}
