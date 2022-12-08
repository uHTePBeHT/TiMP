package TiMP.test.Question;

import java.util.Scanner;

public class Question {
    private int questionID;
    private String taskText;
    private Answer firstPossibleAnswer;
    private Answer secondPossibleAnswer;
    private Answer thirdPossibleAnswer;

    public Question(int questionID, String taskText, String[] firstArray, String[] secondArray, String[] thirdArray) {
        this.questionID = questionID;
        this.taskText = taskText;
        this.firstPossibleAnswer = new Answer(firstArray[0], Integer.parseInt(firstArray[1]));
        this.secondPossibleAnswer = new Answer(secondArray[0], Integer.parseInt(secondArray[1]));
        this.thirdPossibleAnswer = new Answer(thirdArray[0], Integer.parseInt(thirdArray[1]));
        /*this.firstPossibleAnswer.answerText = firstArray[0];
        this.firstPossibleAnswer.points = Integer.parseInt(firstArray[1]);
        this.secondPossibleAnswer.answerText = secondArray[0];
        this.secondPossibleAnswer.points = Integer.parseInt(secondArray[1]);
        this.thirdPossibleAnswer.answerText = thirdArray[0];
        thirdPossibleAnswer.points = Integer.parseInt(thirdArray[1]);*/
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public Answer getFirstPossibleAnswer() {
        return firstPossibleAnswer;
    }

    public String getFirstPossibleAnswerTaskText() {
        return firstPossibleAnswer.answerText;
    }

    public int getFirstPossibleAnswerPoints() {
        return firstPossibleAnswer.points;
    }

    public void setFirstPossibleAnswer(Answer firstPossibleAnswer) {
        this.firstPossibleAnswer = firstPossibleAnswer;
    }

    public Answer getSecondPossibleAnswer() {
        return secondPossibleAnswer;
    }

    public String getSecondPossibleAnswerTaskText() {
        return secondPossibleAnswer.answerText;
    }

    public int getSecondPossibleAnswerPoints() {
        return secondPossibleAnswer.points;
    }

    public void setSecondPossibleAnswer(Answer secondPossibleAnswer) {
        this.secondPossibleAnswer = secondPossibleAnswer;
    }

    public Answer getThirdPossibleAnswer() {
        return thirdPossibleAnswer;
    }

    public String getThirdPossibleAnswerTaskText() {
        return thirdPossibleAnswer.answerText;
    }

    public int getThirdPossibleAnswerPoints() {
        return thirdPossibleAnswer.points;
    }

    public void setThirdPossibleAnswer(Answer thirdPossibleAnswer) {
        this.thirdPossibleAnswer = thirdPossibleAnswer;
    }

    public void editAnswer(Answer possibleAnswer) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new question task text:");
        String text = scanner.nextLine();
        possibleAnswer.setAnswerText(text);
        System.out.println();
        System.out.print("Enter new question points: ");
        int point = scanner.nextInt();
        possibleAnswer.setPoints(point);
    }


    static class Answer {
        private String answerText;
        private int points;

        public Answer(String answerText, int points) {
            this.answerText = answerText;
            this.points = points;
        }

        public String getAnswerText() {
            return answerText;
        }

        public void setAnswerText(String answerText) {
            this.answerText = answerText;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }
    }
}
