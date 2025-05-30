package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.raw.project.RawProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProjectService {

    @Value("${bitbucketminer.base}")
    private String base;

    @Autowired
    RestTemplate template;

    public RawProject getRawProject(String workspace, String repo_slug) {
        try {
            HttpHeaders headers = new HttpHeaders();
            // BitBucket only allows once repository to be accessed with an Auth Token???
            HttpEntity<RawProject> entity = new HttpEntity<>(null, headers);

            ResponseEntity<RawProject> response = this.template.exchange(
                    this.base + "/" + workspace + "/" + repo_slug,
                    HttpMethod.GET,
                    entity,
                    RawProject.class
            );

            return response.getBody();
        } catch (Exception e) {
            return null;
        }
    }

}
