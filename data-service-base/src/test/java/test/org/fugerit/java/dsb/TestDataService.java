package test.org.fugerit.java.dsb;

import org.fugerit.java.dsb.DataService;
import org.fugerit.java.dsb.DataServiceIO;
import org.junit.Assert;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.io.InputStream;

public class TestDataService {

    @Test
    public void testBase() throws IOException {
        String testId = DataServiceIO.generateId();
        DataService ds = new DataService() {
            @Override
            public InputStream load(String id) throws IOException {
                return null;
            }
            @Override
            public String save(InputStream data) throws IOException {
                return testId;
            }

        };
        Assert.assertEquals( testId, ds.save( null, null ) );
    }

}
