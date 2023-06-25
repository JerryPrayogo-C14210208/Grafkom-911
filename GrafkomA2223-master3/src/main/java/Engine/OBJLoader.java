package Engine;

import Engine.*;
import Engine.Object;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import javax.management.modelmbean.ModelMBean;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class OBJLoader {
    public static Model loadModel(File f) throws FileNotFoundException, IOException{
        BufferedReader reader = new BufferedReader(new FileReader(f));
        Model m = new Model();
        String line;
        while((line = reader.readLine()) != null){
            if(line.startsWith("v ")) {
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                float z = Float.valueOf(line.split(" ")[3]);
                m.vertices.add(new Vector3f(x, y, z));
            } else if(line.startsWith("vn ")){
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                float z = Float.valueOf(line.split(" ")[3]);
                m.normals.add(new Vector3f(x, y, z));
            } else if(line.startsWith("vt ")){
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
//                float z = Float.valueOf(line.split(" ")[3]);
                m.texture.add(new Vector2f(x, y));
            }else if(line.startsWith("f ")) {
                Vector3f vertexIndices = new Vector3f(
                        Integer.parseInt(line.split(" ")[1].split("/")[0]),
                        Integer.parseInt(line.split(" ")[2].split("/")[0]),
                        Integer.parseInt(line.split(" ")[3].split("/")[0]));
                Vector3f normalIndices = new Vector3f(
                        Integer.parseInt(line.split(" ")[1].split("/")[2]),
                        Integer.parseInt(line.split(" ")[2].split("/")[2]),
                        Integer.parseInt(line.split(" ")[3].split("/")[2]));
                m.faces.add(new Face(vertexIndices, normalIndices));
            }
        }
        System.out.println("normals");
        System.out.println(m.normals);
        System.out.println("vertex");
        System.out.println(m.vertices);
        System.out.println("Faces");
        for(Face face : m.faces){
            System.out.print(face.vertex);
        }
        reader.close();
        return m;
    }
}
