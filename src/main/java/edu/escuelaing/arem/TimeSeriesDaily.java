package edu.escuelaing.arem;

public class TimeSeriesDaily extends StockHttpConnection {

	@Override
	public String getUrl() {
		return "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + stock + "&apikey=O4NCRGCQKTFV8JGI";
	}
}
