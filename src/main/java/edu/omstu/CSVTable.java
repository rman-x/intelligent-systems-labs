package edu.omstu;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVTable {
    public Map<String, Integer> columnsMapping;
    public List<String[]> values;

    public CSVTable(String csv) {
        this.columnsMapping = new HashMap<>();
        this.values = new ArrayList<>();
        InputStream in = getClass().getClassLoader().getResourceAsStream(csv);
        List<String> lines = new BufferedReader(new InputStreamReader(in)).lines().toList();
        String header = lines.get(0);
        String[] columns = header.split(",");
        for (int i = 0; i < columns.length; i++) {
            columnsMapping.put(columns[i], i);
        }
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] row = line.split(",");
            values.add(row);
        }
    }

    public String getValue(String column, int index) {
        int columnIndex = columnsMapping.getOrDefault(column, -1);
        if (columnIndex != -1) {
            return values.get(index)[columnIndex];
        }
        return null;
    }
}
