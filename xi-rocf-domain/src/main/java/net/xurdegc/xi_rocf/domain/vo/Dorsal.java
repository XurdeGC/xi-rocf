package net.xurdegc.xi_rocf.domain.vo;

import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Function;
import net.xurdegc.xi_rocf.domain.entity.Footballer.ComponenteFutbolista;
import net.xurdegc.xi_rocf.domain.validation.ValidadorDorsal;

public record Dorsal(Byte numberu) implements ComponenteFutbolista {
  private static final ValidadorDorsal VALIDADOR_DORSAL = new ValidadorDorsal();
  private static final ResourceBundle TESTOS = ResourceBundle.getBundle("texts.vo.dorsal");
  private static final String CLAVE_TESTU_DORSAL_NULU = "nulu";

  public static final Function<Dorsal, String> vuelviEnTestu =
      d -> Objects.nonNull(d) ? d.toString() : TESTOS.getString(CLAVE_TESTU_DORSAL_NULU);

  public Dorsal {
    VALIDADOR_DORSAL.validate(numberu, this.getClass().getSimpleName());
  }

  @Override
  public String toString() {
    return Objects.requireNonNull(numberu).toString();
  }
}
