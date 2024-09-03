package net.xurdegc.xi_rocf.domain.vo;

import net.xurdegc.xi_rocf.domain.entity.Footballer;

public enum Disponibilida implements Footballer.ComponenteFutbolista {
  DISPONIBLE(Footballer.TESTOS.getString(Footballer.CLAVE_TESTU_DISPONIBILIDA_DISPONIBLE)),
  MANCAU(Footballer.TESTOS.getString(Footballer.CLAVE_TESTU_DISPONIBILIDA_MANCAU)),
  SANCIONAU(Footballer.TESTOS.getString(Footballer.CLAVE_TESTU_DISPONIBILIDA_SANCIONAU));

  private final String testu;

  Disponibilida(final String testu) {
    this.testu = testu;
  }

  public String vuelviTestu() {
    return testu;
  }
}
