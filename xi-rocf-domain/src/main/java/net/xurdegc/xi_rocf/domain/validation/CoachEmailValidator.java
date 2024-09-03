package net.xurdegc.xi_rocf.domain.validation;

import java.text.MessageFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import net.xurdegc.xi_rocf.domain.validation.base.TextValidator;

public class CoachEmailValidator extends TextValidator {
  private static final String COACH_EMAIL_ADDRESS_MISSING_MESSAGE_KEY =
      "coach_email.address.missing";
  private static final String COACH_EMAIL_ADDRESS_NOT_VALID_MESSAGE_KEY =
      "coach_email.address.not_valid";
  private static final String EMAIL_ADDRESS_PATTERN =
      "^\\s*[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\s*$";
  private static final Predicate<String> IS_EMAIL_ADDRESS_WELL_FORMED =
      emailAddress ->
          Objects.nonNull(emailAddress)
              && Pattern.compile(EMAIL_ADDRESS_PATTERN).matcher(emailAddress).find();

  @Override
  public void validate(final String validatableCoachEmailAddress, final String coachEmailTypeName) {
    Function<Predicate<String>, Optional<String>>
        generateErrorMessageFromCoachEmailAddressPresenceValidation =
            p ->
                p.negate().test(validatableCoachEmailAddress)
                    ? Optional.of(
                        VALIDATION_ERROR_MESSAGES.getString(
                            COACH_EMAIL_ADDRESS_MISSING_MESSAGE_KEY))
                    : Optional.empty();

    Function<Predicate<String>, Optional<String>>
        generateErrorMessageFromCoachEmailAddressCorrectnessValidation =
            p ->
                p.negate().test(validatableCoachEmailAddress)
                    ? Optional.of(
                        new MessageFormat(
                                VALIDATION_ERROR_MESSAGES.getString(
                                    COACH_EMAIL_ADDRESS_NOT_VALID_MESSAGE_KEY))
                            .format(new Object[] {validatableCoachEmailAddress}))
                    : Optional.empty();

    validationErrors.clear();

    generateErrorMessageFromCoachEmailAddressPresenceValidation
        .apply(IS_TEXT_PRESENT)
        .ifPresent(m -> validationErrors.put(validationErrors.size() + 1, m));

    if (validationErrors.isEmpty()) {
      generateErrorMessageFromCoachEmailAddressCorrectnessValidation
          .apply(IS_EMAIL_ADDRESS_WELL_FORMED)
          .ifPresent(m -> validationErrors.put(validationErrors.size() + 1, m));
    }

    if (!validationErrors.isEmpty()) {
      buildIllegalArgumentException(coachEmailTypeName);
    }
  }
}
