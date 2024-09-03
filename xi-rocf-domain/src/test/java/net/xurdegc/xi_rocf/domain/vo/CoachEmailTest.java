package net.xurdegc.xi_rocf.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchIllegalArgumentException;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.StringJoiner;
import net.xurdegc.xi_rocf.domain.validation.base.ValidationErrorMessageLoader;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Coach Email Test")
class CoachEmailTest {
  private static ResourceBundle validationMessages;

  @BeforeAll
  static void init() {
    validationMessages = ValidationErrorMessageLoader.getInstance().getValidationErrorMessages();
  }

  @Nested
  @DisplayName("When creating a coach email...")
  class WhenCreatingCoachEmail {
    private static final String ERROR_CREATING_MESSAGE_KEY = "error.creating";
    private static final String COACH_EMAIL_ADDRESS_NOT_VALID_MESSAGE_KEY =
        "coach_email.address.not_valid";
    private static final String COACH_EMAIL_ADDRESS_MISSING_MESSAGE_KEY =
        "coach_email.address.missing";

    @ParameterizedTest
    @ValueSource(strings = {"calleja@mail.net", "CALLEJA@MAIL.NET", "   calleja@mail.net   "})
    @DisplayName(
        "When creating a coach email with a valid address, the coach email is created succesfully.")
    void createCoachEmail_withValidAddress_createsCoachEmailSuccesfully(final String validAddress) {
      final CoachEmail coachEmail = new CoachEmail(validAddress);

      assertThat(coachEmail.address())
          .as("Testing coach email creation when address is valid.")
          .isEqualTo("calleja@mail.net");
    }

    @ParameterizedTest
    @ValueSource(strings = {"cuenta+invalida#mail.net"})
    @DisplayName(
        "When creating an email with a non-valid address, an IllegalArgumentException is raised.")
    void createCoachEmail_withNonValidAddress_throwsIllegalArgumentException(
        final String nonValidAddress) {
      final IllegalArgumentException exceptionWhenNonValidAddress =
          catchIllegalArgumentException(() -> new CoachEmail(nonValidAddress));

      SoftAssertions assertions = new SoftAssertions();

      assertions
          .assertThat(exceptionWhenNonValidAddress)
          .as("Testing IllegalArgumentException is raised when coach email address is not valid.")
          .isInstanceOf(IllegalArgumentException.class);

      int validationErrorCounter = 1;
      assertions
          .assertThat(exceptionWhenNonValidAddress.getMessage())
          .as("Testing IllegalArgumentException message when coach email address is not valid.")
          .isEqualTo(
              new StringJoiner(System.lineSeparator())
                  .add(
                      new MessageFormat(validationMessages.getString(ERROR_CREATING_MESSAGE_KEY))
                          .format(new Object[] {CoachEmail.class.getSimpleName()}))
                  .add(
                      new StringBuilder()
                          .append(validationErrorCounter)
                          .append(" - ")
                          .append(
                              new MessageFormat(
                                      validationMessages.getString(
                                          COACH_EMAIL_ADDRESS_NOT_VALID_MESSAGE_KEY))
                                  .format(new Object[] {nonValidAddress})))
                  .toString());

      assertions.assertAll();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    @DisplayName(
        "When creating a coach email with a blank or missing address, an IllegalArgumentException is raised.")
    void createCoachEmail_withBlankMissingAddress_throwsIllegalArgumentException(
        final String blankMissingAddress) {
      final IllegalArgumentException exceptionWhenBlankMissingAddress =
          catchIllegalArgumentException(() -> new CoachEmail(blankMissingAddress));

      SoftAssertions assertions = new SoftAssertions();

      assertions
          .assertThat(exceptionWhenBlankMissingAddress)
          .as(
              "Testing IllegalArgumentException is raised when coach email address is blank or missing.")
          .isInstanceOf(IllegalArgumentException.class);

      int validationErrorCounter = 1;
      assertions
          .assertThat(exceptionWhenBlankMissingAddress.getMessage())
          .as(
              "Testing IllegalArgumentException message when coach email address is blank or missing.")
          .isEqualTo(
              new StringJoiner(System.lineSeparator())
                  .add(
                      new MessageFormat(validationMessages.getString(ERROR_CREATING_MESSAGE_KEY))
                          .format(new Object[] {CoachEmail.class.getSimpleName()}))
                  .add(
                      new StringBuilder()
                          .append(validationErrorCounter)
                          .append(" - ")
                          .append(
                              validationMessages.getString(
                                  COACH_EMAIL_ADDRESS_MISSING_MESSAGE_KEY)))
                  .toString());

      assertions.assertAll();
    }
  }

  @Nested
  @DisplayName("When comparing coach emails...")
  class WhenComparingCoachEmails {
    @Test
    @DisplayName("When comparing two coach emails with the same address, both are equal.")
    void compareCoachEmails_withSameAddress_areEqual() {
      final CoachEmail coachEmail1 = new CoachEmail("calleja@mail.net");
      final CoachEmail coachEmail2 = new CoachEmail("  callEja@maiL.net     ");

      assertThat(coachEmail1)
          .as("Testing coach email comparation when two coach emails have the same address.")
          .isEqualTo(coachEmail2);
    }

    @Test
    @DisplayName("When comparing two coach emails with different address, they are not equal.")
    void compareCoachEmails_withDifferentAddress_areNotEqual() {
      final CoachEmail coachEmail1 = new CoachEmail("calleja@mail.net");
      final CoachEmail coachEmail2 = new CoachEmail("caleya@maiL.net");

      assertThat(coachEmail1)
          .as("Testing coach email comparation when two coach emails have different label.")
          .isNotEqualTo(coachEmail2);
    }
  }

  @Nested
  @DisplayName("When checking a coach email...")
  class WhenCheckingCoachEmail {
    @Test
    @DisplayName(
        "When checking the textual representation from an actual coach email, its address is retrieved.")
    void checkTextualCoachEmail_withActualCoachEmail_returnsAddress() {
      final CoachEmail coachEmail = new CoachEmail("calleja@mail.net");

      assertThat(CoachEmail.IN_TEXT.apply(coachEmail))
          .as("Testing textual representation for actual coach email.")
          .isEqualTo(coachEmail.toString());
    }

    @ParameterizedTest
    @NullSource
    @DisplayName(
        "When checking the textual representation from a missing coach email, its nullity is informed.")
    void checkTextualCoachEmail_withMissingCoachEmail_informsItsNullity(
        final CoachEmail missingCoachEmail) {
      final ResourceBundle coachEmailTexts = ResourceBundle.getBundle("texts.vo.CoachEmail");
      final String nullTextKey = "null";
      final String expectedTextualRepresentation = coachEmailTexts.getString(nullTextKey);

      assertThat(CoachEmail.IN_TEXT.apply(missingCoachEmail))
          .as("Testing textual representation when coach mail is missing.")
          .isEqualTo(expectedTextualRepresentation);
    }
  }
}
