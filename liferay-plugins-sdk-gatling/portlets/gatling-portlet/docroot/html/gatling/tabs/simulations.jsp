<%@include file="/html/gatling/header.jsp"%>

<%--NavBar --%>
<div class="navbar">
	<div class="navbar-inner">
		<ul class="nav">
			<li><a id="newSimulation" href="#"> <i class="icon-plus"></i>
					<liferay-ui:message key="simulation-list-btn-add-simulation" /></a></li>
			<c:if test="${not empty listSimulation }">
				<li><a id="exportToggle" href="#"><i class="icon-print"></i>
						<liferay-ui:message key="simulation-list-export" /></a></li>
			</c:if>
			<portlet:renderURL var="testURL">
				<portlet:param name="page" value="editSimulation" />
			</portlet:renderURL>
			<li><a href="${testURL }">Test Simulation</a></li>
		</ul>
	</div>
</div>

<portlet:resourceURL var="resourceUrl" />
<aui:form action="${resourceUrl}" method="post" name="fmExport">
	<aui:input type="hidden" name="gatlingVersion" />
	<%--
		Search container (table) 
	--%>
	<liferay-ui:search-container
		emptyResultsMessage="simulation-list-empty">
		<%--List of data to display --%>
		<liferay-ui:search-container-results results="${listSimulation }"
			total="${listSimulation.size() }" />
		<%--for each  column --%>
		<liferay-ui:search-container-row
			className="com.excilys.liferay.gatling.model.Simulation"
			keyProperty="simulation_id" modelVar="simulation">
			<%-- checkbox --%>
			<liferay-ui:search-container-column-text>
				<c:choose>
					<c:when test="${MapSimulation.get(simulation)[1] == 2}">
						<input type="checkbox" class="checkLine"
							name="<portlet:namespace/>export"
							id="<portlet:namespace/>checkAll"
							value="${simulation.simulation_id}" />
					</c:when>
					<c:otherwise>
						<input type="checkbox" class="checkLine" disabled="disabled" />
						<liferay-ui:icon-help
							message="message-help-info-state-simulation-important" />
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>
			<%--edit url --%>
			<portlet:renderURL var="editSimulationURL">
				<portlet:param name="page" value="/html/gatling/editSimulation.jsp" />
				<portlet:param name="simulationId"
					value="${simulation.simulationId}" />
			</portlet:renderURL>
			<liferay-ui:search-container-column-text
				name="simulation-list-table-header-name" value="${simulation.name }"
				href="${editSimulationURL}" />
			<%-- number of scenario --%>
			<liferay-ui:search-container-column-text
				name="simulation-list-table-header-scenarion"
				value="${MapSimulation.get(simulation)[0] }" />
			<%--status --%>
			<liferay-ui:search-container-column-text
				name="simulation-list-table-header-state">
				<c:choose>
					<c:when test="${MapSimulation.get(simulation)[1] == 2}">
						<span class="badge badge-success"><i class="icon-ok"></i></span>
						<liferay-ui:icon-help
							message="message-help-info-state-simulation-success" />
					</c:when>
					<c:when test="${MapSimulation.get(simulation)[1] == 1}">
						<span class="badge badge-warning"><i class="icon-pencil"></i></span>
						<liferay-ui:icon-help
							message="message-help-info-state-simulation-warning" />
					</c:when>
					<c:otherwise>
						<span class="badge badge-important"><i
							class="icon-ban-circle"></i> </span>
						<liferay-ui:icon-help
							message="message-help-info-state-simulation-important" />
					</c:otherwise>
				</c:choose>
			</liferay-ui:search-container-column-text>
			<%--delete button --%>
			<liferay-ui:search-container-column-text name="delete">
				<portlet:actionURL var="deleteSimulationURL" name="removeSimulation">
					<portlet:param name="simulationId"
						value="${simulation.simulationId }" />
				</portlet:actionURL>

				<liferay-ui:icon-delete url="${deleteSimulationURL}" />
			</liferay-ui:search-container-column-text>
		</liferay-ui:search-container-row>
		<%--iterate and display the list --%>
		<liferay-ui:search-iterator paginate="false" />
	</liferay-ui:search-container>
</aui:form>

<div id="exportModalTemplate" hidden="true">
	<h5>
		<liferay-ui:message key="simulation-list-export" />
	</h5>
	<aui:select label="simulation-list-version-choice"
		name="gatlingVersionSelect">
		<%@include file="/html/gatling/template/gatlingVersionOption.jsp"%>
	</aui:select>
</div>

<%--submit to addSimulation --%>
<portlet:actionURL name="addSimulation" var="addSimulationURL"
	windowState="normal" />
<%-- add simulation form --%>
<div id="newFormSimulation" hidden="true">
	<aui:form action="${addSimulationURL}" name="fm">
		<div class="well well-small">
			<liferay-ui:message key="create-simulation-help" />
		</div>
		<aui:input label="simulation-list-form-name-simulation"
			name="simulationName">
			<aui:validator name="required" />
			<aui:validator name="custom" errorMessage="simulation-name-syntaxe">
				function (val, fieldNode, ruleValue) {
					return /^[\w\s]+$/.test(val);
				}
			</aui:validator>
			<%--Create JS List of simulations' names --%>
			<c:set var="JSList">[</c:set>
			<c:forEach items="${listSimulation }" var="simulation">
				<c:set var="JSList">${JSList},${simulation.name }</c:set>
			</c:forEach>
			<c:set var="JSList">${JSList }]</c:set>
			<aui:validator name="custom"
				errorMessage="simulation-name-already-used">
			 	function (val, fieldNode, ruleValue) {
					var result = false;
					var list = ${JSList };
					if (list.indexOf(val) == -1) {
						result = true;
					}
					return result;
				}
			</aui:validator>
		</aui:input>
		<aui:button type="submit" />
	</aui:form>
</div>

<script type="text/javascript">
	AUI()
			.use(
					'aui-modal',
					'aui-base',
					'event',
					function(A) {
						var modal = new A.Modal(
								{
									bodyContent : A.one("#newFormSimulation")
											.html(),
									centered : true,
									headerContent : '<h3><liferay-ui:message key="simulation-list-form-header" /></h3>',
									modal : true,
									resizable : false,
									visible : false,
									zIndex : 100,
								}).render();
						//Remove the form template (no duplication otherwise it will never validate the pop-up)
						A.one("#newFormSimulation").empty();

						A.one('#newSimulation').on('click', function() {
							modal.show();
						});

						A
								.all('#exportToggle')
								.on(
										'click',
										function() {
											if (A.all(".checkLine:checked")
													.size() > 0) {
												var modalExport = new A.Modal(
														{
															bodyContent : A
																	.one(
																			"#exportModalTemplate")
																	.html(),
															centered : true,
															headerContent : '<h3><liferay-ui:message key="simulation-list-export" /></h3>',
															modal : true,
															resizable : false,
															zIndex : 100,
														}).render();

												modalExport
														.addToolbar([
																{
																	label : '<liferay-ui:message key="cancel" />',
																	on : {
																		click : function() {
																			modalExport
																					.hide();
																		}
																	}
																},
																{
																	label : '<liferay-ui:message key="export" />',
																	cssClass : 'btn-info',
																	on : {
																		click : function() {
																			modalExport
																					.hide();
																			var version = A
																					.one(
																							"#<portlet:namespace/>gatlingVersionSelect")
																					.val();
																			A
																					.one(
																							"#<portlet:namespace/>gatlingVersion")
																					.val(
																							version);
																		}
																	}
																} ])
											} else {
												alert("<liferay-ui:message key='no-selected-simulation' />");
											}
										});

						A
								.one("#help")
								.on(
										'click',
										function(A) {
											Liferay.Util
													.openWindow({
														dialog : {
															modal : true,
															constrain : true,
															cache : true
														},
														uri : '${helpURL}',
														title : '<liferay-ui:message key="help-how-to-use-load-test-portlet"/>'
													});
										});

						/*
						 * Multi select
						 */
						var lastChecked = null;
						var checkboxes = A.all(".checkLine");
						checkboxes.each(function() {
							this.on('click', function(event) {
								if (!lastChecked) {
									lastChecked = this;
									return;
								}

								if (event.shiftKey) {
									var start = checkboxes.indexOf(this);
									var end = checkboxes.indexOf(lastChecked);

									checkboxes.slice(Math.min(start, end),
											Math.max(start, end) + 1).attr(
											'checked',
											lastChecked.get("checked"));

								}
								lastChecked = this;
							});
						});

					});
</script>

<script type="text/javascript">
<%@ include file="/js/tourSimulation.js" %>
</script>