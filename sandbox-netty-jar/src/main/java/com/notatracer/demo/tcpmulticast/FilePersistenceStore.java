package com.notatracer.demo.tcpmulticast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FilePersistenceStore implements PersistenceStore {

	private File f = new File("c:\test.out");
	private int counter = 0;

	public void add(Object o) {
		try {
			FileOutputStream fos = new FileOutputStream(f);
			fos.write(("foo" + counter++).getBytes());
			System.out.println("Wrote to file.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found.");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Problem writing.");
		}

	}

}
