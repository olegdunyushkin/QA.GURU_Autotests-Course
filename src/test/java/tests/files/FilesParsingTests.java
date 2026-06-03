package tests.files;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import tests.files.model.ShopOrder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class FilesParsingTests {

    private final ClassLoader classLoader = FilesParsingTests.class.getClassLoader();

    @Test
    void zipFilesParsingTest() throws Exception {
        byte[] pdfFile = readFileFromZip("order-summary.pdf");
        PDF pdf = new PDF(new ByteArrayInputStream(pdfFile));
        assertTrue(pdf.text.contains("QA.GURU Shop Order"));
        assertTrue(pdf.text.contains("ORD-2026-0603"));
        assertTrue(pdf.text.contains("Customer: Oleg"));
        assertTrue(pdf.text.contains("Total: 15990 RUB"));

        byte[] xlsxFile = readFileFromZip("order-items.xlsx");
        XLS xls = new XLS(new ByteArrayInputStream(xlsxFile));
        assertEquals("Product", xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue());
        assertEquals("Java automation course", xls.excel.getSheetAt(0).getRow(1).getCell(0).getStringCellValue());
        assertEquals(1, xls.excel.getSheetAt(0).getRow(1).getCell(1).getNumericCellValue());
        assertEquals(14990, xls.excel.getSheetAt(0).getRow(1).getCell(2).getNumericCellValue());
        assertEquals("Selenide stickers", xls.excel.getSheetAt(0).getRow(2).getCell(0).getStringCellValue());
        assertEquals(2, xls.excel.getSheetAt(0).getRow(2).getCell(1).getNumericCellValue());
        assertEquals(500, xls.excel.getSheetAt(0).getRow(2).getCell(2).getNumericCellValue());

        byte[] csvFile = readFileFromZip("order-report.csv");
        String csv = new String(csvFile, StandardCharsets.UTF_8);
        assertTrue(csv.contains("orderId,customer,total"));
        assertTrue(csv.contains("ORD-2026-0603,Oleg,15990"));
    }

    @Test
    void jacksonJsonParsingTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream is = getResourceAsStream("shop-order.json")) {
            ShopOrder order = objectMapper.readValue(is, ShopOrder.class);

            assertEquals("ORD-2026-0603", order.orderId);
            assertEquals("Oleg", order.customer.name);
            assertEquals("oleg@example.com", order.customer.email);
            assertTrue(order.paid);
            assertEquals(2, order.items.size());
            assertEquals("Java automation course", order.items.get(0).name);
            assertEquals(14990, order.items.get(0).price);
            assertEquals("Selenide stickers", order.items.get(1).name);
            assertEquals(500, order.items.get(1).price);
            assertEquals("Moscow", order.delivery.city);
        }
    }

    private byte[] readFileFromZip(String fileName) throws Exception {
        try (InputStream is = getResourceAsStream("homework-files.zip");
             ZipInputStream zipInputStream = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.getName().equals(fileName)) {
                    return zipInputStream.readAllBytes();
                }
            }
        }

        throw new IllegalArgumentException("File not found in zip archive: " + fileName);
    }

    private InputStream getResourceAsStream(String resourceName) {
        InputStream is = classLoader.getResourceAsStream(resourceName);
        assertNotNull(is, "Resource not found: " + resourceName);
        return is;
    }
}
