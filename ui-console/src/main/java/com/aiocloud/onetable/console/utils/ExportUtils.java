
package com.aiocloud.onetable.console.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @ClassName ExportUtils
 * @Description 导出工具类
 * @Author shux
 * @Date 2025/2/20 20:33
 */

public class ExportUtils {
    /**
     * 导出数据为 Excel 文件
     *
     * @param headers 表头
     * @param data    数据
     * @return 导出的文件资源
     */
    public static Resource exportToExcel(List<String> headers, List<Map<String, Object>> data) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Sheet1");

            // 创建表头
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers.get(i));
            }

            // 填充数据
            for (int i = 0; i < data.size(); i++) {
                Row row = sheet.createRow(i + 1);
                Map<String, Object> rowData = data.get(i);
                for (int j = 0; j < headers.size(); j++) {
                    Cell cell = row.createCell(j);
                    Object value = rowData.get(headers.get(j));
                    cell.setCellValue(value != null ? value.toString() : "");
                }
            }

            // 写入字节流
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayResource(outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("导出 Excel 失败", e);
        }
    }

    /**
     * 导出数据为 CSV 文件
     *
     * @param headers 表头
     * @param data    数据
     * @return 导出的文件资源
     */
    public static Resource exportToCSV(List<String> headers, List<Map<String, Object>> data) {
        StringBuilder csvContent = new StringBuilder();

        // 添加表头
        csvContent.append(String.join(",", headers)).append("\n");

        // 添加数据
        for (Map<String, Object> rowData : data) {
            for (int i = 0; i < headers.size(); i++) {
                if (i > 0) {
                    csvContent.append(",");
                }
                Object value = rowData.get(headers.get(i));
                csvContent.append(value != null ? value.toString() : "");
            }
            csvContent.append("\n");
        }

        return new ByteArrayResource(csvContent.toString().getBytes());
    }
}

