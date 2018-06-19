<!DOCTYPE html>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="utf-8">
        <title>Questions</title>
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/css/materialize.min.css">
        <link rel="stylesheet" href="css/question.css" type="text/css">
        <script src="https://code.jquery.com/jquery-3.0.0.js"></script>
        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0-beta/js/materialize.min.js"></script>
    </head>
    <body>
        <div id="text" class="z-depth-2">
            <blockquote>
                Este questionário tem como intuito validar, através da ajuda de densevolvedores respondendo a um breve questionário, a categorização
                dos métodos de algumas coleções do framework <b>JCF</b> (<b>J</b>ava <b>C</b>ollections
                <b>F</b>ramework) para um Tabalho de Conclusão de Curso, as categorias foram definidas como: <i>(i)adição</i>, <i>(ii)remoção</i>,
                <i>(iii)busca</i>, <i>(iv)edição</i> e <i>(v)acesso</i>.
                O questionário consiste em cinco questões com sete alternativas geradas aleatoriamente, dentro do conjunto de
                métodos considerados, você deverá selecionar as alternativas que acha que pertencem a categoria informada na
                questão.
            </blockquote>
        </div>
        <div id="main">
            <div class="card blue-grey darken-1 carousel-item" style="width:35%;">
                <div class="card-content white-text">
                    <span class="card-title">Pergunta ${sessionScope.questionCount + 1}/5</span>
                    <p>${question.questionText}</p>
                </div>
                <form action="sendAnswer" method="POST">
                    <div class="card-action">
                        <c:forEach var="option" items="${question.alternatives}">
                            <p class="option">
                                <label>
                                    <input name="check" type="checkbox" class="optionCheck" value="${option}">
                                    <span>${option}</span>
                                </label>
                            </p>
                        </c:forEach>
                        <input name="question" type="hidden" value="${question.questionText}">
                    </div>
                    <div class="card-action">
                        <c:choose>
                            <c:when test="${!dev.allQuestionsAnswered()}">
                                <button class="btn waves-effect waves-light" type="submit" name="action" style="margin-left:75%;" id="sendButton">
                                    próximo
                                    <i class="material-icons right">send</i>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button class="btn waves-effect waves-light" type="submit" name="action" style="margin-left:75%;" id="sendButton" disabled>
                                    próximo
                                    <i class="material-icons right">send</i>
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </form>
            </div>
        </div>
        <form method="POST" action="endAnswer" style="margin-top:2%;">
            <c:choose>
                <c:when test="${!dev.allQuestionsAnswered()}">
                    <button class="btn-large waves-effect waves-light" type="submit" name="action" disabled style="margin:0 auto; display:block;">
                        Concluir
                    </button>
                </c:when>
                <c:otherwise>
                    <button class="btn-large waves-effect waves-light" type="submit" name="action" style=" margin: 0 auto; display: block;">
                        Concluir
                    </button>
                </c:otherwise>
            </c:choose>
        </form>
    </body>
</html>