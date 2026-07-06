package pe.edu.upc.fromzero.Configs;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatIAConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder chatClientBuilder) {
        return chatClientBuilder
                .defaultSystem("""
                        Eres el asistente virtual de FromZero, una plataforma peruana que conecta
                        desarrolladores independientes con microempresas que necesitan infraestructura web.
                        Responde de forma clara, breve y amable, en español, y ayuda al usuario con dudas
                        sobre la plataforma, sus proyectos, o consultas generales de desarrollo web.
                        Si no sabes algo con certeza, dilo honestamente en vez de inventar información.
                        """)
                .build();
    }
}
