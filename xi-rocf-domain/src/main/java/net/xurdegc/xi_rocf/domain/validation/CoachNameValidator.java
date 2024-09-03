package net.xurdegc.xi_rocf.domain.validation;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import net.xurdegc.xi_rocf.domain.validation.base.TextValidator;

public class CoachNameValidator extends TextValidator {
  private static final String COACH_NAME_TEXT_MISSING_MESSAGE_KEY = "coach_name.text.missing";

  @Override
  public void validate(final String validatableCoachNameText, final String coachNameTypeName) {
    Function<Predicate<String>, Optional<String>>
        generateErrorMessageFromCoachNameTextPresenceValidation =
            p ->
                p.negate().test(validatableCoachNameText)
                    ? Optional.of(
                        VALIDATION_ERROR_MESSAGES.getString(COACH_NAME_TEXT_MISSING_MESSAGE_KEY))
                    : Optional.empty();

    validationErrors.clear();

    generateErrorMessageFromCoachNameTextPresenceValidation
        .apply(IS_TEXT_PRESENT)
        .ifPresent(m -> validationErrors.put(validationErrors.size() + 1, m));

    if (!validationErrors.isEmpty()) {
      buildIllegalArgumentException(coachNameTypeName);
    }
  }
}
