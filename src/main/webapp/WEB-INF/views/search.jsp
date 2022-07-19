<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <title>Поиск компании</title>
    <link rel='icon' href='https://img.icons8.com/fluency/16/000000/star.png' type='image/x-icon' sizes="16x16" />
</head>
<body>
<div class="container pt-3">
    <div class="row">
        <ul class="nav">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/index'/>">Главная страница</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/search'/>">Поиск компании</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/create'/>">Добавить новый отклик</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/refusal'/>">Отказы</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/archive'/>">Архивные отклики</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<c:url value='/indexNote'/>">Заметки о поиске</a>
            </li>
        </ul>
    </div>
</div>
<div class="container">
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                Форма поиска компании среди откликов:
            </div>
            <div class="card-body">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col">Название компании</th>
                        <th scope="col">Дата отклика</th>
                        <th scope="col">Статус</th>
                        <th scope="col">Изменить статус</th>
                        <th scope="col">Управление откликом</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${response}" var="comp">
                        <tr>
                            <td><c:out value="${comp.getName()}"/></td>
                            <td><c:out value="${comp.getCreated()}"/></td>
                            <td>
                                <c:if test="${comp.getDone() == 'false' && comp.archive == 'true'}">
                                    <c:out value="Отправлено на рассмотрение (АРХИВ)"/>
                                </c:if>
                                <c:if test="${comp.getDone() == 'false' && comp.archive == 'false'}">
                                    <c:out value="Отправлено на рассмотрение"/>
                                </c:if>
                                <c:if test="${comp.getDone() == 'true'}">
                                    <c:out value="Резюме отклонено"/>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${comp.getDone() == 'false' && comp.archive == 'false'}">
                                    <a href="<c:url value='/setStatusDone?id=${comp.getId()}'/>">"Отказано"</a>
                                    <a href="<c:url value='/setStatusArchive?id=${comp.getId()}'/>">| "В архив"</a>
                                </c:if>
                            </td>
                            <td>
                                <a href="<c:url value='/updateRespDescGet?id=${comp.id}'/>">
                                    <span class="fa fa-edit mr-3"></span>
                                </a>
                                <a href="<c:url value='/delete?id=${comp.id}'/>">
                                    <span class="fa fa-trash-o"></span>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="card-body">
                <label>Введите название компании:</label>
                <form  action="<c:url value='/findName'/>" method='POST'>
                    <div class="form-group">
                        <input type="text" class="form-control" name="name">
                    </div>
                    <button type="submit" class="btn btn-primary">Найти</button>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>


