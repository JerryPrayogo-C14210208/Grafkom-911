import Engine.*;
import Engine.Object;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class Main {
    private Window window =
            new Window
    (800,800,"Hello World");
    private ArrayList<Object> objects
            = new ArrayList<>();
    private ArrayList<Object> objectsRectangle
            = new ArrayList<>();

    private ArrayList<Object> objectsPointsControl
            = new ArrayList<>();

    int modeToggle = 0;
    float mobil = 0f;
    private MouseInput mouseInput;
    int countDegree = 0;
    Projection projection = new Projection(window.getWidth(),window.getHeight());
    Camera camera = new Camera();
    public void init(){
        window.init();
        GL.createCapabilities();
        mouseInput = window.getMouseInput();
        camera.setPosition(-10.0f,100.0f,0.0f);
        camera.setRotation((float)Math.toRadians(0.0f),(float)Math.toRadians(0.0f));
        //code
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,-1.0f,0.0f,-1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0f,
                0f,
                0f,
                36,
                18,
                1
        ));
        //Pesawat
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f,1.0f,1.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "src/main/java/Engine/A380.obj",
                "pesawat"
        ));

        objects.get(1).translateObject(-120.0f,90.0f,-150.0f);

        //Mobil
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f,1.0f,1.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/mobil/mobil.obj",
                "mobil"
        ));
        objects.get(2).translateObject(-10.0f,1.00f,-20.0f);

        //  jalan/kota
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f,1.0f,1.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/jalan/city.obj",
                "kota"
        ));

        objects.get(3).translateObject(0.0f,0.0f,0.0f);



    }

    public void input(){
        float move = 1f;
        if (window.isKeyPressed(GLFW_KEY_UP)) {
            objects.get(1).translateObject(0f, 0f, -0.1f);
//            System.out.println(objects.get(1).getCenterPoint());
        }
        if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            objects.get(1).translateObject(0f, 0f, 0.1f);
        }
        if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            objects.get(1).translateObject(-0.1f, 0f, 0f);
        }
        if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            objects.get(1).translateObject(0.1f, 0f, 0.0f);
        }
        if(window.isKeyPressed(GLFW_KEY_LEFT_CONTROL)){
            objects.get(1).translateObject(0f, -0.1f, 0.0f);
        }
        if(window.isKeyPressed(GLFW_KEY_SPACE)){
            objects.get(1).translateObject(0.0f, 0.1f, 0.0f);
        }
        if (window.isKeyPressed(GLFW_KEY_W)) {
            camera.moveForward(move);
        }
        if (window.isKeyPressed(GLFW_KEY_S)) {
            camera.moveBackwards(move);
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            camera.moveLeft(move);
        }
        if (window.isKeyPressed(GLFW_KEY_D)) {
            camera.moveRight(move);
        }
        if(mouseInput.isLeftButtonPressed()){
            Vector2f displayVec = window.getMouseInput().getDisplVec();
            camera.addRotation((float)Math.toRadians(displayVec.x * 0.1f),
                    (float)Math.toRadians(displayVec.y * 0.1f));
        }
        if(window.getMouseInput().getScroll().y != 0){
            projection.setFOV(projection.getFOV()- (window.getMouseInput().getScroll().y*0.01f));
            window.getMouseInput().setScroll(new Vector2f());
        }
        if(window.isKeyPressed(GLFW_KEY_Z)   && mobil<=0 || mobil>0){
            if(mobil<=0){
                mobil = 25f;
            }
            if (mobil > 19.01){
                objects.get(2).translateObject(0.f,0.0f,-0.05f);
            }
            else if (mobil <= 19.01){
                objects.get(2).translateObject(0.0f,0.0f,-0.05f);

            }
            else if(mobil <= 6.01){
              float tempX = objects.get(2).getModel().get(3,0);
              float tempY = objects.get(2).getModel().get(3,1);
              float tempZ = objects.get(2).getModel().get(3,2);
              objects.get(2).translateObject(-tempX,0.0f,-0.05f);
              objects.get(2).rotateObject(90f,0f,1f,0f);
              objects.get(2).translateObject(tempX,0.0f,-0.05f);
            }
            mobil-=0.01;
        }

    }
    public void loop(){
        while (window.isOpen()) {
            window.update();
            glClearColor(0.0f,
                    0.0f, 0.0f,
                    0.0f);
            GL.createCapabilities();
            input();


            //code
            for(Object object: objects){
                object.draw(camera,projection);
            }

//            for(Object object: objectsRectangle){
//                object.draw();
//            }
//            for(Object object: objectsPointsControl){
//                object.drawLine();
//            }

            // Restore state
            glDisableVertexAttribArray(0);

            // Poll for window events.
            // The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
    public void run() {

        init();
        loop();

        // Terminate GLFW and
        // free the error callbacks
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
    public static void main(String[] args) {
        new Main().run();
    }
}