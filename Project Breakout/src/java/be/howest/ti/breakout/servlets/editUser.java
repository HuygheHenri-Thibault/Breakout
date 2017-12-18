/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.servlets;


import be.howest.ti.breakout.data.Repositories;
import be.howest.ti.breakout.data.UserRepository;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Henri
 */
@WebServlet(name = "editUser", urlPatterns = {"/editUser"})
public class editUser extends HttpServlet {

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
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String passwordCheck = request.getParameter("passwordCheck");
            String bio = request.getParameter("bio");
            
            UserRepository userRepo = Repositories.getUserRepository();
            int userId = userRepo.getUserWithUsername((String)request.getSession().getAttribute("username")).getUserId();

            
            if(email != null && !email.equals("")) {
                userRepo.updateUserStringField(userId, "email", email);
            }
            if(username != null && !username.equals("")) {
                userRepo.updateUserStringField(userId, "username", username);
                request.getSession().setAttribute("username", username);
            }
            if(bio != null && !bio.equals("")) {
                userRepo.updateUserStringField(userId, "bio", bio);
            }
            if(password != null && !password.equals("") && passwordCheck != null && !passwordCheck.equals("")) {
                if(password.equals(passwordCheck)) {
                    userRepo.updateUserStringField(userId, "password", password);
                    request.getSession().setAttribute("password", password);
                }
            }
            
            
            response.sendRedirect("userPage");
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
