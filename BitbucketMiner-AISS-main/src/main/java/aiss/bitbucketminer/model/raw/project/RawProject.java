package aiss.bitbucketminer.model.raw.project;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RawProject {

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("website")
    private String website;

    @JsonProperty("uuid")
    public String getUuid() {
        return uuid;
    }

    @JsonProperty("uuid")
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("website")
    public String getWebsite() {
        return website;
    }

    @JsonProperty("website")
    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "RawProject{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", website='" + website + '\'' +
                '}';
    }

}
