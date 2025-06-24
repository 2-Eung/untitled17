import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void writeFile(String filename,String content) {
//        FileWriter writer = null;
//        try {
//            writer = new FileWriter(filename);
//            writer.write(content);
//        } catch (IOException e) {
//            e.printStackTrace();                    // writer 를 닫는 코드는
//        } finally {                                 // 항상 실행되야하는건데 조건에따라
//            try {                                   // 실행해야해서 if
//                if (writer != null) writer.close(); // 에러 발생할까봐 try-catch
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }                       // 이어쓰기 하려면 '여기에' 두번째인자 true 추가

        try (FileWriter writer = new FileWriter(filename, true)) { // try-with-resource
            writer.write(content);                           // finally 를 괄호() 안에서 수행
        } catch (IOException e) {                            // 위의 코드와 같은 결과
            e.printStackTrace();                             // finally 가 위코드와 비슷한
        }                                                    // 조건문일때 대체가능
    }
    public static void readFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;                                    // FileReader 안에 BufferedReader 있음

            while (( line = reader.readLine()) != null) {    // .readLine() : 한줄씩 읽기위한 메소드
                System.out.println(line);                   // 메모장의 줄 단위로 읽어온다
            }                                               // 줄이 없으면 null 이 찍혀 종료
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {         // 좌측 src 에 .txt 파일 생성됨
//        writeFile("lunch-menu.txt","오늘의 저녁 메뉴는 무엇인가요?");
//        System.out.println("파일 생성 완료");          // 기본적으로 덮어쓰기

//        writeFile("lunch-menu.txt","\n제육");
//        System.out.println("이어쓰기");          // true 추가되면 이어쓰기

        readFile("lunch-menu.txt");     // 읽어오기
    }
}