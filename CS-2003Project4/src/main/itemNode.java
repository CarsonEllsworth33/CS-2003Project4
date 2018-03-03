package main;

import trees.KeyedItem;

public class itemNode extends KeyedItem<Integer> {
	
	private Integer keyNum;
	
	public itemNode(Integer key) {
		super(key);
		// TODO Auto-generated constructor stub
	}
	
	public void setKeyNum(Integer val) {
		keyNum = val;
	}
}
