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
    private int summaryPoints;

    public Result(int mark, List<Question> correctAnswers, List<Question> incorrectAnswers, int summaryPoints) {
        this.mark = mark;
        this.correctAnswers = correctAnswers;
        this.incorrectAnswers = incorrectAnswers;
        this.summaryPoints = summaryPoints;
    }


    public void startApp() throws IOException {
        /**/
        Test test = new Test();
        System.out.println("Welcome!");
        chooseRole();

        startTest(test.getTestQuestions());

    }

    private void chooseRole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the role [1)Admin] or [2)User] and enter the number (1 or 2):");
        int num = scanner.nextInt();
        switch (num) {
            case 1:
                System.out.println("You have entered by Admin-role");
                loginWithAdmin();
                break;
            case 2:
                System.out.println("You have entered by User-role");
                loginWithUser();
                break;
            default:
                System.out.println("You entered wrong number! Be carefully!");
                chooseRole();
        }
    }


    private void startTest(List<Question> testQuestions) {
        Scanner scanner = new Scanner(System.in);
        /*Вывод на экран доп инфы*/
        for (int i = 0; i < testQuestions.size(); i++) {
            Question curQuestion = testQuestions.get(i);
            System.out.println(i + ") " + curQuestion.getTaskText() + ":");
            System.out.println("1) " + testQuestions.get(i).getFirstPossibleAnswerTaskText());
            System.out.println("2) " + testQuestions.get(i).getSecondPossibleAnswerTaskText());
            System.out.println("3) " + testQuestions.get(i).getThirdPossibleAnswerTaskText());
            System.out.println("Choose the answer (enter: 1, 2 or 3) or skip (enter any other number):");
            int answerNumber = scanner.nextInt();
            if (answerNumber != 0 && answerNumber != 1 && answerNumber != 2 && answerNumber != 3) {

            }
            checkQuestionForCorrectAnswer(curQuestion, answerNumber, i, testQuestions);
        }
    }

    private void newQuestion() {

    }

    /*Проверка выбранного ответа*/
    private void checkQuestionForCorrectAnswer(Question curQuestion, int answerNumber, int i, List<Question> testQuestions) {
        switch (answerNumber) {
            case 1: //если выбирает первый вариант ответа
                summaryPoints += testQuestions.get(i).getFirstPossibleAnswerPoints(); //добавляем очки за выбранный вариант ответа
                if (testQuestions.get(i).getFirstPossibleAnswerPoints() == 1) { //если вариант ответа правильный,
                    correctAnswers.add(curQuestion); //то добавляем вопрос в список правильных ответов на вопрос
                } else {
                    incorrectAnswers.add(curQuestion); //иначе в список неверных
                }
                break;
            case 2:
                summaryPoints += testQuestions.get(i).getSecondPossibleAnswerPoints();
                if (testQuestions.get(i).getSecondPossibleAnswerPoints() == 1) {
                    correctAnswers.add(curQuestion);
                } else {
                    incorrectAnswers.add(curQuestion);
                }
                break;
            case 3:
                summaryPoints += testQuestions.get(i).getThirdPossibleAnswerPoints();
                if (testQuestions.get(i).getThirdPossibleAnswerPoints() == 1) {
                    correctAnswers.add(curQuestion);
                } else {
                    incorrectAnswers.add(curQuestion);
                }
                break;
            default: //если любое другое значение (!= 1, 2, 3), то пропускаем вопрос
                incorrectAnswers.add(curQuestion);
                if (i < testQuestions.size()) { //если это не последний вопрос
                    newQuestion(); //то переходим на следующий
                } else {
                    testEnding(); //иначе заканчиваем тест;
                }
                break;
        }
    }
}
