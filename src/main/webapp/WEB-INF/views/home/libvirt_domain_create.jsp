<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <li class="active"><a href="#xml" data-toggle="tab">XML</a></li>
                </ul>
            </div>

            <div class="col-xs-9">
                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane active" id="html">
                        <form action="${pageContext.request.contextPath}/home/domains_r/create" method="POST">
                            <h2>Create domain from xml</h2>
                            <br>
                            <textarea rows="20" cols="80" class="form-control" name="domain_xml"></textarea>
                            <br>
                            <button type="submit" class="btn btn-primary">Create</button>
                        </form>
                    </div>
                </div>

            </div>
        </div>
    </div>
</body>
</html>