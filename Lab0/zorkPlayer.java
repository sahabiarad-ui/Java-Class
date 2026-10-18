import java.util.Scanner;
import java.util.Random;

public class zorkPlayer {

    public String[] moves = {"north", "south", "east", "west", "sleep", "rummage", "eat", "check-progress", "help"};
    public String[] progress = new String[10];
    public String[] userProgression= new String[10];
    public Random rand = new Random();
    public int halfWay = 0;
    public int userProgressionHandler = 0;
    boolean gameWin = false;

    zorkPlayer() {
        generateCompletionPath();
        gameLoop();
    }



    public void checkProgress() {
        if (userProgressionHandler == 10) {
            int guesses = finale();
            System.out.println("You have won the game!");
            gameWin = true;
            System.out.println("It took you " + (halfWay + guesses) + " moves to comlete the game.");
        }
    }
    public int finale()
    {
        String escapeWord = "morpheus";
        int guesses = 0;
        String[] Hints = {
                "You must guess an escape word, the word is related to a technological school, and charred catfish.",
                "You were wrong, think more utilitarian...",
                "The man who brings you to speak to the oracle.",
                "The name is spelled with a ph where an 'f' sound is made phenetically.",
                "He gives you the option to take a red pill or blue pill"
        };
        String userInput = "";
        Scanner s = new Scanner(System.in);
        for(int i = 0; i < 5; i++)
        {
            System.out.println(Hints[i]);
            userInput = s.nextLine();
            guesses++;
            if(userInput.toLowerCase().equals(escapeWord))
            {
                return guesses;
            }
        }
        System.out.println("You have failed to escape, you were wise, but you couldn't crack the code...");
        System.exit(0);
        return guesses;
    }


    public void help() {
        if(halfWay == 0) {
            switch (progress[halfWay]) {
                case "north":
                    String[] northHints = {"Heading north usually leads deeper into uncharted territory.", "Keep an eye out for terrain changes as you travel north.", "Venturing north can expose you to colder winds and tougher paths."};
                    System.out.println(northHints[rand.nextInt(northHints.length)]);
                    break;
                case "south":
                    String[] southHints = {"Going south tends to take you back toward safer, explored zones.", "The southern path often smells faintly of damp earth and water.", "If you are lost, heading south might bring you back to familiar landmarks."};
                    System.out.println(southHints[rand.nextInt(southHints.length)]);
                    break;
                case "east":
                    String[] eastHints = {"The morning sun rises in the east—use it to guide your initial travels.", "Eastern routes often lead to rocky outcrops and steep climbs.", "Listen closely; you might hear the sound of wind or water if you head east."};
                    System.out.println(eastHints[rand.nextInt(eastHints.length)]);
                    break;
                case "west":
                    String[] westHints = {"Shadows lengthen quickly when you travel west late in the day.", "Western trails are often overgrown with briars and thick brush.", "Heading west usually uncovers hidden caves and shadowed alcoves."};
                    System.out.println(westHints[rand.nextInt(westHints.length)]);
                    break;
                case "sleep":
                    String[] sleepHints = {"Find a safe, sheltered spot before you sleep to avoid unexpected hazards.", "Sleeping restores your energy, but make sure your stats aren't critically low first.", "You cannot rest effectively if danger is lurking nearby."};
                    System.out.println(sleepHints[rand.nextInt(sleepHints.length)]);
                    break;
                case "rummage":
                    String[] rummageHints = {"Rummaging through debris or containers often yields useful items.", "Be careful what you touch while rummaging; not everything left behind is safe.", "Check corners and old crates thoroughly when you choose to rummage."};
                    System.out.println(rummageHints[rand.nextInt(rummageHints.length)]);
                    break;
                case "eat":
                    String[] eatHints = {"Keep your food supply stocked so you don't run out of energy on your journey.", "Some items might need to be processed or prepared before you can eat them safely.", "Eating restores vitality, but watch out for spoiled rations."};
                    System.out.println(eatHints[rand.nextInt(eatHints.length)]);
                    break;
                case "check-progress":
                    String[] progressHints = {"Use check-progress to review your recent moves and path history.", "Looking back at your progress helps you trace your steps back out.", "Tracking your history prevents you from blindly looping through the same areas."};
                    System.out.println(progressHints[rand.nextInt(progressHints.length)]);
                    break;
                default:
                    System.out.println("Unknown action.");
                    break;
            }
        } else {
            switch (progress[halfWay]) {
                case "north":
                    String[] northHints = {"Heading north usually leads deeper into uncharted territory.", "Keep an eye out for terrain changes as you travel north.", "Venturing north can expose you to colder winds and tougher paths."};
                    System.out.println(northHints[rand.nextInt(northHints.length)]);
                    break;
                case "south":
                    String[] southHints = {"Going south tends to take you back toward safer, explored zones.", "The southern path often smells faintly of damp earth and water.", "If you are lost, heading south might bring you back to familiar landmarks."};
                    System.out.println(southHints[rand.nextInt(southHints.length)]);
                    break;
                case "east":
                    String[] eastHints = {"The morning sun rises in the east—use it to guide your initial travels.", "Eastern routes often lead to rocky outcrops and steep climbs.", "Listen closely; you might hear the sound of wind or water if you head east."};
                    System.out.println(eastHints[rand.nextInt(eastHints.length)]);
                    break;
                case "west":
                    String[] westHints = {"Shadows lengthen quickly when you travel west late in the day.", "Western trails are often overgrown with briars and thick brush.", "Heading west usually uncovers hidden caves and shadowed alcoves."};
                    System.out.println(westHints[rand.nextInt(westHints.length)]);
                    break;
                case "sleep":
                    String[] sleepHints = {"Find a safe, sheltered spot before you sleep to avoid unexpected hazards.", "Sleeping restores your energy, but make sure your stats aren't critically low first.", "You cannot rest effectively if danger is lurking nearby."};
                    System.out.println(sleepHints[rand.nextInt(sleepHints.length)]);
                    break;
                case "rummage":
                    String[] rummageHints = {"Rummaging through debris or containers often yields useful items.", "Be careful what you touch while rummaging; not everything left behind is safe.", "Check corners and old crates thoroughly when you choose to rummage."};
                    System.out.println(rummageHints[rand.nextInt(rummageHints.length)]);
                    break;
                case "eat":
                    String[] eatHints = {"Keep your food supply stocked so you don't run out of energy on your journey.", "Some items might need to be processed or prepared before you can eat them safely.", "Eating restores vitality, but watch out for spoiled rations."};
                    System.out.println(eatHints[rand.nextInt(eatHints.length)]);
                    break;
                case "check-progress":
                    String[] progressHints = {"Use check-progress to review your recent moves and path history.", "Looking back at your progress helps you trace your steps back out.", "Tracking your history prevents you from blindly looping through the same areas."};
                    System.out.println(progressHints[rand.nextInt(progressHints.length)]);
                    break;
                default:
                    System.out.println("Unknown action.");
                    break;
            }
        }
    }

    public void generateCompletionPath() {

        for(int i = 0; i < 10; i++) {
            progress[i] = moves[rand.nextInt(8)];
        }

        System.out.println("Enter your first move:");
    }

    public void action(String userInput) {
        if(userInput.toLowerCase().equals("help")) {
            help();
            return;
        } else if(halfWay == 0) {
            if(userInput.toLowerCase().equals(progress[halfWay])) {
                if(userInput.toLowerCase().equals("check-progress")) {
                    System.out.println("You have " + (10 - userProgressionHandler) + " moves to complete before you win the game.");
                }
                System.out.println("You have chosen your next move wisely, please proceed with your current aptitude");
                userProgression[userProgressionHandler] = progress[halfWay];
                halfWay++;
                userProgressionHandler++;
                checkProgress();
            } else {
                help();
            }
            return;
        } else {
            if(userInput.toLowerCase().equals(progress[halfWay])) {
                if(userInput.toLowerCase().equals("check-progress")) {
                    System.out.println("You have " + (10 - userProgressionHandler) + " moves to complete before you win the game.");
                }
                System.out.println("Correct move.");
                userProgression[userProgressionHandler] = progress[halfWay];
                halfWay++;
                userProgressionHandler++;
                checkProgress();
            } else {
                help();
            }
            return;
        }
    }

    public void gameLoop() {
        Scanner s = new Scanner(System.in);
        String userInput;

        while(!gameWin)
        {
            boolean gameGood = false;

            userInput = s.nextLine();
            userInput = userInput.trim();

            for(int i = 0; i < 9; i++) {
                if(userInput.toLowerCase().equals(moves[i])) {
                    gameGood = true;
                    break;
                }
            }

            if(gameGood) {
                action(userInput);
            } else {
                System.out.println("Bad Input. Here Are Your Possible Moves: north, south, east, west, rummage, sleep, check-progress, eat, help");
            }
        }
        s.close();
    }

    public static void main(String[] args) {
        new zorkPlayer();
    }
}
