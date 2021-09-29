import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

class Main {
    public static Date stringToDate(String date) throws ParseException {
        Date date1 = new SimpleDateFormat("MM/dd/yyyy").parse(date);
        return date1;
    }

    public static void main(String[] args) {

        RegistrationDatabase r = new RegistrationDatabase();

        Console cnsl = System.console();

        while(true) {
            if (cnsl != null) {
                System.out.println("Welcome to User Registration please Enter Command");
                System.out.println("Please Enter [List, Add, Remove, Get]");
                System.out.print("%> ");
                String in = cnsl.readLine();
                if ("List".equals(in)) {
                    System.out.println(r.listRegistrations());
                } else if ("Add".equals(in)) {
                    System.out.print("First Name %> ");
                    String name = cnsl.readLine();
                    System.out.print("Last Name %> ");
                    String lastName = cnsl.readLine();
                    System.out.print("Date Of Birth (MM/DD/YYYY) %> ");
                    String dob = cnsl.readLine();
                    try {
                        r.addRegistration(new Registration(name, lastName, stringToDate(dob)));
                    } catch (ParseException e) {
                        System.err.print("stringToDate: Parse Exception");
                    }
                } else if ("Remove".equals(in)) {
                    System.out.print("UUID %> ");
                    String uuid = System.console().readLine();
                    r.removeRegistration(UUID.fromString(uuid));
                } else if ("Get".equals(in)) {
                    System.out.print("UUID %> ");
                    String uuid = System.console().readLine();
                    r.getRegistration(UUID.fromString(uuid));
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
