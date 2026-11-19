package backend.auth;

public record UserResponse(Long id, String email, String fullName, String role, Long organizationId) {
}
