<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jspf" />
<div class="card">
    <h2 style="text-align:center; color:#5e3c8e;">Iniciar Sesión</h2>
    <form action="LoginServlet" method="post">
        <label>Usuario:</label><br>
        <input type="text" name="usuario" required><br>
        <label>Contraseña:</label><br>
        <input type="password" name="password" required><br>
        <button type="submit">Entrar</button>
    </form>
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <p style="color:red; text-align:center;"><%= error%></p>
    <% }%>
</div>
<jsp:include page="footer.jspf" />