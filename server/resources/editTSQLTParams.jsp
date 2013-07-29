<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>

<l:settingsGroup title="tSQLt Database Options">
    <tr>
        <th>
            <label for="tsqlt.connection">JDBC Connection String: <span class="mandatoryAsterix" title="Mandatory field">*</span></label>
        </th>
        <td>
            <props:textProperty name="tsqlt.connection" className="longField" />
            <span class="error" id="error_tsqlt.connection"></span>
            <span class="smallNote">JDBC Connection string for database containing the tests to run</span>
        </td>
    </tr>
</l:settingsGroup>
<l:settingsGroup title="tSQLt Database Information">
    <tr>
        <th>
            <label for="tsqlt.server_instance">Database Server: <span class="mandatoryAsterix" title="Mandatory field">*</span></label>
        </th>
        <td>
            <props:textProperty name="tsqlt.server_instance" className="longField" />
            <span class="error" id="error_tsqt.server_instance"></span>
            <span class="smallNote">Database Server. You can specify the instance name with the syntax Server\Instance, ej. (local)\SQLEXPRESS</span>
        </td>
    </tr>
    <tr>
        <th>
            <label for="tsqlt.database">Database: <span class="mandatoryAsterix" title="Mandatory field">*</span></label>
        </th>
        <td>
            <props:textProperty name="tsqlt.database" className="longField" />
            <span class="error" id="error_tsqt.database"></span>
            <span class="smallNote">Database catalog, ej. Marketing</span>
        </td>
    </tr>
</l:settingsGroup>
<l:settingsGroup title="tSQLt Database Credential information">
    <tr>
        <th>
            <label for="tsqlt.user_domain">User: <span class="mandatoryAsterix" title="Mandatory field">*</span></label>
        </th>
        <td>
            <props:textProperty name="tsqlt.user_domain" className="longField" />
            <span class="error" id="error_tsqt.user_domain"></span>
            <span class="smallNote">User for database authentication. You can specify an optional domain using the syntax domain\user</span>
        </td>
    </tr>
    <tr>
        <th>
            <label for="tsqlt.password">Password: <span class="mandatoryAsterix" title="Mandatory field">*</span></label>
        </th>
        <td>
            <props:passwordProperty name="secure:tsqlt.password" className="longField"/>
            <span class="error" id="error_tsqlt.password"></span>
            <span class="smallNote">User password for login</span>
        </td>
    </tr>
</l:settingsGroup>