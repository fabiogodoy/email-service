package br.com.connectas.emailservice.config.filter;

public class UserResolver {

    private static ThreadLocal<Long> identifier = new ThreadLocal<>();

    public static Long getUserId() { return UserResolver.identifier.get(); }

    public static void setUserId(Long id) { UserResolver.identifier.set(id); }
}
