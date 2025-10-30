package huerto.config;

// Simplified stubbed SecurityConfig so the file compiles without Spring on the classpath.
// Replace with the original implementation when Spring dependencies are available.
public class SecurityConfig {

    private final Object authenticationProvider;

    public SecurityConfig(Object authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    public Object securityFilterChain(Object http) throws Exception {
        // No-op stub when Spring is not present; returns null
        return null;
    }
}