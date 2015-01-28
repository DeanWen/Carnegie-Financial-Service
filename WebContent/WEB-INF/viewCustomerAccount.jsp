<!-- Tian Zheng CMU Jan 27, 2015 -->
<jsp:include page="admin-top.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Manage Account</h1>
                        <div class="panel panel-default">
                        <form action="viewCustomerAccount.do" method="POST">
                        <div class="panel-heading">
                            Customer Information 
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="editCustomerAccount.do" style="color:#000000; text-decoration:none"><button class="btn btn-outline btn-default" type="button">Edit</button></a>
                        </div>
						</form>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <tbody>
                                        <tr class="odd gradeX">
                                            <th class="col-md-3"> Name </th>
                                            <td> ${customer.getFirstname()} ${customer.getLastname()} </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th class="col-md-3"> Address Line1 </th>
                                            <td> ${customer.getAddr_line1()} </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th class="col-md-3"> Address Line2 </th>
                                            <td>  ${customer.getAddr_line2()} ${customer.getCity()}, ${customer.getState()} ${customer.getZip()}</td>
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
                                            <td> ${customer.getUsername()}</td>
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
                
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Transaction History 
                        </div>
                         <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <td><b>Name</b></td>
                                            <td><b>Date</b></td>
                                            <td><b>Type</b></td>
                                            <td><b>Status</b></td>
                                            <td align="right"><b>Shares</b></td>
                                            <td align="right"><b>Price</b></td>
                                            <td align="right"><b>Amount</b></td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="history" items="${histories}">
                                        <tr class="success">
                                            <td>${history.getFundName()}</td>
                                            <td>${history.getDate()}</td>
                                            <td>${history.getType()}</td>
                                            <td>${history.getStatus()}</td>
                                            <td align="right">${history.getShares()}</td>
                                            <td align="right">${history.getPrice()}</td>
                                            <td align="right">${history.getAmount()}</td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
                        </div>
                    </div>
                </div>

            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

<jsp:include page="admin-bottom.jsp" />