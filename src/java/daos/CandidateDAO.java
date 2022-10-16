/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.CandidateDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thien's
 */
public class CandidateDAO {

    public static List<CandidateDTO> searchTestedCandidate(int major_id) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("SELECT c.can_id, j.major_id, c.email, c.can_cv, c.isStatus, u.[name], u.[phone] FROM [dbo].[Candidates] c JOIN [dbo].[Jobs] j "
                + " on j.[job_id] = c.[job_id] JOIN [dbo].[User] u on c.[email] = u.[email] WHERE major_id=? and isStatus=2");
        stm.setInt(1, major_id);
        ResultSet rs = stm.executeQuery();
        List<CandidateDTO> list = new LinkedList();
        while (rs.next()) {
            CandidateDTO c = new CandidateDTO();
            c.setId(rs.getString("can_id"));
            c.setMajorId(rs.getInt("major_id"));
            c.setEmail(rs.getString("email"));
            c.setCanCv(rs.getString("can_cv"));
            c.setName(rs.getString("name"));
            c.setIsStatus(rs.getInt("isStatus"));
            c.setPhone(rs.getString("phone"));
            list.add(c);
        }
        con.close();
        return list;
    }

    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
     */
    public List<CandidateDTO> userviewlist() {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select can_id,job_id,can_cv,isStatus from candidates");
            list = new ArrayList<>();
            while (rs.next()) {
                CandidateDTO c = new CandidateDTO();
                c.setId(rs.getString("can_id"));
                c.setJobId(rs.getString("job_id"));
                c.setCanCv(rs.getString("can_cv"));
                c.setIsStatus(rs.getInt("isStatus"));
                list.add(c);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<CandidateDTO> hrstatus0() {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery
        ("select can_id,job_id,email,can_cv,isStatus from candidates");
            list = new ArrayList<>();
            while (rs.next()) {
                CandidateDTO c = new CandidateDTO();
                c.setId(rs.getString("can_id"));
                c.setJobId(rs.getString("job_id"));
                c.setEmail(rs.getString("email"));
                c.setCanCv(rs.getString("can_cv"));
                c.setIsStatus(rs.getInt("isStatus"));
                list.add(c);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<CandidateDTO> hrstatus14() {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select can_id,job_id,email,can_cv,isStatus from candidates");
            list = new ArrayList<>();
            while (rs.next()) {
                CandidateDTO c = new CandidateDTO();
                c.setId(rs.getString("can_id"));
                c.setJobId(rs.getString("job_id"));
                c.setEmail(rs.getString("email"));
                c.setCanCv(rs.getString("can_cv"));
                c.setIsStatus(rs.getInt("isStatus"));
                list.add(c);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public CandidateDTO find(String id) throws SQLException {
        CandidateDTO c = null;
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("select * from candidates where can_id=? ");
        stm.setString(1, id);
        ResultSet rs = stm.executeQuery();

        if (rs.next()) {
            c = new CandidateDTO();
            c.setId(rs.getString("can_id"));
            c.setJobId(rs.getString("job_id"));
            c.setEmail(rs.getString("email"));
            c.setCanCv(rs.getString("can_cv"));
            c.setIsStatus(rs.getInt("isStatus"));

        }

        con.close();
        return c;
    }

    
    // Custom
    public void updateup(String can_id) throws SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("UPDATE Candidates SET isStatus = isStatus + 1 WHERE can_id = ?");
        stm.setString(1, can_id);
        stm.executeUpdate();
        con.close();
    }

    public void delete(String can_id) throws SQLException {
        Connection con = DBUtils.makeConnection();
        System.out.println("Connection done [Delete]");

        PreparedStatement stm = con.prepareStatement("DELETE FROM Candidates WHERE can_id = ?");
        stm.setString(1, can_id);
        stm.executeUpdate();
        System.out.println("Deleted(1): " + can_id);
        con.close();

    }

}
