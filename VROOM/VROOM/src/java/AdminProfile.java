/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
 * @author blraj
 */
@WebServlet(urlPatterns = {"/AdminProfile"})
public class AdminProfile extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           HttpSession session = request.getSession(false);
           response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
           response.setHeader("Pragma", "no-cache");
           response.setHeader("Expires", "0");
           if(session != null)
           {
               String FirstName = (String) session.getAttribute("firstname");
               String LastName = (String) session.getAttribute("lastname");
               
                out.println("<html lang=\"en\">\n" +
                        "    <head>\n" +
                        "        <meta charset=\"UTF-8\">\n" +
                        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "        <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                        "        <link rel=\"stylesheet\" href=\"css/user_main.css\">\n" +
                        "         <script\n" +
                        "      src=\"https://kit.fontawesome.com/64d58efce2.js\"\n" +
                        "      crossorigin=\"anonymous\"\n" +
                        "    ></script>\n" +
                        "        <title>CRS</title>\n" +
                        "    </head>\n" +
                        "    <body>\n" +
                        "        <aside>\n" +
                        "            <figure>\n" +
                        "                <div id=\"avatar\"><img  class=\"logo2\" src=\"images/person_logo.png\"></div>\n" +
                        "                <a href=\"./AdminProfile\">\n" +
                        "                <button style=\"background:none;border:none;font-size: 1.3em;font-weight: bold; margin-left: 15%;\">" 
                                         +LastName+FirstName+"</button></a>\n"+
                        "            </figure>\n" +
                        "            <img  class=\"img\" src=\"images/menu.svg\" alt=\"menu\">\n" +
                        "            <nav>\n" +
                        "                <ul>\n" +
                        "                    <li><a href=\"./AdminPrflData\">Profile</a></li>\n" +
                        "                \n" +
                        "                    <li><a href=\"./AdminUpsertCars\">Upsert Cars</a></li>\n" +
                        "                    <li><a href=\"./AdminUpsLoc\">Upsert Location</a></li>\n" +
                        "                    <li><a href=\"./AdminBkngHistDate\">Booking History</a></li>\n" +
                        "                    <li><a href=\"./Logout\">Logout</a></li>\n" +
                        "                </ul>\n" +
                        "            </nav>\n" +
                        "        </aside>\n" +
                        "        <main> \n" +
                        "            <div class=\"logosec\"><img class=\"logo\" src=\"images/Welcome.svg\" style=\"height:380px\" alt=\"logo\">\n" +
                        "                \n" +
                        "            </div>\n" +
                        "            <h1>Hello, "+FirstName+" "+LastName+"</h1> \n" +
                        "            <p> Being happy is being productive!</p>\n" +
                        "\n" +
                        "        </main>\n" +
                        "        <script>\n" +
                        "        \n" +
                        "            (function() {\n" +
                        "                var menu = document.querySelector('ul'),\n" +
                        "                    menulink = document.querySelector('img');\n" +
                        "                \n" +
                        "                menulink.addEventListener('click', function(e) {\n" +
                        "                    menu.classList.toggle('active');\n" +
                        "                    e.preventDefault();\n" +
                        "                });\n" +
                        "            })();\n" +
                        "        \n" +
                        "        </script>\n" +
                        "    </body>\n" +
                        "</html>");
           }
           else
           {
               response.sendRedirect("signin-signup.html");
               response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
               response.setHeader("Pragma", "no-cache");
               response.setHeader("Expires", "0");

               
           }
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
