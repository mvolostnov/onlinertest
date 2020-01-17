package by.onliner.test.data;

import java.util.Map;

public class ReadSysVariables {
    public class ReadEnvironmentVariable {
        public  void main(String[] args) {

            System.out.println("Read Specific Enviornment Variable");
            System.out.println("JAVA_HOME Value:- " + System.getenv("JAVA_HOME"));

            System.out.println("Read All Variables:-");

            Map<String, String> map = System.getenv();
            for (Map.Entry <String, String> entry: map.entrySet()) {
                System.out.println("Variable Name:- " + entry.getKey() + " Value:- " + entry.getValue());
            }

        }

    }

}
