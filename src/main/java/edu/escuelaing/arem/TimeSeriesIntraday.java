package edu.escuelaing.arem;

public class TimeSeriesIntraday extends StockHttpConnection{
	
	@Override
	public String getUrl() {
		return "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + stock + "&interval=5min&apikey=O4NCRGCQKTFV8JGI";
	}
}
