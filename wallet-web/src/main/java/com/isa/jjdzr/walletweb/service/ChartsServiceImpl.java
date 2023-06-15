package com.isa.jjdzr.walletweb.service;

import com.isa.jjdzr.walletcore.dto.HistoricalDataDto;
import com.isa.jjdzr.walletcore.dto.Wallet;
import com.isa.jjdzr.walletweb.webcommons.WebConstants;
import com.isa.jjdzr.walletweb.dto.DetailedWalletAssetDto;
import com.isa.jjdzr.walletweb.service.fileuploader.FileUploader;
import lombok.RequiredArgsConstructor;
import org.jfree.chart.ChartFactory;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChartsServiceImpl implements ChartsService {

    private final FileUploader fileUploader;

    @Override
    public void createWalletChart(List<DetailedWalletAssetDto> walletAssets, Wallet wallet) {
        DefaultPieDataset dataset = preparePieChartDataset(walletAssets, wallet);
        JFreeChart chart = preparePieChart(wallet.getWalletName(), dataset);
        saveWalletChart(chart, WebConstants.PIE_CHART_FILE);
    }

    public void createCandleStickChart(List<HistoricalDataDto> data) {
        DefaultHighLowDataset dataset = prepareCandleStickDataSet(data);
        String assetName = data.get(0).getName();
        JFreeChart chart = prepareCandleStickChart(assetName, dataset);
        saveWalletChart(chart, WebConstants.CANDLE_CHART_FILE);
    }

    private DefaultHighLowDataset prepareCandleStickDataSet(List<HistoricalDataDto> data) {
        int dataSize = data.size();
        Date[] date = new Date[dataSize];
        double[] high = new double[dataSize];
        double[] low = new double[dataSize];
        double[] open = new double[dataSize];
        double[] close = new double[dataSize];
        double[] volume = new double[dataSize];

        for (int i = 0; i < dataSize; i++) {
            HistoricalDataDto dto = data.get(i);
            date[i] = Date.valueOf(dto.getDate());
            high[i] = Double.parseDouble(dto.getHigh().toString()) ;
            low[i] = Double.parseDouble(dto.getLow().toString());
            open[i] = Double.parseDouble(dto.getOpen().toString());
            close[i] = Double.parseDouble(dto.getClose().toString());
            volume[i] = Double.parseDouble(dto.getVolume().toString());
        }

        return new DefaultHighLowDataset("", date, high, low, open, close, volume);

    }

    private JFreeChart prepareCandleStickChart(String assetName, DefaultHighLowDataset dataset) {
        return ChartFactory.createCandlestickChart(
                assetName,
                "Data",
                "Cena",
                dataset,
                true
        );
    }

    private DefaultPieDataset preparePieChartDataset(List<DetailedWalletAssetDto> walletAssets, Wallet wallet) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        Map<String, BigDecimal> chartData = preparePieChartData(walletAssets);
        for (String key: chartData.keySet()) {
            dataset.setValue(key, chartData.get(key).setScale(2, RoundingMode.CEILING));
        }
        dataset.setValue("Gotówka: \n"+ wallet.getCash() + "PLN", wallet.getCash().setScale(2, RoundingMode.CEILING));
        return dataset;
    }
    private JFreeChart preparePieChart(String walletName, DefaultPieDataset dataset) {
        return ChartFactory.createPieChart(
                walletName,
                dataset,
                true,
                true,
                false);
    }

    private Map<String, BigDecimal> preparePieChartData(List<DetailedWalletAssetDto> walletAssets) {
        Map<String, BigDecimal> result = new HashMap<>();
        String key;
        for (DetailedWalletAssetDto wa: walletAssets) {
            key = wa.getAssetId() + ":\n" + wa.getCurrentValue().setScale(2, RoundingMode.CEILING) + "PLN";
            result.put(key, wa.getCurrentValue());
        }
        return result;
    }

    private void saveWalletChart(JFreeChart chart, String name) {
        int width = 640;
        int height = 480;
//        Path path = Path.of("wallet-web","src","main","resources","static","images", name);
        Path path = Path.of("data", name);
        File pieChart = new File(path.toUri());
        try {
            ChartUtils.saveChartAsJPEG( pieChart , chart , width , height );
            fileUploader.uploadChart(path.toString(), name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
