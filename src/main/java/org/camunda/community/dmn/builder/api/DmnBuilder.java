package org.camunda.community.dmn.builder.api;

import org.camunda.bpm.model.dmn.Dmn;
import org.camunda.bpm.model.dmn.DmnModelInstance;
import org.camunda.bpm.model.dmn.impl.DmnModelConstants;
import org.camunda.bpm.model.dmn.instance.Definitions;
import org.camunda.community.dmn.builder.api.builder.DrgBuilder;

public class DmnBuilder {

  public static final String EXPORTER = "DMN Builder API";

  public static DrgBuilder createDrg(final String id) {
    final DmnModelInstance modelInstance = Dmn.createEmptyModel();
    final Definitions definitions = modelInstance.newInstance(Definitions.class);
    definitions.setName(id);
    definitions.setNamespace(DmnModelConstants.LATEST_DMN_NS);
    definitions.setExporter(EXPORTER);
    modelInstance.setDefinitions(definitions);
    return new DrgBuilder(modelInstance);
  }
}
