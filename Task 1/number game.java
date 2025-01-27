import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int roundsPlayed = 0;
        int roundsWon = 0;
        int totalAttempts = 0;
        
        System.out.println("Welcome to the Number Game!");
        System.out.println("I'm thinking of a number between 1 and 100.");

        while (true) {
            roundsPlayed++;
            int numberToGuess = random.nextInt(100) + 1;
            int attempts = 0;
            final int maxAttempts = 10;
            boolean guessedCorrectly = false;

            System.out.println("You have " + maxAttempts + " attempts to guess the number.");

            while (attempts < maxAttempts) {
                System.out.print("Enter your number: ");
                int guess;

                try {
                    guess = scanner.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next();
                    continue;
                }

                attempts++;

                if (guess < numberToGuess) {
                    System.out.println("Too low!");
                } else if (guess > numberToGuess) {
                    System.out.println("Too high!");
                } else {
                    System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    guessedCorrectly = true;
                    break;
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've used all " + maxAttempts + " attempts. The number was " + numberToGuess + ".");
            }

            totalAttempts += attempts;
            if (guessedCorrectly) {
                roundsWon++;
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next().toLowerCase().trim();
            if (!playAgain.equals("yes")) {
                break;
            }
        }

        System.out.println("\nGame Over! You played " + roundsPlayed + " rounds and won " + roundsWon + " of them.");
        System.out.println("Total attempts: " + totalAttempts);
        if (roundsPlayed > 0) {
            System.out.printf("Average attempts per round: %.2f%n", (double) totalAttempts / roundsPlayed);
        }

        scanner.close();
    }
}