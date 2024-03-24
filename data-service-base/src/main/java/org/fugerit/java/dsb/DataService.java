package org.fugerit.java.dsb;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>Basic interface for saving/loading data.</p>
 */
public interface DataService {

	/**
	 * <p>Retrieve saved data based on ID.</p>
	 * 
	 * @param id	the id of the data to be loaded
	 * @return		the data (null if the ID does not exists)
	 * @throws IOException	if any I/O issue arises
	 */
	InputStream load( String id ) throws IOException;
	
	/**
	 * <p>Save data and assign an ID to it.</p>
	 * 
	 * @param data				the data to be saved
	 * @return					the ID assigned to saved data
	 * @throws IOException	if any I/O issue arises
	 */
	String save( InputStream data ) throws IOException;

	/**
	 * <p>Save data and assign an ID to it.</p>
	 *
	 * <p>Default implementation of this method will invoke <code>this.save( data )</code></p>
	 *
	 * @param data				the data to be saved
	 * @param resourceName		resource name to be saved
	 * @return					the ID assigned to saved data
	 * @throws IOException	if any I/O issue arises
	 */
	default String save( InputStream data, String resourceName ) throws IOException {
		return this.save( data );
	}
	
}
