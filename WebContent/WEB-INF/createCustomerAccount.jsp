<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="admin-top.jsp" />

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Create Customer Account</h1>
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
                                    <form action="createCustomerAccount.do" method="POST">
                                        <tr class="odd gradeX">
                                            <th> Customer ID: </th>
                                            <td class="center">
                                                <input class="text" placeholder="Customer ID" name="customer_id" type="text" value = "${form.getCustomer_id()}"/>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th> Nick Name: </th>
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
                                            <th> Address line:</th>
                                            <td class="center">
                                                <input class="text" placeholder="Address" name="addr_line1" type="text" value = "${form.getAddr_line1()}"/>
                                            </td>
                                        </tr>
                                         <tr class="odd gradeX">
                                            <th> Address line:</th>
                                            <td class="center">
                                                <input class="text" placeholder="Address" name="addr_line2" type="text" value = "${form.getAddr_line2()}"/>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th> City:</th>
                                            <td class="center">
                                                <input class="text" placeholder="City" name="city" type="text" value = "${form.getCity()}"/>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th> State:</th>
                                            <td class="center">
                                                <input class="text" placeholder="State" name="state" type="text" value = "${form.getState()}"/>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th> Zip:</th>
                                            <td class="center">
                                                <input class="text" placeholder="Zip" name="zip" type="text" value = "${form.getZip()}"/>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <td class="center" colspan="2">
                                                <center><input class="btn btn-lg btn-success btn-block" id = "createCustomerAccount" type = "submit" name = "action" value = "Done"/></center>
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