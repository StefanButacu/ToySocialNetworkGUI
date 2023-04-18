package com.toysocialnetworkgui.service.export;

import com.toysocialnetworkgui.utils.CONSTANTS;
import com.toysocialnetworkgui.utils.UserFriendDTO;
import com.toysocialnetworkgui.utils.UserMessageDTO;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonExportStrategy implements ExportStrategy{
    @Override
    public void export(List<UserFriendDTO> friends, List<UserMessageDTO> messages, String filePath) throws IOException {
        JSONArray friendArray = new JSONArray(friends.stream().map(JsonUserFriendDTO::new).collect(Collectors.toList()));

        JSONArray messagesArray = new JSONArray(messages.stream().map(JsonUserMessageDTO::new).collect(Collectors.toList()));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messages", messagesArray);
        jsonObject.put("friends", friendArray);

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonObject.toString(4));
            System.out.println("JSON file created: " + filePath);
        }

    }
    public static class JsonUserFriendDTO {
        private  String firstName, lastName;
        private  LocalDate date;

        public JsonUserFriendDTO(UserFriendDTO userFriendDTO){
            this.firstName = userFriendDTO.getFirstName();
            this.lastName = userFriendDTO.getLastName();
            this.date = userFriendDTO.getDate();
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "JsonUserFriendDTO{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", date=" + date +
                    '}';
        }
    }
    public static class JsonUserMessageDTO {
        private  String firstName, lastName, message, date;
        public JsonUserMessageDTO(UserMessageDTO userMessageDTO){
            this.firstName = userMessageDTO.getSender().getFirstName();
            this.lastName = userMessageDTO.getSender().getLastName();
            this.message = userMessageDTO.getMessageText();
            this.date = userMessageDTO.getDate().format(DateTimeFormatter.ofPattern(CONSTANTS.DATETIME_PATTERN));

        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "JsonUserMessageDTO{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", message='" + message + '\'' +
                    ", date='" + date + '\'' +
                    '}';
        }
    }
}
