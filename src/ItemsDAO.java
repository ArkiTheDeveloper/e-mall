import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for getting all the items present along with their prices
 *
 * NOTE: TABLE SCHEMA
 *  CREATE TABLE ITEMS (
 *          id long,
 *          itemName varchar(50),
 *          price number(50),
 *          desc varchar(100),
 *          imageurl varchar(100)
 *  );
 *
 */
public class ItemsDAO {

    public static final String CHECKED_ITEM_LIST = "SELECT * FROM ITEMS WHERE id = ?";
    private static final String SQL_QUERY_FOR_ITEMS = "SELECT * FROM ITEMS";
    private static final String ID = "id";
    private static final String ITEM_NAME = "itemName";
    private static final String PRICE = "price";
    private static final String DESC = "desc";
    private static final String IMAGE_URL = "imageurl";

    public List<ItemBean> getItemsWithDetails() throws SQLException, ClassNotFoundException {

        Connection dbConnection = JDBCUtils.getConnection();
        if (dbConnection == null) {
            throw new SQLException("Connection to DB failed ");
        }

        return getItemsFromDatabase(dbConnection);
    }

    /**
     * Method to get all the items from the database
     * @param dbConnection dbConnection
     * @return List<ItemBean> itemList
     * @throws SQLException
     */
    private List<ItemBean> getItemsFromDatabase(Connection dbConnection) throws SQLException {
        List<ItemBean> itemsListWithDetails = new ArrayList<>();
        PreparedStatement statement = dbConnection.prepareStatement(SQL_QUERY_FOR_ITEMS);
        ResultSet itemsRetrieved = statement.executeQuery();

        /**
         * Fetching data from database result and
         * setting it to a item object
         * adding the item object to list
         */

        while (itemsRetrieved.next()) {
            ItemBean item = new ItemBean();
            item.setId(itemsRetrieved.getLong(ID));
            item.setItemName(itemsRetrieved.getString(ITEM_NAME));
            item.setPrice(itemsRetrieved.getLong(PRICE));
            item.setDesc(itemsRetrieved.getString(DESC));
            item.setImgUrl(itemsRetrieved.getString(IMAGE_URL));
            itemsListWithDetails.add(item);
        }
        return itemsListWithDetails;
    }

    /**
     * Method to retrieve checkout items
     * @param selectedItemIds selectedItemdIds
     * @return List<ItemBean> checkItemsList
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public static List<ItemBean> getSelectedItems(String[] selectedItemIds) throws SQLException, ClassNotFoundException {
        List<ItemBean> selectedItems = new ArrayList<>();
        Connection dbConnection = JDBCUtils.getConnection();
        if (dbConnection == null) {
            throw new SQLException("Connection to DB failed ");
        }
        return getSelectedItemsFromDb(dbConnection, selectedItemIds);
    }

    private static List<ItemBean> getSelectedItemsFromDb(Connection dbConnection, String[] selectedItemIds) throws SQLException {
        List<ItemBean> itemsListWithDetails = new ArrayList<>();
        PreparedStatement statement = dbConnection.prepareStatement(CHECKED_ITEM_LIST);
        ResultSet itemsRetrieved;
        for (String id : selectedItemIds) {
            statement.setLong(1, Long.parseLong(id.replace("/","")));
            itemsRetrieved = statement.executeQuery();
            ItemBean itembean = new ItemBean();
            itembean.setId(itemsRetrieved.getLong(ID));
            itembean.setItemName(itemsRetrieved.getString(ITEM_NAME));
            itembean.setImgUrl(itemsRetrieved.getString(IMAGE_URL));
            itembean.setPrice(itemsRetrieved.getLong(PRICE));
            itembean.setDesc(itemsRetrieved.getString(PRICE));
            itemsListWithDetails.add(itembean);
        }
        return itemsListWithDetails;
    }

    public static boolean deleteUsers(String[] deletes) throws SQLException, ClassNotFoundException {
        Connection dbConnection = JDBCUtils.getConnection();
        if (dbConnection == null) {
            throw new SQLException("Connection to DB failed ");
        }
        boolean result;
        PreparedStatement statement = dbConnection.prepareStatement("DELETE FROM register1810 WHERE E_mail_1 = ?");
        PreparedStatement statementt = dbConnection.prepareStatement("UPDATE USERSTATUS SET STATUS = 'INACTIVE' WHERE USERNAME = ? ");
        for (String delete : deletes) {
            statement.setString(1, delete.replace("/",""));
            statementt.setString(1, delete.replace("/",""));
            result = statement.execute() && statementt.execute();
            if (!result)
                return false;
        }

        return true;
    }
}
