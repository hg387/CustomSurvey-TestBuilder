package com.gupta.main.resources;

import com.gupta.main.java.Survey;
import com.gupta.main.question.Question;
import com.gupta.main.resources.inputOutput.Input;
import com.gupta.main.resources.inputOutput.Output;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class SurveyModifier implements Serializable {
    private static final long serialVersionUID = 6216604663640880744L;

    public void modify(Survey survey) throws IOException {
        List<Question> questions = survey.getQuestions();
        survey.display();

        Integer input = new Input("Please enter question number you want to modify ?").isInteger();

        Question question = questions.get(input-1);
        String response = new Input("Do you wish to modify the prompt ?").isString(1);
        while( !((response.equalsIgnoreCase("yes")) || response.equalsIgnoreCase("no")) ){
            new Output().display("");
            response = new Input("You have to enter either yes or no").isString(1);
        }

        if (response.equalsIgnoreCase("yes")){
            response = new Input("Enter a new prompt:").isString(1);
            survey.getQuestions().get(input-1).setQuestion(response);
        }

        if (question.getChoices() != null){
                List<String> choices = question.getChoices();

                if (!(choices.equals(Arrays.asList("true", "false")) ||
                        choices.equals(Arrays.asList("Smiles", "Frowns", "Angry", "Surprised", "Sad")))) {
                    if (new Input("Do you wish to modify any choices").modify("")) {
                        Input userInput = new Input("Which choice you want to modify ?");
                        for (int i = 1; i <= choices.size(); i++) {
                            new Output().display(i + ") " + choices.get(i - 1));
                        }

                        Integer choiceToEdit = 0;
                        while ((choiceToEdit = userInput.isInteger()) > choices.size()) {
                            new Output().display("Entered integer out of range");
                        }

                        String newInput = new Input("Enter the new choice:").isString(1);
                        questions.get(input - 1).getChoices().remove(choiceToEdit - 1);
                        questions.get(input - 1).getChoices().add(choiceToEdit - 1, newInput);
                    }
                }
            }

        else if (question.getMatching() != null){
            if (new Input("Do you wish to modify any choices").modify("")) {
                HashMap<String, String> map = question.getMatching();
                Input user = new Input("Which column you want to modify 1 0R 2?");

                Integer userInput = user.isInteger();
                while (userInput != 1 && userInput != 2){
                    userInput = new Input("Invalid response").isInteger();
                }

                if (userInput == 1){
                    user = new Input("Which choice do wish to modify ?");
                    for (String matching:map.keySet()){
                        new Output().display(matching);
                    }

                    String choice = user.isString(1);

                    while(!map.containsKey(choice.trim())){
                        choice = new Input("Not a choice, re-enter").isString(1);
                    }

                    String oldChoiceValue = question.getMatching().get(choice.trim());
                    question.getMatching().remove(choice.trim());
                    choice = new Input("Please enter your new choice").isString(1);

                    while(map.containsKey(choice.trim())){
                        choice = new Input("Choice entered already present, Please re-enter: ").isString(1);
                    }

                    question.getMatching().put(choice, oldChoiceValue);

                }
                else {
                    user = new Input("Which value do wish to modify ?");
                    for (Map.Entry<String, String> tmp : map.entrySet()){
                        new Output().display(tmp.getValue());
                    }

                    String choice = user.isString(1);

                    while(!map.containsValue(choice.trim())){
                        choice = new Input("Not a value, re-enter").isString(1);
                    }

                    String key = null;
                    for (Map.Entry<String, String> tmp : map.entrySet()){
                        if (tmp.getValue().equalsIgnoreCase(choice.trim())){
                            key = tmp.getKey();
                            break;
                        }
                    }

                    choice = new Input("Please enter your new value").isString(1);
                    question.getMatching().put(key, choice.trim());
                }
            }
        }
    }
}
