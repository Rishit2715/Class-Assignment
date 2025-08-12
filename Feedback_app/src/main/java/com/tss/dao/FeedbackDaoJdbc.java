package com.tss.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tss.database.DBUtil;
import com.tss.model.Feedback;

public class FeedbackDaoJdbc implements FeedbackDao {

    private static final String INSERT_SQL =
        "INSERT INTO feedback (name, session_date, comments, session_content, query_resolution, interactivity, impactful_learning, content_delivery_skills) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ALL = "SELECT * FROM feedback ORDER BY submitted_at DESC";

    @Override
    public boolean saveFeedback(Feedback f) throws Exception {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            ps.setString(1, f.getName());
            ps.setDate(2, f.getSessionDate());
            ps.setString(3, f.getComments());
            ps.setString(4, f.getSessionContent());
            ps.setInt(5, f.getQueryResolution());
            ps.setInt(6, f.getInteractivity());
            ps.setInt(7, f.getImpactfulLearning());
            ps.setInt(8, f.getContentDeliverySkills());

            int affected = ps.executeUpdate();
            return affected == 1;
        }
    }

    @Override
    public List<Feedback> listFeedback() throws Exception {
        List<Feedback> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Feedback f = new Feedback();
                f.setId(rs.getInt("id"));
                f.setName(rs.getString("name"));
                f.setSessionDate(rs.getDate("session_date"));
                f.setComments(rs.getString("comments"));
                f.setSessionContent(rs.getString("session_content"));
                f.setQueryResolution(rs.getInt("query_resolution"));
                f.setInteractivity(rs.getInt("interactivity"));
                f.setImpactfulLearning(rs.getInt("impactful_learning"));
                f.setContentDeliverySkills(rs.getInt("content_delivery_skills"));
                list.add(f);
            }
        }
        return list;
    }
}
