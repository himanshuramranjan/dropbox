package models;

import java.util.*;

public class File {
    private final String id;
    private final Metadata metadata;
    private final List<FileVersion> versions;

    public File(String name, String content) {
        this.id = UUID.randomUUID().toString();
        this.versions = new ArrayList<>();
        this.metadata = new Metadata(name, content.length());
        addVersion(content);
    }

    public void addVersion(String content) {
        versions.add(new FileVersion(versions.size() + 1, content));
        metadata.setSize(content.length());
        metadata.updateModifiedTime();
    }

    public String getCurrentContent() {
        return versions.get(versions.size() - 1).getContent();
    }

    public List<FileVersion> getVersions() {
        return Collections.unmodifiableList(versions);
    }

    public void rollbackToVersion(int versionNumber) {
        if(versionNumber <= 0 || versionNumber > versions.size()) {
            throw new IllegalArgumentException("Invalid version number");
        }
        FileVersion version = versions.get(versionNumber - 1);
        // Ideally we should remove the other versions till the given version number
        addVersion(version.getContent());
    }

    public Metadata getMetadata() {
        return metadata;
    }
}
