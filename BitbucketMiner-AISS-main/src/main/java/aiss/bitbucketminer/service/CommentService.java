package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.raw.comment.RawComment;
import aiss.bitbucketminer.model.raw.comment.RawCommentWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Value("${bitbucketminer.base}")
    private String base;

    @Autowired
    RestTemplate template;

    public List<RawComment> getRawComments (
            String workspace,
            String repo_slug,
            String maxPages,
            int issueId
    ) {
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<RawCommentWrapper> entity = new HttpEntity<>(null, headers);

            int remainingPages = Integer.parseInt(maxPages);
            int pagesCounter = 1;

            List<RawComment> rawComments = new ArrayList<>();

            while (pagesCounter <= remainingPages) {
                ResponseEntity<RawCommentWrapper> response = this.template.exchange(
                        this.base + "/" + workspace + "/" + repo_slug + "/issues/" + issueId + "/comments?page=" + pagesCounter ,
                        HttpMethod.GET,
                        entity,
                        RawCommentWrapper.class
                );

                List<RawComment> fetchedRawComments = response.getBody().getValues();

                if (fetchedRawComments.isEmpty()) {
                    return rawComments;
                }

                pagesCounter++;

                rawComments.addAll(fetchedRawComments);
            }

            return rawComments;
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

}
