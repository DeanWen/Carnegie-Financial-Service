<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="pragma" content="no-cache">
<title>DAO Test</title>
<style>
.menu-head {
	font-size: 10pt;
	font-weight: bold;
	color: black;
}

.menu-item {
	font-size: 10pt;
	color: black
}
</style>
</head>

<body>
	<table cellpadding="4" cellspacing="0">
		<tr>
			<!-- Banner row across the top -->
			<td width="130" bgcolor="#99FF99"></td>
			<td bgcolor="#99FF99">&nbsp;</td>
			<td width="500" bgcolor="#99FF99">
				<p align="center">

					<c:choose>
						<c:when test="${title}">
							<font size="5">${title}</font>
						</c:when>
						<c:otherwise>
							<font size="7">DAO Test</font>
						</c:otherwise>
					</c:choose>
				</p>
			</td>
		</tr>

		<!-- Spacer row -->
		<tr>
			<td bgcolor="#99FF99" style="font-size: 5px">&nbsp;</td>
			<td colspan="2" style="font-size: 5px">&nbsp;</td>
		</tr>

		<tr>
			<td bgcolor="#99FF99" valign="top" height="500">
				<!-- Navigation bar is one table cell down the left side -->
				<p align="left">

					<c:choose>
						<c:when test="${empty sessionScope.user}">
							<span class="menu-item"><a href="register.do">Register</a></span>
							<br />
							<br />
						</c:when>
						<c:otherwise>
							<span class="menu-head">${sessionScope.user.firstName}
								${sessionScope.user.lastName}</span>
							<br />
							<span class="menu-item"><a href="manage.do">Manage
									Favorites List</a></span>
							<br />
							<span class="menu-item"><a href="change-pwd.do">Change
									Password</a></span>
							<br />
							<span class="menu-item"><a href="logout.do">Logout</a></span>
							<br />
							<br />
						</c:otherwise>
					</c:choose>
				</p>
			</td>

			<td>
				<!-- Padding (blank space) between navbar and content -->
			</td>
			<td valign="top">