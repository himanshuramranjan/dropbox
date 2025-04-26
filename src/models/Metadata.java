package models;

import java.time.LocalDateTime;

public class Metadata {
    private String name;
    private long size;
    private final LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Metadata(String name, long size) {
        this.name = name;
        this.size = size;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    public void updateModifiedTime() {
        this.modifiedAt = LocalDateTime.now();
    }

    public void setName(String name) {
        this.name = name;
        updateModifiedTime();
    }

    public void setSize(long size) {
        this.size = size;
        updateModifiedTime();
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
