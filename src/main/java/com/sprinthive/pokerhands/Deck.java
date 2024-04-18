package com.sprinthive.pokerhands;

import com.sprinthive.pokerhands.exception.NotEnoughCardsInDeckException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> cards = new ArrayList<Card>(52);

    public Deck() {
        for (Suit suit : Suit.values()) {
            for (CardRank rank : CardRank.values()) {
                cards.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(cards);
    }

    public synchronized int getNumberOfCards() {
        return cards.size();
    }

    /**
     * @param numberOfCards the number of cards to pick from the deck.
     * @return an array of cards dealt.
     * */
    public synchronized Card[] pick(int numberOfCards) throws NotEnoughCardsInDeckException {
        if(numberOfCards > 52){
            throw new IllegalArgumentException("Number of cards to pick from a deck must be 52 or less.");
        }
        Card[] cardsSelected = new Card[numberOfCards];
        for (int i = 0; i < numberOfCards; i++) {
            cardsSelected[i] = this.cards.remove(0);
        }
        Collections.shuffle(this.cards);
        return cardsSelected;
    }
}
