package net.xurdegc.xi_rocf.domain.vo;

import java.util.Arrays;
import java.util.List;

public enum Esquema {
  UN_TRES_DOS_DOS_TRES(
      "1-3-2-2-3",
      Arrays.asList(
          new Puestu(Posicion.PORTERU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.MEDIUCENTRU, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIUCENTRU, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.MEDIAPUNTA, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIAPUNTA, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.ESTREMU, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.DELANTERU_CENTRU),
          new Puestu(Posicion.ESTREMU, Puestu.Perfil.IZQUIERDU))),
  UN_TRES_TRES_UN_TRES(
      "1-3-3-1-3",
      Arrays.asList(
          new Puestu(Posicion.PORTERU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.INTERIOR, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIUCENTRU),
          new Puestu(Posicion.INTERIOR, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.MEDIAPUNTA),
          new Puestu(Posicion.ESTREMU, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.DELANTERU_CENTRU),
          new Puestu(Posicion.ESTREMU, Puestu.Perfil.IZQUIERDU))),
  UN_TRES_CUATRO_UN_DOS(
      "1-3-4-1-2",
      Arrays.asList(
          new Puestu(Posicion.PORTERU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.ESTERIOR, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIUCENTRU, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIUCENTRU, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.ESTERIOR, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.MEDIAPUNTA),
          new Puestu(Posicion.DELANTERU_CENTRU, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.DELANTERU_CENTRU, Puestu.Perfil.IZQUIERDU))),
  UN_TRES_CINCO_DOS(
      "1-3-5-2",
      Arrays.asList(
          new Puestu(Posicion.PORTERU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.ESTERIOR, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.INTERIOR, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIUCENTRU),
          new Puestu(Posicion.INTERIOR, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.ESTERIOR, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.DELANTERU_CENTRU, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.DELANTERU_CENTRU, Puestu.Perfil.IZQUIERDU))),
  UN_TRES_CUATRO_DOS_UN(
      "1-3-4-2-1",
      Arrays.asList(
          new Puestu(Posicion.PORTERU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.ESTERIOR, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIUCENTRU, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIUCENTRU, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.ESTERIOR, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.MEDIAPUNTA, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIAPUNTA, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.DELANTERU_CENTRU))),
  UN_TRES_CINCO_UN_UN(
      "1-3-5-1-1",
      Arrays.asList(
          new Puestu(Posicion.PORTERU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.ESTERIOR, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.INTERIOR, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIUCENTRU),
          new Puestu(Posicion.INTERIOR, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.ESTERIOR, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.MEDIAPUNTA),
          new Puestu(Posicion.DELANTERU_CENTRU))),
  UN_CUATRO_TRES_TRES(
      "1-4-3-3",
      Arrays.asList(
          new Puestu(Posicion.PORTERU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.INTERIOR, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIUCENTRU),
          new Puestu(Posicion.INTERIOR, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.ESTREMU, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.DELANTERU_CENTRU),
          new Puestu(Posicion.ESTREMU, Puestu.Perfil.IZQUIERDU))),
  UN_CUATRO_DOS_DOS_DOS(
      "1-4-2-2-2",
      Arrays.asList(
          new Puestu(Posicion.PORTERU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.MEDIUCENTRU, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIUCENTRU, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.MEDIAPUNTA, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIAPUNTA, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.DELANTERU_CENTRU, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.DELANTERU_CENTRU, Puestu.Perfil.IZQUIERDU))),
  UN_CUATRO_TRES_UN_DOS(
      "1-4-3-1-2",
      Arrays.asList(
          new Puestu(Posicion.PORTERU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.INTERIOR, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIUCENTRU),
          new Puestu(Posicion.INTERIOR, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.MEDIAPUNTA),
          new Puestu(Posicion.DELANTERU_CENTRU, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.DELANTERU_CENTRU, Puestu.Perfil.IZQUIERDU))),
  UN_CUATRO_CUATRO_DOS(
      "1-4-4-2",
      Arrays.asList(
          new Puestu(Posicion.PORTERU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.ESTERIOR, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIUCENTRU, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIUCENTRU, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.ESTERIOR, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.DELANTERU_CENTRU, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.DELANTERU_CENTRU, Puestu.Perfil.IZQUIERDU))),
  UN_CUATRO_CINCO_UN(
      "1-4-5-1",
      Arrays.asList(
          new Puestu(Posicion.PORTERU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.ESTERIOR, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.INTERIOR, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIUCENTRU),
          new Puestu(Posicion.INTERIOR, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.ESTERIOR, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.DELANTERU_CENTRU))),
  UN_CINCO_TRES_DOS(
      "1-5-3-2",
      Arrays.asList(
          new Puestu(Posicion.PORTERU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL),
          new Puestu(Posicion.CENTRAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.INTERIOR, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIUCENTRU),
          new Puestu(Posicion.INTERIOR, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.DELANTERU_CENTRU, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.DELANTERU_CENTRU, Puestu.Perfil.IZQUIERDU))),
  UN_CINCO_TRES_UN_UN(
      "1-5-3-1-1",
      Arrays.asList(
          new Puestu(Posicion.PORTERU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL),
          new Puestu(Posicion.CENTRAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.INTERIOR, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIUCENTRU),
          new Puestu(Posicion.INTERIOR, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.MEDIAPUNTA),
          new Puestu(Posicion.DELANTERU_CENTRU))),
  UN_CINCO_CUATRO_UN(
      "1-5-4-1",
      Arrays.asList(
          new Puestu(Posicion.PORTERU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.CENTRAL),
          new Puestu(Posicion.CENTRAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.LLATERAL, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.ESTERIOR, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIUCENTRU, Puestu.Perfil.DERECHU),
          new Puestu(Posicion.MEDIUCENTRU, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.ESTERIOR, Puestu.Perfil.IZQUIERDU),
          new Puestu(Posicion.DELANTERU_CENTRU)));

  private final String nome;
  private final List<Puestu> puestos;

  Esquema(final String nome, final List<Puestu> puestos) {
    this.nome = nome;
    this.puestos = puestos;
  }

  public String vuelviNome() {
    return nome;
  }

  public List<Puestu> vuelviPuestos() {
    return List.of(puestos.toArray(new Puestu[] {}));
  }

  public record Puestu(Posicion posicion, Perfil perfil) {

    private Puestu(final Posicion posicion) {
      this(posicion, null);
    }

    public enum Perfil {
      DERECHU,
      IZQUIERDU
    }
  }
}
