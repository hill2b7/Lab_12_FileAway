
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;
import java.util.ArrayList;
import javax.swing.JFileChooser;


public class Main
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser(); //setting up the JFileChooser
        File selectedFile;//declare file that we will use for the users file choice
        String rec = " "; //declare string variable
        String array[]; //declare array
        int numOfWords = 0; //declare variable
        int numOfLines = 0; //declare variable
        int numOfCharaters = 0; //declare variable

        try //beginning of try block
        {
            File choice = new File(System.getProperty("user.dir")); //will get the users current directory
            chooser.setCurrentDirectory(choice); //sets the directory to the users current directory 

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) //if statement that will run if the file chosen is valid
            {
                selectedFile = chooser.getSelectedFile(); //sets selected file to the file that the user chose
                Path file = selectedFile.toPath(); //will display the path of the file chosen

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE)); //creation of the new input stream so we are able to test for exceptions in the catches
                BufferedReader reader = new BufferedReader(new InputStreamReader(in)); //will read the text from the input string above

                while(reader.ready()) //will run if the stream is ready to be read in
                {
                    rec = reader.readLine();
                    numOfLines++; //increment the number of lines in the file
                    array = rec.split(" "); //this helps us count the number of words in the file
                    numOfWords = numOfWords + array.length; //calculation that will help us figure out the number of words included in the file
                    numOfCharaters = numOfCharaters + rec.length(); //calculation that will help us figure out the number of characters in the file
                }
                System.out.println("The number of lines in the file are " + numOfLines); //output that lets the user know how many lines are in the file
                System.out.println("The name of the file selected is " + selectedFile.getName()); //output that lets the user know the name of the file taht was selected
                System.out.println("The number of words in the file are " + numOfWords); //output that lets the user know the number of words in a file
                System.out.println("The number of characters in the file chosen are " + numOfCharaters); //output that lets the user know the number of characters in the file
                reader.close(); //force closes the stream and any data that is tied to it
                System.out.println("\n\nData file has been processed!"); //output that lets the user know that the data file was processed just for our verification
            }
            else //statement that runs if the if statement is not met
            {
                System.out.println("Must select a file."); //output that lets the user know that they need to select a file
                System.out.println("Try running the program again"); //output that lets the user know that they need to re-run the program
                System.exit(0); //force and exit of the program
            }
        }

        catch(FileNotFoundException e) //will catch if the file in a specific path name given is valid or not
        {
            System.out.println("File not found"); //lets the user know that the file was not found
            e.printStackTrace();
        }
        catch(IOException e) //will catch an IO exception and display the stack trace
        {
            e.printStackTrace();
        }
    }
}
