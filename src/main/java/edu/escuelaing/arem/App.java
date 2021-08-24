package edu.escuelaing.arem;

import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import static spark.Spark.*;

import java.io.IOException;

/**
 * Minimal web app example for Heroku using SparkWeb
 */
public class App {
	static CacheImpl myCache = new CacheImpl();
	
	/**
     * This main method uses SparkWeb static methods and lambda functions to
     * create a simple Hello World web app. It maps the lambda function to the
     * /hello relative URL.
     */
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        get("/getStockSeries",(req, res) -> getStockSeries(req, res));
    }
    
    private static String getStockSeries(Request req, Response res) {
    	res.type("application/json"); //Devuelve los datos en formato JSON
    	//String stock = req.queryParams("stock"); //Par�metro para poner en las consultas directamente desde ac�
    	//StockHttpConnection stockTimeSeries = FactoryHttpConnection.getStockTimeSeries("TimeSeriesIntraday"); //Para poner la serie de tiempo directamente desde ac�
    	QueryParamsMap map = req.queryMap();//Par�metro recibido desde el front para poner en las consultas
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
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}
