package blackjack;


/**
 * Created by JohnnyBorges on 3/5/17.
 */
public class Card {
//every card will have suit/ value
  private Suit suit;
  private Value value;
    //card constructor
  public Card(Suit suit, Value value) {

    this.value= value;
    this.suit = suit;
  }
  //allow to print value and suit for each card
   public String toString(){
       return this.suit.toString() + "-" + this.value.toString();
   }
   //get value method
  //check values in our deck class , determine how many points the user has on his hands
   public Value getValue()
   {
     return this.value;
   }
}
