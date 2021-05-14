
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;  
import java.util.Date;  
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author blraj
 */
public class Vroom {
    
    int    memID;
    int    bookID;
    int    billID;
    private static ArrayList<Integer> list_cust_ids = new ArrayList<Integer>();
    private static ArrayList<Integer> list_book_ids = new ArrayList<Integer>();
    private static ArrayList<Integer> list_bill_ids = new ArrayList<Integer>();
    private static ArrayList<Integer> list_carid_notavail = new ArrayList<Integer>();
    public HashMap<Integer,ArrayList<String>> caravail=new HashMap<Integer,ArrayList<String>>();  
    public HashMap<Integer,ArrayList<String>> car_details=new HashMap<Integer,ArrayList<String>>();  
    public HashMap<Integer,ArrayList<String>> carloc = new HashMap<Integer,ArrayList<String>>();
    public HashMap<Integer,ArrayList<ArrayList<String>>> user_bookings=new HashMap<Integer,ArrayList<ArrayList<String>>>();
    public HashMap<Integer,ArrayList<String>> allbookings=new HashMap<Integer,ArrayList<String>>();
   
    public Vroom() {
        memIdGenr();
        bookIdGenr();
        billIdGenr();
        poplcars();
        poploc();
    }

    private void memIdGenr()
    {
        try{
            /* TODO output your page here. You may use following sample code. */
            Connection con=null;
            Statement stm=null;
            ResultSet rs=null;
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/crs","root","");
            PreparedStatement id_fetcher;
            String query=("select *from customer");
            stm=con.createStatement();
            rs=stm.executeQuery(query);
            if(rs.next())
            {
                list_cust_ids.add(Integer.parseInt(rs.getString("UserID")));
            }
            
            this.memID = (int)(Math.random() * (2047300 - 2047100 + 1) + 2047100);
            while(list_cust_ids.contains(memID))
            {
                this.memID = (int)(Math.random() * (2047300 - 2047100 + 1) + 2047100);
            }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
     private void bookIdGenr()
    {
        try{
            /* TODO output your page here. You may use following sample code. */
            Connection con=null;
            Statement stm=null;
            ResultSet rs=null;
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/crs","root","");
            PreparedStatement id_fetcher;
            String query=("select *from booking");
            stm=con.createStatement();
            rs=stm.executeQuery(query);
            if(rs.next())
            {
                list_book_ids.add(Integer.parseInt(rs.getString("BookingID")));
            }
            
            this.bookID = (int)(Math.random() * (000000000 - 999999999 + 1) + 999999999);
            while(list_book_ids.contains(bookID))
            {
                this.bookID = (int)(Math.random() * (000000000 - 999999999 + 1) + 999999999);
            }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
     
     
      private void billIdGenr()
    {
        try{
            /* TODO output your page here. You may use following sample code. */
            Connection con=null;
            Statement stm=null;
            ResultSet rs=null;
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost/crs","root","");
            PreparedStatement id_fetcher;
            String query=("select *from billing");
            stm=con.createStatement();
            rs=stm.executeQuery(query);
            if(rs.next())
            {
                list_bill_ids.add(Integer.parseInt(rs.getString("BillID")));
            }
            
            this.billID = (int)(Math.random() * (000000000 - 999999999 + 1) + 999999999);
            while(list_bill_ids.contains(billID))
            {
                this.billID = (int)(Math.random() * (000000000 - 999999999 + 1) + 999999999);
            }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    private void poplcars()
    {
        
         Connection con=null;
         Statement stm=null;
         ResultSet rs=null;
         ResultSet rs2=null;  
         int avail=1;
         try {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost/crs","root","");
                String query="select *from caravailability where AvailFlag='"+avail+"'";
                stm=con.createStatement();
                rs=stm.executeQuery(query);
                while(rs.next())
                {
                    String query2="select *from car where CarID='"+Integer.parseInt(rs.getString("CarID"))+"'";
                    stm=con.createStatement();
                    rs2=stm.executeQuery(query2);
                    if(rs2.next())
                    {
                        ArrayList<String> car = new ArrayList<String>();
                        car.add(rs2.getString("CarID"));
                        car.add(rs2.getString("LocID"));
                        car.add(rs2.getString("CarCategory"));
                        car.add(rs2.getString("RegNumber"));
                        car.add(rs2.getString("ModelName"));
                        car.add(rs2.getString("ModelYear"));
                        car.add(rs2.getString("PersonIncludable"));
                        car.add(rs2.getString("CostPerDay"));
                        
                        if(!car_details.containsKey(Integer.parseInt(rs2.getString("CarID"))))
                        {
                            car_details.put(Integer.parseInt(rs2.getString("CarID")), car);
                        }
                         
                        insert_caravail(Integer.parseInt(rs2.getString("CarID")),car);
                    }
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
     private void poploc()
    {
        
         Connection con=null;
         Statement stm=null;
         ResultSet rs=null; 
         int id=1;
         try {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost/crs","root","");
                String query="select *from location";
                stm=con.createStatement();
                rs=stm.executeQuery(query);
                while(rs.next())
                {
                        ArrayList<String> loc = new ArrayList<String>();
                        loc.add(rs.getString("LocID"));
                        loc.add(rs.getString("Street"));
                        loc.add(rs.getString("City"));
                        loc.add(rs.getString("State"));
                        loc.add(rs.getString("Zipcode"));
                         
                        insert_carloc(Integer.parseInt(rs.getString("LocID")),loc);
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
     
    private void insert_carloc(int id, ArrayList<String> loc)
    {
   
            carloc.put(id, loc);
        
    }
    
    private void delete_carloc(int id)
    {
        if(carloc.containsKey(id))
        {
            carloc.remove(id);
        }
    }
    
    private void insert_caravail(int id, ArrayList<String> car)
    {
   
            caravail.put(id, car);
        
    }
    
    private void delete_caravail(int id)
    {
        if(caravail.containsKey(id))
        {
            caravail.remove(id);
        }
    }
    
    public void deleteall_caravil()
    {
        caravail.clear();
    }
    
    public void pop_availcar(String frmdt, String todt)
    {
         //this.deleteall_caravil();
         String loc_frmdt="2021-03-05"; 
         String loc_todt="2021-03-06";
         Connection con=null;
         Statement stm=null;
         ResultSet booking=null;
         ResultSet rs2=null;  
         int avail=1;
         try {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost/crs","root","");
                String query="select *from booking";
                stm=con.createStatement();
                booking=stm.executeQuery(query);
                while(booking.next())
                {
                    loc_frmdt = booking.getString("PickDt");
                    loc_todt = booking.getString("ReturnDt");
                    int carid = Integer.parseInt(booking.getString("CarID"));
                    
                    SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd");  
                    Date book_frmdt = frmt.parse(loc_frmdt);
                    Date book_todt = frmt.parse(loc_todt);
                    Date desr_frmdt = frmt.parse(frmdt);
                    Date desr_todt = frmt.parse(todt);
                    
                    if( ((desr_frmdt.compareTo(book_frmdt) == 0)) || ((desr_frmdt.compareTo(book_todt) == 0))  || ((desr_todt.compareTo(book_frmdt) == 0)) || ((desr_todt.compareTo(book_todt) == 0))  )
                    {
                        insert_car_notavail(carid);
                        this.delete_caravail(carid);
                    }
                    else if( (desr_frmdt.after(book_frmdt) && desr_frmdt.before(book_todt)) || (desr_todt.after(book_frmdt) && desr_todt.before(book_todt)) )
                    {
                        insert_car_notavail(carid);  
                        this.delete_caravail(carid);
                    }
                    else if( (book_frmdt.after(desr_frmdt) && book_frmdt.before(desr_todt)) || (book_todt.after(desr_frmdt) && book_todt.before(desr_todt)) )
                    {
                        insert_car_notavail(carid);  
                        this.delete_caravail(carid);
                    }
                    else
                    {
                        String query2="select *from car where CarID='"+Integer.parseInt(booking.getString("CarID"))+"'";
                        stm=con.createStatement();
                        rs2=stm.executeQuery(query2);
                        if(rs2.next())
                        {
                            ArrayList<String> car = new ArrayList<String>();
                            car.add(rs2.getString("CarID"));
                            car.add(rs2.getString("LocID"));
                            car.add(rs2.getString("CarCategory"));
                            car.add(rs2.getString("RegNumber"));
                            car.add(rs2.getString("ModelName"));
                            car.add(rs2.getString("ModelYear"));
                            car.add(rs2.getString("PersonIncludable"));
                            car.add(rs2.getString("CostPerDay"));

                            insert_caravail(Integer.parseInt(rs2.getString("CarID")),car);
                        }  
                    }
               }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
            Logger.getLogger(Vroom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void insert_car_notavail(int id)
    {
        if(!list_carid_notavail.contains(id))
        {
            list_carid_notavail.add(id);
        } 
    }
    
    private void delete_car_notavail(int id)
    {
        if(list_carid_notavail.contains(id))
        {
           list_carid_notavail.remove(id);
        }
    }
    
    public void pop_usr_bkng(int userid)
    {    
        String result="yes";
         ArrayList<ArrayList<String>> all_bkng = new ArrayList<ArrayList<String>>();
         Connection con=null;
         Statement stm=null;
         ResultSet booking=null;
         ResultSet billing=null;
         try {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost/crs","root","");
                String query="select *from booking where CustomerID='"+userid+"'";
                stm=con.createStatement();
                booking=stm.executeQuery(query);
                while(booking.next())
                {
                        ArrayList<String> ech_bk = new ArrayList<String>();
                        ech_bk.add(booking.getString("BookingID"));
                        ech_bk.add(booking.getString("CustomerID"));
                        ech_bk.add(booking.getString("CarID"));
                        ech_bk.add(booking.getString("PickDt"));
                        ech_bk.add(booking.getString("ReturnDt"));
                        ech_bk.add(booking.getString("PickLocID"));
                        ech_bk.add(booking.getString("Amount"));
                        ech_bk.add(booking.getString("Status"));
                         
                        String query2="select *from billing where BookingID='"+Integer.parseInt(booking.getString("BookingID"))+"'";
                        stm=con.createStatement();
                        billing=stm.executeQuery(query2);
                        if(billing.next())
                        {
                           ech_bk.add(billing.getString("BillID"));
                        }
                        all_bkng.add(ech_bk);
                }
                
                if(!user_bookings.containsKey(userid))
                {
                    user_bookings.put(userid, all_bkng);
                }
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    
    public void popallbkngs(String frmdt, String todt)
    {    
         String loc_frmdt="2021-03-05"; 
         String loc_todt="2021-03-06";
         Connection con=null;
         Statement stm=null;
         ResultSet booking=null;
         ResultSet billing=null;
         int cnt =0;
         try {
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection("jdbc:mysql://localhost/crs","root","");
                String query="select *from booking";
                stm=con.createStatement();
                booking=stm.executeQuery(query);
                while(booking.next())
                {
                        ArrayList<String> ech_bk = new ArrayList<String>();
                        
                        loc_frmdt = booking.getString("PickDt");
                        loc_todt = booking.getString("ReturnDt");
                        ech_bk.add(booking.getString("BookingID"));
                        ech_bk.add(booking.getString("CustomerID"));
                        ech_bk.add(booking.getString("CarID"));
                        ech_bk.add(booking.getString("PickDt"));
                        ech_bk.add(booking.getString("ReturnDt"));
                        ech_bk.add(booking.getString("PickLocID"));
                        ech_bk.add(booking.getString("Amount"));
                        ech_bk.add(booking.getString("Status"));
                         
                        SimpleDateFormat frmt = new SimpleDateFormat("yyyy-MM-dd");  
                        Date book_frmdt = frmt.parse(loc_frmdt);
                        Date book_todt = frmt.parse(loc_todt);
                        Date desr_frmdt = frmt.parse(frmdt);
                        Date desr_todt = frmt.parse(todt);
                    
                        if( ((desr_frmdt.compareTo(book_frmdt) == 0)) || ((desr_frmdt.compareTo(book_todt) == 0))  || ((desr_todt.compareTo(book_frmdt) == 0)) || ((desr_todt.compareTo(book_todt) == 0))  )
                        {
                            allbookings.put(cnt, ech_bk);
                        }
                        else if( (desr_frmdt.after(book_frmdt) && desr_frmdt.before(book_todt)) || (desr_todt.after(book_frmdt) && desr_todt.before(book_todt)) )
                        {
                            allbookings.put(cnt, ech_bk);
                        }
                        else if( (book_frmdt.after(desr_frmdt) && book_frmdt.before(desr_todt)) || (book_todt.after(desr_frmdt) && book_todt.before(desr_todt)) )
                        {
                           allbookings.put(cnt, ech_bk);
                        }
                        else
                        {
                             allbookings.put(cnt, ech_bk);
                        }
                        cnt++;
                }
                
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
            Logger.getLogger(Vroom.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
