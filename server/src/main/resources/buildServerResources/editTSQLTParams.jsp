<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>

<l:settingsGroup title="tSQLt Database Information">
    <tr>
        <th>
            <label for="tsqlt.server_instance">Database Server: <l:star/></label>
        </th>
        <td>
            <props:textProperty name="tsqlt.server_instance" className="longField" />
            <span class="error" id="error_tsqlt.server_instance"></span>
            <span class="smallNote">Database Server. You can specify the instance name with the syntax Server\Instance, ej. (local)\SQLEXPRESS, additionally you can specify a port using Server:Port</span>
        </td>
    </tr>
    <tr>
        <th>
            <label for="tsqlt.database">Database name: <l:star/></label>
        </th>
        <td>
            <props:textProperty name="tsqlt.database" className="longField" />
            <span class="error" id="error_tsqlt.database"></span>
        </td>
    </tr>
    <tr>
        <th>
            <label for="tsqlt.tests">Tests to run: </label>
        </th>
        <td>
            <props:textProperty name="tsqlt.tests" className="longField" />
            <span class="smallNote">For running specific tests. Leave empty to run all tests.</span>
            <span class="smallNote">Enter either TestClass or TestClass.TestName</span>
        </td>
    </tr>
    <tr>
        <th>
            <label for="tsqlt.options">Additional database connection options: </label>
        </th>
        <td>
            <props:multilineProperty name="tsqlt.options" className="longField" expanded="true" cols="58" rows="6" linkTitle="Additional options" />
            <span class="smallNote">Additional connection options. Options are given in the format option=value separated by semicolon. ej. option1=value;option2=value2</span>
            <span class="smallNote">For complete set of options please check the <a href="http://jtds.sourceforge.net/faq.html#urlFormat" rel="help" target="_blank" title="jTDS FAQ">jTDS FAQ page</a></span>
        </td>
    </tr>
</l:settingsGroup>
<l:settingsGroup title="tSQLt Database Credential Information">
    <tr>
        <th>
            <label for="tsqlt.user_domain">User name: <l:star/></label>
        </th>
        <td>
            <props:textProperty name="tsqlt.user_domain" className="longField" disabled="${propertiesBean.properties['tsqlt.windows_auth'] == 'true'}" />
            <span class="error" id="error_tsqlt.user_domain"></span>
            <span class="smallNote">User for database authentication. You can specify an optional domain using the syntax domain\user</span>
        </td>
    </tr>
    <tr>
        <th>
            <label for="tsqlt.password">Password: <l:star/></label>
        </th>
        <td>
            <props:passwordProperty name="tsqlt.password" className="longField" disabled="${propertiesBean.properties['tsqlt.windows_auth'] == 'true'}" />
            <span class="error" id="error_tsqlt.password"></span>
        </td>
    </tr>
    <tr>
        <th>
            <label for="tsqlt.windows_auth">Use Windows SSO: </label>
        </th>
        <td>
            <c:set var="winAuth_onclick">
                $('tsqlt.user_domain').disabled = this.checked ? 'disabled' : '';
                $('tsqlt.password').disabled = this.checked ? 'disabled' : '';

                if (this.checked) {
                    $('tsqlt.user_domain').value = '';
                    $('tsqlt.password').value = '';
                }
            </c:set>
            <props:checkboxProperty name="tsqlt.windows_auth" onclick="${winAuth_onclick}" />
            <span class="smallNote">Windows SSO will work only in Windows Agents running in a machine joined the Windows Domain</span>
        </td>
    </tr>
</l:settingsGroup>