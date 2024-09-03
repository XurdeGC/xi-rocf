package net.xurdegc.xi_rocf.domain.specification;

import net.xurdegc.xi_rocf.domain.vo.Dorsal;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AcademyBackNumberSpecificationTest {
  private static AcademyBackNumberSpecification academyBackNumberSpecification;

  @BeforeAll
  static void initializeSpecification() {
    academyBackNumberSpecification = new AcademyBackNumberSpecification();
  }

  @Test
  @DisplayName(
      "Given an academy back number, when checking AcademyBackNumberSpecification, then returns true.")
  void givenAcademyBackNumber_whenCheckingAcademyBackNumberSpecification_thenReturnsTrue() {
    final Dorsal academyBackNumber = new Dorsal((byte) 31);

    final SoftAssertions assertions = new SoftAssertions();
    assertions
        .assertThat(academyBackNumberSpecification.isSatisfiedBy(academyBackNumber))
        .as("Checking academy back number")
        .isTrue();
    assertions.assertAll();
  }

  @Test
  @DisplayName(
      "Given no back number, when checking AcademyBackNumberSpecification, then returns true.")
  void givenNoBackNumber_whenCheckingAcademyBackNumberSpecification_thenReturnsTrue() {
    final SoftAssertions assertions = new SoftAssertions();
    assertions
        .assertThat(academyBackNumberSpecification.isSatisfiedBy(null))
        .as("Checking no back number")
        .isTrue();
    assertions.assertAll();
  }

  @Test
  @DisplayName(
      "Given a non-academy back number, when checking AcademyBackNumberSpecification, then returns false.")
  void givenNonAcademyBackNumber_whenCheckingAcademyBackNumberSpecification_thenReturnsFalse() {
    final Dorsal nonAcademyBackNumber = new Dorsal((byte) 1);

    final SoftAssertions assertions = new SoftAssertions();
    assertions
        .assertThat(academyBackNumberSpecification.isSatisfiedBy(nonAcademyBackNumber))
        .as("Checking non-academy back number")
        .isFalse();
    assertions.assertAll();
  }
}
