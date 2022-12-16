package org.camunda.community.dmn.builder.api.model;

public enum VariableType {
  STRING("string"),
  BOOLEAN("boolean"),
  NUMBER("number"),
  DATE("date"),
  TIME("time"),
  DATETIME("dateTime"),
  DAY_TIME_DURATION("dayTimeDuration"),
  YEAR_MONTH_DURATION("yearMonthDuration"),
  ANY("Any");

  private final String typeRef;

  VariableType(final String typeRef) {
    this.typeRef = typeRef;
  }

  public String getTypeRef() {
    return typeRef;
  }
}
