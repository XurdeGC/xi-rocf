package net.xurdegc.xi_rocf.dominiu.ov;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssertions.catchThrowableOfType;

import jakarta.validation.ConstraintViolationException;
import java.util.StringJoiner;
import java.util.UUID;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IdXugadorTest {
  private static final String CLAVE_MENSAXE_ID_XUGADOR_VALOR_NULU = "id_xugador.valor.nulu";

  @DisplayName("En creando l'identificador de xugador, créase correutamente.")
  @Test
  void cuandoSeCreaIdXugador_entosCreaseCorreutamente() {
    assertThat(IdXugador.crea.get())
        .as("Comprobación de creación del identificador de xugador.")
        .isNotNull();
  }

  @DisplayName(
      "Dau un valor esistente, en recreando l'identificador de xugador, recréase correutamente.")
  @Test
  void dauUnUUID_cuandoSeRecreaIdXugador_entosRecreaseCorreutamente() {
    final UUID valorEsistente = UUID.randomUUID();

    assertThat(IdXugador.recrea.apply(valorEsistente))
        .as("Comprobación de recreación del identificador de xugador.")
        .isNotNull();
  }

  @DisplayName(
      "Daos dos identificadores de xugador col mesmu valor, en comparándolos, resulten iguales.")
  @Test
  void daosDosIdsXugadorColMesmuValor_cuandoSeComparan_entosSonIguales() {
    final IdXugador primerIdXugador = IdXugador.crea.get();
    final IdXugador segunduIdXugador = IdXugador.recrea.apply(primerIdXugador.valor());

    assertThat(primerIdXugador)
        .as("Comprobación de la igualdá de dos identificadores de xugador.")
        .isEqualTo(segunduIdXugador);
  }

  @DisplayName("Dau un valor nulu, en contruyendo l'identificador de xugador, surde una esceición.")
  @Test
  void dauUnValorNulu_cuandoSeCreaIdXugador_entosSurdeEsceicion() {
    final ConstraintViolationException esceicion =
        catchThrowableOfType(() -> new IdXugador(null), ConstraintViolationException.class);

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones.assertThat(esceicion.getConstraintViolations())
        .as(
            "Comprobación d'esceicion na creación del identificador de xugador por valor non informáu.")
        .hasSize(1);
    aserciones.assertThat(esceicion.getConstraintViolations().iterator().next().getMessageTemplate())
        .as(
            "Comprobación del mensaxe d'esceicion na creación del identificador de xugador por valor non informáu.")
        .isEqualTo(
            new StringJoiner("", "{", "}").add(CLAVE_MENSAXE_ID_XUGADOR_VALOR_NULU).toString());

    aserciones.assertAll();
  }
}
