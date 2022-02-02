package com.revature.daos;

import com.revature.models.*;
import com.revature.services.AccountService;
import com.revature.services.PersonService;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDaoImpl implements TransactionDao{

    private final PersonService personService = new PersonService();
    private final AccountService accountService = new AccountService();

    @Override
    public boolean createTransaction(Transaction trans) {
        String sql = "insert into transaction (accountid, amount, type) " +
                "values (?, ?, ?)";

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

    @Override
    public List<Transaction> getAllById(int id) {
        String sql = "select * from transaction where accountid = ?";
        List<Transaction> transactions = new ArrayList<>();

        try (Connection c = ConnectionUtil.getConnection();
            PreparedStatement s = c.prepareStatement(sql)){

            s.setInt(1, id);
            ResultSet rs = s.executeQuery();

            while(rs.next()){
                Transaction t = new Transaction();
                t.setTransactionID(rs.getInt("id"));
                t.setAmount(rs.getDouble("amount"));

                int typeOrdinal = rs.getInt("type");
                TransactionType[] types = TransactionType.values();
                t.setType(types[typeOrdinal]);

                int customerID = rs.getInt("accountid");
                if(customerID!=0){
                    Account account = new Account();
                    account = accountService.getAccountById(customerID);
                    t.setAccount(account);
                }
                transactions.add(t);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return transactions;
    }

    @Override
    public boolean deposit(Transaction t) {
        boolean creation = createTransaction(t);

        String sql = "update account set balance = (balance + ?) where id = ?";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDouble(1, t.getAmount());
            ps.setInt(2, t.getAccount().getAccountID());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 1 && creation) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean withdraw(Transaction t) {
        boolean creation = createTransaction(t);

        String sql = "update account set balance = (balance - ?) where id = ?";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setDouble(1, t.getAmount());
            ps.setInt(2, t.getAccount().getAccountID());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected == 1 && creation) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public List<Transaction> getAll() {
        String sql = "select * from transaction";
        List<Transaction> transactions = new ArrayList<>();

        try (Connection c = ConnectionUtil.getConnection();
             Statement s = c.createStatement();){
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()) {
                Transaction t = new Transaction();
                int id = rs.getInt("id");
                t.setTransactionID(id);


                int typeOrdinal = rs.getInt("type");
                TransactionType[] types = TransactionType.values();
                t.setType(types[typeOrdinal]);

                t.setAmount(rs.getDouble("amount"));

                int accountid = rs.getInt("accountid");
                t.setAccount(accountService.getAccountById(accountid));

                transactions.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}


