import java.util.Scanner;

public class Quiz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        String[][] questions = {
            {"What is the capital of India?", "A. Gujarat", "B. Delhi", "C. Bombay", "D. Punjab", "B"},
            {"Who is the author of 'Marvel' series?", "A. J.R.R. Tolkien", "B. Stan Lee", "C. George R.R. Martin", "D. Stephen King", "B"},
            {"Which planet is known as the hotest Planet?", "A. Sun", "B. Mars", "C. Jupiter", "D. Saturn", "A"}
        };
        int numberOfQuestions = questions.length;
        final int QUESTION_TIMEOUT_SECONDS = 15;

        System.out.println("Welcome to the Quiz!");
        System.out.println("You will have " + QUESTION_TIMEOUT_SECONDS + " seconds to answer each question.");
        System.out.println("---------------------------------------------------------------");

        for (int i = 0; i < numberOfQuestions; i++) {
            String[] currentQuestion = questions[i];
            String question = currentQuestion[0];
            String optionA = currentQuestion[1];
            String optionB = currentQuestion[2];
            String optionC = currentQuestion[3];
            String optionD = currentQuestion[4];
            String correctAnswer = currentQuestion[5];

            System.out.println("Question " + (i + 1) + ": " + question);
            System.out.println(optionA);
            System.out.println(optionB);
            System.out.println(optionC);
            System.out.println(optionD);
            System.out.println("Please enter your answer (A, B, C, or D):");

            long startTime = System.currentTimeMillis();
            while (true) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - startTime >= QUESTION_TIMEOUT_SECONDS * 1000) {
                    System.out.println("Time's up!");
                    break;
                }
                if (scanner.hasNextLine()) {
                    String userAnswer = scanner.nextLine().trim().toUpperCase();
                    if (userAnswer.equals(correctAnswer)) {
                        System.out.println("Correct!");
                        score++;
                    } else {
                        System.out.println("Incorrect! The correct answer is " + correctAnswer);
                    }
                    break;
                }
            }
            System.out.println("---------------------------------------------------------------");
        }

        System.out.println("Quiz completed!");
        System.out.println("Your final score is: " + score + " out of " + numberOfQuestions);

        int incorrectAnswers = numberOfQuestions - score;
        System.out.println("Correct answers: " + score);
        System.out.println("Incorrect answers: " + incorrectAnswers);

        scanner.close();
    }
}