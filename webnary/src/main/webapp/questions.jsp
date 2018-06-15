<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width">
        <title>Questions</title>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/css/materialize.min.css">
        <script src="https://code.jquery.com/jquery-3.0.0.js"></script>
        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
        <style>
            .option {
                display: inline-block;
                margin-right: 2%;
            }
            .carousel {
                margin-top: 5%;    
            }
        </style>        
    </head>
    <body>
        <div class="carousel">        
            <c:forEach var="item" items ="${sessionScope.fullQuestions}">
                <div class="card blue-grey darken-1 carousel-item" style="width: 36%;">
                    <div class="card-content white-text">
                        <span class="card-title">Pergunta</span>
                        <p>${item.key}.</p>
                    </div>
                    <form action="sendAnswer" method="POST">
                        <div class="card-action">
                            <c:forEach var="option" items ="${item.value}">
                                <p class="option">
                                    <label>
                                        <input name="check" type="checkbox" class="optionCheck" value="${option}">
                                        <span>${option}</span>
                                    </label>
                                </p>                        
                            </c:forEach>
                            <input name="question" type="hidden" value="${item.key}">
                        </div>        
                        <div class="card-action">
                            <c:choose>
                                <c:when test="${dev.answersIsNull(item.key)}">
                                    <button class="btn waves-effect waves-light" type="submit" name="action">
                                        Submit
                                        <i class="material-icons right">send</i>
                                    </button>
                                </c:when>    
                                <c:otherwise>
                                    <button class="btn waves-effect waves-light" type="submit" name="action" disabled>
                                        Submited
                                        <i class="material-icons right">send</i>
                                    </button>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </form>
                </div>
            </c:forEach>
        </div>
        <form method="POST" action="endAnswer" style="margin-top: 5%">
            <c:choose>
                <c:when test="${!dev.allQuestionsAnswered()}">
                    <button class="btn waves-effect waves-light" type="submit" name="action" disabled style=" margin: 0 auto; display: block;">
                        Concluir
                    </button>
                </c:when>    
                <c:otherwise>
                    <button class="btn waves-effect waves-light" type="submit" name="action" style=" margin: 0 auto; display: block;">
                        Concluir
                    </button>
                </c:otherwise>
            </c:choose>
        </form>
        <script>$(document).ready(function () { $('.carousel').carousel().dist = 0; });</script>
    </body>
</html>