package jarboe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JarboePropLoader {

    public static void saveToFile(String file, Prop[] variables) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            
            int count = (variables != null) ? variables.length : 0;
            writer.write(String.valueOf(count));
            writer.newLine();
            
            for (int i = 0; i < count; i++) {
                Prop p = variables[i];
                if (p != null) {
                    writer.write(p.name != null ? p.name : "");
                    writer.newLine();
                    writer.write(p.type != null ? p.type : "");
                    writer.newLine();
                    writer.write(p.val != null ? p.val : "");
                    writer.newLine();
                } else {
                	//null handling
                    writer.write("");
                    writer.newLine();
                    writer.write("");
                    writer.newLine();
                    writer.write("");
                    writer.newLine();
                }
            }
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {}
            }
        }
    }

    public static Prop[] loadFromFile(String file) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            
            String countStr = reader.readLine();
            if (countStr == null) {
                return new Prop[0];
            }
            
            int count = Integer.parseInt(countStr.trim());
            Prop[] variables = new Prop[count];
            
            for (int i = 0; i < count; i++) {
                String name = reader.readLine();
                String type = reader.readLine();
                String val = reader.readLine();
                
                Prop p = new Prop(name, type, val);
                variables[i] = p;
            }
            
            return variables;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {}
            }
        }
    }
}