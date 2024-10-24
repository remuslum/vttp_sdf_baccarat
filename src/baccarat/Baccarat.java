package src.baccarat;

import java.util.List;
import src.banker.Banker;
import src.card.Card;
import src.player.Player;

public class Baccarat {
    private List<Card> deck;
    private final Player player;
    private final Banker banker;
    
    public Baccarat(List<Card> deck, Player player, Banker banker) {
        this.deck = deck;
        this.player = player;
        this.banker = banker;
    }

    public void runGame(int betAmount) {
        dealHand();
        if(betAmount > this.player.getBuyIn()) {
            System.out.println("Not enough funds!");
        } else {
            this.player.deductBuyIn(betAmount);
            if (this.player.calculatePoints() == this.banker.calculatePoints()) {
                this.player.getHand().add(drawCard());
                this.banker.getHand().add(drawCard());
            }
            determineWinner(betAmount);
        }
    }

    public void determineWinner(int betAmount){
        int playerPoints = this.player.calculatePoints();
        int bankerPoints = this.banker.calculatePoints();
        if (bankerPoints > playerPoints) {
            System.out.println("Banker wins with " + bankerPoints + " points!");
        } else if (playerPoints > bankerPoints) {
            System.out.println("Player wins with " + playerPoints + " points!");
            this.player.increaseWinnings(betAmount);
            this.player.getCapitalBack(betAmount);
        } else {
            System.out.println("It is a draw");
        }
    }

    public void dealHand() {
        int j = 0;
        for(int i = 0; i < 3; i = i+2){
            j = i + 1;
            this.player.getHand().add(this.deck.get(i));
            this.banker.getHand().add(this.deck.get(j));
        }
        this.deck = this.deck.subList(j, this.deck.size());
    }

    public Card drawCard(){
        Card card = this.deck.get(0);
        this.deck = this.deck.subList(1, this.deck.size());
        return card;
    }

}
