<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head>
    <title>DockerManager Libvirt server</title>
    <%@ include file="/resources/html/includes.html" %>
    <link href="${pageContext.request.contextPath}/resources/css/domain_control.css" rel="stylesheet"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/increment.js"></script>

</head>
<body>
<%@ include file="/resources/html/menu.html" %>
<div class="libvirt_server">
    <div class="starter-template">
        <h1>LibvirtManager</h1>

        <p class="lead">Domain: ${domain_name}</p>
        <a href="${pageContext.request.contextPath}/home/domains_r/${domain_name}/run_remote" target="_blank">
            <spring:message code="home.libvirt_r_details.vnc_connection" text="missing" />
        </a>

    </div>
</div>


<div class="rdomain">
    <div class="starter-template">
        <ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
            <li class="active">
                <a href="#info" data-toggle="tab"><spring:message code="home.libvirt_r_details.info" text="missing" /></a>
            </li>
            <li>
                <a href="#control" data-toggle="tab"><spring:message code="home.libvirt_r_details.control" text="missing" /></a>
            </li>
        </ul>

        <div id="my-tab-content" class="tab-content">
            <div class="tab-pane active" id="info">

                <table class="table">
                    <tbody>
                        <tr>
                            <td><spring:message code="home.libvirt_r_details.domainId" text="missing" /></td>
                            <td>${rd_info.domainId}</td>
                        </tr>
                        <tr>
                            <td><spring:message code="home.libvirt_r_details.vcpus" text="missing" /></td>
                            <td>${rd_info.vcpuCount}</td>
                        </tr>
                        <tr>
                            <td><spring:message code="home.libvirt_r_details.max_cpu" text="missing" /></td>
                            <td>${rd_info.maxCpu}</td>
                        </tr>
                        <tr>
                            <td><spring:message code="home.libvirt_r_details.max_cpu" text="missing" /></td>
                            <td>${rd_info.maxMemory}</td>
                        </tr>
                        <tr>
                            <td><spring:message code="home.libvirt_r_details.max_memory" text="missing" /></td>
                            <td>${rd_info.maxMemory}</td>
                        </tr>
                        <tr>
                            <td><spring:message code="home.libvirt_r_details.vnc_port" text="missing" /></td>
                            <td>${rd_info.vncPort}</td>
                        </tr>
                        <tr>
                            <td><spring:message code="home.libvirt_r_details.memory" text="missing" /></td>
                            <td>${rd_info.memory}</td>
                        </tr>
                    </tbody>
                </table>

            </div>

            <div class="tab-pane" id="control">


                <form action="${pageContext.request.contextPath}/home/domain_action" method="POST">
                    <input type="hidden" id="domain_name" name="domain_name" value="${domain_name}">
                    <select name="domain_action">
                        <option value="Destroy"><spring:message code="home.libvirt_r_details.stop" text="missing" /> ${domain_name}</option>
                        <option value="Create"><spring:message code="home.libvirt_r_details.start" text="missing" /> ${domain_name}</option>
                        <option value="Undefine"><spring:message code="home.libvirt_r_details.undefine" text="missing" /> ${domain_name}</option>
                    </select>
                    <input type="submit" value="<spring:message code="home.libvirt_r_details.submit" text="missing" />">
                </form>


                <form method="post" action="${pageContext.request.contextPath}/home/change_domain">
                    <table class="table">
                        <tbody>
                        <tr>
                            <input type="hidden" id="domain_name" name="domain_name" value="${domain_name}">
                            <td>
                                <div class="numbers-row">
                                    <spring:message code="home.libvirt_r_details.max_cpu" text="missing" />
                                    <input type="text" name="MaxCpu" id="MaxCpu" value="${rd_info.maxCpu}">
                                </div>
                            </td>
                            <td>
                                <div class="numbers-row">
                                    <spring:message code="home.libvirt_r_details.vcpus" text="missing" />
                                    <input type="text" name="vCPUs" id="vCPUs" value="${rd_info.vcpuCount}">
                                </div>
                            </td>
                            <td>
                                <div class="numbers-row">
                                    <spring:message code="home.libvirt_r_details.max_memory" text="missing" />
                                    <input type="text" name="MaxMemory" id="MaxMemory" value="${rd_info.maxMemory}">
                                </div>
                            </td>
                            <td>
                                <div class="numbers-row">
                                    <spring:message code="home.libvirt_r_details.memory" text="missing" />
                                    <input type="text" name="Memory" id="Memory" value="${rd_info.memory}">
                                </div>
                            </td>
                            <td>
                                <input type="submit" value="<spring:message code="home.libvirt_r_details.submit" text="missing" />" id="submit">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>


</body>
</html>
