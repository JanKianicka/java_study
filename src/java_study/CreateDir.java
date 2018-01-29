package java_study;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreateDir {

	public static void main(String args[]) {
		// String dirname = "src/java_study/testDir";
		String dirname = "C:/tmp/testDir";
		File d = new File(dirname);

		// Create directory now.
		if (!d.isDirectory()) {
			d.mkdirs();
			d.setWritable(true, false);
		}
		
		try {
			generateFiles(dirname);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		listDir(dirname);
	}

	private static void listDir(String path) {
		File file = null;
		String[] paths;

		try {
			// create new file object
			file = new File(path);

			// array of files and directory
			paths = file.list();

			// for each name in the path array
			for (String path_loc : paths) {
				// prints filename and directory name
				System.out.println(path_loc);
			}
		} catch (Exception e) {
			// if any error occurs
			e.printStackTrace();
		}
	}
	
	private static void generateFiles(String path) throws IOException {
		String[] files = {path + "/test1.txt",
				path + "/test2.txt",
				path + "/test3.txt"};
		for(String _file:files) {
			FileOutputStream out = new FileOutputStream(_file);
			System.out.println(_file);
			out.close();
			
		}
	}

}
