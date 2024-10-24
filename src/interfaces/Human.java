package src.interfaces;

import java.util.List;
import src.card.Card;

public class Human {
    private List<Card> hand;

    public Human(List<Card> hand) {
        this.hand = hand;
    }

    public int calculatePoints() {
        int totalPoints = 0;
        for(Card card:this.hand) {
            totalPoints += card.getRank().getRankvalue();
        }
        return totalPoints % 10;
    }

    public List<Card> getHand() {
        return hand;
    }

    
    
}
