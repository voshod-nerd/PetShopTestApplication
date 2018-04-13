<!DOCTYPE html>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <title>Maven + Spring MVC</title>
        <meta http-equiv="Content-Type" content="text/html; charset=Utf-8">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" type="text/css"
              href="http://cdn.sencha.com/ext/gpl/4.2.0/resources/css/ext-all.css">
        <script type="text/javascript"
        src="http://cdn.sencha.com/ext/gpl/4.2.0/ext-all.js"></script>


        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <style>
            .book-add { background-image:url('resources/images/book_add.png');}
            div#output {margin:100px;}
        </style>

        <script type="text/javascript" src="resources/city.js"></script>
    </head>
    <body>

        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="#">Редактор стран и городов Spring 3+Mabatis + ExtJs</a>
                </div>
                <ul class="nav navbar-nav">
                    <li ><a href="/petshop">Страны</a></li>
                    <li><a href="/petshop/city">Города</a></li>
                    
                    
                </ul>
            </div>
        </nav>


        <div class="container" >
            
            <H4>Редактор городов</H4>
            <div id="output"></div> 
        </div>
    </body>

</html>

