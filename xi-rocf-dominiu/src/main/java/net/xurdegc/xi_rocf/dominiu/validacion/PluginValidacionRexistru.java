package net.xurdegc.xi_rocf.dominiu.validacion;

import static net.bytebuddy.matcher.ElementMatchers.annotationType;
import static net.bytebuddy.matcher.ElementMatchers.hasAnnotation;

import jakarta.validation.Constraint;
import net.bytebuddy.build.Plugin;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.method.ParameterDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.SuperMethodCall;

public class PluginValidacionRexistru implements Plugin {

  @Override
  public boolean matches(TypeDescription target) {
    return target.isRecord()
        && target.getDeclaredMethods().stream()
            .anyMatch(metodu -> metodu.isConstructor() && tienElConstructorValidaciones(metodu));
  }

  @Override
  public Builder<?> apply(
      Builder<?> builder, TypeDescription typeDescription, ClassFileLocator classFileLocator) {
    return builder
        .constructor(this::tienElConstructorValidaciones)
        .intercept(
            SuperMethodCall.INSTANCE.andThen(
                MethodDelegation.to(InterceutorValidacionRexistru.class)));
  }

  private boolean tienElConstructorValidaciones(MethodDescription constructor) {
    return tienElValorVueltuValidaciones(constructor)
        || tienenLosParametrosValidaciones(constructor);
  }

  private boolean tienElValorVueltuValidaciones(MethodDescription metodu) {
    return !metodu
        .getDeclaredAnnotations()
        .asTypeList()
        .filter(hasAnnotation(annotationType(Constraint.class)))
        .isEmpty();
  }

  private boolean tienenLosParametrosValidaciones(MethodDescription metodu) {
    return metodu.getParameters().asDefined().stream().anyMatch(this::tienElParametruValidaciones);
  }

  private boolean tienElParametruValidaciones(ParameterDescription.InDefinedShape parameter) {
    return !parameter
        .getDeclaredAnnotations()
        .asTypeList()
        .filter(hasAnnotation(annotationType(Constraint.class)))
        .isEmpty();
  }

  @Override
  public void close() {
    /* NOTA Implementación innecesaria */
  }
}
