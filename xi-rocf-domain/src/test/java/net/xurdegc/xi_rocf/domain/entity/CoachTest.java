package net.xurdegc.xi_rocf.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchIllegalArgumentException;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.StringJoiner;
import net.xurdegc.xi_rocf.domain.validation.base.ValidationErrorMessageLoader;
import net.xurdegc.xi_rocf.domain.vo.CoachEmail;
import net.xurdegc.xi_rocf.domain.vo.CoachId;
import net.xurdegc.xi_rocf.domain.vo.CoachName;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Coach Test")
class CoachTest {
  private static final String ERROR_CREATING_MESSAGE_KEY = "error.creating";
  private static final String COACH_EMAIL_MISSING_MESSAGE_KEY = "coach.email.missing";
  private static final String COACH_NAME_MISSING_MESSAGE_KEY = "coach.name.missing";

  private static ResourceBundle validationMessages;

  @BeforeAll
  static void init() {
    validationMessages = ValidationErrorMessageLoader.getInstance().getValidationErrorMessages();
  }

  @Nested
  @DisplayName("When creating a coach...")
  class WhenCreatingCoach {
    private static final String COACH_ID_MISSING_MESSAGE_KEY = "coach.id.missing";

    @Test
    @DisplayName("When creating a coach with valid data, the coach is created succesfully.")
    void createCoach_withValidData_createsCoachSuccesfully() {
      final Coach validCoach =
          Coach.builder(
                  CoachId.NEW.get(), new CoachEmail("calleja@mail.net"), new CoachName("Calleja"))
              .build();

      final ResourceBundle coachTexts = ResourceBundle.getBundle("texts.entity.Coach");
      final String toStringTextKey = "to_string";
      final String expectedValidCoach =
          new MessageFormat(coachTexts.getString(toStringTextKey))
              .format(
                  new Object[] {
                    validCoach.theirTextualName.get(),
                    validCoach.theirTextualEmail.get(),
                    validCoach.theirTextualId.get()
                  });

      assertThat(validCoach.inText.get())
          .as("Testing coach creation when coach data is valid.")
          .isEqualTo(expectedValidCoach);
    }

    @Test
    @DisplayName("When creating a coach with missing data, an IllegalArgumentException is raised.")
    void createCoach_withMissingData_throwsIllegalArgumentException() {
      final IllegalArgumentException exceptionWhenMissingData =
          catchIllegalArgumentException(() -> Coach.builder(null, null, null).build());

      final SoftAssertions assertions = new SoftAssertions();

      assertions
          .assertThat(exceptionWhenMissingData)
          .as("Testing IllegalArgumentException is raised when coach data are missing.")
          .isInstanceOf(IllegalArgumentException.class);

      int validationErrorCounter = 1;
      assertions
          .assertThat(exceptionWhenMissingData.getMessage())
          .as("Testing IllegalArgumentException message when coach data are missing.")
          .isEqualTo(
              new StringJoiner(System.lineSeparator())
                  .add(
                      new MessageFormat(validationMessages.getString(ERROR_CREATING_MESSAGE_KEY))
                          .format(new Object[] {Coach.class.getSimpleName()}))
                  .add(
                      new StringBuilder()
                          .append(validationErrorCounter++)
                          .append(" - ")
                          .append(validationMessages.getString(COACH_ID_MISSING_MESSAGE_KEY)))
                  .add(
                      new StringBuilder()
                          .append(validationErrorCounter++)
                          .append(" - ")
                          .append(validationMessages.getString(COACH_EMAIL_MISSING_MESSAGE_KEY)))
                  .add(
                      new StringBuilder()
                          .append(validationErrorCounter)
                          .append(" - ")
                          .append(validationMessages.getString(COACH_NAME_MISSING_MESSAGE_KEY)))
                  .toString());

      assertions.assertAll();
    }
  }

  @Nested
  @DisplayName("When updating a coach...")
  class WhenUpdatingCoach {
    @Test
    @DisplayName("When updating a coach with valid data, the coach is updated succesfully.")
    void updateCoach_withValidData_updatesCoachSuccesfully() {
      final Coach coach =
          Coach.builder(
                  CoachId.NEW.get(), new CoachEmail("caleya@mail.net"), new CoachName("Caleya"))
              .build();

      final CoachId coachId = coach.theirId.get();
      final CoachEmail formerCoachEmail = coach.theirEmail.get();
      final CoachName formerCoachName = coach.theirName.get();

      coach.theirUpdatedEmail.accept(new CoachEmail("calleja@mail.net"));
      coach.theirUpdatedName.accept(new CoachName("Calleja"));

      final SoftAssertions assertions = new SoftAssertions();

      assertions
          .assertThat(coach.theirId.get())
          .as(
              "Testing that coach identifier remains the same when coach data is updated with valid data.")
          .isEqualTo(coachId);

      assertions
          .assertThat(coach.theirEmail.get())
          .as("Testing that coach email is changed when coach data is updated with a valid email.")
          .isNotEqualTo(formerCoachEmail);
      assertions
          .assertThat(coach.theirEmail.get().address())
          .as("Testing updated coach email when coach data is updated with a valid email.")
          .isEqualTo("calleja@mail.net");

      assertions
          .assertThat(coach.theirName.get())
          .as("Testing that coach name is changed when coach data is updated with a valid name.")
          .isNotEqualTo(formerCoachName);
      assertions
          .assertThat(coach.theirName.get().text())
          .as("Testing updated coach name when coach data is updated with a valid name.")
          .isEqualTo("Calleja");

      assertions.assertAll();
    }

    @Test
    @DisplayName("When updating a coach with missing email, an IllegalArgumentException is raised.")
    void updateCoach_withMissingEmail_throwsIllegalArgumentException() {
      final Coach coach =
          Coach.builder(
                  CoachId.NEW.get(), new CoachEmail("caleya@mail.net"), new CoachName("Caleya"))
              .build();

      final IllegalArgumentException exceptionWhenMissingEmail =
          catchIllegalArgumentException(() -> coach.theirUpdatedEmail.accept(null));

      final SoftAssertions assertions = new SoftAssertions();

      assertions
          .assertThat(exceptionWhenMissingEmail)
          .as("Testing IllegalArgumentException is raised when coach email are missing.")
          .isInstanceOf(IllegalArgumentException.class);

      int validationErrorCounter = 1;
      assertions
          .assertThat(exceptionWhenMissingEmail.getMessage())
          .as("Testing IllegalArgumentException message when coach email are missing.")
          .isEqualTo(
              new StringJoiner(System.lineSeparator())
                  .add(
                      new MessageFormat(validationMessages.getString(ERROR_CREATING_MESSAGE_KEY))
                          .format(new Object[] {Coach.class.getSimpleName()}))
                  .add(
                      new StringBuilder()
                          .append(validationErrorCounter)
                          .append(" - ")
                          .append(validationMessages.getString(COACH_EMAIL_MISSING_MESSAGE_KEY)))
                  .toString());

      assertions.assertAll();
    }

    @Test
    @DisplayName("When updating a coach with missing name, an IllegalArgumentException is raised.")
    void updateCoach_withMissingName_throwsIllegalArgumentException() {
      final Coach coach =
          Coach.builder(
                  CoachId.NEW.get(), new CoachEmail("caleya@mail.net"), new CoachName("Caleya"))
              .build();

      final IllegalArgumentException exceptionWhenMissingName =
          catchIllegalArgumentException(() -> coach.theirUpdatedName.accept(null));

      final SoftAssertions assertions = new SoftAssertions();

      assertions
          .assertThat(exceptionWhenMissingName)
          .as("Testing IllegalArgumentException is raised when coach name are missing.")
          .isInstanceOf(IllegalArgumentException.class);

      int validationErrorCounter = 1;
      assertions
          .assertThat(exceptionWhenMissingName.getMessage())
          .as("Testing IllegalArgumentException message when coach name are missing.")
          .isEqualTo(
              new StringJoiner(System.lineSeparator())
                  .add(
                      new MessageFormat(validationMessages.getString(ERROR_CREATING_MESSAGE_KEY))
                          .format(new Object[] {Coach.class.getSimpleName()}))
                  .add(
                      new StringBuilder()
                          .append(validationErrorCounter)
                          .append(" - ")
                          .append(validationMessages.getString(COACH_NAME_MISSING_MESSAGE_KEY)))
                  .toString());

      assertions.assertAll();
    }
  }

  @Nested
  @DisplayName("When comparing coaches...")
  class WhenComparingCoaches {
    @Test
    @DisplayName("When comparing two coaches with the same identifier, both are equal.")
    void compareCoaches_withSameIdentifier_areEqual() {
      final Coach coach1 =
          Coach.builder(
                  CoachId.NEW.get(), new CoachEmail("calleja@mail.net"), new CoachName("Calleja"))
              .build();
      final Coach coach2 =
          Coach.builder(
                  CoachId.FROM_LABEL.apply(coach1.theirId.get().label()),
                  new CoachEmail("caleya@mail.net"),
                  new CoachName("Caleya"))
              .build();

      assertThat(coach1)
          .as("Testing coach comparation when two coaches have the same identifier.")
          .isEqualTo(coach2);
    }

    @Test
    @DisplayName("When comparing two coaches with different identifier, they are not equal.")
    void compareCoaches_withDifferentIdentifier_areNotEqual() {
      final Coach coach1 =
          Coach.builder(
                  CoachId.NEW.get(), new CoachEmail("calleja@mail.net"), new CoachName("Calleja"))
              .build();
      final Coach coach2 =
          Coach.builder(
                  CoachId.NEW.get(), new CoachEmail("caleya@mail.net"), new CoachName("Caleya"))
              .build();

      assertThat(coach1)
          .as("Testing coach comparation when two coaches have different identifier.")
          .isNotEqualTo(coach2);
    }
  }
}
