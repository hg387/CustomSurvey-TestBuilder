package com.gupta.main.question.impl;

import com.gupta.main.resources.inputOutput.Input;
import com.gupta.main.resources.inputOutput.Output;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class Ranking extends MCQ implements Serializable {
    private static final long serialVersionUID = 6216604663640880744L;

    public Ranking(String question){
        super(question);
        this.isRanking = true;
    }

    @Override
    public void setChoices() throws IOException {
        Input input = new Input("Enter the number of choices for your ranking question");
        Integer numberOfChoices = input.isInteger();
        List<String> choices = input.isRanking(numberOfChoices);
        this.setChoices(choices);
    }

    public boolean check(List<String> responses) throws Exception{
        for (String response: responses){
            try {
                Integer tmp = Integer.parseInt(response.trim());
                if (tmp > this.getChoices().size()) throw new NumberFormatException();
            }
            catch (NumberFormatException e){ new Output().display("Not entered Integers");return false;}
        }

        return true;
    }

}
