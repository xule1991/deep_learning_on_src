package execute_tools;

/**
 * name: leo, xu
 * create time: 2016/4/2
 * weixin: 13162003616
 * email: 932803606@qq.com
 * qq: 932803606
 */

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * when I import the source code of lucene 5.5.0 into intellij, I need to add all the jars files it depends on into
 * the classpath, but the bin version of lucene do not have a lib directory, because it is not an app, it just a base
 * library, so I need to extract all the jar files under lucene_5.5.0 into one library
 */
public class ExtractJarFiles {
    private final String ABS_DIR_PATH = "D:\\tools\\lucene-5.5.0";
    private  File root = new File(ABS_DIR_PATH);
    private File lib = new File(ABS_DIR_PATH + "\\lib");

    public ExtractJarFiles() {
        init();
    }

    private void init() {
        lib.mkdir();
    }

    public void run() {
        copyToLibDir(root);
    }

    public void copyToLibDir(File curDir) {
        File[] files = curDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            File cur = files[i];
            if (cur.isDirectory()) {
                copyToLibDir(cur);
            }else {
                if (cur.getName().endsWith("jar")) {
                    copyFile(cur);
                }
            }
        }
    }

    private void copyFile(File cur) {
        File newFile = new File(lib.getAbsolutePath() + "\\" + cur.getName());
        try {
            IOUtils.copy(new FileInputStream(cur), new FileOutputStream(newFile));
        } catch (IOException e) {
            throw new RuntimeException("failed when copy", e);
        }
    }

    public static void main(String[] args) {
        ExtractJarFiles extractJarFiles = new ExtractJarFiles();
        extractJarFiles.run();
    }

}
