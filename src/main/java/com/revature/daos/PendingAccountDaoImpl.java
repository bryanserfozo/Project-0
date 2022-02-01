package com.revature.daos;

import com.revature.models.Account;
import com.revature.models.PendingAccount;
import com.revature.models.Person;
import com.revature.models.Type;
import com.revature.services.PersonService;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PendingAccountDaoImpl implements PendingAccountDao{

    @Override
    public PendingAccount getByPendingId(int pendingId) {
        try (Connection c = ConnectionUtil.getConnection();){
            PreparedStatement s = c.prepareStatement("select * from pending_accounts where id = ?");
            s.setInt(1, pendingId);
            ResultSet rs = s.executeQuery();
            if(rs.next()){
                PersonService personService = new PersonService();
                PendingAccount pa = new PendingAccount();
                pa.setPendingID(rs.getInt("id"));
                pa.setNumOtherAccounts(rs.getInt("numOtherAccounts"));
                int customerID = rs.getInt("customerID");
                if(customerID!=0){
                    Person customer = new Person();
                    customer = personService.getById(customerID);
                    pa.setPerson(customer);
                }
                return pa;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<PendingAccount> getAll() {
        String sql = "select * from pending_accounts";
        List<PendingAccount> pendingAccounts = new ArrayList<>();

        PersonService ps = new PersonService();

        try (Connection c = ConnectionUtil.getConnection();
             Statement s = c.createStatement();) {
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                PendingAccount pa = new PendingAccount();
                pa.setPendingID(rs.getInt("id"));
                pa.setNumOtherAccounts(rs.getInt("numOtherAccounts"));
                int customerID = rs.getInt("customerID");
                if (customerID != 0) {
                    Person customer = ps.getById(customerID);
                    customer.setPassword("HIDDEN");
                    pa.setPerson(customer);

                }
                pendingAccounts.add(pa);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return pendingAccounts;
    }

    @Override
    public boolean deletePendingAccount(PendingAccount pendingAccount) {
        String sql = "delete from pending_accounts where id = ?";
        try(Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, pendingAccount.getPendingID());
            int rowsAffected = ps.executeUpdate();

            if(rowsAffected==1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean createPendingAccount(Person p) {
        String sql = "insert into pending_accounts (customerid, numotheraccounts) values (?, ?)";
        int id = p.getId();
        String sql2 = "select count(id) from account where customerid = " + id;
        int numOtherAccounts = 0;

        try (Connection c = ConnectionUtil.getConnection();
             Statement s = c.createStatement();
             PreparedStatement ps = c.prepareStatement(sql)){

            ResultSet rs = s.executeQuery(sql2);
            rs.next();
            numOtherAccounts = rs.getInt("count");

            ps.setInt(1, p.getId());
            ps.setInt(2, numOtherAccounts);

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected==1){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
