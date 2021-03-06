class Deck {
    private val NUM_OF_CARDS = 52
    private var cardCount: Int = NUM_OF_CARDS
    private var deckCount: Int = 1
    private val deck: MutableList<Card> = mutableListOf()

    init {
        for (cardValue in VALUES.values()) {
            deck.add(Card(cardValue, SUIT.Hearts))
            deck.add(Card(cardValue, SUIT.Diamonds))
            deck.add(Card(cardValue, SUIT.Clubs))
            deck.add(Card(cardValue, SUIT.Spade))
        }
    }

    fun shuffleDeck() {
        deck.shuffle()
    }

    fun dealCards(numOfCards: Int): MutableList<Card> {
        val cards: MutableList<Card> = mutableListOf()
        if (numOfCards > (deck.size)) {
            throw Exception("Not enough cards to deal")
        }
        for (i in 0..(numOfCards - 1)) {
            cards.add(deck[0])
            removeCardFromDeck(deck[0])
        }
        return cards
    }

    private fun removeCardFromDeck(card: Card) {
        deck.remove(card)
    }

    fun getDeck(): MutableList<Card> = deck

    fun getCardCount(): Int = deck.size

    fun addDeck() {
        deckCount++
        cardCount += NUM_OF_CARDS
    }

    fun removeDeck() {
        deckCount--
        cardCount -= NUM_OF_CARDS
    }

}