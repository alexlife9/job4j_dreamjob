package ru.job4j.dreamjob.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static java.util.Objects.isNull;

/**
 * Проверка доступа с помощью фильтра
 *
 * @author Alex_life
 * @version 2.0
 * @since 23.10.2022
 */
@Component
public class AuthFilter implements Filter {
    private static final String PAGES_FILTER =
            String.format("%S%S%S%S",
                    "loginPage", "formAddUser",
                    "login", "registration");
    private static final String USER = "user";
    private static final String LOGIN_PAGE = "/loginPage";
    private final Set<String> pages = Set.of(PAGES_FILTER);

    private boolean containPages(String uri) {
        return pages.stream().allMatch(uri::endsWith);
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (containPages(uri)) {
            chain.doFilter(req, res);
            return;
        }
        if (isNull(req.getSession().getAttribute(USER))) {
            res.sendRedirect(req.getContextPath() + LOGIN_PAGE);
            return;
        }
        chain.doFilter(req, res);
    }
}

