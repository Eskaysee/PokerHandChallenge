package com.sprinthive.pokerhands.handrank;

import com.sprinthive.pokerhands.Card;
import com.sprinthive.pokerhands.CardRank;

import java.util.Collections;
import java.util.List;

public class HandRankerImpl implements HandRanker {

    /**
     * @param cards list of cards to be ranked
     * @return the best hand rank for the given cards
     * */
    @Override
    public HandRank findBestHandRank(List<Card> cards) {
        //preliminary steps
        if (cards.size() != 5) {
            return new NotRankableHandRanker(cards);
        }
        Collections.sort(cards);
        Collections.reverse(cards);
        boolean flush = true;
        boolean straight = true;
        CardRank rank1 = cards.get(0).getRank();
        CardRank rank2=null, kicker=null;
        //qty1 quantity of a particular card in the hand
        int qty1 = 1, qty2 = 0;     //in the case of full house or 2 pair, qty1 is
                                    //associated with rank2 and qty2 associated with rank1

        //gathering the details about the hand: flush, straight & rank repetitions
        for (int i = 1; i < cards.size(); i++) {
            Card currentCard = cards.get(i);
            Card prevCard = cards.get(i-1);
            if (flush && currentCard.getSuit() != cards.get(0).getSuit()) {
                flush = false;
            }
            if (straight && currentCard.compareTo(prevCard) != -1) {
                straight = false;
            }

            if (currentCard.getRank() == prevCard.getRank()) {
                qty1++;
            } else if (qty1 > 1 && i != 4) {
                qty2 = qty1;
                rank1 = prevCard.getRank();
                rank2 = currentCard.getRank();
                qty1 = 1;
            } else {
                if (qty1 == 1) {
                    kicker = prevCard.getRank();
                    rank2 = currentCard.getRank();
                } else {
                    kicker = currentCard.getRank();
                }
            }
        }

        //Determining card strength based on the details gathered above
        if (flush && straight) {
            if (rank1 == CardRank.ACE) {
                return new RoyalFlushHandRank(cards.get(0).getSuit());
            }
            return new StraightFlushHandRank(rank1);
        } else if (qty1 == 4) {                 //4 of a kind
            if (rank1 == kicker) {
                rank1 = rank2;
            }
            return new FourOfAKindHandRank(rank1);
        } else if (qty1 + qty2 == 5) {            //full house
            if (qty1>qty2) {
                return new FullHouseHandRank(rank2, rank1);
            }
            return new FullHouseHandRank(rank1, rank2);
        } else if (flush) {
            return new FlushHandRank(cards);
        } else if (straight) {
            return new StraightHandRank(rank1);
        } else if (qty1 == 3) {                         //3 of a kind
            return new ThreeOfAKindHandRank(rank2);
        } else if (qty2 == 3) {
            return new ThreeOfAKindHandRank(rank1);
        } else if (qty1 == qty2 && qty1 == 2){            //2 pairs
            if (rank2.getValue() > rank1.getValue()) {
                return new TwoPairHandRank(rank2, rank1, kicker);
            }
            return new TwoPairHandRank(rank1, rank2, kicker);
        } else if (qty1 == 2 || qty2 == 2) {           //1 pair
            CardRank finalRank;
            if (qty1 == 2) {
                finalRank = rank2;
            } else {
                finalRank = rank1;
            }
            cards.removeIf(card -> card.getRank() == finalRank);
            return new OnePairHandRank(finalRank, cards.stream().map(Card::getRank).toList());
        }
        return new HighCardHandRank(cards);
    }
}
