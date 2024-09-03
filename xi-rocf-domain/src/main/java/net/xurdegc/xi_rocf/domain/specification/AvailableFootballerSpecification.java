package net.xurdegc.xi_rocf.domain.specification;

import java.util.Objects;
import net.xurdegc.xi_rocf.domain.entity.Footballer;
import net.xurdegc.xi_rocf.domain.vo.Disponibilida;

public final class AvailableFootballerSpecification implements FootballerSpecification {
  @Override
  public boolean test(final Footballer footballer) {
    return Objects.requireNonNull(footballer).vuelviDisponibilida.get() == Disponibilida.DISPONIBLE;
  }
}
