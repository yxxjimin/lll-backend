package lll.backend.common.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@Component
@Slf4j // Temp
public class LoggerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        long start = System.currentTimeMillis();

        filterChain.doFilter(requestWrapper, responseWrapper);

        long end = System.currentTimeMillis();
        int status = responseWrapper.getStatus();

        String userId = getUserId();

        log.info(
                "[HTTP] {}:{} {} \"{}\" userId={}, {} ({} ms)",
                requestWrapper.getRemoteAddr(), // TODO: consider "X-Forwarded-For" on production
                requestWrapper.getRemotePort(),
                requestWrapper.getMethod(),
                requestWrapper.getRequestURI(),
                userId,
                HttpStatus.valueOf(status),
                end - start
        );

        responseWrapper.copyBodyToResponse(); // fill response body
    }

    private String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal() instanceof UserDetails
                ? ((UserDetails) authentication.getPrincipal()).getUsername()
                : "null";
    }
}
