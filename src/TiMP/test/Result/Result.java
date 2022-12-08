package TiMP.test.Result;

import TiMP.test.Question.Question;
import TiMP.test.Test.Test;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Result {
    private int mark; //оценка.
    private List<Question> correctAnswers; //список вопросов, на которые ответил правильно.
    private List<Question> incorrectAnswers; //список вопросов, на которые ответил неправильно.

    public Result() {
    }

    public void startApp() throws IOException {
        /**/
        Test test = new Test();
        startTest(test.getTestQuestions());

    }

    private void consoleMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome!\nChoose the role [1)Admin] or [2)User] and enter the number (1 or 2):");
        int number = scanner.nextInt();
    }

    private void startTest(List<Question> testQuestions) {
        for (int i = 0; i < testQuestions.size(); i++) {
            Question curQuestion = testQuestions.get(i);
            System.out.println(i + ") " + curQuestion.getTaskText() + ":");
            System.out.println("1) " + testQuestions.get(i).getFirstPossibleAnswerTaskText());
            System.out.println("2) " + testQuestions.get(i).getSecondPossibleAnswerTaskText());
            System.out.println("3) " + testQuestions.get(i).getThirdPossibleAnswerTaskText());
        }
    }
}
