package net.xurdegc.xi_rocf.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchIllegalArgumentException;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.StringJoiner;
import java.util.UUID;
import net.xurdegc.xi_rocf.domain.validation.base.ValidationErrorMessageLoader;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

@DisplayName("Coach Identifier Test")
class CoachIdTest {
  @Nested
  @DisplayName("When creating a coach identifier...")
  class WhenCreatingCoachId {
    private static final String ERROR_CREATING_MESSAGE_KEY = "error.creating";
    private static final String COACH_ID_LABEL_MISSING_MESSAGE_KEY = "coach_id.label.missing";

    @Test
    @DisplayName(
        "When creating a coach identifier from scratch, the coach identifier is created succesfully.")
    void createCoachId_fromScratch_createsCoachIdSuccesfully() {
      final CoachId brandNewCoachId = CoachId.NEW.get();

      final SoftAssertions assertions = new SoftAssertions();

      assertions
          .assertThat(brandNewCoachId)
          .as("Testing coach identifier creation from scratch.")
          .isNotNull();
      assertions
          .assertThat(brandNewCoachId.label())
          .as("Testing coach identifier label creation from scratch.")
          .isNotNull();

      assertions.assertAll();
    }

    @Test
    @DisplayName(
        "When creating a coach identifier from a label, the coach identifier is created succesfully.")
    void createCoachId_fromLabel_createsCoachIdSuccesfully() {
      final UUID label = UUID.randomUUID();
      final CoachId coachIdFromLabel = CoachId.FROM_LABEL.apply(label);

      final SoftAssertions assertions = new SoftAssertions();

      assertions
          .assertThat(coachIdFromLabel)
          .as("Testing coach identifier creation from label.")
          .isNotNull();
      assertions
          .assertThat(coachIdFromLabel.label())
          .as("Testing coach identifier value from label.")
          .isEqualTo(label);

      assertions.assertAll();
    }

    @ParameterizedTest
    @NullSource
    @DisplayName(
        "When creating a coach identifier with missing label, an IllegalArgumentException is raised.")
    void createCoachId_withMissingLabel_throwsIllegalArgumentException(final UUID missingLabel) {
      final IllegalArgumentException exceptionWhenMissingLabel =
          catchIllegalArgumentException(() -> new CoachId(missingLabel));

      final SoftAssertions assertions = new SoftAssertions();

      assertions
          .assertThat(exceptionWhenMissingLabel)
          .as("Testing IllegalArgumentException is raised when coach identifier label is missing.")
          .isInstanceOf(IllegalArgumentException.class);

      final ResourceBundle validationMessages =
          ValidationErrorMessageLoader.getInstance().getValidationErrorMessages();
      int validationErrorCounter = 1;
      assertions
          .assertThat(exceptionWhenMissingLabel.getMessage())
          .as("Testing IllegalArgumentException message when coach identifier label is missing.")
          .isEqualTo(
              new StringJoiner(System.lineSeparator())
                  .add(
                      new MessageFormat(validationMessages.getString(ERROR_CREATING_MESSAGE_KEY))
                          .format(new Object[] {CoachId.class.getSimpleName()}))
                  .add(
                      new StringBuilder()
                          .append(validationErrorCounter)
                          .append(" - ")
                          .append(validationMessages.getString(COACH_ID_LABEL_MISSING_MESSAGE_KEY)))
                  .toString());

      assertions.assertAll();
    }
  }

  @Nested
  @DisplayName("When comparing coach identifiers...")
  class WhenComparingCoachIds {
    @Test
    @DisplayName("When comparing two coach identifiers with the same label, both are equal.")
    void compareCoachIds_withSameLabel_areEqual() {
      final CoachId coachId1 = CoachId.NEW.get();
      final CoachId coachId2 = CoachId.FROM_LABEL.apply(coachId1.label());

      assertThat(coachId1)
          .as(
              "Testing coach identifier comparation when two coach identifiers have the same label.")
          .isEqualTo(coachId2);
    }

    @Test
    @DisplayName("When comparing two coach identifiers with different label, they are not equal.")
    void compareCoachIds_withDifferentLabel_areNotEqual() {
      final CoachId coachId1 = CoachId.NEW.get();
      final CoachId coachId2 = CoachId.NEW.get();

      assertThat(coachId1)
          .as(
              "Testing coach identifier comparation when two coach identifiers have different label.")
          .isNotEqualTo(coachId2);
    }
  }

  @Nested
  @DisplayName("When checking a coach identifier...")
  class WhenCheckingCoachId {
    @Test
    @DisplayName(
        "When checking the textual representation from an actual coach identifier, its label in text is retrieved.")
    void checkTextualCoachId_withActualCoachId_returnsLabel() {
      final CoachId coachId = CoachId.NEW.get();

      assertThat(CoachId.IN_TEXT.apply(coachId))
          .as("Testing textual representation for actual coach identifier.")
          .isEqualTo(coachId.toString());
    }

    @ParameterizedTest
    @NullSource
    @DisplayName(
        "When checking the textual representation from a missing coach identifier, its nullity is informed.")
    void checkTextualCoachId_withMissingCoachId_informsItsNullity(final CoachId missingCoachId) {
      final ResourceBundle coachIdTexts = ResourceBundle.getBundle("texts.vo.CoachId");
      final String nullTextKey = "null";
      final String expectedTextualRepresentation = coachIdTexts.getString(nullTextKey);

      assertThat(CoachId.IN_TEXT.apply(missingCoachId))
          .as("Testing textual representation when coach identifier is missing.")
          .isEqualTo(expectedTextualRepresentation);
    }
  }
}
