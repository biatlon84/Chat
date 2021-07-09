package com.epam.chat.server;

import java.util.ArrayList;

public class Heap {
	public static int cu = 0;
	public static byte last;
	public static ArrayList<SerClThread> pull1 = new ArrayList<SerClThread>();
	public static String ww;
	public static byte[] ser_arch = new byte[1024];

	public static long bytesToLong(final byte[] bytes, final int offset) {
		long result = 0;
		for (int i = offset; i < Long.BYTES + offset; i++) {
			result <<= Long.BYTES;
			result |= (bytes[i] & 0xFF);
		}
		return result;
	}

	public static byte[] longToBytes(long l) {
		byte[] result = new byte[8];
		for (int i = 7; i >= 0; i--) {
			result[i] = (byte) (l & 0xFF);
			l >>= 8;
		}
		return result;
	}
}
