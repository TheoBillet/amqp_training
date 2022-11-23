package lernejo.chat;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ChatMessageRepository {
    private List<String> messages = new ArrayList<>();

    void addChatMessage(String message) {
        if (messages.size() == 10) {
            messages.remove(0);
        }
        messages.add(message);
    }

    List<String> getLastTenMessages() {
        if (messages.size() <= 10) {
            return messages;
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < messages.size(); i++) {
            if (i >= messages.size() - 11) {
                res.add(messages.get(i));
            }
        }
        return res;
    }
}
