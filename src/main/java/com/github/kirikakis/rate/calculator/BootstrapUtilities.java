package com.github.kirikakis.rate.calculator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class BootstrapUtilities {

    public List<Investor> FetchCsvDataFromFile(String file) throws IOException {
        List<Investor> investors = new ArrayList<>();
        File csvFile = new File(file);
        CSVParser csvParser = CSVParser.parse(csvFile, Charset.defaultCharset(), CSVFormat.RFC4180.withHeader());
        List<CSVRecord> csvRecords = csvParser.getRecords();
        for (CSVRecord csvRecord : csvRecords) {
            investors.add(new Investor(csvRecord.get(0),
                    Double.parseDouble(csvRecord.get(1)),
                    Double.parseDouble(csvRecord.get(2))));
        }
        return investors;
    }
}
