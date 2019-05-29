package controllers;

import controllers.callback.LoginCallbackHandler;
import domain.User;
import service.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.auth.login.LoginContext;
import java.io.IOException;

@ManagedBean(name = "loginController")
@RequestScoped
public class LoginController {

    private String username;
    private String password;

    @Inject
    private UserService userService;

    private LoginContext lc;

    public LoginController(){
    }

    public void login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        try{
            LoginCallbackHandler handler = new LoginCallbackHandler();
            handler.setPassword(password);
            handler.setUsername(username);
            lc = new LoginContext("kwetter-security-api", handler);
            lc.login();
            User user = userService.getUserByUsername(username);
            context.getExternalContext().getSessionMap().put("user", user);
            context.getExternalContext().redirect(context.getExternalContext().getApplicationContextPath() + "/view/admin/dashboard.xhtml");
        } catch (Exception e){
                username = null;
                password = null;
                context.addMessage(null, new FacesMessage("Unknown login. Please try again."));
                context.getExternalContext().redirect("failure.xhtml");
            e.printStackTrace();
        }
    }

    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
