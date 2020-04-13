package com.songxu;

import com.opencsv.CSVWriter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.*;

public class MySaxHandler extends DefaultHandler {
    int methodIndex = 0;
    MethodList list = new MethodList();
    String output;

    MySaxHandler(String output){
        this.output = output;
    }

    /**
     * 开始解析xml文件
     */
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();

        //System.out.println("Start parsing xml file\n");
    }

    /**
     * 解析xml文件结束
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        /*
        //String filePath = "commons-collections-4.4-3.csv";
        File file = new File(output);
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
            for(MethodInfo info:list.list){
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
         */

        //System.out.println("End parsing xml file\n");
    }

    /**
     * 开始解析xml元素
     */
    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        //qName为标签名称
        if("node".equals(qName)) {
            methodIndex++;
            //System.out.println("======Start parsing method======\n");

            MethodInfo info = new MethodInfo(attributes.getValue("class"),
                                             attributes.getValue("methodName"),
                                             attributes.getValue("methodSignature"),
                                             attributes.getValue("time"),
                                             attributes.getValue("count"),
                                             attributes.getValue("selfTime")
                                            );
            list.add(info);

            /*
            //如果已知标签的属性，则可以直接调用Attributes.getValue(String name)获取属性值
            System.out.println("class:" + attributes.getValue("class") + "\n");
            System.out.println("method:" + attributes.getValue("methodName") + "\n");
            System.out.println("arguments:" + attributes.getValue("methodSignature") + "\n");
            System.out.println("time:" + attributes.getValue("time") + "\n");
            System.out.println("count:" + attributes.getValue("count") + "\n");
            System.out.println("selfTime:" + attributes.getValue("selfTime") + "\n");
*/
            /*
            //如果不知道标签的属性，则需要遍历Attributes分别获取属性名与属性值
            int len = attributes.getLength();
            System.out.println("this node has" + len + "attributes\n");
            for(int i = 0; i < len; i++) {
                //通过Attributes.getQName(int index)获取属性名称
                System.out.print("获取node属性" + attributes.getQName(i) + "\n");
                //通过Attributes.getValue(int index)获取属性值
                System.out.println("，属性值-->" + attributes.getValue(i) + "\n");
            }
             */
        } else if(!"node".equals(qName) ) {
            //System.out.print("解析到节点" + qName + "\n");
        }
    }

    /**
     * 解析xml元素结束
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if("node".equals(qName)) {
            //System.out.println("======解析第" + methodIndex + "个方法结束======\n");
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        //ch为整个xml文档的内容，start为当前标签内容所在位置，length为当前标签内容的长度
        //需要过滤掉空格和换行，SAX解析也会把空格和换行当做xml文档的内容
        String str = new String(ch, start, length);
        if(!"".equals(str.trim())) {
            //System.out.println("-->节点值：" + str + "\n");
        }
    }
}
