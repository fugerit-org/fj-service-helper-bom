package org.fugerit.java.dsb.jvfs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.fugerit.java.core.io.StreamIO;
import org.fugerit.java.core.jvfs.JFile;
import org.fugerit.java.dsb.DataService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Slf4j
/**
 * <p>Implementation of DataService based on a JVFS Folder.</p>
 * 
 * <p>Note: it is possible to use any JVFS implementation with write access.</p>
 */
public class JVFSDataService implements DataService {

	/**
	 * The storeJFolder the base folder to store data
	 * 
	 * @param storeJFolder the folder to store data
	 * @return this folder where data is stored
	 */
 	@Getter
 	@Setter
 	@NonNull
 	private JFile storeJFolder;

 	private JFile toFile( String id ) throws IOException {
 		return this.getStoreJFolder().getChild( id+".jf" );
 	}
 	
	@Override
	public InputStream load(String id) throws IOException {
		JFile file = this.toFile(id);
		log.info( "load - jfile:{} (exists? {}) -> id:{}", id, file.exists(), file.getPath() );
		if ( file.exists() ) {
			return file.getInputStream();
		} else {
			return null;
		}
	}

	@Override
	public String save(InputStream data) throws IOException {
		String id = UUID.randomUUID().toString();
		JFile file = this.toFile(id);
		try ( OutputStream fos = file.getOutputStream() ) {
			StreamIO.pipeStream( data , fos, StreamIO.MODE_CLOSE_BOTH );
		}
		log.info( "save - jfile:{} -> id:{}", file.getPath(), id );
		return id;
	}
	
}
