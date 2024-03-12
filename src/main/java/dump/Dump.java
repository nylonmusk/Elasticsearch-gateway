package dump;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.util.List;
import java.util.Map;

public class Dump {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // Jackson JSR310 모듈 등록
        // 날짜 및 시간 객체를 JSON으로 직렬화 및 역직렬화하기 위한 Jackson 모듈
        objectMapper.registerModule(new JavaTimeModule());
    }

    public void makeJson(List<Map<String, Object>> data) {
        try {
            // Convert the List<Map<String, Object>> to JSON
            String jsonData = objectMapper.writeValueAsString(data);

            // Write JSON data to a file (change the file path as needed)
            try (FileWriter fileWriter = new FileWriter( "C:\\Users\\mayfarm\\Documents\\article.json")) {
                fileWriter.write(jsonData);
                fileWriter.flush();
            }
            System.out.println("JSON data successfully written to output.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
