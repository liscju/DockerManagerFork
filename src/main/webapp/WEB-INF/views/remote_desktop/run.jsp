<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
	<title>${domain_name}</title>
	<body>

		<object type="application/x-java-applet" height="1" width="1">
			<param name="archive" value="<c:url value="/resources/lib/tightvnc-jviewer.jar"/>"/>
			<param name="code" value="com.glavsoft.viewer.Viewer" />
			<param name="Host" value="${host}" />
			<param name="Port" value="${port}" />
		</object>
	</body>
</html>

