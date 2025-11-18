/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package stuff;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Maryr
 */
@MultipartConfig(maxFileSize = 5 * 1024 * 1024)
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

    private static final List<String> EXTENSIONES_PERMITIDAS = Arrays.asList("jpg", "jpeg", "png", "gif");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part archivo = request.getPart("imagen");
        if (archivo == null || archivo.getSize() == 0) {
            mostrarError(response, "No se seleccionó ningún archivo");
            return;
        }
        String nombreOriginal = archivo.getSubmittedFileName();
        nombreOriginal = Paths.get(nombreOriginal).getFileName().toString();
        String extension = obtenerExtension(nombreOriginal);
        if (!EXTENSIONES_PERMITIDAS.contains(extension.toLowerCase())) {
            mostrarError(response, "Solo se permiten imágenes (jpg, png, gif)");
            return;
        }
        String rutaCarpeta = request.getServletContext().getRealPath("/uploads/");
        java.io.File carpeta = new java.io.File(rutaCarpeta);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        String nuevoNombre = System.currentTimeMillis() + "_" + nombreOriginal;
        String ruta = rutaCarpeta + nuevoNombre;
        archivo.write(ruta);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><head>");
        out.println("<style>body{font-family:Arial;background:#f8f4ff;padding:20px;text-align:center;}</style>");
        out.println("</head><body>");
        out.println("<h2 style='color:#5e3c8e;'>Archivo subido con éxito :)</h2>");
        out.println("<p>Guardado como: <b>" + nuevoNombre + "</b></p>");
        out.println("<img src='uploads/" + nuevoNombre + "' style='max-width:300px;margin:20px;border:2px solid #b38aff;border-radius:10px;'>");
        out.println("<br><br>");
        out.println("<a href='Galeria.jsp' style='background:#b38aff;color:white;padding:10px 20px;text-decoration:none;border-radius:8px;margin:5px;'>Ver Galería</a>");
        out.println("<a href='Perfil.jsp' style='background:#7c5ab3;color:white;padding:10px 20px;text-decoration:none;border-radius:8px;margin:5px;'>Volver al Perfil</a>");
        out.println("</body></html>");
    }

    private String obtenerExtension(String nombreArchivo) {
        int puntoIndex = nombreArchivo.lastIndexOf('.');
        if (puntoIndex > 0) {
            return nombreArchivo.substring(puntoIndex + 1);
        }
        return "";
    }

    private void mostrarError(HttpServletResponse response, String mensaje) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html><body style='font-family:Arial;text-align:center;padding:50px;background:#f8f4ff;'>");
        out.println("<h2 style='color:red;'>Error: " + mensaje + "</h2>");
        out.println("<a href='Perfil.jsp' style='background:#b38aff;color:white;padding:10px 20px;text-decoration:none;border-radius:8px;'>Volver</a>");
        out.println("</body></html>");
    }
}
