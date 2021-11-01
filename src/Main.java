import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        GameProgress gameProgress1 = new GameProgress(64, 12, 4, 56.839);
        GameProgress gameProgress2 = new GameProgress(100, 2, 2, 123.235);
        GameProgress gameProgress3 = new GameProgress(25, 33, 142, 444.07);

        File saving1 = new File("C:\\Users\\Games", "saving1.dat");
        saveGame(saving1,gameProgress1);

        File saving2 = new File("C:\\Users\\Games", "saving2.dat");
        saveGame(saving2,gameProgress2);

        File saving3 = new File("C:\\Users\\Games", "saving3.dat");
        saveGame(saving3,gameProgress3);

        File zipArchive = new File("C:\\Users\\Games", "archive.zip");
        try (FileOutputStream fos = new FileOutputStream(zipArchive);
             ZipOutputStream zos = new ZipOutputStream(fos)){
            fillOutArchive(zos, saving1, "save1.dat");
            fillOutArchive(zos, saving2, "save2.dat");
            fillOutArchive(zos, saving3, "save3.dat");
        }


    }

    public static void saveGame(File file, GameProgress gameProgress){
        try(FileOutputStream fileOutputStream = new FileOutputStream(file, false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(gameProgress);
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static void fillOutArchive(ZipOutputStream zipOutputStream,
                                      File fileToPack,
                                      String zipedFile) throws IOException {
        try (FileInputStream fis = new FileInputStream(fileToPack)){
            ZipEntry entry = new ZipEntry(zipedFile);
            zipOutputStream.putNextEntry(entry);
            byte[] text = new byte[fis.available()];
            zipOutputStream.write(text);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }
    }

}