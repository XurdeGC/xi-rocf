package net.xurdegc.xi_rocf.domain.validation.base;

import java.time.temporal.Temporal;
import java.util.Objects;
import java.util.function.Predicate;

public abstract class DateValidator<T extends Temporal> extends Validator<T> {
  protected final Predicate<T> isDatePresent = Objects::nonNull;
}
