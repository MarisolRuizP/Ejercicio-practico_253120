<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="header.jspf" />
<div class="card">
    <h2 style="text-align:center; color:#5e3c8e;">
        Hola, <%= session.getAttribute("usuario")%> :)
    </h2>
    <form action="upload" method="post" enctype="multipart/form-data">
        <label>Subir imagen de perfil:</label><br>
        <input type="file" name="imagen" accept="image/*" required><br>
        <small style="color:#666;">Solo imágenes (max 5MB)</small><br>
        <button type="submit">Subir Archivo</button>
    </form>
    <hr style="border:none; border-top:1px solid #ebddff; margin:20px 0;">
    <div style="text-align:center;">
        <a href="Galeria.jsp" style="background:#7c5ab3; color:white; padding:10px 20px; text-decoration:none; border-radius:8px; display:inline-block; margin:5px;">
            Ver Galería
        </a>
    </div>
    <hr style="border:none; border-top:1px solid #ebddff; margin:20px 0;">
    <form action="LogoutServlet" method="post" style="text-align:center;">
        <button type="submit" style="background:#ff6b6b;">
            Cerrar Sesión
        </button>
    </form>
</div>
<jsp:include page="footer.jspf" />