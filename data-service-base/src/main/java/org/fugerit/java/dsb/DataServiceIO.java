package org.fugerit.java.dsb;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import org.fugerit.java.core.io.StreamIO;

/**
 * <p>I/O utilities for data service.</P>
 */
public class DataServiceIO {

	private DataServiceIO() {}

	/**
	 * <p>Load a resource as a byte array.</p>
	 * 
	 * @param service		the data service
	 * @param id			id of the resource
	 * @return				the resource as a byte array
	 * @throws IOException	if any I/O issue arises
	 */
	public static byte[] loadBytes( DataService service, String id ) throws IOException {
		byte[] data = null;
		try ( InputStream is = service.load( id ) ) {
			if ( is != null ) {
				data = StreamIO.readBytes( is );
			}
		}
		return data;
	}
	
	/**
	 * <p>Load a resource as base64 encoded string.</p>
	 * 
	 * @param service		the data service
	 * @param id			id of the resource
	 * @return				the resource as base64 encoded string
	 * @throws IOException	if any I/O issue arises
	 */
	public static String loadBase64( DataService service, String id ) throws IOException {
		byte[] data = loadBytes(service, id);
		String base64 = null;
		if ( data != null ) {
			base64 = Base64.getEncoder().encodeToString( data );
		}
		return base64;
	}


	/**
	 * <p>Save a resource as base64 encoded string.</p>
	 *
	 * @param service		the data service
	 * @param data			the resource to save
	 * @param resourceName	the resource name to be saved
	 * @return				the ID of the saved resource
	 * @throws IOException	if any I/O issue arises
	 */
	public static String saveBytes( DataService service, byte[] data, String resourceName ) throws IOException {
		String id = null;
		try ( InputStream is = new ByteArrayInputStream( data ) ) {
			id = service.save(is, resourceName);
		}
		return id;
	}

	/**
	 * <p>Save a resource as base64 encoded string.</p>
	 * 
	 * @param service		the data service
	 * @param data			the resource to save
	 * @return				the ID of the saved resource
	 * @throws IOException	if any I/O issue arises
	 */
	public static String saveBytes( DataService service, byte[] data ) throws IOException {
		String id = null;
		try ( InputStream is = new ByteArrayInputStream( data ) ) {
			id = service.save(is);
		}
		return id;
	}
	
	/**
	 * <p>Save a resource as base64 encoded string.</p>
	 * 
	 * @param service		the data service
	 * @param base64		the resource to save as base64 encoded string
	 * @return				the ID of the saved resource
	 * @throws IOException	if any I/O issue arises
	 */
	public static String saveBase64( DataService service, String base64 ) throws IOException {
		return saveBytes(service, Base64.getDecoder().decode( base64 ) );
	}

	/**
	 * <p>Save a resource as base64 encoded string.</p>
	 *
	 * @param service		the data service
	 * @param base64		the resource to save as base64 encoded string
	 * @param resourceName	the resource name to be saved
	 * @return				the ID of the saved resource
	 * @throws IOException	if any I/O issue arises
	 */
	public static String saveBase64( DataService service, String base64, String resourceName ) throws IOException {
		return saveBytes(service, Base64.getDecoder().decode( base64 ), resourceName);
	}
	
	/**
	 * <p>generate a new ID in the format of ${yyyyMMddHHmmss}_${UUID}</p>
	 * 
	 * @return a new ID
	 */
	public static String generateId() {
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyyMMddHHmmss" );
		return sdf.format( new Date() )+"_"+UUID.randomUUID().toString();
	}
	
}
