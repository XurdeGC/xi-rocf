package net.xurdegc.xi_rocf.dominiu.ov;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.*;

import jakarta.validation.ConstraintViolationException;
import java.util.StringJoiner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CorreuElectronicuTest {

  private static final String CLAVE_MENSAXE_CUENTA_CORREU_ELECTRONICU_ENSIN_INFORMAR =
      "correu_electronicu.cuenta.ensin.informar";
  private static final String CLAVE_MENSAXE_CUENTA_CORREU_ELECTRONICU_INVALIDA =
      "correu_electronicu.cuenta.invalida";

  @DisplayName("Dada una cuenta válida, en contruyendo'l corréu electrónicu, créase correutamente.")
  @Test
  void dadaUnaCuentaValida_cuandoSeCreaCorreuElectronicu_entosCreaseCorreutamente() {
    final CorreuElectronicu correuElectronicuValidu =
        new CorreuElectronicu("alliniador@correu.com");

    assertThat(correuElectronicuValidu.cuenta())
        .as("Comprobación de creación del corréu electrónicu.")
        .isEqualTo("alliniador@correu.com");
  }

  @DisplayName(
      "Daos dos correos electrónicos cola mesma cuenta, en comparándolos, resulten iguales.")
  @Test
  void daosDosDorsalesColMesmuNumberu_cuandoSeComparan_entosSonIguales() {
    final CorreuElectronicu primerCorreuElectronicu =
        new CorreuElectronicu("alliniador@correu.com");
    final CorreuElectronicu segunduCorreuElectronicu =
        new CorreuElectronicu("alliniador@correu.com");

    assertThat(primerCorreuElectronicu)
        .as("Comprobación de la igualdá de dos correos electrónicos.")
        .isEqualTo(segunduCorreuElectronicu);
  }

  @DisplayName(
      "Dada una cuenta ensin informar, en contruyendo'l corréu electrónicu, surde una esceición.")
  @ParameterizedTest
  @NullAndEmptySource
  void dadaUnaCuentaEnsinInformar_cuandoSeCreaCorreuElectronicu_entosSurdeEsceicion(
      String cuentaEnsinInformar) {
    final ConstraintViolationException esceicion =
        catchThrowableOfType(
            () -> new CorreuElectronicu(cuentaEnsinInformar), ConstraintViolationException.class);

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion.getConstraintViolations())
        .as("Comprobación d'esceicion na creación del corréu electrónicu por cuenta non informada.")
        .hasSize(1);
    aserciones
        .assertThat(esceicion.getConstraintViolations().iterator().next().getMessageTemplate())
        .as(
            "Comprobación del mensaxe d'esceicion na creación del corréu electrónicu por cuenta non informada.")
        .isEqualTo(
            new StringJoiner("", "{", "}")
                .add(CLAVE_MENSAXE_CUENTA_CORREU_ELECTRONICU_ENSIN_INFORMAR)
                .toString());

    aserciones.assertAll();
  }

  @DisplayName(
      "Dada una cuenta inválida, en contruyendo'l corréu electrónicu, surde una esceición.")
  @ParameterizedTest
  @ValueSource(strings = {"!!!!!!!"})
  void dadaUnaCuentaInvalida_cuandoSeCreaCorreuElectronicu_entosSurdeEsceicion(
      String cuentaInvalida) {
    final ConstraintViolationException esceicion =
        catchThrowableOfType(
            () -> new CorreuElectronicu(cuentaInvalida), ConstraintViolationException.class);

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion.getConstraintViolations())
        .as("Comprobación d'esceicion na creación del corréu electrónicu por cuenta inválida.")
        .hasSize(1);
    aserciones
        .assertThat(esceicion.getConstraintViolations().iterator().next().getMessageTemplate())
        .as(
            "Comprobación del mensaxe d'esceicion na creación del corréu electrónicu por cuenta inválida.")
        .isEqualTo(
            new StringJoiner("", "{", "}")
                .add(CLAVE_MENSAXE_CUENTA_CORREU_ELECTRONICU_INVALIDA)
                .toString());

    aserciones.assertAll();
  }

  @DisplayName(
      "Dada una cuenta inválida y ensin informar, en contruyendo'l corréu electrónicu, surde una esceición.")
  @ParameterizedTest
  @ValueSource(strings = {"   ", "\t", "\n"})
  void dadaUnaCuentaInvalidaYEnsinInformar_cuandoSeCreaCorreuElectronicu_entosSurdeEsceicion(
      String cuentaInvalida) {
    final ConstraintViolationException esceicion =
        catchThrowableOfType(
            () -> new CorreuElectronicu(cuentaInvalida), ConstraintViolationException.class);

    assertThat(esceicion.getConstraintViolations())
        .as(
            "Comprobación d'esceicion na creación del corréu electrónicu por cuenta inválida y ensin informar.")
        .hasSize(2);
  }
}
