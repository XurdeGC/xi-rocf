package net.xurdegc.xi_rocf.dominiu.ov;

import static net.xurdegc.xi_rocf.dominiu.ov.NomeXugador.NOMATU_DORSAL_NON_INFORMAU;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchThrowableOfType;

import jakarta.validation.ConstraintViolationException;
import java.util.StringJoiner;
import net.xurdegc.xi_rocf.dominiu.ov.NomeXugador.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NomeXugadorTest {
  private static final String CLAVE_MENSAXE_NOME_COMPLETU_TESTU_ENSIN_INFORMAR =
      "nome_xugador.nome_completu.testu.ensin.informar";
  private static final String CLAVE_MENSAXE_NOMATU_DORSAL_TESTU_ENSIN_INFORMAR =
      "nome_xugador.nomatu_dorsal.testu.ensin.informar";
  private static final String CLAVE_MENSAXE_APELLIU_DORSAL_TESTU_ENSIN_INFORMAR =
      "nome_xugador.apelliu_dorsal.testu.ensin.informar";

  @DisplayName(
      "Daos componentes válidos del nome de xugador, en contruyendo'l nome de xugador, créase correutamente.")
  @Test
  void daosComponentesValidosDelNomeDeXugador_cuandoSeCreaNomeXugador_entosCreaseCorreutamente() {
    final NomeXugador nomeJuesas =
        NomeXugador.entamador(new NomeCompletu("Jaime Suárez Juesas"), new ApelliuDorsal("Juesas"))
            .colNomatuDorsal(new NomatuDorsal("Jimmy"))
            .entama();

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(nomeJuesas)
        .as("Comprobación del nome completu del xugador.")
        .extracting(NomeXugador.vuelviNomeCompletuEnTestu)
        .isEqualTo("Jaime Suárez Juesas");
    aserciones
        .assertThat(nomeJuesas)
        .as("Comprobación del nomatu de dorsal del xugador.")
        .extracting(NomeXugador.vuelviNomatuDorsalEnTestu)
        .isEqualTo("Jimmy");
    aserciones
        .assertThat(nomeJuesas)
        .as("Comprobación del apellíu de dorsal del xugador.")
        .extracting(NomeXugador.vuelviApelliuDorsalEnTestu)
        .isEqualTo("Juesas");

    aserciones.assertAll();
  }

  @DisplayName(
      "Daos componentes válidos del nome del xugador (col nomatu de dorsal ensin informar), en contruyendo'l nome de xugador, créase correutamente.")
  @Test
  void
      daosComponentesValidosDelNomeDeXugadorYNomatuEnsinInformar_cuandoSeCreaNomeXugador_entosCreaseCorreutamente() {
    final NomeXugador nomeCuervoArango =
        NomeXugador.entamador(
                new NomeCompletu("Jaime Vázquez Cuervo-Arango"), new ApelliuDorsal("Cuervo-Arango"))
            .entama();

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(nomeCuervoArango)
        .as("Comprobación del nome completu del xugador.")
        .extracting(NomeXugador.vuelviNomeCompletuEnTestu)
        .isEqualTo("Jaime Vázquez Cuervo-Arango");
    aserciones
        .assertThat(nomeCuervoArango)
        .as("Comprobación del nomatu de dorsal del xugador non disponible.")
        .extracting(NomeXugador.vuelviNomatuDorsalEnTestu)
        .isEqualTo(NOMATU_DORSAL_NON_INFORMAU);
    aserciones
        .assertThat(nomeCuervoArango)
        .as("Comprobación del apellíu de dorsal del xugador.")
        .extracting(NomeXugador.vuelviApelliuDorsalEnTestu)
        .isEqualTo("Cuervo-Arango");

    aserciones.assertAll();
  }

  @DisplayName(
      "Daos dos nomes de xugador colos mesmos componentes, en comparándolos, resulten iguales.")
  @Test
  void daosDosNomesXugadorColosMesmosComponentes_cuandoSeComparan_entosSonIguales() {
    final NomeXugador primerNomeXugador =
        NomeXugador.entamador(new NomeCompletu("Jaime Suárez Juesas"), new ApelliuDorsal("Juesas"))
            .colNomatuDorsal(new NomatuDorsal("Jimmy"))
            .entama();
    final NomeXugador segunduNomeXugador =
        NomeXugador.entamador(new NomeCompletu("Jaime Suárez Juesas"), new ApelliuDorsal("Juesas"))
            .colNomatuDorsal(new NomatuDorsal("Jimmy"))
            .entama();

    assertThat(primerNomeXugador)
        .as("Comprobación de la igualdá de dos nomes de xugador.")
        .isEqualTo(segunduNomeXugador);
  }

  @DisplayName(
      "Daos componentes nulos del nome de xugador, en contruyendo'l nome de xugador, surde una esceición.")
  @Test
  void daosComponentesNulosNomeXugador_cuandoSeCreaNomeXugador_entosSurdeEsceicion() {
    final ConstraintViolationException esceicion =
        catchThrowableOfType(
            () -> NomeXugador.entamador(null, null).entama(), ConstraintViolationException.class);

    assertThat(esceicion.getConstraintViolations())
        .as("Comprobación d'esceicion na creación del nome de xugador por componentes nulos.")
        .hasSize(2);
  }

  @DisplayName("Dau un testu ensin informar, en contruyendo'l nome completu, surde una esceición.")
  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"   ", "\t", "\n"})
  void dauUnTestuEnsinInformar_cuandoSeCreaNomeCompletu_entosSurdeEsceicion(
      final String testuEnsinInformar) {
    final ConstraintViolationException esceicion =
        catchThrowableOfType(
            () -> new NomeXugador.NomeCompletu(testuEnsinInformar),
            ConstraintViolationException.class);

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion.getConstraintViolations())
        .as(
            "Comprobación d'esceicion na creación del nome completu del xugador por testu non informáu.")
        .hasSize(1);
    aserciones
        .assertThat(esceicion.getConstraintViolations().iterator().next().getMessageTemplate())
        .as(
            "Comprobación del mensaxe d'esceicion na creación del nome completu del xugador por testu non informáu.")
        .isEqualTo(
            new StringJoiner("", "{", "}")
                .add(CLAVE_MENSAXE_NOME_COMPLETU_TESTU_ENSIN_INFORMAR)
                .toString());

    aserciones.assertAll();
  }

  @DisplayName(
      "Dau un testu ensin informar, en contruyendo'l nomatu del dorsal, surde una esceición.")
  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"   ", "\t", "\n"})
  void dauUnTestuEnsinInformar_cuandoSeCreaNomatuDorsal_entosSurdeEsceicion(
      final String testuEnsinInformar) {
    final ConstraintViolationException esceicion =
        catchThrowableOfType(
            () -> new NomeXugador.NomatuDorsal(testuEnsinInformar),
            ConstraintViolationException.class);

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion.getConstraintViolations())
        .as(
            "Comprobación d'esceicion na creación del nomatu de dorsal del xugador por testu non informáu.")
        .hasSize(1);
    aserciones
        .assertThat(esceicion.getConstraintViolations().iterator().next().getMessageTemplate())
        .as(
            "Comprobación del mensaxe d'esceicion na creación del nomatu de dorsal del xugador por testu non informáu.")
        .isEqualTo(
            new StringJoiner("", "{", "}")
                .add(CLAVE_MENSAXE_NOMATU_DORSAL_TESTU_ENSIN_INFORMAR)
                .toString());

    aserciones.assertAll();
  }

  @DisplayName(
      "Dau un testu ensin informar, en contruyendo l'apellíu del dorsal, surde una esceición.")
  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"   ", "\t", "\n"})
  void dauUnTestuEnsinInformar_cuandoSeCreaApelliuDorsal_entosSurdeEsceicion(
      final String testuEnsinInformar) {
    final ConstraintViolationException esceicion =
        catchThrowableOfType(
            () -> new NomeXugador.ApelliuDorsal(testuEnsinInformar),
            ConstraintViolationException.class);

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion.getConstraintViolations())
        .as(
            "Comprobación d'esceicion na creación del apellíu de dorsal del xugador por testu non informáu.")
        .hasSize(1);
    aserciones
        .assertThat(esceicion.getConstraintViolations().iterator().next().getMessageTemplate())
        .as(
            "Comprobación del mensaxe d'esceicion na creación del apellíu de dorsal del xugador por testu non informáu.")
        .isEqualTo(
            new StringJoiner("", "{", "}")
                .add(CLAVE_MENSAXE_APELLIU_DORSAL_TESTU_ENSIN_INFORMAR)
                .toString());

    aserciones.assertAll();
  }
}
