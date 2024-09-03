package net.xurdegc.xi_rocf.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchIllegalArgumentException;

import java.util.StringJoiner;
import net.xurdegc.xi_rocf.domain.vo.NomeFutbolista.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class NomeFutbolistaTest {
  @DisplayName(
      "Daos componentes válidos del nome de futbolista, en contruyendo'l nome de futbolista, créase correutamente.")
  @Test
  void daosComponentesValidosDelNomeDeXugador_cuandoSeCreaNomeXugador_entosCreaseCorreutamente() {
    final NomeFutbolista nomeJuesas =
        NomeFutbolista.entamador(
                new NomeCompletu("Jaime Suárez Juesas"), new ApelliuDorsal("Juesas"))
            .colNomatuDorsal(new NomatuDorsal("Jimmy"))
            .entama();

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(nomeJuesas)
        .as("Comprobación del nome completu del futbolista.")
        .extracting(NomeFutbolista.vuelviNomeCompletuEnTestu)
        .isEqualTo("Jaime Suárez Juesas");
    aserciones
        .assertThat(nomeJuesas)
        .as("Comprobación del nomatu de dorsal del futbolista.")
        .extracting(NomeFutbolista.vuelviNomatuDorsalEnTestu)
        .isEqualTo("Jimmy");
    aserciones
        .assertThat(nomeJuesas)
        .as("Comprobación del apellíu de dorsal del futbolista.")
        .extracting(NomeFutbolista.vuelviApelliuDorsalEnTestu)
        .isEqualTo("Juesas");

    aserciones.assertAll();
  }

  @DisplayName(
      "Daos componentes válidos del nome del futbolista (col nomatu de dorsal ensin informar), en contruyendo'l nome de futbolista, créase correutamente.")
  @Test
  void
      daosComponentesValidosDelNomeDeXugadorYNomatuEnsinInformar_cuandoSeCreaNomeXugador_entosCreaseCorreutamente() {
    final NomeFutbolista nomeCuervoArango =
        NomeFutbolista.entamador(
                new NomeCompletu("Jaime Vázquez Cuervo-Arango"), new ApelliuDorsal("Cuervo-Arango"))
            .entama();

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(nomeCuervoArango)
        .as("Comprobación del nome completu del futbolista.")
        .extracting(NomeFutbolista.vuelviNomeCompletuEnTestu)
        .isEqualTo("Jaime Vázquez Cuervo-Arango");
    aserciones
        .assertThat(nomeCuervoArango)
        .as("Comprobación del nomatu de dorsal del futbolista non disponible.")
        .extracting(NomeFutbolista.vuelviNomatuDorsalEnTestu)
        .isEqualTo("[Non disponible]");
    aserciones
        .assertThat(nomeCuervoArango)
        .as("Comprobación del apellíu de dorsal del futbolista.")
        .extracting(NomeFutbolista.vuelviApelliuDorsalEnTestu)
        .isEqualTo("Cuervo-Arango");

    aserciones.assertAll();
  }

  @DisplayName(
      "Daos dos nomes de futbolista colos mesmos componentes, en comparándolos, resulten iguales.")
  @Test
  void daosDosNomesXugadorColosMesmosComponentes_cuandoSeComparan_entosSonIguales() {
    final NomeFutbolista primerNomeFutbolista =
        NomeFutbolista.entamador(
                new NomeCompletu("Jaime Suárez Juesas"), new ApelliuDorsal("Juesas"))
            .colNomatuDorsal(new NomatuDorsal("Jimmy"))
            .entama();
    final NomeFutbolista segunduNomeFutbolista =
        NomeFutbolista.entamador(
                new NomeCompletu("Jaime Suárez Juesas"), new ApelliuDorsal("Juesas"))
            .colNomatuDorsal(new NomatuDorsal("Jimmy"))
            .entama();

    assertThat(primerNomeFutbolista)
        .as("Comprobación de la igualdá de dos nomes de futbolista.")
        .isEqualTo(segunduNomeFutbolista);
  }

  @DisplayName(
      "Dau un nome nulu de futbolista, en volviendo la representación testual, infórmase de la so nulidá.")
  @ParameterizedTest
  @NullSource
  void dauNomeNuluFutbolista_cuandoSeVuelveEnTestu_entosInformaseNulida(
      final NomeFutbolista nomeNuluFutbolista) {
    assertThat(NomeFutbolista.vuelviEnTestu.apply(nomeNuluFutbolista))
        .as("Comprobación de representación testual de nome nulu de futbolista.")
        .isEqualTo("Nome nulu de futbolista");
  }

  @DisplayName(
      "Dau un nome completu nulu de futbolista, en volviendo la representación testual, infórmase de la so nulidá.")
  @ParameterizedTest
  @NullSource
  void dauNomeCompletuNuluFutbolista_cuandoSeVuelveEnTestu_entosInformaseNulida(
      final NomeCompletu nomeCompletuNuluFutbolista) {
    assertThat(NomeCompletu.vuelviEnTestu.apply(nomeCompletuNuluFutbolista))
        .as("Comprobación de representación testual de nome completu nulu de futbolista.")
        .isEqualTo("Nome completu nulu de futbolista");
  }

  @DisplayName(
      "Dau un nomatu nulu del dorsal de futbolista, en volviendo la representación testual, infórmase de la so nulidá.")
  @ParameterizedTest
  @NullSource
  void dauNomatuNuluDorsalFutbolista_cuandoSeVuelveEnTestu_entosInformaseNulida(
      final NomatuDorsal nomatuNuluDorsalFutbolista) {
    assertThat(NomatuDorsal.vuelviEnTestu.apply(nomatuNuluDorsalFutbolista))
        .as("Comprobación de representación testual de nomatu nulu del dorsal de futbolista.")
        .isEqualTo("[Non disponible]");
  }

  @DisplayName(
      "Dau un apellíu nulu del dorsal de futbolista, en volviendo la representación testual, infórmase de la so nulidá.")
  @ParameterizedTest
  @NullSource
  void dauApelliuNuluDorsalFutbolista_cuandoSeVuelveEnTestu_entosInformaseNulida(
      final ApelliuDorsal apelliuNuluDorsalFutbolista) {
    assertThat(ApelliuDorsal.vuelviEnTestu.apply(apelliuNuluDorsalFutbolista))
        .as("Comprobación de representación testual d'apellíu nulu del dorsal de futbolista.")
        .isEqualTo("Apellíu nulu del dorsal de futbolista");
  }

  @DisplayName(
      "Daos tolos componentes del nome de futbolista nulos, en contruyendo'l nome de futbolista, surde una esceición.")
  @Test
  void daosTolosComponentesNulosNomeXugador_cuandoSeCreaNomeXugador_entosSurdeEsceicion() {
    final IllegalArgumentException esceicion =
        catchIllegalArgumentException(
            () -> NomeFutbolista.entamador(null, null).colNomatuDorsal(null).entama());

    SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion)
        .as(
            "Comprobación d'esceicion na creación del nome de futbolista por tolos componentes nulos.")
        .isNotNull();
    aserciones
        .assertThat(esceicion.getMessage())
        .as(
            "Comprobación de mensaxe d'esceicion na creación del nome de futbolista por tolos componentes nulos.")
        .isEqualTo(
            new StringJoiner(System.lineSeparator())
                .add("Nun se pudo crear 'NomeFutbolista':")
                .add("1 - El nome completu del futbolista ha tar informáu.")
                .add("2 - L'apellíu pal dorsal del futbolista ha tar informáu.")
                .add("3 - El nomatu pal dorsal del futbolista ha tar informáu.")
                .toString());

    aserciones.assertAll();
  }

  @DisplayName(
      "Daos componentes obligatorios nulos del nome de futbolista, en contruyendo'l nome de futbolista, surde una esceición.")
  @Test
  void daosComponentesObligatoriosNulosNomeXugador_cuandoSeCreaNomeXugador_entosSurdeEsceicion() {
    final IllegalArgumentException esceicion =
        catchIllegalArgumentException(() -> NomeFutbolista.entamador(null, null).entama());

    SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion)
        .as(
            "Comprobación d'esceicion na creación del nome de futbolista por componentes obligatorios nulos.")
        .isNotNull();
    aserciones
        .assertThat(esceicion.getMessage())
        .as(
            "Comprobación de mensaxe d'esceicion na creación del nome de futbolista por componentes obligatorios nulos.")
        .isEqualTo(
            new StringJoiner(System.lineSeparator())
                .add("Nun se pudo crear 'NomeFutbolista':")
                .add("1 - El nome completu del futbolista ha tar informáu.")
                .add("2 - L'apellíu pal dorsal del futbolista ha tar informáu.")
                .toString());

    aserciones.assertAll();
  }

  @DisplayName(
      "Daos testos nulos de tolos componentes del nome de futbolista, en contruyendo'l nome de futbolista, surde una esceición.")
  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"   ", "\t", "\n"})
  void daosTestosNulosTolosComponentesNomeXugador_cuandoSeCreaNomeXugador_entosSurdeEsceicion(
      final String testuEnsinInformar) {
    final IllegalArgumentException esceicion =
        catchIllegalArgumentException(
            () ->
                NomeFutbolista.entamador(
                        new NomeCompletu(testuEnsinInformar), new ApelliuDorsal(testuEnsinInformar))
                    .colNomatuDorsal(new NomatuDorsal(testuEnsinInformar))
                    .entama());

    SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion)
        .as(
            "Comprobación d'esceicion na creación del nome de futbolista por testos nulos de tolos componentes.")
        .isNotNull();
    aserciones
        .assertThat(esceicion.getMessage())
        .as(
            "Comprobación de mensaxe d'esceicion na creación del nome de futbolista por testos nulos de tolos componentes.")
        .isEqualTo(
            new StringJoiner(System.lineSeparator())
                .add("Nun se pudo crear 'NomeFutbolista':")
                .add("1 - El testu del nome completu del futbolista ha tar informáu.")
                .add("2 - El testu del apellíu pal dorsal del futbolista ha tar informáu.")
                .add("3 - El testu del nomatu pal dorsal del futbolista ha tar informáu.")
                .toString());

    aserciones.assertAll();
  }

  @DisplayName(
      "Daos testos nulos de componentes obligatorios del nome de futbolista, en contruyendo'l nome de futbolista, surde una esceición.")
  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {"   ", "\t", "\n"})
  void
      daosTestosNulosComponentesObligatoriosNomeXugador_cuandoSeCreaNomeXugador_entosSurdeEsceicion(
          final String testuEnsinInformar) {
    final IllegalArgumentException esceicion =
        catchIllegalArgumentException(
            () ->
                NomeFutbolista.entamador(
                        new NomeCompletu(testuEnsinInformar), new ApelliuDorsal(testuEnsinInformar))
                    .entama());

    SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion)
        .as(
            "Comprobación d'esceicion na creación del nome de futbolista por testos nulos de componentes obligatorios.")
        .isNotNull();
    aserciones
        .assertThat(esceicion.getMessage())
        .as(
            "Comprobación de mensaxe d'esceicion na creación del nome de futbolista por testos nulos de componentes obligatorios.")
        .isEqualTo(
            new StringJoiner(System.lineSeparator())
                .add("Nun se pudo crear 'NomeFutbolista':")
                .add("1 - El testu del nome completu del futbolista ha tar informáu.")
                .add("2 - El testu del apellíu pal dorsal del futbolista ha tar informáu.")
                .toString());

    aserciones.assertAll();
  }
}
