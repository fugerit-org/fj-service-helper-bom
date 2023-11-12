package test.org.fugerit.java.dsb.file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.dsb.DataServiceIO;
import org.fugerit.java.dsb.file.FileDataServiceUUID;
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestFileDataServiceUUID {

	private static final File STORE_FOLDER = new File( "target" );
	
	private static final String TEST_DATA = "test";
	
	private static final String TEST_DATA_BASE64 = SafeFunction.get( () -> Base64.getEncoder().encodeToString( TEST_DATA.getBytes() ) );
	
	private InputStream newData() {
		return new ByteArrayInputStream( TEST_DATA.getBytes() );
	}
	
	@Test
	public void testFileDataService() throws IOException {
		FileDataServiceUUID service = new FileDataServiceUUID();
		service.setStoreFolder(STORE_FOLDER);
		log.info( "data service : {}", service );
		try ( InputStream data = this.newData() ) {
			String id = DataServiceIO.saveBase64( service , TEST_DATA_BASE64 );
			String base64 = DataServiceIO.loadBase64( service , id );
			Assert.assertEquals( TEST_DATA_BASE64 , base64 );
		}
	}

	
	@Test
	public void testCreate() throws IOException {
		Assert.assertThrows( NullPointerException.class , () -> new FileDataServiceUUID( null ) );
		FileDataServiceUUID service = new FileDataServiceUUID( STORE_FOLDER );
		Assert.assertThrows( NullPointerException.class , () -> service.setStoreFolder( null ) );
		log.info( "service : {}", service );
		Assert.assertEquals( STORE_FOLDER , service.getStoreFolder() );
		Assert.assertNull( DataServiceIO.loadBase64( service, "not-exists" ) );
	}
	
}
