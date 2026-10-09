import java.util.Comparator;

public final class CardOrders {

    private CardOrders() {
    }

    public static final Comparator<Card> BY_SUIT_THEN_RANK =
            Comparator.comparing(Card::suit).thenComparing(Card::rank);

    public static final Comparator<Card> BY_RANK_THEN_SUIT =
            Comparator.comparing(Card::rank).thenComparing(Card::suit);
}