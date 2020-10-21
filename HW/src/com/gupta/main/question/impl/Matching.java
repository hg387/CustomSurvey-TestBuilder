package com.gupta.main.question.impl;

import com.gupta.main.question.Question;
import com.gupta.main.resources.inputOutput.Input;
import com.gupta.main.resources.inputOutput.Output;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Matching extends Question implements Serializable {
    protected HashMap<String, String> matching = new HashMap<>();
    private static final long serialVersionUID = 6216604663640880744L;

    public List<String> getValues() {
        return new ArrayList<>(this.matching.values());
    }

    public HashMap<String, String> getMatching() {
        return this.matching;
    }

    public void setMatching(String a, String b) {
        this.matching.put(a, b);
    }

    public void setResponseCount(){
        this.responseCount = matching.size();
    }

    public void setMatching() throws IOException {
        Input input = new Input("Enter the number of rows for your matching question, using this format columnA : columnB");
        Integer numberOfMatching = input.isInteger();
        input.isMatching(numberOfMatching, this.matching);
    }

    public Matching(String question){super(question); this.hasAnswers = true;}

    @Override
    public void display(){
        new Output().display(this.question);
        for (String matching:this.matching.keySet()){
            new Output().display(matching + "\t:\t" + this.matching.get(matching));
        }
    }

}
