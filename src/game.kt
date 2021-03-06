
fun main(args: Array<String>) {

    val player1 = Player("steph")
    val player2 = Player("joules")

    val deck = Deck()
    deck.shuffleDeck()
    player1.setHand(deck.dealCards(26))
    player2.setHand(deck.dealCards(26))

    val war = War()
    war.addPlayer(player1)
    war.addPlayer(player2)

    war.start()

}