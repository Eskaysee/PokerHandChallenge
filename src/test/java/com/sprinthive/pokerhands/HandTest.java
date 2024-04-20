package com.sprinthive.pokerhands;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HandTest {

    @Test
    public void testInvalidHandRank() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.ACE, Suit.CLUBS));
        cards.add(new Card(CardRank.KING, Suit.CLUBS));
        Hand handTwo = new Hand(cards);
        assertEquals("Can not rank a hand with 2 card(s).", handTwo.describeHandRank());
        cards.add(new Card(CardRank.TWO, Suit.CLUBS));
        cards.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        cards.add(new Card(CardRank.JACK, Suit.CLUBS));
        cards.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand handSix = new Hand(cards);
        assertEquals("Can not rank a hand with 6 card(s).", handSix.describeHandRank());
        assertEquals(0, handTwo.compareTo(handSix));
    }

    @Test
    public void testHandRankRoyalFlush() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.ACE, Suit.CLUBS));
        cards.add(new Card(CardRank.KING, Suit.CLUBS));
        cards.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        cards.add(new Card(CardRank.JACK, Suit.CLUBS));
        cards.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("Royal flush of clubs", hand.describeHandRank());
    }

    @Test
    public void testHandRankStraightAceHigh() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.ACE, Suit.CLUBS));
        cards.add(new Card(CardRank.KING, Suit.CLUBS));
        cards.add(new Card(CardRank.QUEEN, Suit.HEARTS));
        cards.add(new Card(CardRank.JACK, Suit.CLUBS));
        cards.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("Straight, ace high", hand.describeHandRank());
    }


    @Test
    public void testHandRankStraightFlush() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.NINE, Suit.CLUBS));
        cards.add(new Card(CardRank.KING, Suit.CLUBS));
        cards.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        cards.add(new Card(CardRank.JACK, Suit.CLUBS));
        cards.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("Straight flush, king high", hand.describeHandRank());
    }

    @Test
    public void testHandRank4OfAKind() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.NINE, Suit.CLUBS));
        cards.add(new Card(CardRank.NINE, Suit.HEARTS));
        cards.add(new Card(CardRank.NINE, Suit.DIAMONDS));
        cards.add(new Card(CardRank.NINE, Suit.SPADES));
        cards.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("4 of a kind of nines", hand.describeHandRank());
    }

    @Test
    public void testHandRankFullHouse() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.NINE, Suit.CLUBS));
        cards.add(new Card(CardRank.NINE, Suit.HEARTS));
        cards.add(new Card(CardRank.NINE, Suit.DIAMONDS));
        cards.add(new Card(CardRank.TEN, Suit.SPADES));
        cards.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("Full house, nines over tens", hand.describeHandRank());
    }

    @Test
    public void testHandRankFlush() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.NINE, Suit.CLUBS));
        cards.add(new Card(CardRank.THREE, Suit.CLUBS));
        cards.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        cards.add(new Card(CardRank.JACK, Suit.CLUBS));
        cards.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("Flush, queen high", hand.describeHandRank());
    }

    @Test
    public void testHandRankStraight() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.NINE, Suit.CLUBS));
        cards.add(new Card(CardRank.KING, Suit.HEARTS));
        cards.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        cards.add(new Card(CardRank.JACK, Suit.CLUBS));
        cards.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("Straight, king high", hand.describeHandRank());
    }

    @Test
    public void testHandRank3OfAKind() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.NINE, Suit.CLUBS));
        cards.add(new Card(CardRank.NINE, Suit.HEARTS));
        cards.add(new Card(CardRank.NINE, Suit.DIAMONDS));
        cards.add(new Card(CardRank.TEN, Suit.SPADES));
        cards.add(new Card(CardRank.TWO, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("3 of a kind of nines", hand.describeHandRank());
    }

    @Test
    public void testHandRank2Pairs() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.NINE, Suit.CLUBS));
        cards.add(new Card(CardRank.NINE, Suit.HEARTS));
        cards.add(new Card(CardRank.TEN, Suit.DIAMONDS));
        cards.add(new Card(CardRank.TEN, Suit.SPADES));
        cards.add(new Card(CardRank.TWO, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("Two pair, tens and nines", hand.describeHandRank());
    }

    @Test
    public void testHandRank1Pairs() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.NINE, Suit.CLUBS));
        cards.add(new Card(CardRank.NINE, Suit.HEARTS));
        cards.add(new Card(CardRank.TEN, Suit.DIAMONDS));
        cards.add(new Card(CardRank.SIX, Suit.SPADES));
        cards.add(new Card(CardRank.TWO, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("One pair of nines", hand.describeHandRank());
    }

    @Test
    public void testHandRankHighCard() {
        ArrayList<Card> cards = new ArrayList<Card>(5);
        cards.add(new Card(CardRank.THREE, Suit.CLUBS));
        cards.add(new Card(CardRank.NINE, Suit.HEARTS));
        cards.add(new Card(CardRank.TEN, Suit.DIAMONDS));
        cards.add(new Card(CardRank.SIX, Suit.SPADES));
        cards.add(new Card(CardRank.TWO, Suit.CLUBS));
        Hand hand = new Hand(cards);
        assertEquals("High card ten of diamonds", hand.describeHandRank());
    }

    @Test
    public void testCompareFlushToStraight() {
        ArrayList<Card> flushList = new ArrayList<Card>(5);
        flushList.add(new Card(CardRank.NINE, Suit.CLUBS));
        flushList.add(new Card(CardRank.THREE, Suit.CLUBS));
        flushList.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        flushList.add(new Card(CardRank.JACK, Suit.CLUBS));
        flushList.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand flushHand = new Hand(flushList);
        assertEquals("Flush, queen high", flushHand.describeHandRank());
        ArrayList<Card> straightList = new ArrayList<Card>(5);
        straightList.add(new Card(CardRank.NINE, Suit.CLUBS));
        straightList.add(new Card(CardRank.KING, Suit.HEARTS));
        straightList.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        straightList.add(new Card(CardRank.JACK, Suit.CLUBS));
        straightList.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand straightHand = new Hand(straightList);
        assertEquals("Straight, king high", straightHand.describeHandRank());
        assertTrue(flushHand.compareTo(straightHand) > 0);
    }

    @Test
    public void testCompareEqualStraights() {
        ArrayList<Card> lowStraightList = new ArrayList<Card>(5);
        lowStraightList.add(new Card(CardRank.NINE, Suit.SPADES));
        lowStraightList.add(new Card(CardRank.EIGHT, Suit.DIAMONDS));
        lowStraightList.add(new Card(CardRank.QUEEN, Suit.SPADES));
        lowStraightList.add(new Card(CardRank.JACK, Suit.SPADES));
        lowStraightList.add(new Card(CardRank.TEN, Suit.HEARTS));
        Hand lowStraightHand = new Hand(lowStraightList);
        assertEquals("Straight, queen high", lowStraightHand.describeHandRank());
        ArrayList<Card> highStraightList = new ArrayList<Card>(5);
        highStraightList.add(new Card(CardRank.NINE, Suit.CLUBS));
        highStraightList.add(new Card(CardRank.EIGHT, Suit.HEARTS));
        highStraightList.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        highStraightList.add(new Card(CardRank.JACK, Suit.CLUBS));
        highStraightList.add(new Card(CardRank.TEN, Suit.CLUBS));
        Hand highStraightHand = new Hand(highStraightList);
        assertEquals("Straight, queen high", highStraightHand.describeHandRank());
        assertFalse(lowStraightHand.compareTo(highStraightHand) != 0);
    }

    @Test
    public void testCompare2PairKicker() {
        ArrayList<Card> twoPair1 = new ArrayList<>(5);
        twoPair1.add(new Card(CardRank.NINE, Suit.CLUBS));
        twoPair1.add(new Card(CardRank.EIGHT, Suit.HEARTS));
        twoPair1.add(new Card(CardRank.TEN, Suit.DIAMONDS));
        twoPair1.add(new Card(CardRank.TEN, Suit.SPADES));
        twoPair1.add(new Card(CardRank.EIGHT, Suit.CLUBS));
        Hand first2Pair = new Hand(twoPair1);
        assertEquals("Two pair, tens and eights", first2Pair.describeHandRank());

        ArrayList<Card> twoPair2 = new ArrayList<>(5);
        twoPair2.add(new Card(CardRank.QUEEN, Suit.CLUBS));
        twoPair2.add(new Card(CardRank.EIGHT, Suit.DIAMONDS));
        twoPair2.add(new Card(CardRank.TEN, Suit.HEARTS));
        twoPair2.add(new Card(CardRank.TEN, Suit.CLUBS));
        twoPair2.add(new Card(CardRank.EIGHT, Suit.SPADES));
        Hand second2Pair = new Hand(twoPair2);
        assertEquals("Two pair, tens and eights", second2Pair.describeHandRank());

        assertTrue(first2Pair.compareTo(second2Pair) < 0);
    }
}
