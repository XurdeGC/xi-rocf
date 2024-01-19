package net.xurdegc.xi_rocf.dominiu.ov;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.BDDAssertions.catchThrowableOfType;

import jakarta.validation.ConstraintViolationException;
import java.util.StringJoiner;
import java.util.UUID;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IdAlliniadorTest {
  private static final String CLAVE_MENSAXE_ID_ALLINIADOR_VALOR_NULU = "id_alliniador.valor.nulu";

  @DisplayName("En creando l'identificador d'alliniador, créase correutamente.")
  @Test
  void cuandoSeCreaIdAlliniador_entosCreaseCorreutamente() {
    assertThat(IdAlliniador.crea.get())
        .as("Comprobación de creación del identificador d'alliniador.")
        .isNotNull();
  }

  @DisplayName(
      "Dau un valor esistente, en recreando l'identificador d'alliniador, recréase correutamente.")
  @Test
  void dauUnUUID_cuandoSeRecreaIdAlliniador_entosRecreaseCorreutamente() {
    final UUID valorEsistente = UUID.randomUUID();

    assertThat(IdAlliniador.recrea.apply(valorEsistente))
        .as("Comprobación de recreación del identificador d'alliniador.")
        .isNotNull();
  }

  @DisplayName(
      "Daos dos identificadores d'alliniador col mesmu valor, en comparándolos, resulten iguales.")
  @Test
  void daosDosIdsAlliniadorColMesmuValor_cuandoSeComparan_entosSonIguales() {
    final IdAlliniador primerIdAlliniador = IdAlliniador.crea.get();
    final IdAlliniador segunduIdAlliniador = IdAlliniador.recrea.apply(primerIdAlliniador.valor());

    assertThat(primerIdAlliniador)
        .as("Comprobación de la igualdá de dos identificadores de d'alliniador.")
        .isEqualTo(segunduIdAlliniador);
  }

  @DisplayName(
      "Dau un valor nulu, en contruyendo l'identificador d'alliniador, surde una esceición.")
  @Test
  void dauUnValorNulu_cuandoSeCreaIdAlliniador_entosSurdeEsceicion() {
    final ConstraintViolationException esceicion =
        catchThrowableOfType(() -> new IdAlliniador(null), ConstraintViolationException.class);

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion.getConstraintViolations())
        .as(
            "Comprobación d'esceicion na creación del identificador d'alliniador por valor non informáu.")
        .hasSize(1);
    aserciones
        .assertThat(esceicion.getConstraintViolations().iterator().next().getMessageTemplate())
        .as(
            "Comprobación del mensaxe d'esceicion na creación del identificador d'alliniador por valor non informáu.")
        .isEqualTo(
            new StringJoiner("", "{", "}").add(CLAVE_MENSAXE_ID_ALLINIADOR_VALOR_NULU).toString());

    aserciones.assertAll();
  }
}
