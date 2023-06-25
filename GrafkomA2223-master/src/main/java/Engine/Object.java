package Engine;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Object extends ShaderProgram{

    List<Vector3f> vertices;
    List<Vector3f> curveVertices;
    int vao;
    int vbo;
    UniformsMap uniformsMap;
    Vector4f color;

    Matrix4f model;

    int vboColor;

    List<Object> childObject;
    List<Float> centerPoint;

    public List<Object> getChildObject() {
        return childObject;
    }

    public void setChildObject(List<Object> childObject) {
        this.childObject = childObject;
    }

    public List<Float> getCenterPoint() {
        updateCenterPoint();
        return centerPoint;
    }

    public void setCenterPoint(List<Float> centerPoint) {
        this.centerPoint = centerPoint;
    }

    List<Vector3f> verticesColor;
    public Object(List<ShaderModuleData> shaderModuleDataList
            , List<Vector3f> vertices
            , Vector4f color) {
        super(shaderModuleDataList);
        this.vertices = vertices;
        setupVAOVBO();
        uniformsMap = new UniformsMap(getProgramId());
        uniformsMap.createUniform(
                "uni_color");
        uniformsMap.createUniform(
                "model");
        uniformsMap.createUniform(
                "projection");
        uniformsMap.createUniform(
                "view");
        this.color = color;
        model = new Matrix4f().identity();
        childObject = new ArrayList<>();
        centerPoint = Arrays.asList(0f,0f,0f);
    }
    public Object(List<ShaderModuleData> shaderModuleDataList,
                  List<Vector3f> vertices,
                  List<Vector3f> verticesColor) {
        super(shaderModuleDataList);
        this.vertices = vertices;
        this.verticesColor = verticesColor;
        setupVAOVBOWithVerticesColor();
    }
    public void setupVAOVBO(){
        //set vao
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        //set vbo
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER,
                Utils.listoFloat(vertices),
                GL_STATIC_DRAW);
    }
    public void setupVAOVBOWithVerticesColor(){
        //set vao
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        //set vbo
        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER,
                Utils.listoFloat(vertices),
                GL_STATIC_DRAW);

        //set vboColor
        vboColor = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboColor);
        glBufferData(GL_ARRAY_BUFFER,
                Utils.listoFloat(verticesColor),
                GL_STATIC_DRAW);
    }
    public void drawSetup(Camera camera, Projection projection){
        bind();
        uniformsMap.setUniform(
                "uni_color", color);
        uniformsMap.setUniform(
                "model", model);
        uniformsMap.setUniform(
                "view", camera.getViewMatrix());
        uniformsMap.setUniform(
                "projection", projection.getProjMatrix());
        // Bind VBO
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3,
                GL_FLOAT,
                false,
                0, 0);

    }
    public void drawSetupWithVerticesColor(){
        bind();
        // Bind VBO
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3,
                GL_FLOAT,
                false,
                0, 0);

        // Bind VBOColor
        glEnableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, vboColor);
        glVertexAttribPointer(1, 3,
                GL_FLOAT,
                false,
                0, 0);
    }
    public void draw(Camera camera, Projection projection){
        drawSetup(camera, projection);
        // Draw the vertices
        //optional
        glLineWidth(10); //ketebalan garis
        glPointSize(10); //besar kecil vertex
        //wajib
        //GL_LINES
        //GL_LINE_STRIP
        //GL_LINE_LOOP
        //GL_TRIANGLES
        //GL_TRIANGLE_FAN
        //GL_POINT
        glDrawArrays(GL_POLYGON,
                0,
                vertices.size());
        for(Object child:childObject){
            child.draw(camera,projection);
        }
    }
    public void drawWithVerticesColor(){
        drawSetupWithVerticesColor();
        // Draw the vertices
        //optional
        glLineWidth(10); //ketebalan garis
        glPointSize(10); //besar kecil vertex
        //wajib
        //GL_LINES
        //GL_LINE_STRIP
        //GL_LINE_LOOP
        //GL_TRIANGLES
        //GL_TRIANGLE_FAN
        //GL_POINT
        glDrawArrays(GL_TRIANGLES,
                0,
                vertices.size());
    }
//    public void drawLine(){
//        drawSetup();
//        // Draw the vertices
//        //optional
//        glLineWidth(1); //ketebalan garis
//        glPointSize(1); //besar kecil vertex
//        glDrawArrays(GL_LINE_STRIP,
//                0,
//                vertices.size());
//    }
    public void addVertices(Vector3f newVertices){
        vertices.add(newVertices);
        setupVAOVBO();
    }
    public void translateObject(Float offsetX,Float offsetY,Float offsetZ){
        model = new Matrix4f().translate(offsetX,offsetY,offsetZ).mul(new Matrix4f(model));
        updateCenterPoint();
        for(Object child:childObject){
            child.translateObject(offsetX,offsetY,offsetZ);
        }
    }
    public void rotateObject(Float degree, Float x,Float y,Float z){
        model = new Matrix4f().rotate(degree,x,y,z).mul(new Matrix4f(model));
        updateCenterPoint();
        for(Object child:childObject){
            child.rotateObject(degree,x,y,z);
        }

    }
    public void updateCenterPoint(){
        Vector3f destTemp = new Vector3f();
        model.transformPosition(0.0f,0.0f,0.0f,destTemp);
        centerPoint.set(0,destTemp.x);
        centerPoint.set(1,destTemp.y);
        centerPoint.set(2,destTemp.z);
        System.out.println(centerPoint.get(0) + " " + centerPoint.get(1));
    }
    public void scaleObject(Float scaleX,Float scaleY,Float scaleZ){
        model = new Matrix4f().scale(scaleX,scaleY,scaleZ).mul(new Matrix4f(model));
        for(Object child:childObject){
            child.translateObject(scaleX,scaleY,scaleZ);
        }
    }
//    public void setupVAOVBOCurve(){
//        // set vao
//        vao = glGenVertexArrays();
//        glBindVertexArray(vao);
//        // set vbo
//        vbo = glGenBuffers();
//        glBindBuffer(GL_ARRAY_BUFFER, vbo);
//        // mengirim vertices
//        glBufferData(GL_ARRAY_BUFFER, Utils.listoFloat(curveVertices), GL_STATIC_DRAW);
//    }
//    public void drawCurve(){
//        if(vertices.size()<3) {
//            if(vertices.size()==2){
//                for (Vector3f vertice: vertices){
//                    curveVertices.add(vertice);
//                }
//            }
//            return;
//        }
//        setupVAOVBOCurve();
//        draw(camera, projection);
//        glLineWidth(10);
//        glPointSize(10);
//        glDrawArrays(GL_LINE_STRIP, 0, curveVertices.size());
//    }
//    public void updateCurve(List<Vector3f> points){
//        if(vertices.size() < 2) return;
//        curveVertices.clear();
//        curveVertices.add(vertices.get(0));
//        int size = vertices.size();
//        double interval = 0.02;
//        for (double i = 0; i <= 1; i += interval) {
////            double j = 1-i;
////            float tempx = 0, tempy = 0;
////            for(int p=0; p<=size-1; p+=1){
////                tempx += C(size-1,p) * Math.pow(i,p) * Math.pow(j,size-p-1) * vertices.get(p).x;
////                tempy += C(size-1,p) * Math.pow(i,p) * Math.pow(j,size-p-1) * vertices.get(p).y;
////            }
//            curveVertices.add(new Vector3f(calculateBezierPoint((float) i, points)));
//        }
//        curveVertices.add(vertices.get(vertices.size()-1));
//    }
//    public int combination(int n, int k){
//        int ans = factorial(n);
//        ans /= factorial(k);
//        ans /= factorial(n-k);
//        return ans;
//    }
//    public int factorial(int x){
//        int ans = 1;
//        for(int i=1; i<=x; i++) ans *= x;
//        return ans;
//    }
//    public static Vector3f calculateBezierPoint(float t, List<Vector3f> points) {
//        int n = points.size() - 1;
//        float x = 0, y = 0;
//
//        for (int i = 0; i <= n; i++) {
//            double coefficient = calculateCoefficient(n, i, t);
//            x += coefficient * points.get(i).x;
//            y += coefficient * points.get(i).y;
//        }
//
//        return new Vector3f(x, y, 0.0f);
//    }
//
//    private static double calculateCoefficient(int n, int i, double t) {
//        return binomialCoefficient(n, i) * Math.pow(t, i) * Math.pow(1 - t, n - i);
//    }
//
//    private static int binomialCoefficient(int n, int k) {
//        if (k == 0 || k == n) {
//            return 1;
//        } else {
//            return binomialCoefficient(n - 1, k - 1) + binomialCoefficient(n - 1, k);
//        }
//    }

}
