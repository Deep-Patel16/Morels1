package board;
import java.util.ArrayList;
import cards.Card;

public class CardList {
    private ArrayList<Card> cList;

    public CardList(){
        cList = new ArrayList<Card>();
    }
    public void add(Card card){
        cList.add(card);
    }
    public int size(){
        return cList.size();
    }
    public Card getElementAt(int index){
        return cList.get(index);
    }
    public Card removeCardAt(int index){
        Card card = cList.get(index);
        cList.remove(index);
        return card;
    }
}
