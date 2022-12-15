package TiMP.test.Test;

import TiMP.test.Question.Question;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Test {
    //private Question question; //вопрос
    private List<Question> testQuestions; //список вопросов.

    public Test() throws IOException {
        this.testQuestions = parseLinesToQuestions();
    }

    private List<String> readInputFileToList() throws IOException { //передаём файл в список строк
        String fileName = "C:\\Users\\Vladislav\\Desktop\\Test\\src\\TiMP\\test\\Input\\input.txt";
        List<String> lines = Files.readAllLines(Paths.get(fileName)); //создаём массив строк

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

    private List<Question> parseLinesToQuestions() throws IOException {
        List<String> questionLines = randomQuestions(readInputFileToList());
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
}
