<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="template-top.jsp" />

<!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Transaction History</h1>
                    </div>
                    <!-- /.col-lg-12 -->
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                        <div class="panel-heading">
                        
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
	
	<jsp:include page="template-bottom.jsp" />