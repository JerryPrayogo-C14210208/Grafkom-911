package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;

public class Sphere extends Circle{
    float radiusZ;
    int stackCount;
    int sectorCount;

    public Sphere(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Float> centerPoint, Float radiusX, Float radiusY, Float radiusZ,
                  int sectorCount,int stackCount, int option) {
        super(shaderModuleDataList, vertices, color, centerPoint, radiusX, radiusY);
        this.radiusZ = radiusZ;
        this.stackCount = stackCount;
        this.sectorCount = sectorCount;
        if (option==1){
            createSphere(); //elipsoid
        } else if (option == 2) {
            createEllipticCone();//elliptic
        } else if (option == 3) {
            createBox(); // kotak
        } else if (option == 4) {
            createLimas(); // limas yang gaada curve
        } else if (option == 5) {
            createCylinder(); // cylinder
        } else if (option == 6) {
            body(); // limas curve
        } else if (option == 7) {
            createEllipsoid();  // elipsoid
        } else if (option == 8) {
            createparuh(); // limas curve beda codingan
        }
        setupVAOVBO();
    }
//

    public void createBox(){
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();
        //TITIK 1
//      kiri atas belakang
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 2
//      kanan atas belakang
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 3
//       kanan bawah belakang
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 4
//      kiri bawah belakang
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
//      kiri atas depan
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 6
//      kanan atas depan
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 7
//      kanan bawah depan
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 8
//      kiri bawah depan
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();

        vertices.clear();
        //kotak yg sisi belakang
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        //kotak yg sisi depan
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(7));
        //kotak yg sisi kiri
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(3));
        //kotak yg sisi kanan
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(2));
        //kotak yg sisi atas
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(4));
        //kotak yg sisi bawah
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(6));
    }
    //    public void draw(){
//        drawSetup();
//        glLineWidth(1); //ketebalan garis
//        glPointSize(1); //besar kecil vertex
//        glDrawArrays(GL_LINE_STRIP,
//                0,
//                vertices.size());
//        for(Object child :childObject){
//            child.draw();
//        }
//    }
    public void createSphere(){
        float pi = (float)Math.PI;

        float sectorAngle, StackAngle, x, y, z;
        float sectorStep = 2 * (float)Math.PI / sectorCount;
        float stackStep = (float)Math.PI / stackCount;


        for (int i = 0; i <= stackCount; ++i)
        {
            StackAngle = pi / 2 - i * stackStep;
            x = radiusX * (float)Math.cos(StackAngle);
            y = radiusY * (float)Math.cos(StackAngle);
            z = radiusZ * (float)Math.sin(StackAngle);

            for (int j = 0; j <= sectorCount; ++j)
            {
                sectorAngle = j * sectorStep;
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = centerPoint.get(0) + x * (float)Math.cos(sectorAngle);
                temp_vector.y = centerPoint.get(1) + y * (float)Math.sin(sectorAngle);
                temp_vector.z = centerPoint.get(2) + z;
                vertices.add(temp_vector);
            }
        }
//        this.index = index;
//        ibo = glGenBuffers();
//        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
//        glBufferData(GL_ELEMENT_ARRAY_BUFFER,
//                Utils.listoInt(index), GL_STATIC_DRAW);
    }

    public void createHiperboloid1side(){

        vertices.clear();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/60){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = 0.5f * (float)(1/Math.cos(v) * Math.cos(u));
                float y = 0.5f * (float)(1/Math.cos(v) * Math.sin(u));
                float z = 0.5f * (float)(Math.tan(v));
                vertices.add(new Vector3f(x,y,z));
            }
        }
    }
    public void createHiperboloid2side(){
        vertices.clear();

        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/180){
            for(double u = -Math.PI/2; u<= Math.PI/2; u+=Math.PI/180){
                float x = -0.5f * (float)(Math.tan(v) * Math.cos(u));
                float y = -0.5f * (float)(1/Math.cos(v));
                float z = -0.5f * (float)(Math.tan(v) * Math.sin(u));
                vertices.add(new Vector3f(x,y,z));
            }
        }
        for(double v = -Math.PI/2; v<= Math.PI/2; v+=Math.PI/180){
            for(double u = Math.PI/2; u<= 3*Math.PI/2; u+=Math.PI/180){
                float x = 0.5f * (float)(Math.tan(v) * Math.cos(u));
                float y = 0.5f * (float)(1/Math.cos(v));
                float z = 0.5f * (float)(Math.tan(v) * Math.sin(u));
                vertices.add(new Vector3f(x,y,z));
            }
        }
    }
    public void createEllipticCone(){

        ArrayList<Vector3f> temp = new ArrayList<>();

        for(double v = 0; v<= Math.PI*2; v+=Math.PI/360){
            for(double u = -Math.PI; u<= Math.PI; u+=Math.PI/60){
                float x = radiusX * (float)(v * Math.cos(u));
                float y = radiusY * (float)(v * Math.sin(u));
                float z = radiusZ * (float) v;
                vertices.add(new Vector3f(x,y,z));
            }
        }
    }
    public void createCylinder(){
        vertices.clear();
        float segmentAngle = (float) (2 * Math.PI / stackCount);

        for (int i = 0; i < stackCount; i++) {
            float x = (float) (radiusX * Math.cos(i * segmentAngle));
            float z = (float) (radiusZ * Math.sin(i * segmentAngle));
            for (int j = 0; j <= stackCount; j++) {
                float y = (float) (radiusY * Math.sin(j * segmentAngle));
                vertices.add(new Vector3f(x, y, z));
            }
        }

    }
    public void createLimas() {
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();
        // TITIK 1
        temp.x = centerPoint.get(0);
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusY / 2.0f;;
        tempVertices.add(temp);
        temp = new Vector3f();
        // TITIK 2
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1);
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        // TITIK 3
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1);
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        // TITIK 4
        temp.x = centerPoint.get(0) ;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) ;
        tempVertices.add(temp);
        temp = new Vector3f();

        vertices.clear();
        // Segitiga miring depan
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(2));
        // Segitiga miring kanan
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        // Segitiga miring kiri
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(1));
        // Segitiga alas
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(2));


    }
        public void body(){
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();

        //TITIK 1
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 2
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 3
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 4
        temp.x = centerPoint.get(0) / radiusX;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 5
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 6
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 7
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 8
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 9
        temp.x = centerPoint.get(0) / radiusX;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 10
        temp.x = tempVertices.get(7).get(0) /2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = tempVertices.get(7).get(2)/2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        System.out.println(tempVertices.get(7).get(0)+","+tempVertices.get(7).get(1)+","+tempVertices.get(7).get(2));
        System.out.println(tempVertices.get(3).get(0)+","+tempVertices.get(3).get(1)+","+tempVertices.get(3).get(2));

        vertices.clear();
        float firstX = tempVertices.get(8).get(0);
        float firstY = tempVertices.get(8).get(1);
        float firstZ = tempVertices.get(8).get(2);
        float secondX = tempVertices.get(8).get(0) + tempVertices.get(7).get(0);
        float secondY = 0;
        float secondZ = tempVertices.get(7).get(2);
        float thirdX = tempVertices.get(7).get(0);
        float thirdY = tempVertices.get(7).get(1);
        float thirdZ = tempVertices.get(7).get(2);

        double newX, newY, newZ;
        for(double i = 0; i <=1; i+= 0.01)
        {
            newX = (Math.pow((1-i), 2) * firstX) + (2 * (1-i) * i * secondX) + (Math.pow(i, 2) * thirdX);
            newY = (Math.pow((1-i), 2) * firstY) + (2 * (1-i) * i * secondY) + (Math.pow(i, 2) * thirdY);
            newZ = (Math.pow((1-i), 2) * firstZ) + (2 * (1-i) * i * secondZ) + (Math.pow(i, 2) * thirdZ);
            vertices.add(new Vector3f((float)newX, (float)newY, (float)newZ));
        }
        firstX = tempVertices.get(6).get(0);
        firstY = tempVertices.get(6).get(1);
        secondX = tempVertices.get(6).get(0) - tempVertices.get(8).get(0);
        secondY = 0;
        thirdX = tempVertices.get(8).get(0);
        thirdY = tempVertices.get(8).get(1);
        for(double i = 0; i <=1; i+= 0.01)
        {
            newX = (Math.pow((1-i), 2) * firstX) + (2 * (1-i) * i * secondX) + (Math.pow(i, 2) * thirdX);
            newY = (Math.pow((1-i), 2) * firstY) + (2 * (1-i) * i * secondY) + (Math.pow(i, 2) * thirdY);
            newZ = (Math.pow((1-i), 2) * firstZ) + (2 * (1-i) * i * secondZ) + (Math.pow(i, 2) * thirdZ);
            vertices.add(new Vector3f((float)newX, (float)newY, tempVertices.get(6).get(2)));
        }

        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(3));
    }

    public void createparuh(){
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();

        //TITIK 1
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 2
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 3
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 4
        temp.x = centerPoint.get(0) / radiusX;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) ;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 5
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 6
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 7
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 8
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 9
        temp.x = centerPoint.get(0) / radiusX;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 10
        temp.x = tempVertices.get(7).get(0) /2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = tempVertices.get(7).get(2)/2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        System.out.println(tempVertices.get(7).get(0)+","+tempVertices.get(7).get(1)+","+tempVertices.get(7).get(2));
        System.out.println(tempVertices.get(3).get(0)+","+tempVertices.get(3).get(1)+","+tempVertices.get(3).get(2));

        vertices.clear();
        float firstX = tempVertices.get(8).get(0);
        float firstY = tempVertices.get(8).get(1);
        float firstZ = tempVertices.get(8).get(2);
        float secondX = tempVertices.get(8).get(0) + tempVertices.get(7).get(0);
        float secondY = 0;
        float secondZ = tempVertices.get(7).get(2);
        float thirdX = tempVertices.get(7).get(0);
        float thirdY = tempVertices.get(7).get(1);
        float thirdZ = tempVertices.get(7).get(2);

        double newX, newY, newZ;
        for(double i = 0; i <=1; i+= 0.01)
        {
            newX = (Math.pow((1-i), 2) * firstX) + (2 * (1-i) * i * secondX) + (Math.pow(i, 2) * thirdX);
            newY = (Math.pow((1-i), 2) * firstY) + (2 * (1-i) * i * secondY) + (Math.pow(i, 2) * thirdY);
            newZ = (Math.pow((1-i), 2) * firstZ) + (2 * (1-i) * i * secondZ) + (Math.pow(i, 2) * thirdZ);
            vertices.add(new Vector3f((float)newX, (float)newY, (float)newZ));
        }
        firstX = tempVertices.get(6).get(0);
        firstY = tempVertices.get(6).get(1);
        secondX = tempVertices.get(6).get(0) - tempVertices.get(8).get(0);
        secondY = 0;
        thirdX = tempVertices.get(8).get(0);
        thirdY = tempVertices.get(8).get(1);
        for(double i = 0; i <=1; i+= 0.01)
        {
            newX = (Math.pow((1-i), 2) * firstX) + (2 * (1-i) * i * secondX) + (Math.pow(i, 2) * thirdX);
            newY = (Math.pow((1-i), 2) * firstY) + (2 * (1-i) * i * secondY) + (Math.pow(i, 2) * thirdY);
            newZ = (Math.pow((1-i), 2) * firstZ) + (2 * (1-i) * i * secondZ) + (Math.pow(i, 2) * thirdZ);
            vertices.add(new Vector3f((float)newX, (float)newY, tempVertices.get(6).get(2)));
        }

        firstX = tempVertices.get(8).get(0);
        firstY = tempVertices.get(8).get(1);
        firstZ = tempVertices.get(8).get(2);
        secondX = tempVertices.get(8).get(0) + tempVertices.get(7).get(0);
        secondY = 0;
        secondZ = tempVertices.get(7).get(2);
        thirdX = tempVertices.get(7).get(0);
        thirdY = tempVertices.get(7).get(1);
        thirdZ = tempVertices.get(7).get(2);

        for(double i = 0; i <=1; i+= 0.01)
        {
            newX = (Math.pow((1-i), 2) * firstX) + (2 * (1-i) * i * secondX) + (Math.pow(i, 2) * thirdX);
            newY = (Math.pow((1-i), 2) * firstY) + (2 * (1-i) * i * secondY) + (Math.pow(i, 2) * thirdY);
            newZ = (Math.pow((1-i), 2) * firstZ) + (2 * (1-i) * i * secondZ) + (Math.pow(i, 2) * thirdZ);
            vertices.add(new Vector3f((float)newX, (float)newY, (float)newZ));
            vertices.add(tempVertices.get(3));
        }

        firstX = tempVertices.get(6).get(0);
        firstY = tempVertices.get(6).get(1);
        secondX = tempVertices.get(6).get(0) - tempVertices.get(8).get(0);
        secondY = 0;
        thirdX = tempVertices.get(8).get(0);
        thirdY = tempVertices.get(8).get(1);
        for(double i = 0; i <=1; i+= 0.01)
        {
            newX = (Math.pow((1-i), 2) * firstX) + (2 * (1-i) * i * secondX) + (Math.pow(i, 2) * thirdX);
            newY = (Math.pow((1-i), 2) * firstY) + (2 * (1-i) * i * secondY) + (Math.pow(i, 2) * thirdY);
            newZ = (Math.pow((1-i), 2) * firstZ) + (2 * (1-i) * i * secondZ) + (Math.pow(i, 2) * thirdZ);
            vertices.add(new Vector3f((float)newX, (float)newY, tempVertices.get(6).get(2)));
            vertices.add(tempVertices.get(3));
        }

        firstX = tempVertices.get(7).get(0);
        firstY = tempVertices.get(7).get(1);
        secondX =0;
        secondY = 0;
        thirdX = tempVertices.get(6).get(0);
        thirdY = tempVertices.get(6).get(1);
        for(double i = 0; i <=1; i+= 0.0001)
        {
            newX = (Math.pow((1-i), 2) * firstX) + (2 * (1-i) * i * secondX) + (Math.pow(i, 2) * thirdX);
            newY = (Math.pow((1-i), 2) * firstY) + (2 * (1-i) * i * secondY) + (Math.pow(i, 2) * thirdY);
            newZ = (Math.pow((1-i), 2) * firstZ) + (2 * (1-i) * i * secondZ) + (Math.pow(i, 2) * thirdZ);
            vertices.add(new Vector3f((float)newX, (float)newY, tempVertices.get(6).get(2)));
            vertices.add(tempVertices.get(3));
        }





    }
    // setengah lingkaran
    public void createEllipsoid(){

        vertices.clear();

        for (double t = -Math.PI/2; t <= Math.PI/2; t += Math.PI/180) {
            float x = (float)(22/7 * Math.cos(t));
            float y = (float)(22/7 * Math.sin(t));
            float z = 0.0f;
            vertices.add(new Vector3f(x, y, z));
        }

    }

}
