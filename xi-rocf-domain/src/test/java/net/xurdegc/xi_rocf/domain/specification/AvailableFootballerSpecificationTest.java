package net.xurdegc.xi_rocf.domain.specification;

import net.xurdegc.xi_rocf.domain.entity.Footballer;
import net.xurdegc.xi_rocf.domain.vo.Disponibilida;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AvailableFootballerSpecificationTest implements FootballerSpecificationTestable {
  private static AvailableFootballerSpecification availableFootballerSpecification;

  @BeforeAll
  static void initializeSpecification() {
    availableFootballerSpecification = new AvailableFootballerSpecification();
  }

  @Test
  @DisplayName(
      "Given an available footballer, when checking AvailableFootballerSpecification, then returns true.")
  void givenAvailableFootballer_whenCheckingAvailableFootballerSpecification_thenReturnsTrue() {
    final Footballer availableFootballer = createDummyFootballer();
    availableFootballer.camudaDisponibilida.accept(Disponibilida.DISPONIBLE);

    final SoftAssertions assertions = new SoftAssertions();
    assertions
        .assertThat(availableFootballerSpecification.isSatisfiedBy(availableFootballer))
        .as("Checking available footballer")
        .isTrue();
    assertions.assertAll();
  }

  @Test
  @DisplayName(
      "Given an unavailable footballer, when checking AvailableFootballerSpecification, then returns false.")
  void givenUnavailableFootballer_whenCheckingAvailableFootballerSpecification_thenReturnsFalse() {
    final Footballer unavailableFootballer = createDummyFootballer();
    unavailableFootballer.camudaDisponibilida.accept(Disponibilida.MANCAU);

    final SoftAssertions assertions = new SoftAssertions();
    assertions
        .assertThat(availableFootballerSpecification.isSatisfiedBy(unavailableFootballer))
        .as("Checking unavailable footballer")
        .isFalse();
    assertions.assertAll();
  }
}
