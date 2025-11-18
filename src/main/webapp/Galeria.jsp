<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>
<jsp:include page="header.jspf" />
<div class="card" style="max-width: 800px;">
    <h2 style="text-align:center; color:#5e3c8e;">
        Galería de Imágenes
    </h2>
    <p style="text-align:center; color:#666;">
        Todas las imágenes subidas
    </p>   
    <div style="display: grid; grid-template-columns: repeat(auto-fill, minmax(200px, 1fr)); gap: 15px; margin-top: 20px;">
        <%
            String rutaCarpeta = application.getRealPath("/uploads/");
            File carpeta = new File(rutaCarpeta);
            if (carpeta.exists() && carpeta.isDirectory()) {
                File[] archivos = carpeta.listFiles();
                if (archivos != null && archivos.length > 0) {
                    for (File archivo : archivos) {
                        if (archivo.isFile()) {
                            String nombreArchivo = archivo.getName();
        %>
                            <div style="text-align:center; padding:10px; border:2px solid #ebddff; border-radius:10px; background:#fdfbff;">
                                <img src="uploads/<%= nombreArchivo %>" 
                                     alt="<%= nombreArchivo %>" 
                                     style="width:100%; height:150px; object-fit:cover; border-radius:8px; margin-bottom:8px;">
                                <small style="color:#666; font-size:11px; word-break:break-all;">
                                    <%= nombreArchivo %>
                                </small>
                            </div>
        <%
                        }
                    }
                } else {
        %>
                    <p style="text-align:center; color:#999; grid-column: 1/-1;">
                        No hay imágenes todavía.
                    </p>
        <%
                }
            } else {
        %>
                <p style="text-align:center; color:#999; grid-column: 1/-1;">
                    La carpeta de uploads no existe aún.
                </p>
        <%
            }
        %>
    </div>
    <div style="text-align:center; margin-top:25px;">
        <a href="Perfil.jsp" style="background:#b38aff; color:white; padding:10px 20px; text-decoration:none; border-radius:8px; display:inline-block;">
            Volver al Perfil
        </a>
    </div>
</div>
<jsp:include page="footer.jspf" />
