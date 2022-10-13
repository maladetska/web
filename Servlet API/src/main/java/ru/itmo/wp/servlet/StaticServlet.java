package ru.itmo.wp.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        String[] splittedUris = uri.split("\\+");
        response.setContentType(getServletContext().getMimeType(splittedUris[0]));
        for (String currUri : splittedUris) {
            File file = new File(getServletContext().getRealPath("") + "../../src/main/webapp/static/" + currUri);
            if (!file.exists()) {
                file = new File(getServletContext().getRealPath("/static/" + currUri));
            }
            if (file.isFile()) {
                try (OutputStream outputStream = response.getOutputStream()) {
                    Files.copy(file.toPath(), outputStream);
                }
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }
}
