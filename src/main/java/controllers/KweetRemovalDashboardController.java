package controllers;

import controllers.validator.RoleValidator;
import domain.Kweet;
import domain.Role;
import domain.User;
import service.KweetService;
import service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@SessionScoped
@RequestScoped
@ManagedBean(name = "kweetDashboard", eager = true)
@RolesAllowed({"Administrator", "Moderator"})
public class KweetRemovalDashboardController {
    @Inject
    private KweetService kweetService;

    @Inject
    private UserService userService;

    private String userId;

    public KweetRemovalDashboardController() throws IOException {
        RoleValidator.validateUserRole((User)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user"), Role.Administrator);
        userId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("userId");
    }

    public String removeKweet(){
        try {
            UUID id  = UUID.fromString(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("kweetId"));
            kweetService.removeKweetById(id);
            return "kweet_dashboard";
        } catch (Exception e){
            e.printStackTrace();
            return "kweet_dashboard";
        }
    }

    public List<Kweet> getKweets(){
        if (userId != null && !userId.isEmpty()){
            return kweetService.getKweetsForUserId(UUID.fromString(userId), 0 ,10);
        }
        return new ArrayList<>();
    }

    public User getUser(){
        return userService.getUserById(UUID.fromString(userId));
    }

    public String getUserId(){
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
