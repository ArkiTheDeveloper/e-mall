import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Admin page for administration purpose
 */
public class AdminPage extends HttpServlet {
    /**
     * Method to open the admin page
     * @param request httpRequest
     * @param response httpResponse
     * @throws ServletException servletException
     * @throws IOException IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        out.println(
                "<html> "+
                "<head> <title> ShoppingCenter </title></head>" +
                "<body>" +
                        "<h1> This page is for deleting the requested users </h1>" +
                        "<h3>Please select the below links for other options </h3>" +
                        "<h4> <a href=\"feedback\" > Feedback</a></h4>" +
                        "<h4> <a href=\"reports\"> Reports </h4>"
        );

        boolean userAuthenticated = AuthenticationService.authenticateUser(request);
            if (userAuthenticated) {
                //User is authenticated
                /**
                 * Method to display the accounts for de-registration
                 */
                try {
                    deRegisterUser(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                response.sendRedirect("./home.html");
            }

        out.println("</body>" +
                "</html>");
    }

    private void deRegisterUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ClassNotFoundException {
        PrintWriter out = response.getWriter();
        LoginDAO dao = new LoginDAO();
        printListOfDeRegisteringUsers(out, dao.getListOfDeRegisteringUsers());

    }

    private void printListOfDeRegisteringUsers(PrintWriter out, List<String> listOfDeRegisteringUsers) {
        out.println("<h1> Please check the box to remove the users </h1>" +
                "<table><form action = \"deleteuser\" >" +
                "<th>Username</th>");
        for (String user : listOfDeRegisteringUsers) {
            out.println("<td>"+user+"</td>");
            out.println("<input type = \"checkbox\" name = \" delete\" value = \" user\" ");
        }
        out.println("<input type = \"submit\" value = \"Delete\" " +
                "</form></table>");
    }

}
