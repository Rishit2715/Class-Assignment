package com.tss.dao;


import java.util.List;

import com.tss.model.Feedback;

public interface FeedbackDao {
    boolean saveFeedback(Feedback feedback) throws Exception;
    List<Feedback> listFeedback() throws Exception;
}
