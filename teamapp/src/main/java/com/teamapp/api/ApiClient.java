package com.teamapp.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamapp.model.TeamMember;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static com.teamapp.MainApp.logger;

public class ApiClient {

    private static final String SPRING_SERVER_URL = "http://localhost:8080/api/members";

    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void addMember(TeamMember member) throws Exception {
        String json = objectMapper.writeValueAsString(member);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SPRING_SERVER_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        TeamMember createdMember = objectMapper.readValue(response.body(), TeamMember.class);
        member.setId(createdMember.getId());
    }

    public static List<TeamMember> getAllMembers() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SPRING_SERVER_URL))
                .GET()
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        logger.info("Get Members response code: " + response.statusCode());
        return objectMapper.readValue(response.body(), objectMapper.getTypeFactory().constructCollectionType(List.class, TeamMember.class));
    }

    public static TeamMember getStudentById(String id) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SPRING_SERVER_URL + "/student/" + id))
                .GET()
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        logger.info("Get Student By Id response code: " + response.statusCode());
        return objectMapper.readValue(response.body(), TeamMember.class);
    }

    public static void updateMember(long id, TeamMember updatedMember) throws Exception {
        String json = objectMapper.writeValueAsString(updatedMember);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SPRING_SERVER_URL + "/update/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void deleteMember(long id) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(SPRING_SERVER_URL + "/delete/" + id))
                .DELETE()
                .build();

        HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        logger.info("Delete response code: " + response.statusCode());

    }


}
