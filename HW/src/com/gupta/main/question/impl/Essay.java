package com.gupta.main.question.impl;

import com.gupta.main.question.Question;

import java.io.Serializable;

public class Essay extends Question implements Serializable {
    protected String choice;
    private static final long serialVersionUID = 6216604663640880744L;

    /*public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }
*/
    public Essay(String question){
        super(question);
        this.responseCount = 1;
    }
}
