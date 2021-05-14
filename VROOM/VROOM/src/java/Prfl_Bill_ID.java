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
@WebServlet(urlPatterns = {"/Prfl_Payment"})
public class Prfl_Bill_ID extends HttpServlet {

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
           String cid= (String) session.getAttribute("cid");
           String uid= (String) session.getAttribute("userid");
           Vroom vr = new Vroom();
           int carid = Integer.parseInt(cid);
           int userid = Integer.parseInt(uid);
           int locid = Integer.parseInt(vr.caravail.get(carid).get(1))-2000;
           
           String FirstName = (String) session.getAttribute("firstname");  
           String LastName = (String) session.getAttribute("lastname");
           
           String fromdate = (String) session.getAttribute("fromdate");  
           String todate = (String) session.getAttribute("todate");
           
           int todt = Integer.parseInt(todate.substring(8,10));
           int frmdt = Integer.parseInt(fromdate.substring(8,10));
           int total =(1+todt)-frmdt;
           total = total * Integer.parseInt(vr.caravail.get(carid).get(7));
           
           

           if(session != null)
           {
               
               
            Connection con=null;
            Statement stm=null;
            ResultSet rs=null;
            int bookid = vr.bookID;
            int billid = vr.billID;
              
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost/crs","root","");
                PreparedStatement bill;
                PreparedStatement book;
                book=con.prepareStatement("insert into booking values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
                book.setInt(1, bookid);
                book.setInt(2, userid);
                book.setInt(3, carid);
                book.setString(4, fromdate);
                book.setString(5, todate);
                book.setInt(6, Integer.parseInt(vr.caravail.get(carid).get(1)));
                book.setInt(7, Integer.parseInt(vr.caravail.get(carid).get(1)));
                book.setInt(8, total);
                book.setString(9, "Approved");
                book.executeUpdate(); 
               
                
                bill=con.prepareStatement("insert into billing values(?, ?, ?, ?, ?)");
                bill.setInt(1, billid);
                bill.setInt(2, bookid);
                bill.setString(3, fromdate);
                bill.setString(4, "Approved");
                bill.setInt(5, total);
                bill.executeUpdate(); 
         
                
            } catch (ClassNotFoundException ex) {out.println("Invalid1");
                Logger.getLogger(Prfl_Bill_ID.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Prfl_Bill_ID.class.getName()).log(Level.SEVERE, null, ex);
            }
            
             request.setAttribute("LastName", LastName);
             request.setAttribute("FirstName", FirstName);
             
             request.setAttribute("Amount", Integer.toString(total*100));
             
             request.setAttribute("bookid", bookid);
             request.setAttribute("billid", billid);
             
             request.getRequestDispatcher("./Prfl_Bill_Book_ID.jsp").forward(request, response);
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


