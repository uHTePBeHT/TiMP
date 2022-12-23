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

    private void startWithChoosing() throws IOException {
        int number = chooseTest();
        if (number == 1 || number == 2 || number == 3) {
            System.out.println("Тестирование начинается...");
            startTest(number);
        } else {
            System.out.println("Тестирование остановлено.");
        }
    }


    public void startApp() throws IOException {
        System.out.println("\nДобро пожаловать!");
        loginWithUserLogin();
        startWithChoosing();
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
                System.out.println("Вы зашли как пользователь.");
                loginWithUserLogin();
                break;
            default:
                System.out.println("Вы ввели неверное число! Будьте внимательны!");
                chooseRole();
        }
    }

    private int chooseTest() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите тест, по какому  предмету хотите решить: ");
        System.out.println("""
                1) по истории
                2) по литературе
                3) по русскому языку
                Завершить программу (любое другое число)""");
        int number = scanner.nextInt();
        return number;
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

    private void loginWithUserLogin() {
        boolean trueLogin = false;

        int userIndex = -1;
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Введите имя пользователя: ");
        String enteredLogin = scanner.nextLine();

        for (int index = 0; index < users.size(); index++) {
            if (users.get(index)[0].equals(enteredLogin)) {
                userIndex = index;
                trueLogin = true;
                break;
            }
        }

        if (!trueLogin) {
            System.out.println("Неверное имя пользователя.");
            loginWithUserLogin();
        }

        if (!loginWithUserPassword(userIndex)) {
            loginWithUserLogin();
        }
        currentUser = enteredLogin;
        System.out.println("Вход выполнен.\n");
    }

    private boolean loginWithUserPassword(int index) {
        Scanner scanner = new Scanner(System.in);
        boolean bool = false;

        for (int i = 0; i < 3; i++) {
            System.out.println();
            System.out.println("Введите пароль: ");
            String enteredPassword = scanner.nextLine();
            if (enteredPassword.equals(users.get(index)[1])) {
                bool = true;
                break;
            } else {
                int temp = 2 - i;
                if (temp > 0) {
                    System.out.println("Вы ввели неверный пароль! Попробуйте ещё раз. Осталось " + temp + " попытки.");
                } else {
                    System.out.println("Введите имя пользователя ещё раз.");
                }
            }
        }
        return bool;
    }



    private void startTest(int number) throws IOException {
        Test test = new Test(number);
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
            System.out.println("\nВыберите вариант ответа (введите: 1, 2 или 3) или пропустите вопрос (введите любое другое число):");
            int answerNumber = scanner.nextInt();

            checkQuestionForCorrectAnswer(curQuestion, answerNumber, testQuestions, i);

        }
        testEnding(test.getFileName());
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

        private void testEnding(String fileName) throws IOException {
        System.out.println("\nВы завершили тестирование. \nБаллы: " + summaryPoints);

        if (summaryPoints >= 0 && summaryPoints < 5) {
            System.out.println("Вы получили '2'");
            mark = 2;
        }
        if (summaryPoints >= 5 && summaryPoints < 7) {
            System.out.println("Вы получили '3'");
            mark = 3;
        }
        if (summaryPoints >= 7 && summaryPoints < 9) {
            System.out.println("Вы получили '4'");
            mark = 4;
        }
        if (summaryPoints >= 9 && summaryPoints <= 10) {
            System.out.println("Вы получили '5'");
            mark = 5;
        }
        if (summaryPoints < 0 || summaryPoints > 10) {
            System.out.println("Ошибка");
            mark = 2;
        }
        System.out.println();
        if (!correctAnswers.isEmpty()) {
            System.out.println("Правильные вопросы: ");
            for (Question correctAnswer : correctAnswers) {
                System.out.print("[" + correctAnswer.getQuestionID() + "] " + correctAnswer.getTaskText() + " ");
                System.out.println();
            }
        }
        System.out.println("\n");

        if (!incorrectAnswers.isEmpty()) {
            System.out.println("Ошибки: ");
            for (Question incorrectQuestion : incorrectAnswers) {
                System.out.print("[" + incorrectQuestion.getQuestionID() + "] " + incorrectQuestion.getTaskText() + " \n");
                if (incorrectQuestion.getFirstPossibleAnswerPoints() == 1) {
                    System.out.println("Правильный ответ на вопрос - 1)" + incorrectQuestion.getFirstPossibleAnswerTaskText() + "\n");
                }
                if (incorrectQuestion.getSecondPossibleAnswerPoints() == 1) {
                    System.out.println("Правильный ответ на вопрос - 2)" + incorrectQuestion.getSecondPossibleAnswerTaskText() + "\n");
                }
                if (incorrectQuestion.getThirdPossibleAnswerPoints() == 1) {
                    System.out.println("Правильный ответ на вопрос - 3)" + incorrectQuestion.getThirdPossibleAnswerTaskText() + "\n");
                }
            }
        }

        FileWriter fileWriter = new FileWriter("C:\\Users\\Vladislav\\Desktop\\Test\\src\\TiMP\\test\\Result\\results.txt", true);
        fileWriter.write(currentUser + "; " +  fileName + "; Оценка " + mark + "; Баллов за тест: " + summaryPoints + "\n");
        fileWriter.close();

        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Введите число:
                1) Решить новый тест.
                2) Посмотреть предыдущие результаты
                Чтобы завершить программу, введите любое другое число.""");
        int temp = scanner.nextInt();
        if (temp == 1) {
            startWithChoosing();
        } else
        if (temp == 2) {
            checkResults();
        } else {
            System.out.println("Тестирование окончено.");
        }
    }
    private void checkResults() throws IOException {
        String fileName = "C:\\Users\\Vladislav\\Desktop\\Test\\src\\TiMP\\test\\Result\\results.txt";
        List<String> lines = Files.readAllLines(Paths.get(fileName)); //создаём массив строк
        for (String line : lines) {
            System.out.println(line);
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Введите число:
                1) Решить новый тест.
                2) Посмотреть предыдущие результаты
                Чтобы завершить программу, введите любое другое число.""");
        int temp = scanner.nextInt();
        if (temp == 1) {
            startWithChoosing();
        } else
        if (temp == 2) {
            checkResults();
        } else {
            System.out.println("Тестирование окончено.");
        }
    }
}
