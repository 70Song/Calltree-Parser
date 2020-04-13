package com.songxu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.opencsv.CSVWriter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;



public class XMLParser {

    /*
    To do:
    1. Put calltree xml file of all modules into the same folder
    2. let the folder in 1 be the "input" of first line of code
    3. output could be any folder exist
    4. filePath is the output csv path, including 3 metrics: Self-time, time, count
    5. Sample of output: csv files in the project
     */

    public static void main(String[] args) {


        String input = "/Users/xusong/pdfbox/2.0.18/";
        String output = "./pdfbox/";
        String filePath = "pdfbox-2.0.18.csv";


        File[] files = new File(input).listFiles();
        MethodList all_methods = new MethodList();
        for(File file:files){
            if(file.getName().endsWith("xml")){
                List<MethodInfo> methods = parseXML(input,file.getName(),output);
                for(MethodInfo method: methods){
                    all_methods.add(method);
                }
            }
        }

        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            Writer outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // adding header to csv
            String[] header = { "Number", "Method","Time","Count","SelfTime" };
            writer.writeNext(header);

            // add data to csv
            int count = 1;
            for(MethodInfo info:all_methods.list){
                String method_info = info.className +"."+ info.method +" "+ info.arguments;
                String[] data = {String.valueOf(count),method_info,info.time,info.count,info.selfTime};
                writer.writeNext(data);
                count++;
            }

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static List<MethodInfo> parseXML(String inputDir, String file, String outputDir){
        // write your code here
        List<MethodInfo> info = new ArrayList<>();

        //String Folder = "/Users/xusong/commons-collections/";
        //String file = "commons-collections-4.4-3.xml";
        String input = inputDir + file;
        //String outfile = "";
        String output = outputDir + file;
        output = output.replace(".xml",".csv");
        System.out.println(output);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            //2、创建SAXParser实例
            SAXParser parser = factory.newSAXParser();
            //3、新建一个类SAXHandler继承DefaultHandler，重写其中的一些方法用来进行业务处理。
            //再创建一个SAXHandler对象
            MySaxHandler handler = new MySaxHandler(output);
            //4、进行解析，传入SAXHandler对象作为解析xml的处理类
            parser.parse(input, handler);
            info = handler.list.list;

        } catch (Exception e) {
        }
        return info;
    }
}