var app = (function () {
     //URL de trabajo
     //var myUrl = "http://localhost:4567/timeSeries.html" 
     var myUrl = "https://spark-web-arep.herokuapp.com/timeSeries.html"

    var saveData = function () {
        //Obtener valores del HTML y guardarlos en el almacenamiento local de la ventana.
        stockId = $("#stockId").val();
        timeSeries = $("#timeSeries").val();
        if (timeSeries == null) {timeSeries = "TimeSeriesIntraday"};
        window.localStorage.setItem("mystock", stockId);
        window.localStorage.setItem("mytimeSeries", timeSeries);
        window.location.href = myUrl;
    };
    var getData = function (){
        //Obtener los valores almacenados.
        var stockValue = window.localStorage.getItem("mystock");
        var timeSeriesValue = window.localStorage.getItem("mytimeSeries")
        document.getElementById("information").innerHTML = stockValue + " " + timeSeriesValue + " " + "Information";
        //Obtener la información de las acciones.
        apiclient.getTimeSeries(stockValue, timeSeriesValue , updateTable)
    }

    var updateTable = function(data){
        var listData = ["1. open", "2. high", "3. low", "4. close", "5. volume"]
        //Vaciar tabla del HTML.
        var tabla = $("table");
        var body = $("tbody");
        if (body != null) {
            body.remove();
        }
        tabla.append("<tbody>");
        //Llenar tabla del HTLM con la información de las acciones.
        var timeSeriesData = data[window.localStorage.getItem("mytimeSeries")]
        var cad = ""
        for (const tdata in timeSeriesData){
            cad = cad + '<tr> <th>' + tdata + '</th>'
            for (const tsdata in listData){
                cad = cad + '<td>' + timeSeriesData[tdata][listData[tsdata]] + '</td>'
            }
            cad = cad + '</tr>'
        }
        $("tbody").append(cad)
    }

    return {
        saveData: saveData,
        getData: getData,
        updateTable: updateTable,
    }
})();