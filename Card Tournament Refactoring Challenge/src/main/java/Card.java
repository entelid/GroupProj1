import java.util.Comparator;

public class Card implements Comparable<Card> {

    private Rank aRank;
    private Suit aSuit;

    public Card(Rank pRank, Suit pSuit) {
        assert pRank != null && pSuit != null;
        aRank = pRank;
        aSuit = pSuit;
    }

    public Rank rank() {
        return aRank;
    }

    public Suit suit() {
        return aSuit;
    }

    @Override
    public int compareTo(Card pCard) {
        return aRank.compareTo(pCard.aRank);
    }

    public static Comparator<Card> createByRankComparator() {
        return new Comparator<Card>() {
            public int compare(Card pCard1, Card pCard2) {
                return pCard1.aRank.compareTo(pCard2.aRank);
            }
        };
    }

    @Override
    public String toString() {
        return String.format("%s of %s", aRank, aSuit);
    }
}

