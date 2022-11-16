package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.model.service.EventService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

abstract public class Page {
    private final UserService userService = new UserService();
    private final EventService eventService = new EventService();

    private HttpServletRequest request;

    protected void action(HttpServletRequest request, Map<String, Object> view) {
        //No operation
    }

    protected void before(HttpServletRequest request, Map<String, Object> view) {
        this.request = request;

        view.put("userCount", userService.findCount());

        User user = getUser();
        if (user != null) {
            view.put("user", user);
        }

        putMessage(request, view);
    }

    protected void after(HttpServletRequest request, Map<String, Object> view) {
        //No operation.
    }

    protected void putMessage(HttpServletRequest request, Map<String, Object> view) {
        String message = getMessage();
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            removeMessage();
        }
    }

    public boolean userExists() {
        return getUser() != null;
    }

    public UserService getUserService() {
        return userService;
    }

    public EventService getEventService() {
        return eventService;
    }

    protected User getUser() {
        return (User) request.getSession().getAttribute("user");
    }

    protected void setUser(User user) {
        if (user == null) {
            removeUser();
        } else {
            if (userExists()) {
                getEventService().addEvent(getUser(), Event.Type.LOGOUT);
            }
            request.getSession().setAttribute("user", user);
        }
    }

    protected void removeUser() {
            request.getSession().removeAttribute("user");
    }

    protected String getMessage() {
        return (String) request.getSession().getAttribute("message");
    }

    protected void removeMessage() {
        request.getSession().removeAttribute("message");
    }

    protected void setMessage(String message) {
        if (message == null) {
            removeMessage();
        } else {
            request.getSession().setAttribute("message", message);
        }
    }
}
