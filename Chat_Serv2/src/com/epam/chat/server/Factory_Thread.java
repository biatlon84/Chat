package com.epam.chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Factory_Thread extends Thread {
	public boolean conEX = false;

	public void cr() {
		try {
			ServerSocket server = new ServerSocket(7728);
			while (!server.isClosed()) {
				Socket client = server.accept();// waiting

				SerClThread threadC = new SerClThread(client);

				Heap.pull1.add(threadC);

				threadC.start();
				System.out.print("Connection accepted.");
				conEX = true;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
