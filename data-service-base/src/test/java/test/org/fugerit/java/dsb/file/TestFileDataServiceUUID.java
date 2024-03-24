package test.org.fugerit.java.dsb.file;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.dsb.DataServiceIO;
import org.fugerit.java.dsb.DataServiceWrapper;
import org.fugerit.java.dsb.file.FileDataServiceUUID;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class TestFileDataServiceUUID {

	private static final File STORE_FOLDER = new File( "target" );
	
	private static final String TEST_DATA = "test";
	
	private static final String TEST_DATA_BASE64 = SafeFunction.get( () -> Base64.getEncoder().encodeToString( TEST_DATA.getBytes() ) );
	
	private InputStream newData() {
		return new ByteArrayInputStream( TEST_DATA.getBytes() );
	}
	
	@Test
	void testFileDataService() throws IOException {
		FileDataServiceUUID service = new FileDataServiceUUID();
		service.setStoreFolder(STORE_FOLDER);
		DataServiceWrapper wrapper = new DataServiceWrapper( service );
		log.info( "data service : {} : {}", wrapper, wrapper.unwrap() );
		try ( InputStream data = this.newData() ) {
			String id = DataServiceIO.saveBase64( wrapper , TEST_DATA_BASE64 );
			String base64 = DataServiceIO.loadBase64( wrapper , id );
			Assertions.assertEquals( TEST_DATA_BASE64 , base64 );
		}
		try ( InputStream data = this.newData() ) {
			String id = DataServiceIO.saveBase64( wrapper , TEST_DATA_BASE64, "res_name.txt" );
			String base64 = DataServiceIO.loadBase64( wrapper , id );
			Assertions.assertEquals( TEST_DATA_BASE64 , base64 );
		}
		wrapper.wrap( service );
		Assertions.assertThrows( NullPointerException.class , () -> new DataServiceWrapper( null ) );
	}

	
	@Test
	void testCreate() throws IOException {
		Assertions.assertThrows( NullPointerException.class , () -> new FileDataServiceUUID( null ) );
		FileDataServiceUUID service = new FileDataServiceUUID( STORE_FOLDER );
		Assertions.assertThrows( NullPointerException.class , () -> service.setStoreFolder( null ) );
		log.info( "service : {}", service );
		Assertions.assertEquals( STORE_FOLDER , service.getStoreFolder() );
		Assertions.assertNull( DataServiceIO.loadBase64( service, "not-exists" ) );
		Assertions.assertNotNull( new DataServiceWrapper() );
	}
	
}
