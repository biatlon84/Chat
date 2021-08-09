package Cl;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Drawing extends Canvas {
	Rectangle half = new Rectangle(90, 90, 90, 90);

	@Override
	public void paint(Graphics g) {
		int XS, YS;
		int i = 0;
		g.setColor(Color.white);
		store.cu = 0;
		store.rPos();
		XS = store.xx;
		YS = store.yy;
		for (i = 0; i < 250; i++) {
			store.rPos();
			if ((store.xx == 0) && (store.yy == 0)) {
				i = 300;
				store.cu = store.cu - 8;
				store.rPos();
			}
			// System.out.println("xx" + store.xx + " yy" + store.yy);
			g.drawLine(store.xx, store.yy, XS, YS);
			XS = store.xx;
			YS = store.yy;

		}
		if (i == 300) {
			store.cu = 0;
			for (i = 0; i < 1008; i++) {
				store.pos[i] = 0;
			}
		}
	}

	public void ref(Graphics g) {
		int XS, YS;
		int i = 0;
		g.setColor(Color.white);
		store.cu = 0;
		store.rPos();
		XS = store.xx;
		YS = store.yy;
		for (i = 0; i < 250; i++) {
			store.rPos();
			if ((store.xx == 0) && (store.yy == 0)) {
				i = 300;
				store.cu = store.cu - 8;
				store.rPos();
			}
			// System.out.println("xx" + store.xx + " yy" + store.yy);
			g.drawLine(store.xx, store.yy, XS, YS);
			XS = store.xx;
			YS = store.yy;
		}
		if (i == 300) {
			store.cu = 0;
			for (i = 0; i < 1008; i++) {
				store.pos[i] = 0;
			}
		}
	}
}
