
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExampleTest {

//    @Test
//    public void testClassNameAndModifiers() {
//        NodeList<Modifier> modifiers = new NodeList<>();
//        modifiers.add(Modifier.publicModifier());
//        modifiers.add(Modifier.staticModifier());
//        ClassOrInterfaceDeclaration testNode = new ClassOrInterfaceDeclaration();
//        testNode.setModifiers(modifiers);
//        testNode.setName("MyClass");
//
//        JavaClassObject classObject = new JavaClassObject.Builder()
//                .className(testNode.getNameAsString())
//                .modifiers(testNode.getModifiers())
//                .build();
//        assertEquals("MyClass", classObject.getClassName());
//        assertTrue(classObject.getModifiers().contains("public"));
//    }
}
