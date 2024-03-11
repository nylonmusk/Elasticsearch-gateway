package index;

import config.ElasticConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class FileToDatabaseIndexer {
    public void getJsonFile() throws IOException {
        try (ElasticConfiguration elasticConfiguration = new ElasticConfiguration("localhost", 9200, "")) {
            String index = "news_articles";
            String filePath = "C:\\Users\\mayfarm\\Desktop\\es\\crawl_yhn.json";
            List<Map<String, Object>> jsonData = readJsonFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Map<String, Object>> readJsonFile(String filePath) throws IOException {
        String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonContent, List.class);
    }
}
