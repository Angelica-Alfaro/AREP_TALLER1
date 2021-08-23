package edu.escuelaing.arem;

public class TimeSeriesMonthly extends StockHttpConnection{
	
	@Override
	public String getUrl() {
		return "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol=" + stock + "&apikey=O4NCRGCQKTFV8JGI";
	}
}
