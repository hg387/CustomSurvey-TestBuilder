package com.gupta.test.java;

import com.gupta.main.resources.inputOutput.Output;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OutputTester {

    @Test
    public void testOutput(){
        new Output().display("sample test String");
        new Output().display(2.0);
        new Output().display(2);
        new Output().display(2.3);
        new Output().display(Arrays.asList(1, 2, 3));
    }

    @Test
    public void testList(){
        List<Integer> temp = new ArrayList<>();
        temp.add(1);
        temp.add(1);
        temp.add(1);
        temp.add(1);
        temp.add(1);


        temp.forEach(name -> new Output().display(name));
    }
}
