package TiMP.test.Result;

import TiMP.test.Question.Question;
import TiMP.test.Test.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Result {
    private int mark; //оценка.
    private List<Question> correctAnswers; //список вопросов, на которые ответил правильно.
    private List<Question> incorrectAnswers; //список вопросов, на которые ответил неправильно.
    private int summaryPoints;
    private int numOfQuestionInTheTest;
    private List<String[]> users;

    public Result() throws IOException {
        this.mark = 2;
        this.correctAnswers = null;
        this.incorrectAnswers = null;
        this.summaryPoints = 0;
        this.users = parseLinesToLogins();
    }


    public void startApp() throws IOException {
        System.out.println("Welcome!");
        chooseRole();
        //startTest(test.getTestQuestions());

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
                loginWithUserLogin();
                break;
            default:
                System.out.println("You entered wrong number! Be carefully!");
                chooseRole();
        }
    }

    private List<String> readInputFileToList() throws IOException { //передаём файл в список строк
        String fileName = "C:\\Users\\Vladislav\\Desktop\\Test\\src\\TiMP\\test\\Login\\login.txt";
        List<String> lines = Files.readAllLines(Paths.get(fileName)); //создаём массив строк

        return lines; //возвращаем массив строк
    }

    private List<String[]> parseLinesToLogins() throws IOException { //преобразовываем строки в список массивов из логина и пароля;
        List<String> loginLines = readInputFileToList();
        List<String[]> tempUserLogins = new ArrayList<>();
        for (String str : loginLines) { //3 строк по очереди
            String[] loginAndPassword = str.split(";");//делим строку по словам, через ";"

            tempUserLogins.add(loginAndPassword);
        }
        return tempUserLogins;
    }

    private void loginWithUserLogin() throws IOException {
        boolean trueLogin = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter login: ");
        String enteredLogin = scanner.nextLine();

        for (String[] user : users) {
            if (user[0].equals(enteredLogin)) {
                loginWithUserPassword();
                trueLogin = true;
                break;
            }
        }
        if (!trueLogin) {
            System.out.println("You are not registered.");
        }
        System.out.println("You logged in.");

        startTest();
    }

    private void loginWithUserPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter password: ");
        String enteredPassword = scanner.nextLine();

    }



    private void startTest() throws IOException {
        Test test = new Test();
        List<Question> testQuestions = test.getTestQuestions();
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

            } else {
            checkQuestionForCorrectAnswer(curQuestion, answerNumber, testQuestions);
            }
        }
    }

    private void newQuestion(List<Question> testQuestions) {
        Scanner scanner = new Scanner(System.in);
        Question curQuestion = testQuestions.get(numOfQuestionInTheTest);
        System.out.println(numOfQuestionInTheTest + ") " + curQuestion.getTaskText() + ":");
        System.out.println("1) " + testQuestions.get(numOfQuestionInTheTest).getFirstPossibleAnswerTaskText());
        System.out.println("2) " + testQuestions.get(numOfQuestionInTheTest).getSecondPossibleAnswerTaskText());
        System.out.println("3) " + testQuestions.get(numOfQuestionInTheTest).getThirdPossibleAnswerTaskText());
        System.out.println("Choose the answer (enter: 1, 2 or 3) or skip (enter any other number):");
        int answerNumber = scanner.nextInt();
        if (answerNumber != 0 && answerNumber != 1 && answerNumber != 2 && answerNumber != 3) {

        } else {
            checkQuestionForCorrectAnswer(curQuestion, answerNumber, testQuestions);
        }
    }

    /*Проверка выбранного ответа*/
    private void checkQuestionForCorrectAnswer(Question curQuestion, int answerNumber, List<Question> testQuestions) {
        switch (answerNumber) {
            case 1: //если выбирает первый вариант ответа
                summaryPoints += testQuestions.get(numOfQuestionInTheTest).getFirstPossibleAnswerPoints(); //добавляем очки за выбранный вариант ответа
                if (testQuestions.get(numOfQuestionInTheTest).getFirstPossibleAnswerPoints() == 1) { //если вариант ответа правильный,
                    correctAnswers.add(curQuestion); //то добавляем вопрос в список правильных ответов на вопрос
                } else {
                    incorrectAnswers.add(curQuestion); //иначе в список неверных
                }
                break;
            case 2:
                summaryPoints += testQuestions.get(numOfQuestionInTheTest).getSecondPossibleAnswerPoints();
                if (testQuestions.get(numOfQuestionInTheTest).getSecondPossibleAnswerPoints() == 1) {
                    correctAnswers.add(curQuestion);
                } else {
                    incorrectAnswers.add(curQuestion);
                }
                break;
            case 3:
                summaryPoints += testQuestions.get(numOfQuestionInTheTest).getThirdPossibleAnswerPoints();
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
