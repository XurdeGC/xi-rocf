package net.xurdegc.xi_rocf.domain.vo;

import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Function;
import net.xurdegc.xi_rocf.domain.entity.Coach.CoachField;
import net.xurdegc.xi_rocf.domain.validation.CoachNameValidator;

public record CoachName(String text) implements CoachField {
  private static final CoachNameValidator COACH_NAME_VALIDATOR = new CoachNameValidator();
  private static final ResourceBundle TEXTS = ResourceBundle.getBundle("texts.vo.CoachName");
  private static final String NULL_TEXT_KEY = "null";

  public static final Function<CoachName, String> IN_TEXT =
      coachName ->
          Objects.nonNull(coachName) ? coachName.toString() : TEXTS.getString(NULL_TEXT_KEY);

  public CoachName {
    COACH_NAME_VALIDATOR.validate(text, this.getClass().getSimpleName());

    text = text.trim();
  }

  @Override
  public String toString() {
    return Objects.requireNonNull(text);
  }
}
