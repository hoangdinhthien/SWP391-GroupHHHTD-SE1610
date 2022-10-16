/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import config.Config;
import daos.CandidateDAO;
import dtos.CandidateDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ListCvController", urlPatterns = {"/listcv"})
public class ListCvController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("controller", "listcv");
        String op = request.getParameter("op");
        request.setAttribute("action", op);
        switch (op) {
            case "list":
                list(request, response);

                break;
            case "userviewlist":
                list(request, response);
                break;

            case "hrstatus0":
                hrstatus0(request, response);
                break;
            case "hrstatus14":
                hrstatus14(request, response);
                break;
        }
    }

    protected void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            CandidateDAO tf = new CandidateDAO();
            List<CandidateDTO> userviewlist = tf.userviewlist();
            request.setAttribute("list", userviewlist);
//        request.getRequestDispatcher("/userviewlist.jsp").forward(request, response);

            request.setAttribute("controller", "listcv");
            request.setAttribute("action", "userviewlist");
            request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

//    protected void userviewlist(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        CandidateDAO tf = new CandidateDAO();
//        List<CandidateDTO> userviewlist = tf.userviewlist();
//        request.setAttribute("userviewlist", userviewlist);
////        request.setAttribute("controller", "listcv");
////        request.setAttribute("action", "userviewlist");
////        request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
//    }
    protected void hrstatus0(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CandidateDAO tf = new CandidateDAO();
        List<CandidateDTO> list = tf.hrstatus0();
        request.setAttribute("list", list);
        request.setAttribute("controller", "listcv");
        request.setAttribute("action", "hrstatus0");
        request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
    }

    protected void hrstatus14(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CandidateDAO tf = new CandidateDAO();
        List<CandidateDTO> list = tf.hrstatus14();
        request.setAttribute("list", list);
        request.setAttribute("controller", "listcv");
        request.setAttribute("action", "hrstatus14");
        request.getRequestDispatcher(Config.LAYOUT).forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
