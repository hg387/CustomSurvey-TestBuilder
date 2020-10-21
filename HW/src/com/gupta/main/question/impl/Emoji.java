package com.gupta.main.question.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Emoji extends MCQ implements Serializable {
    private static final long serialVersionUID = 6216604663640880744L;

    public Emoji(String question){
        super(question);
        this.hasAnswers = true;
        this.choices.addAll(Arrays.asList("Smiles","Frowns","Angry","Surprised","Sad"));
    }

    @Override
    public void setChoices(List<String> choices) { ; }

    @Override
    public void modifyChoice(String oldChoice, String newChoice){ ; }
}
