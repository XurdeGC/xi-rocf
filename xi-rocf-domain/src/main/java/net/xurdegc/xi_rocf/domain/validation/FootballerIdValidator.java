package net.xurdegc.xi_rocf.domain.validation;

import java.util.*;
import net.xurdegc.xi_rocf.domain.validation.base.IdValidator;

public class FootballerIdValidator extends IdValidator {
  private static final String FOOTBALLER_ID_LABEL_MISSING_MESSAGE_KEY =
      "footballer_id.label.missing";

  @Override
  public void validate(final UUID validatableFootballerIdLabel, final String footballerIdTypeName) {
    validateId(
        validatableFootballerIdLabel,
        footballerIdTypeName,
        FOOTBALLER_ID_LABEL_MISSING_MESSAGE_KEY);
  }
}
