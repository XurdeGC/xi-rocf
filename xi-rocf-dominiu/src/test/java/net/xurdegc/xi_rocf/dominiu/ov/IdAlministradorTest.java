package net.xurdegc.xi_rocf.dominiu.ov;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.BDDAssertions.catchThrowableOfType;

import jakarta.validation.ConstraintViolationException;
import java.util.StringJoiner;
import java.util.UUID;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IdAlministradorTest {
  private static final String CLAVE_MENSAXE_ID_ALMINISTRADOR_VALOR_NULU =
      "id_alministrador.valor.nulu";

  @DisplayName("En creando l'identificador d'alministrador, créase correutamente.")
  @Test
  void cuandoSeCreaIdAlministrador_entosCreaseCorreutamente() {
    assertThat(IdAlministrador.crea.get())
        .as("Comprobación de creación del identificador d'alministrador.")
        .isNotNull();
  }

  @DisplayName(
      "Dau un valor esistente, en recreando l'identificador d'alministrador, recréase correutamente.")
  @Test
  void dauUnUUID_cuandoSeRecreaIdAlministrador_entosRecreaseCorreutamente() {
    final UUID valorEsistente = UUID.randomUUID();

    assertThat(IdAlministrador.recrea.apply(valorEsistente))
        .as("Comprobación de recreación del identificador d'alministrador.")
        .isNotNull();
  }

  @DisplayName(
      "Daos dos identificadores d'alministrador col mesmu valor, en comparándolos, resulten iguales.")
  @Test
  void daosDosIdsAlministradorColMesmuValor_cuandoSeComparan_entosSonIguales() {
    final IdAlministrador primerIdAlministrador = IdAlministrador.crea.get();
    final IdAlministrador segunduIdAlministrador =
        IdAlministrador.recrea.apply(primerIdAlministrador.valor());

    assertThat(primerIdAlministrador)
        .as("Comprobación de la igualdá de dos identificadores de d'alministrador.")
        .isEqualTo(segunduIdAlministrador);
  }

  @DisplayName(
      "Dau un valor nulu, en contruyendo l'identificador d'alministrador, surde una esceición.")
  @Test
  void dauUnValorNulu_cuandoSeCreaIdAlministrador_entosSurdeEsceicion() {
    final ConstraintViolationException esceicion =
        catchThrowableOfType(() -> new IdAlministrador(null), ConstraintViolationException.class);

    final SoftAssertions aserciones = new SoftAssertions();

    aserciones
        .assertThat(esceicion.getConstraintViolations())
        .as(
            "Comprobación d'esceicion na creación del identificador d'alministrador por valor non informáu.")
        .hasSize(1);
    aserciones
        .assertThat(esceicion.getConstraintViolations().iterator().next().getMessageTemplate())
        .as(
            "Comprobación del mensaxe d'esceicion na creación del identificador d'alministrador por valor non informáu.")
        .isEqualTo(
            new StringJoiner("", "{", "}")
                .add(CLAVE_MENSAXE_ID_ALMINISTRADOR_VALOR_NULU)
                .toString());

    aserciones.assertAll();
  }
}
