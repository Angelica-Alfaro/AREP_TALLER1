package edu.escuelaing.arem;

public class FactoryHttpConnection {
	
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
