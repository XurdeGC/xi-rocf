package net.xurdegc.xi_rocf.dominiu.ov;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.*;

import jakarta.validation.ConstraintViolationException;
import java.util.StringJoiner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DorsalTest {

  private static final String CLAVE_MENSAXE_DORSAL_NUMBERU_NULU = "dorsal.numberu.nulu";
  private static final String CLAVE_MENSAXE_DORSAL_NUMBERU_NON_POSITIVU =
      "dorsal.numberu.non.positivu";

  @DisplayName(
      "Dau un testu positivu menor de 26, en contruyendo'l dorsal de la primer plantía, créase correutamente.")
  @Test
  void dauUnValorPositivuMenor26_cuandoSeCreaDorsalPrimerPlantia_entosCreaseCorreutamente() {
    final Dorsal dorsal1 = new Dorsal((byte) 1);

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(Dorsal.yeDePrimerPlantia.test(dorsal1))
        .as("Comprobación de dorsal de la primer plantía.")
        .isTrue();
    aserciones
        .assertThat(Dorsal.yeDeCantera.test(dorsal1))
        .as("Comprobación de dorsal non de la cantera.")
        .isFalse();

    aserciones.assertAll();
  }

  @DisplayName(
      "Dau un testu positivu mayor de 25, en contruyendo'l dorsal de la cantera, créase correutamente.")
  @Test
  void dauUnValorPositivuMayor25_cuandoSeCreaDorsalCantera_entosCreaseCorreutamente() {
    final Dorsal dorsal31 = new Dorsal((byte) 31);

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(Dorsal.yeDeCantera.test(dorsal31))
        .as("Comprobación de dorsal de la cantera.")
        .isTrue();
    aserciones
        .assertThat(Dorsal.yeDePrimerPlantia.test(dorsal31))
        .as("Comprobación de dorsal non de la primer plantía.")
        .isFalse();

    aserciones.assertAll();
  }

  @DisplayName("Daos dos dorsales col mesmu númberu, en comparándolos, resulten iguales.")
  @Test
  void daosDosDorsalesColMesmuNumberu_cuandoSeComparan_entosSonIguales() {
    final Dorsal primerDorsal = new Dorsal((byte) 1);
    final Dorsal segunduDorsal = new Dorsal((byte) 1);

    assertThat(primerDorsal)
        .as("Comprobación de la igualdá de dos dorsales.")
        .isEqualTo(segunduDorsal);
  }

  @DisplayName("Dau un testu nulu, en contruyendo'l dorsal, surde una esceición.")
  @Test
  void dauUnValorNulu_cuandoSeCreaDorsal_entosSurdeEsceicion() {
    final ConstraintViolationException esceicion =
        catchThrowableOfType(() -> new Dorsal(null), ConstraintViolationException.class);

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion.getConstraintViolations())
        .as("Comprobación d'esceicion na creación del dorsal por númberu non informáu.")
        .hasSize(1);
    aserciones
        .assertThat(esceicion.getConstraintViolations().iterator().next().getMessageTemplate())
        .as("Comprobación del mensaxe d'esceicion na creación del dorsal por númberu non informáu.")
        .isEqualTo(
            new StringJoiner("", "{", "}").add(CLAVE_MENSAXE_DORSAL_NUMBERU_NULU).toString());

    aserciones.assertAll();
  }

  @DisplayName("Dau un testu non positivu, en contruyendo'l dorsal, surde una esceición.")
  @Test
  void dauUnValorNonPositivu_cuandoSeCreaDorsal_entosSurdeEsceicion() {
    final ConstraintViolationException esceicion =
        catchThrowableOfType(
            () -> new Dorsal(Byte.valueOf("0")), ConstraintViolationException.class);

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion.getConstraintViolations())
        .as("Comprobación d'esceicion na creación del dorsal por númberu non positivu.")
        .hasSize(1);
    aserciones
        .assertThat(esceicion.getConstraintViolations().iterator().next().getMessageTemplate())
        .as("Comprobación del mensaxe d'esceicion na creación del dorsal por númberu non positivu.")
        .isEqualTo(
            new StringJoiner("", "{", "}")
                .add(CLAVE_MENSAXE_DORSAL_NUMBERU_NON_POSITIVU)
                .toString());

    aserciones.assertAll();
  }
}
