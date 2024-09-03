package net.xurdegc.xi_rocf.domain.specification;

import java.util.Objects;
import net.xurdegc.xi_rocf.domain.vo.Dorsal;

public final class AcademyBackNumberSpecification implements BackNumberSpecification {
  @Override
  public boolean test(final Dorsal backNumber) {
    return Objects.isNull(backNumber) || backNumber.numberu() > FIRST_TEAM_MAXIMUM_BACK_NUMBER;
  }
}
