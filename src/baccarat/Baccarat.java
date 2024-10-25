package src.baccarat;

import src.banker.Banker;
import src.card.Card;
import src.card.Deck;
import src.player.Player;

public class Baccarat {
    private Deck deck;
    private final Player player;
    private final Banker banker;
    
    public Baccarat(Deck deck, Player player, Banker banker) {
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
            this.player.getHand().add(this.deck.getDeck().get(i));
            this.banker.getHand().add(this.deck.getDeck().get(j));
        }
        this.deck.setDeck(this.deck.getDeck().subList(j, this.deck.getDeck().size()));
    }

    public Card drawCard(){
        Card card = this.deck.getDeck().get(0);
        this.deck.setDeck(this.deck.getDeck().subList(1, this.deck.getDeck().size()));
        return card;
    }

}
