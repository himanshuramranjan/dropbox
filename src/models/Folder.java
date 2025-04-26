package models;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Folder {
    private final String id;
    private Metadata metadata;
    private final Map<String, File> files;
    private final Map<String, Folder> subFolders;

    public Folder(String name) {
        this.id = UUID.randomUUID().toString();
        this.metadata = new Metadata(name, 0);
        this.files = new HashMap<>();
        this.subFolders = new HashMap<>();
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public Map<String, File> getFiles() {
        return files;
    }

    public Map<String, Folder> getSubFolders() {
        return subFolders;
    }
}
