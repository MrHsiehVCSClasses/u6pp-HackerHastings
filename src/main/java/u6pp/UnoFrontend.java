package u6pp;
import java.util.Scanner;

public class UnoFrontend {
    
    Uno uno = new Uno(3);
    Scanner scanner = new Scanner(System.in);

    public void play(){
        boolean blay = true;
        int isWild = 0;
        int answer = 0;
        String answer2 = "";
        while(blay){
            printDaStatus();
            boolean question = true;
            while(question){
            System.out.println("Whachu wanna do, player" + uno.whichGuy + "? (answer which card you want to play in your hand, as a number)");
            answer = scanner.nextInt();
            if(answer < uno.players.get(uno.whichGuy).hand.size()){
                
            if(uno.players.get(uno.whichGuy).hand.get(answer).getColor().equals("WILD")){
                boolean question2 = true;
                while (question2){
                    System.out.println("What color u wanna set the wild bro? (answer in all caps)");
                    answer2 = scanner.nextLine();
                    System.out.println(answer2);
                    for(String Color : Card.COLORS){
                    if(answer2.equals(Color)){
                        isWild = 1;
                        question2 = false;
                    }
                    }
                }
            }
                question = false;

            }
            }
            if(isWild == 1){
                if(uno.playCard(uno.players.get(uno.whichGuy).hand.get(answer), answer2) == true){

                } else {
                    System.out.println("Your card didn't work, gave you one from the deck.");
                }
                
            } else {
                if(uno.playCard(uno.players.get(uno.whichGuy).hand.get(answer), "")){

                }else{
                    System.out.println("Your card didn't work, gave you one from the deck.");
                }
            }

            int count = 0;
            for(Player p : uno.players){
                if(p.hand.size() == 0){
                    System.out.println("Player " + count + " wins!");
                    blay = false;
                }
                count++;
            }
            
        }
    }

    public void printDaStatus(){
        System.out.println("This is player" + uno.whichGuy + "'s hand");
        for(Card card : (uno.players.get(uno.whichGuy).hand)){
            System.out.println(card.getColor());
            System.out.println(card.getValue());
        }
        System.out.println("This what you playing on");
        System.out.println(uno.discard.peek().getColor());
        System.out.println(uno.discard.peek().getValue());

    }
}