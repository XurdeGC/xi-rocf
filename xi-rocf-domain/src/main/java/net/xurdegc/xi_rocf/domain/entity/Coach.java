package net.xurdegc.xi_rocf.domain.entity;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.xurdegc.xi_rocf.domain.validation.CoachValidator;
import net.xurdegc.xi_rocf.domain.vo.CoachEmail;
import net.xurdegc.xi_rocf.domain.vo.CoachId;
import net.xurdegc.xi_rocf.domain.vo.CoachName;

public class Coach {
  private static final CoachValidator COACH_VALIDATOR = new CoachValidator();
  private static final ResourceBundle TEXTS = ResourceBundle.getBundle("texts.entity.Coach");
  private static final String TO_STRING_TEXT_KEY = "to_string";

  private CoachId id;
  public final Supplier<CoachId> theirId = () -> this.id;
  public final Supplier<String> theirTextualId = () -> CoachId.IN_TEXT.apply(this.id);

  private CoachEmail email;
  public final Supplier<CoachEmail> theirEmail = () -> this.email;
  public final Supplier<String> theirTextualEmail = () -> CoachEmail.IN_TEXT.apply(this.email);

  private CoachName name;
  public final Supplier<CoachName> theirName = () -> this.name;
  public final Supplier<String> theirTextualName = () -> CoachName.IN_TEXT.apply(this.name);

  public final Supplier<String> inText = this::toString;
  public final Consumer<CoachEmail> theirUpdatedEmail = this::updateEmailWithValidatedEmail;
  public final Consumer<CoachName> theirUpdatedName = this::updateCoachWithValidatedName;

  private Coach(CoachId id, CoachEmail email, CoachName name) {
    this.id = id;
    this.email = email;
    this.name = name;
  }

  public static Builder builder(final CoachId id, final CoachEmail email, final CoachName name) {
    return new Builder(id, email, name);
  }

  private static LinkedHashMap<Class<? extends CoachField>, CoachField>
      collectValidatableCoachFields(
          final CoachId id, final CoachEmail email, final CoachName name) {
    final LinkedHashMap<Class<? extends CoachField>, CoachField> validatableCoachFields =
        LinkedHashMap.newLinkedHashMap(3);

    validatableCoachFields.put(CoachId.class, id);
    validatableCoachFields.put(CoachEmail.class, email);
    validatableCoachFields.put(CoachName.class, name);

    return validatableCoachFields;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Coach coach)) return false;
    return Objects.equals(id, coach.id);
  }

  @Override
  public String toString() {
    return new MessageFormat(TEXTS.getString(TO_STRING_TEXT_KEY))
        .format(
            new Object[] {theirTextualName.get(), theirTextualEmail.get(), theirTextualId.get()});
  }

  private void updateEmailWithValidatedEmail(final CoachEmail emailToUpdateCoachWith) {
    final LinkedHashMap<Class<? extends CoachField>, CoachField> validatableCoachFields =
        collectValidatableCoachFields(this.id, emailToUpdateCoachWith, this.name);

    COACH_VALIDATOR.validate(validatableCoachFields, Coach.class.getSimpleName());

    this.email = emailToUpdateCoachWith;
  }

  private void updateCoachWithValidatedName(final CoachName nameToUpdateCoachWith) {
    final LinkedHashMap<Class<? extends CoachField>, CoachField> validatableCoachFields =
        collectValidatableCoachFields(this.id, this.email, nameToUpdateCoachWith);

    COACH_VALIDATOR.validate(validatableCoachFields, Coach.class.getSimpleName());

    this.name = nameToUpdateCoachWith;
  }

  public sealed interface CoachField permits CoachId, CoachEmail, CoachName {}

  public static final class Builder {
    private final LinkedHashMap<Class<? extends CoachField>, CoachField> validatableCoachFields;

    private final Supplier<CoachId> newId;
    private final Supplier<CoachEmail> newEmail;
    private final Supplier<CoachName> newName;

    private Builder(final CoachId id, final CoachEmail email, final CoachName name) {
      validatableCoachFields = collectValidatableCoachFields(id, email, name);

      this.newId = () -> id;
      this.newEmail = () -> email;
      this.newName = () -> name;
    }

    public Coach build() {
      COACH_VALIDATOR.validate(validatableCoachFields, Coach.class.getSimpleName());

      return new Coach(newId.get(), newEmail.get(), newName.get());
    }
  }
}
