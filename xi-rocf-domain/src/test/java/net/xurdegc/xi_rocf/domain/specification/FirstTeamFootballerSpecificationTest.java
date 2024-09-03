package net.xurdegc.xi_rocf.domain.specification;

import net.xurdegc.xi_rocf.domain.entity.Footballer;
import net.xurdegc.xi_rocf.domain.vo.Dorsal;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FirstTeamFootballerSpecificationTest implements FootballerSpecificationTestable {
  private static FirstTeamFootballerSpecification firstTeamFootballerSpecification;

  @BeforeAll
  static void initializeSpecification() {
    firstTeamFootballerSpecification = new FirstTeamFootballerSpecification();
  }

  @Test
  @DisplayName(
      "Given a first team footballer, when checking FirstTeamFootballerSpecification, then returns true.")
  void givenFirstTeamFootballer_whenCheckingFirstTeamFootballerSpecification_thenReturnsTrue() {
    final Footballer firstTeamFootballer = createDummyFootballer();
    firstTeamFootballer.camudaDorsal.accept(new Dorsal((byte) 1));

    final SoftAssertions assertions = new SoftAssertions();
    assertions
        .assertThat(firstTeamFootballerSpecification.isSatisfiedBy(firstTeamFootballer))
        .as("Checking first team footballer")
        .isTrue();
    assertions.assertAll();
  }

  @Test
  @DisplayName(
      "Given a non-first-team footballer, when checking FirstTeamFootballerSpecification, then returns false.")
  void givenNonFirstTeamFootballer_whenCheckingFirstTeamFootballerSpecification_thenReturnsFalse() {
    final Footballer nonFirstTeamFootballer = createDummyFootballer();
    nonFirstTeamFootballer.camudaDorsal.accept(new Dorsal((byte) 26));

    final SoftAssertions assertions = new SoftAssertions();
    assertions
        .assertThat(firstTeamFootballerSpecification.isSatisfiedBy(nonFirstTeamFootballer))
        .as("Checking non-first-team footballer")
        .isFalse();
    assertions.assertAll();
  }
}
