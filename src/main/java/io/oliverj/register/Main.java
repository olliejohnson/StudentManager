package io.oliverj.register;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

class Main {

    //Converts a string in to a date based on the MM/DD/YYYY format
    public static Date stringToDate(String date) throws ParseException {
        return new SimpleDateFormat("MM/dd/yyyy").parse(date);
    }

    public static void main(String[] args) {

        // Create and Instance of Registration Database called r
        RegistrationDatabase r = new RegistrationDatabase();

        while(true) {
            //Is Console Not Null
            if (System.console() != null) {
                //Prints What You Need To Do
                System.out.println("Welcome to User Registration please Enter Command");
                System.out.println("Please Enter [List, Add, Remove, Get]");
                System.out.print("%> ");

                //Capture Input
                String in = System.console().readLine();

                //Check Input
                if ("List".equals(in)) {
                    System.out.println(r.listRegistrations());
                } else if ("Add".equals(in)) {
                    System.out.print("First Name %> ");
                    String name = System.console().readLine();
                    System.out.print("Last Name %> ");
                    String lastName = System.console().readLine();
                    System.out.print("Date Of Birth (MM/DD/YYYY) %> ");
                    String dob = System.console().readLine();
                    try {
                        r.addRegistration(new Registration(name, lastName, stringToDate(dob)));
                    } catch (ParseException e) {
                        System.err.print("stringToDate: Parse Exception");
                    }
                } else if ("Remove".equals(in)) {
                    System.out.print("UUID %> ");
                    String uuid = System.console().readLine();
                    System.out.println("Removed: " + r.getRegistration(UUID.fromString(uuid)));
                    r.removeRegistration(UUID.fromString(uuid));
                } else if ("Get".equals(in)) {
                    System.out.print("UUID %> ");
                    String uuid = System.console().readLine();
                    System.out.println("Got: " + r.getRegistration(UUID.fromString(uuid)));
                } else {
                    System.out.println("Command Not Found");
                }
                System.out.flush();
            } else {
                System.out.println("Console is null");
                break;
            }

        }
    }
}
