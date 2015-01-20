<jsp:include page="template-top.jsp" />

<div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Manage Account</h1>
                        <div class="panel panel-default">
                        <form action="customerAccountView.do" method="POST">
                        <div class="panel-heading">
                            Personal Information 
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="customerAccountEdit.do" style="color:#000000; text-decoration:none"><button class="btn btn-outline btn-default" type="button">Edit</button></a>
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

            </div>
            <!-- /.container-fluid -->
        </div>
        <!-- /#page-wrapper -->

<jsp:include page="template-bottom.jsp" />