class War {

    private val NUM_OF_PLAYERS = 2
    private val PLAYER_ONE_POS = 0
    private val PLAYER_TWO_POS = 1
    private val TIE = 2
    private val MAX_ITERATIONS = 3000
    private val players: ArrayList<Player> = ArrayList(NUM_OF_PLAYERS)

    /**
     * Add a player to the current game. There is a
     * maximum size of NUM_OF_PLAYERS for a single game.
     */
    fun addPlayer(player: Player) {
        if (players.size < NUM_OF_PLAYERS) {
            players.add(player)
        } else {
            println("Already two players in the game!")
        }
    }

    /** Start the game and play until someone runs out of cards. */
    fun start() {
        if (!validatePlayers()) return
        var gameOver = false
        var i = 0

        while (!gameOver) {
            val (playerOneCard, playerTwoCard) = playRound()
            evaluateRound(playerOneCard, playerTwoCard)
            gameOver = getGameOverStatus()
            i++
            if (MAX_ITERATIONS == i) gameOver = true
        }

        if (null == getGameWinner()) {
            println("No winner")
            println("Player 1 count: ${players[0].getCardCount()}")
            println("Player 2 count: ${players[1].getCardCount()}")
        } else {
            println("Game winner after $i rounds: ${getGameWinner()!!.getName()}")
        }
    }

    /**
     * Ensure there are not too many or too little
     * players in the game
     *
     * @return Boolean
     *   true: there are enough players
     *   false: there are not enough players or too many
     */
    private fun validatePlayers(): Boolean {
        return if (players.size != NUM_OF_PLAYERS) {
            println("Incorrect number of players: ${players.size}! Need at $NUM_OF_PLAYERS")
            false
        } else {
            true
        }
    }

    /**
     * Play a single round by getting a card from each player.
     *
     * @return Array<Card>
     *   An array of the two cards that were played
     */
    private fun playRound(): Array<Card> {
        val playerOneCard: Card = players[PLAYER_ONE_POS].playCard()
        val playerTwoCard: Card = players[PLAYER_TWO_POS].playCard()
        println("${players[PLAYER_ONE_POS].getName()} played $playerOneCard")
        println("${players[PLAYER_TWO_POS].getName()} played $playerTwoCard")
        return arrayOf(playerOneCard, playerTwoCard)
    }

    private fun evaluateRound(playerOneCard: Card, playerTwoCard: Card) {
        val result = determineRoundStatus(playerOneCard, playerTwoCard)
        if (TIE == result) {
            println("WAR!")
            initiateWar(playerOneCard, playerTwoCard)
        } else {
            println("${players[result].getName()} wins the round!")
            players[result].addCard(playerOneCard)
            players[result].addCard(playerTwoCard)
        }
    }

    /**
     * Evaluate the two cards that were played.
     *
     * @return Int
     *   The position in the `players` array for the player who
     *   won the round (PLAYER_ONE or PLAYER_TWO), or 2 for a tie (TIE).
     */
    private fun determineRoundStatus(playerOneCard: Card, playerTwoCard: Card): Int {
        return when {
            playerOneCard.cardValue > playerTwoCard.cardValue -> PLAYER_ONE_POS
            playerTwoCard.cardValue > playerOneCard.cardValue -> PLAYER_TWO_POS
            else -> TIE
        }
    }

    /**
     * When two cards are equal in value, initiate "war" and
     * keep comparing cards until one value is higher than the
     * other. The player with the higher card gets all cards
     * that were previously played.
     *
     * @param card1: player one's card
     * @param card2: player two's card
     */
    private fun initiateWar(card1: Card, card2: Card) {
        val cards: MutableList<Card> = mutableListOf()
        var winner = false
        var roundResults: Int = -1
        cards.add(card1)
        cards.add(card2)
        while (!winner) {
            val (playerOneCard, playerTwoCard) = playRound()
            roundResults = determineRoundStatus(playerOneCard, playerTwoCard)
            if (roundResults != TIE) winner = true
            cards.add(playerOneCard)
            cards.add(playerTwoCard)
        }
        if (roundResults >= 0 && roundResults < NUM_OF_PLAYERS) {
            println("[!!] ${players[roundResults].getName()} won war!! They get ${cards.size} cards")
            for (card in cards) {
                players[roundResults].addCard(card)
            }
        }
    }

    /**
     * Determine if the game is over by seeing the number of cards
     * left in each players hands.
     *
     * @return Boolean
     *  false: the game is not over
     *  true:  the game is over
     */
    private fun getGameOverStatus(): Boolean {
        return (0 == players[PLAYER_ONE_POS].getCardCount() ||
                0 == players[PLAYER_TWO_POS].getCardCount())
    }

    /**
     * A winner is determined by having all of the cards
     * in the deck (52).
     *
     * @return Player?
     *   If there is a winner, return the `Player` object.
     *   Otherwise return null
     */
    private fun getGameWinner(): Player? {
        return when {
            52 == players[PLAYER_ONE_POS].getCardCount() -> players[PLAYER_ONE_POS]
            52 == players[PLAYER_TWO_POS].getCardCount() -> players[PLAYER_TWO_POS]
            else -> null
        }
    }

}