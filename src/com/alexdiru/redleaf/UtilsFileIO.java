package com.alexdiru.redleaf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public abstract class UtilsFileIO {

	public static ArrayList<String> readAllLines(String filePath, boolean isInternal) {
		BufferedReader br;

		try {
			br = new BufferedReader(new InputStreamReader(isInternal ? Gdx.files.internal(filePath).read() : Gdx.files.local(filePath).read()));

			ArrayList<String> lines = new ArrayList<String>();
			String line;

			while ((line = br.readLine()) != null)
				lines.add(line);

			br.close();

			return lines;
		} catch (Exception ex) {
			return null;
		}
	}

	public static ArrayList<String> readAllLines(String filePath) {
		return readAllLines(filePath, true);
	}

	public static byte[] getByteStream(String filePath) {
		try {
			InputStream input = Gdx.files.internal(filePath).read();

			byte[] stream = new byte[input.available()];
			input.read(stream);
			input.close();

			return stream;
		} catch (IOException ex) {
			return null;
		}
	}
	
	public static String getFileType(String filename) {
		String type = "";
		
		for (int i = filename.length() - 1; i >= 0; i--)
			if (filename.charAt(i) == '.')
				break;
			else
				type += filename.charAt(i);
		
		return new StringBuffer(type).reverse().toString();
	}

	public static void writeAllLines(String filename, ArrayList<String> lines) {
		FileHandle handle = Gdx.files.local(filename);
		
		String line = "";
		
		for (int i = 0; i < lines.size(); i++) 
			line += lines.get(i) + "\n";
		handle.writeString(line, false);
	}
}
