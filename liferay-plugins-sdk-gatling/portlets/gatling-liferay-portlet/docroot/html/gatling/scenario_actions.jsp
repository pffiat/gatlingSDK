<%-- 
	Copyright 2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
--%>
<%@include file="/html/gatling/header.jsp"%>

<c:set var="row" value="${requestScope.WebKeys.SEARCH_CONTAINER_RESULT_ROW}" />
<c:set var="scenario" value="${row.object}" />
<liferay-ui:icon-menu>
	<portlet:renderURL var="editScenarioURL">
		<portlet:param name="render" value="renderScenario" />
		<portlet:param name="scenarioId" value="${scenario.scenario_id }" />
	</portlet:renderURL>

	<liferay-ui:icon image="edit" url="${editScenarioURL}" />

	<portlet:actionURL var="deleteScenarioURL">
		<portlet:param name="action" value="deleteScenario"/>
		<portlet:param name="scenarioId" value="${scenario.scenario_id }" />
		<portlet:param name="simulationId" value="${scenario.simulation_id }" />
	</portlet:actionURL>
	<liferay-ui:icon-delete url="${deleteScenarioURL}" />
</liferay-ui:icon-menu>