package com.client.fakedata;

public class FakeData {
	
	private int[] array;
	private int totalCount;
	
	public static FakeData fakeData;
	
	public static FakeData getInstance() {
		
		if(fakeData == null) {
			fakeData = new FakeData(new int[10], 0);
		}
		
		return fakeData;
	}
	
	private FakeData(int[] array, int totalCount) {
		this.array = array;
		this.totalCount = totalCount;
	}
	
	public int[] getArray() {
		return array;
	}
	public void setArray(int[] array) {
		this.array = array;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}
