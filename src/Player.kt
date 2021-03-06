class Player constructor(val name: String) {

    private val hand: Hand

    init {
        hand = Hand()
    }

    fun addCard(card: Card) {
        hand.addCard(card)
    }

    fun setHand(cards: MutableList<Card>) {
        for (card in cards) {
            hand.addCard(card)
        }
    }

    fun showHand() {
        hand.show()
    }

    fun playCard(): Card {
        return hand.playCard()
    }

    fun getCardCount(): Int {
        return hand.getSize()
    }

    @JvmName("getName1")
    fun getName(): String = name

}