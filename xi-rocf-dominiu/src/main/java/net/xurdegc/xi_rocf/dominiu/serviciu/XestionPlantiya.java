package net.xurdegc.xi_rocf.dominiu.serviciu;

import java.util.*;
import net.xurdegc.xi_rocf.dominiu.entida.Xugador;

public class XestionPlantiya {

  public Set<Xugador> amestaXugadorAPlantiya(
      final Set<Xugador> plantiya, final Xugador xugadorAAmestar) {
    Set<Xugador> plantiyaColXugadorAmestau = Objects.requireNonNullElse(plantiya, new HashSet<>());
    Objects.requireNonNull(xugadorAAmestar);

    plantiyaColXugadorAmestau.add(xugadorAAmestar);

    return plantiyaColXugadorAmestau;
  }

  public Set<Xugador> desaniciaXugadorDePlantiya(
      final Set<Xugador> plantiya, final Xugador xugadorADesaniciar) {
    Set<Xugador> plantiyaColXugadorDesaniciau = Objects.requireNonNull(plantiya);
    Objects.requireNonNull(xugadorADesaniciar);

    plantiyaColXugadorDesaniciau.remove(xugadorADesaniciar);

    return plantiyaColXugadorDesaniciau;
  }
}
