package com.gupta.main.resources.inputOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Input implements Serializable {
    private static final long serialVersionUID = 6216604663640880744L;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public Input(String prompt){
        new Output().display(prompt);
    }

    public Integer isInteger() throws IOException {
        boolean counter = true;
        String input = "";
        Integer inputInteger = 0;
        while (counter) {
            try {
                input = this.reader.readLine();
                inputInteger = Integer.valueOf(input);
                if (inputInteger <= 0) {
                    new Output().display("\nPlease enter a positive Integer:");
                    continue;
                }
                counter = false;
            } catch (NumberFormatException e) {
                new Output().display("\nNot an Integer, Please enter a positive Integer:");
            }
        }
        //this.reader.close();
        return inputInteger;
    }

    public String isString() throws IOException {
        String input;
        String response = "";
        while((input = this.reader.readLine()) != null && (input.length() != 0)){
            //if (input.equalsIgnoreCase("done")) break;
            response += (input + " ");
        }
        //this.reader.close();
        return response;
    }

    public List<String> isResponse() throws IOException {
        String input;
        List<String> response = new ArrayList<>();
        while((input = this.reader.readLine()) != null && (input.length() != 0)){
            //if (input.equalsIgnoreCase("done")) break;
            response.add(input);
        }
        //this.reader.close();
        return response;
    }

    public List<String> isResponse(Integer max) throws IOException {
        String input;
        List<String> response = new ArrayList<>();
        boolean counter = true;
        while((input = this.reader.readLine()) != null && (input.length() != 0)){
            try{
                Integer temp = Integer.valueOf(input.trim());
                if (temp > max || temp <= 0) {new Output().display("Invalid Integer, re-enter");continue;}
                if (response.contains(input)){new Output().display("Entered response already exists, enter a new one"); continue;}
            }
            catch(Exception e){
                new Output().display("Not entered an integer");
                continue;
            }
            response.add(input);
        }
        return response;
    }

    public String isString(Integer count) throws IOException {
        String input;
        String response = "";
        for (Integer i=0; i<count; i++) {
            input = this.reader.readLine();
            if ((input.length() == 0)) {
                new Output().display("Entered a blank choice\n");
                    i--;
                    continue;
            }
            response += (input);
        }
        //this.reader.close();
        return response;
    }

    public List<String> isMCQ(Integer count) throws IOException {
        List<String> choices = new ArrayList<>();
        String response = "";

        for (Integer i=0; i<count; i++){
            new Output().display("Please enter choice number " + (i+1) + ":");
            response = this.reader.readLine();
            if (response.length() != 0){ choices.add(response);continue;}
            new Output().display("Please do not enter a blank choice");
            i--;
        }

        //this.reader.close();
        return choices;
    }

    public List<String> isRanking(Integer count) throws IOException {
        List<String> choices = new ArrayList<>();
        String response = "";

        for (Integer i=0; i<count; i++){
            new Output().display("Please enter choice number " + (i+1) + ":");
            response = this.reader.readLine();
            if (response.length() != 0){
                if (choices.contains(response.trim())){
                    new Output().display("Entered choice already present");
                    i--;
                    continue;
                }
                choices.add(response);
                continue;
            }
            new Output().display("Please do not enter a blank choice");
            i--;
        }

        //this.reader.close();
        return choices;
    }

    public List<String> isShortAnswer(Integer count, Integer responseLength) throws IOException {
        List<String> choices = new ArrayList<>();
        String response = "";

        for (Integer i=0; i<count; i++){
            new Output().display("Please enter choice number " + (i+1) + ":");
            response = this.reader.readLine();
            if (response.length() != 0){
                if (response.length() > responseLength){new Output().display("Exceeded the word limit"); i--; continue;}
                choices.add(response);
                continue;
            }
            new Output().display("Please do not enter a blank choice");
            i--;
        }

        //this.reader.close();
        return choices;
    }

    public List<String> isMCQ(Integer count, List<String> userChoices) throws IOException {
        List<String> choices = new ArrayList<>();
        String response = "";

        for (Integer i=0; i<count; i++){
            new Output().display("Please enter correct choice number " + (i+1) + ":");
            response = this.reader.readLine();
            if (response.length() != 0){
                try{
                    Integer tmp = Integer.valueOf(response);
                    if (tmp > userChoices.size() || tmp == 0 || choices.contains(response)){throw new NumberFormatException();}
                    choices.add(response);
                    continue;
                }
                catch(NumberFormatException e){
                    new Output().display("Answer entered is not an integer " +
                        "or greater than the number of options");
                    i--;
                    continue;
                }
            }
            new Output().display("Please do not enter a blank choice");
            i--;
        }

        //this.reader.close();
        return choices;
    }

    public void isMatching(Integer numberOfMatching, HashMap<String, String> matching) throws IOException{
        for (int i=1; i<=numberOfMatching; i++){
            String question = null;
            List<String> tmp = new ArrayList<>();
            new Output().display("\nPlease enter choice number " + i + ":");
            if (((question = this.reader.readLine()) != null) && (question.length() != 0)) {
                    tmp = Arrays.asList(question.split(":"));
                    if (tmp.size() == 2) {
                        matching.put(tmp.get(0).trim(), tmp.get(1).trim());
                        continue;
                    }
                    new Output().display("Invalid question format");
                    i--;
                    continue;
            }
            new Output().display("Please do not enter a blank matching");
            i--;
        }
        //reader.close();
    }

    public List<String> isMatching(HashMap<String, String> matching, String correct) throws IOException{
        List<String> values = new ArrayList<>(matching.values());
        List<String> choices = new ArrayList<>();

        for (String colA : matching.keySet()){
            Input input = new Input(("Please enter "+ correct +" for "+ colA));
            String correctAnswer = input.isString(1).trim();
            while(!values.contains(correctAnswer)){
                new Output().display("Answer entered is not in the choices");
                correctAnswer = new Input(("Please enter "+ correct +" for "+ colA)).isString(1).trim();
            }
            choices.add(correctAnswer.trim());
        }

        return choices;
    }

    public boolean modify(String prompt) throws IOException {
        new Output().display(prompt);
        String input = this.reader.readLine();

        while(input.length() == 0 || (!input.equalsIgnoreCase("yes")
                && !input.equalsIgnoreCase("no"))){
            new Output().display("Please enter either yes or no");
            input = this.reader.readLine();
        }

        return !input.equalsIgnoreCase("no");
    }

    public static void main(String[] args) throws IOException {
        Input input = new Input("Enter the prompt here");
        //Integer userInput = input.isInteger();
        String userInput = input.isString();
        //List<String> userInput = input.isMCQ(2);
        /*HashMap<String, String> matching = new HashMap<>();
        input.isMatching(2, matching);*/

        new Output().display(userInput);
    }

}
