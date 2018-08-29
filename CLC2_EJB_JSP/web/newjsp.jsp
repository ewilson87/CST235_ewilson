<%--
    Document   : newjsp
    Created on : Aug 29, 2018, 8:46:11 PM
    Author     : ewwil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>This is a JSP page!</h1>
        <h1>It will interact with an EJB!</h1>
        <form>
            <jsp:useBean id="EJB" scope="session" class="CLC2.NewSessionBean" />

            <jsp:setProperty name="EJB" property="name" value="Evan"/>
            <h2>
                This JSP page instantiates the NewSessionBean.java class, sets the name variable to Evan, and then calls the getName() method
                to say hello
                <br></br>
                <jsp:getProperty name="EJB" property="name"/>
            </h2>

        </form>
    </body>
</html>
