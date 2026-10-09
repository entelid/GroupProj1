import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ProjectDriver {
    public static void main(String[] args) {

        System.out.println("CARD TOURNAMENT TESTS");

        // TEST 1: TournamentRunner with ScriptedCardSource
        System.out.println("\nTEST 1: ScriptedCardSource");

        Card card1 = new Card(Rank.ACE, Suit.SPADES);
        Card card2 = new Card(Rank.KING, Suit.HEARTS);
        Card card3 = new Card(Rank.QUEEN, Suit.CLUBS);

        List<Card> scriptedCards = List.of(card1, card2, card3);

        CardSource scriptedSource =
                new ScriptedCardSource(scriptedCards);

        TournamentRunner runner = new TournamentRunner();

        List<Card> hand = runner.playRound(scriptedSource, 2);

        System.out.println("Expected hand size: 2");
        System.out.println("Actual hand size: " + hand.size());
        System.out.println("Cards drawn: " + hand);

        check(hand.size() == 2, "Scripted source draws 2 cards");
        check(hand.get(0).equals(card1),
                "First scripted card is drawn first");
        check(hand.get(1).equals(card2),
                "Second scripted card is drawn second");


        // TEST 2: Same runner works with Deck
        System.out.println("\nTEST 2: Deck substitution");

        CardSource deckSource =
                new Deck(CardOrders.BY_RANK_THEN_SUIT);

        List<Card> deckHand = runner.playRound(deckSource, 5);

        System.out.println("Expected hand size: 5");
        System.out.println("Actual hand size: " + deckHand.size());

        check(deckHand.size() == 5,
                "TournamentRunner works with Deck");


        // TEST 3: Drawing from an empty source
        System.out.println("\nTEST 3: Empty source");

        CardSource emptySource = new ScriptedCardSource(
                new ArrayList<>()
        );

        List<Card> emptyHand = runner.playRound(emptySource, 3);

        check(emptyHand.isEmpty(),
                "Empty source produces an empty hand");


        // TEST 4: Hand size cannot exceed available cards
        System.out.println("\nTEST 4: Limited number of cards");

        CardSource smallSource =
                new ScriptedCardSource(List.of(card1, card2));

        List<Card> smallHand = runner.playRound(smallSource, 5);

        check(smallHand.size() == 2,
                "Hand contains only available cards");


        // TEST 5: Sort by suit, then rank
        System.out.println("\nTEST 5: Suit then rank");

        Deck suitDeck = new Deck(CardOrders.BY_SUIT_THEN_RANK);

        suitDeck.push(new Card(Rank.KING, Suit.CLUBS));
        suitDeck.push(new Card(Rank.ACE, Suit.CLUBS));
        suitDeck.push(new Card(Rank.ACE, Suit.HEARTS));

        suitDeck.sort();

        List<Card> suitSorted = new ArrayList<>();

        for (Card card : suitDeck) {
            suitSorted.add(card);
        }

        System.out.println("Suit-sorted cards: " + suitSorted);

        check(
                suitSorted.get(0).suit()
                        .compareTo(suitSorted.get(1).suit()) <= 0,
                "Suit ordering is applied"
        );


        // TEST 6: Sort by rank, then suit
        System.out.println("\nTEST 6: Rank then suit");

        Deck rankDeck = new Deck(CardOrders.BY_RANK_THEN_SUIT);

        rankDeck.push(new Card(Rank.ACE, Suit.SPADES));
        rankDeck.push(new Card(Rank.KING, Suit.CLUBS));
        rankDeck.push(new Card(Rank.ACE, Suit.CLUBS));

        rankDeck.sort();

        List<Card> rankSorted = new ArrayList<>();

        for (Card card : rankDeck) {
            rankSorted.add(card);
        }

        System.out.println("Rank-sorted cards: " + rankSorted);

        check(
                rankSorted.get(0).rank()
                        .compareTo(rankSorted.get(1).rank()) <= 0,
                "Rank ordering is applied"
        );


        // TEST 7: Iterator cannot remove cards
        System.out.println("\nTEST 7: Iterator safety");

        Deck iteratorDeck =
                new Deck(CardOrders.BY_RANK_THEN_SUIT);

        int originalSize = 0;

        for (Card card : iteratorDeck) {
            originalSize++;
        }

        Iterator<Card> iterator = iteratorDeck.iterator();

        boolean removeBlocked = false;

        if (iterator.hasNext()) {
            iterator.next();

            try {
                iterator.remove();
            } catch (UnsupportedOperationException e) {
                removeBlocked = true;
            }
        }

        check(removeBlocked,
                "Iterator remove() is blocked");

        int newSize = 0;

        for (Card card : iteratorDeck) {
            newSize++;
        }

        check(originalSize == newSize,
                "Iterator did not change deck size");


        // TEST 8: Print final result
        System.out.println("\n===== TESTS FINISHED =====");
    }

    private static void check(boolean passed, String description) {
        if (passed) {
            System.out.println("[PASS] " + description);
        } else {
            System.out.println("[FAIL] " + description);
        }
    }

}

