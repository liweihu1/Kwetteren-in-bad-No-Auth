package controllers.validator;

import domain.Role;
import domain.User;

import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.Collection;

public class RoleValidator {
    public static void validateUserRole(User user, Role roleToContain) throws IOException {
        if (!user.getRoles().contains(roleToContain)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().invalidateSession();
            context.getExternalContext().redirect(context.getExternalContext().getApplicationContextPath() + "/view/error/401.xhtml");
        }
    }

    public static void validateUserRoles(User user, Collection rolesToContain) throws IOException {
        if (!user.getRoles().containsAll(rolesToContain)) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().invalidateSession();
            context.getExternalContext().redirect(context.getExternalContext().getApplicationContextPath() + "/view/error/401.xhtml");
        }
    }
}
