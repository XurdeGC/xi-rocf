package net.xurdegc.xi_rocf.domain.entity;

import static org.assertj.core.api.BDDAssertions.catchIllegalArgumentException;

import java.time.LocalDate;
import java.util.StringJoiner;
import net.xurdegc.xi_rocf.domain.vo.DataNacenciaFutbolista;
import net.xurdegc.xi_rocf.domain.vo.Disponibilida;
import net.xurdegc.xi_rocf.domain.vo.Dorsal;
import net.xurdegc.xi_rocf.domain.vo.FootballerId;
import net.xurdegc.xi_rocf.domain.vo.Llateralida;
import net.xurdegc.xi_rocf.domain.vo.NomeFutbolista;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FootballerTest {
  @DisplayName(
      "En contruyendo a un futbolista diestru, disponible y con dorsal, créase correutamente.")
  @Test
  void cuandoSeCreaFutbolistaDiestruDisponibleYConDorsal_entosCreaseCorreutamente() {
    final Footballer roman =
        Footballer.entamador(
                FootballerId.NEW.get(),
                NomeFutbolista.entamador(
                        new NomeFutbolista.NomeCompletu("Leonardo Román Riquelme"),
                        new NomeFutbolista.ApelliuDorsal("Román"))
                    .colNomatuDorsal(new NomeFutbolista.NomatuDorsal("Leo Román"))
                    .entama(),
                new DataNacenciaFutbolista(LocalDate.of(2_000, 7, 6)),
                Llateralida.DERECHA,
                Disponibilida.DISPONIBLE)
            .colDorsal(new Dorsal((byte) 31))
            .entama();

    final String futbolistaEsperau =
        "Leonardo Román Riquelme, \"Leo Román\" (o, meyor, \"Román\"), nacíu'l 06/07/2000, col dorsal 31, diestru y disponible (id: "
            + roman.vuelviIdEnTestu.get()
            + ")";

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones.assertThat(roman.vuelviEnTestu.get()).isEqualTo(futbolistaEsperau);

    aserciones.assertAll();
  }

  @DisplayName(
      "En contruyendo a un futbolista zurdu, sancionáu y con dorsal, créase correutamente.")
  @Test
  void cuandoSeCreaFutbolistaZurduSancionauYConDorsal_entosCreaseCorreutamente() {
    final Footballer pomares =
        Footballer.entamador(
                FootballerId.NEW.get(),
                NomeFutbolista.entamador(
                        new NomeFutbolista.NomeCompletu("Carlos Pomares Rayo"),
                        new NomeFutbolista.ApelliuDorsal("Pomares"))
                    .colNomatuDorsal(new NomeFutbolista.NomatuDorsal("Pomares"))
                    .entama(),
                new DataNacenciaFutbolista(LocalDate.of(1_992, 12, 5)),
                Llateralida.IZQUIERDA,
                Disponibilida.SANCIONAU)
            .colDorsal(new Dorsal((byte) 21))
            .entama();

    final String futbolistaEsperau =
        "Carlos Pomares Rayo, \"Pomares\", nacíu'l 05/12/1992, col dorsal 21, zurdu y sancionáu (id: "
            + pomares.vuelviIdEnTestu.get()
            + ")";

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones.assertThat(pomares.vuelviEnTestu.get()).isEqualTo(futbolistaEsperau);

    aserciones.assertAll();
  }

  @DisplayName(
      "En contruyendo a un futbolista ambidiestru, mancáu y con dorsal, créase correutamente.")
  @Test
  void cuandoSeCreaFutbolistaAmbidiestruMancauYConDorsal_entosCreaseCorreutamente() {
    final Footballer cazorla =
        Footballer.entamador(
                FootballerId.NEW.get(),
                NomeFutbolista.entamador(
                        new NomeFutbolista.NomeCompletu("Santiago Cazorla González"),
                        new NomeFutbolista.ApelliuDorsal("Cazorla"))
                    .colNomatuDorsal(new NomeFutbolista.NomatuDorsal("S. Cazorla"))
                    .entama(),
                new DataNacenciaFutbolista(LocalDate.of(1_984, 12, 13)),
                Llateralida.AMBIDIESTRA,
                Disponibilida.MANCAU)
            .colDorsal(new Dorsal((byte) 8))
            .entama();

    final String futbolistaEsperau =
        "Santiago Cazorla González, \"S. Cazorla\" (o, meyor, \"Cazorla\"), nacíu'l 13/12/1984, col dorsal 8, ambidiestru y mancáu (id: "
            + cazorla.vuelviIdEnTestu.get()
            + ")";

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones.assertThat(cazorla.vuelviEnTestu.get()).isEqualTo(futbolistaEsperau);

    aserciones.assertAll();
  }

  @DisplayName("En contruyendo a un futbolista ensin dorsal, créase correutamente.")
  @Test
  void cuandoSeCreaFutbolistaEnsinDorsal_entosCreaseCorreutamente() {
    final Footballer cuervoArango =
        Footballer.entamador(
                FootballerId.NEW.get(),
                NomeFutbolista.entamador(
                        new NomeFutbolista.NomeCompletu("Jaime Vázquez Cuervo-Arango"),
                        new NomeFutbolista.ApelliuDorsal("Cuervo-Arango"))
                    .entama(),
                new DataNacenciaFutbolista(LocalDate.of(2_006, 2, 1)),
                Llateralida.IZQUIERDA,
                Disponibilida.DISPONIBLE)
            .entama();

    final String futbolistaEsperau =
        "Jaime Vázquez Cuervo-Arango, \"[Non disponible]\" (o, meyor, \"Cuervo-Arango\"), nacíu'l 01/02/2006, col dorsal ¿?, zurdu y disponible (id: "
            + cuervoArango.vuelviIdEnTestu.get()
            + ")";

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones.assertThat(cuervoArango.vuelviEnTestu.get()).isEqualTo(futbolistaEsperau);

    aserciones.assertAll();
  }

  @DisplayName("En camudando datos d'un futbolista, camuden correutamente.")
  @Test
  void cuandoSeCamudaDatosDeFutbolista_entosCamudenCorreutamente() {
    final Footballer cazorla =
        Footballer.entamador(
                FootballerId.NEW.get(),
                NomeFutbolista.entamador(
                        new NomeFutbolista.NomeCompletu("Santiago Cazzorla González"),
                        new NomeFutbolista.ApelliuDorsal("Cazzorla"))
                    .colNomatuDorsal(new NomeFutbolista.NomatuDorsal("S. Cazzorla"))
                    .entama(),
                new DataNacenciaFutbolista(LocalDate.of(1_983, 12, 13)),
                Llateralida.IZQUIERDA,
                Disponibilida.SANCIONAU)
            .colDorsal(new Dorsal((byte) 9))
            .entama();

    final FootballerId idCazorlaAnterior = cazorla.vuelviId.get();
    final NomeFutbolista.NomeCompletu nomeCompletuCazorlaAnterior =
        cazorla.vuelviNome.get().nomeCompletu();
    final NomeFutbolista.NomatuDorsal nomatuDorsalCazorlaAnterior =
        cazorla.vuelviNome.get().nomatuDorsal();
    final NomeFutbolista.ApelliuDorsal apelliuDorsalCazorlaAnterior =
        cazorla.vuelviNome.get().apelliuDorsal();
    final DataNacenciaFutbolista dataNacenciaCazorlaAnterior = cazorla.vuelviDataNacencia.get();
    final Dorsal dorsalCazorlaAnterior = cazorla.vuelviDorsal.get();
    final Llateralida llateralidaCazorlaAnterior = cazorla.vuelviLlateralida.get();
    final Disponibilida disponibilidaCazorlaAnterior = cazorla.vuelviDisponibilida.get();

    cazorla.camudaNomeCompletu.accept(new NomeFutbolista.NomeCompletu("Santiago Cazorla González"));
    cazorla.camudaNomatuDorsal.accept(new NomeFutbolista.NomatuDorsal("S. Cazorla"));
    cazorla.camudaApelliuDorsal.accept(new NomeFutbolista.ApelliuDorsal("Cazorla"));
    cazorla.camudaDataNacencia.accept(new DataNacenciaFutbolista(LocalDate.of(1_984, 12, 13)));
    cazorla.camudaDorsal.accept(new Dorsal((byte) 8));
    cazorla.camudaLlateralida.accept(Llateralida.AMBIDIESTRA);
    cazorla.camudaDisponibilida.accept(Disponibilida.MANCAU);

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones.assertThat(cazorla.vuelviId.get()).isEqualTo(idCazorlaAnterior);

    aserciones
        .assertThat(cazorla.vuelviNomeCompletu.get())
        .isNotEqualTo(nomeCompletuCazorlaAnterior);
    aserciones
        .assertThat(cazorla.vuelviNomeCompletuEnTestu.get())
        .isEqualTo("Santiago Cazorla González");

    aserciones
        .assertThat(cazorla.vuelviNomatuDorsal.get())
        .isNotEqualTo(nomatuDorsalCazorlaAnterior);
    aserciones.assertThat(cazorla.vuelviNomatuDorsalEnTestu.get()).isEqualTo("S. Cazorla");

    aserciones
        .assertThat(cazorla.vuelviApelliuDorsal.get().testu())
        .isNotEqualTo(apelliuDorsalCazorlaAnterior);
    aserciones.assertThat(cazorla.vuelviApelliuDorsalEnTestu.get()).isEqualTo("Cazorla");

    aserciones
        .assertThat(cazorla.vuelviDataNacencia.get())
        .isNotEqualTo(dataNacenciaCazorlaAnterior);
    aserciones
        .assertThat(cazorla.vuelviDataNacencia.get().fecha())
        .isEqualTo(LocalDate.of(1_984, 12, 13));

    aserciones.assertThat(cazorla.vuelviDorsal.get()).isNotEqualTo(dorsalCazorlaAnterior);
    aserciones.assertThat(cazorla.vuelviDorsal.get().numberu()).isEqualTo((byte) 8);

    aserciones.assertThat(cazorla.vuelviLlateralida.get()).isNotEqualTo(llateralidaCazorlaAnterior);
    aserciones.assertThat(cazorla.vuelviLlateralida.get().name()).isEqualTo("AMBIDIESTRA");

    aserciones
        .assertThat(cazorla.vuelviDisponibilida.get())
        .isNotEqualTo(disponibilidaCazorlaAnterior);
    aserciones.assertThat(cazorla.vuelviDisponibilida.get().name()).isEqualTo("MANCAU");

    aserciones.assertAll();
  }

  @DisplayName(
      "Daos tolos componentes del futbolista nulos, en contruyendo'l futbolista, surde una esceición.")
  @Test
  void daosTolosComponentesNulosXugador_cuandoSeCreaXugador_entosSurdeEsceicion() {
    final IllegalArgumentException esceicion =
        catchIllegalArgumentException(
            () -> Footballer.entamador(null, null, null, null, null).colDorsal(null).entama());

    SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion)
        .as("Comprobación d'esceicion na creación del futbolista por tolos componentes nulos.")
        .isNotNull();
    aserciones
        .assertThat(esceicion.getMessage())
        .as(
            "Comprobación de mensaxe d'esceicion na creación del futbolista por tolos componentes nulos.")
        .isEqualTo(
            new StringJoiner(System.lineSeparator())
                .add("Nun se pudo crear 'Footballer':")
                .add("1 - L'identificador del futbolista ha tar informáu.")
                .add("2 - El nome del futbolista ha tar informáu.")
                .add("3 - La data de nacencia del futbolista ha tar informada.")
                .add("4 - La llateralidá del futbolista ha tar informada.")
                .add("5 - La disponibilidá del futbolista ha tar informada.")
                .add("6 - El dorsal del futbolista ha tar informáu.")
                .toString());

    aserciones.assertAll();
  }

  @DisplayName(
      "Daos componentes obligatorios nulos del futbolista, en contruyendo'l futbolista, surde una esceición.")
  @Test
  void daosComponentesObligatoriosNulosXugador_cuandoSeCreaXugador_entosSurdeEsceicion() {
    final IllegalArgumentException esceicion =
        catchIllegalArgumentException(
            () -> Footballer.entamador(null, null, null, null, null).entama());

    SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion)
        .as(
            "Comprobación d'esceicion na creación del futbolista por componentes obligatorios nulos.")
        .isNotNull();
    aserciones
        .assertThat(esceicion.getMessage())
        .as(
            "Comprobación de mensaxe d'esceicion na creación del futbolista por componentes obligatorios nulos.")
        .isEqualTo(
            new StringJoiner(System.lineSeparator())
                .add("Nun se pudo crear 'Footballer':")
                .add("1 - L'identificador del futbolista ha tar informáu.")
                .add("2 - El nome del futbolista ha tar informáu.")
                .add("3 - La data de nacencia del futbolista ha tar informada.")
                .add("4 - La llateralidá del futbolista ha tar informada.")
                .add("5 - La disponibilidá del futbolista ha tar informada.")
                .toString());

    aserciones.assertAll();
  }
}
