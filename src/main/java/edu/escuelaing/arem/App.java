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
	
	/**
     * This main method uses SparkWeb static methods and lambda functions to
     * create a simple Hello World web app. It maps the lambda function to the
     * /hello relative URL.
     */
    public static void main(String[] args) {
        port(getPort());
        staticFiles.location("/public");
        get("/inputdata", (req, res) -> inputDataPage(req, res));
        get("/results", (req, res) -> resultsPage(req, res));
        get("/getStockSeries",(req, res) -> getStockSeries(req, res)); //Opcional application/json
    }

	private static String inputDataPage(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h2>HTML Forms</h2>"
                + "<form action=\"/results\">"
                + "  First name:<br>"
                + "  <input type=\"text\" name=\"firstname\" value=\"Mickey\">"
                + "  <br>"
                + "  Last name:<br>"
                + "  <input type=\"text\" name=\"lastname\" value=\"Mouse\">"
                + "  <br><br>"
                + "  <input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "<p>If you click the \"Submit\" button, the form-data will be sent to a page called \"/results\".</p>"
                + "</body>"
                + "</html>";
        return pageContent;
    }

    private static String resultsPage(Request req, Response res) {
        return req.queryParams("firstname") + " " +
                req.queryParams("lastname"); //Lo que muestra la página
    }
    
    private static String getStockSeries(Request req, Response res) {
    	res.type("application/json"); //Devuelve los datos en formato JSON
    	String stock = req.queryParams("stock"); //Parámetro para poner en las consultas directamente desde acá
    	StockHttpConnection stockTimeSeries = FactoryHttpConnection.getStockTimeSeries("TimeSeriesIntraday"); //Para poner la serie de tiempo directamente desde acá
    	String response = "Failed";
    	
    	if (stock != "" && stock != null && !stock.isEmpty()) {
    		stockTimeSeries.setStock(stock);
    	}
    	else {
    		stockTimeSeries.setStock("GOOG");
    	}
    	
    	try {
			response = stockTimeSeries.getStockData();
		} catch (IOException e) {
			e.printStackTrace();
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
