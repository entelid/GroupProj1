import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Deck implements CardSource, Iterable<Card> {

    private List<Card> aCards = new ArrayList<>();

    public Deck() {
        shuffle();
    }

    public void shuffle() {
        aCards.clear();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                aCards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(aCards);
    }

    public void push(Card pCard) {
        assert pCard != null;
        aCards.add(pCard);
    }

    public Card draw() {
        assert !isEmpty();
        return aCards.remove(aCards.size() - 1);
    }

    public boolean isEmpty() {
        return aCards.isEmpty();
    }

    /*public List<Card> getCards() {
        return Collections.unmodifiableList(aCards);
    }

    public void sort(SortMode mode) {
        Collections.sort(aCards, new Comparator<Card>() {

        });
    }*/

    public void printDeck()
    {
        Iterator<Card> it = aCards.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }

    @Override
    public Iterator<Card> iterator() {
        return aCards.iterator();
    }
}
