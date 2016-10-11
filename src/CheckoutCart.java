import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Responsible for prices and products
 */
public class CheckoutCart extends HttpServlet {

    /**
     * Method to display the shopping cart
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        List<ItemBean> itemBeanList;
        boolean userAuthenticated = AuthenticationService.authenticateUser(request);
            if (userAuthenticated) {
                //User is authenticated
                String selectedItemIds[] = request.getParameterValues("itemName");
                try {
                     itemBeanList = ItemsDAO.getSelectedItems(selectedItemIds);
                     printHtmlElement(out, itemBeanList);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
            //User is not authenticated
            response.sendRedirect("./home.html");
        }
        /* These below lines are only for testing purpose.
        Delete if useless..
        List<ItemBean> list = new ArrayList<>();
        ItemBean itemBean = new ItemBean();
        String selectedItemIds[] = request.getParameterValues("itemName");
        itemBean.setId(Long.parseLong(selectedItemIds[0].replace("/", "")));
        itemBean.setPrice(100);
        list.add(itemBean);
        printHtmlElement(out, list);*/
    }

    /**
     * Method to print all the selected items
     * @param out printWriter
     * @param listOfItems selectedItems
     */
    private void printHtmlElement(PrintWriter out, List<ItemBean> listOfItems) {
        out.println(" " +
                "<html> "+
                "<head> <title> ShoppingCenter </title></head>" +
                "<body>" +
                "<div>" +
                "<center>" +
                "<h1><b> Below are your selected items </b></h1>" +
                "<h3> Review your cart and click buy to proceed.</h3> " +
                "</center>" +
                "</div>");
        out.println("<div><form action = \"./completeshopping\" method = \"post\"> ");
        out.println("<table>" +
                "<tr>" +
                "<th>Picture</th>" +
                "<th>Id</th>" +
                "<th>Name</th>" +
                "<th>Price</th>" +
                "<th>Description</th>" +
                "</tr>");
        long sumOfItems = 0;
        for (ItemBean item : listOfItems) {
            out.println("<tr>" +
                    "<td><img src = \""+item.getImgUrl()+"\" width=\"300\" height=\"300\"></img></td>" +
                    "<td>"+ item.getId() +"</td>" +
                    "<td>"+ item.getItemName() +"</td>" +
                    "<td>"+ item.getPrice() +"</td>" +
                    "<td>"+ item.getDesc() +"</td>" +
                    "</tr>");
            sumOfItems += item.getPrice();
        }
        out.println("</table>" +
                "<input type = \"submit\" value = \"Proceed\">" +
                "</form></div>");
        out.println("<h1>Total value of cart is :\n"+sumOfItems+"</h1>");
        out.println("</body>" +
                "</html>");
    }
}
