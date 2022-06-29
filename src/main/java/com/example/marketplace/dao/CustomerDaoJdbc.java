package com.example.marketplace.dao;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.marketplace.model.Customer;

public class CustomerDaoJdbc implements CustomerDao{

    @Override
    public Customer findCustomerByEmail(String email) {
        String sql = "SELECT * FROM customers WHERE email = \"" + email + "\";";
        Connection conn = null;
        Customer customer = null;
        try {
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setId((rs.getLong("id")));
                customer.setFullName(rs.getString("full_name"));
                customer.setEmail(rs.getString("email"));
                customer.setPassword(rs.getString("password"));
                customer.setCash(rs.getInt("cash"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            Jdbc.closeConnection(conn);
        }
        return customer;
    }

    @Override
    public String hashPassword(String password){
        String md5 = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for(byte b : digest){
                sb.append(String.format("%02x", b & 0xff));
            }
            md5 = sb.toString();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return md5;
    }

	@Override
	public boolean insertNewCustomer(String email, String password, String fullName) {
		String sql = "INSERT INTO customers (email, password, full_name) VALUES (\""+email+"\", md5(\""+password+"\"), \""+fullName+"\")";
        Connection conn = null;
        try{
            conn = Jdbc.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        }
        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        finally{
            Jdbc.closeConnection(conn);
        }
		return true;
	}

    @Override
    public boolean setCostumerCashTo(long costumerId, int setAmount) {
        return false;
    }
}
