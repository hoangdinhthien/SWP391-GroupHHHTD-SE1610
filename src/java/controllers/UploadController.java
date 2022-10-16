/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import config.Config;
import daos.CandidateDAO;
import daos.UserDAO;
import dtos.CandidateDTO;
import dtos.GoogleDTO;
import dtos.UserDTO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import utils.DBUtils;
import utils.GoogleUtils;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UploadController", urlPatterns = {"/upload"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10,
        maxFileSize = 1024 * 1024 * 1000,
        maxRequestSize = 1024 * 1024 * 1000)

public class UploadController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //Upload
    PrintWriter out = null;
    Connection con = null;
    PreparedStatement ps = null;
    HttpSession session = null;

    //Download
    public static int BUFFER_SIZE = 1024 * 100;
    public static final String UPLOAD_DIR = "cv";
    public static String fileName = null;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        request.setAttribute("controller", "upload");
        String op = request.getParameter("op");
        request.setAttribute("action", op);
        switch (op) {
            case "upload_index":
                upload_index(request, response);
                break;
            //Sau khi click login with google
            case "uploadFile":
                uploadFile(request, response);
                break;
            //Sau khi click logout
            case "downloadFile":
                downloadFile(request, response);
                break;
            case "file_list":
                file_list(request, response);
                break;
            case "deleteFile":
                deleteFile(request, response);
                break;
            case "yesup":
                yesup(request, response);
                break;
        }
    }

    protected void upload_index(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
//        CandidateDAO tf = new CandidateDAO();
//        List<CandidateDTO> list = tf.selectAll();
//        request.setAttribute("list", list);
        request.setAttribute("controller", "upload");
        request.setAttribute("action", "upload_index");
        request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
    }

    protected void file_list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
    }

    protected void uploadFile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            out = response.getWriter();
            session = request.getSession(false);
            String folderName = "cv";
            Part filePart = request.getPart("file");//Textbox name FILE
            String fileName = filePart.getSubmittedFileName();

            //Đường dẫn đến Nơi Lưu
            // C:\Users\ADMIN\OneDrive\Máy tính\PRJ\UploadFileProPro\build\web\cv\
            String uploadPath = request.getServletContext().getRealPath("") + folderName + File.separator;

            File dir = new File(uploadPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String path
                    = //                    folderName +
                    fileName;
            UserDTO userDTO = new UserDTO();
            userDTO.getEmail();

            System.out.println("fileName:" + fileName);
            System.out.println("path:" + path);

            //============
            // Mã của can_id : Cxxx
            // Mã của job_id : Jxxx
            int num = 5;
            String can_id = "C00" + num;
            String job_id = "J00" + num;
            String gmail
                    =                     num + 
                    "@gmail.com";

            InputStream is = filePart.getInputStream();
            Files.copy(is, Paths.get(uploadPath + fileName), StandardCopyOption.REPLACE_EXISTING);
            try {
                con = DBUtils.makeConnection();
                System.out.println("Connection done [Upload]");

                String sql = "insert into candidates(can_id,job_id,email,can_cv,isStatus) "
                        + "values(?,?,?,?,?)";

                ps = con.prepareStatement(sql);
                ps.setString(1, can_id);
                //1 email - 1 job_id
                ps.setString(2, job_id);
                System.out.println("Email: " + userDTO.getEmail());
//                ps.setString(3, "2@gmail.com");
                System.out.println("Email" + gmail);
                ps.setString(3, gmail);
                System.out.println(gmail);
                ps.setString(4, path);
                ps.setInt(5, 0);

                int status = ps.executeUpdate();
                if (status > 0) {
                    session.setAttribute("fileName", fileName);
                    String msg = "" + fileName + "file uploaded successfully...";
                    request.setAttribute("msg", msg);

//Đường tới /upload/success.jsp
                    request.setAttribute("controller", "upload");
                    request.setAttribute("action", "success");
                    request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
//                    RequestDispatcher rd = request.getRequestDispatcher("/success.jsp");
//                    rd.forward(request, response);
//=====
                    System.out.println("File upload successfully");
                    System.out.println("Uploaded Path: " + uploadPath);
                }
            } catch (SQLException ex) {
                out.println("Exception: " + ex);
                System.out.println("Exception1: " + ex);

            } finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException e) {
                    out.println(e);
                }
            }

        } catch (IOException | ServletException e) {
            out.println("Exception: " + e);
            System.out.println("Exception2: " + e);
        }

    }

    protected void downloadFile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        fileName = request.getParameter("fileName");
        if (fileName == null || fileName.equals("")) {
            response.setContentType("text/html");
            response.getWriter().println("<h3>File " + fileName + " Is Not Present ...!(1)<h3>");
            System.out.println("Error Downloading :" + fileName);
        } else {
            System.out.println("Downloading(2) :" + fileName);
            String applicationPath = getServletContext().getRealPath("");
            String downloadPath = applicationPath
                    + UPLOAD_DIR;
            String filePath = downloadPath + File.separator + fileName;
            System.out.println(fileName);
            System.out.println(filePath);

            File file = new File(filePath);
            OutputStream outStream = null;
            FileInputStream inputStream = null;

            if (file.exists()) {
                String mimeType = "application/octet-stream";
                response.setContentType(mimeType);

                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
                response.setHeader(headerKey, headerValue);

                try {
                    outStream = response.getOutputStream();
                    inputStream = new FileInputStream(file);
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int bytesRead = -1;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outStream.write(buffer, 0, bytesRead);
                    }
                } catch (IOException ioExObj) {
                    System.out.println("Exception While Performing The I/O Operation?= " + ioExObj.getMessage());
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }

                    outStream.flush();
                    if (outStream != null) {
                        outStream.close();
                    }
                }
            } else {
                response.setContentType("text/html");
                response.getWriter().println("<h3>File " + fileName + " Is Not Present..!(2)<h3>");
                System.out.println("Upload FileName: " + fileName);
            }

        }
    }

    protected void deleteFile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String can_id = request.getParameter("can_id"); // lấy id
            CandidateDAO tf = new CandidateDAO();
            tf.delete(can_id);
            //Cho hiện lại danh sách 
            response.sendRedirect("upload?op=upload_index");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    protected void yesup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String can_id = request.getParameter("can_id"); // lấy id        
            CandidateDAO tf = new CandidateDAO();
            tf.updateup(can_id);
            CandidateDTO cd = new CandidateDTO();
            System.out.println("status :" + cd.getIsStatus());
            //Cho hiện lại danh sách 
            response.sendRedirect("upload?op=upload_index");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UploadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UploadController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
