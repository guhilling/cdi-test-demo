package de.hilling.cdi.sampleapp.testsupport;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.hilling.junit.cdi.jee.jpa.DatabaseCleaner;

/**
 * All implementations of {@link DatabaseCleaner} will automatically be called before each new test.
 * So the database doesn't need to be restarted between test cases.
 *
 * <p>
 * Brute force implementation.
 * </p>
 */
@SuppressWarnings("unused")
public class H2DatabaseCleaner implements DatabaseCleaner {

    public static final String USER_TABLE_IDENTIFIER = "TABLE";

    public void run(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, "PUBLIC", null, new String[]{USER_TABLE_IDENTIFIER});
        while (tables.next()) {
            String tableName = tables.getString("TABLE_NAME");
            if(!tableName.equals("SEQUENCE")) {
                connection.prepareStatement("delete from " + tableName).execute();
            }
        }
        connection.commit();
    }

}
