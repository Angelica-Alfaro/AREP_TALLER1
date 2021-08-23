var apiclient = (function () {

    var getTimeSeries = function (stockId, timeSeries, callback) {
        $.getJSON("/getStockSeries?id=" + stockId + "&ts=" + timeSeries ,function (data) {
            callback(data);
        });}
    return {
        getTimeSeries: getTimeSeries,
    };
})();