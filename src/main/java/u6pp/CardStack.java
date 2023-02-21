package u6pp;
import java.util.ArrayList;
import java.util.Random;

public class CardStack {

    ArrayList<Card> deck = new ArrayList<Card>();

    public CardStack(){

    }

    public static CardStack createUnoDeck() {
        CardStack stack = new CardStack();
        for (String color : Card.COLORS) {
            if (color.equalsIgnoreCase(Card.WILD)) {
                continue;
            }
    
            for (String value : Card.VALUES) {
                if (value.equals(Card.WILD) || value.equals(Card.WILD_DRAW_4)) {
                    continue;
                }
                stack.push(new Card(color, value));
                if (!value.equalsIgnoreCase(Card.ZERO)) {
                    stack.push(new Card(color, value));
                }
            }
        }
    
        for (int i = 0; i < 4; i++) {
            stack.push(new Card(Card.WILD, Card.WILD));
            stack.push(new Card(Card.WILD, Card.WILD_DRAW_4));
        }
    
        return stack;
    }

    public Card pop(){
        Card temp = deck.get(0);
        deck.remove(0);
        return temp;
    }

    public Card topPop(){
        Card temp = deck.get(deck.size()-1);
        deck.remove(deck.size()-1);
        return temp;
    }

    public void push(Card card){
        deck.add(0, card);
    }

    public Card peek(){
        if(deck.size() != 0){
            return deck.get(0);
        } else {
            return null;
        }
    }

    public void clear(){
        for(int i=0; i < deck.size(); i++){
            deck.remove(0);
            System.out.println(deck.get(0));
        }
        deck.remove(0);
    }

    public void addAll(CardStack otherDeck){
        if(!deck.equals(otherDeck.deck)){
        while(otherDeck.getSize() > 0){
            deck.add(0, otherDeck.topPop());
            System.out.println(deck.get(0));
        }
    }   
    }

    public void shuffle(){
        if(deck.size()>0){
        Random rand = new Random();
        for(int i = 0; i < deck.size(); i++){
            int j = rand.nextInt(i+1);
            Card temp = deck.get(j);
            deck.set(j, deck.get(i));
            deck.set(i, temp);
        }
    }
    }

    public int getSize(){
        return deck.size();
    }
    
    public boolean isEmpty(){
        if(getSize() <= 0){
            return true;
        } else {
            return false;
        }
    }

}    