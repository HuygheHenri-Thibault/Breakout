/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import data.Repositories;
import domain.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Henri
 */
@WebServlet(name = "userPage", urlPatterns = {"/userPage"})
public class userPage extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        if(session != null) {
            String username = (String)session.getAttribute("username");
            String password = (String)session.getAttribute("password");
        
            User u = Repositories.getUserRepository().getUserWithUsername(username);
            if (u.getHashPassword().equals(password)) {
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html><html lang=\"en\">"
                            + "<head>"
                                + "<meta charset=\"UTF-8\">"
                                + "<meta name=\"viewport\"content=\"width=device-width, initial-scale=1.0\">"
                                + "<meta http-equiv=\"X-UA-Compatible\"content=\"ie=edge\">"
                                + "<!--Import Google Icon Font & materialize.css-->"
                                + "<link href=\"http://fonts.googleapis.com/icon?family=Material+Icons\"rel=\"stylesheet\">"
                                + "<link rel=\"stylesheet\"href=\"https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/css/materialize.min.css\">"
                                + "<link rel=\"stylesheet\"href=\"assets/css/screen.css\">"
                                + "<link rel=\"stylesheet\"href=\"assets/css/user.css\">"
                                + "<title>Breakout</title>"
                            + "</head>"
                            + "<body class=\"med-grey\">"
                                + "<header class=\"dark-grey z-depth-4\">"
                                    + "<nav class=\"transparent\">"
                                        + "<div class=\"nav-wrapper\">"
                                            + "<a href=\"index.html\"class=\"brand-logo\">Logo</a>"
                                            + "<ul class=\"right\">"
                                                + "<li id=\"user\">"
                                                    + "<a href=\"#\"class=\"dropdown-button\"data-activates=\"user-options\">"+session.getAttribute("username") 
                                                        + "<i class=\"material-icons right\">arrow_drop_down</i>"
                                                    + "</a>"
                                                    + "<!-- TODO: Replace _USERNAME_ with the actual username of the user -->"
                                                + "</li>"
                                                + "<li><i class=\"material-icons right\">account_circle</i></li>"
                                                + "<!-- TODO: Add username username here if logged in & otherwise 'sign in' button -->"
                                            + "</ul>"
                                            + "<!-- Dropdown TODO: when the user is logged in this is displayed and otherwise it is not $(\".nav-wrapper\").append(\"hieronder\") -->"
                                            + "<ul id=\"user-options\"class=\"dropdown-content light-grey\">"+
                                                "<li><a href=\"#!\"class=\"white-text text-lighten-1\">one</a></li>"+
                                                "<li><a href=\"#!\"class=\"white-text\">Just a test bro</a></li>"+
                                                "<li class=\"divider\"></li>"+
                                                "<li><a href=\"#!\"class=\"white-text red\">Log out</a></li>"+
                                            "</ul>"+
                                        "</div>"+
                                    "</nav>"+
                            "</header>"+
                            "<main>"+
                                "<div class=\"row\">"+
                                    "<h1 class=\"white-text center-align\">"+session.getAttribute("username")+"</h1>"+
                                "</div>"+
                                "<div class=\"row\">"+
                                    "<div class=\"col s2 offset-s3\">"+
                                        "<p class=\"white-text center-align\">Level _VALUE_</p>"+
                                    "</div>"+
                                    "<div class=\"col s2\">"+
                                        "<p class=\"white-text center-align\">Gems: _VALUE_</p>"+
                                    "</div>"+
                                    "<div class=\"col s2\">"+
                                        "<p class=\"white-text center-align\">Gold: _VALUE_</p>"+
                                    "</div>"+
                                "</div>"+
                                "<div class=\"row\">"+
                                    "<div class=\"col s5 offset-s1\">"+
                                        "<div class=\"card-panel\">test</div>"+
                                    "</div>"+
                                    "<div class=\"col s3 offset-s2\">"+
                                        "<div class=\"card-panel\">test</div>"+
                                    "</div>"+
                                "</div>"+
                                "<div class=\"row spacing\">"+
                                    "<div class=\"col s4 offset-s1\">"+
                                        "<img src=\"https://upload.wikimedia.org/wikipedia/commons/5/55/Square_dissected_into_6_equal_area_triangles_no_border.svg\"alt=\"\"class=\"responsive-img circle profile-picture\">"+
                                    "</div>"+
                                    "<div id=\"recent-activity\"class=\"col s6\">"+
                                        "<ul class=\"collection with-header\">"+
                                            "<li class=\"collection-item\">"+
                                                "<h2 class=\"center-align\">Recent activity</h2>"+
                                            "</li>"+
                                            "<li class=\"collection-item\">one</li>"+
                                            "<li class=\"collection-item\">two</li>"+
                                            "<li class=\"collection-item\">three</li>"+
                                            "<li class=\"collection-item\">four</li>"+
                                            "<li class=\"collection-item\">five</li>"+
                                        "</ul>"+
                                    "</div>"+
                                "</div>"+
                                "<div class=\"row spacing\">"+
                                    "<div class=\"col s1 offset-s1\">"+
                                        "<img src=\"https://upload.wikimedia.org/wikipedia/commons/5/55/Square_dissected_into_6_equal_area_triangles_no_border.svg\"alt=\"\"class=\"responsive-img circle\">"+
                                    "</div>"+
                                    "<div class=\"col s1\">"+
                                        "<img src=\"https://upload.wikimedia.org/wikipedia/commons/5/55/Square_dissected_into_6_equal_area_triangles_no_border.svg\"alt=\"\"class=\"responsive-img circle\">"+
                                    "</div>"+
                                    "<div class=\"col s1\">"+
                                        "<img src=\"https://upload.wikimedia.org/wikipedia/commons/5/55/Square_dissected_into_6_equal_area_triangles_no_border.svg\"alt=\"\"class=\"responsive-img circle\">"+
                                    "</div>"+
                                    "<div class=\"col s1\">"+
                                        "<img src=\"https://upload.wikimedia.org/wikipedia/commons/5/55/Square_dissected_into_6_equal_area_triangles_no_border.svg\"alt=\"\"class=\"responsive-img circle\">"+
                                    "</div>"+
                                    "<div class=\"col s1\">"+
                                        "<img src=\"https://upload.wikimedia.org/wikipedia/commons/5/55/Square_dissected_into_6_equal_area_triangles_no_border.svg\"alt=\"\"class=\"responsive-img circle\">"+
                                    "</div>"+
                                "</div>"+
                            "</main>"+
                            "<footer class=\"page-footer dark-grey\">"+
                                "<p class=\"grey-text text-darken-2\">Copyright (c) 2017 Copyright Holder All Rights Reserved.</p>"+
                            "</footer>"+
                            "<!--Import jQuery before materialize.js-->"+
                            "<script type=\"text/javascript\"src=\"https://code.jquery.com/jquery-2.1.1.min.js\"></script>"+
                            "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/js/materialize.min.js\"></script>"+
                        "</body>"+
                    "</html>");

                }
            } else {
                response.sendRedirect("index.html");
            }
        } else {
            response.sendRedirect("login.html");
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
