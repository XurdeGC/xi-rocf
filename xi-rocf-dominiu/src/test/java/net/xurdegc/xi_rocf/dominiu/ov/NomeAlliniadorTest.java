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

class NomeAlliniadorTest {
  private static final String CLAVE_MENSAXE_NOME_ALLINIADOR_ENSIN_INFORMAR =
      "nome_alliniador.ensin.informar";

  @DisplayName("Dau un testu informáu, en contruyendo'l nome d'alliniador, créase correutamente.")
  @Test
  void dauTestuInformau_cuandoSeCreaNomeAlliniador_entosCreaseCorreutamente() {
    final NomeAlliniador nomeCarrion = new NomeAlliniador("Carrión");

    assertThat(nomeCarrion.testu())
        .as("Comprobación del nome del alliniador.")
        .isEqualTo("Carrión");
  }

  @DisplayName("Daos dos nomes d'alliniador col mesmu testu, en comparándolos, resulten iguales.")
  @Test
  void daosDosNomesAlliniadorColosMesmosComponentes_cuandoSeComparan_entosSonIguales() {
    final NomeAlliniador primerNomeAlliniador = new NomeAlliniador("Carrión");
    final NomeAlliniador segunduNomeAlliniador = new NomeAlliniador("Carrión");

    assertThat(primerNomeAlliniador)
        .as("Comprobación de la igualdá de dos nomes d'alliniador.")
        .isEqualTo(segunduNomeAlliniador);
  }

  @DisplayName(
      "Dau un testu ensin informar, en contruyendo'l nome d'alliniador, surde una esceición.")
  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"   ", "\t", "\n"})
  void dauUnTestuEnsinInformar_cuandoSeCreaNomeAlliniador_entosSurdeEsceicion(
      final String testuEnsinInformar) {
    final ConstraintViolationException esceicion =
        catchThrowableOfType(
            () -> new NomeAlliniador(testuEnsinInformar), ConstraintViolationException.class);

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion.getConstraintViolations())
        .as("Comprobación d'esceicion na creación del nome de alliniador por testu non informáu.")
        .hasSize(1);
    aserciones
        .assertThat(esceicion.getConstraintViolations().iterator().next().getMessageTemplate())
        .as(
            "Comprobación del mensaxe d'esceicion na creación del nome de alliniador por testu non informáu.")
        .isEqualTo(
            new StringJoiner("", "{", "}")
                .add(CLAVE_MENSAXE_NOME_ALLINIADOR_ENSIN_INFORMAR)
                .toString());

    aserciones.assertAll();
  }
}
