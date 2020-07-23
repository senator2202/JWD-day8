package test.kharitonov.day6.model.connect;

import by.kharitonov.day6.model.connect.SqlConnector;
import by.kharitonov.day6.model.exception.DaoException;
import com.mysql.cj.conf.HostInfo;
import com.mysql.cj.jdbc.ConnectionImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.Statement;

public class SqlConnectorTest {
    @InjectMocks
    private SqlConnector connector = new SqlConnector();
    @Mock
    private Connection mockConnection;
    @Mock
    private Statement mockStatement;

    @BeforeMethod
    public void setUp() throws DaoException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMockDBConnection() throws Exception {
    }
}