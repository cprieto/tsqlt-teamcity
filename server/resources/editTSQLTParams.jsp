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