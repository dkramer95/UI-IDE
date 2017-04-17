package client;

import lib.Application;

public class Main {

	public static void main(String[] args) {
		Application app = new Application(new JUIElementFactory());
		app.start();
	}
}
