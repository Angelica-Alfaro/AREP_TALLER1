package edu.escuelaing.arem;

/**
 * Stock time series daily.
 * 
 * @author Ang?lica
 */
public class TimeSeriesIntraday extends StockHttpConnection{
	
	/**
	 * Get the URL to make the connection to the external API.
	 * @return URL for the connection.
	 */
	@Override
	public String getUrl() {
		return "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + stock + "&interval=5min&apikey=O4NCRGCQKTFV8JGI";
	}
}
