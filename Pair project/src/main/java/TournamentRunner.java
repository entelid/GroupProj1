import java.util.ArrayList;
import java.util.List;
public final class TournamentRunner {
    public List<Card> playRound(Deck deck, int handSize) {
        var hand = new ArrayList<Card>();
        while (!deck.isEmpty() && hand.size() < handSize) {
            hand.add(deck.draw());
        }
        return hand;
    }


    public List<Card> playRound(CardSource source, int handSize) {
        var hand = new ArrayList<Card>();

        while (!source.isEmpty() && hand.size() < handSize) {
            hand.add(source.draw());
        }
        return hand;
    }
}

