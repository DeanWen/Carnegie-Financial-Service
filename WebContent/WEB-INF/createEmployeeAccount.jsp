<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="admin-top.jsp" />

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Create Employee Account</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Please Specify Detail Information:
                        </div>
                        <div class="panel-body">
							<div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <tbody>
                                    <form action="createEmployeeAccount.do" method="POST">
                                        <tr class="odd gradeX">
                                            <th> User Name: </th>
                                            <td class="center">
                                                <input class="text" placeholder="User Name" name="username" type="text" value = "${form.getUsername()}"/>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th> Last Name:</th>
                                            <td class="center">
                                                <input class="text" placeholder="Last Name" name="lastname" type="text" value = "${form.getLastname()}"/>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th> First Name:</th>
                                            <td class="center">
                                                <input class="text" placeholder="First Name" name="firstname" type="text" value = "${form.getFirstname()}"/>
                                            </td>
                                        </tr>
                                         <tr class="odd gradeX">
                                            <th> Password:</th>
                                            <td class="center">
                                                <input class="text" placeholder="Password" name="password" type="password" value = "${form.getPassword()}"/>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <td class="center" colspan="2">
                                                <center><input class="btn btn-lg btn-success btn-block" id = "createEmployeeAccount" type = "submit" name = "action" value = "Done"/></center>
                                            </td>
                                        </tr>
                                    </form>
                                    </tbody>
                                </table>
                            </div>
                            <!-- dataTable_wrapper -->
                            <c:forEach var="error" items="${errors}">
								<div class="alert alert-danger">
                                ${error}.
                            	</div> 
							</c:forEach>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- form -->
            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

<jsp:include page="admin-bottom.jsp" />