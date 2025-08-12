package com.tss.service;

import java.util.List;

import com.tss.dao.QuestionDAO;
import com.tss.model.Question;

public class QuestionService {
    private QuestionDAO dao = new QuestionDAO();

    public List<Question> fetchQuizQuestions() {
        return dao.getAllQuestions();
    }
}
