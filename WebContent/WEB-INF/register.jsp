<jsp:include page="template-top.jsp" />

<jsp:include page="error-list.jsp" />

<p>
<form action="register.do" method="post">
	<input type="hidden" name="redirect" value="${redirect}" />
	<table>
		<tr>
			<td>Username:</td>
			<td><input type="text" name="userid" value="${form.userid}" /></td>
		</tr>
		<tr>
			<td>First Name:</td>
			<td><input type="text" name="firstname"
				value="${form.firstname}" /></td>
		</tr>
		<tr>
			<td>Last Name:</td>
			<td><input type="text" name="lastname" value="${form.lastname}" /></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type="password" name="password" value="" /></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><input type="submit"
				name="action" value="Done" /></td>
		</tr>
	</table>
</form>
</p>

<jsp:include page="template-bottom.jsp" />

