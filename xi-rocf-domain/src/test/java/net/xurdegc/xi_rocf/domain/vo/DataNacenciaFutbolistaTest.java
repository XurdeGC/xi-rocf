package net.xurdegc.xi_rocf.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchIllegalArgumentException;

import java.time.LocalDate;
import java.util.StringJoiner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

class DataNacenciaFutbolistaTest {
  @DisplayName(
      "Dada una fecha, en contruyendo la data de nacencia del futbolista, créase correutamente.")
  @Test
  void dadaUnaFecha_cuandoSeCreaDataNacenciaFutbolista_entosCreaseCorreutamente() {
    final DataNacenciaFutbolista dataNacenciaFutbolista =
        new DataNacenciaFutbolista(LocalDate.of(1996, 12, 31));

    assertThat(dataNacenciaFutbolista.fecha())
        .as("Comprobación de creación de la data de nacencia del futbolista.")
        .hasToString("1996-12-31");
  }

  @DisplayName(
      "Dada una data de nacencia de futbolista y una fecha, en calculando la edá del futbolista, calcúlase correutamente.")
  @Test
  void dadaDataNacenciaFutbolistaYFecha_cuandoSeCalculaEda_entosCalculaseCorreutamente() {
    final DataNacenciaFutbolista dataNacenciaFutbolista =
        new DataNacenciaFutbolista(LocalDate.of(1996, 12, 31));
    final LocalDate fecha = LocalDate.of(2024, 2, 25);

    assertThat(DataNacenciaFutbolista.vuelviEdaFutbolista.apply(dataNacenciaFutbolista, fecha))
        .as("Comprobación de la edá del futbolista.")
        .isEqualTo((byte) 27);
  }

  @DisplayName(
      "Dada una data nula de nacencia de futbolista y una fecha, en calculando la edá del futbolista, resulta nula.")
  @Test
  void dadaDataNulaNacenciaFutbolistaYFecha_cuandoSeCalculaEda_entosResultaNula() {
    final LocalDate fecha = LocalDate.of(2024, 2, 25);

    assertThat(DataNacenciaFutbolista.vuelviEdaFutbolista.apply(null, fecha))
        .as("Comprobación d'edá nula del futbolista (data de nacencia nula).")
        .isNull();
  }

  @DisplayName(
      "Dada una data de nacencia de futbolista y una fecha nula, en calculando la edá del futbolista, resulta nula.")
  @Test
  void dadaDataNacenciaFutbolistaYFechaNula_cuandoSeCalculaEda_entosResultaNula() {
    final DataNacenciaFutbolista dataNacenciaFutbolista =
        new DataNacenciaFutbolista(LocalDate.of(1996, 12, 31));

    assertThat(DataNacenciaFutbolista.vuelviEdaFutbolista.apply(dataNacenciaFutbolista, null))
        .as("Comprobación d'edá nula del futbolista (fecha nula).")
        .isNull();
  }

  @DisplayName(
      "Daes una data de nacencia de futbolista y una fecha nules, en calculando la edá del futbolista, resulta nula.")
  @Test
  void daesDataNacenciaFutbolistaYFechaNules_cuandoSeCalculaEda_entosResultaNula() {
    assertThat(DataNacenciaFutbolista.vuelviEdaFutbolista.apply(null, null))
        .as("Comprobación d'edá nula del futbolista (data de nacencia y fecha nules).")
        .isNull();
  }

  @DisplayName(
      "Daes dos dates de nacencia de futbolista cola mesma fecha, en comparándoles, resulten iguales.")
  @Test
  void daesDosDatesNacenciaFutbolistaColaMesmaFecha_cuandoSeComparan_entosSonIguales() {
    final DataNacenciaFutbolista primerDataNacenciaFutbolista =
        new DataNacenciaFutbolista(LocalDate.of(1996, 12, 31));
    final DataNacenciaFutbolista segundaDataNacenciaFutbolista =
        new DataNacenciaFutbolista(LocalDate.of(1996, 12, 31));

    assertThat(primerDataNacenciaFutbolista)
        .as("Comprobación de la igualdá de dos dates de nacencia de futbolista.")
        .isEqualTo(segundaDataNacenciaFutbolista);
  }

  @DisplayName(
      "Dada una data de nacencia nula de futbolista, en volviendo la representación testual, infórmase de la so nulidá.")
  @ParameterizedTest
  @NullSource
  void dadaDataNacenciaNulaFutbolista_cuandoSeVuelveEnTestu_entosInformaseNulida(
      final DataNacenciaFutbolista dataNacenciaNulaFutbolista) {
    assertThat(DataNacenciaFutbolista.vuelviEnTestu.apply(dataNacenciaNulaFutbolista))
        .as("Comprobación de representación testual de data de nacencia nula de futbolista.")
        .isEqualTo("Data de nacencia de futbolista nula");
  }

  @DisplayName(
      "Dada una fecha ensin informar, en contruyendo la data de nacencia de futbolista, surde una esceición.")
  @ParameterizedTest
  @NullSource
  void dadaUnaFechaEnsinInformar_cuandoSeCreaDataNacenciaFutbolista_entosSurdeEsceicion(
      LocalDate fechaEnsinInformar) {
    final IllegalArgumentException esceicion =
        catchIllegalArgumentException(() -> new DataNacenciaFutbolista(fechaEnsinInformar));

    SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion)
        .as(
            "Comprobación d'esceicion na creación de la data de nacencia de futbolista por fecha non informada.")
        .isNotNull();
    aserciones
        .assertThat(esceicion.getMessage())
        .as(
            "Comprobación de mensaxe d'esceicion na creación de la data de nacencia de futbolista por fecha non informada.")
        .isEqualTo(
            new StringJoiner(System.lineSeparator())
                .add("Nun se pudo crear 'DataNacenciaFutbolista':")
                .add("1 - La data de nacencia del futbolista nun puede ser nula.")
                .toString());

    aserciones.assertAll();
  }

  @DisplayName(
      "Dada una fecha de nacencia cola que nun se supera la edá mínima pal profesionalismu, en contruyendo la data de nacencia de futbolista, surde una esceición.")
  @Test
  void
      dadaFechaNacenciaImpideProfesionalismu_cuandoSeCreaDataNacenciaFutbolista_entosSurdeEsceicion() {
    final int edaMinimaProfesional = 16;
    final LocalDate dataNacenciaNonProfesional =
        LocalDate.now().minusYears(edaMinimaProfesional).plusDays(1);
    final IllegalArgumentException esceicion =
        catchIllegalArgumentException(() -> new DataNacenciaFutbolista(dataNacenciaNonProfesional));

    SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion)
        .as(
            "Comprobación d'esceicion na creación de la data de nacencia de futbolista por edá non profesional.")
        .isNotNull();
    aserciones
        .assertThat(esceicion.getMessage())
        .as(
            "Comprobación de mensaxe d'esceicion na creación de la data de nacencia de futbolista por edá non profesional.")
        .isEqualTo(
            new StringJoiner(System.lineSeparator())
                .add("Nun se pudo crear 'DataNacenciaFutbolista':")
                .add("1 - El futbolista nun tien la edá mínima profesional.")
                .toString());

    aserciones.assertAll();
  }
}
