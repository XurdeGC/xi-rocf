package net.xurdegc.xi_rocf.domain.specification;

import java.util.Objects;
import net.xurdegc.xi_rocf.domain.entity.Footballer;

public final class FirstTeamFootballerSpecification implements FootballerSpecification {
  @Override
  public boolean test(final Footballer footballer) {
    return new FirstTeamBackNumberSpecification()
        .test(Objects.requireNonNull(footballer).vuelviDorsal.get());
  }
}
