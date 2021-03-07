class Hand {

    private val hand: MutableList<Card> = mutableListOf<Card>()

    fun addCard(card: Card) {
        var pos = 0
        if (hand.size > 0) {
            pos = (0..(hand.size - 1)).random()
        }
        hand.add(pos, card)
    }

    fun playCard(): Card {
        var card: Card = hand[0]
        removeCard(card)
        return card
    }

    private fun removeCard(card: Card) {
        hand.remove(card)
    }

    fun show() {
        for (card in hand) {
            println(card.toString())
        }
    }

    fun getSize(): Int = hand.size
}