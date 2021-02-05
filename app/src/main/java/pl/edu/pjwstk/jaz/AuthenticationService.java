package pl.edu.pjwstk.jaz;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.jaz.RoleEntity;
import pl.edu.pjwstk.jaz.UserEntity;
import pl.edu.pjwstk.jaz.UserService;
import pl.edu.pjwstk.jaz.UserNotExistsException;

import java.util.stream.Collectors;

@Component
public class AuthenticationService {

     final UserSession userSession;
     final UserService userService;
     final Users users;

    public AuthenticationService(UserSession userSession, UserService userService, Users users) {
        this.userSession = userSession;
        this.userService = userService;
        this.users = users;
    }

    public boolean login(String username, String password) {

        if (userService.userExist(username)){ //users.nameExist(username)
            if ((userService.passwordFindUserByUsername(username,password))) { // users.passwordSame(username, password)
                    userSession.logIn();

                        UserEntity userEntity = userService.findUserByUsername(username);
                        User userFromDatabase = new User(userEntity.getUsername(),userEntity.getPassword(),userEntity.getRoles().stream().map(RoleEntity::getRole).collect(Collectors.toSet()));
                    SecurityContextHolder.getContext().setAuthentication(new AppAuthentication(userFromDatabase));
                    return true;
            }
          // else throw new UserNotExistsException("Bad password");
        }
//        else throw new UserNotExistsException("User not exists");
       // userSession.logOut();
        return false;
    }
}
