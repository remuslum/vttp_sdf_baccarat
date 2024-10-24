package src.card;

public enum Suit {
    HEARTS(1),
    DIAMOND(2),
    SPADES(3),
    CLUBS(4);

    private final int suitValue;

    private Suit(int suitValue){
        this.suitValue = suitValue;
    }

    public int getSuitValue() {
        return suitValue;
    }

}
