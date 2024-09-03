package net.xurdegc.xi_rocf.domain.vo;

import static java.time.temporal.ChronoUnit.YEARS;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import net.xurdegc.xi_rocf.domain.validation.FootballerPersonalDataValidator;

public record FootballerPersonalData(FullName fullName, Birthdate birthdate) {
  private static final FootballerPersonalDataValidator FOOTBALLER_PERSONAL_DATA_VALIDATOR =
      new FootballerPersonalDataValidator();
  private static final ResourceBundle TEXTS =
      ResourceBundle.getBundle("texts.vo.FootballerPersonalData");
  private static final String NULL_TEXT_KEY = "null";
  private static final String TO_STRING_TEXT_KEY = "to_string";

  public static final Function<FootballerPersonalData, String> IN_TEXT =
      footballerPersonalData ->
          Objects.nonNull(footballerPersonalData)
              ? footballerPersonalData.toString()
              : TEXTS.getString(NULL_TEXT_KEY);

  public static Builder builder(final FullName fullName, final Birthdate birthdate) {
    return new Builder(fullName, birthdate);
  }

  @Override
  public String toString() {
    return new MessageFormat(TEXTS.getString(TO_STRING_TEXT_KEY))
        .format(
            new Object[] {
              fullName, Birthdate.CALCULATE_AGE_IN_TEXT.apply(birthdate, LocalDate.now())
            });
  }

  public sealed interface FootballerPersonalDataField permits FullName, Birthdate {}

  public record FullName(String text) implements FootballerPersonalDataField {
    private static final String FULL_NAME_NULL_TEXT_KEY = "full_name.null";

    public static final Function<FullName, String> IN_TEXT =
        fullName ->
            Objects.nonNull(fullName)
                ? fullName.toString()
                : TEXTS.getString(FULL_NAME_NULL_TEXT_KEY);

    public FullName {
      if (Objects.nonNull(text)) {
        text = text.trim();
      }
    }

    @Override
    public String toString() {
      return Objects.requireNonNull(text);
    }
  }

  public record Birthdate(LocalDate dob) implements FootballerPersonalDataField {
    private static final String BIRTHDATE_NULL_TEXT_KEY = "birthdate.null";
    private static final String BIRTHDATE_FORMAT_TEXT_KEY = "birthdate.format";
    private static final String TEXTUAL_AGE_TEXT_KEY = "age.in_text";
    private static final String UNKNOWN_AGE_TEXT_KEY = "age.unknown";

    public static final BiFunction<Birthdate, LocalDate, Byte> CALCULATE_AGE =
        (birthdate, someDate) ->
            Objects.nonNull(birthdate)
                    && Objects.nonNull(someDate)
                    && someDate.isAfter(birthdate.dob())
                ? (byte) YEARS.between(birthdate.dob(), someDate)
                : null;
    public static final BiFunction<Birthdate, LocalDate, String> CALCULATE_AGE_IN_TEXT =
        (birthdate, someDate) -> {
          Byte footballerAge = CALCULATE_AGE.apply(birthdate, someDate);
          return Objects.nonNull(footballerAge)
              ? new MessageFormat(TEXTS.getString(TEXTUAL_AGE_TEXT_KEY))
                  .format(new Object[] {footballerAge})
              : TEXTS.getString(UNKNOWN_AGE_TEXT_KEY);
        };
    public static final Function<Birthdate, String> IN_TEXT =
        dob -> Objects.nonNull(dob) ? dob.toString() : TEXTS.getString(BIRTHDATE_NULL_TEXT_KEY);

    @Override
    public String toString() {
      return Objects.requireNonNull(dob)
          .format(DateTimeFormatter.ofPattern(TEXTS.getString(BIRTHDATE_FORMAT_TEXT_KEY)));
    }
  }

  public static final class Builder {
    private final LinkedHashMap<
            Class<? extends FootballerPersonalDataField>, FootballerPersonalDataField>
        validatableFootballerPersonalDataFields;

    private final Supplier<FullName> newFullName;
    private final Supplier<Birthdate> newBirthdate;

    private Builder(final FullName fullName, final Birthdate birthdate) {
      validatableFootballerPersonalDataFields =
          collectValidatableFootballerPersonalDataFields(fullName, birthdate);

      this.newFullName = () -> fullName;
      this.newBirthdate = () -> birthdate;
    }

    private static LinkedHashMap<
            Class<? extends FootballerPersonalDataField>, FootballerPersonalDataField>
        collectValidatableFootballerPersonalDataFields(
            final FullName fullName, final Birthdate birthdate) {
      final LinkedHashMap<Class<? extends FootballerPersonalDataField>, FootballerPersonalDataField>
          validatableFootballerPersonalDataFields = LinkedHashMap.newLinkedHashMap(3);

      validatableFootballerPersonalDataFields.put(FullName.class, fullName);
      validatableFootballerPersonalDataFields.put(Birthdate.class, birthdate);

      return validatableFootballerPersonalDataFields;
    }

    public FootballerPersonalData build() {
      FOOTBALLER_PERSONAL_DATA_VALIDATOR.validate(
          validatableFootballerPersonalDataFields, FootballerPersonalData.class.getSimpleName());

      return new FootballerPersonalData(newFullName.get(), newBirthdate.get());
    }
  }
}
