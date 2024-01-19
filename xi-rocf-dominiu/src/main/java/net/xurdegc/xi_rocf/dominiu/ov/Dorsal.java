package net.xurdegc.xi_rocf.dominiu.ov;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public record Dorsal(Byte numberu) {
  public static final String DORSAL_NON_INFORMAU = "¿?";
  public static final Predicate<Dorsal> yeDePrimerPlantia = d -> d.numberu() <= 25;
  public static final Predicate<Dorsal> yeDeCantera = d -> Objects.isNull(d) || d.numberu() > 25;
  public static final Function<Dorsal, String> vuelviEnTestu =
      d -> Objects.nonNull(d) ? d.numberu.toString() : DORSAL_NON_INFORMAU;

  public Dorsal(
      @NotNull(message = "{dorsal.numberu.nulu}")
          @Positive(message = "{dorsal.numberu.non.positivu}")
          final Byte numberu) {
    this.numberu = numberu;
  }
}
