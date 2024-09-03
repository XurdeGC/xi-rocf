package net.xurdegc.xi_rocf.domain.validation;

import java.util.LinkedHashMap;
import net.xurdegc.xi_rocf.domain.validation.base.Validator;
import net.xurdegc.xi_rocf.domain.vo.FootballerPersonalData.FootballerPersonalDataField;

public class FootballerPersonalDataValidator
    extends Validator<
        LinkedHashMap<Class<? extends FootballerPersonalDataField>, FootballerPersonalDataField>> {
  @Override
  public void validate(
      LinkedHashMap<Class<? extends FootballerPersonalDataField>, FootballerPersonalDataField>
          validatableFootballerPersonalDataFields,
      String footballerPersonalDataTypeName) {
    validationErrors.clear();

    validatableFootballerPersonalDataFields.forEach(this::validateFootballerPersonalDataField);

    if (!validationErrors.isEmpty()) {
      buildIllegalArgumentException(footballerPersonalDataTypeName);
    }
  }

  private void validateFootballerPersonalDataField(
      Class<? extends FootballerPersonalDataField> footballerPersonalDataFieldType,
      FootballerPersonalDataField footballerPersonalDataField) {}
}
