package org.camunda.community.dmn.builder.api.builder;

import java.util.function.Consumer;
import org.camunda.bpm.model.dmn.DmnModelInstance;
import org.camunda.bpm.model.dmn.HitPolicy;
import org.camunda.bpm.model.dmn.instance.Decision;
import org.camunda.bpm.model.dmn.instance.DecisionTable;
import org.camunda.bpm.model.dmn.instance.InformationRequirement;
import org.camunda.bpm.model.dmn.instance.LiteralExpression;
import org.camunda.bpm.model.dmn.instance.RequiredDecisionReference;
import org.camunda.bpm.model.dmn.instance.Text;
import org.camunda.bpm.model.dmn.instance.Variable;
import org.camunda.community.dmn.builder.api.model.VariableType;

public class DecisionBuilder {

  private final Decision decision;
  private final DmnModelInstance modelInstance;

  public DecisionBuilder(final Decision decision, final DmnModelInstance modelInstance) {
    this.decision = decision;
    this.modelInstance = modelInstance;
  }

  public DecisionBuilder literalExpression(final String expression) {
    final var literalExpression = modelInstance.newInstance(LiteralExpression.class);
    final var text = modelInstance.newInstance(Text.class);
    text.setTextContent(expression);
    literalExpression.addChildElement(text);
    decision.addChildElement(literalExpression);
    return this;
  }

  public DecisionBuilder variable(final String name, final VariableType variableType) {
    final var variable = modelInstance.newInstance(Variable.class);
    variable.setName(name);
    variable.setTypeRef(variableType.getTypeRef());
    decision.addChildElement(variable);
    return this;
  }

  public DecisionBuilder decisionTable(
      final HitPolicy hitPolicy, final Consumer<DecisionTableBuilder> consumer) {
    final var decisionTable = modelInstance.newInstance(DecisionTable.class);
    decisionTable.setHitPolicy(hitPolicy);
    decision.addChildElement(decisionTable);
    final var decisionTableBuilder = new DecisionTableBuilder(decisionTable, modelInstance);
    consumer.accept(decisionTableBuilder);
    return this;
  }

  public DecisionBuilder requires(final String decisionId) {
    final var informationRequirement = modelInstance.newInstance(InformationRequirement.class);
    final var requiredDecision = modelInstance.newInstance(RequiredDecisionReference.class);
    requiredDecision.setHref("#" + decisionId);
    informationRequirement.addChildElement(requiredDecision);
    decision.addChildElement(informationRequirement);
    return this;
  }
}
