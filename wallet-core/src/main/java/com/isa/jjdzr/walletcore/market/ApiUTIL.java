package com.isa.jjdzr.walletcore.market;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class ApiUTIL {
    private ApiUTIL() {};

    public static String getApiKeyFromFile() {
        Path path = Path.of("data", "apikey.txt");
        StringBuilder builder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(path.toUri()), StandardCharsets.UTF_8)) {
            stream.forEach(builder::append);
        }catch (IOException e) {
            e.printStackTrace();
        }

        return builder.toString();
    }

    public static String getJsonFromUrl(String url) {
        String json = "";
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                json += line;
            }

            reader.close();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    public static List<LocalDate> getLastMonthDates() {
        List<LocalDate> result = new ArrayList<>();
        LocalDate day = LocalDate.now();
        while (result.size() < 30) {
            result.add(day);
            day = day.minusDays(1);
        }
        return result;
    }

    public static String formatDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }
}
