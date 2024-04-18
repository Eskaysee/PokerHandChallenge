package com.sprinthive.pokerhands.handrank;

import com.sprinthive.pokerhands.Card;
import com.sprinthive.pokerhands.CardRank;

import java.util.Collections;
import java.util.List;

public class HandRankerImpl implements HandRanker {
    @Override
    public HandRank findBestHandRank(List<Card> cards) {
        if (cards.size() != 5) {
            return new NotRankableHandRanker(cards);
        }
        Collections.sort(cards);
        Collections.reverse(cards);
        boolean flush = true;
        boolean straight = true;
        CardRank hpair, lpair;
        CardRank rank = cards.get(0).getRank();
        int matches1 = 0, matches2 = 0;
        //gathering the details about the hand: flush, straight & rank repetitions
        for (int i = 1; i < cards.size(); i++) {
            if (cards.get(i).getSuit() != cards.get(0).getSuit()) {
                flush = false;
            }
            if (cards.get(i).getRank().compareTo(cards.get(i-1).getRank()) != -1) {
                int res = cards.get(i).getRank().compareTo(cards.get(i-1).getRank());
                straight = false;
            }
            if (cards.get(i).getRank() == cards.get(i-1).getRank()) {
                matches1++;
            } else if (matches1 > 0 && i != 4) {
                matches2 = matches1;
                matches1 = 0;
            }
        }
        //Determining card strength based on the details gathered above
        if (flush) {
            if (straight) {
                if (rank.equals(CardRank.ACE)) {
                    return new RoyalFlushHandRank(cards.get(0).getSuit());
                }
                return new StraightFlushHandRank(rank);
            }
            return new FlushHandRank(cards);
        } else if (straight) {
            return new StraightHandRank(rank);
        }
        return new HighCardHandRank(cards);
    }
}
