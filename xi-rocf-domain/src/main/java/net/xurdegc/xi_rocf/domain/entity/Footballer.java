package net.xurdegc.xi_rocf.domain.entity;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.xurdegc.xi_rocf.domain.validation.ValidadorFutbolista;
import net.xurdegc.xi_rocf.domain.vo.DataNacenciaFutbolista;
import net.xurdegc.xi_rocf.domain.vo.Disponibilida;
import net.xurdegc.xi_rocf.domain.vo.Dorsal;
import net.xurdegc.xi_rocf.domain.vo.FootballerId;
import net.xurdegc.xi_rocf.domain.vo.Llateralida;
import net.xurdegc.xi_rocf.domain.vo.NomeFutbolista;

public class Footballer {
  private static final ResourceBundle TESTOS = ResourceBundle.getBundle("texts.entity.footballer");
  private static final String CLAVE_TESTU_TO_STRING = "to_string";
  private static final String CLAVE_TESTU_LLATERALIDA_DERECHA = "llateralida.derecha";
  private static final String CLAVE_TESTU_LLATERALIDA_IZQUIERDA = "llateralida.izquierda";
  private static final String CLAVE_TESTU_LLATERALIDA_AMBIDIESTRA = "llateralida.ambidiestra";
  private static final String CLAVE_TESTU_DISPONIBILIDA_DISPONIBLE = "disponibilida.disponible";
  private static final String CLAVE_TESTU_DISPONIBILIDA_MANCAU = "disponibilida.mancau";
  private static final String CLAVE_TESTU_DISPONIBILIDA_SANCIONAU = "disponibilida.sancionau";

  private FootballerId id;
  public final Supplier<FootballerId> vuelviId = () -> this.id;
  public final Supplier<String> vuelviIdEnTestu = () -> FootballerId.IN_TEXT.apply(this.id);

  private NomeFutbolista nome;
  public final Consumer<NomeFutbolista.NomeCompletu> camudaNomeCompletu =
      nc ->
          this.nome =
              NomeFutbolista.entamador(nc, this.nome.apelliuDorsal())
                  .colNomatuDorsal(this.nome.nomatuDorsal())
                  .entama();
  public final Consumer<NomeFutbolista.NomatuDorsal> camudaNomatuDorsal =
      nd ->
          this.nome =
              NomeFutbolista.entamador(this.nome.nomeCompletu(), this.nome.apelliuDorsal())
                  .colNomatuDorsal(nd)
                  .entama();
  public final Consumer<NomeFutbolista.ApelliuDorsal> camudaApelliuDorsal =
      ad ->
          this.nome =
              NomeFutbolista.entamador(this.nome.nomeCompletu(), ad)
                  .colNomatuDorsal(this.nome.nomatuDorsal())
                  .entama();
  public final Supplier<NomeFutbolista.NomeCompletu> vuelviNomeCompletu =
      () -> this.nome.nomeCompletu();
  public final Supplier<NomeFutbolista.NomatuDorsal> vuelviNomatuDorsal =
      () -> this.nome.nomatuDorsal();
  public final Supplier<NomeFutbolista.ApelliuDorsal> vuelviApelliuDorsal =
      () -> this.nome.apelliuDorsal();
  public final Supplier<String> vuelviNomeCompletuEnTestu =
      () -> NomeFutbolista.vuelviNomeCompletuEnTestu.apply(this.nome);
  public final Supplier<String> vuelviNomatuDorsalEnTestu =
      () -> NomeFutbolista.vuelviNomatuDorsalEnTestu.apply(this.nome);
  public final Supplier<String> vuelviApelliuDorsalEnTestu =
      () -> NomeFutbolista.vuelviApelliuDorsalEnTestu.apply(this.nome);
  public final Supplier<NomeFutbolista> vuelviNome = () -> this.nome;
  public final Supplier<String> vuelviNomeEnTestu =
      () -> NomeFutbolista.vuelviEnTestu.apply(this.nome);

  private DataNacenciaFutbolista dataNacencia;
  public final Consumer<DataNacenciaFutbolista> camudaDataNacencia = dn -> this.dataNacencia = dn;
  public final Supplier<DataNacenciaFutbolista> vuelviDataNacencia = () -> this.dataNacencia;
  public final Supplier<String> vuelviDataNacenciaEnTestu =
      () -> DataNacenciaFutbolista.vuelviEnTestu.apply(this.dataNacencia);

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

  public final Supplier<String> vuelviEnTestu = this::toString;

  private Footballer(
      final FootballerId id,
      final NomeFutbolista nome,
      final DataNacenciaFutbolista dataNacencia,
      final Dorsal dorsal,
      final Llateralida llateralida,
      final Disponibilida disponibilida) {
    this.id = id;
    this.nome = nome;
    this.dataNacencia = dataNacencia;
    this.dorsal = dorsal;
    this.llateralida = llateralida;
    this.disponibilida = disponibilida;
  }

  public static Entamador entamador(
      final FootballerId id,
      final NomeFutbolista nome,
      final DataNacenciaFutbolista dataNacencia,
      final Llateralida llateralida,
      final Disponibilida disponibilida) {
    return new Entamador(id, nome, dataNacencia, llateralida, disponibilida);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Footballer footballer)) return false;
    return Objects.equals(id.label(), footballer.id.label());
  }

  @Override
  public String toString() {
    return new MessageFormat(TESTOS.getString(CLAVE_TESTU_TO_STRING))
        .format(
            new Object[] {
              vuelviNomeEnTestu.get(),
              vuelviDataNacenciaEnTestu.get(),
              vuelviDorsalEnTestu.get(),
              vuelviLlateralidaEnTestu.get(),
              vuelviDisponibilidaEnTestu.get(),
              vuelviIdEnTestu.get()
            });
  }

  public sealed interface ComponenteFutbolista
      permits FootballerId,
          NomeFutbolista,
          Dorsal,
          Llateralida,
          Disponibilida,
          DataNacenciaFutbolista {}

  public static class Entamador {
    private static final ValidadorFutbolista VALIDADOR_XUGADOR = new ValidadorFutbolista();

    private final LinkedHashMap<Class<? extends ComponenteFutbolista>, ComponenteFutbolista>
        componentesXugadorAValidar = LinkedHashMap.newLinkedHashMap(5);

    private final Supplier<FootballerId> suministradorId;
    private final Supplier<NomeFutbolista> suministradorNome;
    private final Supplier<DataNacenciaFutbolista> suministradorDataNacencia;
    private final Supplier<Llateralida> suministradorLlateralida;
    private final Supplier<Disponibilida> suministradorDisponibilida;
    private Supplier<Dorsal> suministradorDorsal;

    private Entamador(
        final FootballerId id,
        final NomeFutbolista nome,
        final DataNacenciaFutbolista dataNacencia,
        final Llateralida llateralida,
        final Disponibilida disponibilida) {
      componentesXugadorAValidar.clear();

      componentesXugadorAValidar.put(FootballerId.class, id);
      componentesXugadorAValidar.put(NomeFutbolista.class, nome);
      componentesXugadorAValidar.put(DataNacenciaFutbolista.class, dataNacencia);
      componentesXugadorAValidar.put(Llateralida.class, llateralida);
      componentesXugadorAValidar.put(Disponibilida.class, disponibilida);

      this.suministradorId = () -> id;
      this.suministradorNome = () -> nome;
      this.suministradorDataNacencia = () -> dataNacencia;
      this.suministradorLlateralida = () -> llateralida;
      this.suministradorDisponibilida = () -> disponibilida;
      this.suministradorDorsal = () -> null;
    }

    public Entamador colDorsal(final Dorsal dorsal) {
      componentesXugadorAValidar.put(Dorsal.class, dorsal);

      this.suministradorDorsal = () -> dorsal;

      return this;
    }

    public Footballer entama() {
      VALIDADOR_XUGADOR.validate(componentesXugadorAValidar, Footballer.class.getSimpleName());

      return new Footballer(
          suministradorId.get(),
          suministradorNome.get(),
          suministradorDataNacencia.get(),
          suministradorDorsal.get(),
          suministradorLlateralida.get(),
          suministradorDisponibilida.get());
    }
  }
}
