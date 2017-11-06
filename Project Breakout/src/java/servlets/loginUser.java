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

/**
 *
 * @author Henri
 */
@WebServlet(name = "loginUser", urlPatterns = {"/loginUser"})
public class loginUser extends HttpServlet {

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User u = Repositories.getUserRepository().getUserWithUsername(username);
        if (u.getHashPassword().equals(password)) {
                response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<head>\n" +
"    <meta charset=\"UTF-8\">\n" +
"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
"    <!--Import Google Icon Font & materialize.css-->\n" +
"    <link href=\"http://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">\n" +
"    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/css/materialize.min.css\">\n" +
"    <link rel=\"stylesheet\" href=\"assets/css/screen.css\">\n" +
"    <link rel=\"stylesheet\" href=\"assets/css/user.css\">\n" +
"    <title>Breakout</title>\n" +
"</head>\n" +
"\n" +
"<body class=\"med-grey\">\n" +
"    <header class=\"dark-grey z-depth-4\">\n" +
"        <nav class=\"transparent\">\n" +
"            <div class=\"nav-wrapper\">\n" +
"                <a href=\"index.html\" class=\"brand-logo\">Logo</a>\n" +
"                <ul class=\"right\">\n" +
"                    <li id=\"user\">\n" +
"                        <a href=\"#\" class=\"dropdown-button\" data-activates=\"user-options\">"+u.getUsername()+"\n" +
"                            <i class=\"material-icons right\">arrow_drop_down</i>\n" +
"                        </a>\n" +
"                        <!-- TODO: Replace _USERNAME_ with the actual username of the user -->\n" +
"                    </li>\n" +
"                    <li><i class=\"material-icons right\">account_circle</i></li>\n" +
"                    <!-- TODO: Add username username here if logged in & otherwise 'sign in' button -->\n" +
"                </ul>\n" +
"                <!-- Dropdown TODO: when the user is logged in this is displayed and otherwise it is not $(\".nav-wrapper\").append(\"hieronder\") -->\n" +
"                <ul id=\"user-options\" class=\"dropdown-content light-grey\">\n" +
"                    <li><a href=\"#!\" class=\"white-text text-lighten-1\">one</a></li>\n" +
"                    <li><a href=\"#!\" class=\"white-text\">Just a test bro</a></li>\n" +
"                    <li class=\"divider\"></li>\n" +
"                    <li><a href=\"#!\" class=\"white-text red\">Log out</a></li>\n" +
"                </ul>\n" +
"            </div>\n" +
"        </nav>\n" +
"  </header>\n" +
"\n" +
"    <main>\n" +
"        <aside class=\"sidenav light-grey\">\n" +
"            <!-- TODO: Add user nav here for settings,... -->\n" +
"            <ul>\n" +
"                <li><a href=\"\">User</a></li>\n" +
"                <li><a href=\"\">Settings</a></li>\n" +
"                <li><a href=\"\">lel</a></li>\n" +
"                <li class=\"divider grey\"></li>\n" +
"                <li><a href=\"\">test</a></li>\n" +
"            </ul>\n" +
"        </aside>\n" +
"        <section id=\"user-area\">\n" +
"            <div id=\"user-head\">\n" +
"                <img src=\"http://summeratais.org/wp-content/uploads/2017/01/fff.png\" title=\"profile picture\" alt=\"profile picture\" class=\"responsive-img circle\">\n" +
"                <h1>"+u.getUsername()+"</h1>\n" +
"                <!-- TODO: Replace _USERNAME_ with the actual username of the user -->\n" +
"            </div>\n" +
"            <div id=\"user-info\">\n" +
"                <div class=\"left red\" style=\"width:50%;\">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</div>\n" +
"                <div class=\"left blue\" style=\"width:50%;\">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</div>\n" +
"                <div class=\"left amber\" style=\"width:50%;\">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</div>\n" +
"                <div class=\"left indigo\" style=\"width:50%;\">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</div>\n" +
"            </div>\n" +
"        </section>\n" +
"        <section id=\"setting-area\" class=\"hide\">\n" +
"            <!-- TODO: Add content & more sections as needed -->\n" +
"        </section>\n" +
"    </main>\n" +
"\n" +
"    <footer class=\"page-footer dark-grey\">\n" +
"        <p class=\"grey-text text-darken-2\">Copyright (c) 2017 Copyright Holder All Rights Reserved.</p>\n" +
"    </footer>\n" +
"    \n" +
"    <!--Import jQuery before materialize.js-->\n" +
"    <script type=\"text/javascript\" src=\"https://code.jquery.com/jquery-2.1.1.min.js\"></script>\n" +
"    <script src=\"https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/js/materialize.min.js\"></script>\n" +
"</body>\n" +
"</html>");

            }
        } else {
            response.sendRedirect("index.html");
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
