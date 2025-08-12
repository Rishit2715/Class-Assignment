package com.tss.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.tss.database.DBConnection;
import com.tss.model.Score;

public class ScoreDAO {

    public boolean insertScore(Score score) {
        String query = "INSERT INTO score (result_id, user_id, username, score) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.connect();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, score.getResultId());
            ps.setInt(2, score.getUserId());
            ps.setString(3, score.getUsername());
            ps.setInt(4, score.getScore());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
