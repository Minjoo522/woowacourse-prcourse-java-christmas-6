package christmas.service;

import christmas.domain.Menu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    private CSVReader() {
        // 인스턴스 생성 방지용
    }

    public static List<Menu> getMenus(String path) {
        List<Menu> menus = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            br.readLine(); // skip CSV header
            while ((line = br.readLine()) != null) {
                menus.add(readMenu(line));
            }
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }
        return menus;
    }

    private static Menu readMenu(String line) {
        List<String> parsedLine = Parser.splitByComma(line);
        if (parsedLine.size() < 3) {
            throw new IllegalArgumentException();
        }
        return new Menu(parsedLine.get(0), parsedLine.get(1), parsedLine.get(2));
    }
}
