package aiss.bitbucketminer.model.raw.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RawUserLinks {

    @JsonProperty("avatar")
    private RawUserLinksAvatar avatar;

    @JsonProperty("avatar")
    public RawUserLinksAvatar getAvatar() {
        return avatar;
    }

    @JsonProperty("avatar")
    public void setAvatar(RawUserLinksAvatar avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "RawUserLinks{" +
                "avatar='" + avatar + '\'' +
                '}';
    }

}
