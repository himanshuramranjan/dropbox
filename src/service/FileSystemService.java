package service;

import models.File;
import models.FileVersion;
import models.Folder;

import java.util.Arrays;

public class FileSystemService {
    private final Folder root;

    public FileSystemService() {
        this.root = new Folder("/");
    }

    public void createFolder(String path) {
        navigateToFolder(path, true);
        System.out.println("Folder created at : " + path);
    }

    public void uploadFile(String path, String content) {
        String[] tokens = path.split("/");
        String fileName = tokens[tokens.length - 1];
        String folderPath = String.join("/", Arrays.copyOf(tokens, tokens.length - 1));
        Folder folder = navigateToFolder(folderPath, true);

        folder.getFiles().put(fileName, new File(fileName, content));
        System.out.println("File upload at : " + path);
    }

    public String readFile(String path) {
        File file = getFile(path);
        return file.getCurrentContent();
    }

    public void deleteFile(String path) {
        String[] tokens = path.split("/");
        String fileName = tokens[tokens.length - 1];
        String folderPath = String.join("/", Arrays.copyOf(tokens, tokens.length - 1));
        Folder folder = navigateToFolder(folderPath, false);
        folder.getFiles().remove(fileName);
        System.out.println("File deleted: " + path);
    }

    public void listFolder(String path) {
        Folder folder = navigateToFolder(path, false);
        System.out.println("Folders:");
        folder.getSubFolders().keySet().forEach(System.out::println);
        System.out.println("Files:");
        folder.getFiles().keySet().forEach(System.out::println);
    }

    public void rollbackToVersion(String path, int version) {
        File file = getFile(path);
        file.rollbackToVersion(version);
        System.out.println("Rolled back to version: " + version);
    }

    public void listVersions(String path) {
        File file = getFile(path);
        for (FileVersion v : file.getVersions()) {
            System.out.println("Version: " + v.getVersion() + " - Timestamp: " + v.getTimestamp());
        }
    }

    private File getFile(String path) {
        String[] tokens = path.split("/");
        String fileName = tokens[tokens.length - 1];
        String folderPath = String.join("/", Arrays.copyOf(tokens, tokens.length - 1));
        Folder folder = navigateToFolder(folderPath, false);
        return folder.getFiles().get(fileName);
    }

    private Folder navigateToFolder(String path, boolean createIfAbsent) {
        String[] parts = path.split("/");
        Folder current = root;
        for (String part : parts) {
            if (part.isEmpty()) continue;

            if (!current.getSubFolders().containsKey(part)) {
                if (createIfAbsent) {
                    current.getSubFolders().put(part, new Folder(part));
                } else {
                    throw new IllegalArgumentException("Folder not found: " + part);
                }
            }

            current = current.getSubFolders().get(part);
        }
        return current;
    }
}
