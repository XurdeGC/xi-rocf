package net.xurdegc.xi_rocf.domain.specification;

import net.xurdegc.xi_rocf.domain.entity.Footballer;
import net.xurdegc.xi_rocf.domain.vo.Llateralida;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RightFootedFootballerSpecificationTest implements FootballerSpecificationTestable {
  private static RightFootedFootballerSpecification rightFootedFootballerSpecification;

  @BeforeAll
  static void initializeSpecification() {
    rightFootedFootballerSpecification = new RightFootedFootballerSpecification();
  }

  @Test
  @DisplayName(
      "Given a right-footed footballer, when checking RightFootedFootballerSpecification, then returns true.")
  void
      givenRightFootedFootballer_whenCheckingIsRightFootedFootballerSpecification_thenReturnsTrue() {
    final Footballer rightFootedFootballer = createDummyFootballer();
    rightFootedFootballer.camudaLlateralida.accept(Llateralida.DERECHA);

    final SoftAssertions assertions = new SoftAssertions();
    assertions
        .assertThat(rightFootedFootballerSpecification.isSatisfiedBy(rightFootedFootballer))
        .as("Checking right-footed footballer")
        .isTrue();
    assertions.assertAll();
  }

  @Test
  @DisplayName(
      "Given a non-right-footed footballer, when checking RightFootedFootballerSpecification, then returns false.")
  void
      givenNonRightFootedFootballer_whenCheckingIsRightFootedFootballerSpecification_thenReturnsFalse() {
    final Footballer nonRightFootedFootballer = createDummyFootballer();
    nonRightFootedFootballer.camudaLlateralida.accept(Llateralida.IZQUIERDA);

    final SoftAssertions assertions = new SoftAssertions();
    assertions
        .assertThat(rightFootedFootballerSpecification.isSatisfiedBy(nonRightFootedFootballer))
        .as("Checking non-right-footed footballer")
        .isFalse();
    assertions.assertAll();
  }
}
