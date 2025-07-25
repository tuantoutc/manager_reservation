package com.example.nat.clone;

public class TableFormatter {
    public static void printTable(String[] headers, String[][] data) {
        // Tính độ rộng tối đa cho mỗi cột
        int[] columnWidths = calculateColumnWidths(headers, data);

        // In đường viền trên
        printHorizontalBorder(columnWidths);

        // In tiêu đề
        printRow(headers, columnWidths);

        // In đường viền giữa
        printHorizontalBorder(columnWidths);

        // In dữ liệu
        for (String[] row : data) {
            printRow(row, columnWidths);
        }

        // In đường viền dưới
        printHorizontalBorder(columnWidths);
    }

    private static int[] calculateColumnWidths(String[] headers, String[][] data) {
        int[] widths = new int[headers.length];

        // Bắt đầu với độ rộng của tiêu đề
        for (int i = 0; i < headers.length; i++) {
            widths[i] = headers[i].length();
        }

        // So sánh với độ rộng của dữ liệu
        for (String[] row : data) {
            for (int i = 0; i < row.length && i < widths.length; i++) {
                if (row[i] != null && row[i].length() > widths[i]) {
                    widths[i] = row[i].length();
                }
            }
        }

        // Thêm padding
        for (int i = 0; i < widths.length; i++) {
            widths[i] += 2; // padding 1 ký tự mỗi bên
        }

        return widths;
    }

    private static void printHorizontalBorder(int[] columnWidths) {
        System.out.print("+");
        for (int width : columnWidths) {
            for (int i = 0; i < width; i++) {
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.println();
    }

    private static void printRow(String[] row, int[] columnWidths) {
        System.out.print("|");
        for (int i = 0; i < row.length; i++) {
            String cell = row[i] == null ? "" : row[i];
            String formatted = String.format(" %-" + (columnWidths[i] - 1) + "s", cell);
            System.out.print(formatted + "|");
        }
        System.out.println();
    }
}
