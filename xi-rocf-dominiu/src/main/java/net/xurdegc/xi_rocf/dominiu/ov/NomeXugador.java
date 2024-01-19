package net.xurdegc.xi_rocf.dominiu.ov;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public record NomeXugador(
    NomeCompletu nomeCompletu, NomatuDorsal nomatuDorsal, ApelliuDorsal apelliuDorsal) {
  public static final String NOMATU_DORSAL_NON_INFORMAU = "[Non disponible]";
  public static final Function<NomeXugador, String> vuelviNomeCompletuEnTestu =
      nx -> nx.nomeCompletu.testu;
  public static final Function<NomeXugador, String> vuelviNomatuDorsalEnTestu =
      nx -> Objects.nonNull(nx.nomatuDorsal) ? nx.nomatuDorsal.testu : NOMATU_DORSAL_NON_INFORMAU;
  public static final Function<NomeXugador, String> vuelviApelliuDorsalEnTestu =
      nx -> nx.apelliuDorsal.testu;

  public NomeXugador(
      @NotNull(message = "{nome_xugador.nome_completu.ensin.informar}")
          final NomeCompletu nomeCompletu,
      final NomatuDorsal nomatuDorsal,
      @NotNull(message = "{nome_xugador.apelliu_dorsal.ensin.informar}")
          final ApelliuDorsal apelliuDorsal) {
    this.nomeCompletu = nomeCompletu;
    this.nomatuDorsal = nomatuDorsal;
    this.apelliuDorsal = apelliuDorsal;
  }

  public static Entamador entamador(
      final NomeCompletu nomeCompletu, final ApelliuDorsal apelliuDorsal) {
    return new Entamador(nomeCompletu, apelliuDorsal);
  }

  public record NomeCompletu(String testu) {
    public NomeCompletu(
        @NotBlank(message = "{nome_xugador.nome_completu.testu.ensin.informar}")
            final String testu) {
      this.testu = testu;
    }
  }

  public record NomatuDorsal(String testu) {
    public NomatuDorsal(
        @NotBlank(message = "{nome_xugador.nomatu_dorsal.testu.ensin.informar}")
            final String testu) {
      this.testu = testu;
    }
  }

  public record ApelliuDorsal(String testu) {
    public ApelliuDorsal(
        @NotBlank(message = "{nome_xugador.apelliu_dorsal.testu.ensin.informar}")
            final String testu) {
      this.testu = testu;
    }
  }

  public static class Entamador {
    private final Supplier<NomeCompletu> suministradorNomeCompletu;
    private final Supplier<ApelliuDorsal> suministradorApelliuDorsal;
    private Supplier<NomatuDorsal> suministradorNomatuDorsal;

    private Entamador(NomeCompletu nomeCompletu, ApelliuDorsal apelliuDorsal) {
      this.suministradorNomeCompletu = () -> nomeCompletu;
      this.suministradorApelliuDorsal = () -> apelliuDorsal;
      this.suministradorNomatuDorsal = () -> null;
    }

    public Entamador colNomatuDorsal(final NomatuDorsal nomatuDorsal) {
      this.suministradorNomatuDorsal = () -> nomatuDorsal;
      return this;
    }

    public NomeXugador entama() {
      return new NomeXugador(
          suministradorNomeCompletu.get(),
          suministradorNomatuDorsal.get(),
          suministradorApelliuDorsal.get());
    }
  }
}
