package ru.tinkoff.edu.java.scrapper.domain.jdbc.model;


import java.net.URI;
import java.time.OffsetDateTime;


public record Link(long id, long tgChatId, URI uri, OffsetDateTime lastCheckedAt, OffsetDateTime lastActivity,
                   int issueCount, int answerCount) {
    public Link(long tgChatId, URI uri) {
        this(-1, tgChatId, uri, OffsetDateTime.now(), OffsetDateTime.now(), 0, 0);
    }


}
