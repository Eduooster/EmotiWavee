package org.example.emotiwave.infra.client;



import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import org.example.emotiwave.domain.entities.Musica;
import org.example.emotiwave.domain.exceptions.HuggingFaceException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class HuggingFaceZeroShotClient {
    private final ObjectMapper objectMapper;
    private final WebClient webClient;
    String huggingKey = System.getenv("HUGGING_KEY");
    String url = "https://api-inference.huggingface.co/models/facebook/bart-large-mnli";

    public HuggingFaceZeroShotClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.webClient = WebClient.builder().baseUrl("https://api-inference.huggingface.co").defaultHeader("Authorization", new String[]{"Bearer " + this.huggingKey}).defaultHeader("Content-Type", new String[]{"application/json"}).exchangeStrategies(ExchangeStrategies.builder().codecs((configurer) -> configurer.defaultCodecs().maxInMemorySize(Integer.MAX_VALUE)).build()).build();
    }

    public ArrayList<Serializable> obterAnalise(Musica musica) throws IOException {
        String response = this.enviarRequisicao(musica);
        return this.responseParseado(response);
    }

    public ArrayList<Serializable> responseParseado(String responseBody) throws IOException, HuggingFaceException {
        JsonNode root = this.objectMapper.readTree(responseBody);
        String topLabel = null;
        BigDecimal topScore = null;
        if (root.has("labels") && root.has("scores")) {
            JsonNode labels = root.get("labels");
            JsonNode scores = root.get("scores");
            if (labels.isArray() && labels.size() > 0) {
                topLabel = labels.get(0).asText();
                double topScoreDouble = scores.get(0).asDouble();
                topScore = BigDecimal.valueOf(topScoreDouble);
                return new ArrayList(Arrays.asList(topLabel, topScore));
            }
        } else {
            System.err.println("Resposta inesperada da Hugging Face: " + responseBody);
        }

        return null;
    }

    public String enviarRequisicao(Musica musica) throws IOException {
        String bodyString = this.montarJson(musica);
        return (String)((WebClient.RequestBodySpec)this.webClient.post().uri("/models/facebook/bart-large-mnli", new Object[0])).bodyValue(bodyString).retrieve().bodyToMono(String.class).block();
    }

    private String montarJson(Musica musica) throws IOException {
        ObjectNode bodyJson = this.objectMapper.createObjectNode();
        bodyJson.put("inputs", musica.getLetra());
        ObjectNode parameters = this.objectMapper.createObjectNode();
        ArrayNode candidateLabels = this.objectMapper.createArrayNode();
        candidateLabels.add("happy");
        candidateLabels.add("sad");
        candidateLabels.add("angry");
        candidateLabels.add("calm");
        candidateLabels.add("romantic");
        candidateLabels.add("love");
        candidateLabels.add("heartbreak");
        candidateLabels.add("lonely");
        candidateLabels.add("nostalgic");
        candidateLabels.add("energetic");
        candidateLabels.add("hopefull");
        parameters.set("candidate_labels", candidateLabels);
        bodyJson.set("parameters", parameters);
        return this.objectMapper.writeValueAsString(bodyJson);
    }
}
