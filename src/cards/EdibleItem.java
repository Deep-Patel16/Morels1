package cards;

public class EdibleItem extends Card{
    protected int flavourPoints;
    String name;
    CardType type;
    public EdibleItem(CardType type1, String cardName1) {
        super(type1, cardName1);
        this.type = type1;
        this.name = cardName1;
       
    }
    public int getFlavourPoints() {
        if(name.equals("lawyerswig")||name.equals("shiitake")){
            flavourPoints=2;
          }
          else if(name.equals("honeyfungus")|| name.equals("treeear")){
            flavourPoints=1;
          }
          else if (name.equals("henofwoods")||name.equals("birchbolete")|| name.equals("porcini")){
            flavourPoints=3;
      
          }
          else if (name.equals("morel")){
            flavourPoints=6;
          }
          else if (name.equals("chanterelle")){
            flavourPoints=4;
          }
      
          return flavourPoints;
        
    }
}
