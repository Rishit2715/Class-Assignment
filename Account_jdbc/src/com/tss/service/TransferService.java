package com.tss.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;

import com.tss.dao.AccountDao;
import com.tss.database.DBConnection;
import com.tss.model.Account;

public class TransferService {
    private final AccountDao dao = new AccountDao();

    public void transferFunds(int senderId, int receiverId, double amount) {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);
            Savepoint savepoint = null;

            Account sender = dao.getAccountById(conn, senderId);
            Account receiver = dao.getAccountById(conn, receiverId);

            if (sender == null || receiver == null) {
                System.out.println("Invalid account(s).");
                return;
            }

            if (sender.getBalance() < amount) {
                System.out.println("Insufficient balance.");
                return;
            }

            sender.setBalance(sender.getBalance() - amount);
            dao.updateBalance(conn, sender.getId(), sender.getBalance());
            savepoint = conn.setSavepoint("AfterDebit");

            try {
                receiver.setBalance(receiver.getBalance() + amount);
                dao.updateBalance(conn, receiver.getId(), receiver.getBalance());
                conn.commit();
                System.out.println("Transfer successful.");
            } catch (SQLException e) {
                conn.rollback(savepoint);
                conn.commit();
                System.out.println("Transfer failed while crediting. Debit retained. " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
