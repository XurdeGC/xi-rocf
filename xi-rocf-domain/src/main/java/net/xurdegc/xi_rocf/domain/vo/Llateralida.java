package net.xurdegc.xi_rocf.domain.vo;

import net.xurdegc.xi_rocf.domain.entity.Footballer;

public enum Llateralida implements Footballer.ComponenteFutbolista {
  DERECHA(Footballer.TESTOS.getString(Footballer.CLAVE_TESTU_LLATERALIDA_DERECHA)),
  IZQUIERDA(Footballer.TESTOS.getString(Footballer.CLAVE_TESTU_LLATERALIDA_IZQUIERDA)),
  AMBIDIESTRA(Footballer.TESTOS.getString(Footballer.CLAVE_TESTU_LLATERALIDA_AMBIDIESTRA));

  private final String testu;

  Llateralida(String testu) {
    this.testu = testu;
  }

  String vuelviTestu() {
    return testu;
  }
}
