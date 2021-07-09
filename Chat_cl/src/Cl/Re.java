package Cl;

public class Re extends Thread {
	@Override
	public void run() {
		while ((!store.soccc.flagErr)) {
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (!store.DR && !store.soccc.flagErr) {
				// store.soccc.sendR();
			}
		}
	}
}