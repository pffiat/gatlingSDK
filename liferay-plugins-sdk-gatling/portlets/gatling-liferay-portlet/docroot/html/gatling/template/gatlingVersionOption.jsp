<%-- 
	Copyright 2014 eBusiness Information, Groupe Excilys (www.ebusinessinformation.fr)
--%>
<%@include file="/html/gatling/header.jsp"%>

<c:choose>
	<c:when test="${gatlingVersion == 1}">
		<c:set var="selected1" value="true"/>
	</c:when>
	<c:otherwise>
		<c:set var="selected2" value="true"/>
	</c:otherwise>
</c:choose>
<aui:option value="2" selected="${selected2 }" >Gatling 2.0.X</aui:option>
<aui:option value="1" selected="${selected1 }" >Gatling 1.5</aui:option>