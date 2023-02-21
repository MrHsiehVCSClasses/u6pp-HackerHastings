package u6pp;
import java.util.ArrayList;
import java.util.Scanner;

public class Uno {
    ArrayList<Player> players = new ArrayList<Player>();
    CardStack deck = new CardStack();
    CardStack discard = new CardStack();
    int whichGuy;
    boolean isReverse;
    Scanner scanner = new Scanner(System.in);

    public Uno(int playersCount){
        deck.addAll(CardStack.createUnoDeck());
        deck.shuffle();
        discard.push(deck.pop());
        for(int i = 0; i<playersCount; i++){
            players.add(new Player("Joe"));
            for(int j = 0; j<7; j++){
            players.get(i).hand.add(deck.pop());
            }
        }
    }

    public Uno(ArrayList<Player> players, CardStack deck, CardStack discard, int whichGuy, boolean isReverse){
        this.players = players;
        this.deck = deck;
        this.discard = discard;
        this.whichGuy = whichGuy;
        this.isReverse = isReverse;
    }

    public boolean playCard(Card card, String str){
        boolean checkHand = false;
        int whichIndex = 0;
//NULL CHECKER
        if(card == null){
            shuffleIfEmpty();
            getCurrentPlayer().hand.add(deck.pop());
            shuffleIfEmpty();
            changePlayers(false);
            return false;
        }
//CHECK IF IN HAND
        for(int i = 0; i < players.get(whichGuy).getHand().size(); i++){
            if(card.equals(players.get(whichGuy).getHand().get(i))){
                checkHand = true;
                whichIndex = i;
            }
        }
        if(checkHand == true){
//CHECK IF CAN PLAY, THEN PLAYS
            if(card.canPlayOn(discard.peek()) == true){
                discard.push(card);
                players.get(whichGuy).getHand().remove(whichIndex);
                if(card.getValue().equals("REVERSE")){
                    isReverse = !isReverse;
                }
                if(card.getValue().equals("SKIP")){
                    changePlayers(true);
                    return true;
                }
                if(card.getValue().equals("DRAW_2")){
                    shuffleIfEmpty();
                    if(isReverse){
                        if(whichGuy>0){
                            players.get(whichGuy-1).hand.add(deck.pop());
                            shuffleIfEmpty();
                            players.get(whichGuy-1).hand.add(deck.pop());
                            shuffleIfEmpty();
                        } else {
                            players.get(players.size()-1).hand.add(deck.pop());
                            shuffleIfEmpty();
                            players.get(players.size()-1).hand.add(deck.pop());
                            shuffleIfEmpty();
                        }
                        changePlayers(true);
                        return true;
                    } else {
                        if(whichGuy<players.size()-1){
                            players.get(whichGuy+1).hand.add(deck.pop());
                            shuffleIfEmpty();
                            players.get(whichGuy+1).hand.add(deck.pop());
                            shuffleIfEmpty();
                        } else {
                            players.get(0).hand.add(deck.pop());
                            shuffleIfEmpty();
                            players.get(0).hand.add(deck.pop());
                            shuffleIfEmpty();
                        }
                        changePlayers(true);
                        return true;
                    } 
                }
                if(card.getValue().equals("WILD")){
                        if(card.trySetColor(str) == true){
                            discard.peek().setColor(str);
                        }
                }
                if(card.getValue().equals("WILD_DRAW_4")){
                    shuffleIfEmpty();
                    if(isReverse){
                        if(whichGuy>0){
                            players.get(whichGuy-1).hand.add(deck.pop());
                            shuffleIfEmpty();
                            players.get(whichGuy-1).hand.add(deck.pop());
                            shuffleIfEmpty();
                            players.get(whichGuy-1).hand.add(deck.pop());
                            shuffleIfEmpty();
                            players.get(whichGuy-1).hand.add(deck.pop());
                            shuffleIfEmpty();
                        } else {
                            players.get(players.size()-1).hand.add(deck.pop());
                            shuffleIfEmpty();
                            players.get(players.size()-1).hand.add(deck.pop());
                            shuffleIfEmpty();
                            players.get(players.size()-1).hand.add(deck.pop());
                            shuffleIfEmpty();
                            players.get(players.size()-1).hand.add(deck.pop());
                            shuffleIfEmpty();
                        }
                        changePlayers(true);
                        return true;
                    } else {
                        if(whichGuy<players.size()-1){
                            players.get(whichGuy+1).hand.add(deck.pop());
                            shuffleIfEmpty();
                            players.get(whichGuy+1).hand.add(deck.pop());
                            shuffleIfEmpty();
                            players.get(whichGuy+1).hand.add(deck.pop());
                            shuffleIfEmpty();
                            players.get(whichGuy+1).hand.add(deck.pop());
                            shuffleIfEmpty();
                        } else {
                            players.get(0).hand.add(deck.pop());
                            shuffleIfEmpty();
                            players.get(0).hand.add(deck.pop());
                            shuffleIfEmpty();
                            players.get(0).hand.add(deck.pop());
                            shuffleIfEmpty();
                            players.get(0).hand.add(deck.pop());
                            shuffleIfEmpty();
                        }
                        changePlayers(true);
                        return true;
                    } 
                }
                changePlayers(false);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void shuffleIfEmpty(){
        if(deck.getSize() == 0){
            if(discard.getSize() > 0){
                Card temp = discard.pop();
                deck.addAll(discard);
                deck.shuffle();
                discard.push(temp);
            } else {
                deck = CardStack.createUnoDeck();
            }
        }
    }

    public void changePlayers(boolean isSkip){
        if(isSkip){
            if(isReverse){
                if(whichGuy>1){
                    whichGuy -= 2;
                } else if (whichGuy == 1){
                    whichGuy = players.size()-1;
                } else {
                    whichGuy = players.size()-2;
                }
            } else {
                if(whichGuy<players.size()-1){
                    whichGuy += 2;
                } else if (whichGuy == players.size()-2){
                    whichGuy = 0;
                } else {
                    whichGuy = 1;
                }
            } 
            return;
        }
        if(isReverse){
            if(whichGuy>0){
                whichGuy--;
            } else {
                whichGuy = players.size()-1;
            }
        } else {
            if(whichGuy<players.size()-1){
                whichGuy++;
            } else {
                whichGuy = 0;
            }
        } 
    }

    public Player getCurrentPlayer(){
        return players.get(whichGuy);
    }

    public Player getNextPlayer(){
        if(isReverse){
            if(whichGuy>0){
                return players.get(whichGuy-1);
            } else {
                return players.get(players.size()-1);
            }
        } else {
            if(whichGuy<players.size()-1){
                return players.get(whichGuy+1);
            } else {
                return players.get(0);
            }
        }
    }   

    public Card getTopDiscard(){
        if(discard.getSize()>0){
            return discard.peek();
        } else {
            return null;
        }
    }

    public Player getWinner(){
        for(Player p : players){
            if(p.getHand().size() == 0){
                return p;
            }
        }
        return null;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }
    
}
