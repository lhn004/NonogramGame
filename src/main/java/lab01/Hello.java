/* *****************************************
*CSCI205 -Software Engineering and Design
* Spring2023
* Instructor: Brian King
* Section: 9am
*
* Name: Linh Nguyen
* Date: 01/24/2023
*
*Lab / Assignment: Lab 01 - Hello, Java!
*
*Description: A "HelloWorld"program in Java
* * *****************************************/

package lab01;
import java.util.Scanner;
public class Hello
{
    public static void main(String[] args){
        Scanner scnr = new Scanner(System.in);
        System.out.println("What's your name?");
        String username = scnr.next();
        System.out.println("Here is your operating system info, " + username);
        System.out.println("OS Name: " + System.getProperty("os.name"));
        System.out.println("OS Architecture: " + System.getProperty("os.arch"));
        System.out.println("OS Version: " + System.getProperty("os.version"));

        System.out.println(String.format("%s your name is %d characters long.", username, username.length()));

        System.out.println("What is your age? ");
        int age = scnr.nextInt();
        if (age < 20)
        {
            System.out.println("You are a teenage");
        }
        else if (age < 30)
        {
            System.out.println("You're in your 20s");
        }
        else
        {
            System.out.println("You're at least 30.");
        }



        System.exit(0);
    }
}
