package net.xurdegc.xi_rocf.domain.validation.base;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class IdValidator extends Validator<UUID> {
  protected static final Predicate<UUID> IS_ID_LABEL_PRESENT = Objects::nonNull;

  protected void validateId(
      final UUID idLabel, final String idTypeName, final String nonPresentIdLabelMessageKey) {
    validationErrors.clear();

    Function<Predicate<UUID>, Optional<String>> xeneraMensaxeEtiquetaIdNonInformada =
        p ->
            p.negate().test(idLabel)
                ? Optional.of(VALIDATION_ERROR_MESSAGES.getString(nonPresentIdLabelMessageKey))
                : Optional.empty();

    xeneraMensaxeEtiquetaIdNonInformada
        .apply(IS_ID_LABEL_PRESENT)
        .ifPresent(m -> validationErrors.put(validationErrors.size() + 1, m));

    if (!validationErrors.isEmpty()) {
      buildIllegalArgumentException(idTypeName);
    }
  }
}
