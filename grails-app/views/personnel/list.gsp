
<%@ page import="covoiturageups.Personnel" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'personnel.label', default: 'Personnel')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-personnel" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-personnel" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="prenom" title="${message(code: 'personnel.prenom.label', default: 'Prenom')}" />
					
						<g:sortableColumn property="nom" title="${message(code: 'personnel.nom.label', default: 'Nom')}" />
					
						<g:sortableColumn property="email" title="${message(code: 'personnel.email.label', default: 'Email')}" />
					
						<g:sortableColumn property="adresse" title="${message(code: 'personnel.adresse.label', default: 'Adresse')}" />
					
						<g:sortableColumn property="latitude" title="${message(code: 'personnel.latitude.label', default: 'Latitude')}" />
					
						<g:sortableColumn property="longitude" title="${message(code: 'personnel.longitude.label', default: 'Longitude')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${personnelInstanceList}" status="i" var="personnelInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${personnelInstance.id}">${fieldValue(bean: personnelInstance, field: "prenom")}</g:link></td>
					
						<td>${fieldValue(bean: personnelInstance, field: "nom")}</td>
					
						<td>${fieldValue(bean: personnelInstance, field: "email")}</td>
					
						<td>${fieldValue(bean: personnelInstance, field: "adresse")}</td>
					
						<td>${fieldValue(bean: personnelInstance, field: "latitude")}</td>
					
						<td>${fieldValue(bean: personnelInstance, field: "longitude")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${personnelInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
