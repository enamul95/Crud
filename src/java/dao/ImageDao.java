/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dbConnection.ConnectionHandler;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ehaqu
 */
public class ImageDao {

    public byte[] getImage(int id) {
        byte[] byteData = null;
        String sql = "SELECT image FROM user WHERE id = ? ";

        Connection con = ConnectionHandler.getConnection();

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("image");
                if (blob != null) {
                    byteData = blob.getBytes(1, (int) blob.length());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (con != null) {
                ConnectionHandler.releaseConnection(con);
            }
        }
        return byteData;

    }
}
