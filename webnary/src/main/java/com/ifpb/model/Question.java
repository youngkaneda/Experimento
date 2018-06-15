/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.model;

import java.util.List;

/**
 *
 * @author kuuhaku
 */
public class Question {
    private String questionText;
    private List<String> alternatives, answers;

    public Question(String questionText, List<String> alternatives) {
        this.questionText = questionText;
        this.alternatives = alternatives;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getAlternatives() {
        return alternatives;
    }

    public void setAlternatives(List<String> alternatives) {
        this.alternatives = alternatives;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" + "questionText=" + questionText + ", alternatives=" + alternatives + ", answers=" + answers + '}';
    }
}
