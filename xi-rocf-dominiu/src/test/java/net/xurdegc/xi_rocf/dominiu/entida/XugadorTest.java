package net.xurdegc.xi_rocf.dominiu.entida;

import net.xurdegc.xi_rocf.dominiu.ov.Dorsal;
import net.xurdegc.xi_rocf.dominiu.ov.IdXugador;
import net.xurdegc.xi_rocf.dominiu.ov.NomeXugador;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class XugadorTest {
  @DisplayName(
      "En contruyendo a un xugador diestru, disponible y con dorsal, créase correutamente.")
  @Test
  void cuandoSeCreaXugadorDiestruDisponibleYConDorsal_entosCreaseCorreutamente() {
    final Xugador roman =
        Xugador.entamador(
                IdXugador.crea.get(),
                NomeXugador.entamador(
                        new NomeXugador.NomeCompletu("Leonardo Román Riquelme"),
                        new NomeXugador.ApelliuDorsal("Román"))
                    .colNomatuDorsal(new NomeXugador.NomatuDorsal("Leo Román"))
                    .entama(),
                Xugador.Llateralida.DERECHA,
                Xugador.Disponibilida.DISPONIBLE)
            .colDorsal(new Dorsal((byte) 31))
            .entama();

    final String xugadorEsperau =
        "Leonardo Román Riquelme, \"Leo Román\" (o, meyor, \"Román\"), col dorsal 31, diestru y disponible (id: "
            + roman.vuelviIdEnTestu.get()
            + ")";

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones.assertThat(roman).hasToString(xugadorEsperau);

    aserciones.assertThat(Xugador.yeDiestru.test(roman)).isTrue();
    aserciones.assertThat(Xugador.yeAmbidiestru.negate().test(roman)).isTrue();
    aserciones.assertThat(Xugador.tienFichaDeCantera.test(roman)).isTrue();
    aserciones.assertThat(Xugador.taDisponible.test(roman)).isTrue();

    aserciones.assertAll();
  }

  @DisplayName("En contruyendo a un xugador zurdu, sancionáu y con dorsal, créase correutamente.")
  @Test
  void cuandoSeCreaXugadorZurduSancionauYConDorsal_entosCreaseCorreutamente() {
    final Xugador pomares =
        Xugador.entamador(
                IdXugador.crea.get(),
                NomeXugador.entamador(
                        new NomeXugador.NomeCompletu("Carlos Pomares Rayo"),
                        new NomeXugador.ApelliuDorsal("Pomares"))
                    .colNomatuDorsal(new NomeXugador.NomatuDorsal("Pomares"))
                    .entama(),
                Xugador.Llateralida.IZQUIERDA,
                Xugador.Disponibilida.SANCIONAU)
            .colDorsal(new Dorsal((byte) 21))
            .entama();

    final String xugadorEsperau =
        "Carlos Pomares Rayo, \"Pomares\", col dorsal 21, zurdu y sancionáu (id: "
            + pomares.vuelviIdEnTestu.get()
            + ")";

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones.assertThat(pomares).hasToString(xugadorEsperau);

    aserciones.assertThat(Xugador.yeZurdu.test(pomares)).isTrue();
    aserciones.assertThat(Xugador.yeDiestru.negate().test(pomares)).isTrue();
    aserciones.assertThat(Xugador.tienFichaDePrimerPlantia.test(pomares)).isTrue();
    aserciones.assertThat(Xugador.taDisponible.negate().test(pomares)).isTrue();

    aserciones.assertAll();
  }

  @DisplayName(
      "En contruyendo a un xugador ambidiestru, mancáu y con dorsal, créase correutamente.")
  @Test
  void cuandoSeCreaXugadorAmbidiestruMancauYConDorsal_entosCreaseCorreutamente() {
    final Xugador cazorla =
        Xugador.entamador(
                IdXugador.crea.get(),
                NomeXugador.entamador(
                        new NomeXugador.NomeCompletu("Santiago Cazorla González"),
                        new NomeXugador.ApelliuDorsal("Cazorla"))
                    .colNomatuDorsal(new NomeXugador.NomatuDorsal("S. Cazorla"))
                    .entama(),
                Xugador.Llateralida.AMBIDIESTRA,
                Xugador.Disponibilida.MANCAU)
            .colDorsal(new Dorsal((byte) 8))
            .entama();

    final String xugadorEsperau =
        "Santiago Cazorla González, \"S. Cazorla\" (o, meyor, \"Cazorla\"), col dorsal 8, ambidiestru y mancáu (id: "
            + cazorla.vuelviIdEnTestu.get()
            + ")";

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones.assertThat(cazorla).hasToString(xugadorEsperau);

    aserciones.assertThat(Xugador.yeAmbidiestru.test(cazorla)).isTrue();
    aserciones.assertThat(Xugador.yeZurdu.negate().test(cazorla)).isTrue();
    aserciones.assertThat(Xugador.tienFichaDePrimerPlantia.test(cazorla)).isTrue();
    aserciones.assertThat(Xugador.taDisponible.negate().test(cazorla)).isTrue();

    aserciones.assertAll();
  }

  @DisplayName("En contruyendo a un xugador ensin dorsal, créase correutamente.")
  @Test
  void cuandoSeCreaXugadorEnsinDorsal_entosCreaseCorreutamente() {
    final Xugador cuervoArango =
        Xugador.entamador(
                IdXugador.crea.get(),
                NomeXugador.entamador(
                        new NomeXugador.NomeCompletu("Jaime Vázquez Cuervo-Arango"),
                        new NomeXugador.ApelliuDorsal("Cuervo-Arango"))
                    .entama(),
                Xugador.Llateralida.IZQUIERDA,
                Xugador.Disponibilida.DISPONIBLE)
            .entama();

    final String xugadorEsperau =
        "Jaime Vázquez Cuervo-Arango, \"[Non disponible]\" (o, meyor, \"Cuervo-Arango\"), col dorsal ¿?, zurdu y disponible (id: "
            + cuervoArango.vuelviIdEnTestu.get()
            + ")";

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones.assertThat(cuervoArango).hasToString(xugadorEsperau);

    aserciones.assertThat(Xugador.yeZurdu.test(cuervoArango)).isTrue();
    aserciones.assertThat(Xugador.yeAmbidiestru.negate().test(cuervoArango)).isTrue();
    aserciones.assertThat(Xugador.tienFichaDeCantera.test(cuervoArango)).isTrue();
    aserciones.assertThat(Xugador.taDisponible.test(cuervoArango)).isTrue();

    aserciones.assertAll();
  }

  @DisplayName("En camudando datos d'un xugador, camuden correutamente.")
  @Test
  void cuandoSeCamudaDatosDeXugador_entosCamudenCorreutamente() {
    final Xugador cazorla =
        Xugador.entamador(
                IdXugador.crea.get(),
                NomeXugador.entamador(
                        new NomeXugador.NomeCompletu("Santiago Cazzorla González"),
                        new NomeXugador.ApelliuDorsal("Cazzorla"))
                    .colNomatuDorsal(new NomeXugador.NomatuDorsal("S. Cazzorla"))
                    .entama(),
                Xugador.Llateralida.IZQUIERDA,
                Xugador.Disponibilida.SANCIONAU)
            .colDorsal(new Dorsal((byte) 9))
            .entama();

    final IdXugador idCazorlaAnterior = cazorla.vuelviId.get();
    final NomeXugador.NomeCompletu nomeCompletuCazorlaAnterior = cazorla.vuelviNomeCompletu.get();
    final NomeXugador.NomatuDorsal nomatuDorsalCazorlaAnterior = cazorla.vuelviNomatuDorsal.get();
    final NomeXugador.ApelliuDorsal apelliuDorsalCazorlaAnterior =
        cazorla.vuelviApelliuDorsal.get();
    final Dorsal dorsalCazorlaAnterior = cazorla.vuelviDorsal.get();
    final Xugador.Llateralida llateralidaCazorlaAnterior = cazorla.vuelviLlateralida.get();
    final Xugador.Disponibilida disponibilidaCazorlaAnterior = cazorla.vuelviDisponibilida.get();

    cazorla.camudaNomeCompletu.accept(new NomeXugador.NomeCompletu("Santiago Cazorla González"));
    cazorla.camudaNomatuDorsal.accept(new NomeXugador.NomatuDorsal("S. Cazorla"));
    cazorla.camudaApelliuDorsal.accept(new NomeXugador.ApelliuDorsal("Cazorla"));
    cazorla.camudaDorsal.accept(new Dorsal((byte) 8));
    cazorla.camudaLlateralida.accept(Xugador.Llateralida.AMBIDIESTRA);
    cazorla.camudaDisponibilida.accept(Xugador.Disponibilida.MANCAU);

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
}
