package com.gupta.test.java;

import com.gupta.main.resources.inputOutput.Input;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static junit.framework.TestCase.assertEquals;

public class InputTester {
    final String PROMPT = "Enter your prompt here:\n";


    @Test
    public void isIntegerTest() throws IOException {
        String case1 = "-1";
        String case2 = "10 20 30";
        String case3 = "string";
        String case4 = "10";
        InputStream inputStream= new ByteArrayInputStream(case4.getBytes());
        System.setIn(inputStream);

        Input input = new Input(PROMPT);
        assertEquals(Integer.valueOf(case4), input.isInteger());
    }

}
