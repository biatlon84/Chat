package com.epam.chat.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class SerClThread extends Thread {

	public Socket clientDialog;
	// BufferedReader inn;
	// BufferedWriter outn;
	OutputStream outSClient;
	DataInputStream inSClient;
	public boolean flagErr = false;
	public boolean sended = false;
	int indx;

	public SerClThread(Socket client) {
		this.clientDialog = client;
	}

	@Override
	public void run() {
		try {
			String ww = "";
			outSClient = new DataOutputStream(new BufferedOutputStream(clientDialog.getOutputStream()));
			InputStream in = clientDialog.getInputStream();
			inSClient = new DataInputStream(new BufferedInputStream(in));
			while (!clientDialog.isClosed()) {
				ww = "";
				byte fl = 0;
				// long l = 0;
				byte[] b = new byte[1024];
				byte[] q = new byte[8];
				// byte[] k = new byte[1011];
				// Date dat;
				int num = inSClient.read(b);// waiting

				fl = b[0];
				b[0] = 0;
				// ---------------------Date Time
				if (fl != 26) {
					for (int i = 0; i < 8; i++) {
						q[i] = b[i];
					}
					SimpleDateFormat fo = new SimpleDateFormat("HH:mm:ss EEEE");
					Date da = new Date(Heap.bytesToLong(q, 0) + 700000);// local time
					if (Heap.bytesToLong(q, 0) != 0)
						System.out.println(fo.format(da));
				}
				// ------------------------
				if (fl == 73) {
					Heap.cu = 0;
					for (int i = 0; i < 1000; i++) {
						Heap.ser_arch[i] = b[i + 8];
					}
				}
				if (fl == 5) {
					for (int i = 8; i < 1024; i++) {
						if (b[i] != 0)
							ww = ww + (char) b[i];
					}
					/*
					 * Heap.ww = ww; if (ww.equalsIgnoreCase("quit")) {
					 * System.out.println("Main Server initiate exiting..."); inSClient.close();
					 * outSClient.close(); clientDialog.close(); break; }
					 */
					System.out.println("TXT RES>" + ww);
				}
				Heap.last = fl;
			}
			flagErr = false;
		} catch (SocketException e) {
			/// e.printStackTrace();
			flagErr = true;
			System.out.println("SocketExceptionRUN");
		} catch (IOException e) {
			System.out.println("IOExceptionRUN");
			;
			flagErr = true;
		} finally {
			try {
				inSClient.close();
				outSClient.close();
				clientDialog.close();
			} catch (IOException e) {
				System.out.println("Ошибка закрытия ");
			}

			System.out.println("Client disconnected");
		}

	}

	// --------------------------------------------- Sending
	public void sendT(String ss, byte fl, Socket clientDialog) {

		byte[] b = new byte[1024];
		byte[] q = new byte[8];
		byte[] k = new byte[1011];
		try {
			OutputStream datO = new DataOutputStream(new BufferedOutputStream(clientDialog.getOutputStream()));
			q = Heap.longToBytes(System.currentTimeMillis());
			for (int i = 0; i < 8; i++) {
				b[i] = q[i];
			}
			// -------------------------------
			// System.out.println(fl);
			if (fl == 73) {
				Heap.cu = 0;
				for (int i = 0; i < 1000; i++) {
					b[i + 8] = Heap.ser_arch[i];
					// System.out.println(Heap.ser_arch[i]);
				}
				// fl = 73;
			}
			/// ---------------
			if (fl == 50) {
				k = ss.getBytes();
				for (int i = 0; i < k.length; i++) {
					b[i + 8] = k[i];
				}
			}
			/// -------------------
			b[0] = fl;
			datO.write(b);
			datO.flush();
			// System.out.println(Heap.pull1.indexOf(this));
			sended = true;
		} catch (IOException e) {
			System.out.println("IOExceptionST");
			try {
				inSClient.close();
				outSClient.close();
				clientDialog.close();
			} catch (IOException ee) {
				System.out.println("Ошибка закрытия ");
			}
			flagErr = true;
		} catch (NullPointerException e) {
			System.out.println("NullPointerEST");
			flagErr = true;
		}

	}

	public void chek() {

		try {
			if (!clientDialog.isClosed()) {
				flagErr = false;
			}
		} catch (NullPointerException e) {
			flagErr = true;
		}

	}

}
