package net.xurdegc.xi_rocf.domain.service;

import java.util.*;
import net.xurdegc.xi_rocf.domain.entity.Footballer;

public class XestionPlantiya {

  public Set<Footballer> amestaXugadorAPlantiya(
      final Set<Footballer> plantiya, final Footballer futbolistaAAmestar) {
    Set<Footballer> plantiyaColFutbolistaAmestau =
        Objects.requireNonNullElse(plantiya, new HashSet<>());
    Objects.requireNonNull(futbolistaAAmestar);

    plantiyaColFutbolistaAmestau.add(futbolistaAAmestar);

    return plantiyaColFutbolistaAmestau;
  }

  public Set<Footballer> desaniciaXugadorDePlantiya(
      final Set<Footballer> plantiya, final Footballer futbolistaADesaniciar) {
    Set<Footballer> plantiyaColFutbolistaDesaniciau = Objects.requireNonNull(plantiya);
    Objects.requireNonNull(futbolistaADesaniciar);

    plantiyaColFutbolistaDesaniciau.remove(futbolistaADesaniciar);

    return plantiyaColFutbolistaDesaniciau;
  }
}
