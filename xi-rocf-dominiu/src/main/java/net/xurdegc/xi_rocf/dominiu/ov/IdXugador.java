package net.xurdegc.xi_rocf.dominiu.ov;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public record IdXugador(UUID valor) {
  public static final Supplier<IdXugador> crea = () -> new IdXugador(UUID.randomUUID());
  public static final Function<UUID, IdXugador> recrea = IdXugador::new;
  public static final Function<IdXugador, String> vuelviEnTestu = ix -> ix.valor.toString();

  public IdXugador(@NotNull(message = "{id_xugador.valor.nulu}") final UUID valor) {
    this.valor = valor;
  }
}
