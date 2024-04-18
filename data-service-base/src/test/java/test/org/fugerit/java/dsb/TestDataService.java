package test.org.fugerit.java.dsb;

import org.fugerit.java.dsb.DataService;
import org.fugerit.java.dsb.DataServiceIO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;


class TestDataService {

    @Test
    void testBase() throws IOException {
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
        Assertions.assertEquals( testId, ds.save( null, null ) );
    }

}
