package org.fugerit.java.dsb;

import java.io.IOException;
import java.io.InputStream;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * <p>Class wrapping another DataService</p>
 */
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class DataServiceWrapper implements DataService {

	@NonNull private DataService dataService;
	
	/**
	 * <p>Unwrap the inner DataService.</p>
	 * 
	 * @return	the wrapped DataService
	 */
	public DataService unwrap() {
		return this.dataService;
	}
	
	/**
	 * <p>Wrap this DataService around another.</p>
	 * 
	 * @param dataService	the DataService to wrap
	 */
	public void wrap( DataService dataService ) {
		this.dataService = dataService;
	}
	
	@Override
	public InputStream load(String id) throws IOException {
		return this.unwrap().load(id);
	}

	@Override
	public String save(InputStream data) throws IOException {
		return this.unwrap().save(data);
	}

	@Override
	public String save(InputStream data, String resourceName) throws IOException {
		return this.unwrap().save(data, resourceName);
	}

}
