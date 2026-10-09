# GroupProject
Project Overview:

This project refactors a card tournament program to make it more flexible and easier to maintain. The program uses interfaces, polymorphism, the Iterator pattern, and the Strategy pattern to separate responsibilities between classes. The main goal is to allow the tournament to work with different card sources and sorting strategies without changing the tournament algorithm. 

Design Changes: 

Originally, the tournament runner depended directly on the Deck class. This limited the program because it could not easily use other types of card sources.The runner now accepts the CardSource interface. Both Deck and ScriptedCardSource implement this interface. This allows the same tournament method to work with either implementation without changing its code.

The deck originally needed to determine how cards should be sorted. The updated design uses a Comparator<Card> passed into the Deck constructor.The CardOrders class provides two sorting strategies: Sort by suit, then rank. Sort by rank, then suit. This separates the sorting rules from the deck itself and allows new sorting strategies to be added without rewriting the deck's sorting method.

The deck implements Iterable<Card> so its cards can be traversed using an iterator or a for-each loop.The iterator returns an unmodifiable view of the deck. This prevents a caller from using iterator.remove() to remove cards from the deck. However, the view is live, meaning that changes made to the deck can be visible during iteration.

Trade-Offs: 

Using interfaces and comparators makes the program more flexible, but it also introduces additional classes and concepts. For a small card game, this design may seem more complicated than putting everything into one class. However, it becomes more useful when additional card sources or sorting rules are needed.The iterator provides protection against structural changes through the iterator, but it does not make the entire deck immutable. Other methods, such as draw() and shuffle(), can still change the deck.

Rejected Alternative: 

One alternative was to use a SortMode enum and a switch statement inside Deck.sort(). This would allow the deck to select between sorting rules, but every new sorting strategy would require changing the deck's code.

