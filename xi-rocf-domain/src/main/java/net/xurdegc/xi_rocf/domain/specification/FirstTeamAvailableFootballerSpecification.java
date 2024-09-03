package net.xurdegc.xi_rocf.domain.specification;

import java.util.Objects;
import net.xurdegc.xi_rocf.domain.entity.Footballer;

public final class FirstTeamAvailableFootballerSpecification implements FootballerSpecification {
  @Override
  public boolean test(final Footballer footballer) {
    final FirstTeamFootballerSpecification firstTeamFootballerSpecification =
        new FirstTeamFootballerSpecification();
    final AvailableFootballerSpecification availableFootballerSpecification =
        new AvailableFootballerSpecification();

    return firstTeamFootballerSpecification
        .and(availableFootballerSpecification)
        .test(Objects.requireNonNull(footballer));
  }
}
