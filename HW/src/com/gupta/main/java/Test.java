package com.gupta.main.java;

import com.gupta.main.question.Question;
import com.gupta.main.question.impl.*;
import com.gupta.main.resources.*;
import com.gupta.main.resources.inputOutput.Input;
import com.gupta.main.resources.inputOutput.Output;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Test extends Survey implements Serializable {
    List<List<String>> correctAnswers = new ArrayList<>();
    private static final long serialVersionUID = 6216604663640880744L;
    public List<String> grades = new ArrayList<>();
    public Test(String name) throws IOException {
        super(name);
    }

    public void addQuestion(Integer userResponse) throws Exception {
        String question = null;
        if (userResponse == 1){
            question = new Input("Enter the prompt for your True/False question:").isString(1);
            while (question.length() == 0){
                new Output().display("Entered a blank question");
                question = new Input("Enter the prompt for your True/False question:").isString(1);
            }
            TandF tandF = new TandF(question);

            Integer correctAnswer = 0;
            while ((correctAnswer = new Input("Enter the correct Answer, 1 for true and 2 for false").isInteger()) > 2){
                new Output().display("Invalid Integer");
            }
            this.correctAnswers.add(Arrays.asList(correctAnswer.toString()));
            this.questions.add(tandF);
        }
        else if (userResponse == 2){
            question = new Input("Enter the prompt for your MCQ question:").isString(1);
            while (question.length() == 0){
                new Output().display("Entered a blank question");
                question = new Input("Enter the prompt for your MCQ question:").isString(1);
            }
            MCQ mcq = new MCQ(question);
            mcq.setChoices();

            Input input = new Input("Enter the number of correct Answer/s");
            Integer numberOfCorrectAnswers = input.isInteger();
            while (numberOfCorrectAnswers > mcq.getChoices().size()){
                new Output().display("You have entered more than the choices, Try again");
                numberOfCorrectAnswers = input.isInteger();
            }

            List<String> correctAnswer = input.isMCQ(numberOfCorrectAnswers, mcq.getChoices());
            this.correctAnswers.add(correctAnswer);
            this.questions.add(mcq);
        }
        else if (userResponse == 3){
            question = new Input("Enter the prompt for your short question:").isString(1);
            while (question.length() == 0){
                new Output().display("Entered a blank question");
                question = new Input("Enter the prompt for your short answer question:").isString(1);
            }

            ShortAnswer shortAnswer = new ShortAnswer(question);
            Input input = new Input("Enter the number of correct Answer/s, each answer has a word limit of 8");
            Integer numberOfCorrectAnswers = input.isInteger();

            List<String> correctAnswer = input.isShortAnswer(numberOfCorrectAnswers, shortAnswer.getResponseLength());
            this.correctAnswers.add(correctAnswer);
            this.questions.add(shortAnswer);
        }
        else if (userResponse == 4){
            question = new Input("Enter the prompt for your essay question:").isString(1);
            while (question.length() == 0){
                new Output().display("Entered a blank question");
                question = new Input("Enter the prompt for your essay question:").isString(1);
            }
            Essay essay = new Essay(question);
            this.questions.add(essay);
        }
        else if (userResponse == 5){
            question = new Input("Enter the prompt for your emoji question:").isString(1);
            while (question.length() == 0){
                new Output().display("Entered a blank question");
                question = new Input("Enter the prompt for your emoji question:").isString(1);
            }
            Emoji emoji = new Emoji(question);

            new Output().display("\nKeep in mind: answer choices are 1 for Smiles, 2 for Frowns, 3 for Angry, 4 for Surprised, 5 for Sad");
            Input input = new Input("Enter the number of correct Answer/s");
            Integer numberOfCorrectAnswers = input.isInteger();
            while (numberOfCorrectAnswers > emoji.getChoices().size()){
                new Output().display("You have entered more than the choices, Try again");
                numberOfCorrectAnswers = input.isInteger();
            }

            List<String> correctAnswer = input.isMCQ(numberOfCorrectAnswers, emoji.getChoices());
            this.correctAnswers.add(correctAnswer);
            this.questions.add(emoji);
        }
        else if (userResponse == 6){
            Input input = new Input("Enter the prompt for your Matching question, using this format columnA : columnB");
            question = input.isString(1);
            while (question.length() == 0){
                new Output().display("Entered a blank question");
                question = new Input("Enter the prompt for your Matching question, using this format columnA : columnB").isString(1);
            }
            Matching matching = new Matching(question);
            matching.setMatching();

            List<String> correctAnswer = input.isMatching(matching.getMatching(), "correct choice");
            this.correctAnswers.add(correctAnswer);
            this.questions.add(matching);
        }
        else if (userResponse == 7){
            question = new Input("Enter the prompt for your Ranking question").isString(1);
            while (question.length() == 0){
                new Output().display("Entered a blank question");
                question = new Input("Enter the prompt for your Ranking question").isString(1);
            }
            Ranking ranking = new Ranking(question);
            ranking.setChoices();

            List<String> correctAnswers = new ArrayList<>();
            while (((correctAnswers = new Input("Please enter your ranks as Integers:").isResponse(ranking.getChoices().size())).size() != ranking.getChoices().size())){
                new Output().display("You have not entered exactly the same number of the choices\n");
            }
            this.correctAnswers.add(correctAnswers);
            this.questions.add(ranking);
        }
    }

    @Override
    public void display(){
        Integer count = 0;
        new Output().display("\t" + "---- " + this.name + " Begin " + " ----" + "\n");
        for (int i=0; i<this.questions.size();i++){
            new Output().displayInLine("Q"+ (i+1) + ") ");
            this.questions.get(i).display();
            if (this.questions.get(i).hasAnswers()) {
                new Output().display("\nThe correct answer/s for question " + (i + 1) + " :");
                (this.correctAnswers.get(count)).forEach(temp -> new Output().display(temp));
                count++;
            }
            new Output().display("");
        }
        new Output().display("\n\t" + "---- " + this.name + " End " + "----");
    }

    @Override
    public Test load() throws IOException {
        List<String> files = new ReaderWriter().listAllFiles();
        String fileNames = "";
        new Output().display("Please select a file to load:");

        if (files.size() ==0 ){new Output().display("\nThere are no related files in the current directory"); return null;}
        for (int i=0; i<files.size(); i++){
            fileNames += ((i+1) + ") " + files.get(i) + "\n");
        }

        String fileName = "";
        while (! (fileName = new Input(fileNames).isString(1)).contains("test")){
            new Output().display("Not entered a test");
            new Output().display("Please select a file to load:");
        }
        Test test = (Test) new ReaderWriter().read(fileName + ".dat", "test");

        return test;
    }

    @Override
    public void save() throws IOException {
        Test temp = this;
        if (new ReaderWriter().exits(this.getName())){new ReaderWriter().delete(this.getName());}
        new ReaderWriter().write(temp,(temp.name + ".dat"));
    }

    @Override
    public Test take() throws Exception {
        ResponseCorrectAnswers RCA = new ResponseCorrectAnswers();
        List<List<String>> responseCorrectAnswers = RCA.getResponses();

        Test test = this.load();
        new ReaderWriter().delete(test.getName());
        if (test != null){
            new Output().display("Please enter Integers to represent your responses for True/False, " +
                    "Multiple choice, and emoji type questions\n");
            for (Question question: test.getQuestions()){
                question.display();
                if (question.getMatching() == null){
                    if (question.getChoices() != null) {
                        Integer max = question.getChoices().size();
                        if ((question.getChoices().size() == 2) && (question.getChoices().get(0).equalsIgnoreCase("true"))
                                && (question.getChoices().get(1).equalsIgnoreCase("false")) && !question.isRanking()) {
                            String response = new Input("Please enter your response:").isInteger().toString();
                            while(Integer.parseInt(response) > 2){
                                new Output().display("Entered response greater than 2");
                                response = new Input("Please enter your response:").isInteger().toString();
                            }
                            responseCorrectAnswers.add(Arrays.asList(response));
                        } else if (question.getChoices().size() != 2 && !question.isRanking()) {
                            List<String> responses = new ArrayList<>();
                            while ((responses = new Input("Please enter your response:").isResponse(max)).size() == 0 ||
                                    ((responses).size() > question.getChoices().size())){
                                if (responses.size() == 0) new Output().display("Entered a blank choice");
                                else new Output().display("Entered more than the choices");
                            }
                            responseCorrectAnswers.add(responses);
                        } else if ((question.getChoices().size() != 0) && question.isRanking()) {
                            List<String> responses = new ArrayList<>();
                            while (((responses = new Input("Please enter your ranks as Integers:").isResponse(max)).size() != question.getChoices().size())
                                    && (question.check(responses))) {
                                new Output().display("You have not entered exactly the same number of the choices\n");
                            }

                            responseCorrectAnswers.add(responses);
                        }
                    }
                    else {
                        List<String> responses = new ArrayList<>();
                        while ((responses = new Input("Please enter your response:").isResponse()).size() == 0){
                            new Output().display("Entered a blank");
                        }
                        responseCorrectAnswers.add(responses);
                    }
                }
                else{
                    List<String> responses = new Input("").isMatching(question.getMatching(),"your response");
                    responseCorrectAnswers.add(responses);
                }
            }

            test.getResponses().add(RCA);
            String grade = new TestGrader().grade(test, RCA);
            test.getGrades().add(grade);

            new ReaderWriter().write(test,(test.name + ".dat"));
        }
        return test;
    }

    @Override
    public void tabulate() throws IOException{
        Test test = this.load();
        List<ResponseCorrectAnswers> RCAs = test.getResponses();
        if (RCAs.isEmpty()){new Output().display("test not taken yet\n");return;}

        for (int i=0; i<test.getQuestions().size(); i++){

            Question question = test.getQuestions().get(i);
            question.display();
            new Output().display("\nResponses:");
            for (ResponseCorrectAnswers response: RCAs){
                List<String> tmp = response.getResponses().get(i);
                new Output().display("");
                tmp.forEach(temp -> new Output().display(temp));
            }

            new Output().display("\n Tabulation");
            List<String> choices;
            if ((choices = question.getChoices()) != null && !question.isRanking()){
                for (Integer j =1; j<=choices.size(); j++){
                    int count =0;
                    for (ResponseCorrectAnswers response: RCAs){
                        List<String> tmp = response.getResponses().get(i);
                        if (tmp.contains(j.toString())) count++;
                    }
                    new Output().display(choices.get(j-1) + " : " + count);
                }
                new Output().display("");
            }
            else if (question.getMatching() != null || question.isRanking()){
                HashMap<List<String>, Integer> map = new HashMap<>();

                for (ResponseCorrectAnswers response: RCAs){
                    List<String> tmp = response.getResponses().get(i);
                    if (map.containsKey(tmp)){
                        Integer counter = map.get(tmp) + 1;
                        map.put(tmp, counter);
                        continue;
                    }
                    map.put(tmp, 1);
                }

                for (List<String> mapValue: map.keySet()){
                    new Output().display("Frequency:"+ map.get(mapValue));
                    /*map.values().forEach(System.out::println);*/
                    mapValue.forEach(temp -> new Output().display(temp));
                    new Output().display("");
                }
            }
            else{
                HashMap<String, Integer> map = new HashMap<>();

                for (ResponseCorrectAnswers response: RCAs){
                    List<String> temps = response.getResponses().get(i);
                    for (String tmp: temps) {
                        if (map.containsKey(tmp)) {
                            Integer counter = map.get(tmp) + 1;
                            map.put(tmp, counter);
                            continue;
                        }
                        map.put(tmp, 1);
                    }
                }

                for (String mapValue: map.keySet()){
                    new Output().display(map.get(mapValue));
                    /*map.values().forEach(System.out::println);*/
                    /*mapValue.forEach(System.out::println);*/
                    /*System.out.println("");*/
                    new Output().display(mapValue);
                }
            }
        }
    }

    @Override
    public Test modify() throws Exception {
        Test test = this.load();
        new ReaderWriter().delete(test.getName());

        new TestModifier().modify(test);
        new ReaderWriter().write(test, (test.name + ".dat") );
        return test;
    }

    public String gradeTest(){
        Integer maxIndex = this.getGrades().size() - 1;
        if (this.getGrades().isEmpty()){
            return "You have not taken the test";
        }
        return this.getGrades().get(maxIndex);
    }

    public List<List<String>> getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(List<List<String>> correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public List<String> getGrades() {
        return grades;
    }

    public void setGrades(List<String> grades) {
        this.grades = grades;
    }
}
