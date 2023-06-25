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
    (1920,1080,"Hello World");
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
        camera.setPosition(-10.0f,5.0f,0.0f);
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
                new Vector4f(1.0f,0.0f,0.0f,1.0f),
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

        //Mobil1
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,1.0f,1.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/mobil/mobil.obj",
                "mobil1"
        ));
        objects.get(2).translateObject(-10.0f,0.50f,-20.0f);


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
                "resources/jalan/city3.obj",
                "kota"
        ));

        objects.get(3).translateObject(0.0f,0.0f,0.0f);

// Human
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.0f,1.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Human/human.obj",
                "human1"
        ));
        objects.get(4).translateObject(-30.0f,0.80f,-25.0f);


    // Human 2
        objects.add(new Sphere(
            Arrays.asList(
            new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
            ),
            new ArrayList<>(),
            new Vector4f(0.0f,0.0f,1.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                        5.125f,
                        5.125f,
                        5.125f,
                        36,
                        18,
                        2,
                        "resources/Human/human2.obj",
                        "human2"
                        ));
        objects.get(5).translateObject(-28.0f,-0.10f,-25.0f);

        //Mobil2
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.0f,1.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/mobil/mobil.obj",
                "mobil2"
        ));
        objects.get(6).translateObject(-15.0f,0.50f,-20.0f);

        //  gedung 1
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung1.obj",
                "gedung"
        ));

        objects.get(7).translateObject(0.0f,0.0f,0.0f);
        //  gedung 2
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung2.obj",
                "gedung"
        ));

        objects.get(8).translateObject(0.0f,0.0f,0.0f);
        //  gedung 3
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.5f, 0.3f, 0.8f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung3.obj",
                "gedung"
        ));

        objects.get(9).translateObject(0.0f,0.0f,0.0f);

        //  gedung 4
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.5f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung4.obj",
                "gedung"
        ));

        objects.get(10).translateObject(0.0f,0.0f,0.0f);
        //  gedung 5
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung5.obj",
                "gedung"
        ));

        objects.get(11).translateObject(0.0f,0.0f,0.0f);

        // dataran atau tanah kota (baru kepikiran)
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.58f, 0.29f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/jalan/jalan.obj",
                "dataran kota"
        ));

        objects.get(11).translateObject(0.0f,0.0f,0.0f);
        //  gedung ke 6
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung6.obj",
                "gedung"
        ));
        //  gedung ke 7
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung7.obj",
                "gedung"
        ));
        //  gedung ke 8
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung8.obj",
                "gedung"
        ));
        //  gedung ke 9
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.5f, 0.3f, 0.8f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung9.obj",
                "gedung"
        ));
        //  gedung ke 10
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.5f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung10.obj",
                "gedung"
        ));
        //  gedung ke 11
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung11.obj",
                "gedung"
        ));
        //  gedung ke 12
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung12.obj",
                "gedung"
        ));
        //  gedung ke 13
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung13.obj",
                "gedung"
        ));
        //  gedung ke 14
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung14.obj",
                "gedung"
        ));
//  gedung ke 15
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.5f, 0.3f, 0.8f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung15.obj",
                "gedung"
        ));
        //  gedung ke 16
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.5f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung16.obj",
                "gedung"
        ));
        //  gedung ke 17
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung17.obj",
                "gedung"
        ));
        //  gedung ke 18
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung18.obj",
                "gedung"
        ));
        //  gedung ke 19
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung19.obj",
                "gedung"
        ));
        //  gedung ke 20
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung20.obj",
                "gedung"
        ));
        //  gedung ke 21
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.5f, 0.3f, 0.8f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung21.obj",
                "gedung"
        ));
        //  gedung ke 22
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung22.obj",
                "gedung"
        ));
        //  gedung ke 23
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung23.obj",
                "gedung"
        ));
        //  gedung ke 24
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung24.obj",
                "gedung"
        ));
        //  gedung ke 25
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung25.obj",
                "gedung"
        ));
        //  gedung ke 26
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung26.obj",
                "gedung"
        ));
        //  gedung ke 27
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.5f, 0.3f, 0.8f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung27.obj",
                "gedung"
        ));
        //  gedung ke 28
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung28.obj",
                "gedung"
        ));
        //  gedung ke 29
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung29.obj",
                "gedung"
        ));
        //  gedung ke 30
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung30.obj",
                "gedung"
        ));
        //  gedung ke 31
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung31.obj",
                "gedung"
        ));
        //  gedung ke 32
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung32.obj",
                "gedung"
        ));
        //  gedung ke 33
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.5f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung33.obj",
                "gedung"
        ));
        //  gedung ke 34
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.5f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung34.obj",
                "gedung"
        ));
        //  gedung ke 35
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 0.5f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung35.obj",
                "gedung"
        ));
        //  gedung ke 36
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.5f, 0.3f, 0.8f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung36.obj",
                "gedung"
        ));
        //  gedung ke 37
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.75f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung37.obj",
                "gedung"
        ));
        //  gedung ke 38
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                5.125f,
                5.125f,
                5.125f,
                36,
                18,
                2,
                "resources/Kumpulan gedung/gedung38.obj",
                "gedung"
        ));

}

    public void input(){
        float move = 0.1f;
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
            System.out.println(camera.getPosition().get(0)+" "+camera.getPosition().get(1)+" "+camera.getPosition().get(2));
        }
        if (window.isKeyPressed(GLFW_KEY_S)) {
            camera.moveBackwards(move);
            System.out.println(camera.getPosition().get(0)+" "+camera.getPosition().get(1)+" "+camera.getPosition().get(2));
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            camera.moveLeft(move);
            System.out.println(camera.getPosition().get(0)+" "+camera.getPosition().get(1)+" "+camera.getPosition().get(2));
        }
        if (window.isKeyPressed(GLFW_KEY_D)) {
            camera.moveRight(move);
            System.out.println(camera.getPosition().get(0)+" "+camera.getPosition().get(1)+" "+camera.getPosition().get(2));
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
                mobil = 30f;
            }
            if (mobil > 24.31){
                objects.get(2).translateObject(0.f,0.0f,-0.1f);
            }
            if(mobil >= 23.31 && mobil < 24.31){
              float tempX = objects.get(2).getModel().get(3,0);
              float tempY = objects.get(2).getModel().get(3,1);
              float tempZ = objects.get(2).getModel().get(3,2);
              objects.get(2).translateObject(-tempX,-tempY,-tempZ);
              objects.get(2).rotateObject(0.015f,0f,1f,0f);
              objects.get(2).translateObject(tempX,tempY,tempZ);
            }
            if(mobil > 19.31 && mobil < 23.30){
                float tempX = objects.get(2).getModel().get(3,0);
                float tempY = objects.get(2).getModel().get(3,1);
                float tempZ = objects.get(2).getModel().get(3,2);
                objects.get(2).translateObject(-tempX,-tempY,-tempZ);
                objects.get(2).translateObject(-0.1f,0.0f,0.0f);
                objects.get(2).translateObject(tempX,tempY,tempZ);
            }
            if(mobil >= 18.31 && mobil < 19.31) {
                float tempX = objects.get(2).getModel().get(3, 0);
                float tempY = objects.get(2).getModel().get(3, 1);
                float tempZ = objects.get(2).getModel().get(3, 2);
                objects.get(2).translateObject(-tempX, -tempY, -tempZ);
                objects.get(2).rotateObject(0.015f, 0f, 1f, 0f);
                objects.get(2).translateObject(tempX, tempY, tempZ);
            }
            if(mobil > 15.31 && mobil < 18.30){
                float tempX = objects.get(2).getModel().get(3,0);
                float tempY = objects.get(2).getModel().get(3,1);
                float tempZ = objects.get(2).getModel().get(3,2);
                objects.get(2).translateObject(-tempX,-tempY,-tempZ);
                objects.get(2).translateObject(-0.1f,0.0f,0.0f);
                objects.get(2).translateObject(tempX,tempY,tempZ);
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