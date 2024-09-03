package net.xurdegc.xi_rocf.domain.validation;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import net.xurdegc.xi_rocf.domain.validation.base.Validator;
import net.xurdegc.xi_rocf.domain.vo.NomeFutbolista.*;

public class ValidadorNomeFutbolista
    extends Validator<
        LinkedHashMap<Class<? extends ComponenteNomeFutbolista>, ComponenteNomeFutbolista>> {
  private static final String CLAVE_MENSAXE_NOME_COMPLETU_ENSIN_INFORMAR =
      "nome_futbolista.nome_completu.ensin.informar";
  private static final String CLAVE_MENSAXE_NOMATU_DORSAL_ENSIN_INFORMAR =
      "nome_futbolista.nomatu_dorsal.ensin.informar";
  private static final String CLAVE_MENSAXE_APELLIU_DORSAL_ENSIN_INFORMAR =
      "nome_futbolista.apelliu_dorsal.ensin.informar";
  private static final String CLAVE_MENSAXE_NOME_COMPLETU_TESTU_ENSIN_INFORMAR =
      "nome_futbolista.nome_completu.testu.ensin.informar";
  private static final String CLAVE_MENSAXE_NOMATU_DORSAL_TESTU_ENSIN_INFORMAR =
      "nome_futbolista.nomatu_dorsal.testu.ensin.informar";
  private static final String CLAVE_MENSAXE_APELLIU_DORSAL_TESTU_ENSIN_INFORMAR =
      "nome_futbolista.apelliu_dorsal.testu.ensin.informar";

  private static final Predicate<ComponenteNomeFutbolista> taInformauComponenteNomeFutbolista =
      Objects::nonNull;
  private static final Predicate<String> taInformauTestuComponenteNomeFutbolista =
      t -> Objects.nonNull(t) && !t.isBlank();

  private static Optional<String> validaTestuComponenteNomeFutbolista(
      final ComponenteNomeFutbolista componenteNomeFutbolista) {
    final String claveMensaxeError =
        switch (componenteNomeFutbolista) {
          case NomeCompletu ignored -> CLAVE_MENSAXE_NOME_COMPLETU_TESTU_ENSIN_INFORMAR;
          case NomatuDorsal ignored -> CLAVE_MENSAXE_NOMATU_DORSAL_TESTU_ENSIN_INFORMAR;
          case ApelliuDorsal ignored -> CLAVE_MENSAXE_APELLIU_DORSAL_TESTU_ENSIN_INFORMAR;
        };
    Function<Predicate<String>, Optional<String>>
        xeneraMensaxeTestuComponenteNomeFutbolistaNonInformau =
            vuelviXeneradorMensaxeTestuComponenteNomeFutbolistaNonInformau(
                componenteNomeFutbolista, claveMensaxeError);

    return xeneraMensaxeTestuComponenteNomeFutbolistaNonInformau.apply(
        taInformauTestuComponenteNomeFutbolista);
  }

  private static Function<Predicate<String>, Optional<String>>
      vuelviXeneradorMensaxeTestuComponenteNomeFutbolistaNonInformau(
          final ComponenteNomeFutbolista componenteNomeFutbolista, final String claveMensaxeError) {
    final String testuComponenteNomeFutbolista =
        switch (componenteNomeFutbolista) {
          case NomeCompletu nc -> nc.testu();
          case NomatuDorsal nd -> nd.testu();
          case ApelliuDorsal ad -> ad.testu();
        };

    return p ->
        p.negate().test(testuComponenteNomeFutbolista)
            ? Optional.of(VALIDATION_ERROR_MESSAGES.getString(claveMensaxeError))
            : Optional.empty();
  }

  @Override
  public void validate(
      final LinkedHashMap<Class<? extends ComponenteNomeFutbolista>, ComponenteNomeFutbolista>
          componentesNomeFutbolistaAValidar,
      final String nomeTipuNomeFutbolista) {
    validationErrors.clear();

    componentesNomeFutbolistaAValidar.forEach(this::validaComponenteNomeFutbolista);

    if (!validationErrors.isEmpty()) {
      buildIllegalArgumentException(nomeTipuNomeFutbolista);
    }
  }

  private void validaComponenteNomeFutbolista(
      final Class<? extends ComponenteNomeFutbolista> claseComponenteNomeFutbolista,
      final ComponenteNomeFutbolista componenteNomeFutbolista) {
    final String claveMensaxeError =
        switch (claseComponenteNomeFutbolista.getSimpleName()) {
          case "NomeCompletu":
            yield CLAVE_MENSAXE_NOME_COMPLETU_ENSIN_INFORMAR;
          case "NomatuDorsal":
            yield CLAVE_MENSAXE_NOMATU_DORSAL_ENSIN_INFORMAR;
          default:
            yield CLAVE_MENSAXE_APELLIU_DORSAL_ENSIN_INFORMAR;
        };

    Function<Predicate<ComponenteNomeFutbolista>, Optional<String>>
        xeneraMensaxeComponenteNomeFutbolistaNonInformau =
            p ->
                p.negate().test(componenteNomeFutbolista)
                    ? Optional.of(VALIDATION_ERROR_MESSAGES.getString(claveMensaxeError))
                    : validaTestuComponenteNomeFutbolista(componenteNomeFutbolista);

    xeneraMensaxeComponenteNomeFutbolistaNonInformau
        .apply(taInformauComponenteNomeFutbolista)
        .ifPresent(m -> validationErrors.put(validationErrors.size() + 1, m));
  }
}
