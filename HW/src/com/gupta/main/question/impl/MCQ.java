package com.gupta.main.question.impl;

import com.gupta.main.question.Question;
import com.gupta.main.resources.inputOutput.Input;
import com.gupta.main.resources.inputOutput.Output;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MCQ extends Question implements Serializable {
    protected List<String> choices = new ArrayList<>();
    private static final long serialVersionUID = 6216604663640880744L;

    @Override
    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices = choices;
    }

    public void setChoices() throws IOException {
        Input input = new Input("Enter the number of choices for your multiple-choice question");
        Integer numberOfChoices = input.isInteger();
        List<String> choices = input.isMCQ(numberOfChoices);
        this.setChoices(choices);
    }

    public MCQ(String question){
        super(question);
        this.hasAnswers = true;
    }

    public void modifyChoice(String oldChoice, String newChoice){
        if (this.choices.contains(oldChoice)){
            this.choices.remove(oldChoice);
            this.choices.add(newChoice);
        }
    }

    @Override
    public void display(){
        new Output().display(this.question);
        for (int i=0; i<this.choices.size(); i++){
            new Output().display((i+1) + ") " + this.choices.get(i));
        }
    }

}
