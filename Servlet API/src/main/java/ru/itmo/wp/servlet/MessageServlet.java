package ru.itmo.wp.servlet;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageServlet extends HttpServlet {
    private static final List<Message> messages = new ArrayList<>();

    private static boolean isWhitespaces(String string) {
        return string.trim().length() == 0;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        String uri = request.getRequestURI();
        String authUser = (String) session.getAttribute("user");
        String json = "";
        switch (uri.substring(uri.lastIndexOf('/') + 1)) {
            case "auth":
                String currUser = request.getParameter("user");
                if (currUser == null || isWhitespaces(currUser) || currUser.equals(authUser)) {
                    json = new Gson().toJson(authUser);
                } else {
                    session.setAttribute("user", currUser);
                    json = new Gson().toJson(currUser);
                }
                break;
            case "add":
                String text = request.getParameter("text");
                if (isWhitespaces(text)) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                } else {
                    messages.add(new Message(authUser, text));
                }
                break;
            case "findAll":
                if (authUser != null && !messages.isEmpty()) {
                    json = new Gson().toJson(messages.toArray());
                }
                break;
            default:
                return;
        }

        response.setContentType("application/json");
        response.getWriter().print(json);
        response.getWriter().flush();
    }

    static class Message {
        String user;
        String text;

        Message(String user, String text) {
            this.user = user;
            this.text = text;
        }
    }
}
