package com.keyit.utils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;

public final class WebUIHandler {
	
	public static void createTempImage(String fileName, Blob blog) {

		InputStream inputStream = null;
		OutputStream outputStream = null;

		try {
			if (blog.length() > 0) {

				inputStream = blog.getBinaryStream();
				outputStream = new FileOutputStream(fileName);

				int readBytes = 0;
				byte[] buffer = new byte[10000];
				while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static boolean stringToBoolean(String str) {

		if ("on".equalsIgnoreCase(str)) {
			return true;
		}

		return false;
	}

}
