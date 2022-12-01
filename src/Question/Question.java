package Question;

public class Question {
    private int questionID; // individual question number
    private String taskText; // wording of the question
    private Answer firstPossibleAnswer;
    private Answer secondPossibleAnswer;
    private Answer thirdPossibleAnswer;

    public Question(int questionID, String taskText, Answer firstPossibleAnswer, Answer secondPossibleAnswer, Answer thirdPossibleAnswer) {
        this.questionID = questionID;
        this.taskText = taskText;
        this.firstPossibleAnswer = firstPossibleAnswer;
        this.secondPossibleAnswer = secondPossibleAnswer;
        this.thirdPossibleAnswer = thirdPossibleAnswer;
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

    public void setFirstPossibleAnswer(Answer firstPossibleAnswer) {
        this.firstPossibleAnswer = firstPossibleAnswer;
    }

    public Answer getSecondPossibleAnswer() {
        return secondPossibleAnswer;
    }

    public void setSecondPossibleAnswer(Answer secondPossibleAnswer) {
        this.secondPossibleAnswer = secondPossibleAnswer;
    }

    public Answer getThirdPossibleAnswer() {
        return thirdPossibleAnswer;
    }

    public void setThirdPossibleAnswer(Answer thirdPossibleAnswer) {
        this.thirdPossibleAnswer = thirdPossibleAnswer;
    }

    class Answer {
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
