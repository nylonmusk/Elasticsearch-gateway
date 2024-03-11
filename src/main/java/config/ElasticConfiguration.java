package config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.Closeable;
import java.io.IOException;

public class ElasticConfiguration implements Closeable {

    public final RestHighLevelClient elasticClient;

    public ElasticConfiguration(String host, int port, String user) {
        elasticClient = getElasticSearchClient(host, port, user);
    }

    public RestHighLevelClient getElasticClient() {
        return this.elasticClient;
    }

    private RestHighLevelClient getElasticSearchClient(String host, int port, String user) {
        String protocol = "http";
        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost(host, port, protocol));
        if (user != null) {
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials(user));
            restClientBuilder.setHttpClientConfigCallback(httpClientBuilder
                    -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        }
        return new RestHighLevelClient(restClientBuilder);
    }

    @Override
    public void close() throws IOException {
        elasticClient.close();
    }
}