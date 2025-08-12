package com.tss.service;



import java.util.List;

import com.tss.dao.FeedbackDao;
import com.tss.dao.FeedbackDaoJdbc;
import com.tss.model.Feedback;

public class FeedbackService {
    private final FeedbackDao feedbackDao = new FeedbackDaoJdbc();

    public boolean submitFeedback(Feedback feedback) throws Exception {
        // simple validation example
        if (feedback.getName() == null || feedback.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name required");
        }
        return feedbackDao.saveFeedback(feedback);
    }

    public List<Feedback> getAllFeedback() throws Exception {
        return feedbackDao.listFeedback();
    }
}
