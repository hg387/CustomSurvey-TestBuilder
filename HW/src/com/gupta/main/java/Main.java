package com.gupta.main.java;

import com.gupta.main.resources.ReaderWriter;
import com.gupta.main.resources.inputOutput.Input;
import com.gupta.main.resources.inputOutput.Output;

import java.io.IOException;
import java.io.Serializable;
/*To do:
    Added ranking question in add and take survey
    Add/check the same in modify
    Do the same two for test with added answers
**/
public class Main implements Serializable {
    private Integer numberOfSurveys = 1;
    private Integer numberOfTests = 1;
    private static final long serialVersionUID = 6216604663640880744L;
    private final String MENU1 = "\t----Menu1----\n1) Survey\n2) Test\nPlease enter your choice:";
    private final String SURVEY_MENU2 = "\t----Menu2----\n1) Create a new Survey\n" +
            "2) Display a Survey\n" +
            "3) Load a Survey\n" +
            "4) Save a Survey\n" +
            "5) Modify an Existing Survey\n" +
            "6) Take a Survey\n" +
            "7) Tabulate a Survey\n" +
            "8) Quit \n\nPlease enter your choice";
    private final String TEST_MENU2= "\t----Menu2----\n1) Create a new Test\n" +
            "2) Display a Test\n" +
            "3) Load a Test\n" +
            "4) Save a Test\n" +
            "5) Modify an Existing Test\n" +
            "6) Take a Test\n" +
            "7) Tabulate a Test\n" +
            "8) Grade a Test\n" +
            "9) Quit \n\nPlease enter your choice";

    public void addSurvey() throws Exception {
        Survey survey = new Survey("survey " + this.getNumberOfSurveys());

        Integer userResponse = new Input(this.SURVEY_MENU2).isInteger();

        while(userResponse != 8) {

            if (userResponse == 1) {
                this.numberOfSurveys = new ReaderWriter().getNumber("survey");
                survey = new Survey("survey " + this.getNumberOfSurveys());
                survey.create();
//                this.increaseNumberOfSurveys();
            } else if (userResponse == 2) {
                if (survey.getQuestions().size() != 0) {
                    survey.display();
                } else {
                    new Output().display("No Survey in Memory");
                    this.addSurvey();
                }
            } else if (userResponse == 3) {
                try {
                    survey = survey.load();
                    new Output().display(survey.getName() + " loaded");
                }
                catch(NullPointerException e){new Output().display("\tLoad failed");}
                //surveyToDisplay.display();
            } else if (userResponse == 4) {
                survey.save();
                new Output().display(survey.getName() + " saved");
            }
            else if (userResponse == 5){
                new Output().display("\t---- " + "Modify a survey ----" + "\n");
                survey = survey.modify();
            }
            else if (userResponse == 6){
                new Output().display("\t---- " + "Take a survey ----" + "\n");
                survey = survey.take();
            }
            else if (userResponse == 7){
                survey.tabulate();
            }

            userResponse = new Input(this.SURVEY_MENU2).isInteger();
            while(userResponse > 8){
                new Output().display("\nPlease enter a positive integer less than 9");
                userResponse = new Input("").isInteger();
            }
        }

        new Output().display("\t Exiting...");
        System.exit(0);

    }

    public void addTest() throws Exception {
        Test test = new Test("test " + this.getNumberOfTests());

        Integer userResponse = new Input(this.TEST_MENU2).isInteger();

        while(userResponse != 9) {

            if (userResponse == 1) {
                this.numberOfTests = new ReaderWriter().getNumber("test");
                test = new Test("test " + this.getNumberOfTests());
                test.create();
//                this.increaseNumberOfTests();
            } else if (userResponse == 2) {
                if (test.getQuestions().size() != 0) {
                    test.display();
                } else {
                    new Output().display("No Test in Memory");
                    this.addTest();
                }
            } else if (userResponse == 3) {
                try {
                    test = test.load();
                    //testToDisplay.display();
                    new Output().display(test.getName() + " loaded");
                }
                catch(NullPointerException e){new Output().display("\tLoad Failed");}
            } else if (userResponse == 4) {
                test.save();
                new Output().display(test.getName() + " saved");
            }
            else if (userResponse == 5){
                new Output().display("\t---- " + "Modify a survey ----" + "\n");
                test = test.modify();
            }
            else if (userResponse == 6){
                new Output().display("\t---- " + "Take a survey ----" + "\n");
                test = test.take();
            }
            else if (userResponse == 7){
                test.tabulate();
            }
            else if (userResponse == 8){
                new Output().display(test.gradeTest());
            }

            userResponse = new Input(this.TEST_MENU2).isInteger();
            while(userResponse > 9){
                new Output().display("\nPlease enter a positive integer less than 6");
                userResponse = new Input("").isInteger();
            }
        }

        new Output().display("\t Exiting...");
        System.exit(0);

    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        Integer userResponse = new Input(main.MENU1).isInteger();
        while(userResponse > 2){
            new Output().display("\nPlease enter a positive integer less than 3");
            userResponse = new Input("").isInteger();
        }

        if (userResponse == 1){
            main.numberOfSurveys = new ReaderWriter().getNumber("survey");
            main.addSurvey();
        }
        else if (userResponse == 2){
            main.numberOfTests = new ReaderWriter().getNumber("test");
            main.addTest();
        }
    }

    public Integer getNumberOfSurveys() {
        return this.numberOfSurveys;
    }

    public void increaseNumberOfSurveys(){
        this.numberOfSurveys++;
    }

    public void increaseNumberOfTests(){
        this.numberOfTests++;
    }

    public Integer getNumberOfTests() {
        return this.numberOfTests;
    }
}
