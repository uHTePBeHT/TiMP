package TiMP.test.Result;

import TiMP.test.Question.Question;
import TiMP.test.Test.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Result {
    private int mark; //оценка.
    private List<Question> correctAnswers = new ArrayList<>(); //список вопросов, на которые ответил правильно.
    private List<Question> incorrectAnswers = new ArrayList<>(); //список вопросов, на которые ответил неправильно.
    private int summaryPoints = 0;
    private List<String[]> users;
    private String currentUser;

    public Result() throws IOException {
        this.users = parseLinesToLogins();
    }



    public void startApp() throws IOException {
        System.out.println("Welcome!");
        chooseRole();
    }

    private void chooseRole() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the role [ 1)Admin ] or [ 2)User ] and enter the number (1 or 2):");
        int num = scanner.nextInt();
        switch (num) {
            case 1:
                System.out.println("You have entered by Admin-role");
                //loginWithAdmin();
                chooseRole();
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

        int userIndex = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter login: ");
        String enteredLogin = scanner.nextLine();

        for (int index = 0; index < users.size(); index++) {
            if (users.get(index)[0].equals(enteredLogin)) {
                userIndex = index;
                trueLogin = true;
                break;
            }
        }
        if (!trueLogin) {
            System.out.println("You are not registered.");
            loginWithUserLogin();
        }

        if (!loginWithUserPassword(userIndex)) {
            loginWithUserLogin();
        }
        currentUser = enteredLogin;
        System.out.println("You logged in.\n");
        System.out.println("Test starts...");
        startTest();
    }

    private boolean loginWithUserPassword(int index) {
        Scanner scanner = new Scanner(System.in);
        boolean bool = false;

        for (int i = 0; i < 3; i++) {
            System.out.println();
            System.out.println("Enter password: ");
            String enteredPassword = scanner.nextLine();
            if (enteredPassword.equals(users.get(index)[1])) {
                bool = true;
                break;
            } else {
                int temp = 2 - i;
                if (temp > 0) {
                    System.out.println("You entered wrong password! Try again. There are " + temp + " attempts.");
                } else {
                    System.out.println("Try to login again.");
                }
            }
        }
        return bool;
    }



    private void startTest() throws IOException {
        Test test = new Test();
        List<Question> testQuestions = test.getTestQuestions();
        Scanner scanner = new Scanner(System.in);
        /*Вывод на экран доп инфы*/
        for (int i = 0; i < testQuestions.size(); i++) {
            int index = i + 1;
            Question curQuestion = testQuestions.get(i);
            System.out.println("Вопрос " + index + ". " + curQuestion.getTaskText() + ":");
            System.out.println("1) " + testQuestions.get(i).getFirstPossibleAnswerTaskText());
            System.out.println("2) " + testQuestions.get(i).getSecondPossibleAnswerTaskText());
            System.out.println("3) " + testQuestions.get(i).getThirdPossibleAnswerTaskText());
            System.out.println("\nChoose the answer (enter: 1, 2 or 3) or skip (enter any other number):");
            int answerNumber = scanner.nextInt();

            checkQuestionForCorrectAnswer(curQuestion, answerNumber, testQuestions, i);

        }
        testEnding();
    }

    /*Проверка выбранного ответа*/
    private void checkQuestionForCorrectAnswer(Question curQuestion, int answerNumber, List<Question> testQuestions, int i) {
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
                break;
        }
    }

        private void testEnding() throws IOException {
        System.out.println("\nYou have completed the test. \nYour summary points: " + summaryPoints);

        if (summaryPoints >= 0 && summaryPoints < 5) {
            System.out.println("Your mark is '2'");
            mark = 2;
        }
        if (summaryPoints >= 5 && summaryPoints < 7) {
            System.out.println("Your mark is '3'");
            mark = 3;
        }
        if (summaryPoints >= 7 && summaryPoints < 9) {
            System.out.println("Your mark is '4'");
            mark = 4;
        }
        if (summaryPoints >= 9 && summaryPoints <= 10) {
            System.out.println("Your mark is '5'");
            mark = 5;
        }
        if (summaryPoints < 0 || summaryPoints > 10) {
            System.out.println("Error");
            mark = 2;
        }
        System.out.println();
        if (!correctAnswers.isEmpty()) {
            System.out.println("Correct answers are: ");
            for (Question correctAnswer : correctAnswers) {
                System.out.print("[" + correctAnswer.getQuestionID() + "] " + correctAnswer.getTaskText() + " ");
                System.out.println();
            }
        }
        System.out.println("\n");

        if (!incorrectAnswers.isEmpty()) {
            System.out.println("Mistakes: ");
            for (Question incorrectQuestion : incorrectAnswers) {
                System.out.print("[" + incorrectQuestion.getQuestionID() + "] " + incorrectQuestion.getTaskText() + " \n");
                if (incorrectQuestion.getFirstPossibleAnswerPoints() == 1) {
                    System.out.println("Right answer - 1)" + incorrectQuestion.getFirstPossibleAnswerTaskText() + "\n");
                }
                if (incorrectQuestion.getSecondPossibleAnswerPoints() == 1) {
                    System.out.println("Right answer - 2)" + incorrectQuestion.getSecondPossibleAnswerTaskText() + "\n");
                }
                if (incorrectQuestion.getThirdPossibleAnswerPoints() == 1) {
                    System.out.println("Right answer - 3)" + incorrectQuestion.getThirdPossibleAnswerTaskText() + "\n");
                }
            }
        }

        FileWriter fileWriter = new FileWriter("C:\\Users\\Vladislav\\Desktop\\Test\\src\\TiMP\\test\\Result\\results.txt", true);
        fileWriter.write(currentUser + ":  Mark is '" + mark + "'  Summary points for test: " + summaryPoints + '\n');
        fileWriter.close();
    }
}
