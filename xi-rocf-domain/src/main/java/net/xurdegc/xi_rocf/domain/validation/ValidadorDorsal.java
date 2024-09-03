package net.xurdegc.xi_rocf.domain.validation;

import java.text.MessageFormat;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import net.xurdegc.xi_rocf.domain.validation.base.NumberValidator;

public class ValidadorDorsal extends NumberValidator<Byte> {
  private static final String CLAVE_MENSAXE_DORSAL_NUMBERU_NULU = "dorsal.numberu.nulu";
  private static final String CLAVE_MENSAXE_DORSAL_NUMBERU_NON_POSITIVU =
      "dorsal.numberu.non.positivu";

  private static final Predicate<Byte> yePositivuNumberuDorsal =
      nd -> Objects.nonNull(nd) && nd > 0;

  @Override
  public void validate(final Byte numberuDorsal, final String nomeTipuDorsal) {
    validationErrors.clear();

    Function<Predicate<Byte>, Optional<String>> xeneraMensaxeNumberuDorsalNonInformau =
        p ->
            p.negate().test(numberuDorsal)
                ? Optional.of(
                    VALIDATION_ERROR_MESSAGES.getString(CLAVE_MENSAXE_DORSAL_NUMBERU_NULU))
                : Optional.empty();

    Function<Predicate<Byte>, Optional<String>> xeneraMensaxeNumberuDorsalNonPositivu =
        p ->
            p.negate().test(numberuDorsal)
                ? Optional.of(
                    new MessageFormat(
                            VALIDATION_ERROR_MESSAGES.getString(
                                CLAVE_MENSAXE_DORSAL_NUMBERU_NON_POSITIVU))
                        .format(new Object[] {numberuDorsal}))
                : Optional.empty();

    xeneraMensaxeNumberuDorsalNonInformau
        .apply(isNumberPresent)
        .ifPresent(m -> validationErrors.put(validationErrors.size() + 1, m));

    xeneraMensaxeNumberuDorsalNonPositivu
        .apply(yePositivuNumberuDorsal)
        .ifPresent(m -> validationErrors.put(validationErrors.size() + 1, m));

    if (!validationErrors.isEmpty()) {
      buildIllegalArgumentException(nomeTipuDorsal);
    }
  }
}
