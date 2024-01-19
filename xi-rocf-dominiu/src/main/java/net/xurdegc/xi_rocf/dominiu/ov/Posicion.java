package net.xurdegc.xi_rocf.dominiu.ov;

public enum Posicion {
  PORTERU(Llinia.PORTERIA),
  LLATERAL(Llinia.DEFENSA),
  CENTRAL(Llinia.DEFENSA),
  ESTERIOR(Llinia.CENTRU_CAMPU_1),
  INTERIOR(Llinia.CENTRU_CAMPU_1),
  MEDIUCENTRU(Llinia.CENTRU_CAMPU_1),
  MEDIAPUNTA(Llinia.CENTRU_CAMPU_2),
  ESTREMU(Llinia.DELANTERA),
  DELANTERU_CENTRU(Llinia.DELANTERA);

  private final Llinia lliniaALaQuePertenez;

  Posicion(final Llinia lliniaALaQuePertenez) {
    this.lliniaALaQuePertenez = lliniaALaQuePertenez;
  }

  public Llinia vuelviLliniaALaQuePertenez() {
    return lliniaALaQuePertenez;
  }
}
