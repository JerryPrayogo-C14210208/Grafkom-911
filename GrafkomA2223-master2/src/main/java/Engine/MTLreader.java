package Engine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MTLreader {
    public static Map<String, Material> readMTLFile(String filename) {
        Map<String, Material> materials = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            Material currentMaterial = null;

            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) {
                    continue; // Skip empty lines and comments
                }

                String[] tokens = line.split("\\s+");

                if (tokens[0].equals("newmtl")) {
                    String materialName = tokens[1];
                    currentMaterial = new Material(materialName);
                    materials.put(materialName, currentMaterial);
                } else if (tokens[0].equals("Ka")) {
                    float ambientR = Float.parseFloat(tokens[1]);
                    float ambientG = Float.parseFloat(tokens[2]);
                    float ambientB = Float.parseFloat(tokens[3]);
                    currentMaterial.setAmbient(ambientR, ambientG, ambientB);
                } else if (tokens[0].equals("Kd")) {
                    float diffuseR = Float.parseFloat(tokens[1]);
                    float diffuseG = Float.parseFloat(tokens[2]);
                    float diffuseB = Float.parseFloat(tokens[3]);
                    currentMaterial.setDiffuse(diffuseR, diffuseG, diffuseB);
                } else if (tokens[0].equals("Ks")) {
                    float specularR = Float.parseFloat(tokens[1]);
                    float specularG = Float.parseFloat(tokens[2]);
                    float specularB = Float.parseFloat(tokens[3]);
                    currentMaterial.setSpecular(specularR, specularG, specularB);
                } else if (tokens[0].equals("Ns")) {
                    float shininess = Float.parseFloat(tokens[1]);
                    currentMaterial.setShininess(shininess);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return materials;
    }

    public static class Material {
        public String name;
        public float ambientR, ambientG, ambientB;
        public float diffuseR, diffuseG, diffuseB;
        public float specularR, specularG, specularB;
        public float shininess;

        public Material(String name) {
            this.name = name;
        }

        public void setAmbient(float r, float g, float b) {
            ambientR = r;
            ambientG = g;
            ambientB = b;
        }

        public void setDiffuse(float r, float g, float b) {
            diffuseR = r;
            diffuseG = g;
            diffuseB = b;
        }

        public void setSpecular(float r, float g, float b) {
            specularR = r;
            specularG = g;
            specularB = b;
        }

        public void setShininess(float shininess) {
            this.shininess = shininess;
        }

        public String getName() {
            return name;
        }

        // Getter methods for material properties
        // ...
    }
}