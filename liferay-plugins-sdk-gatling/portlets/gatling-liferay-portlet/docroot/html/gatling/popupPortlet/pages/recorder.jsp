<%@page import="com.liferay.portal.kernel.util.CookieKeys"%>
<%@page import="com.liferay.portal.kernel.util.HttpUtil"%>
<%@include file="/html/gatling/header.jsp"%>

<%
	String portletId = ParamUtil.getString(request, "pagePortletId");
	String instancePortletName = PortletLocalServiceUtil.getPortletById(portletId).getDisplayName();
	long  groupId =  ParamUtil.getLong(request, "groupId");
	System.out.println("portlet id= "+portletId);
%>

<h3><liferay-ui:message key="recorder-for" />: <%= instancePortletName %></h3>

<c:if test='<%= ((portletId != null) &&  (!"0".equals(portletId))) %>'>
	<aui:input name="useCaseRecordName" inlineField="true" ></aui:input>
	<div class="btn-group inline-button">
	    <button class="btn btn-warning"><i class="icon-play"></i> <liferay-ui:message key="record" /></button>
	</div>
	<hr/>
	<liferay-portlet:renderURL var="portletURL" portletName="<%= portletId %>" windowState="pop_up" doAsGroupId="<%= groupId %>" />
	<iframe src="${portletURL }" width="95%" style="min-height: 600px;padding: 10px;padding-right: 0px;"></iframe>
</c:if>
