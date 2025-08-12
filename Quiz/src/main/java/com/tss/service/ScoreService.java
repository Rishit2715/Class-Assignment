package com.tss.service;

import com.tss.dao.ScoreDAO;
import com.tss.model.Score;

public class ScoreService {
    private ScoreDAO scoreDAO = new ScoreDAO();

    public boolean storeScore(Score score) {
        return scoreDAO.insertScore(score);
    }
}
