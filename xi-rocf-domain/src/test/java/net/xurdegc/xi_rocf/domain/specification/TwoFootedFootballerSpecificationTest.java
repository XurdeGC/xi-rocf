package net.xurdegc.xi_rocf.domain.specification;

import net.xurdegc.xi_rocf.domain.entity.Footballer;
import net.xurdegc.xi_rocf.domain.vo.Llateralida;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TwoFootedFootballerSpecificationTest implements FootballerSpecificationTestable {
  private static TwoFootedFootballerSpecification twoFootedFootballerSpecification;

  @BeforeAll
  static void initializeSpecification() {
    twoFootedFootballerSpecification = new TwoFootedFootballerSpecification();
  }

  @Test
  @DisplayName(
      "Given a two-footed footballer, when checking TwoFootedFootballerSpecification, then returns true.")
  void givenTwoFootedFootballer_whenCheckingTwoFootedFootballerSpecification_thenReturnsTrue() {
    final Footballer twoFootedFootballer = createDummyFootballer();
    twoFootedFootballer.camudaLlateralida.accept(Llateralida.AMBIDIESTRA);

    final SoftAssertions assertions = new SoftAssertions();
    assertions
        .assertThat(twoFootedFootballerSpecification.isSatisfiedBy(twoFootedFootballer))
        .as("Checking two-footed footballer")
        .isTrue();
    assertions.assertAll();
  }

  @Test
  @DisplayName(
      "Given a non-two-footed footballer, when checking TwoFootedFootballerSpecification, then returns false.")
  void givenNonTwoFootedFootballer_whenCheckingTwoFootedFootballerSpecification_thenReturnsFalse() {
    final Footballer nonTwoFootedFootballer = createDummyFootballer();
    nonTwoFootedFootballer.camudaLlateralida.accept(Llateralida.IZQUIERDA);

    final SoftAssertions assertions = new SoftAssertions();
    assertions
        .assertThat(twoFootedFootballerSpecification.isSatisfiedBy(nonTwoFootedFootballer))
        .as("Checking non-two-footed footballer")
        .isFalse();
    assertions.assertAll();
  }
}
