package tests.jenkins;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SkippedTests {

    @Test
    @Disabled
    @DisplayName("Пропущенная проверка без причины")
    void skippedTest1() {
        assertTrue(false);
    }

    @Test
    @Disabled("Проверка временно отключена для демонстрации отчета")
    @DisplayName("Пропущенная проверка с причиной")
    void skippedTest2() {
        assertTrue(false);
    }
}
