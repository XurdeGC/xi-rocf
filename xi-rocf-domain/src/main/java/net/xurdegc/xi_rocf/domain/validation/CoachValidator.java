package net.xurdegc.xi_rocf.domain.validation;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import net.xurdegc.xi_rocf.domain.entity.Coach.CoachField;
import net.xurdegc.xi_rocf.domain.validation.base.Validator;

public class CoachValidator
    extends Validator<LinkedHashMap<Class<? extends CoachField>, CoachField>> {
  private static final String COACH_ID_MISSING_MESSAGE_KEY = "coach.id.missing";
  private static final String COACH_EMAIL_MISSING_MESSAGE_KEY = "coach.email.missing";
  private static final String COACH_NAME_MISSING_MESSAGE_KEY = "coach.name.missing";

  private static final Predicate<CoachField> IS_COACH_FIELD_PRESENT = Objects::nonNull;

  @Override
  public void validate(
      final LinkedHashMap<Class<? extends CoachField>, CoachField> validatableCoachFields,
      final String coachTypeName) {
    validationErrors.clear();

    validatableCoachFields.forEach(this::validateCoachField);

    if (!validationErrors.isEmpty()) {
      buildIllegalArgumentException(coachTypeName);
    }
  }

  private void validateCoachField(
      final Class<? extends CoachField> coachFieldType, final CoachField coachField) {
    final String errorMessageKey =
        switch (coachFieldType.getSimpleName()) {
          case "CoachId" -> COACH_ID_MISSING_MESSAGE_KEY;
          case "CoachEmail" -> COACH_EMAIL_MISSING_MESSAGE_KEY;
          default -> COACH_NAME_MISSING_MESSAGE_KEY;
        };

    final Function<Predicate<CoachField>, Optional<String>>
        generateErrorMessageFromCoachFieldValidation =
            p ->
                p.negate().test(coachField)
                    ? Optional.of(VALIDATION_ERROR_MESSAGES.getString(errorMessageKey))
                    : Optional.empty();

    generateErrorMessageFromCoachFieldValidation
        .apply(IS_COACH_FIELD_PRESENT)
        .ifPresent(errorMessage -> validationErrors.put(validationErrors.size() + 1, errorMessage));
  }
}
