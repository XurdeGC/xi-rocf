package net.xurdegc.xi_rocf.dominiu.validacion;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ElementKind;
import jakarta.validation.Path.Node;
import jakarta.validation.Path.ParameterNode;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.lang.reflect.Constructor;
import java.util.Set;
import java.util.stream.Collectors;
import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;

public class InterceutorValidacionRexistru {

  public static final String NUN_SE_PUDO_CREAR_REXISTRU_DEL_TIPU =
      "Nun se pudo crear rexistru del tipu ";
  private static final Validator VALIDADOR =
      Validation.buildDefaultValidatorFactory().getValidator();

  private InterceutorValidacionRexistru() {}

  public static <T> void validaRexistru(
      @Origin Constructor<T> constructor, @AllArguments Object[] parametros) {
    Set<ConstraintViolation<T>> violacionesRexistru =
        VALIDADOR.forExecutables().validateConstructorParameters(constructor, parametros);

    if (!violacionesRexistru.isEmpty()) {
      String mensaxeViolaciones =
          violacionesRexistru.stream()
              .sorted(InterceutorValidacionRexistru::comparaViolacionesPorIndizDeParametru)
              .map(
                  violacion ->
                      vuelviElNomeDelParametruDeLaViolacion(violacion)
                          + " - "
                          + violacion.getMessage())
              .collect(Collectors.joining(System.lineSeparator()));

      throw new ConstraintViolationException(
          NUN_SE_PUDO_CREAR_REXISTRU_DEL_TIPU
              + constructor.getDeclaringClass().getSimpleName()
              + System.lineSeparator()
              + mensaxeViolaciones,
          violacionesRexistru);
    }
  }

  private static int comparaViolacionesPorIndizDeParametru(
      ConstraintViolation<?> violacion1, ConstraintViolation<?> violacion2) {
    return Integer.compare(
        vuelviElIndizDelParametruDeLaViolacion(violacion1),
        vuelviElIndizDelParametruDeLaViolacion(violacion2));
  }

  private static String vuelviElNomeDelParametruDeLaViolacion(ConstraintViolation<?> violacion) {
    String nomeParametru = null;

    for (Node nueduValidacion : violacion.getPropertyPath()) {
      if (nueduValidacion.getKind() == ElementKind.PARAMETER) {
        nomeParametru = nueduValidacion.getName();
        break;
      }
    }

    return nomeParametru == null ? "" : nomeParametru;
  }

  private static int vuelviElIndizDelParametruDeLaViolacion(ConstraintViolation<?> violacion) {
    Integer indizParametru = null;

    for (Node nueduValidacion : violacion.getPropertyPath()) {
      if (nueduValidacion.getKind() == ElementKind.PARAMETER) {
        indizParametru = nueduValidacion.as(ParameterNode.class).getParameterIndex();
        break;
      }
    }

    return indizParametru == null ? -1 : indizParametru;
  }
}
