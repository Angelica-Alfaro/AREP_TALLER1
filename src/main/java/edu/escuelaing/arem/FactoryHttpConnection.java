package edu.escuelaing.arem;

/**
 * Factory method that allows you to create objects according to the time series.
 * 
 * @author Ang�lica
 */
public class FactoryHttpConnection {
	
	/**
	 * Gets the time series based on its type.
	 * @param type - Daily, weekly, intraday(5min), monthly.
	 * @return time series object. 
	 */
	public static StockHttpConnection getStockTimeSeries(String type) {
		
		StockHttpConnection timeSeries;
		
		if (type.equals("Time Series (5min)")) {
			timeSeries = new TimeSeriesIntraday();
		}
		
		else if (type.equals("Time Series (Daily)")) {
			timeSeries = new TimeSeriesDaily();
		}
		
		else if (type.equals("Weekly Time Series")) {
			timeSeries = new TimeSeriesWeekly();
		}
		
		else if (type.equals("Monthly Time Series")){
			timeSeries = new TimeSeriesMonthly();		
		}
		else {
			timeSeries = new TimeSeriesIntraday();
		}
		
		return timeSeries;
	}

}
