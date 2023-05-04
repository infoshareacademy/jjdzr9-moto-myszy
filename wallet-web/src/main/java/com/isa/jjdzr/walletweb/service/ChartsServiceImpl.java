package com.isa.jjdzr.walletweb.service;

import com.isa.jjdzr.walletcore.dto.Wallet;
import com.isa.jjdzr.walletweb.dto.DetailedWalletAssetDto;
import com.isa.jjdzr.walletweb.service.fileuploader.FileUploader;
import lombok.RequiredArgsConstructor;
import org.jfree.chart.ChartFactory;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChartsServiceImpl implements ChartsService {

    private final FileUploader fileUploader;

    @Override
    public void createWalletChart(List<DetailedWalletAssetDto> walletAssets, Wallet wallet) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        Map<String, BigDecimal> chartData = prepareChartData(walletAssets);
        for (String key: chartData.keySet()) {
            dataset.setValue(key, chartData.get(key));
        }
        dataset.setValue("Gotówka: \n"+ wallet.getCash() + "PLN", wallet.getCash());

        JFreeChart chart = ChartFactory.createPieChart(
                wallet.getWalletName(),
                dataset,
                true,
                true,
                false);
        saveWalletChart(chart);

    }

    private Map<String, BigDecimal> prepareChartData(List<DetailedWalletAssetDto> walletAssets) {
        Map<String, BigDecimal> result = new HashMap<>();
        String key;
        for (DetailedWalletAssetDto wa: walletAssets) {
            key = wa.getAssetId() + ":\n" + wa.getCurrentValue() + "PLN";
            result.put(key, wa.getCurrentValue());
        }
        return result;
    }

    private void saveWalletChart(JFreeChart chart) {
        int width = 640;
        int height = 480;
//        Path path = Path.of("wallet-web","src","main","resources","static","images", "piechart.jpeg");
        Path path = Path.of("wallet-web","target","classes","static","images", "piechart.jpeg");
        File pieChart = new File(path.toUri());
        try {
            ChartUtils.saveChartAsJPEG( pieChart , chart , width , height );
            fileUploader.uploadChart(path.toString(), "piechart.jpeg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
