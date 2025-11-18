package stuff;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Maryr
 */
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        if (usuario == null || password == null || usuario.trim().isEmpty() || password.trim().isEmpty()) {
            request.setAttribute("error", "Usuario y contraseña requeridos");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return;
        }
        if ("Sol".equals(usuario.trim()) && "1234".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario.trim());
            response.sendRedirect("Perfil.jsp");
        } else {
            request.setAttribute("error", "Usuario o contraseña incorrectos");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
}
