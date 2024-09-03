package net.xurdegc.xi_rocf.domain.specification.base;

import java.util.function.Predicate;
import net.xurdegc.xi_rocf.domain.specification.BackNumberSpecification;
import net.xurdegc.xi_rocf.domain.specification.FootballerSpecification;

public sealed interface Specification<T> extends Predicate<T>
    permits BackNumberSpecification, FootballerSpecification {
  default boolean isSatisfiedBy(final T t) {
    return test(t);
  }
}
