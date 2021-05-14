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

/**
 *
 * @author blraj
 */
@WebServlet(urlPatterns = {"/Register"})
public class Register extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            Connection con=null;
            Statement stm=null;
            ResultSet rs=null;
            String fname = request.getParameter("firstname");
            String lname = request.getParameter("lastname");
            String email = request.getParameter("email");
            String phnumber = request.getParameter("phonenumber");
            String dob = request.getParameter("dob");
            String adrnumber = request.getParameter("adrnumber");
            String dlnumber = request.getParameter("dlnumber");
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String zipcode = request.getParameter("zipcode");
            String password1="Sandeep501002";
            int id1=2047300;
            String password = fname+zipcode;
            Vroom person = new Vroom();
            int id = person.memID;
             /* TODO output your page here. You may use following sample code. */
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost/crs","root","");
                PreparedStatement login;
                PreparedStatement reg;
                reg=con.prepareStatement("insert into customer values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
              
                reg.setInt(1, id);
                reg.setString(2, fname);
                reg.setString(3, lname);
                reg.setString(4, email);
                reg.setString(5, phnumber);
                reg.setString(6, dob);
                reg.setString(7, dlnumber);
                reg.setString(8, adrnumber);
                reg.setString(9, street);
                reg.setString(10, city);
                reg.setString(11, state);
                reg.setString(12, zipcode);
                reg.executeUpdate(); 
                String altmsg = "Registration Successful!\\nPlease make note of your login credentials\\nUsername :  "+id+"\\nPassword  : "+password;
                
                login=con.prepareStatement("insert into logincredentials values(?, ?)");
                login.setInt(1, id);
                login.setString(2, password);
                login.executeUpdate(); 
         
                response.setContentType("text/html");  
                out.println("<script type=\"text/javascript\">");  
                out.println("alert('" + altmsg +"');");  
                out.println("window.location.href = \"signin-signup.html\";");
                out.println("</script>"); 
            } catch (ClassNotFoundException ex) {out.println("Invalid1");
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
            
                String altmsg = "Registration Successful!\\nPlease make note of your login credentials\\nUsername :  "+id1+"\\nPassword  : "+password1;
                out.println("<script type=\"text/javascript\">");  
                out.println("alert('" + altmsg +"');");  
                out.println("window.location.href = \"signin-signup.html\";");
                out.println("</script>"); 
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
