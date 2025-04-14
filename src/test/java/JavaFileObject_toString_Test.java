import com.github.JavaCodeVisualizer.JavaFileObject;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

public class JavaFileObject_toString_Test {
    @Test
    public void testToString() throws IOException {
        Path path = Paths.get("src/test/java/JavaFileObject_toString_Test.java");
        CompilationUnit cu = StaticJavaParser.parse(Files.newInputStream(path));
        JavaFileObject testFileObject = new JavaFileObject.Builder()
                .fileName(path.getFileName().toString())
                .filePath(path)
                .filePackage(cu.getPackageDeclaration().get())
                .imports(cu.getImports())
                .classes(null)
                .build();
        assertEquals("JavaFileObject [fileName=JavaFileObject_toString_Test.java\n"
                + "filePath=src\\test\\java\\JavaFileObject_toString_Test.java\n"
                + "filePackage=null\n"
                + "imports=[com.github.JavaCodeVisualizer.JavaFileObject, com.github.javaparser.StaticJavaParser, com.github.javaparser.ast.CompilationUnit, org.junit.jupiter.api.Test, java.io.IOException, java.nio.file.Files, java.nio.file.Path, java.nio.file.Paths, org.junit.jupiter.api.Assertions]\n"
                + "classes=null]", testFileObject.toString());
    }
}
