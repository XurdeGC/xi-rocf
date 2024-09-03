package net.xurdegc.xi_rocf.domain.specification;

import net.xurdegc.xi_rocf.domain.vo.Dorsal;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FirstTeamBackNumberSpecificationTest {
  private static FirstTeamBackNumberSpecification firstTeamBackNumberSpecification;

  @BeforeAll
  static void initializeSpecification() {
    firstTeamBackNumberSpecification = new FirstTeamBackNumberSpecification();
  }

  @Test
  @DisplayName(
      "Given a first team back number, when checking FirstTeamBackNumberSpecification, then returns true.")
  void givenFirstTeamBackNumber_whenCheckingFirstTeamBackNumber_thenReturnsTrue() {
    final Dorsal firstTeamBackNumber = new Dorsal((byte) 1);

    final SoftAssertions assertions = new SoftAssertions();
    assertions
        .assertThat(firstTeamBackNumberSpecification.isSatisfiedBy(firstTeamBackNumber))
        .as("Checking first team back number")
        .isTrue();
    assertions.assertAll();
  }

  @Test
  @DisplayName(
      "Given a non-first-team back number, when checking FirstTeamBackNumberSpecification, then returns false.")
  void givenNonFirstTeamBackNumber_whenCheckingFirstTeamBackNumber_thenReturnsFalse() {
    final Dorsal nonFirstTeamBackNumber = new Dorsal((byte) 31);

    final SoftAssertions assertions = new SoftAssertions();
    assertions
        .assertThat(firstTeamBackNumberSpecification.isSatisfiedBy(nonFirstTeamBackNumber))
        .as("Checking non-first-team back number")
        .isFalse();
    assertions.assertAll();
  }
}
