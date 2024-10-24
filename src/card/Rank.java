package src.card;

public enum Rank {
    ACE("1",1),
    TWO("2",2),
    THREE("3",3),
    FOUR("4",4),
    FIVE("5",5),
    SIX("6",6),
    SEVEN("7",7),
    EIGHT("8",8),
    NINE("9",9),
    TEN("10",10),
    JACK("11",10),
    QUEEN("12",10),
    KING("13",10);

    private final String rankName;
    private final int rankvalue;

    private Rank(String rankName, int rankvalue) {
        this.rankName = rankName;
        this.rankvalue = rankvalue;
    }

    public String getRankName() {
        return rankName;
    }

    public int getRankvalue() {
        return rankvalue;
    }

     

}
