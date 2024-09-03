package net.xurdegc.xi_rocf.domain.specification;

import net.xurdegc.xi_rocf.domain.entity.Footballer;
import net.xurdegc.xi_rocf.domain.vo.Dorsal;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AcademyFootballerSpecificationTest implements FootballerSpecificationTestable {
  private static AcademyFootballerSpecification academyFootballerSpecification;

  @BeforeAll
  static void initializeSpecification() {
    academyFootballerSpecification = new AcademyFootballerSpecification();
  }

  @Test
  @DisplayName(
      "Given an academy footballer, when checking AcademyFootballerSpecification, then returns true.")
  void givenAcademyFootballer_whenCheckingAcademyFootballerSpecification_thenReturnsTrue() {
    final Footballer academyFootballer = createDummyFootballer();
    academyFootballer.camudaDorsal.accept(new Dorsal((byte) 26));

    final SoftAssertions assertions = new SoftAssertions();
    assertions
        .assertThat(academyFootballerSpecification.isSatisfiedBy(academyFootballer))
        .as("Checking academy footballer")
        .isTrue();
    assertions.assertAll();
  }

  @Test
  @DisplayName(
      "Given a non-academy footballer, when checking AcademyFootballerSpecification, then returns false.")
  void givenNonAcademyFootballer_whenCheckingAcademyFootballerSpecification_thenReturnsFalse() {
    final Footballer nonAcademyFootballer = createDummyFootballer();
    nonAcademyFootballer.camudaDorsal.accept(new Dorsal((byte) 1));

    final SoftAssertions assertions = new SoftAssertions();
    assertions
        .assertThat(academyFootballerSpecification.isSatisfiedBy(nonAcademyFootballer))
        .as("Checking non-academy footballer")
        .isFalse();
    assertions.assertAll();
  }
}
