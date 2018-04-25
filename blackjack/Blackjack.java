package blackjack;

import java.util.Scanner;

/**
 * Created by JohnnyBorges on 3/5/17.
 */
public class Blackjack {

    public static void main(String[] args) {

        //Welcome message
        System.out.println( "Welcome to Cassino CS2, This is a (21) BlackJack (21) Table, GOOD LUCK!!!");

        //Create our playing Deck - 52 cards we are going to use
        Deck playingDeck= new Deck();
        playingDeck.createfullDeck();
        playingDeck.shuffle();

        //Create a deck for the player
        Deck playerDeck = new Deck ();
        //Create a deck for the Dealer
        Deck dealerDeck = new Deck();
        //keep track amount of money player has
        double playerMoney = 100.00;

        Scanner userInput = new Scanner(System.in);

        // Game Loop - every time player has a turn runs through the loop
        while(playerMoney > 0){
            //Play On!
            //Take the players bet
            System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if (playerBet > playerMoney) {
                System.out.println( "You cannot bet more than you have. Please leave the BlackJack Table");
                break;
            }

            boolean  endRound = false;

            //Start Dealing
            //Player gets Two Cards
            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            //Dealer gets Two Cards
            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);

            while(true){
                System.out.println("Your hand:");
                System.out.print(playerDeck.toString());
                System.out.println("Your hand is valued at:" + playerDeck.cardsValue());

                //Display Dealer Hand
                System.out.println("Dealer Hand:" + dealerDeck.getCard(0).toString() + " and [Hidden]");

                //What does the player want to do?
                System.out.println("Would you like to (1)Hit or (2) Stand?");
                int response = userInput.nextInt();

                //They Hit
                if(response == 1){
                    playerDeck.draw(playingDeck);
                    System.out.println("You draw a:" + playerDeck.getCard(playerDeck.deckSize()-1).toString());
                    //Bust if >21 cards
                    if(playerDeck.cardsValue() > 21){
                        System.out.println("Bust. Currently value at: " + playerDeck.cardsValue());
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }
                if (response == 2) {
                    break;
                }
            }

            //Reveal Dealer Cards
            System.out.println("Dealer Cards:" + dealerDeck.toString());
            if((dealerDeck.cardsValue() > playerDeck.cardsValue())&& endRound ==false){
                System.out.println("Dealer beats you!");
                playerMoney -= playerBet;
                endRound = true;
            }
//Dealer Draws at 16, stand at 17
            while((dealerDeck.cardsValue() < 17) && endRound == false){
                dealerDeck.draw(playerDeck);
                System.out.println("Delaer Draws:" + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
            }

            //Display total value for Dealer
            System.out.println("Dealer's Hand is value at:" + dealerDeck.cardsValue());
            //Determine if dealer busted = value on the hand is over 21 points
            if((dealerDeck.cardsValue() > 21)&& endRound ==false){
                System.out.println("Dealer busts! You WIN");
                        playerMoney += playerBet;
                endRound = true;
            }
            //Determine if push = you and the dealer has the same hand value
            if((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false){
                System.out.println("PUSH");
                endRound = true;
            }
            if((playerDeck.cardsValue() > dealerDeck.cardsValue()) && endRound ==false){
                System.out.println("You win the hand!");
                playerMoney += playerBet;
                endRound = true;
            }
            else if (endRound ==false){
                System.out.println("You lose the hand.");
                playerMoney -= playerBet;
                endRound = true;
            }

            playerDeck.moveAlltoDeck(playerDeck);
            dealerDeck.moveAlltoDeck(playingDeck);
            System.out.println("End of hand.");
        }

        System.out.println("Game Over!!! You are OUT of $$$$$$ MONEY $$$$$$ :(");
//print results of our deck
        System.out.println(playingDeck);


    }


}
