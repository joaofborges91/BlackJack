package blackjack;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by JohnnyBorges on 3/5/17.
 */
public class Deck {

//constructor for deck :  array  list of cards
    private ArrayList<Card> cards;
//import from deck
    public Deck()
    {
        this.cards = new ArrayList<Card>();

    }
//not going to return anything - using public void
    public void  createfullDeck()
    {
            //Generate Cards - loop for 4 suits and 52 cards FOR LOOP
            for(Suit cardSuit : Suit.values()) {
            for(Value cardValue : Value.values()) {
                //Add a new card to the deck / USING THE PREVIOUS CONSTRUCTOR FROM CARDS VALUE
                this.cards.add(new Card(cardSuit,cardValue));

            }
        }
    }

    //Shuffle the Deck
    public void shuffle(){
        ArrayList<Card> tmpDeck = new ArrayList<Card>();
        //Use Random  pull cards into a temporary deck : have a new deck everytime
        Random random = new Random();
        int randomCardIndex = 0;
        //keep the size of the deck
        int originalSize = this.cards.size();
        for(int i = 0; i < originalSize; i++){
            //Generate Random Index formula  = rand.nextInt ((max - min) +1) + min; between 0 and 51
            randomCardIndex = random.nextInt((this.cards.size() -1 - 0)+1)+0; //follow formula
            //remove from deck and throw into the temporary deck
            tmpDeck.add(this.cards.get(randomCardIndex));
            //Remove from original deck
            this.cards.remove(randomCardIndex);
        }
        this.cards= tmpDeck;



    }
    //loop to make  string with all of our card values into it
    public  String toString(){
        String cardListOutput = " ";
        for(Card aCard : this.cards){
            cardListOutput += "\n" + aCard.toString();


        }
        return cardListOutput;
    }
public void removeCard(int i){
        this.cards.remove(i);

}
public Card getCard(int i)
{
    return this.cards.get(i);
}
public void addCard (Card addCard){
    this.cards.add(addCard);

}

//Draws from the Deck
    public void draw(Deck comingFrom){
    //top card of the deck -first thing in the deck , use of 0 - begin of array list
    this.cards.add(comingFrom.getCard(0));
    //remove the card
    comingFrom.removeCard(0);

    }

    public int deckSize(){
        return  this.cards.size();
    }

    public void moveAlltoDeck(Deck moveTo){
        int thisDeckSize = this.cards.size();

        //put cards into moveTo deck
        for(int i = 0; i < thisDeckSize; i++){
            moveTo.addCard(this.getCard(i));
        }

        for(int i = 0; i < thisDeckSize; i++){
            this.removeCard(0);
        }
    }




//return total value of cards in Deck
public int cardsValue(){
        int totalValue = 0;
        int aces = 0;

        for(Card aCard: this.cards){
            switch (aCard.getValue()){
                case TWO: totalValue +=2; break;
                case THREE: totalValue +=3; break;
                case FOUR: totalValue +=4; break;
                case FIVE: totalValue +=5; break;
                case SIX: totalValue +=6; break;
                case SEVEN: totalValue +=7; break;
                case EIGHT: totalValue +=8; break;
                case NINE: totalValue +=9; break;
                case TEN: totalValue +=10; break;
                case JACK: totalValue +=10; break;
                case QUEEN: totalValue +=10; break;
                case KING: totalValue +=10; break;
                case ACE: aces +=1; break;
            }
        }
        //aces value if there are more than 1 ace in players hand, ACES VALUE CAN BE DIFFERENT !
  for(int i = 0; i < aces; i++){
            if (totalValue > 10) {
                totalValue += 1;
            }
            else{
                totalValue +=11;
            }
  }
        return totalValue;
}

}

