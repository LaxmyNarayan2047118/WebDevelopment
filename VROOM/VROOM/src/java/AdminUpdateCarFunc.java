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
@WebServlet(urlPatterns = {"/AdminUpdateCarFunc"})
public class AdminUpdateCarFunc extends HttpServlet {

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
         Vroom vr = new Vroom();
         if(session != null)
         {
             Connection con=null;
             Statement stm=null;
             ResultSet rs=null;
             String FirstName = (String) session.getAttribute("firstname"); 
             String LastName = (String) session.getAttribute("lastname");
             
             String carid = request.getParameter("carid");
             String carlocid = request.getParameter("locid");
             String cartype = request.getParameter("cartype");
             String carregno = request.getParameter("regno");
             String carmodel = request.getParameter("model");
             String caryear = request.getParameter("year");
             String carprsninc = request.getParameter("prsn");
             String carcost = request.getParameter("cost");
             
             
             try {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost/crs","root","");
                PreparedStatement carreg;
                carreg=con.prepareStatement("insert into car values(?, ?, ?, ?, ?, ?, ?, ?)");
              
                carreg.setInt(1, Integer.parseInt(carid));
                carreg.setInt(2, Integer.parseInt(carlocid));
                carreg.setString(3, cartype);
                carreg.setString(4, carregno);
                carreg.setString(5, carmodel);
                carreg.setInt(6, Integer.parseInt(caryear));
                carreg.setInt(7, Integer.parseInt(carprsninc));
                carreg.setInt(8, Integer.parseInt(carcost));
                carreg.executeUpdate();  
                
                String altmsg = "Car Insertion Successful!";

                out.println("<script type=\"text/javascript\">");  
                out.println("alert('" + altmsg +"');");  
                out.println("window.location.href = \"./AdminUpsertCars\";");
                out.println("</script>"); 
            } catch (ClassNotFoundException ex) {out.println("Invalid1");
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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
