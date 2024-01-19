package net.xurdegc.xi_rocf.dominiu.entida;

import static org.assertj.core.api.Assertions.assertThat;

import net.xurdegc.xi_rocf.dominiu.ov.*;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AlliniadorTest {
  @DisplayName("En contruyendo l'alliniador, créase correutamente.")
  @Test
  void cuandoSeCreaAlliniador_entosCreaseCorreutamente() {
    final Alliniador alliniador =
        Alliniador.entamador()
            .colId(IdAlliniador.crea.get())
            .colCorreuElectronicu(new CorreuElectronicu("alliniador@correu.com"))
            .colNome(new NomeAlliniador("Carrión"))
            .entama();

    final String xugadorEsperau =
        "Carrión [alliniador@correu.com] (id: " + alliniador.vuelviIdEnTestu.get() + ")";

    assertThat(alliniador).hasToString(xugadorEsperau);
  }

  @DisplayName("En camudando datos del alliniador, camuden correutamente.")
  @Test
  void cuandoSeCamudaDatosDeAlliniador_entosCamudenCorreutamente() {
    final Alliniador alliniador =
        Alliniador.entamador()
            .colId(IdAlliniador.crea.get())
            .colCorreuElectronicu(new CorreuElectronicu("alliniadorr@correu.com"))
            .colNome(new NomeAlliniador("Carriónn"))
            .entama();

    final IdAlliniador idAlliniadorAnterior = alliniador.vuelviId.get();
    final CorreuElectronicu correuElectronicuAnterior = alliniador.vuelviCorreuElectronicu.get();
    final NomeAlliniador nomeAlliniadorAnterior = alliniador.vuelviNome.get();

    alliniador.camudaCorreuElectronicu.accept(new CorreuElectronicu("alliniador@correu.com"));
    alliniador.camudaNome.accept(new NomeAlliniador("Carrión"));

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones.assertThat(alliniador.vuelviId.get()).isEqualTo(idAlliniadorAnterior);

    aserciones
        .assertThat(alliniador.vuelviCorreuElectronicu.get())
        .isNotEqualTo(correuElectronicuAnterior);
    aserciones
        .assertThat(alliniador.vuelviCorreuElectronicu.get().cuenta())
        .isEqualTo("alliniador@correu.com");

    aserciones.assertThat(alliniador.vuelviNome.get()).isNotEqualTo(nomeAlliniadorAnterior);
    aserciones.assertThat(alliniador.vuelviNome.get().testu()).isEqualTo("Carrión");

    aserciones.assertAll();
  }
}
