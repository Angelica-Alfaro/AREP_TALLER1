package edu.escuelaing.arem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class StockHttpConnection {

    private static final String USER_AGENT = "Mozilla/5.0";
    protected String stock;

    public String getStock() {
		return stock;
	}
    
    public void setStock(String stock) {
		this.stock = stock;
	}
    
    abstract public String getUrl();

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
