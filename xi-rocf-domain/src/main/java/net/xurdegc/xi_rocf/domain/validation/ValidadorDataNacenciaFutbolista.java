package net.xurdegc.xi_rocf.domain.validation;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import net.xurdegc.xi_rocf.domain.validation.base.DateValidator;

public class ValidadorDataNacenciaFutbolista extends DateValidator<LocalDate> {
  private static final String CLAVE_MENSAXE_DATA_NACENCIA_FUTBOLISTA_FECHA_NULA =
      "data_nacencia_futbolista.fecha.nula";
  private static final String CLAVE_MENSAXE_DATA_NACENCIA_FUTBOLISTA_EDA_NON_PROFESIONAL =
      "data_nacencia_futbolista.eda.non.profesional";
  private static final int EDA_MINIMA_PROFESIONAL = 16;
  private static final Predicate<LocalDate> permiteXugarProfesionalmente =
      fdnx ->
          LocalDate.now()
              .isAfter(Objects.requireNonNull(fdnx).plusYears(EDA_MINIMA_PROFESIONAL).minusDays(1));

  @Override
  public void validate(
      final LocalDate fechaDataNacenciaFutbolista, final String nomeTipuDataNacenciaFutbolista) {
    validationErrors.clear();

    Function<Predicate<LocalDate>, Optional<String>>
        xeneraMensaxeFechaDataNacenciaFutbolistaNonInformada =
            p ->
                p.negate().test(fechaDataNacenciaFutbolista)
                    ? Optional.of(
                        VALIDATION_ERROR_MESSAGES.getString(
                            CLAVE_MENSAXE_DATA_NACENCIA_FUTBOLISTA_FECHA_NULA))
                    : Optional.empty();

    Function<Predicate<LocalDate>, Optional<String>> xeneraMensaxeEdaNonProfesional =
        p ->
            p.negate().test(fechaDataNacenciaFutbolista)
                ? Optional.of(
                    new MessageFormat(
                            VALIDATION_ERROR_MESSAGES.getString(
                                CLAVE_MENSAXE_DATA_NACENCIA_FUTBOLISTA_EDA_NON_PROFESIONAL))
                        .format(new Object[] {fechaDataNacenciaFutbolista}))
                : Optional.empty();

    xeneraMensaxeFechaDataNacenciaFutbolistaNonInformada
        .apply(isDatePresent)
        .ifPresent(m -> validationErrors.put(validationErrors.size() + 1, m));

    if (Objects.nonNull(fechaDataNacenciaFutbolista)) {
      xeneraMensaxeEdaNonProfesional
          .apply(permiteXugarProfesionalmente)
          .ifPresent(m -> validationErrors.put(validationErrors.size() + 1, m));
    }

    if (!validationErrors.isEmpty()) {
      buildIllegalArgumentException(nomeTipuDataNacenciaFutbolista);
    }
  }
}
