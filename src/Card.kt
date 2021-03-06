class Card(val cardValue: VALUES, val suit: SUIT) {

    override fun toString(): String {
        return "$cardValue of $suit"
    }

}