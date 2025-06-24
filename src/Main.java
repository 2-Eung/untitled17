import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
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
            ByteBuffer buffer = ByteBuffer.allocate(1024); // 1024byte = 1kb

            buffer.put(content.getBytes()); // content 는 String 이라서 byte로 바꿔야 한다.
            buffer.flip(); // WRITE 모드 에서 CREATE 모드로 변경 ( 읽기모드 -> 쓰기모드 )
            writeChannel.write(buffer);
            System.out.println("파일을 성공적으로 작성 완료했습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
//        writeFile("dinner-menu.txt");
        writeFile("dinner-menu.txt" ,"오늘의 저녁 메뉴는 뭘까요?");
    }
}