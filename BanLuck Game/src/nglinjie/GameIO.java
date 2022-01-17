package nglinjie;

import java.util.ArrayList;
import java.util.Scanner;

public class GameIO {
    private static CardDeck myDeck;
    private static Player player1;
    private static Player computer;
    private static ArrayList<Player> allPlayers;

    public GameIO() {
        myDeck = new CardDeck();
        player1 = new Player("Player 1");
        computer = new Player("Computer");
        allPlayers = new ArrayList<>(){{
            add(player1);
            add(computer);
        }};
    }

    public static void main(String[] args) {

        GameIO gameIO = new GameIO();
        gameEngine();
    }

    private static void startMenu(){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Do you want to play a game of Ban Luck?");
        System.out.println("Press 'Y' for yes and 'N' for no");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++");
    }

    private static void gameEngine(){
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        while (!quit){
            startMenu();

            String userInput = scanner.nextLine().toLowerCase();

            switch (userInput) {
                case "y", "yes" -> {
                    if (!checkBanLuck()) {
                        gameLogic();
                    }
                    else {
                        checkWin();
                    }
                    reset();
                }
                case "n", "no" -> {
                    System.out.println("System exiting");
                    quit = true;
                }
                default -> System.out.println("Please enter a valid input");
            }
        }
    }

    private static void gameLogic(){

        System.out.println("Do you want to draw another card?: ");
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().toLowerCase();

        switch (userInput){
            case "y":
            case "yes":
                playerChoice();
            case "n":
            case "no":
                computerChoice();
                checkWin();
                break;
            default:
                System.out.println("Please enter a valid input");
        }
    }

    private static void playerChoice(){
        Scanner scanner = new Scanner(System.in);
        String answer;
        boolean exit = false;

        do{
            answer = "";
            player1.addCard(myDeck.drawCard());
            while (!exit){
                System.out.println(player1.getCardsOnHand());
                System.out.println(player1.getName() + " total value: " + player1.getTotalValue());
                System.out.println("Do you want to draw another card?: ");
                answer = scanner.nextLine();
                if (answer.equals("n") || answer.equals("no") || player1.getCardsOnHand().size() == 4){
                    exit = true;
                }
                else if (answer.equals("y") || answer.equals("yes")){
                    player1.addCard(myDeck.drawCard());
                }
            }

        } while (answer.equals("y") || answer.equals("yes"));
    }

    private static void computerChoice(){
        for (int i = 0; i < 3; i++){
            if(computer.getCardsOnHand().size() <= 5){
                if(computer.getTotalValue() < 17){
                    computer.addCard(myDeck.drawCard());
                }
                else if(computer.cardsOnHand.size() > 2){
                    if(comDecisionYes()){
                        computer.addCard(myDeck.drawCard());
                    }
                    else {
                        break;
                    }
                }
                else {
                    break;
                }
            }
            else {
                break;
            }
        }
    }

    private static boolean comDecisionYes(){
        int max = 21 ;
        int min = 0;
        int i = min + (int)(Math.random() * ((max - min) + 1));
        return i < 21 - (computer.getTotalValue());
    }

    private static void startingCards(){
        for (int i = 0; i < 2; i++){
            player1.addCard(myDeck.drawCard());
            computer.addCard(myDeck.drawCard());
        }
        System.out.println(player1.getName() + " : " + player1.getCardsOnHand() + " ; Total value: " + player1.getTotalValue());
    }

    private static boolean checkBanLuck(){
        boolean win = false;
        startingCards();
        for (Player player : allPlayers){
            if (player.getTotalValue() == 22){
                System.out.println(player.getName() + " Ban ban!");
                win = true;
            }
            else if (player.getTotalValue() == 21){
                System.out.println(player.getName() + " Ban Luck!");
                win = true;
            }
            else {
                win = false;
            }
        }
        return win;
    }

    public static void reset(){
        for (Player player : allPlayers){
            ArrayList<Card> cards = player.cardsOnHand;
            myDeck.addToDeck(cards);
            player.cardsOnHand.clear();
        }

    }

    private static void checkWin(){
        System.out.println("=========================================================");
        System.out.println(player1.getName() + " : " + player1.getCardsOnHand() + " ; Total value: " + player1.getTotalValue());
        System.out.println(computer.getName() + " : " + computer.getCardsOnHand() + " ; Total value: " + computer.getTotalValue());
        System.out.println("=========================================================");

        if (player1.getCardsOnHand().size() == 5 && player1.getTotalValue() > 21){
            System.out.println(computer.getName() + " win!");
            System.out.println(player1.getName() + " explode while forming 5 dragons");
        }
        else if (computer.getCardsOnHand().size() == 5 && computer.getTotalValue() > 21){
            System.out.println(player1.getName() + " win!");
            System.out.println(computer.getName() + " explode while forming 5 dragons");
        }
        else if (computer.getTotalValue() > 21 && computer.getCardsOnHand().size() > 1){
            if (player1.getTotalValue() > 21 && player1.getCardsOnHand().size() > 1){
                System.out.println("Zhao");
            }
            else {
                System.out.println(computer.getName() + " exploded");
                System.out.println(player1.getName() + " win!");
            }
        }
        else if(player1.getCardsOnHand().size() == 5 && player1.getTotalValue() <= 21){
            System.out.println(player1.getName() + " win with 5 dragons!");
        }
        else if(computer.getCardsOnHand().size() == 5 && computer.getTotalValue() <= 21){
            System.out.println(computer.getName() + " win with 5 dragons!");
        }
        else if(player1.getTotalValue() > 21 && player1.getCardsOnHand().size() > 1){
            System.out.println(player1.getName() + " exploded");
            System.out.println(computer.getName() + " win!");
        }

        else if(player1.getTotalValue() > computer.getTotalValue()){
            System.out.println(player1.getName() + " win!");
        }
        else if(player1.getTotalValue() == computer.getTotalValue()){
            System.out.println("Zhao");
        }
        else {
            System.out.println(computer.getName() + " win!");
        }
        System.out.println();
    }

}

