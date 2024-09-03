package net.xurdegc.xi_rocf.domain.vo;

import java.util.Objects;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;
import net.xurdegc.xi_rocf.domain.entity.Footballer.ComponenteFutbolista;
import net.xurdegc.xi_rocf.domain.validation.FootballerIdValidator;

public record FootballerId(UUID label) implements ComponenteFutbolista {
  private static final FootballerIdValidator FOOTBALLER_ID_VALIDATOR = new FootballerIdValidator();
  private static final ResourceBundle TEXTS = ResourceBundle.getBundle("texts.vo.FootballerId");
  private static final String NULL_TEXT_KEY = "null";

  public static final Supplier<FootballerId> NEW = () -> new FootballerId(UUID.randomUUID());
  public static final Function<UUID, FootballerId> FROM_LABEL = FootballerId::new;
  public static final Function<FootballerId, String> IN_TEXT =
      footballerId ->
          Objects.nonNull(footballerId) ? footballerId.toString() : TEXTS.getString(NULL_TEXT_KEY);

  public FootballerId {
    FOOTBALLER_ID_VALIDATOR.validate(label, this.getClass().getSimpleName());
  }

  @Override
  public String toString() {
    return Objects.requireNonNull(label).toString();
  }
}
