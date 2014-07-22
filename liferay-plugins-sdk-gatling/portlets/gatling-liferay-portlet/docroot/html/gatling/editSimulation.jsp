<%@include file="/html/gatling/header.jsp"%>

<portlet:renderURL var="backURL">
	<portlet:param name="page" value="/html/gatling/view.jsp" />
</portlet:renderURL>
<%--entete --%>
<c:set var="entete">
	<liferay-ui:message key="simulation-edit-header" arguments="${simulation.name}" />
</c:set>
<%--
	lien vers la FAQ 
--%>
<div class="well well-small">
	<a target="blank" href="https://github.com/excilys/gatling/wiki/Getting-Started">
		<span class="label label-warning"><liferay-ui:message key="help-faq-gatling"/></span>
	</a>
	<a target="blank" href="#">
	<span class="label"><liferay-ui:message key="help-how-to-use-portlet"/></span>
	</a>
	<a target="blank" href="#">
	<span class="label label-info"><liferay-ui:message key="help-what-scenario"/></span>
	</a>
</div>

<%--
	session errors 
--%>
<liferay-ui:error key="scenario-name-required" message="scenario-name-required" />
<liferay-ui:error key="simulation-duration-required" message="simulation-duration-required" />
<liferay-ui:error key="simulation-users_per_seconds-required" message="simulation-users_per_seconds-required" />
<liferay-ui:header title="${entete}" backURL="${backURL}" />
<%--
	Contenu page 

 --%>
<div>
	<%--Search container (tableau) --%>
	<liferay-ui:search-container emptyResultsMessage="scenario-list-empty">
		<%--Liste sur laquelle on travail --%>
		<liferay-ui:search-container-results results="${listScenario }" total="${listScenario.size() }" />
		<%--itération des colonnes --%>
		<liferay-ui:search-container-row className="com.liferay.sample.model.Scenario" keyProperty="scenario_id" modelVar="scenario">
			<portlet:renderURL var="editScenarioURL">
				<portlet:param name="page" value="/html/gatling/editScenario.jsp" />
				<portlet:param name="scenarioId" value="${scenario.scenario_id }" />
			</portlet:renderURL>
			<%--un champs texte --%>
			<liferay-ui:search-container-column-text name="simulation-edit-table-header-name" value="${scenario.name } (${MapScenario.get(scenario) } requests)"
				href="${editScenarioURL}" />
			<%--menu action --%>
			<liferay-ui:search-container-column-jsp align="right" path="/html/gatling/scenario_actions.jsp" />
		</liferay-ui:search-container-row>
		<%--itere et affiche la liste --%>
		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</div>

<aui:button id="newScenario" value="simulation-edit-btn-add-scenario"></aui:button>

<%--redirect to addSimulation --%>
<portlet:actionURL name="addScenario" var="addScenarioURL">
	<portlet:param name="page" value="/html/gatling/editSimulation.jsp" />
	<portlet:param name="simulationId" value="${simulation.simulation_id}" />
</portlet:actionURL>
<%--Formulaire d'ajout --%>
<div id="newFormScenario" hidden="true">
	<aui:form action="${addScenarioURL}" name="scenario_fm" id="scenario_fm">
		<aui:input label="simulation-edit-form-nom-scenario" name="scenarioName">
			<aui:validator name="required"></aui:validator>
		</aui:input>
		<aui:fieldset>
			<aui:select label="simulation-edit-form-sites" name="sites" showEmptyOption="true">
				<c:forEach var="group" items="${listGroup}">
					<aui:option label="${group.name}" value="${group.groupId}" />
				</c:forEach>
			</aui:select>
		</aui:fieldset>

		<aui:button name="ajouter-scenario" type="submit"></aui:button>
	</aui:form>
</div>



<aui:script>
YUI().use(
  'aui-modal',
  function(Y) {
    var modal = new Y.Modal(
      {
        bodyContent: AUI().one("#newFormScenario").html(),
        centered: true,
        headerContent: '<h3><liferay-ui:message key="simulation-edit-form-header" /></h3>',
        modal: true,
        resizable: false,
        visible: false,
        zIndex: 100,
        width: 450
      }
    ).render();
    
    Y.one('#newScenario').on(
      'click',
      function() {
        modal.show();
      }
    );
  }
);
</aui:script>
