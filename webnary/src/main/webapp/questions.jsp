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
                This questionnaire aims to validate, through the help of developers responding to a brief questionnaire, 
                the categorization of the methods of some collections of the <b>JCF</b> framework (<b>J</b>ava <b>C</b>ollections 
                <b>F</b>ramework) for a final paper, the categories were defined as: <i>(i)</i> addition <i>(ii)</i> search 
                , <i>(iv)</i> edit, <i>(ii)</i> removal, and <i>(v)</i> access. The questionnaire consists of five questions
                with seven alternatives generated randomly, within the set of methods considered, you should select the 
                alternatives that you think belong to the category informed in the question.
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
                                    Next
                                    <i class="material-icons right">send</i>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button class="btn waves-effect waves-light" type="submit" name="action" style="margin-left:75%;" id="sendButton" disabled>
                                    Next
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
                        Complete
                    </button>
                </c:when>
                <c:otherwise>
                    <button class="btn-large waves-effect waves-light" type="submit" name="action" style=" margin: 0 auto; display: block;">
                        Complete
                    </button>
                </c:otherwise>
            </c:choose>
        </form>
    </body>
</html>