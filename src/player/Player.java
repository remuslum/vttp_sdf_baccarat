package src.player;

import java.util.List;
import src.card.Card;
import src.interfaces.Human;

public class Player extends Human{
    private final String name;
    private int buyIn;
    private List<Card> hand;
    private int winnings;

    public Player(String name, int buyIn, List<Card> hand) {
        super(hand);
        this.name = name;
        this.buyIn = buyIn;
    }

    public void increaseWinnings(int amount) {
        this.winnings += amount;
    }

    public void deductBuyIn(int betAmount) {
        this.buyIn -= betAmount;
    }

    public void getCapitalBack(int initialAmount) {
        this.buyIn += initialAmount;
    }

    public int getBuyIn() {
        return buyIn;
    }

    public int getWinnings() {
        return winnings;
    }
    
    
}
