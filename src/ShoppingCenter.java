import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Responsible for prices and products
 */
public class ShoppingCenter extends HttpServlet {

    public static final String IMAGES_ERROR_PNG = "\\images\\error.png";

    /**
     * Method to display all items available for shopping
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean userAuthenticated = AuthenticationService.authenticateUser(request);
        PrintWriter out = response.getWriter();

            if (userAuthenticated) {
                //User is authenticated
                ItemsDAO dao = new ItemsDAO();
                List<ItemBean> listOfItems = null;
                try {
                    listOfItems = dao.getItemsWithDetails();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                printHtmlElement(out, listOfItems);
            } else {
                response.sendRedirect("./home.html");
            }

            /*
                These lines are only for testing purpose
                Delete if lines are useless..
            List<ItemBean> itemBeanList = new ArrayList<>();
            ItemBean itemBean = new ItemBean();
            itemBean.setId(123L);
            itemBean.setImgUrl(IMAGES_ERROR_PNG);
            itemBean.setPrice(100);
            itemBean.setDesc("Something");
            itemBean.setItemName("Something name");
            itemBeanList.add(itemBean);
            itemBean.setId(123L);
            itemBean.setImgUrl(IMAGES_ERROR_PNG);
            itemBean.setPrice(100);
            itemBean.setDesc("Something");
            itemBean.setItemName("Something name");
            itemBeanList.add(itemBean);
            printHtmlElement(out, itemBeanList);
*/
    }


    private void printHtmlElement(PrintWriter out, List<ItemBean> listOfItems) {
        out.println(" " +
                "<html> "+
                "<head>" +
                "<link rel = \"stylesheet\" type = \"text/css\" href = \"css\\ShoppingCenter.css\" ></link>" +
                "<title> ShoppingCenter </title></head>" +
                "<body>" +
                "<div>" +
                "<center>" +
                "<h1> Welcome to the Shopping Center </h1>" +
                "<h3> Select from the below items that you want to add to cart! Happy Shopping!</h3> " +
                "</center>" +
                "</div>");
        out.println("<div><form action = \"./checkoutcart\" method = \"post\"> ");
        out.println("<table>" +
                "<tr>" +
                "<th>Picture</th>" +
                "<th>Id</th>" +
                "<th>Name</th>" +
                "<th>Price</th>" +
                "<th>Description</th>" +
                "</tr>");
        for (ItemBean item : listOfItems) {
            out.println("<tr>" +
                    "<td><img src = \""+item.getImgUrl()+"\" width=\"300\" height=\"300\"></img></td>" +
                    "<td>"+ item.getId() +"</td>" +
                    "<td>"+ item.getItemName() +"</td>" +
                    "<td>"+ item.getPrice() +"</td>" +
                    "<td>"+ item.getDesc() +"</td>" +
                    "<td><input type = \"checkbox\" " +
                                "name = \"itemName\" " +
                                "value = "+item.getId()+ "/> Buy it? </td>" +
                    "</tr>");
        }
        out.println("</table>" +
                "<input type = \"submit\" value = \"Checkout\">" +
                "</form></div>");

        out.println( "</body>" +
                "</html>");
    }
}
