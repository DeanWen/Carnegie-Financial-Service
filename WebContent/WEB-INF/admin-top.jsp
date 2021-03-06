<!-- Tian Zheng CMU Jan 27, 2015 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Carnegie Financial Service</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.11.1.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/state.js" charset="utf-8"></script>   

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="customerAccountView.do">Carnegie Financial Service</a>
            </div>
            <!-- /.navbar-header -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                            <a href="transition.do"><i class="fa fa-paper-plane fa-fw"></i> Transition Day</a>
                        </li>
                        <li>
                            <a href="changeEmpPWD.do"><i class="fa fa-key fa-fw"></i> Change Password</a>
                        </li>
                        
                        <li>
                            <a href="#"><i class="fa fa-user fa-fw"></i> Create Account <span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="createCustomerAccount.do">Customer Account</a>
                                </li>
                                <li>
                                	<a href="createEmployeeAccount.do"> Employee Account</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="createFund.do"><i class="fa fa-line-chart fa-fw"></i> Create Fund</a>
                        </li>
                        <li>
                            <a href="deposit.do"><i class="fa fa-money fa-fw"></i> Deposit Check</a>
                        </li>
                        <li>
                            <a href="findCustomer.do"><i class="fa fa-pencil-square-o fa-fw"></i> Manage Customer Account</a>
                        </li>
                        <li>
                            <a href="logout.do"><i class="fa fa-sign-out fa-fw"></i> Log Out</a>
                        </li>
                        
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>