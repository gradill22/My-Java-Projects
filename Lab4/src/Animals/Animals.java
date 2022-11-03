package Animals;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Animals {
    private static ArrayList<Elephant> elephants;

    public static void main(String[] args) {
        elephants = new ArrayList<>();
        getText();
        for(Elephant e : elephants) {
            System.out.println(e.toString());
        }
    }

    private static void getText() {
        try {
            File file = new File("DataLab3.txt");
            Desktop d = Desktop.getDesktop();
            if(file.exists()) {
                d.open(file);
            }
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String string = br.readLine();
            StringBuilder str = new StringBuilder();
            String name = null;
            int age = -1;
            boolean sick = false;
            double weight;
            while(string != null) {
                for(int i = 0; i < string.length(); i++) {
                    if(string.charAt(i) != ',' && string.charAt(i) != ' ') {
                        str.append(string.charAt(i));
                    } else {
                        if(name == null) {
                            name = str.toString();
                        }
                        else if(age == -1) {
                            age = Integer.parseInt(str.toString());
                        }
                        else if(str.toString().equals("true") || str.toString().equals("false")) {
                            sick = Boolean.parseBoolean(str.toString());
                        }
                        str.delete(0, str.length());
                        i++;
                    }
                }
                weight = Double.parseDouble(str.toString());
                elephants.add(new Elephant(name, age, sick, weight));
                str.delete(0, str.length());
                string = br.readLine();
                name = null;
                age = -1;
                sick = false;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}