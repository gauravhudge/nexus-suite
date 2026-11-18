package backend.organization;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

    private final OrganizationRepository repo;

    public OrganizationController(OrganizationRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Organization> all() {
        return repo.findAll();
    }

    @PostMapping
    public Organization create(@Valid @RequestBody Organization org) {
        org.setId(null);
        return repo.save(org);
    }
}