<%@page import="utilisateur.Utilisateur" %>
<%

    String[] features = Utilisateur.getFeatures();
    String p = (request.getParameter("p") == null) ? "application.jsp" : request.getParameter("p") + ".jsp";

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./assets/css/bootstrap.css">
    <title>Home</title>
</head>
<body>
    <div class="container shadow-sm rounded-pill mt-3 w-75">
        <header class="d-flex justify-content-center py-3">
          <ul class="nav nav-pills">
            <% for (String feature : features) { %>
            <li class="nav-item"><a href="/katsaka/?p=<%=feature.toLowerCase().replace(" ", "-") %>" class="nav-link <%=Utilisateur.getActive(feature, p) %>"><%=feature %></a></li>
            <% } %>
          </ul>
        </header>
    </div>
    <div class="container">
        <jsp:include page="<%=p %>" flush="true" />
    </div>
</body>
</html>