package com.example.marketplace.dao;

import java.security.MessageDigest;

import com.example.marketplace.model.Customer;

public class CustomerDaoJdbc implements CustomerDao{

    @Override
    public Customer findCustomerByEmail(String email) {
        // TODO Auto-generated method stub
        return null;
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
		// TODO Auto-generated method stub
		return false;
	} 
}
