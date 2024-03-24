package test.org.fugerit.java.dsb.jvfs;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.core.jvfs.JFile;
import org.fugerit.java.core.jvfs.file.RealJMount;
import org.fugerit.java.dsb.DataServiceIO;
import org.fugerit.java.dsb.jvfs.JVFSDataService;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class TestJVFSDataService {

	private static final JFile STORE_FOLDER = SafeFunction.get( () -> RealJMount.createJVFS( new File( "target" ) ).getRoot() );
	
	private static final String TEST_DATA = "test";
	
	private static final String TEST_DATA_BASE64 = SafeFunction.get( () -> Base64.getEncoder().encodeToString( TEST_DATA.getBytes() ) );
	
	private InputStream newData() {
		return new ByteArrayInputStream( TEST_DATA.getBytes() );
	}
	
	@Test
	void testFileDataService() throws IOException {
		JVFSDataService service = new JVFSDataService();
		service.setStoreJFolder(STORE_FOLDER);
		log.info( "data service : {}", service );
		try ( InputStream data = this.newData() ) {
			String id = DataServiceIO.saveBase64( service , TEST_DATA_BASE64 );
			String base64 = DataServiceIO.loadBase64( service , id );
			Assertions.assertEquals( TEST_DATA_BASE64 , base64 );
		}
	}

	
	@Test
	void testCreate() throws IOException {
		Assertions.assertThrows( NullPointerException.class , () -> new JVFSDataService( null ) );
		JVFSDataService service = new JVFSDataService( STORE_FOLDER );
		Assertions.assertThrows( NullPointerException.class , () -> service.setStoreJFolder( null ) );
		log.info( "service : {}", service );
		Assertions.assertEquals( STORE_FOLDER , service.getStoreJFolder() );
		Assertions.assertNull( DataServiceIO.loadBase64( service, "not-exists" ) );
	}
	
}
