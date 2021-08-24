package edu.escuelaing.arem;

import java.util.concurrent.ConcurrentHashMap;

public class CacheImpl{
	private final ConcurrentHashMap<String,String> memoryStock = new ConcurrentHashMap<>();
	private String idData;

	public void saveData(String data, String stockID, String timeSeries) {
		idData = stockID + timeSeries ;
		memoryStock.put(idData, data);
	}

	public String getData(String stockID, String timeSeries) {
		String resultData = "";
		idData = stockID + timeSeries;
	    if(memoryStock.containsKey(idData)){
	    	resultData = memoryStock.get(idData);
	    }
	    return resultData;
	}
}
