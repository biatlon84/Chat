package Cl;

public class store {
	public static int cu = 0;
	public static Soc soccc;
	public static Drawing canv;
	// public static Graphics c;
	public static byte[] pos = new byte[1024];
	public static short xx;
	public static short yy;
	public static boolean over = false;
	public static boolean DR = false;
	// public static byte[][] mas;
	// parameter passing

	public static boolean wrPos(short xx, short yy) {
		if (cu < 1009) {
			store.pos[cu] = (byte) (xx);
			xx >>= 8;
			store.pos[cu + 1] = (byte) (xx);
//--------------------------------------------
			store.pos[cu + 2] = (byte) (yy);
			yy >>= 8;
			store.pos[cu + 3] = (byte) (yy);
			over = false;
			cu = cu + 4;
		} else {
			over = true;

		}
		return !over;
	}

	public static void rPos() {
		xx = (short) (store.pos[cu + 1] & 0xFF);
		xx <<= 8;
		xx |= store.pos[cu] & 0xFF;
		yy = (short) (store.pos[cu + 3] & 0xFF);
		yy <<= 8;
		yy |= store.pos[cu + 2] & 0xFF;

		cu = cu + 4;
	}
}
