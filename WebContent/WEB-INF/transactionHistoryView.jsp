<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="template-top.jsp" />

<!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">View Fund List</h1>
                        <div class="panel panel-default">
                        <div class="panel-heading">
                            Fund List
                        </div>

                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Fund</th>
                                            <th>Ticker</th>
                                            <th>Shares</th>
                                            <th>Price</th>
                                            <th>Value</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="record" items="${records}">
										<tr class="success">
                                            <td>${record.getFundID()}</td>
                                            <td>${record.getFundName()}</td>
                                            <td>${record.getFundSymbol()}</td>
                                            <td align="right">${record.getShares()}</td>
                                            <td align="right">${record.getPrice()}</td>
                                            <td align="right">${record.getValue()}</td>
                                            <td>
                                            <form action="transactionHistoryView.do" method="POST">
                                            <input type="hidden" name="id" value="${record.getFundID()}"/>
                                            <input type="submit" class="btn btn-outline btn-primary" name="button" value ="View History"/>
                                            </form>
                                            </td>
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
                
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">${curFund.getName()}</h1>
                        <div class="panel panel-default">
                        <div class="panel-heading">
                            PNC Fund History
                        </div>

                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Date</th>
                                            <th>Type</th>
                                            <th>Shares</th>
                                            <th>Price</th>
                                            <th>Dollar Amount</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="history" items="${histories}">
										<tr class="success">
                                            <td>${history.getFundName()}</td>
                                            <td>${history.getDate()}</td>
                                            <td>${history.getType()}</td>
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