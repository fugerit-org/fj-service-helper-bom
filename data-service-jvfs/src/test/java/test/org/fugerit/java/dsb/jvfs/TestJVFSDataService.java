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
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestJVFSDataService {

	private static final JFile STORE_FOLDER = SafeFunction.get( () -> RealJMount.createJVFS( new File( "target" ) ).getRoot() );
	
	private static final String TEST_DATA = "test";
	
	private static final String TEST_DATA_BASE64 = SafeFunction.get( () -> Base64.getEncoder().encodeToString( TEST_DATA.getBytes() ) );
	
	private InputStream newData() {
		return new ByteArrayInputStream( TEST_DATA.getBytes() );
	}
	
	@Test
	public void testFileDataService() throws IOException {
		JVFSDataService service = new JVFSDataService();
		service.setStoreJFolder(STORE_FOLDER);
		log.info( "data service : {}", service );
		try ( InputStream data = this.newData() ) {
			String id = DataServiceIO.saveBase64( service , TEST_DATA_BASE64 );
			String base64 = DataServiceIO.loadBase64( service , id );
			Assert.assertEquals( TEST_DATA_BASE64 , base64 );
		}
	}

	
	@Test
	public void testCreate() throws IOException {
		Assert.assertThrows( NullPointerException.class , () -> new JVFSDataService( null ) );
		JVFSDataService service = new JVFSDataService( STORE_FOLDER );
		Assert.assertThrows( NullPointerException.class , () -> service.setStoreJFolder( null ) );
		log.info( "service : {}", service );
		Assert.assertEquals( STORE_FOLDER , service.getStoreJFolder() );
		Assert.assertNull( DataServiceIO.loadBase64( service, "not-exists" ) );
	}
	
}
