import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

/**
 * Responsible for prices and products
 */
public class ShoppingCenter extends HttpServlet {

    /**
     * Method to display the shopping cart
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        PrintWriter out = response.getWriter();

        for (Cookie cookie : cookies) {
            if ("authenticatedUser".equals(cookie.getName()) && cookie.getValue() != null) {
                //User is authenticated
                ItemsDAO dao = new ItemsDAO();
                List<ItemBean> listOfItems = null;
                try {
                    listOfItems = dao.getItemsWithDetails();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                printHtmlElement(out, listOfItems);
            }
        }
        printHtmlElement(out, Collections.emptyList());
        response.sendRedirect("./testing.html");
    }

    private void printHtmlElement(PrintWriter out, List<ItemBean> listOfItems) {
        out.println(" " +
                "<html> "+
                "<head> <title> ShoppingCenter </title></head>" +
                "<body>" +
                "<div>" +
                "<center>" +
                "<h1> Welcome to the Shopping Center </h1>" +
                "<h3> Select from the below items that you want to add to cart! Happy Shopping!</h3> " +
                "</center>" +
                "</div>");
        out.println("<div>");
        out.println("<table>" +
                "<tr>" +
                "<th>Id</th>" +
                "<th>Name</th>" +
                "<th>Price</th>" +
                "<th>Description</th>" +
                "</tr>");
        for (ItemBean item : listOfItems) {
            out.println("<tr>" +
                    "<td>"+ item.getId() +"</td>" +
                    "<td>"+ item.getItemName() +"</td>" +
                    "<td>"+ item.getPrice() +"</td>" +
                    "<td>"+ item.getDesc() +"</td>" +
                    "</tr>");
        }
        out.println("</table>" +
                "</div>");

        out.println( "</body>" +
                "</html>");
    }
}
