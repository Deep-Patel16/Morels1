package board;
import java.util.ArrayList;
import cards.*;

public class Board {
    private static CardPile forestCardsPile;
    private static CardList forest;
    private static ArrayList<Card> decayPile;

    public static void initialisePiles(){
        forestCardsPile = new CardPile();
        decayPile = new ArrayList<Card>();
        forest = new CardList();
    }
    
    public static void setUpCards(){ // Added all type of mushrooms according to the required number in each pile
        Card card;
        for (int i = 0;i<10;i++){
            card = new HoneyFungus(CardType.DAYMUSHROOM);
            forestCardsPile.addCard(card);
        }
        card = new HoneyFungus(CardType.NIGHTMUSHROOM);
        forestCardsPile.addCard(card);

        for (int i = 0;i<8;i++){
            card = new TreeEar(CardType.DAYMUSHROOM);
            forestCardsPile.addCard(card);
        }
        card = new TreeEar(CardType.NIGHTMUSHROOM);
        forestCardsPile.addCard(card);

        for (int i = 0;i<5;i++){
            card = new Shiitake(CardType.DAYMUSHROOM);
            forestCardsPile.addCard(card);
        }
        card = new Shiitake(CardType.NIGHTMUSHROOM);
        forestCardsPile.addCard(card);

        for (int i = 0;i<5;i++){
            card = new HenOfWoods(CardType.DAYMUSHROOM);
            forestCardsPile.addCard(card);
        }
        card = new HenOfWoods(CardType.NIGHTMUSHROOM);
        forestCardsPile.addCard(card);

        for (int i = 0;i<6;i++){
            card = new LawyersWig(CardType.DAYMUSHROOM);
            forestCardsPile.addCard(card);
        }
        card = new LawyersWig(CardType.NIGHTMUSHROOM);
        forestCardsPile.addCard(card);

        for (int i = 0;i<4;i++){
            card = new BirchBolete(CardType.DAYMUSHROOM);
            forestCardsPile.addCard(card);
        }
        card = new BirchBolete(CardType.NIGHTMUSHROOM);
        forestCardsPile.addCard(card);

        for (int i = 0; i < 4; i ++){
            card = new Porcini(CardType.DAYMUSHROOM);
            forestCardsPile.addCard(card);
        }
        card = new Porcini(CardType.NIGHTMUSHROOM);
        forestCardsPile.addCard(card);

        for (int i = 0; i < 4; i ++){
            card = new Chanterelle(CardType.DAYMUSHROOM);
            forestCardsPile.addCard(card);
        }
        card = new Chanterelle(CardType.NIGHTMUSHROOM);
        forestCardsPile.addCard(card);

        for (int i = 0; i < 3; i ++){
            card = new Morel(CardType.DAYMUSHROOM);
            forestCardsPile.addCard(card);
        }
        
        for (int i = 0; i < 3; i ++) {
            Butter card1 = new Butter();
            forestCardsPile.addCard(card1);
        }

        for (int i = 0; i < 3; i ++) {
            Cider card1 = new Cider();
            forestCardsPile.addCard(card1);
        }

        for (int i = 0; i < 11; i ++) {
            Pan card1 = new Pan();
            forestCardsPile.addCard(card1);
        }

        for (int i = 0; i < 5; i ++) {
            Basket card1 = new Basket();
            forestCardsPile.addCard(card1);
        }

    }
    public static CardPile getForestCardsPile(){
        return forestCardsPile;
    }
    public static CardList getForest(){
        return forest;
    }
    public static ArrayList<Card> getDecayPile(){
        return decayPile;
    }
    public static void updateDecayPile(){
        if (decayPile.size()<4){ // if decay pile is not full then we remove card from forest and add it to decay pile
            decayPile.add(forest.getElementAt(forest.size()-1));
            getForest().removeCardAt(forest.size()-1);
        }
        else{
        decayPile.clear(); // if decay pile is full then we clear the whole pile and add it to decay pile and remove it from forest
        decayPile.add(forest.getElementAt(forest.size()-1));
        forest.removeCardAt(forest.size()-1);
    }
}
}
