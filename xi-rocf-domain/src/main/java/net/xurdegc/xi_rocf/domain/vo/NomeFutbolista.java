package net.xurdegc.xi_rocf.domain.vo;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Supplier;
import net.xurdegc.xi_rocf.domain.entity.Footballer.ComponenteFutbolista;
import net.xurdegc.xi_rocf.domain.validation.ValidadorNomeFutbolista;

public record NomeFutbolista(
    NomeCompletu nomeCompletu, NomatuDorsal nomatuDorsal, ApelliuDorsal apelliuDorsal)
    implements ComponenteFutbolista {
  private static final ResourceBundle TESTOS = ResourceBundle.getBundle("texts.vo.nomeFutbolista");
  private static final String CLAVE_TESTU_TO_STRING_APELLIU_IGUAL_NOMATU =
      "to_string.apelliu.igual.nomatu";
  private static final String CLAVE_TESTU_TO_STRING_APELLIU_DIFERENTE_NOMATU =
      "to_string.apelliu.diferente.nomatu";
  private static final String CLAVE_TESTU_NOME_FUTBOLISTA_NULU = "nulu";

  public static final Function<NomeFutbolista, String> vuelviNomeCompletuEnTestu =
      nf -> NomeCompletu.vuelviEnTestu.apply(nf.nomeCompletu);
  public static final Function<NomeFutbolista, String> vuelviNomatuDorsalEnTestu =
      nf -> NomatuDorsal.vuelviEnTestu.apply(nf.nomatuDorsal);
  public static final Function<NomeFutbolista, String> vuelviApelliuDorsalEnTestu =
      nf -> ApelliuDorsal.vuelviEnTestu.apply(nf.apelliuDorsal);
  public static final Function<NomeFutbolista, String> vuelviEnTestu =
      nf ->
          Objects.nonNull(nf) ? nf.toString() : TESTOS.getString(CLAVE_TESTU_NOME_FUTBOLISTA_NULU);

  public static Entamador entamador(
      final NomeCompletu nomeCompletu, final ApelliuDorsal apelliuDorsal) {
    return new Entamador(nomeCompletu, apelliuDorsal);
  }

  @Override
  public String toString() {
    final String nomeCompletu = vuelviNomeCompletuEnTestu.apply(this);
    final String apelliuDorsal = vuelviApelliuDorsalEnTestu.apply(this);
    final String nomatuDorsal = vuelviNomatuDorsalEnTestu.apply(this);

    return apelliuDorsal.equals(nomatuDorsal)
        ? new MessageFormat(TESTOS.getString(CLAVE_TESTU_TO_STRING_APELLIU_IGUAL_NOMATU))
            .format(new Object[] {nomeCompletu, nomatuDorsal})
        : new MessageFormat(TESTOS.getString(CLAVE_TESTU_TO_STRING_APELLIU_DIFERENTE_NOMATU))
            .format(new Object[] {nomeCompletu, nomatuDorsal, apelliuDorsal});
  }

  public sealed interface ComponenteNomeFutbolista
      permits NomeCompletu, NomatuDorsal, ApelliuDorsal {}

  public record NomeCompletu(String testu) implements ComponenteNomeFutbolista {
    private static final String CLAVE_TESTU_NOME_COMPLETU_NULU = "nome_completu.nulu";

    public static final Function<NomeCompletu, String> vuelviEnTestu =
        nc ->
            Objects.nonNull(nc) ? nc.toString() : TESTOS.getString(CLAVE_TESTU_NOME_COMPLETU_NULU);

    public NomeCompletu {
      if (Objects.nonNull(testu)) {
        testu = testu.trim();
      }
    }

    @Override
    public String toString() {
      return Objects.requireNonNull(testu);
    }
  }

  public record NomatuDorsal(String testu) implements ComponenteNomeFutbolista {
    private static final String CLAVE_TESTU_NOMATU_DORSAL_NULU = "nomatu_dorsal.nulu";

    public static final Function<NomatuDorsal, String> vuelviEnTestu =
        nd ->
            Objects.nonNull(nd) ? nd.toString() : TESTOS.getString(CLAVE_TESTU_NOMATU_DORSAL_NULU);

    public NomatuDorsal {
      if (Objects.nonNull(testu)) {
        testu = testu.trim();
      }
    }

    @Override
    public String toString() {
      return Objects.requireNonNull(testu);
    }
  }

  public record ApelliuDorsal(String testu) implements ComponenteNomeFutbolista {
    private static final String CLAVE_TESTU_APELLIU_DORSAL_NULU = "apelliu_dorsal.nulu";

    public static final Function<ApelliuDorsal, String> vuelviEnTestu =
        ad ->
            Objects.nonNull(ad) ? ad.toString() : TESTOS.getString(CLAVE_TESTU_APELLIU_DORSAL_NULU);

    public ApelliuDorsal {
      if (Objects.nonNull(testu)) {
        testu = testu.trim();
      }
    }

    @Override
    public String toString() {
      return Objects.requireNonNull(testu);
    }
  }

  public static class Entamador {
    private static final ValidadorNomeFutbolista VALIDADOR_NOME_FUTBOLISTA =
        new ValidadorNomeFutbolista();

    private final LinkedHashMap<Class<? extends ComponenteNomeFutbolista>, ComponenteNomeFutbolista>
        componentesNomeXugadorAValidar = LinkedHashMap.newLinkedHashMap(3);

    private final Supplier<NomeCompletu> suministradorNomeCompletu;
    private final Supplier<ApelliuDorsal> suministradorApelliuDorsal;
    private Supplier<NomatuDorsal> suministradorNomatuDorsal;

    private Entamador(NomeCompletu nomeCompletu, ApelliuDorsal apelliuDorsal) {
      componentesNomeXugadorAValidar.clear();

      componentesNomeXugadorAValidar.put(NomeCompletu.class, nomeCompletu);
      componentesNomeXugadorAValidar.put(ApelliuDorsal.class, apelliuDorsal);

      this.suministradorNomeCompletu = () -> nomeCompletu;
      this.suministradorApelliuDorsal = () -> apelliuDorsal;
      this.suministradorNomatuDorsal = () -> null;
    }

    public Entamador colNomatuDorsal(final NomatuDorsal nomatuDorsal) {
      componentesNomeXugadorAValidar.put(NomatuDorsal.class, nomatuDorsal);

      this.suministradorNomatuDorsal = () -> nomatuDorsal;

      return this;
    }

    public NomeFutbolista entama() {
      VALIDADOR_NOME_FUTBOLISTA.validate(
          componentesNomeXugadorAValidar, NomeFutbolista.class.getSimpleName());

      return new NomeFutbolista(
          suministradorNomeCompletu.get(),
          suministradorNomatuDorsal.get(),
          suministradorApelliuDorsal.get());
    }
  }
}
