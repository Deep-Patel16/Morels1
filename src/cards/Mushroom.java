package cards;


public class Mushroom extends EdibleItem{
    protected int sticksPerMushroom;
    public Mushroom(CardType type1, String cardName1) {
        super(type1, cardName1);
        }
    
    public int  getSticksPerMushroom(){
    if(name.equals("birchbolete")||name.equals("chanterelle")||name.equals("shiitake")|| name.equals("treeear")){
        sticksPerMushroom=2;
      }
      else if(name.equals("honeyfungus")|| name.equals("lawyerswig")||name.equals("henofwoods")){
        sticksPerMushroom=1;
      }
      else if (name.equals("porcini")){
        sticksPerMushroom=3;
  
      }
      else if (name.equals("morel")){
        sticksPerMushroom=4;
      }
  
      return sticksPerMushroom;
    }
}

