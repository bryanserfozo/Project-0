package com.revature.daos;

import com.revature.models.Account;
import com.revature.models.Transaction;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao{

    @Override
    public boolean createTransaction(Transaction trans) {
        String sql = "insert into transaction (accountid, amount, type, date) " +
                "values (?, ?, ?, ?)";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);){

            Account account = trans.getAccount();
            ps.setInt(1, account.getAccountID());

            ps.setDouble(2, trans.getAmount());
            ps.setInt(3, trans.getType().ordinal());





            int rowsAffected = ps.executeUpdate();
            if(rowsAffected==1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return null;
    }

    @Override
    public List<Transaction> getTransactionsByAccount(Account account) {
        return null;
    }

    @Override
    public Transaction getTransactionByID(int id) {
        return null;
    }
}
