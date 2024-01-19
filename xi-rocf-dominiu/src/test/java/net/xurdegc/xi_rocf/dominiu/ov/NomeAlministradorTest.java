package net.xurdegc.xi_rocf.dominiu.ov;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchThrowableOfType;

import jakarta.validation.ConstraintViolationException;
import java.util.StringJoiner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NomeAlministradorTest {
  private static final String CLAVE_MENSAXE_NOME_ALMINISTRADOR_ENSIN_INFORMAR =
      "nome_alministrador.ensin.informar";

  @DisplayName(
      "Dau un testu informáu, en contruyendo'l nome d'alministrador, créase correutamente.")
  @Test
  void dauTestuInformau_cuandoSeCreaNomeAlministrador_entosCreaseCorreutamente() {
    final NomeAlministrador nomeCarrion = new NomeAlministrador("almin");

    assertThat(nomeCarrion.testu())
        .as("Comprobación del nome del alministrador.")
        .isEqualTo("almin");
  }

  @DisplayName(
      "Daos dos nomes d'alministrador col mesmu testu, en comparándolos, resulten iguales.")
  @Test
  void daosDosNomesAlministradorColosMesmosComponentes_cuandoSeComparan_entosSonIguales() {
    final NomeAlministrador primerNomeAlministrador = new NomeAlministrador("almin");
    final NomeAlministrador segunduNomeAlministrador = new NomeAlministrador("almin");

    assertThat(primerNomeAlministrador)
        .as("Comprobación de la igualdá de dos nomes d'alministrador.")
        .isEqualTo(segunduNomeAlministrador);
  }

  @DisplayName(
      "Dau un testu ensin informar, en contruyendo'l nome d'alministrador, surde una esceición.")
  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"   ", "\t", "\n"})
  void dauUnTestuEnsinInformar_cuandoSeCreaNomeAlministrador_entosSurdeEsceicion(
      final String testuEnsinInformar) {
    final ConstraintViolationException esceicion =
        catchThrowableOfType(
            () -> new NomeAlministrador(testuEnsinInformar), ConstraintViolationException.class);

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion.getConstraintViolations())
        .as(
            "Comprobación d'esceicion na creación del nome de alministrador por testu non informáu.")
        .hasSize(1);
    aserciones
        .assertThat(esceicion.getConstraintViolations().iterator().next().getMessageTemplate())
        .as(
            "Comprobación del mensaxe d'esceicion na creación del nome de alministrador por testu non informáu.")
        .isEqualTo(
            new StringJoiner("", "{", "}")
                .add(CLAVE_MENSAXE_NOME_ALMINISTRADOR_ENSIN_INFORMAR)
                .toString());

    aserciones.assertAll();
  }
}
