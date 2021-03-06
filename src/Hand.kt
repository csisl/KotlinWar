class Hand {

    private val hand: MutableList<Card> = mutableListOf<Card>()

    fun addCard(card: Card) {
        hand.add(card)
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