package edu.escuelaing.arem;

import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import static spark.Spark.*;

import java.io.IOException;

/**
 * Minimal web app example for Heroku using SparkWeb
 * 
 * @author Daniel, Angélica
 */
public class App {
	static CacheImpl myCache = new CacheImpl();
	
	/**
     * This main method uses SparkWeb static methods and lambda functions to
     * create a web app. It maps the lambda function to the getStockSeries relative URL.
     */
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        get("/getStockSeries",(req, res) -> getStockSeries(req, res));
    }
    
    /**
     * This method receives the necessary parameters to make the queries 
     * and obtains the data from the cache or by calling the HTTP connection class.
     * @param req - Request to go to another page or file.
     * @param res - Response to go to another page or file.
     * @return the data in JSON format
     */
    private static String getStockSeries(Request req, Response res) {
    	res.type("application/json"); // Returns the data in JSON format
    	//String stock = req.queryParams("stock"); // Parameter to put in the queries directly from here
    	//StockHttpConnection stockTimeSeries = FactoryHttpConnection.getStockTimeSeries("TimeSeriesIntraday"); // To put the time series directly from here
    	QueryParamsMap map = req.queryMap();// Parameter received from the front to put in the queries
    	String stock =  map.get("id").value();
    	StockHttpConnection stockTimeSeries = FactoryHttpConnection.getStockTimeSeries(map.get("ts").value());
    	String response = "Failed";
    	
    	if (stock != "" && stock != null && !stock.isEmpty()) {
    		stockTimeSeries.setStock(stock);
    	}
    	else {
    		stockTimeSeries.setStock("GOOG");
    	}
    	
    	String dataStr = myCache.getData(stock, map.get("ts").value());
    	if(dataStr.equals("") || dataStr.equals(null)) {
	    	try {
				response = stockTimeSeries.getStockData();
				myCache.saveData(response, stock, map.get("ts").value());
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	else {
    		response = dataStr;
    	}
    	return response;
  	}

    /**
     * This method reads the default port as specified by the PORT variable in
     * the environment.
     *
     * Heroku provides the port automatically so you need this to run the
     * project on Heroku.
     * @return the connection port
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}
