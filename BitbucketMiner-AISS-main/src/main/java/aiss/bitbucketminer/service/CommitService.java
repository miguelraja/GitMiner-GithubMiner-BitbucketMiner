package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.raw.commit.RawCommit;
import aiss.bitbucketminer.model.raw.commit.RawCommitWrapper;
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
public class CommitService {

    @Value("${bitbucketminer.base}")
    private String base;

    @Autowired
    RestTemplate template;

    public List<RawCommit> getRawCommits(
            String workspace,
            String repo_slug,
            String nCommits,
            String maxPages
    ) {
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<RawCommitWrapper> entity = new HttpEntity<>(null, headers);

            int remainingCommits = Integer.parseInt(nCommits) * Integer.parseInt(maxPages);
            int pagesCounter = 1;

            List<RawCommit> rawCommits = new ArrayList<>();

            while (remainingCommits > 0) {
                int actualNCommits = Math.min(remainingCommits, 100);

                ResponseEntity<RawCommitWrapper> response = this.template.exchange(
                        this.base + "/" + workspace + "/" + repo_slug + "/commits?pagelen=" + actualNCommits + "&page=" + pagesCounter,
                        HttpMethod.GET,
                        entity,
                        RawCommitWrapper.class
                );

                List<RawCommit> fetchedRawCommits = response.getBody().getValues();

                if (fetchedRawCommits.isEmpty()) {
                    return rawCommits;
                }

                remainingCommits -= fetchedRawCommits.size();
                pagesCounter++;

                rawCommits.addAll(fetchedRawCommits);
            }

            return rawCommits;
        } catch (Exception e) {
            return List.of();
        }
    }

}
