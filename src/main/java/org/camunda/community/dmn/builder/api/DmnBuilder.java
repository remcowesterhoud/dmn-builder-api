package org.camunda.community.dmn.builder.api;

import org.camunda.bpm.model.dmn.Dmn;
import org.camunda.bpm.model.dmn.DmnModelInstance;
import org.camunda.community.dmn.builder.api.builder.DrgBuilder;

public class DmnBuilder {

  public static DrgBuilder createDrg(final String id) {
    final DmnModelInstance modelInstance = Dmn.createEmptyModel();
    return new DrgBuilder(id, modelInstance);
  }
}
