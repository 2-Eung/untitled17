import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class Main {
    public static void writeFile(String filename, String content) {
        Path filePath = Paths.get(filename); // Path : NIO 시스템이있는 클래스
                                             // 파일 경로관련 특화되어있음
//        System.out.println(filePath);        // filePath 의 filename 출력
                                                                             // 없으면 생성하고, 있으면 쓰기모드로 채널을 열겠다
        try (FileChannel writeChannel = FileChannel.open(filePath, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024); // 1024byte = 1kb 짜리 버퍼 생성

            buffer.put(content.getBytes()); // content 는 String 이라서 byte로 바꿔야 한다.
            buffer.flip(); // WRITE 모드 에서 CREATE 모드로 변경 ( 쓰기모드 -> 새로쓰기모드 )
            writeChannel.write(buffer);
            System.out.println("파일을 성공적으로 작성 완료했습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readFile(String filename) {
        Path filePath = Paths.get(filename);
                                                                        // 읽기모드
        try (FileChannel readChannel = FileChannel.open(filePath, StandardOpenOption.READ)) {
            ByteBuffer buffer = ByteBuffer.allocate(1024); // 1024byte = 1kb 짜리 버퍼 생성
            int bytesRead = readChannel.read(buffer); // buffer 를 읽을때 읽은 byte 수

            while (bytesRead != -1) {                 // 읽은게 없으면 -1 (다 읽었다!)
                buffer.flip(); // PUT -> GET

                String chunk = StandardCharsets.UTF_8.decode(buffer).toString();
                System.out.print(chunk);

                buffer.clear();
                bytesRead = readChannel.read(buffer);
            }
            System.out.println("\n파일 읽기가 완료 되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        writeFile("dinner-menu.txt", "오늘의 저녁 메뉴는 뭘까요?");
        readFile("dinner-menu.txt");
    }
}