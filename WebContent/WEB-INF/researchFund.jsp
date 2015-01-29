<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="template-top.jsp" />

<!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Research Fund</h1>
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
                                            <td><b>#</b></td>
                                            <td><b>Fund</b></td>
                                            <td><b>Ticker</b></td>
                                            <td align="right"><b>Current Price</b></td>
                                            <td align="right"><b>Shares Hold</b></td>

                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach var="fundList" items="${fundList}">
										<tr class="success">
                                            <td>${fundList.getFund_id()}</td>
                                            <td>${fundList.getName()}</td>
                                            <td>${fundList.getSymbol()}</td>
                                            <td align="right">${fundList.getPrice()}</td>
                                            <td align="right">${fundList.getShare()}</td>

                                            <td>
                                            <form action="buyFund.do" method="POST">
                                            <input type="hidden" name="id" value="${fundList.getFund_id()}"/>
                                            <input type="submit" class="btn btn-outline btn-primary" name="button" value ="Buy"/>
                                            </form>
                                            </td>
                                            
                                            <td>
                                            <form action="sellFund.do" method="POST">
                                            <input type="hidden" name="id" value="${fundList.getFund_id()}"/>
                                            <input type="submit" class="btn btn-outline btn-primary" name="button" value ="Sell"/>
                                            </form>
                                            </td>
                                            
                                            <td>                            
                                            <form action="fundChart.do" target="_blank" method="POST">
                                            <input type="hidden" name="id" value="${fundList.getFund_id()}"/>
                                            <input type="submit" class="btn btn-outline btn-primary" name="button" value ="Performance"/>
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
            </div>
            <!-- /.container-fluid -->
        </div>
	
<jsp:include page="template-bottom.jsp" />