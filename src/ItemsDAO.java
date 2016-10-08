import alreadywritten.JDBCUtils;

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
 *          desc varchar(100)
 *  );
 *
 */
public class ItemsDAO {

    private static final String SQL_QUERY_FOR_ITEMS = "SELECT * FROM ITEMS";
    private static final String ID = "id";
    private static final String ITEM_NAME = "itemName";
    private static final String PRICE = "price";
    private static final String DESC = "desc";

    public List<ItemBean> getItemsWithDetails() throws SQLException, ClassNotFoundException {

        Connection dbConnection = JDBCUtils.getConnection();
        if (dbConnection == null) {
            throw new SQLException("Connection to DB failed ");
        }

        return getItemsFromDatabase(dbConnection);
    }

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
            itemsListWithDetails.add(item);
        }
        return itemsListWithDetails;
    }
}
