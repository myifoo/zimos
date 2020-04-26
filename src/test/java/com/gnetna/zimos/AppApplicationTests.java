package com.gnetna.zimos;

import org.junit.jupiter.api.Test;

import java.io.File;

class AppApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void fileTest() {
		File dir = new File("./images");
		File[] files = dir.listFiles();

		for (int i = 0; i < files.length; i++) {
			String name = files[i].getName();
			System.out.println(name.substring(0, name.lastIndexOf('.')));
		}
	}

}
