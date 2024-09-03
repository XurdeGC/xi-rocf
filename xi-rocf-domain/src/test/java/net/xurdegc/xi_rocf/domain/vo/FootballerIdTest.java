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

@DisplayName("Footballer Identifier Test")
class FootballerIdTest {
  @Nested
  @DisplayName("When creating a footballer identifier...")
  class WhenCreatingFootballerId {
    private static final String ERROR_CREATING_MESSAGE_KEY = "error.creating";
    private static final String FOOTBALLER_ID_LABEL_MISSING_MESSAGE_KEY =
        "footballer_id.label.missing";

    @Test
    @DisplayName(
        "When creating a footballer identifier from scratch, the footballer identifier is created succesfully.")
    void createFootballerId_fromScratch_createsFootballerIdSuccesfully() {
      final FootballerId brandNewFootballerId = FootballerId.NEW.get();

      final SoftAssertions assertions = new SoftAssertions();

      assertions
          .assertThat(brandNewFootballerId)
          .as("Testing footballer identifier creation from scratch.")
          .isNotNull();
      assertions
          .assertThat(brandNewFootballerId.label())
          .as("Testing footballer identifier label creation from scratch.")
          .isNotNull();

      assertions.assertAll();
    }

    @Test
    @DisplayName(
        "When creating a footballer identifier from a label, the footballer identifier is created succesfully.")
    void createFootballerId_fromLabel_createsFootballerIdSuccesfully() {
      final UUID label = UUID.randomUUID();
      final FootballerId footballerIdFromLabel = FootballerId.FROM_LABEL.apply(label);

      final SoftAssertions assertions = new SoftAssertions();

      assertions
          .assertThat(footballerIdFromLabel)
          .as("Testing footballer identifier creation from label.")
          .isNotNull();
      assertions
          .assertThat(footballerIdFromLabel.label())
          .as("Testing footballer identifier value from label.")
          .isEqualTo(label);

      assertions.assertAll();
    }

    @ParameterizedTest
    @NullSource
    @DisplayName(
        "When creating a footballer identifier with missing label, an IllegalArgumentException is raised.")
    void createFootballerId_withMissingLabel_throwsIllegalArgumentException(
        final UUID missingLabel) {
      final IllegalArgumentException exceptionWhenMissingLabel =
          catchIllegalArgumentException(() -> new FootballerId(missingLabel));

      final SoftAssertions assertions = new SoftAssertions();

      assertions
          .assertThat(exceptionWhenMissingLabel)
          .as(
              "Testing IllegalArgumentException is raised when footballer identifier label is missing.")
          .isInstanceOf(IllegalArgumentException.class);

      final ResourceBundle validationMessages =
          ValidationErrorMessageLoader.getInstance().getValidationErrorMessages();
      int validationErrorCounter = 1;
      assertions
          .assertThat(exceptionWhenMissingLabel.getMessage())
          .as(
              "Testing IllegalArgumentException message when footballer identifier label is missing.")
          .isEqualTo(
              new StringJoiner(System.lineSeparator())
                  .add(
                      new MessageFormat(validationMessages.getString(ERROR_CREATING_MESSAGE_KEY))
                          .format(new Object[] {FootballerId.class.getSimpleName()}))
                  .add(
                      new StringBuilder()
                          .append(validationErrorCounter)
                          .append(" - ")
                          .append(
                              validationMessages.getString(
                                  FOOTBALLER_ID_LABEL_MISSING_MESSAGE_KEY)))
                  .toString());

      assertions.assertAll();
    }
  }

  @Nested
  @DisplayName("When comparing footballer identifiers...")
  class WhenComparingFootballerIds {
    @Test
    @DisplayName("When comparing two footballer identifiers with the same label, both are equal.")
    void compareFootballerIds_withSameLabel_areEqual() {
      final FootballerId footballerId1 = FootballerId.NEW.get();
      final FootballerId footballerId2 = FootballerId.FROM_LABEL.apply(footballerId1.label());

      assertThat(footballerId1)
          .as(
              "Testing footballer identifier comparation when two footballer identifiers have the same label.")
          .isEqualTo(footballerId2);
    }

    @Test
    @DisplayName(
        "When comparing two footballer identifiers with different label, they are not equal.")
    void compareFootballerIds_withDifferentLabel_areNotEqual() {
      final FootballerId footballerId1 = FootballerId.NEW.get();
      final FootballerId footballerId2 = FootballerId.NEW.get();

      assertThat(footballerId1)
          .as(
              "Testing footballer identifier comparation when two footballer identifiers have different label.")
          .isNotEqualTo(footballerId2);
    }
  }

  @Nested
  @DisplayName("When checking a footballer identifier...")
  class WhenCheckingFootballerId {
    @Test
    @DisplayName(
        "When checking the textual representation from an actual footballer identifier, its label in text is retrieved.")
    void checkTextualFootballerId_withActualFootballerId_returnsLabel() {
      final FootballerId footballerId = FootballerId.NEW.get();

      assertThat(FootballerId.IN_TEXT.apply(footballerId))
          .as("Testing textual representation for actual footballer identifier.")
          .isEqualTo(footballerId.toString());
    }

    @ParameterizedTest
    @NullSource
    @DisplayName(
        "When checking the textual representation from a missing footballer identifier, its nullity is informed.")
    void checkTextualFootballerId_withMissingFootballerId_informsItsNullity(
        final FootballerId missingFootballerId) {
      final ResourceBundle footballerIdTexts = ResourceBundle.getBundle("texts.vo.FootballerId");
      final String nullTextKey = "null";
      final String expectedTextualRepresentation = footballerIdTexts.getString(nullTextKey);

      assertThat(FootballerId.IN_TEXT.apply(missingFootballerId))
          .as("Testing textual representation when footballer identifier is missing.")
          .isEqualTo(expectedTextualRepresentation);
    }
  }
}
