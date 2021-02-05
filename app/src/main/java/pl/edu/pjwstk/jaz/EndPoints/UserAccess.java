package pl.edu.pjwstk.jaz.EndPoints;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasAnyAuthority('User')")
@RestController
public class UserAccess {
    @GetMapping("/users")
    public void users(){

    }
}
