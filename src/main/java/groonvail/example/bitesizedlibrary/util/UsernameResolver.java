package groonvail.example.bitesizedlibrary.util;

import jakarta.servlet.http.HttpServletRequest;

public class UsernameResolver {
    public static String ipPortToUsername(HttpServletRequest request) {
        return request.getRemoteAddr() + ":" + request.getRemotePort();
    }
}
