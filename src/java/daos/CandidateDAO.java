/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.CandidateDTO;
import dtos.GoogleDTO;
import dtos.InterviewingDTO;
import dtos.JobsDTO;
import dtos.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpSession;
import utils.DBUtils;

/**
 *
 * @author Thien's
 */
public class CandidateDAO {

//    public void delete(String can_id) throws SQLException, ClassNotFoundException {
//        Connection con = DBUtils.makeConnection();
//        System.out.println("Connection done [Delete]");
//
//        PreparedStatement stm = con.prepareStatement("DELETE FROM [Candidate] WHERE [can_id] = ?");
//        stm.setString(1, can_id);
//        stm.executeUpdate();
//        System.out.println("Deleted(1): " + can_id);
//        con.close();
//
//    }

    public static List<CandidateDTO> viewUserApplication(String email) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("Select c.can_id,j.job_name,can_cv,score , c.isStatus "
                + "from [dbo].[Candidate] c inner join [job] j on c.[job_id] = j.[job_id] "
                + "where [email] like ?  order by [can_id] ASC");
        stm.setString(1, email);
        UserDAO uDao = new UserDAO();
        UserDTO user = uDao.find(email);
        System.out.println("List User Application: " + user.getEmail());
        ResultSet rs = stm.executeQuery();
        List<CandidateDTO> list = new ArrayList<>();
        while (rs.next()) {
            JobsDTO j = new JobsDTO();
            String id = rs.getString("can_id");
            j.setJob_name(rs.getString("job_name"));
            String cv = rs.getString("can_cv");
            float score = rs.getFloat("score");
            int isStatus = rs.getInt("isStatus");
            CandidateDTO join = new CandidateDTO(id, j, cv, score, isStatus);
            list.add(join);
        }
        con.close();
        return list;
    }
    public static List<CandidateDTO> search2(String email) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("Select c.can_id,j.job_name,can_cv,score , c.isStatus "
                + "from [dbo].[Candidate] c inner join [job] j on c.[job_id] = j.[job_id] "
                + "where [email] like ?  order by [can_id] ASC");
        stm.setString(1, email);
        UserDAO uDao = new UserDAO();
        UserDTO user = uDao.find(email);
        System.out.println("Account: " + user.getEmail());
        ResultSet rs = stm.executeQuery();
        List<CandidateDTO> list = new ArrayList<>();
        while (rs.next()) {
            JobsDTO j = new JobsDTO();
            String id = rs.getString("can_id");
            j.setJob_name(rs.getString("job_name"));
            String cv = rs.getString("can_cv");
            float score = rs.getFloat("score");
            int isStatus = rs.getInt("isStatus");
            CandidateDTO join = new CandidateDTO(id, j, cv, score, isStatus);
            list.add(join);
        }
        con.close();
        return list;
    }

    public static List<CandidateDTO> search(String job_name) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("select c.can_id,j.job_name,c.email,can_cv,score, c.isStatus from candidate c \n"
                + "                 inner join job j on c.job_id = j.job_id \n"
                + "                  where job_name like ?  order by can_id  ASC");
        stm.setString(1, "%" + job_name + "%");
        ResultSet rs = stm.executeQuery();
        List<CandidateDTO> list = new ArrayList<>();
        while (rs.next()) {
            JobsDTO j = new JobsDTO();
            String id = rs.getString("can_id");
            j.setJob_name(rs.getString("job_name"));
            String cv = rs.getString("can_cv");
            String email = rs.getString("email");
            float score = rs.getInt("score");
            int isStatus = rs.getInt("isStatus");
            CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
            list.add(join);
        }
        con.close();
        return list;
    }

    public String newId() throws SQLException, ClassNotFoundException {
        Connection con = DBUtils.makeConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select * from [Candidate] ");
        int i = 0;
        while (rs.next()) {
            i++;
        }
        i++;
        System.out.println(i);
        String newId = null;
        if (i < 10) {
            newId = "C00" + i;
        } else if (i < 100) {
            newId = "C0" + i;
        } else {
            newId = "C" + i;
        }
        PreparedStatement pstm = con.prepareStatement("select * from [Candidate] where [can_id] = ?");
        pstm.setString(1, newId);
        rs = pstm.executeQuery();
        while (rs.next()) {
            i++;
            if (i < 10) {
                newId = "C00" + i;
            } else if (i < 100) {
                newId = "C0" + i;
            } else {
                newId = "C" + i;
            }
            pstm.setString(1, newId);
            rs = pstm.executeQuery();
        }
        con.close();
        return newId;
    }

    public static List<CandidateDTO> searchCandidateByMajor(int major_id, int status) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("SELECT c.can_id, j.major_id, c.email, c.can_cv, c.isStatus, u.[name], u.[phone] FROM [dbo].[Candidate] c JOIN [dbo].[Job] j "
                + " on j.[job_id] = c.[job_id] JOIN [dbo].[User] u on c.[email] = u.[email] WHERE major_id=? and isStatus=?");
        stm.setInt(1, major_id);
        stm.setInt(2, status);
        ResultSet rs = stm.executeQuery();
        List<CandidateDTO> list = new LinkedList();
        while (rs.next()) {
            CandidateDTO c = new CandidateDTO();
            c.setId(rs.getString("can_id"));
            c.setMajorId(rs.getInt("major_id"));
            c.setEmail(rs.getString("email"));
            c.setCv(rs.getString("can_cv"));
            c.setName(rs.getString("name"));
            c.setIsStatus(rs.getInt("isStatus"));
            c.setPhone(rs.getString("phone"));
            list.add(c);
        }
        con.close();
        return list;
    }

    public static CandidateDTO searchCandidateByEmail(String email) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("SELECT c.can_id, j.major_id, c.email, c.can_cv, c.isStatus, u.[name], u.[phone] FROM [dbo].[Candidate] c JOIN [dbo].[Job] j "
                + " on j.[job_id] = c.[job_id] JOIN [dbo].[User] u on c.[email] = u.[email] WHERE c.[email]=?");
        stm.setString(1, email);
        ResultSet rs = stm.executeQuery();
        CandidateDTO c = new CandidateDTO();
        if (rs.next()) {
            c.setId(rs.getString("can_id"));
            c.setMajorId(rs.getInt("major_id"));
            c.setEmail(rs.getString("email"));
            c.setCv(rs.getString("can_cv"));
            c.setName(rs.getString("name"));
            c.setIsStatus(rs.getInt("isStatus"));
            c.setPhone(rs.getString("phone"));
        }
        con.close();
        return c;
    }

    public static CandidateDTO searchCandidateById(String id) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("SELECT c.can_id, j.major_id, c.email, c.can_cv, u.[name] FROM [dbo].[Candidate] c JOIN [dbo].[Job] j "
                + " on j.[job_id] = c.[job_id] JOIN [dbo].[User] u on c.[email] = u.[email] WHERE c.[can_id]=?");
        stm.setString(1, id);
        ResultSet rs = stm.executeQuery();
        CandidateDTO c = new CandidateDTO();
        if (rs.next()) {
            c.setId(rs.getString("can_id"));
            c.setMajorId(rs.getInt("major_id"));
            c.setEmail(rs.getString("email"));
            c.setCv(rs.getString("can_cv"));
            c.setName(rs.getString("name"));
        }
        con.close();
        return c;
    }

    public static boolean updateCandidateStatus(String id, int isStatus) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("update [dbo].[Candidate] set isStatus=? where can_id=?");
        stm.setInt(1, isStatus);
        stm.setString(2, id);
        int rs = stm.executeUpdate();
        con.close();
        return rs != 0;
    }

    //SEARCH 
    public static List<CandidateDTO> searchJobAll(String search) throws SQLException, ClassNotFoundException {
        Connection con = DBUtils.makeConnection();
        String sql = "select can_id,job_id,email,can_cv,isStatus from candidate where job_id like ?";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, "%" + search + "%");
        ResultSet rs = stm.executeQuery();
        List<CandidateDTO> list = new ArrayList<>();
        while (rs.next()) {
            CandidateDTO c = new CandidateDTO();
            c.setId(rs.getString("can_id"));
            c.setJobId(rs.getString("job_id"));
            c.setEmail(rs.getString("email"));
            c.setCv(rs.getString("can_cv"));
            c.setIsStatus(rs.getInt("isStatus"));
            list.add(c);
        }
        return list;
    }

    // LIST ALL APPLICATIONS
    public List<CandidateDTO> selectAll() throws SQLException, ClassNotFoundException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT c.can_id,j.job_name,c.email,can_cv,score,isStatus "
                + "FROM [Candidate] c "
                + "INNER JOIN [job] j ON c.[job_id] = j.[job_id] "
        );
        list = new ArrayList<>();
        while (rs.next()) {
            JobsDTO j = new JobsDTO();
            String id = rs.getString(1);
            j.setJob_name(rs.getString(2));
            String cv = rs.getString(4);
            String email = rs.getString(3);
            float score = rs.getInt(5);
            int isStatus = rs.getInt(6);
            CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
            list.add(join);

        }
        con.close();
        return list;
    }

    public List<CandidateDTO> hrstatus0() throws ClassNotFoundException, SQLException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score, isStatus from candidate c "
                    + "inner join job j on c.job_id = j.job_id "
                    + "where c.isStatus =0");
            list = new ArrayList<>();
            while (rs.next()) {
                JobsDTO j = new JobsDTO();
                String id = rs.getString(1);
                j.setJob_name(rs.getString(2));
                String cv = rs.getString(4);
                String email = rs.getString(3);
                float score = rs.getInt(5);
                int isStatus = rs.getInt(6);
                CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
                list.add(join);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<CandidateDTO> hrstatus1() throws ClassNotFoundException, SQLException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score, isStatus from candidate c "
                    + "inner join job j on c.job_id = j.job_id "
                    + "where c.isStatus =1");
            list = new ArrayList<>();
            while (rs.next()) {
                JobsDTO j = new JobsDTO();
                String id = rs.getString(1);
                j.setJob_name(rs.getString(2));
                String cv = rs.getString(4);
                String email = rs.getString(3);
                float score = rs.getInt(5);
                int isStatus = rs.getInt(6);
                CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
                list.add(join);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<CandidateDTO> hrstatus2() throws ClassNotFoundException, SQLException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score, isStatus from candidate c "
                    + "inner join job j on c.job_id = j.job_id "
                    + "where c.isStatus =2");
            list = new ArrayList<>();
            while (rs.next()) {
                JobsDTO j = new JobsDTO();
                String id = rs.getString(1);
                j.setJob_name(rs.getString(2));
                String cv = rs.getString(4);
                String email = rs.getString(3);
                float score = rs.getInt(5);
                int isStatus = rs.getInt(6);
                CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
                list.add(join);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<CandidateDTO> hrstatus3() throws ClassNotFoundException, SQLException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score, isStatus from candidate c "
                    + "inner join job j on c.job_id = j.job_id "
                    + "where c.isStatus =3");
            list = new ArrayList<>();
            while (rs.next()) {
                JobsDTO j = new JobsDTO();
                String id = rs.getString(1);
                j.setJob_name(rs.getString(2));
                String cv = rs.getString(4);
                String email = rs.getString(3);
                float score = rs.getInt(5);
                int isStatus = rs.getInt(6);
                CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
                list.add(join);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<CandidateDTO> hrstatus5() throws ClassNotFoundException, SQLException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score, isStatus from candidate c "
                    + "inner join job j on c.job_id = j.job_id "
                    + "where c.isStatus =5 ");
            list = new ArrayList<>();
            while (rs.next()) {
                JobsDTO j = new JobsDTO();
                String id = rs.getString(1);
                j.setJob_name(rs.getString(2));
                String cv = rs.getString(4);
                String email = rs.getString(3);
                float score = rs.getInt(5);
                int isStatus = rs.getInt(6);
                CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
                list.add(join);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<CandidateDTO> hrstatus14() throws ClassNotFoundException, SQLException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score, isStatus from candidate c "
                    + "inner join job j on c.job_id = j.job_id "
                    + "where c.isStatus <5 and c.isStatus > 0");
            list = new ArrayList<>();
            while (rs.next()) {
                JobsDTO j = new JobsDTO();
                String id = rs.getString(1);
                j.setJob_name(rs.getString(2));
                String cv = rs.getString(4);
                String email = rs.getString(3);
                float score = rs.getInt(5);
                int isStatus = rs.getInt(6);
                CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
                list.add(join);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<CandidateDTO> hrstatus4() throws ClassNotFoundException, SQLException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score , i.inter_score, c.isStatus from candidate c "
                    + "inner join interviewing i on c.can_id = i.can_id "
                    + "inner join job j on c.job_id = j.job_id "
                    + "where c.isStatus =4 ");
            list = new ArrayList<>();
            while (rs.next()) {
                InterviewingDTO i = new InterviewingDTO();
                JobsDTO j = new JobsDTO();
                i.setScore(rs.getInt(6));
                String id = rs.getString(1);
                j.setJob_name(rs.getString(2));
                String cv = rs.getString(4);
                String email = rs.getString(3);
                float score = rs.getInt(5);
                int isStatus = rs.getInt(7);
                CandidateDTO join = new CandidateDTO(id, j, cv, email, score, i, isStatus);
                list.add(join);
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    // SORT STATUS ALL
    public List<CandidateDTO> sortByStatusASCAll() throws SQLException, ClassNotFoundException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score ,  c.isStatus from candidate c "
                + "inner join job j on c.job_id = j.job_id "
                + "order by isStatus ,can_id ASC");
        list = new ArrayList<>();
        while (rs.next()) {
            JobsDTO j = new JobsDTO();
            String id = rs.getString(1);
            j.setJob_name(rs.getString(2));
            String cv = rs.getString(4);
            String email = rs.getString(3);
            float score = rs.getInt(5);
            int isStatus = rs.getInt(6);
            CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
            list.add(join);
        }
        con.close();
        return list;
    }

    public List<CandidateDTO> sortByStatusDESCAll() throws SQLException, ClassNotFoundException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score ,  c.isStatus from candidate c "
                + "inner join job j on c.job_id = j.job_id "
                + "order by isStatus DESC,can_id ASC");
        list = new ArrayList<>();
        while (rs.next()) {
            JobsDTO j = new JobsDTO();
            String id = rs.getString(1);
            j.setJob_name(rs.getString(2));
            String cv = rs.getString(4);
            String email = rs.getString(3);
            float score = rs.getInt(5);
            int isStatus = rs.getInt(6);
            CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
            list.add(join);
        }
        con.close();
        return list;
    }

    // SORT SCORE ALL
    public List<CandidateDTO> sortByScoreASCAll() throws SQLException, ClassNotFoundException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score ,  c.isStatus from candidate c "
                + "inner join job j on c.job_id = j.job_id "
                + "order by score ,can_id ASC");
        list = new ArrayList<>();
        while (rs.next()) {
            JobsDTO j = new JobsDTO();
            String id = rs.getString(1);
            j.setJob_name(rs.getString(2));
            String cv = rs.getString(4);
            String email = rs.getString(3);
            float score = rs.getInt(5);
            int isStatus = rs.getInt(6);
            CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
            list.add(join);
        }
        con.close();
        return list;
    }

    public List<CandidateDTO> sortByScoreDESCAll() throws SQLException, ClassNotFoundException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score , c.isStatus from candidate c "
                + "inner join job j on c.job_id = j.job_id "
                + "order by score DESC,can_id ASC");
        list = new ArrayList<>();
        while (rs.next()) {
            JobsDTO j = new JobsDTO();
            String id = rs.getString(1);
            j.setJob_name(rs.getString(2));
            String cv = rs.getString(4);
            String email = rs.getString(3);
            float score = rs.getInt(5);
            int isStatus = rs.getInt(6);
            CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
            list.add(join);
        }
        con.close();
        return list;
    }

    // SORT CAN_ID ALL
    public List<CandidateDTO> sortByCanASCAll() throws SQLException, ClassNotFoundException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score , c.isStatus from candidate c "
                + "inner join job j on c.job_id = j.job_id "
                + "order by  c.can_id  ASC ");
        list = new ArrayList<>();
        while (rs.next()) {
            JobsDTO j = new JobsDTO();
            String id = rs.getString(1);
            j.setJob_name(rs.getString(2));
            String email = rs.getString(3);
            String cv = rs.getString(4);
            float score = rs.getInt(5);
            int isStatus = rs.getInt(6);
            CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
            list.add(join);
        }
        con.close();
        return list;
    }

    public List<CandidateDTO> sortByCanDESCAll() throws SQLException, ClassNotFoundException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score , c.isStatus from candidate c "
                + "inner join job j on c.job_id = j.job_id "
                + "order by  c.can_id  DESC ");
        list = new ArrayList<>();
        while (rs.next()) {
            JobsDTO j = new JobsDTO();
            String id = rs.getString(1);
            j.setJob_name(rs.getString(2));
            String email = rs.getString(3);
            String cv = rs.getString(4);
            float score = rs.getInt(5);
            int isStatus = rs.getInt(6);
            CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
            list.add(join);
        }
        con.close();
        return list;
    }

    // SORT STATUS Inprocess
    public List<CandidateDTO> sortByStatusASCInprocess() throws SQLException, ClassNotFoundException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score , c.isStatus from candidate c "
                + "inner join job j on c.job_id = j.job_id "
                + "where isStatus > 0 and isStatus < 5 order by isStatus, can_id  ASC");
        list = new ArrayList<>();
        while (rs.next()) {
            JobsDTO j = new JobsDTO();
            String id = rs.getString(1);
            j.setJob_name(rs.getString(2));
            String email = rs.getString(3);
            String cv = rs.getString(4);
            float score = rs.getInt(5);
            int isStatus = rs.getInt(6);
            CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
            list.add(join);

        }
        con.close();
        return list;
    }

    public List<CandidateDTO> sortByStatusDESCInprocess() throws SQLException, ClassNotFoundException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score , c.isStatus from candidate c "
                + "inner join job j on c.job_id = j.job_id "
                + "where isStatus > 0 and isStatus < 5 order by isStatus DESC , can_id ASC");
        list = new ArrayList<>();
        while (rs.next()) {
            JobsDTO j = new JobsDTO();
            String id = rs.getString(1);
            j.setJob_name(rs.getString(2));
            String email = rs.getString(3);
            String cv = rs.getString(4);
            float score = rs.getInt(5);
            int isStatus = rs.getInt(6);
            CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
            list.add(join);
        }
        con.close();
        return list;
    }

    // SORT SCORE Inprocess
    public List<CandidateDTO> sortByScoreASCInprocess() throws SQLException, ClassNotFoundException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score , c.isStatus from candidate c "
                + "inner join job j on c.job_id = j.job_id "
                + "where isStatus > 0 and isStatus < 5 order by score, can_id ASC");
        list = new ArrayList<>();
        while (rs.next()) {
            JobsDTO j = new JobsDTO();
            String id = rs.getString(1);
            j.setJob_name(rs.getString(2));
            String email = rs.getString(3);
            String cv = rs.getString(4);
            float score = rs.getInt(5);
            int isStatus = rs.getInt(6);
            CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
            list.add(join);
        }
        con.close();
        return list;
    }

    public List<CandidateDTO> sortByScoreDESCInprocess() throws SQLException, ClassNotFoundException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score , c.isStatus from candidate c "
                + "inner join job j on c.job_id = j.job_id "
                + "where isStatus > 0 and isStatus < 5 order by score DESC , can_id ASC");
        list = new ArrayList<>();
        while (rs.next()) {
            JobsDTO j = new JobsDTO();
            String id = rs.getString(1);
            j.setJob_name(rs.getString(2));
            String email = rs.getString(3);
            String cv = rs.getString(4);
            float score = rs.getInt(5);
            int isStatus = rs.getInt(6);
            CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
            list.add(join);
        }
        con.close();
        return list;
    }

    // SORT CAN_ID Inprocess
    public List<CandidateDTO> sortByCanASCInprocess() throws SQLException, ClassNotFoundException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score , c.isStatus from candidate c "
                + "inner join job j on c.job_id = j.job_id "
                + "where isStatus > 0 and isStatus < 5 order by  can_id ASC");
        list = new ArrayList<>();
        while (rs.next()) {
            JobsDTO j = new JobsDTO();
            String id = rs.getString(1);
            j.setJob_name(rs.getString(2));
            String email = rs.getString(3);
            String cv = rs.getString(4);
            float score = rs.getInt(5);
            int isStatus = rs.getInt(6);
            CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
            list.add(join);
        }
        con.close();
        return list;
    }

    public List<CandidateDTO> sortByCanDESCInprocess() throws SQLException, ClassNotFoundException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score , c.isStatus from candidate c "
                + "inner join job j on c.job_id = j.job_id "
                + "where isStatus > 0 and isStatus < 5 order by  can_id DESC");
        list = new ArrayList<>();
        while (rs.next()) {
            JobsDTO j = new JobsDTO();
            String id = rs.getString(1);
            j.setJob_name(rs.getString(2));
            String email = rs.getString(3);
            String cv = rs.getString(4);
            float score = rs.getInt(5);
            int isStatus = rs.getInt(6);
            CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
            list.add(join);
        }
        con.close();
        return list;
    }

    // SORT Status NEWEST
    public List<CandidateDTO> sortByCanASCNewest() throws SQLException, ClassNotFoundException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score, c.isStatus from candidate c "
                + "inner join job j on c.job_id = j.job_id "
                + " where isStatus =0 order by can_id  ASC ");
        list = new ArrayList<>();
        while (rs.next()) {
            JobsDTO j = new JobsDTO();
            String id = rs.getString(1);
            j.setJob_name(rs.getString(2));
            String cv = rs.getString(4);
            String email = rs.getString(3);
            float score = rs.getInt(5);
            int isStatus = rs.getInt(6);
            CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
            list.add(join);
        }
        con.close();
        return list;
    }

    public List<CandidateDTO> sortByCanDESCNewest() throws SQLException, ClassNotFoundException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score, c.isStatus from candidate c "
                + "inner join job j on c.job_id = j.job_id "
                + "where isStatus =0 order by can_id  DESC ");
        list = new ArrayList<>();
        while (rs.next()) {
            JobsDTO j = new JobsDTO();
            String id = rs.getString(1);
            j.setJob_name(rs.getString(2));
            String cv = rs.getString(4);
            String email = rs.getString(3);
            float score = rs.getInt(5);
            int isStatus = rs.getInt(6);
            CandidateDTO join = new CandidateDTO(id, j, cv, email, score, isStatus);
            list.add(join);
        }
        con.close();
        return list;
    }

    // SORT JOB_ID RECRUIT
//    public List<CandidateDTO> sortByJobASCRecruit() throws SQLException, ClassNotFoundException {
//        List<CandidateDTO> list = null;
//        Connection con = DBUtils.makeConnection();
//        Statement stm = con.createStatement();
//        ResultSet rs = stm.executeQuery("select can_id,job_id,email,can_cv,score,isStatus from [Candidate] where isStatus = 4 order by job_id, can_id  ASC");
//        list = new ArrayList<>();
//        while (rs.next()) {
//            CandidateDTO c = new CandidateDTO();
//            c.setId(rs.getString("can_id"));
//            c.setJobId(rs.getString("job_id"));
//            c.setEmail(rs.getString("email"));
//            c.setCv(rs.getString("can_cv"));
//            c.setScore(rs.getInt("score"));
//            c.setIsStatus(rs.getInt("isStatus"));
//            list.add(c);
//        }
//        con.close();
//        return list;
//    }
//    public List<CandidateDTO> sortByJobDESCRecruit() throws SQLException, ClassNotFoundException {
//        List<CandidateDTO> list = null;
//        Connection con = DBUtils.makeConnection();
//        Statement stm = con.createStatement();
//        ResultSet rs = stm.executeQuery("select can_id,job_id,email,can_cv,score,isStatus from [Candidate] where isStatus = 4 order by job_id DESC , can_id ASC");
//        list = new ArrayList<>();
//        while (rs.next()) {
//            CandidateDTO c = new CandidateDTO();
//            c.setId(rs.getString("can_id"));
//            c.setJobId(rs.getString("job_id"));
//            c.setEmail(rs.getString("email"));
//            c.setCv(rs.getString("can_cv"));
//            c.setScore(rs.getInt("score"));
//            c.setIsStatus(rs.getInt("isStatus"));
//            list.add(c);
//        }
//        con.close();
//        return list;
//    }
    // SORT CAN_ID RECRUIT
    public List<CandidateDTO> sortByCanASCRecruit() throws SQLException, ClassNotFoundException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score , i.inter_score, c.isStatus from candidate c "
                + "inner join interviewing i on c.can_id = i.can_id "
                + "inner join job j on c.job_id = j.job_id "
                + "where c.isStatus =4 order by can_id  ASC");
        list = new ArrayList<>();
        while (rs.next()) {
            InterviewingDTO i = new InterviewingDTO();
            JobsDTO j = new JobsDTO();
            i.setScore(rs.getInt(6));
            String id = rs.getString(1);
            j.setJob_name(rs.getString(2));
            String cv = rs.getString(4);
            String email = rs.getString(3);
            float score = rs.getInt(5);
            int isStatus = rs.getInt(7);
            CandidateDTO join = new CandidateDTO(id, j, cv, email, score, i, isStatus);
            list.add(join);
        }
        con.close();
        return list;
    }

    public List<CandidateDTO> sortByCanDESCRecruit() throws SQLException, ClassNotFoundException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score , i.inter_score, c.isStatus from candidate c "
                + "inner join interviewing i on c.can_id = i.can_id "
                + "inner join job j on c.job_id = j.job_id "
                + "where c.isStatus =4 order by can_id  DESC");
        list = new ArrayList<>();
        while (rs.next()) {
            InterviewingDTO i = new InterviewingDTO();
            JobsDTO j = new JobsDTO();
            i.setScore(rs.getInt(6));
            String id = rs.getString(1);
            j.setJob_name(rs.getString(2));
            String cv = rs.getString(4);
            String email = rs.getString(3);
            float score = rs.getInt(5);
            int isStatus = rs.getInt(7);
            CandidateDTO join = new CandidateDTO(id, j, cv, email, score, i, isStatus);
            list.add(join);
        }
        con.close();
        return list;
    }

    // SORT EXAM SCORE RECRUIT
    public List<CandidateDTO> sortByScoreASCRecruit() throws SQLException, ClassNotFoundException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score , i.inter_score, c.isStatus from candidate c "
                + "inner join interviewing i on c.can_id = i.can_id "
                + "inner join job j on c.job_id = j.job_id "
                + "where c.isStatus =4 order by score,can_id  ASC");
        list = new ArrayList<>();
        while (rs.next()) {
            InterviewingDTO i = new InterviewingDTO();
            JobsDTO j = new JobsDTO();
            i.setScore(rs.getInt(6));
            String id = rs.getString(1);
            j.setJob_name(rs.getString(2));
            String cv = rs.getString(4);
            String email = rs.getString(3);
            float score = rs.getInt(5);
            int isStatus = rs.getInt(7);
            CandidateDTO join = new CandidateDTO(id, j, cv, email, score, i, isStatus);
            list.add(join);
        }
        con.close();
        return list;
    }

    public List<CandidateDTO> sortByScoreDESCRecruit() throws SQLException, ClassNotFoundException {
        List<CandidateDTO> list = null;
        Connection con = DBUtils.makeConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("select c.can_id,j.job_name,c.email,can_cv,score , i.inter_score, c.isStatus FROM [Candidate] c "
                + "INNER JOIN interviewing i ON c.[can_id] = i.[can_id] "
                + "INNER JOIN job j ON c.[job_id] = j.[job_id] "
                + "WHERE c.isStatus =4 ORDER BY [score] DESC,[can_id]  ASC");
        list = new ArrayList<>();
        while (rs.next()) {
            InterviewingDTO i = new InterviewingDTO();
            JobsDTO j = new JobsDTO();
            i.setScore(rs.getInt(6));
            String id = rs.getString(1);
            j.setJob_name(rs.getString(2));
            String cv = rs.getString(4);
            String email = rs.getString(3);
            float score = rs.getInt(5);
            int isStatus = rs.getInt(7);
            CandidateDTO join = new CandidateDTO(id, j, cv, email, score, i, isStatus);
            list.add(join);
        }
        con.close();
        return list;
    }

    // Custom
    public void updateup(String can_id) throws SQLException, ClassNotFoundException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("UPDATE [Candidate] SET [isStatus] = [isStatus] + 1 WHERE [can_id] = ?");
        stm.setString(1, can_id);
        stm.executeUpdate();
        con.close();
    }

    public void delete(String can_id) throws SQLException, ClassNotFoundException {
        Connection con = DBUtils.makeConnection();
        System.out.println("Connection done [Delete]");

        PreparedStatement stm = con.prepareStatement("DELETE FROM [Candidate] WHERE [can_id] = ?");
        stm.setString(1, can_id);
        stm.executeUpdate();
        System.out.println("Deleted(1): " + can_id);
        con.close();
    }

    public int getMajor(String canId) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("select [Job].[major_id] from [Candidate] INNER JOIN [Job] ON   [Candidate].[job_id] = [Job].[job_id] where [Candidate].[can_id] = ? ");
        stm.setString(1, canId);
        ResultSet rs = stm.executeQuery();
        int major = 0;
        if (rs.next()) {
            major = rs.getInt("major_id");
        }
        con.close();
        return major;
    }

    public void result(double score, String id) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("Update [Candidate] set [score] = ? , [isStatus] = 2 where [can_id] = ? ");
        stm.setDouble(1, score);
        stm.setString(2, id);
        stm.executeUpdate();
        System.out.println("Update " + score + " " + id);
        con.close();
    }

    public boolean check(String canId) throws ClassNotFoundException, SQLException {
        Connection con = DBUtils.makeConnection();
        PreparedStatement stm = con.prepareStatement("Select [isStatus] from [Candidate] where [can_id] = ? ");
        stm.setString(1, canId);
        ResultSet rs = stm.executeQuery();
        boolean check = false;
        if (rs.next()) {
            if (rs.getInt("isStatus") == 2) {
                check = true;
            }
        }
        con.close();
        return check;
    }

}
