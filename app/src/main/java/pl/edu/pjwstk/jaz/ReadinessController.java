package pl.edu.pjwstk.jaz;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;


@RestController
public class ReadinessController {

    private final EntityManager entityManager;

    public ReadinessController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PreAuthorize("hasAnyAuthority('Everyone')")
    @Transactional
    @GetMapping("auth0/is-ready")
    public void readinessTest() {
    }
}
