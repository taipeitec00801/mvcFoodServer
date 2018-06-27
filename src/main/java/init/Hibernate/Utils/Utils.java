package init.Hibernate.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;

public class Utils {
	public static Blob fileToBlob(InputStream is, long size) throws IOException, SQLException {
		byte[] b = new byte[(int) size];
		SerialBlob sb = null;
		is.read(b);
		sb = new SerialBlob(b);
		return sb;
	}
	public static Blob fileToBlob(String imageFileName) 
			               throws IOException, SQLException {
		File imageFile = new File(imageFileName);
		long size = imageFile.length();
		byte[] b = new byte[(int) size];
		SerialBlob sb = null;
		try (
			FileInputStream fis = new FileInputStream(imageFile);
		) {
			fis.read(b);
			sb = new SerialBlob(b);
		}
		return sb;
	}

	public static Clob fileToClob(String textFileName) 
			                  throws IOException,	SQLException {
		InputStreamReader isr = new InputStreamReader(
				      new FileInputStream(textFileName), "UTF-8");
		char[] c = new char[8192];
		StringBuffer buf = new StringBuffer();
		int len = 0;
		while ((len = isr.read(c)) != -1) {
			buf.append(new String(c, 0, len));
		}
		char[] ca = buf.toString().toCharArray();
		Clob clob = new SerialClob(ca);
		isr.close();
		return clob;
	}
}
