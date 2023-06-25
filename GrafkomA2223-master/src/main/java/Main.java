import Engine.*;
import Engine.Object;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private MouseInput mouseInput;
    int countDegree = 0;
    Projection projection = new Projection(window.getWidth(),window.getHeight());
    Camera camera = new Camera();
    public void init(){
        window.init();
        GL.createCapabilities();
        mouseInput = window.getMouseInput();
        camera.setPosition(0,0,2.0f);
        camera.setRotation((float)Math.toRadians(0.0f),(float)Math.toRadians(0.0f));
        //code
//        objects.add(new Object2d(
//            Arrays.asList(
//                //shaderFile lokasi menyesuaikan objectnya
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/scene.vert"
//                , GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/scene.frag"
//                , GL_FRAGMENT_SHADER)
//            ),
//            new ArrayList<>(
//                List.of(
//                    new Vector3f(0.0f,0.5f,0.0f),
//                    new Vector3f(-0.5f,-0.5f,0.0f),
//                    new Vector3f(0.5f,-0.5f,0.0f)
//                )
//            ),
//            new Vector4f(0.0f,1.0f,1.0f,1.0f)
//        ));
//        objects.add(new Object(
//            Arrays.asList(
//                //shaderFile lokasi menyesuaikan objectnya
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/" +
//                    "sceneWithVerticesColor.vert"
//                        , GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData
//                    ("resources/shaders/" +
//                    "sceneWithVerticesColor.frag"
//                            , GL_FRAGMENT_SHADER)
//        ),
//        new ArrayList<>(
//                List.of(
//                    new Vector3f(0.0f,0.5f,0.0f),
//                    new Vector3f(-0.5f,-0.5f,0.0f),
//                    new Vector3f(0.5f,-0.5f,0.0f)
//                )
//            ),
//        new ArrayList<>(
//            List.of(
//                new Vector3f(1.0f,0.0f,0.0f),
//                new Vector3f(0.0f,1.0f,0.0f),
//                new Vector3f(0.0f,0.0f,1.0f)
//            )
//        )
//        ));
//        objectsRectangle.add(new Rectangle(
//            Arrays.asList(
//                //shaderFile lokasi menyesuaikan objectnya
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/scene.vert"
//                , GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/scene.frag"
//                , GL_FRAGMENT_SHADER)
//            ),
//            new ArrayList<>(
//                List.of(
//                    new Vector3f(0.0f,0.0f,0.0f),
//                    new Vector3f(0.5f,0.0f,0.0f),
//                    new Vector3f(0.0f,0.5f,0.0f),
//                    new Vector3f( 0.5f,0.5f,0.0f)
//                )
//            ),
//            new Vector4f(0.0f,1.0f,1.0f,1.0f),
//            Arrays.asList(0,1,2,1,2,3)
//
//        ));
//        objectsPointsControl.add(new Object(
//            Arrays.asList(
//                //shaderFile lokasi menyesuaikan objectnya
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/scene.vert"
//                , GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData
//                ("resources/shaders/scene.frag"
//                , GL_FRAGMENT_SHADER)
//            ),
//            new ArrayList<>(),
//            new Vector4f(0.0f,1.0f,1.0f,1.0f)
//        ));
//  KEPALA
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.8f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.133f,
                0.125f,
                0.125f,
                36,
                500,
                1
        ));
        objects.get(0).translateObject(0.0f,0.0f,0.0f);
        objects.get(0).scaleObject(5f,5f,5f);
// MATA KIRI
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1f,1.0f,1.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.100f,
                0.100f,
                0.125f,
                36,
                500,
                1
        ));
        objects.get(0).getChildObject().get(0).translateObject(-0.3f,0.0f,0.90f);
        objects.get(0).getChildObject().get(0).scaleObject(0.9f,0.9f,0.63f);
        objects.get(0).getChildObject().get(0).setCenterPoint(Arrays.asList(0.0f,0.0f,0.0f));
//MATA KANAN
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f,1.0f,1.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.100f,
                0.100f,
                0.125f,
                36,
                500,
                1
        ));
        objects.get(0).getChildObject().get(1).translateObject(0.4f,0.0f,0.80f);
        objects.get(0).getChildObject().get(1).scaleObject(0.9f,0.9f,0.63f);

////BOLA MATA KIRI
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.0f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.065f,
                0.065f,
                0.025f,
                36,
                500,
                1
        ));
        objects.get(0).getChildObject().get(2).translateObject(-0.71f,0.0f,2.15f);
        objects.get(0).getChildObject().get(2).scaleObject(0.38f,0.38f,0.3f);


//BOLA MATA KANAN
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.0f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.065f,
                0.065f,
                0.025f,
                36,
                500,
                1
        ));
        objects.get(0).getChildObject().get(3).translateObject(0.9f,0.0f,1.95f);
        objects.get(0).getChildObject().get(3).scaleObject(0.38f,0.38f,0.3f);

//ALIS KIRI
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.5f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.125f,
                0.005f,
                0.125f,
                36,
                500,
                3
        ));
        objects.get(0).getChildObject().get(4).translateObject(-0.33f,0.55f,-0.50f);
        objects.get(0).getChildObject().get(4).scaleObject(1.0f,0.6f,-1f);
        objects.get(0).getChildObject().get(4).rotateObject((float) Math.toRadians(20f),0f,0f,0.4f);
//
//ALIS KANAN
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.5f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.125f,
                0.005f,
                0.125f,
                36,
                500,
                3
        ));
        objects.get(0).getChildObject().get(5).translateObject(0.30f,0.5f,-0.5f);
        objects.get(0).getChildObject().get(5).scaleObject(1f,0.6f,-1f);
        objects.get(0).getChildObject().get(5).rotateObject((float) Math.toRadians(20f),-0.2f,0.3f,-0.4f);

//BELAKANG RAMBUT
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.0f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.125f,
                0.125f,
                0.125f,
                36,
                500,
                3
        ));
        objects.get(0).getChildObject().get(6).translateObject(0f,0.0f,0.688f);
        objects.get(0).getChildObject().get(6).scaleObject(2f,0.7f,-1f);
        objects.get(0).getChildObject().get(6).rotateObject((float) Math.toRadians(20f),1f,0f,0.f);

        // Hidung
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,1.0f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.125f,
                0.125f,
                0.185f,
                36,
                500,
                1
        ));
        objects.get(0).getChildObject().get(7).translateObject(0.f,-0.0f,1.0f);
        objects.get(0).getChildObject().get(7).scaleObject(1.2f,0.9f,0.63f);
        objects.get(0).getChildObject().get(7).rotateObject((float) Math.toRadians(20f),0.0f,0.0f,-0.4f);
    //bola hidung 1
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(-0.4f,0.0f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.02f,
                0.04f,
                0.01f,
                36,
                500,
                1
        ));
        objects.get(0).getChildObject().get(8).translateObject(0.05f,-0.0f,1.12f);
        objects.get(0).getChildObject().get(8).scaleObject(1.2f,0.9f,0.63f);
        //bola hidung 2
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(-0.4f,0.0f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.02f,
                0.04f,
                0.01f,
                36,
                500,
                1
        ));
        objects.get(0).getChildObject().get(9).translateObject(-0.05f,-0.0f,1.12f);
        objects.get(0).getChildObject().get(9).scaleObject(1.2f,0.9f,0.63f);

        // telinga kiri
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.9f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.175f,
                0.175f,
                0.175f,
                36,
                500,
                1
        ));
        objects.get(0).getChildObject().get(10).translateObject(-0.8f,1.3f,1f);
        objects.get(0).getChildObject().get(10).scaleObject(0.38f,0.38f,0.3f);
        // telinga kanan
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.9f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.175f,
                0.175f,
                0.175f,
                36,
                500,
                1
        ));
        objects.get(0).getChildObject().get(11).translateObject(0.2f,1.5f,1f);
        objects.get(0).getChildObject().get(11).scaleObject(0.38f,0.38f,0.3f);
        // telinga kiri hitam dalam
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.0f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.090f,
                0.090f,
                0.090f,
                36,
                500,
                1
        ));
        objects.get(0).getChildObject().get(12).translateObject(-0.77f,1.275f,1.13f);
        objects.get(0).getChildObject().get(12).scaleObject(0.38f,0.38f,0.3f);
        //Telinga kanan hitam dalam
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.0f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.090f,
                0.090f,
                0.090f,
                36,
                500,
                1
        ));
        objects.get(0).getChildObject().get(13).translateObject(0.2f,1.46f,1.12f);
        objects.get(0).getChildObject().get(13).scaleObject(0.38f,0.38f,0.3f);
        // mulut
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.9f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.105f,
                0.105f,
                0.105f,
                36,
                500,
                1
        ));
        objects.get(0).getChildObject().get(14).translateObject(0.f,-0.06f,0.94f);
        objects.get(0).getChildObject().get(14).scaleObject(1.2f,0.9f,0.63f);


        //mulut 2
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.5f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.075f,
                0.075f,
                0.075f,
                36,
                500,
                1
        ));
        objects.get(0).getChildObject().get(15).translateObject(0.f,-0.08f,0.99f);
        objects.get(0).getChildObject().get(15).scaleObject(1.2f,0.8f,0.63f);




        // CURVE Helm
        objects.get(0).getChildObject().add(new Sphere(
               Arrays.asList(
                       new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
               ),
                new ArrayList<>(),
                new Vector4f(0.663f, 0.596f, 0.494f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
               0.125f,
                0.225f,
                0.125f,
                36,
               500,
               6
       ));
        objects.get(0).getChildObject().get(16).translateObject(0.0f,0.2f,1.0f);
       objects.get(0).getChildObject().get(16).scaleObject(8.0f,2.7f,-0f);
       objects.get(0).getChildObject().get(16).rotateObject((float) Math.toRadians(20f),1f,0f,0.f);

       // shield 1
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.663f, 0.596f, 0.494f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.1542f,
                0.165f,
                0.125f,
                36,
                500,
                3
        ));
        objects.get(0).getChildObject().get(17).translateObject(-0.15f,0.01f,-8.0f);
        objects.get(0).getChildObject().get(17).scaleObject(4.0f,4f,0.08f);
        objects.get(0).getChildObject().get(17).rotateObject((float) Math.toRadians(180f),1.0f,0.0f,0.2f);

        // shield 2
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.663f, 0.596f, 0.494f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.155f,
                0.225f,
                0.125f,
                36,
                500,
                4
        ));
        objects.get(0).getChildObject().get(18).translateObject(-0.15f,0.09f,-8.0f);
        objects.get(0).getChildObject().get(18).scaleObject(4.0f,4f,0.08f);
        objects.get(0).getChildObject().get(18).rotateObject((float) Math.toRadians(180f),1.0f,0.0f,0.2f);

        //shield 3
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.545f, 0.271f, 0.075f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.1442f,
                0.155f,
                0.125f,
                36,
                500,
                3
        ));
        objects.get(0).getChildObject().get(19).translateObject(-0.15f,0.01f,-8.01f);
        objects.get(0).getChildObject().get(19).scaleObject(4.0f,4f,0.08f);
        objects.get(0).getChildObject().get(19).rotateObject((float) Math.toRadians(180f),1.0f,0.0f,0.2f);

        //shield 4
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.545f, 0.271f, 0.075f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.140f,
                0.210f,
                0.125f,
                36,
                500,
                4
        ));
        objects.get(0).getChildObject().get(20).translateObject(-0.15f,0.09f,-8.01f);
        objects.get(0).getChildObject().get(20).scaleObject(4.0f,4f,0.08f);
        objects.get(0).getChildObject().get(20).rotateObject((float) Math.toRadians(180f),1.0f,0.0f,0.2f);

        // logo tameng
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.663f, 0.596f, 0.494f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.140f,
                0.210f,
                0.125f,
                36,
                500,
                8
        ));
        objects.get(0).getChildObject().get(21).translateObject(-0.148f,0.09f,-7.3f);
        objects.get(0).getChildObject().get(21).scaleObject(4.0f,4f,0.09f);
        objects.get(0).getChildObject().get(21).rotateObject((float) Math.toRadians(180f),1.0f,0.0f,0.2f);
    //pedang
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.140f,
                0.210f,
                0.125f,
                36,
                500,
                8
        ));
        objects.get(0).getChildObject().get(22).translateObject(0.6f,0.49f,5.5f);
        objects.get(0).getChildObject().get(22).scaleObject(0.9f,0.9f,0.09f);
        objects.get(0).getChildObject().get(22).rotateObject((float) Math.toRadians(45f),1.0f,0.0f,0.0f);
        //pedang 2
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(1.0f, 1.0f, 1.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.136f,
                0.510f,
                0.125f,
                36,
                500,
                3
        ));
        objects.get(0).getChildObject().get(23).translateObject(0.6f,0.15f,5.5f);
        objects.get(0).getChildObject().get(23).scaleObject(0.9f,0.9f,0.09f);
        objects.get(0).getChildObject().get(23).rotateObject((float) Math.toRadians(45f),1.0f,0.0f,0.0f);
        //pedang 3
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.336f,
                0.110f,
                0.025f,
                36,
                500,
                3
        ));
        objects.get(0).getChildObject().get(24).translateObject(0.6f,-0.08f,5.5f);
        objects.get(0).getChildObject().get(24).scaleObject(0.9f,0.9f,0.09f);
        objects.get(0).getChildObject().get(24).rotateObject((float) Math.toRadians(45f),1.0f,0.0f,0.0f);
        //pedang 4
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f, 0.0f, 0.0f, 1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.065f,
                0.210f,
                0.025f,
                36,
                500,
                3
        ));
        objects.get(0).getChildObject().get(25).translateObject(0.6f,-0.2f,5.5f);
        objects.get(0).getChildObject().get(25).scaleObject(0.9f,0.9f,0.09f);
        objects.get(0).getChildObject().get(25).rotateObject((float) Math.toRadians(45f),1.0f,0.0f,0.0f);
        // object buff wajah
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.0f,0.0f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.100f,
                0.100f,
                0.025f,
                36,
                500,
                1
        ));
        objects.get(0).getChildObject().get(26).translateObject(-0.0f,0.8f,0.35f);
        objects.get(0).getChildObject().get(26).scaleObject(0.9f,0.9f,0.63f);
        objects.get(0).getChildObject().get(26).setCenterPoint(Arrays.asList(0.0f,0.0f,0.0f));
        // bola mata tengah
        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.5f,0.0f,0.0f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.065f,
                0.065f,
                0.025f,
                36,
                500,
                1
        ));
        objects.get(0).getChildObject().get(27).translateObject(-0.0f,0.8f,0.37f);
        objects.get(0).getChildObject().get(27).scaleObject(0.9f,0.9f,0.63f);
        objects.get(0).getChildObject().get(27).setCenterPoint(Arrays.asList(0.0f,0.0f,0.0f));

        // background atas 1

        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.6f,0.4f,0.2f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.65f,
                0.05f,
                0.0f,
                36,
                500,
                3
        ));
        objects.get(1).translateObject(-0.0f,0.25f,0.0f);
        objects.get(1).scaleObject(5f,5f,0f);
        // background bawah

        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.6f,0.4f,0.2f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.65f,
                0.05f,
                0.0f,
                36,
                500,
                3
        ));
        objects.get(2).translateObject(-0.0f,-0.25f,0.0f);
        objects.get(2).scaleObject(5f,5f,0f);
        // background kanan
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.6f,0.4f,0.2f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.05f,
                0.55f,
                0.0f,
                36,
                500,
                3
        ));
        objects.get(3).translateObject(0.25f,0.0f,0.0f);
        objects.get(3).scaleObject(5f,5f,0f);

        //background kiri
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.6f,0.4f,0.2f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.05f,
                0.55f,
                0.0f,
                36,
                500,
                3
        ));
        objects.get(4).translateObject(-0.25f,0.0f,0.0f);
        objects.get(4).scaleObject(5f,5f,0f);
        // backgorund kiri 2
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.6f,0.4f,0.2f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.05f,
                0.55f,
                0.0f,
                36,
                500,
                3
        ));
        objects.get(5).translateObject(0.31f,0.0f,0.0f);
        objects.get(5).scaleObject(5f,5f,0f);
        // backgorund kanan 2
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.6f,0.4f,0.2f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.05f,
                0.55f,
                0.0f,
                36,
                500,
                3
        ));
        objects.get(6).translateObject(-0.31f,0.0f,0.0f);
        objects.get(6).scaleObject(5f,5f,0f);

        // backgorund atas ES
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.678f,0.847f,0.902f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.1f,
                0.30f,
                0.0f,
                36,
                500,
                3
        ));
        objects.get(7).translateObject(-0.05f,0.4f,0.0f);
        objects.get(7).scaleObject(5f,5f,0f);
        // backgroun es mendatar
        // backgorund atas ES
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.678f,0.847f,0.902f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.345f,
                0.125f,
                0.0f,
                36,
                500,
                3
        ));
        objects.get(8).translateObject(-0.03f,0.3f,0.0f);
        objects.get(8).scaleObject(5f,5f,0f);

        // kayu bawah kanan
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.6f,0.4f,0.2f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.05f,
                0.55f,
                0.0f,
                36,
                500,
                3
        ));
        objects.get(9).translateObject(0.25f,-0.5f,0.0f);
        objects.get(9).scaleObject(5f,5f,0f);
        // kayu bawah kiri
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.6f,0.4f,0.2f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.05f,
                0.55f,
                0.0f,
                36,
                500,
                3
        ));
        objects.get(10).translateObject(-0.25f,-0.5f,0.0f);
        objects.get(10).scaleObject(5f,5f,0f);

        // BATU
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.5f,0.5f,0.5f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.25f,
                0.55f,
                0.0f,
                36,
                500,
                3
        ));
        objects.get(11).translateObject(-0.00f,-0.5f,0.0f);
        objects.get(11).scaleObject(5f,5f,0f);

        //BAWAH BATU

        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.6f,0.4f,0.2f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.125f,
                0.125f,
                0.0f,
                36,
                500,
                3
        ));
        objects.get(12).translateObject(-0.23f,-0.835f,0.0f);
        objects.get(12).scaleObject(5f,5f,0f);
        // BAWAH BATU PANJANG

        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.6f,0.4f,0.2f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.455f,
                0.125f,
                0.0f,
                36,
                500,
                3
        ));
        objects.get(13).translateObject(0.08f,-0.835f,0.0f);
        objects.get(13).scaleObject(5f,5f,0f);

        //BAWAH BAWAH BATU KAYU
        // BAWAH BATU PANJANG

        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.6f,0.4f,0.2f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.455f,
                0.125f,
                0.0f,
                36,
                500,
                3
        ));
        objects.get(14).translateObject(-0.066f,-0.965f,0.0f);
        objects.get(14).scaleObject(5f,5f,0f);

        //BAWAH BWAH BATU KANAN
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.6f,0.4f,0.2f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.135f,
                0.125f,
                0.0f,
                36,
                500,
                3
        ));
        objects.get(15).translateObject(0.24f,-0.965f,0.0f);
        objects.get(15).scaleObject(5f,5f,0f);
        // BATU PALING BAWAH
        //BAWAH BWAH BATU KANAN
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.5f,0.5f,0.5f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.335f,
                0.125f,
                0.0f,
                36,
                500,
                3
        ));
        objects.get(16).translateObject(0.145f,-1.1f,0.0f);
        objects.get(16).scaleObject(5f,5f,0f);
        //BATU PALING BAWAH KIRI
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.5f,0.5f,0.5f,1.0f),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.26f,
                0.125f,
                0.0f,
                36,
                500,
                3
        ));
        objects.get(17).translateObject(-0.159f,-1.1f,0.0f);
        objects.get(17).scaleObject(5f,5f,0f);


    }


//    public void input(){
//        if (window.isKeyPressed(GLFW_KEY_W)) {
//            countDegree++;
//            //rotasi terhadap matahari
//            objects.get(0).rotateObject((float) Math.toRadians(1.3f),0.0f,0.0f,1f);
//
//        }
//        if(mouseInput.isLeftButtonPressed()){
//            Vector2f pos = mouseInput.getCurrentPos();
////            System.out.println("x : "+pos.x+" y : "+pos.y);
//            pos.x = (pos.x - (window.getWidth())/2.0f) /
//                    (window.getWidth()/2.0f);
//            pos.y = (pos.y - (window.getHeight())/2.0f) /
//                    (-window.getHeight()/2.0f);
//            //System.out.println("x : "+pos.x+" y : "+pos.y);
//
//            if((!(pos.x > 1 || pos.x < -0.97) && !(pos.y > 0.97 || pos.y < -1))){
//                System.out.println("x : "+pos.x+" y : "+pos.y);
////                objectsPointsControl.get(0).addVertices(new Vector3f(pos.x,pos.y,0));
//            }
//        }
//        if (window.isKeyPressed(GLFW_KEY_D)) {
//            countDegree++;
//            //rotasi terhadap matahari
//            objects.get(0).rotateObject((float) Math.toRadians(1.3f), 0.0f, 1.0f, 0f);
//        }
//        if (window.isKeyPressed(GLFW_KEY_A)) {
//            countDegree++;
//            //rotasi terhadap matahari
//            objects.get(0).rotateObject((float) Math.toRadians(1.3f), 0.0f, -1.0f, 0f);
//        }
//        if (window.isKeyPressed(GLFW_KEY_S)) {
//            countDegree++;
//            //rotasi terhadap matahari
//            objects.get(0).rotateObject((float) Math.toRadians(1.3f), 0.0f, 0.0f, -1.0f);
//        }
//        if(window.isKeyPressed(GLFW_KEY_LEFT_SHIFT)){
//            camera.setPosition(0.0f,0.0f,camera.getPosition().get(2)-0.1f);
//        }
//        if(window.isKeyPressed(GLFW_KEY_LEFT_CONTROL)){
//            camera.setPosition(0.0f,0.0f,camera.getPosition().get(2)+0.1f);
//        }
//
//        if (window.isKeyPressed(GLFW_KEY_F)) {
//            countDegree++;
//            //rotasi terhadap matahari
//            objects.get(0).getChildObject().get(22).rotateObject((float) Math.toRadians(10f),1.0f,0.0f,0.0f);
//            objects.get(0).getChildObject().get(23).rotateObject((float) Math.toRadians(10f),1.0f,0.0f,0.0f);
//            objects.get(0).getChildObject().get(24).rotateObject((float) Math.toRadians(10f),1.0f,0.0f,0.0f);
//            objects.get(0).getChildObject().get(25).rotateObject((float) Math.toRadians(10f),1.0f,0.0f,0.0f);
//        }


//    }

    float looptime = 0f;
    float looptime4 = 0f;
    float looptime5 =0f;

    float looptime6 =0f;
    public void input(){
        if (window.isKeyPressed(GLFW_KEY_W)) {
            countDegree++;
            //rotasi terhadap matahari
            objects.get(0).rotateObject((float) Math.toRadians(1.3f), -1.0f, 0.0f, 0f);
            objects.get(0).translateObject(0.01f, 0.0f, 0f);


        }
        if (window.isKeyPressed(GLFW_KEY_S)) {
            countDegree++;
            //rotasi terhadap matahari
            objects.get(0).rotateObject((float) Math.toRadians(1.3f), 1.0f, 0.0f, 0.0f);
        }
        if (window.isKeyPressed(GLFW_KEY_D)) {
            countDegree++;
            //rotasi terhadap matahari
            objects.get(0).rotateObject((float) Math.toRadians(1.3f), 0.0f, 1.0f, 0f);
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            countDegree++;
            //rotasi terhadap matahari
            objects.get(0).rotateObject((float) Math.toRadians(1.3f), 0.0f, -1.0f, 0f);
        }
        if (window.isKeyPressed(GLFW_KEY_Q)) {
            countDegree++;
            //rotasi terhadap matahari
            objects.get(0).rotateObject((float) Math.toRadians(1.3f), 0.0f, 0.0f, 1.0f);
        }
        if (window.isKeyPressed(GLFW_KEY_E)) {
            countDegree++;
            //rotasi terhadap matahari
            objects.get(0).rotateObject((float) Math.toRadians(1.3f), 0.0f, 0.0f, -1.0f);
        }
        if(window.isKeyPressed(GLFW_KEY_LEFT_SHIFT)){
            camera.setPosition(0.0f,0.0f,camera.getPosition().get(2)-0.1f);
        }
        if(window.isKeyPressed(GLFW_KEY_LEFT_CONTROL)) {
            camera.setPosition(0.0f, 0.0f, camera.getPosition().get(2) + 0.1f);
        }
        if(mouseInput.isLeftButtonPressed()){
            Vector2f pos = mouseInput.getCurrentPos();
//            System.out.println("x : "+pos.x+" y : "+pos.y);
            pos.x = (pos.x - (window.getWidth())/2.0f) /
                    (window.getWidth()/2.0f);
            pos.y = (pos.y - (window.getHeight())/2.0f) /
                    (-window.getHeight()/2.0f);
            //System.out.println("x : "+pos.x+" y : "+pos.y);

            if((!(pos.x > 1 || pos.x < -0.97) && !(pos.y > 0.97 || pos.y < -1))){
                System.out.println("x : "+pos.x+" y : "+pos.y);
//                objectsPointsControl.get(0).addVertices(new Vector3f(pos.x,pos.y,0));
            }
        }

        if(window.isKeyPressed(GLFW_KEY_R)  || looptime>0){
            if(looptime<=0){
                looptime = 2f;
            }
//            if (window.isKeyPressed(GLFW_KEY_F)) {
//            countDegree++;
//            //rotasi terhadap matahari
//            objects.get(0).getChildObject().get(22).rotateObject((float) Math.toRadians(10f),1.0f,0.0f,0.0f);
//            objects.get(0).getChildObject().get(23).rotateObject((float) Math.toRadians(10f),1.0f,0.0f,0.0f);
//            objects.get(0).getChildObject().get(24).rotateObject((float) Math.toRadians(10f),1.0f,0.0f,0.0f);
//            objects.get(0).getChildObject().get(25).rotateObject((float) Math.toRadians(10f),1.0f,0.0f,0.0f);
//        }
            if(looptime >1.0001) {


                //shield

                objects.get(0).getChildObject().get(17).translateObject( 0.001f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(17).rotateObject((float) Math.toRadians(0f),2.0f,0.0f,0.0f);
                objects.get(0).getChildObject().get(18).translateObject( 0.001f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(18).rotateObject((float) Math.toRadians(0f),2.0f,0.0f,0.0f);
                objects.get(0).getChildObject().get(19).translateObject( 0.001f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(19).rotateObject((float) Math.toRadians(0f),2.0f,0.0f,0.0f);
                objects.get(0).getChildObject().get(20).translateObject( 0.001f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(20).rotateObject((float) Math.toRadians(0f),2.0f,0.0f,0.0f);
                objects.get(0).getChildObject().get(21).translateObject( 0.001f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(21).rotateObject((float) Math.toRadians(0f),2.0f,0.0f,0.0f);
                // pedang
                objects.get(0).getChildObject().get(22).translateObject( 0.0f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(22).rotateObject((float) Math.toRadians(1f),1.0f,0.0f,0.0f);
                objects.get(0).getChildObject().get(23).translateObject( 0.0f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(23).rotateObject((float) Math.toRadians(1f),1.0f,0.0f,0.0f);
                objects.get(0).getChildObject().get(24).translateObject( 0.0f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(24).rotateObject((float) Math.toRadians(1f),1.0f,0.0f,0.0f);
                objects.get(0).getChildObject().get(25).translateObject( 0.0f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(25).rotateObject((float) Math.toRadians(1f),1.0f,0.0f,0.0f);
            }else if (looptime <=1){


                //shield
                objects.get(0).getChildObject().get(17).translateObject( -0.001f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(17).rotateObject((float) Math.toRadians(0f),-2.0f,0.0f,0.0f);
                objects.get(0).getChildObject().get(18).translateObject( -0.001f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(18).rotateObject((float) Math.toRadians(0f),-2.0f,0.0f,0.0f);
                objects.get(0).getChildObject().get(19).translateObject( -0.001f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(19).rotateObject((float) Math.toRadians(0f),-2.0f,0.0f,0.0f);
                objects.get(0).getChildObject().get(20).translateObject( -0.001f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(20).rotateObject((float) Math.toRadians(0f),-2.0f,0.0f,0.0f);
                objects.get(0).getChildObject().get(21).translateObject( -0.001f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(21).rotateObject((float) Math.toRadians(0f),-2.0f,0.0f,0.0f);
                //pedang
                objects.get(0).getChildObject().get(22).translateObject( 0.0f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(22).rotateObject((float) Math.toRadians(1f),-1.0f,0.0f,0.0f);
                objects.get(0).getChildObject().get(23).translateObject( 0.0f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(23).rotateObject((float) Math.toRadians(1f),-1.0f,0.0f,0.0f);
                objects.get(0).getChildObject().get(24).translateObject( 0.0f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(24).rotateObject((float) Math.toRadians(1f),-1.0f,0.0f,0.0f);
                objects.get(0).getChildObject().get(25).translateObject( 0.0f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(25).rotateObject((float) Math.toRadians(1f),-1.0f,0.0f,0.0f);

            }
            looptime-=0.01;
        }

        // kedua mata
        if(window.isKeyPressed(GLFW_KEY_T)  || looptime4>0){
            if(looptime4<=0){
                looptime4 = 2f;
            }
            if(looptime4 >1.01) {
                //mata kiri
                objects.get(0).getChildObject().get(2).translateObject( 0.001f,-0.0f,-0.0f);
                objects.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(0f),0.0f,0.0f,0.0f);


                //mata kanan
                objects.get(0).getChildObject().get(3).translateObject( 0.001f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(0f),0.0f,0.0f,0.0f);


            }else if (looptime4 <=2.01){
                //mata kiri
                objects.get(0).getChildObject().get(2).translateObject( -0.001f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(2).rotateObject((float) Math.toRadians(0f),0.0f,0.0f,0.0f);

                //mata kanan
                objects.get(0).getChildObject().get(3).translateObject( -0.001f,0.0f,-0.0f);
                objects.get(0).getChildObject().get(3).rotateObject((float) Math.toRadians(0f),0.0f,0.0f,0.0f);
            }
            looptime4-=0.03;
        }

        // loncat
        if(window.isKeyPressed(GLFW_KEY_Y)  || looptime5>0){
            if(looptime5<=0){
                looptime5 = 2f;
            }
            if(looptime5 >1.01) {

                objects.get(0).translateObject( 0.0f,0.005f,-0.0f);

            }else if (looptime5 <=1){

                objects.get(0).translateObject( 0.0f,-0.005f,-0.0f);

            }
            looptime5-=0.01;
        }
//        // BUFF
//        if(window.isKeyPressed(GLFW_KEY_J)  || looptime6>0){
//            if(looptime6<=0){
//                looptime6 = 2f;
//            }
//            if(looptime6 >1.01) {
//                //mata tengah
//                objects.get(0).getChildObject().get(26).translateObject( 0.00f,-0.0f,0.004f);
//                objects.get(0).getChildObject().get(26).rotateObject((float) Math.toRadians(0f),0.0f,0.0f,0.0f);
//                objects.get(0).getChildObject().get(27).translateObject( 0.00f,-0.0f,0.004f);
//                objects.get(0).getChildObject().get(27).rotateObject((float) Math.toRadians(0f),0.0f,0.0f,0.0f);
//
//            }else if (looptime6 <=2.01){
//                //mata tengah
//                objects.get(0).getChildObject().get(26).translateObject( -0.00f,0.0f,0.000f);
//                objects.get(0).getChildObject().get(26).rotateObject((float) Math.toRadians(0f),0.0f,0.0f,0.0f);
//                objects.get(0).getChildObject().get(27).translateObject( -0.00f,0.0f,0.000f);
//                objects.get(0).getChildObject().get(27).rotateObject((float) Math.toRadians(0f),0.0f,0.0f,0.0f);
//            }
//            looptime6-=0.03;
//        }
    }
        public void loop(){
        while (window.isOpen()) {
            window.update();
            glClearColor(0.647f,
                    0.165f, 0.165f,
                    1.0f);
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
        // free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
    public static void main(String[] args) {
        new Main().run();
    }
}