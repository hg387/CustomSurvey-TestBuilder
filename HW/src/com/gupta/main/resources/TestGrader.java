package com.gupta.main.resources;

import com.gupta.main.java.Test;
import com.gupta.main.question.Question;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestGrader implements Serializable {
    private static final long serialVersionUID = 6216604663640880744L;

    public String grade(Test test, ResponseCorrectAnswers RCA){
        List<Question> questions = test.getQuestions();
        List<List<String>> correctAnswers = test.getCorrectAnswers();
        int grade = 0;
        int questionsGradable = 0;
        int count = 0;
        for (int i=0; i<questions.size(); i++) {
            Question question = questions.get(i);
            if (question.hasAnswers()) {
                questionsGradable += 10;

                List<String> response = RCA.getResponses().get(i);
                List<String> correctAnswer = correctAnswers.get(count);
                count++;
                if (response.size() == correctAnswer.size()) {
                    if (question.getMatching() == null && !question.isRanking()) {
                        boolean counter = true;
                        for (String check : response) {
                            if (!correctAnswer.contains(check)) {
                                counter = false;
                                break;
                            }
                            //else{correctAnswer.remove(check);}
                        }

                        if (counter) {
                            grade += 10;
                        }
                    } else {
                        if (response.equals(correctAnswer)) {
                            grade += 10;
                        }
                    }
                }
            }
        }

        return ( grade + "/" + questionsGradable);
    }

    public List<String> grades(Test test){
        List<Question> questions = test.getQuestions();
        List<List<String>> correctAnswers = test.getCorrectAnswers();

        int[] grades = new int[test.getResponses().size()];
        int questionsGradable = 0;
        for (int i=0; i<questions.size(); i++){
            Question question = questions.get(i);
            if (question.hasAnswers()){
                questionsGradable += 10;
                for(int j=0; j<test.getResponses().size(); j++){
                    ResponseCorrectAnswers responses = test.getResponses().get(j);
                    List<String> response = responses.getResponses().get(i);
                    List<String> correctAnswer = correctAnswers.get(i);
                    
                    if (response.equals(correctAnswer)){grades[j] += 10;}
                }
            }
        }
        
        List<String> gradeFormat = new ArrayList<>();
        for (int grade : grades) {
            gradeFormat.add((grade + "/" + questionsGradable));
        }
        return gradeFormat;
    }
}
