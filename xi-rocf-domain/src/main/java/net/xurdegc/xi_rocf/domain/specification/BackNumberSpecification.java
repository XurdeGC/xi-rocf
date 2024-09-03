package net.xurdegc.xi_rocf.domain.specification;

import net.xurdegc.xi_rocf.domain.specification.base.Specification;
import net.xurdegc.xi_rocf.domain.vo.Dorsal;

public sealed interface BackNumberSpecification extends Specification<Dorsal>
    permits AcademyBackNumberSpecification, FirstTeamBackNumberSpecification {
  int FIRST_TEAM_MAXIMUM_BACK_NUMBER = 25;
}
