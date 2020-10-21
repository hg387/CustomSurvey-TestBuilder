package com.gupta.main.resources;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResponseCorrectAnswers implements Serializable {
    private static final long serialVersionUID = 6216604663640880744L;
    private List<List<String>> responses = new ArrayList<>();

    public List<List<String>> getResponses() {
        return responses;
    }

    public void setResponses(List<List<String>> responses) {
        this.responses = responses;
    }
}
