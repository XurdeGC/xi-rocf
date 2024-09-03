package net.xurdegc.xi_rocf.domain.vo;

import static java.time.temporal.ChronoUnit.YEARS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.BiFunction;
import java.util.function.Function;
import net.xurdegc.xi_rocf.domain.entity.Footballer.ComponenteFutbolista;
import net.xurdegc.xi_rocf.domain.validation.ValidadorDataNacenciaFutbolista;

public record DataNacenciaFutbolista(LocalDate fecha) implements ComponenteFutbolista {
  private static final ValidadorDataNacenciaFutbolista VALIDADOR_DATA_NACENCIA_FUTBOLISTA =
      new ValidadorDataNacenciaFutbolista();
  private static final ResourceBundle TESTOS =
      ResourceBundle.getBundle("texts.vo.dataNacenciaFutbolista");
  private static final String CLAVE_TESTU_DATA_NACENCIA_FUTBOLISTA_NULA = "nula";
  private static final String CLAVE_TESTU_FORMATU_DATA_NACENCIA_FUTBOLISTA = "formatu";

  public static final BiFunction<DataNacenciaFutbolista, LocalDate, Byte> vuelviEdaFutbolista =
      (dnf, ld) ->
          Objects.nonNull(dnf) && Objects.nonNull(ld)
              ? (byte) YEARS.between(dnf.fecha(), ld)
              : null;
  public static final Function<DataNacenciaFutbolista, String> vuelviEnTestu =
      dnf ->
          Objects.nonNull(dnf)
              ? dnf.toString()
              : TESTOS.getString(CLAVE_TESTU_DATA_NACENCIA_FUTBOLISTA_NULA);

  public DataNacenciaFutbolista {
    VALIDADOR_DATA_NACENCIA_FUTBOLISTA.validate(fecha, this.getClass().getSimpleName());
  }

  @Override
  public String toString() {
    return Objects.requireNonNull(fecha)
        .format(
            DateTimeFormatter.ofPattern(
                TESTOS.getString(CLAVE_TESTU_FORMATU_DATA_NACENCIA_FUTBOLISTA)));
  }
}
