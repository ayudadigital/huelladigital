package com.huellapositiva.unit;

import com.huellapositiva.domain.model.valueobjects.Location;
import com.huellapositiva.util.TestData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(TestData.class)
class LocationShould {

    @ParameterizedTest
    @MethodSource("provideIncorrectZipCode")
    void return_false_if_send_wrong_zip_code(String zipCode) {
        assertThat(!Location.isNotZipCode(zipCode)).isFalse();
    }

    private static Stream<String> provideIncorrectZipCode() {
        return Stream.of(
                "20000",
                "35a00",
                "2x332"
        );
    }

    @ParameterizedTest
    @MethodSource("provideCorrectZipCode")
    void return_true_if_send_wrong_zip_code(String zipCode) {
        assertThat(!Location.isNotZipCode(zipCode)).isTrue();
    }

    private static Stream<String> provideCorrectZipCode() {
        return Stream.of(
                "35000",
                "38200"
        );
    }
}
