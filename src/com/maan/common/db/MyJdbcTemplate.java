package com.maan.common.db;

import com.maan.common.util.LogUtil;
import com.maan.common.util.ResourceLocator;

import java.sql.SQLException;
import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

/**
 * @author Raja.K
 *
 * Common Login Template
 */
public class MyJdbcTemplate {

    private static final Logger logger = LogUtil.getLogger(MyJdbcTemplate.class);
    protected JdbcTemplate mytemplate;
    private SimpleJdbcTemplate simpleJdbcTemplate;

    public MyJdbcTemplate() {
        try {
            mytemplate = DatabaseDAO.getInstance().gettemplate();
            simpleJdbcTemplate = new SimpleJdbcTemplate(mytemplate);
        } catch (SQLException e) {
            logger.error("Error while getting Spring instance ", e);
        }
    }

    public JdbcTemplate getMytemplate() {
        return mytemplate;
    }

    public void setMytemplate(final JdbcTemplate mytemplate) {
        this.mytemplate = mytemplate;
    }

    public String getQuery(String key){
        String query;
        query=ResourceLocator.getInstance().getDBBundle().getString(key);
        return query;

    }

    public SimpleJdbcTemplate getSimpleJdbcTemplate() {
        return simpleJdbcTemplate;
    }
}