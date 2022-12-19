package TiMP.test.Test;

import TiMP.test.Question.Question;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Test {
    private List<Question> testQuestions; //список вопросов.
    private String fileName;

    public Test(int number) throws IOException {
        this.testQuestions = parseLinesToQuestions(number);
    }

    private List<String> readInputFileToList(int number) throws IOException { //передаём файл в список строк
        if (number == 1) {
            fileName = "C:\\Users\\Vladislav\\Desktop\\Test\\src\\TiMP\\test\\Input\\history.txt";
        }
        if (number == 2) {
            fileName = "C:\\Users\\Vladislav\\Desktop\\Test\\src\\TiMP\\test\\Input\\literature.txt";
        }
        if (number == 3) {
            fileName = "C:\\Users\\Vladislav\\Desktop\\Test\\src\\TiMP\\test\\Input\\russian.txt";
        }
        List<String> lines = Files.readAllLines(Paths.get(fileName)); //создаём массив строк
        if (number == 1) {
            fileName = "Тест по истории";
        }
        if (number == 2) {
            fileName = "Тест по литературе";
        }
        if (number == 3) {
            fileName = "Тест по русскому языку";
        }
        return lines; //возвращаем массив строк
    }

    private List<String> randomQuestions(List<String> lines) { //генерируем, какие вопросы пойдут в тест
        List<String> questionsLines = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < lines.size(); i++) {
            int num = random.nextInt(lines.size());
            questionsLines.add(lines.get(num));
        }
        return questionsLines; //возвращаем 10 выбранных строк-вопросов
    }

    private List<Question> parseLinesToQuestions(int number) throws IOException {
        List<String> questionLines = randomQuestions(readInputFileToList(number));
        List<Question> tempTestQuestions = new ArrayList<>();
        for (int i = 0; i < 10; i++) { //10 строк по очереди
            String str = questionLines.get(i);
            String[] words = str.split(";");//делим строку по словам, через ";"

            tempTestQuestions.add(createQuestion(words));
        }
        return tempTestQuestions;
    }

    private Question createQuestion(String[] words) { //передаём слова строки с помощью массива String
        String[] firstAnswer = new String[] {words[2], words[3]}; //передаём текст вопроса и очки за вопрос
        String[] secondAnswer = new String[] {words[4], words[5]};//аналогично
        String[] thirdAnswer = new String[] {words[6], words[7]};//аналогично
        return new Question(Integer.parseInt(words[0]), words[1], firstAnswer, secondAnswer, thirdAnswer); //создаём вопрос
    }

    public List<Question> getTestQuestions() {
        return testQuestions;
    }

    public void setTestQuestions(List<Question> testQuestions) {
        this.testQuestions = testQuestions;
    }

    public String getFileName() {
        return fileName;
    }
}
