import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Authentication Service
 */
public class AuthenticationService  {

    public static final String ADMIN_PAGE = "/admin";
    public static final String SHOPPING_CENTER_PAGE = "/shoppingcenter";

    /**
     * Method to authenticate and redirect accordingly
     * Admin or normal client
     * @param request req
     * @param response res
     * @param username username
     */
    public static void authenticateAndAssignRole(HttpServletRequest request, HttpServletResponse response, String username)
            throws IOException {
        Cookie authenticatedUser = new Cookie("authenticatedUser", null);
        if ("admin".equals(username)) {
            authenticatedUser.setValue(username);
            response.sendRedirect(ADMIN_PAGE);
        } else {
            authenticatedUser.setValue(username);
            response.sendRedirect(SHOPPING_CENTER_PAGE);
        }
    }

    /**
     * Authenticate users using cookies
     * @return userAuthenticated
     * @param request httpRequest
     */
    public static boolean authenticateUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("authenticatedUser".equals(cookie.getName()) && cookie.getValue() != null) {
                return true;
            }
        }

        return false;
    }
}
