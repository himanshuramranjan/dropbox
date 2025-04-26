package models;

import java.time.LocalDateTime;

public class FileVersion {
    private final int version;
    private final String content;
    private final LocalDateTime timestamp;

    public FileVersion(int version, String content) {
        this.version = version;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }

    public int getVersion() {
        return version;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
