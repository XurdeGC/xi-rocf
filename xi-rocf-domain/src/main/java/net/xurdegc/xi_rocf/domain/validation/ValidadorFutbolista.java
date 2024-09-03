package net.xurdegc.xi_rocf.domain.validation;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import net.xurdegc.xi_rocf.domain.entity.Footballer.ComponenteFutbolista;
import net.xurdegc.xi_rocf.domain.validation.base.Validator;

public class ValidadorFutbolista
    extends Validator<LinkedHashMap<Class<? extends ComponenteFutbolista>, ComponenteFutbolista>> {
  private static final String CLAVE_MENSAXE_ID_ENSIN_INFORMAR = "futbolista.id.ensin.informar";
  private static final String CLAVE_MENSAXE_NOME_ENSIN_INFORMAR = "futbolista.nome.ensin.informar";
  private static final String CLAVE_MENSAXE_DATA_NACENCIA_ENSIN_INFORMAR =
      "futbolista.data_nacencia.ensin.informar";
  private static final String CLAVE_MENSAXE_DORSAL_ENSIN_INFORMAR =
      "futbolista.dorsal.ensin.informar";
  private static final String CLAVE_MENSAXE_LLATERALIDA_ENSIN_INFORMAR =
      "futbolista.llateralida.ensin.informar";
  private static final String CLAVE_MENSAXE_DISPONIBILIDA_ENSIN_INFORMAR =
      "futbolista.disponibilida.ensin.informar";

  private static final Predicate<ComponenteFutbolista> taInformauComponenteFutbolista =
      Objects::nonNull;

  @Override
  public void validate(
      final LinkedHashMap<Class<? extends ComponenteFutbolista>, ComponenteFutbolista>
          componentesFutbolistaAValidar,
      final String nomeTipuFutbolista) {
    validationErrors.clear();

    componentesFutbolistaAValidar.forEach(this::validaComponenteFutbolista);

    if (!validationErrors.isEmpty()) {
      buildIllegalArgumentException(nomeTipuFutbolista);
    }
  }

  private void validaComponenteFutbolista(
      final Class<? extends ComponenteFutbolista> claseComponenteFutbolista,
      final ComponenteFutbolista componenteFutbolista) {
    final String claveMensaxeError =
        switch (claseComponenteFutbolista.getSimpleName()) {
          case "FootballerId" -> CLAVE_MENSAXE_ID_ENSIN_INFORMAR;
          case "NomeFutbolista" -> CLAVE_MENSAXE_NOME_ENSIN_INFORMAR;
          case "DataNacenciaFutbolista" -> CLAVE_MENSAXE_DATA_NACENCIA_ENSIN_INFORMAR;
          case "Dorsal" -> CLAVE_MENSAXE_DORSAL_ENSIN_INFORMAR;
          case "Llateralida" -> CLAVE_MENSAXE_LLATERALIDA_ENSIN_INFORMAR;
          default -> CLAVE_MENSAXE_DISPONIBILIDA_ENSIN_INFORMAR;
        };

    Function<Predicate<ComponenteFutbolista>, Optional<String>>
        xeneraMensaxeComponenteFutbolistaNonInformau =
            p ->
                p.negate().test(componenteFutbolista)
                    ? Optional.of(VALIDATION_ERROR_MESSAGES.getString(claveMensaxeError))
                    : Optional.empty();

    xeneraMensaxeComponenteFutbolistaNonInformau
        .apply(taInformauComponenteFutbolista)
        .ifPresent(m -> validationErrors.put(validationErrors.size() + 1, m));
  }
}
