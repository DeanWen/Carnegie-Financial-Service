<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="template-top.jsp" />

<div id="page-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">Buy Fund</h1>
                        <div class="panel panel-default">

                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                
                                    <tbody>
                                    <form action="buyFund.do" method="POST">
                                        <tr class="odd gradeX">
                                            <th> Fund Name </th>
                                            <td class="center">
                                                <input class="text" placeholder="Fund Name" name="fundName" value="${form.getFundName()}"/>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th> Buy Amount</th>
                                            <td class="center">
                                                <input class="text" placeholder="Buy Amount" name="buyAmount" value="${form.getBuyAmount()}"/>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <th> Confirm Amount</th>
                                            <td class="center">
                                                <input class="text" placeholder="Confirm Amount" name="cfmAmount" value="${form.getCfmAmount()}"/>
                                            </td>
                                        </tr>
                                        <tr class="odd gradeX">
                                            <td class="center" colspan="2">
                                                <center><input class="btn btn-lg btn-success btn-block" id = "buyFund" type = "submit" name = "action" value = "Done"/></center>
                                            </td>
                                        </tr>
                                    </form>
                                    </tbody>
                                </table>
                            </div>
                            <c:if test="${check}">
                            <div class="alert alert-success">
                            	Congratulations! Buy fund successfully!    
                            </div> 
                            </c:if>
                            <c:forEach var="error" items="${errors}">
								<div class="alert alert-danger">
                                ${error}.
                            	</div> 
							</c:forEach>
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
	
<jsp:include page="template-bottom.jsp" />