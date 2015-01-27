<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="template-top.jsp" />

<div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Change Password</h1>
                        <div class="panel panel-default">

                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                
                                    <tbody>
                                    <form action="changePWD.do" method="POST">
                                        <tr class="odd gradeX">
                                            <th> Old Password</th>
                                            <td class="center">
                                                <input class="text" placeholder="Old Password" name="oldPWD" type="password"/>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th> New Password</th>
                                            <td class="center">
                                                <input class="text" placeholder="New Password" name="newPWD" type="password"/>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th> Confirm Password</th>
                                            <td class="center">
                                                <input class="text" placeholder="Confirm Password" name="cfmPWD" type="password"/>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <td class="center" colspan="2">
                                                <center><input class="btn btn-lg btn-success btn-block" id = "changePWD" type = "submit" name = "action" value = "Done"/></center>
                                            </td>
                                        </tr>
                                    </form>
                                    </tbody>
                                </table>
                            </div>
                            <c:if test="${check}">
                            <div class="alert alert-success">
                            	Congratulations! Change password successfully!    
                            </div> 
                            </c:if>
                            <c:forEach var="error" items="${errors}">
								<div class="alert alert-danger">
                                ${error}.
                            	</div> 
							</c:forEach>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
            </div>
            <!-- /.container-fluid -->
        </div>

<jsp:include page="template-bottom.jsp" />