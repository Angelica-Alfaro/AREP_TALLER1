package edu.escuelaing.arem;

/**
 * Stock time series daily.
 * 
 * @author Angélica
 */
public class TimeSeriesWeekly extends StockHttpConnection {
	
	/**
	 * Get the URL to make the connection to the external API.
	 * @return URL for the connection.
	 */
	@Override
	public String getUrl() {
		return "https://www.alphavantage.co/query?function=TIME_SERIES_WEEKLY&symbol=" + stock + "&apikey=O4NCRGCQKTFV8JGI";
	}
}
