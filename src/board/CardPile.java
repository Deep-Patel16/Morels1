package board;
import cards.Card;
import java.util.Stack;
import java.util.Collections;

public class CardPile{

    private Stack<Card> cPile;

    public CardPile(){
        cPile = new Stack<Card>();
    }
    public void addCard(Card card1){
        cPile.add(card1);
    }
    public Card drawCard(){
        return cPile.pop();
    }
    public void shufflePile(){
        Collections.shuffle(cPile);
    }
    public int pileSize(){
        return cPile.size();
    }
    public boolean isEmpty(){
        return cPile.isEmpty();
    }
}