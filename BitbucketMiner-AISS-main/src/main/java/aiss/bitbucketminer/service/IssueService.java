package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.raw.issue.RawIssue;
import aiss.bitbucketminer.model.raw.issue.RawIssueWrapper;
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
public class IssueService {

    @Value("${bitbucketminer.base}")
    private String base;

    @Autowired
    RestTemplate template;

    public List<RawIssue> getRawIssues(
            String workspace,
            String repo_slug,
            String nIssues,
            String maxPages
    ) {
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<RawIssueWrapper> entity = new HttpEntity<>(null, headers);

            int remainingIssues = Integer.parseInt(nIssues) * Integer.parseInt(maxPages);
            int pagesCounter = 1;

            List<RawIssue> rawIssues = new ArrayList<>();

            while (remainingIssues > 0) {
                int actualNIssues = Math.min(remainingIssues, 100);

                ResponseEntity<RawIssueWrapper> response = this.template.exchange(
                        this.base + "/" + workspace + "/" + repo_slug + "/issues?pagelen=" + actualNIssues + "&page=" + pagesCounter,
                        HttpMethod.GET,
                        entity,
                        RawIssueWrapper.class
                );

                List<RawIssue> fetchedRawIssues = response.getBody().getValues();

                if (fetchedRawIssues.isEmpty()) {
                    return rawIssues;
                }

                remainingIssues -= fetchedRawIssues.size();
                pagesCounter++;

                rawIssues.addAll(fetchedRawIssues);
            }

            return rawIssues;
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

}
