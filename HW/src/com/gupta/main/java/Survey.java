package com.gupta.main.java;

import com.gupta.main.question.Question;
import com.gupta.main.question.impl.*;
import com.gupta.main.resources.ReaderWriter;
import com.gupta.main.resources.ResponseCorrectAnswers;
import com.gupta.main.resources.SurveyModifier;
import com.gupta.main.resources.inputOutput.Input;
import com.gupta.main.resources.inputOutput.Output;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Survey implements Serializable {
    public String name;
    public List<Question> questions = new ArrayList<>();
    private static final long serialVersionUID = 6216604663640880744L;
    List<ResponseCorrectAnswers> responses = new ArrayList<>();
    public final String QUESTIONS = "1) Add a new T/F question\n" +
            "2) Add a new multiple-choice question\n" +
            "3) Add a new short answer question\n" +
            "4) Add a new essay question\n" +
            "5) Add a new emoji question\n" +
            "6) Add a new matching question\n" +
            "7) Add a new ranking question\n";

    public void addQuestion(Integer userResponse) throws Exception {
        String question = null;
        if (userResponse == 1){
            question = new Input("Enter the prompt for your True/False question:").isString(1);
            while (question.length() == 0){
                new Output().display("Entered a blank question");
                question = new Input("Enter the prompt for your True/False question:").isString(1);
            }
            TandF tandF = new TandF(question);
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
            this.questions.add(mcq);
        }
        else if (userResponse == 3){
            question = new Input("Enter the prompt for your short question:").isString(1);
            while (question.length() == 0){
                new Output().display("Entered a blank question");
                question = new Input("Enter the prompt for your short question:").isString(1);
            }
            ShortAnswer shortAnswer = new ShortAnswer(question);
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
            this.questions.add(emoji);
        }
        else if (userResponse == 6){
            question = new Input("Enter the prompt for your Matching question, using this format columnA : columnB").isString(1);
            while (question.length() == 0){
                new Output().display("Entered a blank question");
                question = new Input("Enter the prompt for your Matching question, using this format columnA : columnB").isString(1);
            }
            Matching matching = new Matching(question);
            matching.setMatching();
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
            this.questions.add(ranking);
        }
    }


    public Survey(String name) throws IOException {
        this.name = name;
    }

    public void create() throws Exception {
        new Output().display(this.name +" is added");
        Integer numberOfQuestions = new Input("Please enter number of questions:").isInteger();
        new Output().display("Please select Questions from these following types:");



        for (int i=0; i<numberOfQuestions; i++) {
            Input input = new Input(this.QUESTIONS);
            new Output().display("Question #" +(i+1));
            Integer userResponse = input.isInteger();
            while(userResponse > 7){
                new Output().display("\nPlease enter a positive integer less than 8");
                userResponse = new Input("").isInteger();
            }
            this.addQuestion(userResponse);
        }
    }

    public void display(){
        new Output().display("\t" + "---- " +this.name + " Begin " + " ----" +"\n");
        for (int i=0; i<this.questions.size();i++){
            new Output().displayInLine("Q"+ (i+1) + ") ");
            this.questions.get(i).display();
            new Output().display("");
        }
        new Output().display("\n\t" + "---- " + this.name + " End " + " ----");
    }

    public void save() throws IOException {
        Survey temp = this;
        if (new ReaderWriter().exits(this.getName())){new ReaderWriter().delete(this.getName());}
        new ReaderWriter().write(temp,(temp.name + ".dat"));
    }

    public Survey load() throws IOException {
        List<String> files = new ReaderWriter().listAllFiles();
        String fileNames = "";
        new Output().display("Please select a file to load:");

        if (files.size() == 0 ){new Output().display("\nThere are no related files in the current directory");return null;}
        for (int i=0; i<files.size(); i++){
            fileNames += ((i+1) + ") " + files.get(i) + "\n");
        }

        String fileName = "";
        while (! (fileName = new Input(fileNames).isString(1)).contains("survey")){
            new Output().display("Not entered a survey");
            new Output().display("Please select a file to load:");
        }
        Survey survey = new ReaderWriter().read(fileName + ".dat", "survey");

        return survey;
    }

    public Survey take() throws Exception {
        ResponseCorrectAnswers RCA = new ResponseCorrectAnswers();
        List<List<String>> responseCorrectAnswers = RCA.getResponses();

        Survey survey = this.load();
        new ReaderWriter().delete(survey.getName());
        if (survey != null){
            new Output().display("Please enter Integers to represent your responses for True/False, " +
                    "Multiple choice, and emoji type questions\n");
            for (Question question: survey.getQuestions()){
                question.display();
                if (question.getMatching() == null){
                    if (question.getChoices() != null) {
                        Integer max = question.getChoices().size();
                        if ((question.getChoices().size() == 2) && (question.getChoices().get(0).equalsIgnoreCase("true"))
                                && (question.getChoices().get(1).equalsIgnoreCase("false"))  && !question.isRanking()) {
                            String response = new Input("Please enter your response:").isInteger().toString();
                            while(Integer.parseInt(response) > 2){
                                new Output().display("Entered response greater than 2");
                                response = new Input("Please enter your response:").isInteger().toString();
                            }
                            responseCorrectAnswers.add(Arrays.asList(response));
                        } else if (question.getChoices().size() != 2  && !question.isRanking()) {
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

            survey.getResponses().add(RCA);
            new ReaderWriter().write(survey,(survey.name + ".dat"));

        }
        return survey;
    }

    public Survey modify() throws Exception {
        Survey survey = this.load();
        new ReaderWriter().delete(survey.getName());

        new SurveyModifier().modify(survey);
        new ReaderWriter().write(survey, (survey.name + ".dat") );
        return survey;
    }

    public void tabulate() throws IOException{
        Survey survey = this.load();
        List<ResponseCorrectAnswers> RCAs = survey.getResponses();
        if (RCAs.isEmpty()){new Output().display("survey not taken yet\n");return;}

        for (int i=0; i<survey.getQuestions().size(); i++){

            Question question = survey.getQuestions().get(i);
            new Output().display("");
            question.display();
            new Output().display("\nResponses:");
            for (ResponseCorrectAnswers response: RCAs){
                List<String> tmp = response.getResponses().get(i);
                /*System.out.println(tmp);*/
                new Output().display("");
                tmp.forEach(t -> new Output().display(t));
            }

            new Output().display("\n Tabulation");
            List<String> choices;
            if (((choices = question.getChoices()) != null) && !question.isRanking()){
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
                    new Output().display( "Frequency:"+ map.get(mapValue));
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


    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public List<ResponseCorrectAnswers> getResponses() {
        return responses;
    }

    public void setResponses(List<ResponseCorrectAnswers> responses) {
        this.responses = responses;
    }
}
