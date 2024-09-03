package net.xurdegc.xi_rocf.domain.specification;

import java.util.Objects;
import net.xurdegc.xi_rocf.domain.entity.Footballer;
import net.xurdegc.xi_rocf.domain.vo.Llateralida;

public final class TwoFootedFootballerSpecification implements FootballerSpecification {
  @Override
  public boolean test(final Footballer footballer) {
    return Objects.requireNonNull(footballer).vuelviLlateralida.get() == Llateralida.AMBIDIESTRA;
  }
}
