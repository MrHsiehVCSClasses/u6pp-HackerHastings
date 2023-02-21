package u6pp;
import java.util.ArrayList;

public class Player {
    String name;
    ArrayList<Card> hand = new ArrayList<Card>();
    public Player(String str){
        name = str;
    }

    public String getName(){
        return name;
    }

    public ArrayList<Card> getHand(){
        return hand;
    }

}
