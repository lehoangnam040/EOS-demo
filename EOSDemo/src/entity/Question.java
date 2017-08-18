/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author Nam
 */
public class Question {

    private String id;
    private String content;
    private ArrayList<Answer> answers;

    public Question(String id, String content, ArrayList<Answer> answers) {
        this.id = id;
        this.content = content;
        this.answers = answers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public boolean isMultiple() {
        int noOfCorrect = 0;
        for (Answer a : answers) {
            if (a.isCorrect()) {
                noOfCorrect++;
            }
        }
        return noOfCorrect != 1;
    }

    @Override
    public String toString() {
        return "Question{" + "id=" + id + ", content=" + content + ", answers=" + answers + '}';
    }
    
}
