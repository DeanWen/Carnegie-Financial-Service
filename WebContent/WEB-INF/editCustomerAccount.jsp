<!-- Tian Zheng CMU Jan 27, 2015 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="admin-top.jsp" />

<div id="page-wrapper">
            <div class="container-fluid">
            	
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Manage Account</h1>
                        <div class="panel panel-default">
                        <form action="editCustomerAccount.do" method="POST">
                        <div class="panel-heading">
                            Personal Information &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="action" value="Done" />
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                	
                                    <tbody>
                                       <tr class="odd gradeX">
                                            <th class="col-md-3"> First Name </th>
                                            <td>
                                            <div class="form-group">
                                            <input class="form-control" placeholder="First Name" name = "firstname" value = "${form.getFirstname()}" />
                                            </div>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th class="col-md-3"> Last Name </th>
                                            <td>
                                            <div class="form-group">
                                            <input class="form-control" placeholder="Last Name" name = "lastname" value = "${form.getLastname()}" />
                                            </div>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th class="col-md-3"> Address Line 1 </th>
                                            <td>
                                            <div class="form-group">
                                            <input class="form-control" placeholder="Address Line 1" name = "addr1" value = "${form.getAddr1()}" />
                                            </div>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th class="col-md-3"> Address Line 2 </th>
                                            <td>
                                            <div class="form-group">
                                            <input class="form-control" placeholder="Address Line 2" name = "addr2" value = "${form.getAddr2()}" />
                                            </div>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th class="col-md-3"> City </th>
                                            <td>
                                            <div class="form-group">
                                            <input class="form-control" placeholder="City" name = "city" value = "${form.getCity() }" />
                                            </div>
                                            </td>
                                        </tr>
                                        
                                        <tr>
                							<td><div align="left"><b>ZIP code:</b></div></td>
                							<td><input name="zip" type="text" size="5" id="zipL" id="L5"></td>
                						</tr>
            							<tr>
                							<td><div align="left"><b>State/Region:</b></div></td>
                							<td>
                    						<select name="state" size="1" id="statelist" id="L8">
                    						</select>
                    						</td>
            							</tr>
                                        
<%--                                         <tr class="odd gradeX">
                                            <th class="col-md-3"> State </th>
                                            <td>
                                            <div class="form-group">
                                            <input class="form-control" placeholder="State" name = "state" value = "${form.getState()}" />
                                            </div>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th class="col-md-3"> Zip Code </th>
                                            <td>
                                            <div class="form-group">
                                            <input class="form-control" placeholder="Zip Code" name = "zip" value = "${form.getZip()}" />
                                            </div>
                                            </td>
                                        </tr> --%>
                                    </tbody>
                                </table>
                                <c:forEach var="error" items="${errors}">
								<div class="alert alert-danger">
                                ${error}
                            	</div> 
								</c:forEach>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        </form>
                        <!-- /.panel-body -->
                        </div>
                </div>
                    <!-- /.col-lg-12 -->
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Account Information
                        </div>

                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <tbody>
                                        <tr class="odd gradeX">
                                            <th class="col-md-3"> User Name </th>
                                            <td> ${customer.getUsername()} </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th class="col-md-3"> Last Trading Day </th>
                                            <td> ${lastTransaction.getExecute_date()} </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th class="col-md-3"> Cash Balance </th>
                                            <td > ${customer.getCash()} </td>
                                        </tr>                        
                                    </tbody>
                                </table>
                            </div>
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
        <!-- /#page-wrapper -->
</form>
<jsp:include page="admin-bottom.jsp" />