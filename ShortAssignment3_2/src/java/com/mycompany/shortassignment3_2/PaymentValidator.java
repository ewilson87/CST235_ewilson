/**
 * Program: CST-235 Short Assignment 3
 * File: PaymentValidator.java
 * Summary: Servlet to read user entered billing information
 * Author: Evan Wilson
 * Date: August 29th, 2018
 * */
package com.mycompany.shortassignment3_2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PaymentValidator")
public class PaymentValidator extends HttpServlet {

    private String fName;
    private String lName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String cardNumber;
    private String expMonth;
    private String expYear;
    private String securityCode;
    private String whatFailed = "";
    private String cardValid;
    private boolean allValid;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet PaymentValidator</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet PaymentValidator at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
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

        //response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher view = request.getRequestDispatcher("/index.xhtml?faces-redirect=true");

        view.forward(request, response);
//        processRequest(request, response);
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
        //create PrintWriter object for writing a response in HTML
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        //sets PaymentValidator.java variables based on what the user entered in the response from index.xhtml
        setfName(request.getParameter("fname"));
        setlName(request.getParameter("lname"));
        setAddress(request.getParameter("address"));
        setCity(request.getParameter("city"));
        setState(request.getParameter("state"));
        setZip(request.getParameter("zip"));
        setCardNumber(request.getParameter("cardNumber"));
        setExpMonth(request.getParameter("expMonth"));
        setExpYear(request.getParameter("expYear"));
        setSecurityCode(request.getParameter("securityCode"));

        //runs method to check all fields for proper format, if wrong it tells the user what part was wrong.
        if (!checkAllFields()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PaymentValidator</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> The following errors occured: <br></br>" + getWhatFailed() + "</h1>");
            //resets whatFailed to empty before user can try again
            setWhatFailed("");
            out.println("<form action=\"/ShortAssignment3_2/PaymentValidator\" method=\"get\">");

            out.println("<input type=\"submit\" value=\"Back\"/>\n"
                    + "\n"
                    + "        </form>");
            out.println("</body>");
            out.println("</html>");
            out.println("<html><body>");
            //else if all entries were in proper format, checks against the billing info file
        } else {
            //calls method to open inventory file and compare to user entry
            validateToFile();

            //writes to the browser the response in HTML
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PaymentValidator</title>");
            out.println("</head>");
            out.println("<body>");
            //just to write where the servlet is coming from
            out.println("<h1>Servlet PaymentValidator at " + request.getContextPath() + "</h1>");
            //writes to the user information about whether the billing info was valid
            out.println("<h1>" + getCardValid() + "</h1>");
            out.println("<form action=\"/ShortAssignment3_2/PaymentValidator\" method=\"get\">");

            out.println("<input type=\"submit\" value=\"Back\"/>\n"
                    + "\n"
                    + "        </form>");
            out.println("</body>");
            out.println("</html>");
            out.println("<html><body>");
        }

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

    // method to check all fields
    private boolean checkAllFields() {

        boolean Valid = true;

        // Setup regex to include numbers, letters, and hyphens
        String regularCharacterPattern = "[^a-zA-Z0-9-]";
        String numberPattern = "[^0-9]";

        // Check First Name
        if (fName.equals("")) {
            Valid = false;
            setWhatFailed("First name cannot be blank. ");
        } else if (Pattern.compile(regularCharacterPattern).matcher(fName).find()) {
            Valid = false;
            setWhatFailed("Last name can only contain letters and hyphens. ");
        }

        // Check Last Name
        if (lName.equals("")) {
            Valid = false;
            setWhatFailed("Last name cannot be blank");
        } else if (Pattern.compile(regularCharacterPattern).matcher(lName).find()) {
            setWhatFailed("Last name can only contain letters and hyphens. ");
            Valid = false;
        }

        // Check Address
        if (address.equals("")) {
            Valid = false;
            setWhatFailed("Address cannot be blank. ");
        }

        // Check City
        if (city.equals("")) {
            Valid = false;
            setWhatFailed("City cannot be blank. ");
        } else if (Pattern.compile(regularCharacterPattern).matcher(city).find()) {
            Valid = false;
            setWhatFailed("City can only contain letters and hyphens. ");
        }

        // Check State
        if (state.equals("")) {
            Valid = false;
            setWhatFailed("State cannot be blank. ");
        } else if (state.length() > 2 || Pattern.compile(regularCharacterPattern).matcher(state).find()) {
            Valid = false;
            setWhatFailed("State must be two letters. ");
        }

        // Check Zip
        if (zip.equals("")) {
            Valid = false;
            setWhatFailed("Zip code cannot be blank. ");
        } else if (zip.length() != 5) {
            Valid = false;
            setWhatFailed("Zip code must be 5 digits. ");
        } else if (Pattern.compile(numberPattern).matcher(zip).find()) {
            Valid = false;
            setWhatFailed("Zip code can only contain numbers. ");
        }

        // Check card
        if (cardNumber.equals("")) {
            Valid = false;
            setWhatFailed("Card number cannot be blank. ");
        } else if (cardNumber.length() != 16) {
            Valid = false;
            setWhatFailed("Card number must be 16 digits. ");
        } else if (Pattern.compile(numberPattern).matcher(cardNumber).find()) {
            Valid = false;
            setWhatFailed("Card number can only contain numbers. ");
        }
        // Check Expiration Date
        if (expMonth.length() != 2 || expYear.length() != 2) {
            Valid = false;
            setWhatFailed("Expiration dates must be 2 digits");
        }
        //check Security Code
        if (securityCode.length() != 3) {
            Valid = false;
            setWhatFailed("Security Code must be 3 digits");
        }

        allValid = Valid;

        return Valid;
    }

    //Opens billing info csv file, then compares each entry to what the user entered for  validation
    public void validateToFile() {
        // if all fields are valid check for credit cards match
        if (allValid) {
            String billingInfo = "\\Users\\ewwil\\Documents\\SCHOOL\\GCU\\CST-235\\Week 3\\billing info.csv";
            String line = "";
            String delimiter = ",";

            //try and catch to open a billing info file
            try (BufferedReader br = new BufferedReader(new FileReader(billingInfo))) {
                boolean valid = false;
                //skips header line
                br.readLine();
                //reads through file until the end
                while ((line = br.readLine()) != null) {

                    // use comma as delimiter check if card info is in file by adding each line to a temporary array and comparing each
                    //to the user entered values
                    String[] card = line.split(delimiter);
                    if (card[0].toLowerCase().equals(fName.toLowerCase())
                            && card[1].toLowerCase().equals(lName.toLowerCase())
                            && card[2].toLowerCase().equals(address.toLowerCase())
                            && card[3].toLowerCase().equals(city.toLowerCase())
                            && card[4].toLowerCase().equals(state.toLowerCase())
                            && card[5].toLowerCase().equals(zip.toLowerCase())
                            && card[6].toLowerCase().equals(cardNumber.toLowerCase())
                            && card[7].toLowerCase().equals(expMonth.toLowerCase())
                            && card[8].toLowerCase().equals(expYear.toLowerCase())
                            && card[9].toLowerCase().equals(securityCode.toLowerCase())) {
                        valid = true;
                    }
                }

                if (valid) {
                    setCardValid("Billing information entered was enterred correctly");
                } else {
                    setCardValid("Billing information entered does not match current records.");
                }

                //catch exception to let the user know there was an error on the server side reading the file
            } catch (IOException e) {
                setCardValid("THERE WAS AN ERROR READING THE CURRENT BILLING INFORMATION FILE ON THER SERVER.");
            }

        }
    }

    //getters and setters
    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getWhatFailed() {
        return whatFailed;
    }

    public void setWhatFailed(String whatFailed) {
        //concatanates itself every time it's set, adding an html break tag
        this.whatFailed = this.whatFailed + "<br></br>" + whatFailed;
    }

    public String getCardValid() {
        return cardValid;
    }

    public void setCardValid(String cardValid) {
        this.cardValid = cardValid;
    }

    public boolean getAllValid() {
        return allValid;
    }

    public void setAllValid(boolean allValid) {
        this.allValid = allValid;
    }
}
