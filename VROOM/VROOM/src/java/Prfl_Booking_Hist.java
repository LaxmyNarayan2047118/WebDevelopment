/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
@WebServlet(urlPatterns = {"/Prfl_Booking_Hist"})
public class Prfl_Booking_Hist extends HttpServlet {

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
           String uid= (String) session.getAttribute("userid");
           
           Vroom vr = new Vroom();
           int userid = Integer.parseInt(uid);
           vr.pop_usr_bkng(userid);
           
           String FirstName = (String) session.getAttribute("firstname");  
           String LastName = (String) session.getAttribute("lastname");
          
           int no_of_bkngs = vr.user_bookings.get(userid).size();
           
           if(session == null)
           {
               RequestDispatcher rd = request.getRequestDispatcher("Login");
               rd.forward(request,response);
           }
           else
           {   
                out.println("<html lang=\"en\">\n" +
                        "    <head>\n" +
                        "        <meta charset=\"UTF-8\">\n" +
                        "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "        <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                        "        <link rel=\"stylesheet\" href=\"css/car_data.css\">\n" +
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
                        "                <a href=\"./Profile\">\n" +
                        "                <button style=\"background:none;border:none;font-size: 1.3em;font-weight: bold; margin-left: 15%;\">" 
                                         +FirstName+" "+ LastName+"</button></a>\n"+
                        "            </figure>\n" +
                        "            <img  class=\"img\" src=\"images/menu.svg\" alt=\"menu\">\n" +
                        "            <nav>\n" +
                        "                <ul>\n" +
                        "                    <li><a href=\"./Prfl_Data\">Profile</a></li>\n" +
                        "                \n" +
                        "                    <li><a href=\"./Prfl_CarAvailDate\">Book A Car</a></li>\n" +
                        "                    <li><a href=\"./Prfl_Book_Hist\">Booking History</a></li>\n" +
                        "                    <li><a href=\"./Logout\">Logout</a></li>\n" +
                        "                </ul>\n" +
                        "            </nav>\n" +
                        "        </aside>\n" +
                        "        <main> \n");
                        if(no_of_bkngs>0)
                        {
                            out.println("<div class=\"container\">\n");
                            out.println("<h1>Booking History</h1>");
                            out.println("<img src=\"images/BookHist.svg\" style=\"position:absolute; height:520px; top:15px; left:640px; bottom: 150px\">");
                            for(int i=0; i < no_of_bkngs; i++)
                            {
                                out.println("<h3>Booking : " + (i+1) + "</h3>" +
                                "<p>Booking ID : " + vr.user_bookings.get(userid).get(i).get(0) + "</p>" +
                                "<p>Billing ID : " + vr.user_bookings.get(userid).get(i).get(8) + "</p>" +
                                "<p>Car Model : "+ vr.car_details.get(Integer.parseInt(vr.user_bookings.get(userid).get(i).get(2))).get(4) + "</p>" +
                                "<p>Total Amount : "+ vr.user_bookings.get(userid).get(i).get(6) + "</p>" +
                                "<p>Pick Up Date : "+ vr.user_bookings.get(userid).get(i).get(3) + "</p>" +
                                "<p>Return Date : "+ vr.user_bookings.get(userid).get(i).get(4) + "</p>" +
                                "<p>Location : "+ vr.carloc.get(Integer.parseInt(vr.user_bookings.get(userid).get(i).get(5))).get(1)+ "</p>" +
                                "<p></br></p>");

                            }
                            out.println("<form method=\"post\" action=\"Gnrt_PDF\" class=\"download\">"+
                            "<button class=\"btn\">Download History</button></for>");
                            out.println("</div>");
                        }
                        
                        else
                        {
                            out.println("<div class=\"container\">\n");
                            out.println("<h1 style=\"text-align:center;\">No Booking yet :(</h1>");
                            out.println("<img src=\"images/BookHistEmpty.svg\" style=\"position:absolute; height:370px; top:125px; left:520px; bottom: 150px\">");
                            out.println("</div>");
                        }
                        
             
                      


                        out.println("        </main>\n" +
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
