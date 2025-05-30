package aiss.bitbucketminer.model.raw.user;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RawUserLinksAvatar {

    @JsonProperty("href")
    private String href;

    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    @JsonProperty("href")
    public void setHref(String href) {
        this.href = href;
    }

    @Override
    public String toString() {
        return "RawUserLinksAvatar{" +
                "href='" + href + '\'' +
                '}';
    }

}
