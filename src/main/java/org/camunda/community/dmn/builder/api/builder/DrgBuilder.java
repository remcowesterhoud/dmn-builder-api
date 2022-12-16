package org.camunda.community.dmn.builder.api.builder;

import java.util.function.Consumer;
import org.camunda.bpm.model.dmn.DmnModelInstance;
import org.camunda.bpm.model.dmn.impl.DmnModelConstants;
import org.camunda.bpm.model.dmn.instance.Decision;
import org.camunda.bpm.model.dmn.instance.Definitions;
import org.camunda.bpm.model.dmn.instance.DrgElement;

public class DrgBuilder {
  private static final String EXPORTER = "DMN Builder API";
  private final DmnModelInstance modelInstance;
  private final Definitions definitions;

  public DrgBuilder(final String id, final DmnModelInstance modelInstance) {
    this.modelInstance = modelInstance;
    definitions = modelInstance.newInstance(Definitions.class);
    definitions.setName(id);
    definitions.setNamespace(DmnModelConstants.LATEST_DMN_NS);
    definitions.setExporter(EXPORTER);
    modelInstance.setDefinitions(definitions);
  }

  public DmnModelInstance done() {
    return modelInstance;
  }

  public DrgBuilder decision(final String id, final Consumer<DecisionBuilder> consumer) {
    final Decision decision = createChild(Decision.class, id);
    definitions.addChildElement(decision);
    final var decisionBuilder = new DecisionBuilder(decision, modelInstance);
    consumer.accept(decisionBuilder);
    return this;
  }

  private <T extends DrgElement> T createChild(final Class<T> typeClass, final String id) {
    final T childInstance = modelInstance.newInstance(typeClass);
    childInstance.setId(id);
    childInstance.setName(id);
    return childInstance;
  }
}
