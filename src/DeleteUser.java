import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Admin page for administration purpose
 */
public class DeleteUser extends HttpServlet {
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
                        "<h3>Please select the below links for other options </h3>" +
                        "<h4> <a href=\"feedback\" > Feedback</a></h4>" +
                        "<h4> <a href=\"admin\" > Admin - Home </a></h4>" +
                        "<h4> <a href=\"reports\"> Reports </h4>"
        );

        boolean userAuthenticated = AuthenticationService.authenticateUser(request);
            if (userAuthenticated) {
                //User is authenticated
                try {
                   if (deleteUser(request, response)){
                     out.println("<h1>Successful</h1>");
                   } else {
                       out.println("<h1>UnSuccessful!!</h1>");
                   }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                response.sendRedirect("./home.html");
            }

        out.println("</body>" +
                "</html>");
    }

    private boolean deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
      return ItemsDAO.deleteUsers(request.getParameterValues("Delete"));

    }
}
