package com.epam.chat.server;

public class Main {
	public static void main(String args[]) {

		Factory_Thread fac = new Factory_Thread();
		Sand_Thr s = new Sand_Thr(fac);

		fac.start();
		s.start();
		fac.cr();

		System.out.print("while");
	}
}
