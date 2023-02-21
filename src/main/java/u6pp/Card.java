package u6pp;

public class Card {

    public static String RED = "RED";
    public static String GREEN = "GREEN";
    public static String BLUE = "BLUE";
    public static String YELLOW = "YELLOW";

    public static String ZERO = "0";
    public static String ONE = "1";
    public static String TWO = "2";
    public static String THREE = "3";
    public static String FOUR = "4";
    public static String FIVE = "5";
    public static String SIX = "6";
    public static String SEVEN = "7";
    public static String EIGHT = "8";
    public static String NINE = "9";

    public static String DRAW_2 = "DRAW_2";
    public static String REVERSE = "REVERSE";
    public static String SKIP = "SKIP";
    public static String WILD = "WILD";
    public static String WILD_DRAW_4 = "WILD_DRAW_4";

    // Wild color is the default color for wilds, before they are played. 
    public static String[] COLORS = {RED, GREEN, BLUE, YELLOW, WILD}; 
    public static String[] VALUES = {ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, 
        DRAW_2, REVERSE, SKIP, WILD, WILD_DRAW_4};

    String color;
    String value;

    // start you code here
    public Card(){
        color = "";
        value = "";
    }

    public Card(String color, String value){
        this.color = color;
        this.value = value;
    }

    public String getColor(){
        return this.color;
    }

    public void setColor(String color){
        this.color = color;
    }

    public boolean trySetColor(String color){
        if(this.value == WILD){
            if(color == null || color.equals(WILD)){
                return false;
            }
            boolean bals = false;
            for(String clr : COLORS){
                if(color.equals(clr)){
                    bals = true;
                }
            }
            if(bals == false){
                return false;
            }
            this.color = color;
            return true;
        } else{
            return false;
        }
    }

    public String getValue(){
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean canPlayOn(Card card){
        if(card == null){
            return false;
        }
        if(this.value.equals(WILD) || this.color.equals(WILD)){
            return true;
        }
        
        if(this.color.equals(card.color)){
            return true;
        } else if(this.value.equals(card.value)){
            return true;
        } else if (card.getColor().equals("WILD")){
            return true;
        } else {
            return false;
        }
    }
    
}
