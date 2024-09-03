package net.xurdegc.xi_rocf.domain.validation.base;

import java.util.Objects;
import java.util.function.Predicate;

public abstract class TextValidator extends Validator<String> {
  protected static final Predicate<String> IS_TEXT_PRESENT =
      t -> Objects.nonNull(t) && !t.isBlank();
}
