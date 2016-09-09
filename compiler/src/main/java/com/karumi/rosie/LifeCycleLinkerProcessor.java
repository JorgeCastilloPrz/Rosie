package com.karumi.rosie;

import com.google.auto.service.AutoService;
import com.karumi.rosie.annotations.Presenter;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/**
 * Custom annotation processor used to generate PresenterLifeCycleLinker classes at compilation
 * time based on {@link Presenter} annotations.
 */
@AutoService(Processor.class) public class LifeCycleLinkerProcessor extends AbstractProcessor {

  private Elements elementUtils;
  private Types typeUtils;
  private Filer filer;
  private PresenterAnnotationParser annotationParser;

  @Override public synchronized void init(ProcessingEnvironment processingEnvironment) {
    super.init(processingEnvironment);
    elementUtils = processingEnv.getElementUtils();
    typeUtils = processingEnv.getTypeUtils();
    filer = processingEnv.getFiler();
    ErrorLogger.getInstance().setMessager(processingEnv.getMessager());
  }

  /**
   * We want to support java versions up to the current JDK
   *
   * @return latest supported java version
   */
  @Override public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }

  /**
   * @return annotations supported by this processor
   */
  @Override public Set<String> getSupportedAnnotationTypes() {
    Set<String> supportTypes = new LinkedHashSet<>();
    supportTypes.add(Presenter.class.getCanonicalName());
    return supportTypes;
  }

  /**
   * Main method for an annotation processor. Executed once per round environment.
   *
   * @param annotations input set of annotation types.
   * @param roundEnv processing is made by rounds, as auto generated code could include
   * annotations too, and those classes should be processed in subsequent rounds.
   * @return It is important to return false, as annotations supported and processed by this
   * processor could be used as an Input to subsequent running processors.
   */
  @Override public boolean process(Set<? extends TypeElement> annotations,
      RoundEnvironment roundEnv) {
    Set<? extends Element> presenterElements = roundEnv.getElementsAnnotatedWith(Presenter.class);

    return false;
  }
}
