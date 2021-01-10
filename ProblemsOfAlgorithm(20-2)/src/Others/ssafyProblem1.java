package Others;

/*
1. 52장의 카드에서 만들수있는 페어가 정확히 하나만 있는 5장 조합을 모두 출력하는 프로그램을 작성하라.
출력이 너무 많으면 카드 수를 줄일수 있다.
 */

/*
[Solve]
전체 경우의 수는 52장의 카드에서 5장을 뽑는 조합이다. 이 중에서 원페어인 경우의 수만 모두 출력한다.
필요한 함수
1) 카드 배열을 만드는 함수
2) DFS를 통해 탐색하는 함수
3) 5개의 카드 셋이 완성되었을 때, 해당 셋이 원페어인지 확인하는 함수

 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ssafyProblem1 {
    private static String[] cards;
    private static int numOfOnePair;

    public static void main(String[] args) {
        makeCards();
        cardsCombination(0, new ArrayList<>());
        System.out.println(numOfOnePair);
    }

    private static void makeCards() {
        int cnt = 0;
        cards = new String[52];
        numOfOnePair = 0;

        for(int i = 0; i < 4; i++) {
            for(int j = 1; j <= 13; j++) {
                switch (i) {
                    case 0:
                        cards[cnt] = "♡";
                        break;
                    case 1:
                        cards[cnt] = "◇";
                        break;
                    case 2:
                        cards[cnt] = "♣";
                        break;
                    case 3:
                        cards[cnt] = "♠";
                        break;
                }
                switch (j) {
                    case 11:
                        cards[cnt] = cards[cnt] + "J";
                        break;
                    case 12:
                        cards[cnt] = cards[cnt] + "Q";
                        break;
                    case 13:
                        cards[cnt] = cards[cnt] + "K";
                        break;
                    default:
                        cards[cnt] = cards[cnt] + j;
                }
                cnt++;
            }
        }
        System.out.println(Arrays.toString(cards));
    }

    private static void cardsCombination(int flag, List<String> cardList) {
        if(cardList.size() == 5 && isOnePair(cardList)) {
            System.out.println(cardList);
            numOfOnePair++;
            return;
        }
        if(flag >= cards.length - 1 || cardList.size() >= 5) {
            return;
        }
        for(int i = flag; i < cards.length; i++) {
            cardList.add(cards[i]);
            cardsCombination(i + 1, cardList);
            cardList.remove(cardList.size() - 1);
        }
    }

    private static boolean isOnePair(List<String> cardList) {
        int numOfPair = 0;
        for(int i = 0; i < cardList.size() - 1; i++) {
            int cnt = 0;
            for(int j = i + 1; j < cardList.size(); j++) {
                if(cardList.get(i).substring(1, 2).equals(cardList.get(j).substring(1, 2)))
                    cnt++;
            }
            if(cnt == 1)
                numOfPair++;
        }
        return numOfPair == 1 ? true : false;
    }
}
