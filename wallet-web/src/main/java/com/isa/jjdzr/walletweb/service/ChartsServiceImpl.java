package com.isa.jjdzr.walletweb.service;

import com.isa.jjdzr.walletcore.dto.Wallet;
import com.isa.jjdzr.walletweb.dto.DetailedWalletAssetDto;
import org.jfree.chart.ChartFactory;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;

@Service
public class ChartsServiceImpl implements ChartsService {

    @Override
    public void createWalletChart(List<DetailedWalletAssetDto> walletAssets, Wallet wallet) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (DetailedWalletAssetDto wa : walletAssets) {
            dataset.setValue(wa.getAssetId(), wa.getCurrentValue());
        }
        dataset.setValue("Gotówka", wallet.getCash());

        JFreeChart chart = ChartFactory.createPieChart(
                wallet.getWalletName(),   // chart title
                dataset,          // data
                true,             // include legend
                true,
                false);
        saveWalletChart(chart);

    }
    private void saveWalletChart(JFreeChart chart) {
        int width = 640;
        int height = 480;
        Path path = Path.of("wallet-web","src","main","resources","static","images", "piechart.jpeg");
        File pieChart = new File( path.toUri());
        try {
            ChartUtils.saveChartAsJPEG( pieChart , chart , width , height );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
