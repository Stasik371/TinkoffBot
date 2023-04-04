package ru.tinkoff.edu.java.bot.telegram.commands;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.tinkoff.edu.java.bot.webclients.dto.link.request.AddLinkRequest;
import ru.tinkoff.edu.java.bot.webclients.interfaces.LinksClient;

@Component
public class TrackCommand implements Command {

    private final LinksClient linksClient;
    private final String COMMAND = "/track";
    private final String DESCRIPTION = "Отследить ссылку.";

    private final String GOOD_ANSWER_BEFORE = "Введите ссылку в формате URL, которую хотите отслеживать";
    private final String GOOD_ANSWER = "Ссылка успешна добавлена";
    private final String BAD_ANSWER = "Некорректная ссылка, бот поддерживает ссылки в формате URL";

    @Autowired
    public TrackCommand(LinksClient linksClient) {
        this.linksClient = linksClient;
    }

    @Override
    public String command() {
        return COMMAND;
    }

    @Override
    public String description() {
        return DESCRIPTION;
    }

    @Override
    public SendMessage handle(Update update) {
        var msg = update.message().text();
        if (msg.startsWith("/")) return new SendMessage(update.message().chat().id(), GOOD_ANSWER_BEFORE);
        linksClient.addLink(update.message().chat().id(), new AddLinkRequest(msg));
        return new SendMessage(update.message().chat().id(), GOOD_ANSWER);
    }
}
