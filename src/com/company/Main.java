package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean sensitive = false;
        if (args.length > 0) if (args[0].equals("-cs")) sensitive = true;
        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("Enter a string to proceed or 'q' to exit");
            String command = in.nextLine();
            if(command.equals("q")) System.exit(0);
            else {
                System.out.println("Now enter a substring to find");
                String substring = in.nextLine();
                System.out.println(SearchString.searchWord(substring, command, sensitive));
            }
        }
    }
}
