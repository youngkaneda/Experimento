/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kuuhaku
 */
public class Developer {
    private final String githubNick;
    private final List<Question> questions;

    public Developer(String githubNick) {
        this.githubNick = githubNick;
        this.questions = new ArrayList<>();
    }

    public String getGithubNick() {
        return githubNick;
    }

    public List<Question> getQuestions() {
        return questions;
    }
    
    public Question getQuestion(int index) {
        return questions.get(index);
    }
    
    public boolean addQuestion(Question question) {
        return this.questions.add(question);
    }
    
    public boolean answersIsNull(String questionText) {
        long count = questions.stream().filter((q)->q.getQuestionText().equals(questionText) && q.getAnswers() == null).count();
        return count > 0;
    }
    
    public boolean allQuestionsAnswered() {
        long count = questions.stream().filter((q)-> q.getAnswers() != null).count();
        return count == 5;        
    }
    
    public void addAnswers(String questionText, List<String> answers) {
        questions.stream().filter((q)-> q.getQuestionText().equals(questionText)).forEach((q)-> q.setAnswers(answers));
    }

    @Override
    public String toString() {
        return "Developer{" + "githubNick=" + githubNick + ", questions=" + questions + '}';
    }
}
