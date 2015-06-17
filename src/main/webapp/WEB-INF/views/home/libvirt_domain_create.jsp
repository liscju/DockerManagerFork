<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html>
<head lang="en">
    <title>DockerManager Libvirt Create Domain</title>
    <%@ include file="/resources/html/includes.html" %>
    <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet"/>
</head>
<body>
    <%@ include file="/resources/html/menu.html" %>
    <div class="container">
        <div class="row">
            <div class="col-xs-3"> <!-- required for floating -->
                <!-- Nav tabs -->
                <ul class="nav nav-tabs tabs-left">
                    <li class="active">
                        <a href="#xml" data-toggle="tab">
                            <spring:message code="home.libvirt_domain_create.xml" text="missing" />
                        </a>
                    </li>
                    <li>
                        <a href="#properties" data-toggle="tab">
                            <spring:message code="home.libvirt_domain_create.properties" text="missing" />
                        </a>
                    </li>
                </ul>
            </div>

            <div class="col-xs-9">
                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane active" id="xml">
                        <form action="${pageContext.request.contextPath}/home/domains_r/create_from_xml" method="POST">
                            <h2><spring:message code="home.libvirt_domain_create.create_domain_from_xml" text="missing" /></h2>
                            <br>
                            <textarea rows="20" cols="80" class="form-control" name="domain_xml"></textarea>
                            <br>
                            <button type="submit" class="btn btn-primary"><spring:message code="home.libvirt_domain_create.create" text="missing" /></button>
                        </form>
                    </div>
                    <div class="tab-pane" id="properties">
                        <form action="${pageContext.request.contextPath}/home/domains_r/create_from_properties" method="POST">
                            <h2><spring:message code="home.libvirt_domain_create.create_domain_from_properties" text="missing" /></h2>

                            <div class="row">
                                <label for="domain_name"><spring:message code="home.libvirt_domain_create.domain_name" text="missing" />:</label>
                                <input type="text" class="form-control" id="domain_name" name="domain_name"></input>
                                <br>
                            </div>
                            <div class="row">
                                <label for="domain_memory"><spring:message code="home.libvirt_domain_create.domain_memory" text="missing" />:</label>
                                <input type="text" class="form-control" id="domain_memory" name="domain_memory" value="128000"></input>
                                <br>
                            </div>
                            <div class="row">
                                <label for="domain_vcpu"><spring:message code="home.libvirt_domain_create.domain_vcpu" text="missing" />:</label>
                                <input type="text" class="form-control" id="domain_vcpu" name="domain_vcpu" value="1"></input>
                                <br>
                            </div>
                            <div class="row">
                                <label for="domain_emulator"><spring:message code="home.libvirt_domain_create.domain_emulator" text="missing" />:</label>
                                <input type="text" class="form-control" id="domain_emulator" name="domain_emulator" value="/usr/libexec/qemu-kvm"></input>
                                <br>
                            </div>
                            <div class="row">
                                <label for="domain_sourcefile"><spring:message code="home.libvirt_domain_create.domain_source_file" text="missing" />:</label>
                                <input type="text" class="form-control" id="domain_sourcefile" name="domain_sourcefile" value="/var/lib/libvirt/images/"></input>
                                <br>
                            </div>

                            <button type="submit" class="btn btn-primary"><spring:message code="home.libvirt_domain_create.create" text="missing" /></button>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>
</body>
</html>