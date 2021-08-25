package edu.escuelaing.arem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Connection with external API to get time series stock data.
 * 
 * @author Angélica
 */
public abstract class StockHttpConnection {

    private static final String USER_AGENT = "Mozilla/5.0";
    protected String stock;
    
    /**
	 * Get stock.
	 * @return stock.
	 */
    public String getStock() {
		return stock;
	}
    
    /**
	 * Set stock.
	 * @param stock id.
	 */
    public void setStock(String stock) {
		this.stock = stock;
	}
    
    /**
	 * Get the URL to make the connection to the external API.
	 * @return URL for the connection.
	 */
    abstract public String getUrl();

    /**
	 * Get the data by making the HTTP connection with the external API.
	 * @throws If exist an error handle exception.
	 * @return stock data get in the external API.
	 */
	public String getStockData() throws IOException {
    	String finalResponse = "None";
        URL obj = new URL(getUrl());
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        
        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            finalResponse = response.toString();
            System.out.println(finalResponse);
        }
        
        else {
            System.out.println("GET request not worked");
        }
        
        System.out.println("GET DONE");
        return finalResponse;
    }
}
