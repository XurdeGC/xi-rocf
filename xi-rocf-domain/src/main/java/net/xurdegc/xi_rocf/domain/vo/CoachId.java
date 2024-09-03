package net.xurdegc.xi_rocf.domain.vo;

import java.util.Objects;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;
import net.xurdegc.xi_rocf.domain.entity.Coach.CoachField;
import net.xurdegc.xi_rocf.domain.validation.CoachIdValidator;

public record CoachId(UUID label) implements CoachField {
  private static final CoachIdValidator COACH_ID_VALIDATOR = new CoachIdValidator();
  private static final ResourceBundle TEXTS = ResourceBundle.getBundle("texts.vo.CoachId");
  private static final String NULL_TEXT_KEY = "null";

  public static final Supplier<CoachId> NEW = () -> new CoachId(UUID.randomUUID());
  public static final Function<UUID, CoachId> FROM_LABEL = CoachId::new;
  public static final Function<CoachId, String> IN_TEXT =
      coachId -> Objects.nonNull(coachId) ? coachId.toString() : TEXTS.getString(NULL_TEXT_KEY);

  public CoachId {
    COACH_ID_VALIDATOR.validate(label, this.getClass().getSimpleName());
  }

  @Override
  public String toString() {
    return Objects.requireNonNull(label).toString();
  }
}
