package edu.escuelaing.arem;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache with queried information.
 * 
 * @author Angélica
 */
public class CacheImpl{
	private final ConcurrentHashMap<String,String> memoryStock = new ConcurrentHashMap<>();
	private String idData;
	
	/**
	 * Save queried data from external API.
	 * @param data - Information consulted in the external API.
	 * @param stockId - Identifier of the stock queried.
	 * @param timeSeries - Daily, weekly, intraday(5min), monthly.
	 */
	public void saveData(String data, String stockID, String timeSeries) {
		idData = stockID + timeSeries ;
		memoryStock.put(idData, data);
	}
	
	/**
	 * Get frequently queried data from external API.
	 * @param stockId - Identifier of the stock queried.
	 * @param timeSeries - Daily, weekly, intraday, monthly.
	 * @return cached data.
	 */
	public String getData(String stockID, String timeSeries) {
		String resultData = "";
		idData = stockID + timeSeries;
	    if(memoryStock.containsKey(idData)){
	    	resultData = memoryStock.get(idData);
	    }
	    return resultData;
	}
}
