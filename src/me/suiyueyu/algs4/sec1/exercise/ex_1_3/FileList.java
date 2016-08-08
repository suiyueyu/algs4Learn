package me.suiyueyu.algs4.sec1.exercise.ex_1_3;

import java.io.File;

/**
 * Created by yzcc on 2016/8/8.
 * 1.3.43 文件列表。文件夹就是一列文件和文件夹的列表。
 * 编写一个程序，从命令行接受一个文件夹名作为参数，打印出该文件夹下的所有文件
 * 并用递归的方式在所有子文件夹的名下（缩进）列出其下的所有文件
 * 提示：使用队列，并参考java.io.File.
 */
public class FileList {
    private class FileNode {
        public File file;
        public int depth;
    }

    private Queue<FileNode> fileQueue = new Queue<>();

    public void listAllFiles(File filepath, int depth) {
        File[] filelists = filepath.listFiles();

        for (File file : filelists) {
            FileNode fileNode = new FileNode();
            fileNode.file = file;
            fileNode.depth = depth;

            fileQueue.enqueue(fileNode);

            if (file.isDirectory()) {
                listAllFiles(file, depth + 1);
            } else {

            }
        }
    }

    public void print() {
        for (FileNode fileNode : fileQueue) {
            for (int i = 0; i < fileNode.depth; i++) {
                System.out.print('\t');
            }
            System.out.print(fileNode.file.getName());
            if (fileNode.file.isDirectory()) {
                System.out.print("\\");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        String path = "G:\\SVN\\algs4Learn\\src";
        File f = new File(path);
//        File[] files=f.listFiles();
//        for(File file:files){
//            System.out.println(file + " " + file.isDirectory());
//        }
        FileList fileList = new FileList();
        fileList.listAllFiles(f, 0);
        fileList.print();
    }
}
