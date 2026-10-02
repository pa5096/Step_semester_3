package oop.class_problems;

import java.util.ArrayList;
import java.util.List;

abstract class Question {
    private String questionText;

    public Question(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public abstract boolean evaluate(String answer);
}

class MultipleChoiceQuestion extends Question {
    private String correctAnswer;

    public MultipleChoiceQuestion(String questionText, String correctAnswer) {
        super(questionText);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Student {
    private String studentId;
    private String name;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Examination {
    private String title;
    private List<Question> questions;

    public Examination(String title) {
        this.title = title;
        this.questions = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }

    public List<Question> getQuestions() {
        return questions;
    }
}

class Attempt {
    private Student student;
    private Examination examination;
    private List<String> studentAnswers;
    private boolean submitted;
    private int score;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
        this.studentAnswers = new ArrayList<>();
        this.submitted = false;
        this.score = 0;
        System.out.println("Examination '" + examination.getTitle() + "' started by Student.");
    }

    public void answerQuestion(int questionIndex, String answer) {
        if (submitted) {
            System.out.println("Cannot change answers: Attempt already submitted.");
            return;
        }
        studentAnswers.add(answer);
        System.out.println("Question " + (questionIndex + 1) + " answered with '" + answer + "'.");
    }

    public void submit() {
        if (submitted) {
            System.out.println("Attempt already submitted.");
            return;
        }
        submitted = true;
        System.out.println("Examination '" + examination.getTitle() + "' submitted successfully.");
        calculateResult();
    }

    private void calculateResult() {
        List<Question> questions = examination.getQuestions();
        int correctCount = 0;
        for (int i = 0; i < studentAnswers.size() && i < questions.size(); i++) {
            if (questions.get(i).evaluate(studentAnswers.get(i))) {
                correctCount++;
            }
        }
        this.score = correctCount;
        System.out.println("Result for '" + examination.getTitle() + "' attempt: " + score + "/" + questions.size() + " correct.");
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        Student student = new Student("S101", "Alice");
        Examination exam = new Examination("Math Quiz");
        
        exam.addQuestion(new MultipleChoiceQuestion("What is 2 + 2?", "A"));
        exam.addQuestion(new MultipleChoiceQuestion("What is 5 * 3?", "B"));

        Attempt attempt = new Attempt(student, exam);
        attempt.answerQuestion(0, "A");
        attempt.answerQuestion(1, "C");
        attempt.submit();
    }
}