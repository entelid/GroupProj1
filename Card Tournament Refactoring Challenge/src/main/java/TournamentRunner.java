import java.util.ArrayList;
import java.util.List;
public final class TournamentRunner {
    public List<Card> playRound(CardSource source, int handSize) {
        var hand = new ArrayList<Card>();

        while (!source.isEmpty() && hand.size() < handSize) {
            hand.add(source.draw());
        }
        return hand;
    }
}

