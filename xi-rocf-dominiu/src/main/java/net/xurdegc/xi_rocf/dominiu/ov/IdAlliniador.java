package net.xurdegc.xi_rocf.dominiu.ov;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public record IdAlliniador(UUID valor) {
  public static final Supplier<IdAlliniador> crea = () -> new IdAlliniador(UUID.randomUUID());
  public static final Function<UUID, IdAlliniador> recrea = IdAlliniador::new;
  public static final Function<IdAlliniador, String> vuelviEnTestu = ia -> ia.valor.toString();

  public IdAlliniador(@NotNull(message = "{id_alliniador.valor.nulu}") final UUID valor) {
    this.valor = valor;
  }
}
