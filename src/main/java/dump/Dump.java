package dump;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import view.Log;

import java.io.FileWriter;
import java.util.List;
import java.util.Map;

public class Dump {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        // Jackson JSR310 모듈 등록
        // 날짜 및 시간 객체를 JSON으로 직렬화 및 역직렬화하기 위한 Jackson 모듈
        objectMapper.registerModule(new JavaTimeModule());
        // JSON 출력을 포맷팅하여 가독성 향상
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public void makeJson(List<Map<String, Object>> data) {
        try {
            String jsonData = objectMapper.writeValueAsString(data);

            try (FileWriter fileWriter = new FileWriter("C:\\Users\\mayfarm\\Documents\\article.json")) {
                fileWriter.write(jsonData);
                fileWriter.flush();
            }
            Log.info(Dump.class.getName(), "created JSON file successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
