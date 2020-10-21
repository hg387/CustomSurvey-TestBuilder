package com.gupta.test.java;

import com.gupta.main.resources.inputOutput.Output;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class ListTester {

    @Test
    public void check(){
        HashMap<List<String>, Integer> map = new HashMap();
        List<List<String>> list = new ArrayList<>();

        List<String> s1 = Arrays.asList("1", "2", "3");
        List<String> s2 = Arrays.asList("1", "2", "3");
        list.add(s1);
        if (list.contains(s2)){new Output().display(1);}

        map.put(s1, 1);
        if (map.containsKey(s2)){Integer tmp = map.get(s2) +1; map.put(s2, tmp); new Output().display(map.get(s2));}

//        map.put(s1, 1);
//        map.put(s2, 2);
        /*if (s1.equals(s2)){
            Integer tmp = map.get(s2);
            map.put(s2, tmp++);
        }*/

    }

    @Test
    public void checkList(){
        List<String> s1 = Arrays.asList("1", "2", "3");
        List<String> s2 = Arrays.asList("2", "1", "3");
        if (s1.equals(s2)){
            new Output().display("s1 equals s2");
        }

    }
}
