package edu.escuelaing.arem;

public class TimeSeriesWeekly extends StockHttpConnection {

	@Override
	public String getUrl() {
		return "https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=" + stock + "&apikey=O4NCRGCQKTFV8JGI";
	}
}
