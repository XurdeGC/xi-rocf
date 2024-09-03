package net.xurdegc.xi_rocf.domain.vo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchIllegalArgumentException;

import java.util.StringJoiner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class DorsalTest {
  @DisplayName("Daos dos dorsales col mesmu númberu, en comparándolos, resulten iguales.")
  @Test
  void daosDosDorsalesColMesmuNumberu_cuandoSeComparan_entosSonIguales() {
    final Dorsal primerDorsal = new Dorsal((byte) 1);
    final Dorsal segunduDorsal = new Dorsal((byte) 1);

    assertThat(primerDorsal)
        .as("Comprobación de la igualdá de dos dorsales.")
        .isEqualTo(segunduDorsal);
  }

  @DisplayName(
      "Dau un dorsal nulu de futbolista, en volviendo la representación testual, infórmase de la so nulidá.")
  @ParameterizedTest
  @NullSource
  void dauDorsalNuluFutbolista_cuandoSeVuelveEnTestu_entosInformaseNulida(
      final Dorsal dorsalNuluFutbolista) {
    assertThat(Dorsal.vuelviEnTestu.apply(dorsalNuluFutbolista))
        .as("Comprobación de representación testual de dorsal nulu de futbolista.")
        .isEqualTo("¿?");
  }

  @DisplayName("Dau un númberu nulu, en contruyendo'l dorsal, surde una esceición.")
  @ParameterizedTest
  @NullSource
  void dauUnValorNulu_cuandoSeCreaDorsal_entosSurdeEsceicion(final Byte numberuEnsinInformar) {
    final IllegalArgumentException esceicion =
        catchIllegalArgumentException(() -> new Dorsal(numberuEnsinInformar));

    SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion)
        .as("Comprobación d'esceicion na creación del dorsal por númberu non informáu.")
        .isNotNull();
    aserciones
        .assertThat(esceicion.getMessage())
        .as("Comprobación de mensaxe d'esceicion na creación del dorsal por númberu non informáu.")
        .isEqualTo(
            new StringJoiner(System.lineSeparator())
                .add("Nun se pudo crear 'Dorsal':")
                .add("1 - El númberu de dorsal del futbolista nun puede ser nulu.")
                .add(
                    "2 - El númberu de dorsal del futbolista (%d) ha ser positivu."
                        .formatted(numberuEnsinInformar))
                .toString());

    aserciones.assertAll();
  }

  @DisplayName("Dau un númberu non positivu, en contruyendo'l dorsal, surde una esceición.")
  @ParameterizedTest
  @ValueSource(bytes = {0})
  void dauUnValorNonPositivu_cuandoSeCreaDorsal_entosSurdeEsceicion(final Byte numberuNonPositivu) {
    final IllegalArgumentException esceicion =
        catchIllegalArgumentException(() -> new Dorsal(numberuNonPositivu));

    SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion)
        .as("Comprobación d'esceicion na creación del dorsal por númberu non positivu.")
        .isNotNull();
    aserciones
        .assertThat(esceicion.getMessage())
        .as("Comprobación de mensaxe d'esceicion na creación del dorsal por númberu non positivu.")
        .isEqualTo(
            new StringJoiner(System.lineSeparator())
                .add("Nun se pudo crear 'Dorsal':")
                .add(
                    "1 - El númberu de dorsal del futbolista (%d) ha ser positivu."
                        .formatted(numberuNonPositivu))
                .toString());

    aserciones.assertAll();
  }
}
