import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

public class SampleTests {
    @Disabled("Ужас! Все падает!")
    @DisplayName("Просто Тест")
    @Test
    void samples() {
        Assertions.assertEquals("Good", "Good");
        Assertions.assertNotEquals("Good", "Bad");
        Assertions.assertTrue(5 > 2);
        Assertions.assertFalse(10 < 2);
        Assertions.assertAll(
                () -> Assertions.assertFalse(10 < 2),
                () -> Assertions.assertTrue(5 > 2),
                () -> Assertions.assertEquals("Good", "Good")
        );

    }

    @CsvSource(value = {
            "2, 2, 4",
            "3, 2, 5",
            "6, 6, 12",
            "5, 5, 10"
    })
    @ParameterizedTest
    void sumTest(int a, int b, int c) {
        Assertions.assertEquals(a + b, c);
    }

    // Метод передачи данных. Его имя должно совпадать с именем аннотации ниже
    static Stream<Arguments> methodSourceExampleTest() {
        return Stream.of(
                // с первым запуском тест получит в виде аргументов строки и список
                Arguments.of("first string", List.of(42, 13)),
                // со вторым запуском уже другую строку и список
                Arguments.of("second string", List.of(1, 2))
        );
    }

    // Аннотация поставщика данных, помним про имя
    @MethodSource("methodSourceExampleTest")
    @ParameterizedTest
    // Метод теста. В этом случае просто выводит в консоль аргументы
    void methodSourceExampleTest(String first, List<Integer> second) {
        System.out.println(first + " and list: " + second);
    }

    enum Direction {
        EAST, WEST, NORTH, SOUTH
    }

    @ParameterizedTest
    @EnumSource(Direction.class)
    void testWithEnumSource(Direction d) {
        // тело теста
    }
}
