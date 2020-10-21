package com.gupta.main.question.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class TandF extends MCQ implements Serializable {
    private static final long serialVersionUID = 6216604663640880744L;

    public TandF(String question){
        super(question);
        this.choices.addAll(Arrays.asList("true", "false"));
        this.hasAnswers = true;
        this.responseCount = 1;
    }

    @Override
    public void setChoices(List<String> choices) { ; }

    @Override
    public void setChoices() { ; }

    @Override
    public void modifyChoice(String oldChoice, String newChoice){ ; }
}
