package pe.edu.upc.fromzero.ServiceImplements;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.fromzero.DTO.ChatMensajeDTO;
import pe.edu.upc.fromzero.ServiceInterface.IChatIAService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatIAServiceImplement implements IChatIAService {

    @Autowired
    private ChatClient chatClient;

    @Override
    public String Responder(List<ChatMensajeDTO> mensajes) {
        if (mensajes == null || mensajes.isEmpty()) {
            throw new IllegalArgumentException("La conversación no puede estar vacía");
        }

        List<Message> historial = new ArrayList<>();
        for (ChatMensajeDTO m : mensajes) {
            if (m.getContenido() == null || m.getContenido().isBlank()) {
                continue;
            }
            if ("assistant".equalsIgnoreCase(m.getRole())) {
                historial.add(new AssistantMessage(m.getContenido()));
            } else {
                historial.add(new UserMessage(m.getContenido()));
            }
        }

        // El system prompt por defecto ya viene configurado en ChatIAConfig
        Prompt prompt = new Prompt(historial);
        return chatClient.prompt(prompt).call().content();
    }
}
