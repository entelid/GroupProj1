
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScriptedCardSource implements CardSource {

    private final List<Card> cards;

    public ScriptedCardSource(List<Card> cards) {
        this.cards = new ArrayList<>(
                Objects.requireNonNull(cards)
        );
    }

    @Override
    public Card draw() {
        if (isEmpty()) {
            throw new IllegalStateException(
                    "No cards remaining"
            );
        }

        return cards.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return cards.isEmpty();
    }
}