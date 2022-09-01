package com.maan.common.db;
/**
 * @author  Raja.K
 *
 * Common Login Template
 */
import com.maan.common.util.LogUtil;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseDAO {

    private static final Logger logger = LogUtil.getLogger(DatabaseDAO.class);
    private static DatabaseDAO mymanager = new DatabaseDAO();
    private transient JdbcTemplate template;
    private transient DataSource dataSource;

    private DatabaseDAO() {
        try {
            final InitialContext cxt = new InitialContext();
            dataSource = (DataSource) cxt.lookup("java:/comp/env/jdbc/RIBROKING");
            template = new JdbcTemplate(dataSource);
        } catch (Exception e) {
            logger.error("Error while creating DB instance ",e);
        }
    }

    public static DatabaseDAO getInstance() {
        return mymanager;
    }

    public Connection getDBConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public JdbcTemplate gettemplate() throws SQLException {
        return template;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
