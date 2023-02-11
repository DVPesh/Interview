package ru.peshekhonov;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HelloServlet", urlPatterns = "")
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.printf("<!DOCTYPE html><html><head></head><body>");
        out.println("<h1 style=\"color: purple;font-size:2em;\">Hello World</h1>");
        out.println("""
                <p style="color: blue;font-size:1.5em;">
                Курс "Подготовка к собеседованию Java-разработчика".
                </p>
                <p style="color: green;font-size:1.5em;">
                Практичекое задание №6.
                </p>
                """);
        out.printf("</body></html>");
        out.close();
    }
}
