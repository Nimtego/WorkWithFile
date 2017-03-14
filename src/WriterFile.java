import java.io.FileReader;
import java.io.IOException;

/**
 * Created by myasnikov on 14.03.2017.
 */
public class WriterFile {
    public static void solu() {
        String address = "C:\\МЯСНИКОВ\\IDEA_Projeckt\\MyFile\\notes4.docx";
        int countA = 0;
        int countB = 0;
        try {
            FileReader fileReader = new FileReader(address);
            int i;
            while((i=fileReader.read())!=-1){
                System.out.print((char)i);
           /* while ((i = fileReader.read()) != -1) {
                if ((char) i == '}') {
                    countA++;
                }
                if ((char) i == '{') {
                    countB++;
                }*/
            }
            //  System.out.println(countA == countB);
        } catch (IOException e) {
            e.printStackTrace();
        }

 /*       try(FileWriter writer = new FileWriter(address, false))
        {
            // запись всей строки
            String text = "Мама мыла раму, раму мыла мама";
            writer.write(text);
            // запись по символам
            writer.append('\n');
            writer.append('E');

            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
        try {
            FileReader fileReader = new FileReader(address);
            int c;
            while((c=fileReader.read())!=-1){
                System.out.print((char)c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    */
    }
}
