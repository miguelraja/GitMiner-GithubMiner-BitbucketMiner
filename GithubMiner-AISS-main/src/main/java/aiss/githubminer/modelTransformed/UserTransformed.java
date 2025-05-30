package aiss.githubminer.modelTransformed;

import aiss.githubminer.model.User;
import aiss.githubminer.model.UserData;
import com.fasterxml.jackson.annotation.JsonProperty;

  // Watch out: User is a reserved keyword in H2
public class UserTransformed {


    @JsonProperty("id")
    private String id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("name")
    private String name;

    @JsonProperty("avatar_url")
    private String avatar_url;

    @JsonProperty("web_url")
    private String web_url;

    public UserTransformed() {}
    public UserTransformed(UserData rawUser) {
        this.id = rawUser.getId().toString();
        this.username = rawUser.getLogin();
        this.name = rawUser.getName();
        this.avatar_url = rawUser.getAvatarUrl();
        this.web_url = rawUser.getReposUrl();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getavatar_url() {
        return avatar_url;
    }

    public void setavatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getweb_url() {
        return web_url;
    }

    public void setweb_url(String web_url) {
        this.web_url = web_url;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UserTransformed.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("username");
        sb.append('=');
        sb.append(((this.username == null)?"<null>":this.username));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("avatar_url");
        sb.append('=');
        sb.append(((this.avatar_url == null)?"<null>":this.avatar_url));
        sb.append(',');
        sb.append("web_url");
        sb.append('=');
        sb.append(((this.web_url == null)?"<null>":this.web_url));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
