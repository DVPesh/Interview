package ru.peshekhonov;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloServlet", urlPatterns = "/")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.printf("<!DOCTYPE html><html><head></head><body>");
        out.println("<h1 style=\"color: purple;font-size:4em;\">Hello World</h1>");
        out.println("""
                <p style="color: blue;font-size:3em;">
                Курс "Подготовка к собеседованию Java-разработчика".
                Практичекое задание №6.
                </p>
                """);
        out.printf("</body></html>");
        out.close();
    }
}
