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
    int count = 0;
    boolean check = false;
    for (Card i: Board.getDecayPile()){  // checks if basket is there in the decay file
        if (i.getType() == CardType.BASKET){
            this.handlimit+=2;
            count=count+1;
        }
    }
    for (int i=0; i<h.size(); i++){ // removes basket from hand and adds it to display 
        if (h.getElementAt(i).getType()==CardType.BASKET){
            this.handlimit+=2;
            addCardtoDisplay(h.getElementAt(i));
            h.removeElement(i);
        }
    }

    if(h.size()+(Board.getDecayPile().size()-count)<=getHandLimit()){
        check=true;
    }

    if (check){
        for (Card i: Board.getDecayPile()){ // adds basket from decay file to display and other cards to hand if handlimit is greater than total cards
            if (i.getType() ==CardType.BASKET){
                addCardtoDisplay(i);
            }
            else{
                addCardtoHand(i);
            }
        }
        Board.getDecayPile().clear();
        return true;
    }
    else{
    return false;
    } }

public boolean cookMushrooms(ArrayList<Card> deck){
    boolean pan_f = false;
    for (int i =0;i<deck.size();i++) { //this checks if pan is there in the deck then we remove it from hand
        if (deck.get(i).getType() == CardType.PAN){
            pan_f = true;
            deck.remove(deck.get(i));
            for (int j=0;j<h.size();j++) {
                if (h.getElementAt(j).getType() == CardType.PAN){
                    h.removeElement(j);
                }
            }
        }
    }
    for (int i=0;i<this.d.size();i++) { // checks if there is a pan in the display then it is removed from the display
        if (d.getElementAt(i).getType() == CardType.PAN){
            pan_f = true;
            d.removeElement(i);
        }
    }
     if (!pan_f){
        return false;

     }
    
    int number_of_mushrooms = 0;
    int cider_num = 0;
    int butter_num = 0;
    for (int i = 0;i<deck.size();i++){ // checks if cider is there in the deck and removes from hand if cider is present
        if (deck.get(i).getType() == CardType.CIDER){
            deck.remove(deck.get(i));
            cider_num=cider_num+1;
            i--;
            for (int j=0;j<h.size();j++) {
                if (h.getElementAt(j).getType() == CardType.CIDER){
                    h.removeElement(j);
                }
            }
        }
        else if (deck.get(i).getType() == CardType.BUTTER){ // check if butter is there in the deck and removes from hand if butter is present
            deck.remove(deck.get(i));
            butter_num=butter_num+1;
            i--;
            for (int j=0;j<h.size();j++) {
                if (h.getElementAt(j).getType() == CardType.BUTTER){
                    h.removeElement(j);
                }
            }
        }
    }

   
    if (deck.isEmpty()){
        return false;
    }
    String cardcheck=deck.get(0).getName(); // this check if all the mushrooms are of same type.
    for (Card c : deck){
        if (c.getName().equals(cardcheck)){
            
            continue;
        }
        else{
            return false;
        }}


    for (Card i:deck){  // checks the type of daymushrooms and nightmushrooms in the deck
        if (i.getType() == CardType.DAYMUSHROOM){
            number_of_mushrooms =number_of_mushrooms+1;
        }
        else if (i.getType() == CardType.NIGHTMUSHROOM){
            number_of_mushrooms = number_of_mushrooms+2;
        }
    }

    EdibleItem ed =new EdibleItem(CardType.DAYMUSHROOM, cardcheck);
    if (cider_num>0 && butter_num>0){ // this is implemented if there is both cider and butter in the deck
        int mushroom_required = cider_num*5 + butter_num*4;
        if (number_of_mushrooms>=mushroom_required){
            //ed = new EdibleItem(CardType.DAYMUSHROOM,deck.get(0).getName());
            score=ed.getFlavourPoints()*number_of_mushrooms+ cider_num*5+ butter_num*3; // score is updated
            for (int j=0;j<deck.size();j++){ // removes the mushrooms from the hand
                for (int i=0;i<h.size();i++){
                    if (h.getElementAt(i).getName().equals(deck.get(j).getName())){
                        h.removeElement(i);
                        addCardtoDisplay(h.getElementAt(i));
                    }
                }
            }
            return true;
        }
        else{
            return false;
        }
    }
    else if (cider_num==0 && butter_num==0){ // this is implemented if there no cider and butter present in the deck
        if (number_of_mushrooms>=3){
           // ed = new EdibleItem(CardType.DAYMUSHROOM,deck.get(0)getName());
            score += (ed.getFlavourPoints())* number_of_mushrooms;
            for (int j=0;j<deck.size();j++){
                for (int i=0;i<h.size();i++){
                    if (h.getElementAt(i).getName().equals(deck.get(j).getName())){
                        h.removeElement(i);
                        addCardtoDisplay(h.getElementAt(i));
                    }
                }
            }
            return true;
        }
        else{
            return false;
        }}
    else{ // this implemented if there is only one cider or one butter
        int mushroom_required = cider_num*5 + butter_num*4 ;
        if (number_of_mushrooms>=mushroom_required){
          //  ed = new EdibleItem(CardType.DAYMUSHROOM,deck.get(0).getName());
            score += (ed.getFlavourPoints())* number_of_mushrooms + cider_num*5 +butter_num*3;
            for (int j=0;j<deck.size();j++){
                for (int i=0;i<h.size();i++){
                    if (h.getElementAt(i).getName().equals(deck.get(j).getName())){
                        h.removeElement(i);
                        addCardtoDisplay(h.getElementAt(i));
                    }
                }
            }
            return true;
        }
        else{
            return false;
        }
    }
}
    
    


public boolean sellMushrooms(String cardname,int number_of_mushrooms){
    boolean check=false;
    if(number_of_mushrooms>=2){
    String str="";
    for(int i=0;i<cardname.length();i++) {
        if(cardname.charAt(i)!=' '){
            str=str+cardname.charAt(i);
        }
    
}
    Mushroom mushroom1;
    int count = 0;
    int sticks =0;
    int i =0 ;
    int day_mushrooms = 0;
    int night_mushrooms = 0;
    while(i<h.size()){
        if(count==number_of_mushrooms){
            check = true;
            break;
        }
        else{
            if (h.getElementAt(i).getName().equals(str.toLowerCase())){
                if (h.getElementAt(i).getType() == CardType.NIGHTMUSHROOM){
                    if (count+2 - day_mushrooms == number_of_mushrooms){
                        count -= day_mushrooms;
                        for (int k =0;k<day_mushrooms;k++){
                            addCardtoHand(new Mushroom(CardType.DAYMUSHROOM,h.getElementAt(i).getName()));
                        }
                        mushroom1 = new Mushroom(h.getElementAt(i).getType(),h.getElementAt(i).getName());
                        sticks = sticks + (mushroom1.getSticksPerMushroom())*2;
                        night_mushrooms+=2;
                        count=count+2;
                        h.removeElement(i);
                    }
                    else if (count+2>number_of_mushrooms){
                        i=i+1;
                    }
                    else{
                        mushroom1  = new Mushroom(h.getElementAt(i).getType(),h.getElementAt(i).getName());
                        sticks = sticks + (mushroom1.getSticksPerMushroom())*2;
                        night_mushrooms+=2;
                        count= count+2;
                        h.removeElement(i);                        
                    }

                }
            else if (h.getElementAt(i).getType()==CardType.DAYMUSHROOM){
                if(count+1>number_of_mushrooms){
                    i++;
                }
                else{
                    mushroom1 = new Mushroom(h.getElementAt(i).getType(),h.getElementAt(i).getName());
                    sticks = sticks+mushroom1.getSticksPerMushroom();
                    count=count+1;
                    day_mushrooms+=1;
                    h.removeElement(i);
                }   
            }
        else{
            check = false;
            break;
        }
        continue;
            }
            else{
                i++;
            }
        }
    }
        if (count==number_of_mushrooms){
            addSticks(sticks);
            check = true;}
        }
        return check;
    }
    

public boolean putPanDown(){
    for (int i=0;i<h.size();i++){ // checks if card is there in hand and adds it to display and removes it from hand
        if (h.getElementAt(i).getType()==CardType.PAN){
            addCardtoDisplay(h.getElementAt(i));
            h.removeElement(i);
            return true;
        }
        
        }
    return false;
}
    
    
}

