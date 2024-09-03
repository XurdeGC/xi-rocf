package net.xurdegc.xi_rocf.domain.vo;

import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Function;
import net.xurdegc.xi_rocf.domain.entity.Coach.CoachField;
import net.xurdegc.xi_rocf.domain.validation.CoachEmailValidator;

public record CoachEmail(String address) implements CoachField {
  private static final CoachEmailValidator COACH_EMAIL_VALIDATOR = new CoachEmailValidator();
  private static final ResourceBundle TEXTS = ResourceBundle.getBundle("texts.vo.CoachEmail");
  private static final String NULL_TEXT_KEY = "null";

  public static final Function<CoachEmail, String> IN_TEXT =
      coachEmail ->
          Objects.nonNull(coachEmail) ? coachEmail.toString() : TEXTS.getString(NULL_TEXT_KEY);

  public CoachEmail {
    COACH_EMAIL_VALIDATOR.validate(address, this.getClass().getSimpleName());

    address = address.trim().toLowerCase();
  }

  @Override
  public String toString() {
    return Objects.requireNonNull(address);
  }
}
