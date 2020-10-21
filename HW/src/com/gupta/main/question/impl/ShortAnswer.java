package com.gupta.main.question.impl;

import com.gupta.main.resources.inputOutput.Input;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortAnswer extends Essay implements Serializable {
    private Integer responseLength = 8;
    private static final long serialVersionUID = 6216604663640880744L;

    public Integer getResponseLength() {
        return responseLength;
    }

    public ShortAnswer(String question){
        super(question);
        this.hasAnswers = true;
    }

}
