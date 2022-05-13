package board;
import java.util.ArrayList;
import cards.*;

public class Player {
    private Hand h;
    private Display d;
    private int score;
    private int handlimit;
    private int sticks;

    public Player(){
        d = new Display();
        Pan pan = new Pan();
        d.add(pan);
        h= new Hand();
        score = 0;
        handlimit = 8;
        sticks=0;
        }
    public int getScore(){
        return score;
    }
    public int getHandLimit(){
        return handlimit;
    }
    public int getStickNumber(){
        return sticks;
    }
    public void addSticks(int number){
        this.sticks+=number;
        Stick stick = new Stick();
        for (int i=0; i<number;i++){
            d.add(stick);
        }
    }
    public void removeSticks(int number){
        this.sticks-=number;
        for (int i=0;i<number;i++){
            for (int k=0;k<d.size();k++){
                if (d.getElementAt(k).getType()==CardType.STICK){
                    d.removeElement(k);
                    break;
                }
            }
        }
    }
    public Hand getHand(){
        return this.h;
    }
    public Display getDisplay(){
        return this.d;
    }
    public void addCardtoHand(Card card){
        if (card.getType()==CardType.BASKET){
            this.handlimit +=2;
            addCardtoDisplay(card);
        } 
        else{
        this.h.add(card);}
    }
    public void addCardtoDisplay(Card card){
        this.d.add(card);
    }

    public boolean takeCardFromTheForest(int i){
        int sticks_required = 0;
        if (i<=2){
            sticks_required = 0;
        }
        else{
            sticks_required = i -2;
        }
        boolean check1 = false;
        boolean check2 = false;
        if (h.size()+1<=getHandLimit()){
            check1 = true;
        }
        if(getStickNumber()>=sticks_required){
            check2 = true;
        }
        if (check1 & check2){ 
            if (Board.getForest().getElementAt(Board.getForest().size()-i).getType()!=CardType.BASKET){
                addCardtoHand(Board.getForest().getElementAt(Board.getForest().size()-i));
                Board.getForest().removeCardAt(Board.getForest().size()-i);
            }
            else{
                addCardtoDisplay(Board.getForest().getElementAt(Board.getForest().size()-i));
                Board.getForest().removeCardAt(Board.getForest().size()-i);
                handlimit+=2;
            }
            removeSticks(sticks_required);
            return true;
        }
        return false;
                   
}

public boolean takeFromDecay(){
    return false; // add false so that the this actions don't give errors when testing takeCardFromTheForest
}

public boolean cookMushrooms(ArrayList<Card> deck){
    return false; // add false so that the this actions don't give errors when testing takeCardFromTheForest
}
    
    


public boolean sellMushrooms(String cardname,int number_of_mushrooms){
       return false; // add false so that the this actions don't give errors when testing takeCardFromTheForest
    }
    

public boolean putPanDown(){
        return false; // add false so that the this actions don't give errors when testing takeCardFromTheForest
    
    }
}

