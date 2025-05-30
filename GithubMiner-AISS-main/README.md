<h1>GitHubMiner</h1>
This is an adapter service that will read data from the GitHub REST API and send it
to GitMiner using the appropriate data model (Figure 2). In a real data engineering scenario this
process could be performed periodically (e.g. every 24 hours). In this case, the service will
implement a RESTful service with an operation to get the data from GitHub and send it to GitMiner
each time it is invoked. Specifically, the operation shall be able to be invoked as follows, providing
mandatory parameters for name of the owner and name of the repository:

POST apipath/{owner}/{repoName}[?sinceCommits=5&sinceIssues=30&maxPages=2]

Optional parameters:
* sinceCommits: The operation will return the commits sent in the last X days, where X is
the value entered as parameter. Default value: 2.
Architecture and Integration of Software Systems 2024/2025
2
* sinceIssues: The operation will return the issues updated in the last X days, where X is the
value entered as parameter. Default value: 20.
* maxPages: Maximum number of pages to be iterated in all cases. Default value: 2.
It is recommended to also implement an equivalent read-only operation for testing purposes, i.e.
displaying the search results without sending them to GitMiner
