/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.howest.ti.breakout.servlets;

import be.howest.ti.breakout.data.Repositories;
import be.howest.ti.breakout.data.UserRepository;
import be.howest.ti.breakout.domain.game.MultiPlayerHighscore;
import be.howest.ti.breakout.domain.game.SinglePlayerHighscore;
import be.howest.ti.breakout.domain.game.User;
import be.howest.ti.breakout.util.BCrypt;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;
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
 * @author Brecht
 */
@WebServlet(name = "ControllerServlet", urlPatterns = {"/registerUser", "/LogInUser", "/LogOutUser", "/editUser", "/userPage", "/CheckLoggedIn", "/support", "/getScore"})
public class ControllerServlet extends HttpServlet {
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
        String path = request.getServletPath();
        
        switch(path){
            case "/registerUser":
                this.register(request, response);
                break;
            case "/LogInUser":
                this.login(request, response);
                break;
            case "/LogOutUser":
                HttpSession session = request.getSession();
                session.invalidate();
                request.getRequestDispatcher("index.html").forward(request, response);
                break;
            case "/editUser":
                this.edit(request, response);
                break;
            case "/userPage":
                this.createUserPage(request, response);
                break;
            case "/CheckLoggedIn":
                this.checkLoggedIn(request, response);
                break;
            case "/support":
                this.support(request, response);
                break;
            case "/getScore":
                List<User> totalScores = Repositories.getUserRepository().getAllUsers();
                request.setAttribute("totalscore", totalScores);
                List<SinglePlayerHighscore> spScores = Repositories.getHighscoreRepository().getAllSingleplayerHighscores();
                request.setAttribute("SPscores", spScores);
                List<MultiPlayerHighscore> mpScores = Repositories.getHighscoreRepository().getAllMultiplayerScores();
                request.setAttribute("MPscores", mpScores);
                request.getRequestDispatcher("leaderboard.jsp").forward(request, response);
        }
    }
    
    private void support(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    String username = request.getParameter("username");
    String onderwerp = request.getParameter("subject");
    String telnr = request.getParameter("phonenumber");
    String bericht = request.getParameter("bericht");
    
        AuthMethod auth = new TokenAuthMethod("48e4e3e2", "17c43e555e15b92e");
        NexmoClient client = new NexmoClient(auth);
    
    SmsSubmissionResult[] responses = null;
        try {
            responses = client.getSmsClient().submitMessage(new TextMessage(
                    "Breakout 2017",
                    telnr,
                    "Support request succesfull, a ticket has now been opened. Subject: "+onderwerp+" We thank you for your trust."));
        } catch (NexmoClientException ex) {
            Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String email = request.getParameter("email");
        String username = request.getParameter("usernameInput");
        String password = request.getParameter("password");
        String passwordCheck = request.getParameter("passwordCheck");
        String hashedPasswd = BCrypt.hashpw(password, BCrypt.gensalt(10));
        if(password.equals(passwordCheck)) {
            if(Repositories.getUserRepository().getUserWithUsername(username) == null) { // user doesn't exsist in this case
                Repositories.getUserRepository().addUser(new User(username, hashedPasswd, email));
                request.getRequestDispatcher("index.html").forward(request, response);
            } else {
                request.getRequestDispatcher("index.html").forward(request, response);
            }
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("usernameInput");
        String password = request.getParameter("password");
        User u = Repositories.getUserRepository().getUserWithUsername(username);
        if (u != null && BCrypt.checkpw(password, u.getHashPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("password", password);
            request.getRequestDispatcher("userPage.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }
    
    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
        request.getRequestDispatcher("userPage.jsp").forward(request, response); 
    }
    
    private void createUserPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession();
        if(session != null) {
            String username = (String)session.getAttribute("username");
            String password = (String)session.getAttribute("password");

            User u = Repositories.getUserRepository().getUserWithUsername(username);
            if (u.getHashPassword().equals(password)) {
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "\n" +
                    "<head>\n" +
                    "  <meta charset=\"UTF-8\">\n" +
                    "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "  <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                    "  <!--Import Google Icon Font & materialize.css-->\n" +
                    "  <link href=\"http://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">\n" +
                    "  <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/css/materialize.min.css\">\n" +
                    "  <link rel=\"stylesheet\" href=\"assets/css/screen.css\">\n" +
                    "  <link rel=\"stylesheet\" href=\"assets/css/user.css\">\n" +
                    "  <title>Breakout</title>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body class=\"med-grey\">\n" +
                    "  <header class=\"dark-grey z-depth-4\">\n" +
                    "    <nav class=\"transparent\">\n" +
                    "      <div class=\"nav-wrapper\">\n" +
                    "        <a href=\"index.html\" class=\"brand-logo\">Logo</a>\n" +
                    "        <ul class=\"right\">\n" +
                    "          <li id=\"user\">\n" +
                    "            <a href=\"#\" class=\"dropdown-button\" data-activates=\"user-options\"></a>\n" +
                    "          </li>\n" +
                    "          <li><i class=\"material-icons right\">account_circle</i></li>\n" +
                    "        </ul>\n" +
                    "      </div>\n" +
                    "    </nav>\n" +
                    "  </header>\n" +
                    "\n" +
                    "  <main>\n" +
                    "    <div class=\"row\">\n" +
                    "      <div class=\"col s4 offset-s4\">\n" +
                    "        <h1 class=\"white-text center-align\">"+u.getUsername()+"</h1>\n" +
                    "      </div>\n" +
                    "      <div class=\"col s1 offset-s3\">\n" +
                    "        <a class=\"modal-trigger\" href=\"#edit-user-modal\"><i class=\"material-icons white-text small\">settings</i></a>\n" +
                    "      </div>\n" +
                    "\n" +
                    "      <!-- MODAL START -->\n" +
                    "      <div id=\"edit-user-modal\" class=\"modal\">\n" +
                    "        <div class=\"modal-content med-grey white-text\">\n" +
                    "          <h4>Edit user</h4>\n" +
                    "          <form class=\"accented\" action=\"editUser\" method=\"post\">\n" +
                    "          <div class=\"row\">\n" +
                    "            <div class=\"col s5\">\n" +
                    "                <div class=\"input-field\">\n" +
                    "                  <input type=\"email\" id=\"email\" name=\"email\" />\n" +
                    "                  <label for=\"email\" class=\"active\">Email</label>\n" +
                    "                </div>\n" +
                    "\n" +
                    "                <div class=\"input-field\">\n" +
                    "                  <input type=\"text\" id=\"username\" name=\"username\" />\n" +
                    "                  <label for=\"username\" class=\"active\">Username</label>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "            <div class=\"col s5 offset-s1\">\n" +
                    "              <div class=\"input-field\">\n" +
                    "                <input type=\"password\" id=\"password\" name=\"password\" />\n" +
                    "                <label for=\"password\" class=\"active\">Password</label>\n" +
                    "              </div>" +
                    "              <div class=\"input-field\">\n" +
                    "                <input type=\"password\" id=\"passwordCheck\" name=\"passwordCheck\" />\n" +
                    "                <label for=\"passwordCheck\" class=\"active\">Password (again)</label>\n" +
                    "              </div>\n" +
                    "            </div>\n" +
                    "          </div>\n" +
                    "          <div class=\"row\">\n" +
                    "            <div class=\"col s6 offset-s3 input-field\">\n" +
                                "<input type=\"text\" id=\"bio\" name=\"bio\">"+
                                  "<label for=\"bio\" class=\"active\">Bio</label>"+
                    "            </div>\n" +
                    "          </div>\n" +
                    "          </form>\n" +
                    "        </div>\n" +
                    "        <div class=\"modal-footer dark-grey\">\n" +
                    "          <a href=\"#!\" id=\"cancel-edit\" class=\"modal-action modal-close waves-effect waves-red btn-flat\">\n" +
                    "            <i class=\"material-icons red-text small\">cancel</i>\n" +
                    "          </a>\n" +
                    "          <a href=\"#!\" id=\"confirm-edit\" class=\"modal-action modal-close waves-effect waves-green btn-flat\">\n" +
                    "            <i class=\"material-icons green-text small\">check_circle</i>\n" +
                    "          </a>\n" +
                    "        </div>\n" +
                    "      </div>\n" +
                    "      <!-- MODAL END -->\n" +
                    "\n" +
                    "    </div>\n" +
                    "    <div class=\"row\">\n" +
                    "      <div class=\"col s2 offset-s3\">\n" +
                    "        <p class=\"white-text center-align\">Level "+u.getLevel()+"</p>\n" +
                    "      </div>\n" +
                    "      <div class=\"col s2\">\n" +
                    "        <p class=\"white-text center-align\">Gems: _VALUE_</p>\n" +
                    "      </div>\n" +
                    "      <div class=\"col s2\">\n" +
                    "        <p class=\"white-text center-align\">Gold: _VALUE_</p>\n" +
                    "      </div>\n" +
                    "    </div>\n" +
                    "    <div class=\"row\">\n" +
                    "      <div class=\"col s5 offset-s1\">\n" +
                    "        <div class=\"card-panel\">"+u.getBio()+"</div>\n" +
                    "      </div>\n" +
                    "      <div class=\"col s3 offset-s2\">\n" +
                    "        <div class=\"card-panel\">_CLANNAME_</div>\n" +
                    "      </div>\n" +
                    "    </div>\n" +
                    "    <div class=\"row spacing\">\n" +
                    "      <div class=\"col s4 offset-s1\">\n" +
                    "        <img src=\"https://tinyurl.com/y8zv9vu8\" alt=\"\" class=\"responsive-img circle profile-picture\">\n" +
                    "      </div>\n" +
                    "      <div id=\"recent-activity\" class=\"col s6\">\n" +
                    "        <ul class=\"collection with-header\">\n" +
                    "          <li class=\"collection-item\">\n" +
                    "            <h2 class=\"center-align\">Recent activity</h2>\n" +
                    "          </li>\n" +
                    "          <li class=\"collection-item\">one</li>\n" +
                    "          <li class=\"collection-item\">two</li>\n" +
                    "          <li class=\"collection-item\">three</li>\n" +
                    "          <li class=\"collection-item\">four</li>\n" +
                    "          <li class=\"collection-item\">five</li>\n" +
                    "        </ul>\n" +
                    "      </div>\n" +
                    "    </div>\n" +
                    "    <div class=\"row spacing\">\n" +
                    "      <div class=\"col s1 offset-s1\">\n" +
                    "        <img src=\"https://tinyurl.com/y8zv9vu8\" alt=\"\" class=\"responsive-img circle\">\n" +
                    "      </div>\n" +
                    "      <div class=\"col s1\">\n" +
                    "        <img src=\"https://tinyurl.com/y8zv9vu8\" alt=\"\" class=\"responsive-img circle\">\n" +
                    "      </div>\n" +
                    "      <div class=\"col s1\">\n" +
                    "        <img src=\"https://tinyurl.com/y8zv9vu8\" alt=\"\" class=\"responsive-img circle\">\n" +
                    "      </div>\n" +
                    "      <div class=\"col s1\">\n" +
                    "        <img src=\"https://tinyurl.com/y8zv9vu8\" alt=\"\" class=\"responsive-img circle\">\n" +
                    "      </div>\n" +
                    "      <div class=\"col s1\">\n" +
                    "        <img src=\"https://tinyurl.com/y8zv9vu8\" alt=\"\" class=\"responsive-img circle\">\n" +
                    "      </div>\n" +
                    "    </div>\n" +
                    "  </main>\n" +
                    "\n" +
                    "  <footer class=\"page-footer dark-grey\">\n" +
                    "    <p class=\"grey-text text-darken-2\">Copyright (c) 2017 Copyright Holder All Rights Reserved.</p>\n" +
                    "  </footer>\n" +
                    "\n" +
                    "  <!--Import jQuery before materialize.js-->\n" +
                    "  <script type=\"text/javascript\" src=\"https://code.jquery.com/jquery-2.1.1.min.js\"></script>\n" +
                    "  <script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.2/js/materialize.min.js\"></script>\n" +
                    "  <script type=\"text/javascript\" src=\"assets/js/script.js\"></script>\n" +
                    "  <script type=\"text/javascript\" src=\"assets/js/user.js\"></script>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>");
                }
            } else {
                request.getRequestDispatcher("index.html").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("login.html").forward(request, response);
        }
    }
    
    private void checkLoggedIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        if(session.getAttribute("username") == null) {
            response.getWriter().write("Guest");
        } else {
            response.getWriter().write((String)session.getAttribute("username"));
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
