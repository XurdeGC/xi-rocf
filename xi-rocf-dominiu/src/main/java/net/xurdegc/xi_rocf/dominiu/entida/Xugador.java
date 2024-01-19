package net.xurdegc.xi_rocf.dominiu.entida;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.xurdegc.xi_rocf.dominiu.ov.Dorsal;
import net.xurdegc.xi_rocf.dominiu.ov.IdXugador;
import net.xurdegc.xi_rocf.dominiu.ov.NomeXugador;

public class Xugador {
  public static final Predicate<Xugador> tienFichaDePrimerPlantia =
      x -> Dorsal.yeDePrimerPlantia.test(x.dorsal);
  public static final Predicate<Xugador> tienFichaDeCantera =
      x -> Dorsal.yeDeCantera.test(x.dorsal);
  public static final Predicate<Xugador> yeDiestru = x -> x.llateralida == Llateralida.DERECHA;
  public static final Predicate<Xugador> yeZurdu = x -> x.llateralida == Llateralida.IZQUIERDA;
  public static final Predicate<Xugador> yeAmbidiestru =
      x -> x.llateralida == Llateralida.AMBIDIESTRA;
  public static final Predicate<Xugador> taDisponible =
      x -> x.disponibilida == Disponibilida.DISPONIBLE;

  private IdXugador id;
  public final Supplier<IdXugador> vuelviId = () -> this.id;
  public final Supplier<String> vuelviIdEnTestu = () -> IdXugador.vuelviEnTestu.apply(this.id);

  private NomeXugador nome;
  public final Consumer<NomeXugador.NomeCompletu> camudaNomeCompletu =
      nc ->
          this.nome =
              NomeXugador.entamador(nc, this.nome.apelliuDorsal())
                  .colNomatuDorsal(this.nome.nomatuDorsal())
                  .entama();
  public final Consumer<NomeXugador.NomatuDorsal> camudaNomatuDorsal =
      nd ->
          this.nome =
              NomeXugador.entamador(this.nome.nomeCompletu(), this.nome.apelliuDorsal())
                  .colNomatuDorsal(nd)
                  .entama();
  public final Consumer<NomeXugador.ApelliuDorsal> camudaApelliuDorsal =
      ad ->
          this.nome =
              NomeXugador.entamador(this.nome.nomeCompletu(), ad)
                  .colNomatuDorsal(this.nome.nomatuDorsal())
                  .entama();
  public final Supplier<NomeXugador.NomeCompletu> vuelviNomeCompletu =
      () -> this.nome.nomeCompletu();
  public final Supplier<NomeXugador.NomatuDorsal> vuelviNomatuDorsal =
      () -> this.nome.nomatuDorsal();
  public final Supplier<NomeXugador.ApelliuDorsal> vuelviApelliuDorsal =
      () -> this.nome.apelliuDorsal();
  public final Supplier<String> vuelviNomeCompletuEnTestu =
      () -> NomeXugador.vuelviNomeCompletuEnTestu.apply(this.nome);
  public final Supplier<String> vuelviNomatuDorsalEnTestu =
      () -> NomeXugador.vuelviNomatuDorsalEnTestu.apply(this.nome);
  public final Supplier<String> vuelviApelliuDorsalEnTestu =
      () -> NomeXugador.vuelviApelliuDorsalEnTestu.apply(this.nome);

  private Dorsal dorsal;
  public final Consumer<Dorsal> camudaDorsal = d -> this.dorsal = d;
  public final Supplier<Dorsal> vuelviDorsal = () -> this.dorsal;
  public final Supplier<String> vuelviDorsalEnTestu = () -> Dorsal.vuelviEnTestu.apply(this.dorsal);

  private Llateralida llateralida;
  public final Consumer<Llateralida> camudaLlateralida = ll -> this.llateralida = ll;
  public final Supplier<Llateralida> vuelviLlateralida = () -> this.llateralida;
  public final Supplier<String> vuelviLlateralidaEnTestu = () -> this.llateralida.vuelviTestu();

  private Disponibilida disponibilida;
  public final Consumer<Disponibilida> camudaDisponibilida = e -> this.disponibilida = e;
  public final Supplier<Disponibilida> vuelviDisponibilida = () -> this.disponibilida;
  public final Supplier<String> vuelviDisponibilidaEnTestu = () -> this.disponibilida.vuelviTestu();

  private Xugador(
      IdXugador id,
      NomeXugador nome,
      Dorsal dorsal,
      Llateralida llateralida,
      Disponibilida disponibilida) {
    this.id = id;
    this.nome = nome;
    this.dorsal = dorsal;
    this.llateralida = llateralida;
    this.disponibilida = disponibilida;
  }

  public static Entamador entamador(
      final IdXugador id,
      final NomeXugador nome,
      final Llateralida llateralida,
      final Disponibilida disponibilida) {
    return new Entamador(id, nome, llateralida, disponibilida);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Xugador xugador)) return false;
    return Objects.equals(id.valor(), xugador.id.valor());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return vuelviNomeCompletuEnTestu.get()
        + ", \""
        + vuelviNomatuDorsalEnTestu.get()
        + "\""
        + (!vuelviApelliuDorsalEnTestu.get().equals(vuelviNomatuDorsalEnTestu.get())
            ? " (o, meyor, \"" + vuelviApelliuDorsalEnTestu.get() + "\")"
            : "")
        + ", col dorsal "
        + vuelviDorsalEnTestu.get()
        + ", "
        + vuelviLlateralidaEnTestu.get()
        + " y "
        + vuelviDisponibilidaEnTestu.get()
        + " (id: "
        + vuelviIdEnTestu.get()
        + ")";
  }

  public enum Llateralida {
    DERECHA("diestru"),
    IZQUIERDA("zurdu"),
    AMBIDIESTRA("ambidiestru");

    private final String testu;

    Llateralida(String testu) {
      this.testu = testu;
    }

    String vuelviTestu() {
      return testu;
    }
  }

  public enum Disponibilida {
    DISPONIBLE("disponible"),
    MANCAU("mancáu"),
    SANCIONAU("sancionáu");

    private final String testu;

    Disponibilida(String testu) {
      this.testu = testu;
    }

    public String vuelviTestu() {
      return testu;
    }
  }

  public static class Entamador {
    private final Supplier<IdXugador> suministradorId;
    private final Supplier<NomeXugador> suministradorNome;
    private final Supplier<Llateralida> suministradorLlateralida;
    private final Supplier<Disponibilida> suministradorDisponibilida;
    private Supplier<Dorsal> suministradorDorsal;

    private Entamador(
        final IdXugador id,
        final NomeXugador nome,
        final Llateralida llateralida,
        final Disponibilida disponibilida) {
      this.suministradorId = () -> id;
      this.suministradorNome = () -> nome;
      this.suministradorLlateralida = () -> llateralida;
      this.suministradorDisponibilida = () -> disponibilida;
      this.suministradorDorsal = () -> null;
    }

    public Entamador colDorsal(Dorsal dorsal) {
      this.suministradorDorsal = () -> dorsal;
      return this;
    }

    public Xugador entama() {
      return new Xugador(
          suministradorId.get(),
          suministradorNome.get(),
          suministradorDorsal.get(),
          suministradorLlateralida.get(),
          suministradorDisponibilida.get());
    }
  }
}
