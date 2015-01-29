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
                                            <label>Enter Date (yyyy-mm-dd):</label>
                                            <input class="form-control" placeholder="yyyy-mm-dd" name="date" value = "${form.getDate()}"/>
                                        </div>
                            <div class="table-responsive">
                                 <table class="table">
                                    <thead>
                                        <tr>
                                            <td><b>#</b></td>
                                            <td><b>Fund</b></td>
                                            <td><b>Ticker</b></td>
                                            <td align="right"><b>Current Price</b></td>
                                            <td><b>Change</b></td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="fundList" items="${fundList}">
                                        <tr class="success">
                                            <td>${fundList.getFund_id()}</td>
                                            <td>${fundList.getName()}</td>
                                            <td>${fundList.getSymbol()}</td>
                                            <td align="right">${fundList.getPrice()}</td>

                                            <td>
                                            <input type="hidden" name="id" value="${fundList.getFund_id()}"/>
                                            <input type="text" name="price"/>
                                            </td>

                                        </tr>
                                        </c:forEach>                                      
                                    </tbody>
                                </table>
                            </div>
                            			<button class="btn btn-default">Submit</button>
                            			<input type="reset" class="btn btn-default" value = "Reset" />
                                    </form>
                                </div>
                            </div>
                            <!-- row -->
                            <c:forEach var="error" items="${errors}">
								<div class="alert alert-danger">
                                ${error}
                            	</div> 
							</c:forEach>
							<c:if test="${check}">
                            <div class="alert alert-success">
                            	Update price successfully!    
                            </div> 
                            </c:if>
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