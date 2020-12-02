/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JApplet;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.Sphere;
import java.awt.*;
import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
public class Human3D extends JApplet{

    TransformGroup body;
    TransformGroup tgBola;
    TransformGroup tgBadan;
    TransformGroup tgBahu;
    TransformGroup tgLengan;
    TransformGroup tgTangan;
    TransformGroup tgKaki;

    Sphere bola;
    Cylinder silinder;

    Transform3D trans = new Transform3D();
    Vector3f vector = new Vector3f();

    public Human3D(){
        setLayout(new BorderLayout());
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
        Canvas3D canvas = new Canvas3D(config);
        add("Center",canvas);

        BranchGroup scene = createSceneGraph();
        scene.compile();

        SimpleUniverse simU = new SimpleUniverse(canvas);
        simU.getViewingPlatform().setNominalViewingTransform();
        simU.addBranchGraph(scene);
    }

    Appearance apr(float r, float g, float b){
        Color3f red = new Color3f(r,g,b);
        Color3f black = new Color3f(0.0f,0.0f,0.0f);
        Color3f white = new Color3f(1.0f,1.0f,1.0f);
        //mix warna
        Material material = new Material(red, black, red, white, 100);
        Appearance appearance = new Appearance();
        appearance.setMaterial(material);
        return appearance;
    }

    Transform3D transform(float x, float y, float z) {
        vector.set(x, y, z);
        trans.set(vector);
        return trans;
    }

    void draw(){
     
        body = new TransformGroup();
        body.setTransform(transform(0.0f, 0.0f, 0.0f));

        tgBola = new TransformGroup();
        tgBola.setTransform(transform(0.0f, 3.0f, 0.0f));
        bola = new Sphere(1.0f, apr(1.0f, 0.0f, 0.0f));
        tgBola.addChild(bola);
        body.addChild(tgBola);

        tgBadan = new TransformGroup();
        tgBadan.setTransform(transform(0.0f, 0.2f, 0.0f));
        silinder = new Cylinder(1.5f, 3.5f, apr(1.0f, 0.0f, 0.0f));
        tgBadan.addChild(silinder);
        body.addChild(tgBadan);

        tgBahu = new TransformGroup();
        tgBahu.setTransform(transform(-1.95f, 1.5f, 0.0f));
        bola = new Sphere(0.45f, apr(1.0f, 0.0f, 0.0f));
        tgBahu.addChild(bola);
        body.addChild(tgBahu);

        tgBahu = new TransformGroup();
        tgBahu.setTransform(transform(1.95f, 1.5f, 0.0f));
        bola = new Sphere(0.45f, apr(1.0f, 0.0f, 0.0f));
        tgBahu.addChild(bola);
        body.addChild(tgBahu);

        tgLengan = new TransformGroup();
        tgLengan.setTransform(transform(-1.95f, 0.1f, 0.0f));
        silinder = new Cylinder(0.4f, 2.5f, apr(1.0f, 0.0f, 0.0f));
        tgLengan.addChild(silinder);
        body.addChild(tgLengan);

        tgLengan = new TransformGroup();
        tgLengan.setTransform(transform(1.95f, 0.1f, 0.0f));
        silinder = new Cylinder(0.4f, 2.5f, apr(1.0f, 0.0f, 0.0f));
        tgLengan.addChild(silinder);
        body.addChild(tgLengan);

        tgTangan = new TransformGroup();
        tgTangan.setTransform(transform(1.95f, -1.2f, 0.0f));
        bola = new Sphere(0.4f, apr(1.0f, 0.0f, 0.0f));
        tgTangan.addChild(bola);
        body.addChild(tgTangan);

        tgTangan = new TransformGroup();
        tgTangan.setTransform(transform(-1.95f, -1.2f, 0.0f));
        bola = new Sphere(0.4f, apr(1.0f, 0.0f, 0.0f));
        tgTangan.addChild(bola);
        body.addChild(tgTangan);

        tgKaki = new TransformGroup();
        tgKaki.setTransform(transform(0.7f, -3.0f, 0.0f));
        silinder = new Cylinder(0.5f, 3.0f, apr(1.0f, 0.0f, 0.0f));
        tgKaki.addChild(silinder);
        body.addChild(tgKaki);

        tgKaki = new TransformGroup();
        tgKaki.setTransform(transform(-0.7f, -3.0f, 0.0f));
        silinder = new Cylinder(0.5f, 3.0f, apr(1.0f, 0.0f, 0.0f));
        tgKaki.addChild(silinder);
        body.addChild(tgKaki);
    }
    BranchGroup createSceneGraph(){
        BranchGroup root = new BranchGroup();

        TransformGroup objScale = new TransformGroup();
        //membuat transformasi
        Transform3D scaleTrans = new Transform3D();
        scaleTrans.set(1/6.0f);
        objScale.setTransform(scaleTrans);
        root.addChild(objScale);

        //objek transformasi
        TransformGroup objTrans = new TransformGroup();
        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        objScale.addChild(objTrans);

        draw();
        objTrans.addChild(body);

        BoundingSphere bounds = new BoundingSphere(new Point3d(), 100);
        Background bg = new Background(new Color3f(1.0f, 1.0f, 1.0f));
        bg.setApplicationBounds(bounds);
        objTrans.addChild(bg);

        //mengatur mouse
        MouseRotate mr = new MouseRotate();
        mr.setTransformGroup(objTrans);
        mr.setSchedulingBounds(bounds);
        mr.setFactor(0.007);
        objTrans.addChild(mr);

        //mengatur terang
        Color3f ambientColor = new Color3f(0.1f, 0.1f, 0.1f);
        //penyebaran
        AmbientLight ambientLightNode = new AmbientLight(ambientColor);
        ambientLightNode.setInfluencingBounds(bounds);
        root.addChild(ambientLightNode);
        Color3f light1Color = new Color3f(1.0f, 1.0f, 1.0f);
        Vector3f light1Direction = new Vector3f(-0.2f, 0.0f, -0.8f);
        DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
        light1.setInfluencingBounds(bounds);
        root.addChild(light1);

        return root;
    }
    public static void main(String[] args) {
        Frame frame = new MainFrame(new Human3D(), 500,400);
        frame.setTitle("Human 3D");
        frame.setLocationRelativeTo(null);
    }
}