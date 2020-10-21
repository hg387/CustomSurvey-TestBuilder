package com.gupta.main.question;

import com.gupta.main.resources.inputOutput.Output;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Question implements Serializable {
    protected Integer responseCount;
    protected String question = null;
    protected boolean hasAnswers = false;
    protected boolean isRanking = false;
    private static final long serialVersionUID = 6216604663640880744L;

    public Question(String question){
        this.question = question;
    }

    public void display(){
        new Output().display(this.question);
    }

    public boolean equals(Question question){
        return question.getQuestion().equals(this.question);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getResponseCount() {
        return responseCount;
    }

    public void setChoices() throws IOException {;}

    public List<String> getChoices() {
        return null;
    }

    public boolean hasAnswers() {
        return hasAnswers;
    }

    public void setHasAnswers(boolean hasAnswers) {
        this.hasAnswers = hasAnswers;
    }

    public HashMap<String, String> getMatching() {
        return null;
    }

    public boolean isRanking() {
        return isRanking;
    }

    public boolean check(List<String> responses) throws Exception{return false;}
}
