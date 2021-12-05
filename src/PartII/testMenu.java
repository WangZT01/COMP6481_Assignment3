package PartII;


/**
 * 40171434 40186367
 * @author Zitao Wang & Jiaming Han
 *
 */

public class testMenu {
	public static void main(String args[]) {
		CleverSIDC x = new CleverSIDC();
		x.SetSIDCThreshold(666);
		x.add(40171434, "Zitao Wang");
		x.add(99999999, "Zexin Peng");
		x.add(30000001, "Jiaming Han");
		x.allKeys();
		x.getValues(40171434);
		long r = x.generate();
		x.remove(r);
		x.add(r, "Renjie Peng");
		x.allKeys();
		x.prevKey(40171434);
		x.nextKey(40171434);
		x.rangeKey(60000000, 60000050);
		x.rangeKey(20000000, 70000000);
		x.SetSIDCThreshold(400000);
		x.allKeys();
	}
}
