/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dbConnection.ConnectionHandler;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ResponseModel;
import model.UserModel;

/**
 *
 * @author A
 */
public class UserDao {

    public ResponseModel insertData(UserModel model) {
        ResponseModel outModel = new ResponseModel();
        String sql = "INSERT INTO user (name, mobile, email) VALUES (?, ?, ?) ";
        Connection con = ConnectionHandler.getConnection();

        try {
            PreparedStatement ps = con.prepareCall(sql);
            ps.setString(1, model.getName());
            ps.setString(2, model.getMobile());
            ps.setString(3, model.getEmail());
            int i = ps.executeUpdate();
            if (i > 0) {
                outModel.setResponseCode("1");
                outModel.setResponseMessage("Data Save Successfully");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return outModel;
    }

    public List<UserModel> getAllData() {
        String sql = "SELECT `id`, `name`, `mobile`, `email` FROM `user`  order by id desc";
        Connection con = ConnectionHandler.getConnection();
        List<UserModel> list = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                UserModel model = new UserModel();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String mobile = rs.getString("mobile");
                String email = rs.getString("email");
                model.setId(id);
                model.setName(name);
                model.setMobile(mobile);
                model.setEmail(email);
                list.add(model);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;

    }

    public int deleteData(int id) {
        int rValue = 0;
        String sql = "DELETE FROM `user` WHERE id =?";
        Connection con = ConnectionHandler.getConnection();
        try {
            PreparedStatement ps = con.prepareCall(sql);
            ps.setInt(1, id);
            rValue  = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rValue;
    }

    public static void main(String[] args) {
        UserDao ud = new UserDao();
        UserModel model = new UserModel();
        model.setName("Test");
        model.setMobile("01921687433");
        model.setEmail("abc@gmail.com");
        ResponseModel insertData = ud.insertData(model);
        System.out.println(insertData.getResponseMessage());

        List<String> l = new ArrayList<>();
        l.add("A");
        l.add("B");

        for (String s : l) {

        }
    }
}
