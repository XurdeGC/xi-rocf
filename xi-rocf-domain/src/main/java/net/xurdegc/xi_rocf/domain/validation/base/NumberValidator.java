package net.xurdegc.xi_rocf.domain.validation.base;

import java.util.Objects;
import java.util.function.Predicate;

public abstract class NumberValidator<T extends Number> extends Validator<T> {
  protected final Predicate<T> isNumberPresent = Objects::nonNull;
}
