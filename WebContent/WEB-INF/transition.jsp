<!-- Tian Zheng CMU Jan 27, 2015 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="admin-top.jsp" />

        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Transition Day</h1>
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
                            <div class="row">
								<div class="col-lg-6">
                                    <form action="transition.do" method="POST">
                                    	<div class="form-group">
                                            <label>Enter Date:</label>
                                            <input class="form-control" placeholder="Date:" name="date" value = "${form.getDate()}"/>
                                        </div>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <td><b>#</b></td>
                                            <td><b>Fund</b></td>
                                            <td><b>Ticker</b></td>
                                            <td align="right"><b>Shares</b></td>
                                            <td align="right"><b>Price</b></td>
                                            <td align="right"><b>Value</b></td>
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
                                            <input type="hidden" name="id" value="${record.getFundID()}"/>
                                            </td>
                                            <td>
                                            <input type="submit" class="btn btn-outline btn-primary" name="button" value ="Sell"/>
                                            </td>
                                        </tr>
                                        </c:forEach>                                      
                                    </tbody>
                                </table>
                            </div>
                                        <button type="submit" class="btn btn-default">Submit</button>
                                        <button type="reset" class="btn btn-default">Reset</button>
                                    </form>
                                </div>
                            </div>
                            <!-- row -->
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