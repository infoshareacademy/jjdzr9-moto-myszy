package com.isa.jjdzr.walletcore.market;

import com.isa.jjdzr.walletcore.dto.Asset;
import com.isa.jjdzr.walletcore.dto.HistoricalDataDto;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistoricalMarket {

    public List<HistoricalDataDto> getMonthlyData(String id) {
        List<HistoricalDataDto> result = new ArrayList<>();
        List<LocalDate> days  = ApiUTIL.getLastMonthDates();

        String url = "https://www.alphavantage.co/query?function=DIGITAL_CURRENCY_DAILY&symbol=" +
                id +
                "&market=PLN&apikey=" +
                ApiUTIL.getApiKeyFromFile();

        try {
            String json = ApiUTIL.getJsonFromUrl(url);
            JSONObject jsonObject = new JSONObject(json);
            JSONObject metaData = jsonObject.getJSONObject("Meta Data");
            String name = metaData.getString("3. Digital Currency Name");
            for (LocalDate d: days) {
                String date = ApiUTIL.formatDate(d);
                JSONObject dailyData = jsonObject.getJSONObject("Time Series (Digital Currency Daily)")
                        .getJSONObject(date);

                HistoricalDataDto historicalData = new HistoricalDataDto();
                historicalData.setId(id);
                historicalData.setName(name);
                historicalData.setDate(d);
                historicalData.setOpen(dailyData.getBigDecimal("1a. open (PLN)"));
                historicalData.setHigh(dailyData.getBigDecimal("2a. high (PLN)"));
                historicalData.setLow(dailyData.getBigDecimal("3a. low (PLN)"));
                historicalData.setClose(dailyData.getBigDecimal("4a. close (PLN)"));
                historicalData.setVolume(dailyData.getBigDecimal("5. volume"));
                result.add(historicalData);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
