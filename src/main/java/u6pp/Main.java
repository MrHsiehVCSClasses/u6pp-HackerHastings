package u6pp;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to u6pp! :)\nGood luck!");

        //UnoFrontend game = new UnoFrontend();
        //game.play();

        // use this space to test your code :)
        Card card1 = new Card("BLUE", "1");
        Card card2 = new Card("BLUE", "1");
        Card card3 = new Card("BLUE", "1");
        Card card4 = new Card("BLUE", "1");
        CardStack deck = new CardStack();
        deck.push(card1);
        deck.push(card2);
        CardStack deck1 = new CardStack();
        deck1.push(card3);
        deck1.push(card4);

        System.out.println(deck.getSize());
        deck.clear();
        System.out.println(deck.getSize());
    }
}