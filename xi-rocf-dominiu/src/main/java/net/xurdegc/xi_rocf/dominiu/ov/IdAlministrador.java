package net.xurdegc.xi_rocf.dominiu.ov;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public record IdAlministrador(UUID valor) {
  public static final Supplier<IdAlministrador> crea = () -> new IdAlministrador(UUID.randomUUID());
  public static final Function<UUID, IdAlministrador> recrea = IdAlministrador::new;
  public static final Function<IdAlministrador, String> vuelviEnTestu = ia -> ia.valor.toString();

  public IdAlministrador(@NotNull(message = "{id_alministrador.valor.nulu}") final UUID valor) {
    this.valor = valor;
  }
}
