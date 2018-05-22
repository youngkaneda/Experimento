/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package experimento.tcc;

/**
 *
 * @author juan
 */
public class Output {
    private String collectionType;
    private String methodName;
    private String category;
    private String targetClass;
    
    public Output(String collectionType, String methodName, String category, String targetClass) {
        this.collectionType = collectionType;
        this.methodName = methodName;
        this.category = category;
        this.targetClass = targetClass;
    }

    public String getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    public String getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Output{" + "collectionType=" + collectionType + ", methodName=" + methodName + ", category=" + category + ", targetClass=" + targetClass + '}';
    }
}
