<%@ page import="covoiturageups.Personnel" %>



<div class="fieldcontain ${hasErrors(bean: personnelInstance, field: 'prenom', 'error')} required">
	<label for="prenom">
		<g:message code="personnel.prenom.label" default="Prenom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="prenom" required="" value="${personnelInstance?.prenom}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personnelInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="personnel.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" required="" value="${personnelInstance?.nom}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personnelInstance, field: 'email', 'error')} required">
	<label for="email">
		<g:message code="personnel.email.label" default="Email" />
		<span class="required-indicator">*</span>
	</label>
	<g:field type="email" name="email" pattern="${personnelInstance.constraints.email.matches}" required="" value="${personnelInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personnelInstance, field: 'adresse', 'error')} required">
	<label for="adresse">
		<g:message code="personnel.adresse.label" default="Adresse" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="adresse" required="" value="${personnelInstance?.adresse}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: personnelInstance, field: 'latitude', 'error')} required">
	<label for="latitude">
		<g:message code="personnel.latitude.label" default="Latitude" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="latitude" value="${fieldValue(bean: personnelInstance, field: 'latitude')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: personnelInstance, field: 'longitude', 'error')} required">
	<label for="longitude">
		<g:message code="personnel.longitude.label" default="Longitude" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="longitude" value="${fieldValue(bean: personnelInstance, field: 'longitude')}" required=""/>
</div>

