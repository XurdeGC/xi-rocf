package net.xurdegc.xi_rocf.dominiu.entida;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.xurdegc.xi_rocf.dominiu.ov.*;

public class Alliniador {
  private IdAlliniador id;
  public final Supplier<IdAlliniador> vuelviId = () -> this.id;
  public final Supplier<String> vuelviIdEnTestu = () -> IdAlliniador.vuelviEnTestu.apply(this.id);

  private CorreuElectronicu correuElectronicu;
  public final Consumer<CorreuElectronicu> camudaCorreuElectronicu =
      ce -> this.correuElectronicu = ce;
  public final Supplier<CorreuElectronicu> vuelviCorreuElectronicu = () -> this.correuElectronicu;

  private NomeAlliniador nome;
  public final Consumer<NomeAlliniador> camudaNome = n -> this.nome = n;
  public final Supplier<NomeAlliniador> vuelviNome = () -> this.nome;

  private Alliniador(IdAlliniador id, CorreuElectronicu correuElectronicu, NomeAlliniador nome) {
    this.id = id;
    this.correuElectronicu = correuElectronicu;
    this.nome = nome;
  }

  public static Entamador entamador() {
    return new Entamador();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Alliniador alliniador)) return false;
    return Objects.equals(id.valor(), alliniador.id.valor());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return nome.testu()
        + " ["
        + correuElectronicu.cuenta()
        + "] (id: "
        + vuelviIdEnTestu.get()
        + ")";
  }

  public static class Entamador {
    private Supplier<IdAlliniador> suministradorId;
    private Supplier<CorreuElectronicu> suministradorCorreuElectronicu;
    private Supplier<NomeAlliniador> suministradorNome;

    private Entamador() {}

    public Entamador colId(IdAlliniador id) {
      this.suministradorId = () -> id;
      return this;
    }

    public Entamador colCorreuElectronicu(CorreuElectronicu correuElectronicu) {
      this.suministradorCorreuElectronicu = () -> correuElectronicu;
      return this;
    }

    public Entamador colNome(NomeAlliniador nome) {
      this.suministradorNome = () -> nome;
      return this;
    }

    public Alliniador entama() {
      return new Alliniador(
          suministradorId.get(), suministradorCorreuElectronicu.get(), suministradorNome.get());
    }
  }
}
