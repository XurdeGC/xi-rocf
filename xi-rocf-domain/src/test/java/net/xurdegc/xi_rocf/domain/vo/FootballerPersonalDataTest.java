package net.xurdegc.xi_rocf.domain.vo;

import static org.assertj.core.api.BDDAssertions.catchIllegalArgumentException;

import java.time.LocalDate;
import net.xurdegc.xi_rocf.domain.vo.FootballerPersonalData.Birthdate;
import net.xurdegc.xi_rocf.domain.vo.FootballerPersonalData.FullName;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Footballer Personal Data Test")
public class FootballerPersonalDataTest {
  @Nested
  @DisplayName("When creating footballer personal data...")
  class WhenCreatingFootballerPersonalData {
    @ParameterizedTest
    @ValueSource(strings = {"Santiago Cazorla González", "  \t Santiago Cazorla González  \t "})
    @DisplayName(
        "When creating footballer personal data with valid data, footballer personal data are created succesfully.")
    void createFootballerPersonalData_withValidData_createsFootballerPersonalDataSuccesfully(
        final String fullNameText) {
      final FullName fullName = new FullName(fullNameText);
      final Birthdate birthdate = new Birthdate(LocalDate.of(1984, 12, 13));
      final FootballerPersonalData footballerPersonalData =
          new FootballerPersonalData(fullName, birthdate);

      SoftAssertions assertions = new SoftAssertions();

      assertions
          .assertThat(footballerPersonalData.fullName())
          .as("Testing footballer personal data creation with valid data (full name).")
          .isEqualTo("Santiago Cazorla González");

      assertions
          .assertThat(footballerPersonalData.birthdate())
          .as("Testing footballer personal data creation with valid data (birthdate).")
          .hasToString("1984-12-13");

      assertions.assertAll();

      //        final ResourceBundle footballerPersonalDataTexts =
      // ResourceBundle.getBundle("texts.vo.FootballerPersonalData");
      //        final String nullTextKey = "to_string";
      //        final String expectedTextualRepresentation =
      // footballerPersonalDataTexts.getString(nullTextKey);

    }

    @Test
    @DisplayName(
        "When creating footballer personal data with missing data, an IllegalArgumentException is raised.")
    void createFootballerPersonalData_withMissingData_throwsIllegalArgumentException() {
      final IllegalArgumentException exceptionWhenMissingData =
          catchIllegalArgumentException(() -> new FootballerPersonalData(null, null));

      final SoftAssertions assertions = new SoftAssertions();

      assertions
          .assertThat(exceptionWhenMissingData)
          .as(
              "Testing IllegalArgumentException is raised when footballer personal data are missing.")
          .isInstanceOf(IllegalArgumentException.class);

      assertions.assertAll();
    }
  }
}
