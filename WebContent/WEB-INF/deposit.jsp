<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="admin-top.jsp" />

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Deposit Check</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Enter account number and amount:
                        </div>
                        <div class="panel-body">
							<div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                    <tbody>
                                    <form action="deposit.do" method="POST">
                                        <tr class="odd gradeX">
                                            <th> Customer ID</th>
                                            <td class="center">
                                                <input class="text" placeholder="Customer ID" name="customerId" type="text"/>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th> Deposit Amount</th>
                                            <td class="center">
                                                <input class="text" placeholder="Deposit Amount" name="depositAmount" type="text"/>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <td class="center" colspan="2">
                                                <center><input class="btn btn-lg btn-success btn-block" id = "deposit" type = "submit" name = "action" value = "Done"/></center>
                                            </td>
                                        </tr>
                                    </form>
                                    </tbody>
                                </table>
                            </div>
                            <!-- dataTable_wrapper -->
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
