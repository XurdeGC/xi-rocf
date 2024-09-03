package net.xurdegc.xi_rocf.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchIllegalArgumentException;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.StringJoiner;
import net.xurdegc.xi_rocf.domain.validation.base.ValidationErrorMessageLoader;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Coach Name Test")
class CoachNameTest {
  @Nested
  @DisplayName("When creating a coach name...")
  class WhenCreatingCoachName {
    private static final String ERROR_CREATING_MESSAGE_KEY = "error.creating";
    private static final String COACH_NAME_TEXT_MISSING_MESSAGE_KEY = "coach_name.text.missing";

    @ParameterizedTest
    @ValueSource(strings = {"Calleja", "  \t Calleja  \t "})
    @DisplayName(
        "When creating a coach name with an actual text, the coach name is created succesfully.")
    void createCoachName_withActualText_createsCoachNameSuccesfully(final String text) {
      final CoachName coachName = new CoachName(text);

      assertThat(coachName.text())
          .as("Testing coach name creation with an actual text.")
          .isEqualTo("Calleja");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    @DisplayName(
        "When creating a coach name with a blank or missing text, an IllegalArgumentException is raised.")
    void createCoachName_withBlankMissingText_throwsIllegalArgumentException(
        final String blankMissingText) {
      final IllegalArgumentException exceptionWhenBlankMissingText =
          catchIllegalArgumentException(() -> new CoachName(blankMissingText));

      SoftAssertions assertions = new SoftAssertions();

      assertions
          .assertThat(exceptionWhenBlankMissingText)
          .as(
              "Testing IllegalArgumentException is raised when coach name text is blank or missing.")
          .isInstanceOf(IllegalArgumentException.class);

      final ResourceBundle validationMessages =
          ValidationErrorMessageLoader.getInstance().getValidationErrorMessages();
      int validationErrorCounter = 1;
      assertions
          .assertThat(exceptionWhenBlankMissingText.getMessage())
          .as("Testing IllegalArgumentException message when coach name text is blank or missing.")
          .isEqualTo(
              new StringJoiner(System.lineSeparator())
                  .add(
                      new MessageFormat(validationMessages.getString(ERROR_CREATING_MESSAGE_KEY))
                          .format(new Object[] {CoachName.class.getSimpleName()}))
                  .add(
                      new StringBuilder()
                          .append(validationErrorCounter)
                          .append(" - ")
                          .append(
                              validationMessages.getString(COACH_NAME_TEXT_MISSING_MESSAGE_KEY)))
                  .toString());

      assertions.assertAll();
    }
  }

  @Nested
  @DisplayName("When comparing coach names...")
  class WhenComparingCoachNames {
    @Test
    @DisplayName("When comparing two coach names with the same text, both are equal.")
    void compareCoachNames_withSameText_areEqual() {
      final CoachName coachName1 = new CoachName("Calleja");
      final CoachName coachName2 = new CoachName("   Calleja   ");

      assertThat(coachName1)
          .as("Testing coach name comparation when two coach names have the same text.")
          .isEqualTo(coachName2);
    }

    @Test
    @DisplayName("When comparing two coach names with different text, they are not equal.")
    void compareCoachNames_withDifferentText_areNotEqual() {
      final CoachName coachName1 = new CoachName("Calleja");
      final CoachName coachName2 = new CoachName("Caleya");

      assertThat(coachName1)
          .as("Testing coach name comparation when two coach names have different text.")
          .isNotEqualTo(coachName2);
    }
  }

  @Nested
  @DisplayName("When checking a coach name...")
  class WhenCheckingCoachName {
    @Test
    @DisplayName(
        "When checking the textual representation from an actual coach name, its text is retrieved.")
    void checkTextualCoachName_withActualCoachName_returnsText() {
      final CoachName coachName = new CoachName("Calleja");

      assertThat(CoachName.IN_TEXT.apply(coachName))
          .as("Testing textual representation for actual coach name.")
          .isEqualTo(coachName.toString());
    }

    @ParameterizedTest
    @NullSource
    @DisplayName(
        "When checking the textual representation from a missing coach name, its nullity is informed.")
    void checkTextualCoachName_withMissingCoachName_informsItsNullity(
        final CoachName missingCoachName) {
      final ResourceBundle coachNameTexts = ResourceBundle.getBundle("texts.vo.CoachName");
      final String nullTextKey = "null";
      final String expectedTextualRepresentation = coachNameTexts.getString(nullTextKey);

      assertThat(CoachName.IN_TEXT.apply(missingCoachName))
          .as("Testing textual representation when coach name is missing.")
          .isEqualTo(expectedTextualRepresentation);
    }
  }
}
