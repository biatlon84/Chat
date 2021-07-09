package Cl;

public class IniThr extends Thread {
	public boolean fa = true;

	@Override
	public void run() {
		store.soccc.Socin();
		while (fa) {
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// System.out.print("loop");
			store.soccc.Check();
			if (!store.soccc.flagErr) {
				store.soccc.getT();// checking for coming messages
			} else {
				// System.out.print("error in geting loop\n");
			}
		}
		System.out.print("Bay!");
	}
}
