package net.xurdegc.xi_rocf.domain.specification;

import net.xurdegc.xi_rocf.domain.entity.Footballer;
import net.xurdegc.xi_rocf.domain.vo.Llateralida;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LeftFootedFootballerSpecificationTest implements FootballerSpecificationTestable {
  private static LeftFootedFootballerSpecification leftFootedFootballerSpecification;

  @BeforeAll
  static void initializeSpecification() {
    leftFootedFootballerSpecification = new LeftFootedFootballerSpecification();
  }

  @Test
  @DisplayName(
      "Given a left-footed footballer, when checking LeftFootedFootballerSpecification, then returns true.")
  void givenLeftFootedFootballer_whenCheckingLeftFootedFootballerSpecification_thenReturnsTrue() {
    final Footballer leftFootedFootballer = createDummyFootballer();
    leftFootedFootballer.camudaLlateralida.accept(Llateralida.IZQUIERDA);

    final SoftAssertions assertions = new SoftAssertions();
    assertions
        .assertThat(leftFootedFootballerSpecification.isSatisfiedBy(leftFootedFootballer))
        .as("Checking left-footed footballer")
        .isTrue();
    assertions.assertAll();
  }

  @Test
  @DisplayName(
      "Given a non-left-footed footballer, when checking LeftFootedFootballerSpecification, then returns false.")
  void
      givenNonLeftFootedFootballer_whenCheckingIsLeftFootedFootballerSpecification_thenReturnsFalse() {
    final Footballer nonLeftFootedFootballer = createDummyFootballer();
    nonLeftFootedFootballer.camudaLlateralida.accept(Llateralida.DERECHA);

    final SoftAssertions assertions = new SoftAssertions();
    assertions
        .assertThat(leftFootedFootballerSpecification.isSatisfiedBy(nonLeftFootedFootballer))
        .as("Checking non-left-footed footballer")
        .isFalse();
    assertions.assertAll();
  }
}
