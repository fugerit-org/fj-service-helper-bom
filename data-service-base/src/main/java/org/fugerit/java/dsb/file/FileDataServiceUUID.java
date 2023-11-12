package org.fugerit.java.dsb.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.fugerit.java.core.io.StreamIO;
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
 * <p>Implementation of DataService based on a single folder.</p>
 * 
 * <p>Note: this implementation is just as a POC for test purposes.</p>
 */
public class FileDataServiceUUID implements DataService {

 	@Getter
 	@Setter
 	@NonNull
 	private File storeFolder;

 	private File toFile( String id ) {
 		return new File( this.getStoreFolder(), id+".data" );
 	}
 	
	@Override
	public InputStream load(String id) throws IOException {
		File file = this.toFile(id);
		log.info( "load - file:{} -> id:{}", id, file.getCanonicalPath() );
		if ( file.exists() ) {
			return new FileInputStream(file);
		} else {
			return null;
		}
	}

	@Override
	public String save(InputStream data) throws IOException {
		String id = UUID.randomUUID().toString();
		File file = this.toFile(id);
		try ( FileOutputStream fos = new FileOutputStream(file) ) {
			StreamIO.pipeStream( data , fos, StreamIO.MODE_CLOSE_BOTH );
		}
		log.info( "save - file:{} -> id:{}", file.getCanonicalPath(), id );
		return id;
	}
	
}
