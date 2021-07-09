package Cl;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class Frame1 {
	JFrame frame = new JFrame("My Drawing");
	Color bl = new Color(2, 0, 1);
	Drawing canvas = new Drawing();

	MosL ml = new MosL();

	public Frame1() {
		store.canv = canvas;
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		canvas.setSize(800, 500);
		canvas.setBackground(bl);
		frame.add(canvas);
		canvas.addMouseListener(ml);
		canvas.addMouseMotionListener(ml);
		frame.pack();

	}

	public void ca() {

	}

	public void Vi() {
		frame.setVisible(true);
	}

	public class MosL extends MouseAdapter {
		int sx = 0;
		int sy = 0;
		int cuW = 44;
		// store.cu = 0;

		boolean onDrag = false;

		@Override
		public void mouseDragged(MouseEvent e) {
			Canvas comp = (Canvas) e.getSource();
			Graphics g = comp.getGraphics();
			// if (e.getButton() == MouseEvent.BUTTON1)

			if (cuW > 10) {
				if (store.wrPos((short) e.getX(), (short) e.getY())) {

					int x = e.getX();
					int y = e.getY();
					g.setColor(Color.white);
					g.drawLine(sx, sy, x, y);
					g.drawLine(sx + 1, sy, x + 1, y);
					g.drawLine(sx - 1, sy, x - 1, y);
					g.drawLine(sx, sy + 1, x, y + 1);
					g.drawLine(sx, sy - 1, x, y - 1);

					sx = e.getX();
					sy = e.getY();
				}
				cuW = 0;
			}
			cuW++;

			/*
			 * for (int i = 0; i < 600; i++) { System.out.print(" " + store.pos[i]); if (i %
			 * 40 == 0) { System.out.print("" + "\n"); } }
			 */
		}

		@Override
		public void mousePressed(MouseEvent e) {
			for (int i = 0; i < 1000; i++) {
				store.pos[i] = 0;
			}
			store.cu = 0;
			store.DR = true;
			store.wrPos((short) e.getX(), (short) e.getY());
			sx = e.getX();
			sy = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			cuW = 0;
			store.cu = 0;
			store.DR = false;
			if (!store.soccc.flagErr) { // Checking flag in object
				store.soccc.sendT("hi", false);
				System.out.println("send");
			}
			store.cu = 0;
		}
	}

}
