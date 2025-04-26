import service.FileSystemService;

public class Main {
    public static void main(String[] args) {

        FileSystemService fileSystem = new FileSystemService();

        fileSystem.createFolder("/docs");
        fileSystem.uploadFile("/docs/readme.txt", "Hello World");
        fileSystem.uploadFile("/docs/readme.txt", "Updated Content");
        System.out.println("Current Content: " + fileSystem.readFile("/docs/readme.txt"));

        fileSystem.listVersions("/docs/readme.txt");
        fileSystem.rollbackToVersion("/docs/readme.txt", 1);
        System.out.println("Content after rollback: " + fileSystem.readFile("/docs/readme.txt"));

        fileSystem.listFolder("/docs");
    }
}