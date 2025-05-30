package aiss.bitbucketminer.model.raw.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RawUser {

    @JsonProperty("display_name")
    private String display_name;

    @JsonProperty("links")
    private RawUserLinks links;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("display_name")
    public String getDisplay_name() {
        return display_name;
    }

    @JsonProperty("display_name")
    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    @JsonProperty("links")
    public RawUserLinks getLinks() {
        return links;
    }

    @JsonProperty("links")
    public void setLinks(RawUserLinks links) {
        this.links = links;
    }

    @JsonProperty("uuid")
    public String getUuid() {
        return uuid;
    }

    @JsonProperty("uuid")
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @JsonProperty("nickname")
    public String getNickname() {
        return nickname;
    }

    @JsonProperty("nickname")
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "RawUser{" +
                "display_name='" + display_name + '\'' +
                ", links=" + links +
                ", uuid='" + uuid + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

}
