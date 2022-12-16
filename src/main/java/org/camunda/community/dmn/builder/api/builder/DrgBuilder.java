package org.camunda.community.dmn.builder.api.builder;

import org.camunda.bpm.model.dmn.DmnModelInstance;
import org.camunda.bpm.model.xml.ModelInstance;

public class DrgBuilder {

  private DmnModelInstance modelInstance;

  public DrgBuilder(final DmnModelInstance modelInstance) {
    this.modelInstance = modelInstance;
  }

  public DmnModelInstance done() {
    return modelInstance;
  }

}
