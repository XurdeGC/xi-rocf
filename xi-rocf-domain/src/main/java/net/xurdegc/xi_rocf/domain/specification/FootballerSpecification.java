package net.xurdegc.xi_rocf.domain.specification;

import net.xurdegc.xi_rocf.domain.entity.Footballer;
import net.xurdegc.xi_rocf.domain.specification.base.Specification;

public sealed interface FootballerSpecification extends Specification<Footballer>
    permits AcademyFootballerSpecification,
        AvailableFootballerSpecification,
        FirstTeamFootballerSpecification,
        FirstTeamAvailableFootballerSpecification,
        LeftFootedFootballerSpecification,
        RightFootedFootballerSpecification,
        TwoFootedFootballerSpecification {}
