package com.tuacy.java.workstation.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 自定义插件统计代码行数
 *
 * @version 1.0
 * @author: tuacy.
 * @date: 2021/5/6 16:43.
 */
@Mojo(name = "fileStat", requiresProject = false, defaultPhase = LifecyclePhase.PACKAGE)
public class FileStatMojo extends AbstractMojo {

    /**
     * 外部参数，获取文件路径
     */
    @Parameter(property = "baseDir", defaultValue = "/Users/pathHome")
    private String baseDir;

    /**
     * 外部参数 定义文件后缀
     * 如果要传递 Args的参数需要这样传：mvn clean -Dsuffix=.xml
     */
    @Parameter(property = "suffix", defaultValue = ".java")
    private String suffix;

    @Override
    public void execute() {
        // 当前路径下的文件列表
        List<String> fileList = new ArrayList<>();
        scanFile(this.baseDir, fileList);
        System.out.println("文 件 路 径: " + this.baseDir);
        System.out.println("文 件 后 缀: " + this.suffix);
        System.out.println("文 件 个 数: " + fileList.size());
        System.out.println("代 码 行 数: " + fileLines(fileList));
    }

    /**
     * 递归统计文件，并将符合条件的文件放入集合中
     *
     * @param filePath 文件路径
     * @param fileList 文件列表
     */
    private void scanFile(String filePath, List<String> fileList) {
        File dir = new File(filePath);
        // 递归查找到所有的class文件
        if (dir.listFiles() != null) {
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                if (file.isDirectory()) {
                    scanFile(file.getAbsolutePath(), fileList);
                } else {
                    if (file.getName().endsWith(suffix)) {
                        fileList.add(file.getName());
                    }
                }
            }
        }
    }

    /**
     * 统计指定文件的行数
     *
     * @param fileList 文件列表
     * @return 所有文件的行数
     */
    private int fileLines(List<String> fileList) {
        if (fileList == null || fileList.size() == 0) {
            return 0;
        }
        int lines = 0;
        try {
            for (String file : fileList) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                while (reader.ready()) {
                    reader.readLine();
                    lines++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
