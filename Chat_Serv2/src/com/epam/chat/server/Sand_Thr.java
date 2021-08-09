package com.epam.chat.server;

import java.net.Socket;

public class Sand_Thr extends Thread {
	private Factory_Thread Fac;
	Socket clientDialog;

	public Sand_Thr(Factory_Thread fac) {
		Fac = fac;
		// this.clientDialog = clientDialog;
	}

	@Override
	public void run() {
		int p = 0;
		while (true) {

			if (Fac.conEX && (Heap.pull1.size() != 0)) {
				Heap.pull1.get(p).chek();
				if (!Heap.pull1.get(p).flagErr) {
					Heap.pull1.get(p).sendT("test ArrayList", Heap.last, Heap.pull1.get(p).clientDialog);
				} else {
					Heap.pull1.remove(p);
				}
			}
			try {
				Thread.currentThread().sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (Heap.pull1.size() > p + 1) {
				p++;
			} else {
				p = 0;
			}

		}
	}

}
