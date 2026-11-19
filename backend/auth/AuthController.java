package backend.auth;

import backend.organization.Organization;
import backend.organization.OrganizationRepository;
import backend.user.AppUser;
import backend.user.Role;
import backend.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final OrganizationRepository orgs;
    private final UserRepository users;
    private final PasswordEncoder encoder;

    public AuthController(OrganizationRepository orgs, UserRepository users, PasswordEncoder encoder) {
        this.orgs = orgs;
        this.users = users;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest r) {
        String email = r.email().trim().toLowerCase();
        if (users.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
        }

        Organization org = new Organization();
        org.setName(r.companyName().trim());
        orgs.save(org);

        AppUser u = new AppUser();
        u.setEmail(email);
        u.setFullName(r.fullName().trim());
        u.setPasswordHash(encoder.encode(r.password()));
        u.setRole(Role.ADMIN);
        u.setOrganization(org);
        users.save(u);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new UserResponse(u.getId(), u.getEmail(), u.getFullName(), u.getRole().name(), org.getId()));
    }
}
