package com.dreamers.core;

public class Card {

    private final CardSuit cardSuit;
    private final CardRank cardRank;

    public Card(CardSuit cardSuit, CardRank cardRank) {
        this.cardSuit = cardSuit;
        this.cardRank = cardRank;
    }

    public CardRank getCardRank() {
        return cardRank;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    public enum CardSuit {
        DIAMONDS, CLUBS, HEARTS, SPEARS
    }

    public enum CardRank {

        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(10),
        QUEEN(10),
        KING(10),
        ACE(11),
        JOKER(0);

        private final int value;

        CardRank(int value) {
            this.value = value;
        }

        public int getCardValue() {
            return value;
        }
    }

    @Override
    public String toString() {

        char cardsSuit;
        String cardsValue;

        switch (cardSuit) {
            case DIAMONDS:
                cardsSuit = '\u2666';
                break;
            case SPEARS:
                cardsSuit = '\u2663';
                break;
            case CLUBS:
                cardsSuit = '\u2660';
                break;
            case HEARTS:
                cardsSuit = '\u2665';
                break;
            default:
                cardsSuit = '!';
        }

        switch (cardRank) {
            case TWO:
                cardsValue = Integer.toString(2);
                break;
            case THREE:
                cardsValue = Integer.toString(3);
                break;
            case FOUR:
                cardsValue = Integer.toString(4);
                break;
            case FIVE:
                cardsValue = Integer.toString(5);
                break;
            case SIX:
                cardsValue = Integer.toString(6);
                break;
            case SEVEN:
                cardsValue = Integer.toString(7);
                break;
            case EIGHT:
                cardsValue = Integer.toString(8);
                break;
            case NINE:
                cardsValue = Integer.toString(9);
                break;
            case TEN:
                cardsValue = Integer.toString(10);
                break;
            case JACK:
                cardsValue = "J";
                break;
            case QUEEN:
                cardsValue = "Q";
                break;
            case KING:
                cardsValue = "K";
                break;
            case ACE:
                cardsValue = "A";
                break;
            case JOKER:
                cardsValue = "Joker";
                break;
            default:
                cardsValue = "!";
        }

        return cardsValue + cardsSuit;
    }
}
