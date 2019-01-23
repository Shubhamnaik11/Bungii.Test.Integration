package com.bungii.common.utilities;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class GenerateSummaryReport {
    static Path configFilePath ;
    private static ArrayList<String> summaryData = new ArrayList<>();
    private static int passCount = 0, failCount = 0, inConclusiveCount = 0;
    private static String logoFilePath="";

    public static void main(String[] args) throws IOException {
        if(args.length>0) {
            String mainFolder = args[0];
            configFilePath = Paths.get(mainFolder);
            //get List of File
            List<String> listOfResultFile = getListOfResultFile();

            //Iterate over all HTML file
            for (String path : listOfResultFile) {

                File in = new File(path);

                String subFolder = in.getParentFile().getName();
                //Logo image patj
                if (logoFilePath.equals("")) {
                    PropertyUtility.loadRunConfigProps();
                    String logoPath = PropertyUtility.getResultConfigProperties("MISC_DIRECTORY") + "/" + PropertyUtility.getResultConfigProperties("LOGO_FILENAME");
                    logoFilePath = subFolder + "/" + logoPath;
                }
                //Parse HTML file and extract data
                Document doc = Jsoup.parse(in, null);
                Element table = doc.select("table").get(0); //select the first table.
                Elements rows = table.select("tr");
                summaryData.add("<tr> </tr>");
                summaryData.add(" <td colspan=6><a href=" + subFolder + "/" + in.getName() + ">TEST SUITE SUMMARY : " + in.getName() + "</td>");
                summaryData.add("<tr> </tr>");

                passCount = passCount + Integer.parseInt(doc.getElementById("pass").val().contains("--") ? "0" : doc.getElementById("pass").val());
                failCount = failCount + Integer.parseInt(doc.getElementById("fail").val().contains("--") ? "0" : doc.getElementById("fail").val());
                inConclusiveCount = inConclusiveCount + Integer.parseInt(doc.getElementById("inconclusive").val().contains("--") ? "0" : doc.getElementById("inconclusive").val());

                for (int i = 1 + 1; i < rows.size(); i++) { //first row is the col names so skip it.
                    Element row = rows.get(i);
                    Elements cols = row.select("td");
                    String data = cols.toString();
                    System.out.println(data);
                    summaryData.add("<tr></tr>");
                    summaryData.add(data);
                }
            }
            createResultFileFromTemplate();
        }else{
            System.err.println("Pass Main folder  name of parallel test  as argument");
        }
    }

    /**
     * @return return List of HTML file
     * @throws IOException
     */
    public static List<String> getListOfResultFile()throws IOException{
        List<String> listOfResultFile = Files.walk(configFilePath)
                .filter(s -> s.toString().endsWith(".html")).map((p) -> p.getParent() + "/" + p.getFileName())
                .sorted()
                .collect(toList());
        return listOfResultFile;
    }

    /**
     * Create Summery File for parallel test
     */
    public static void createResultFileFromTemplate() {

        try {
            File result = new File(configFilePath + "/" + PropertyUtility.getResultConfigProperties("MERGED_SUMMARY_FILE"));
            BufferedReader br = new BufferedReader(new InputStreamReader(ReportGeneratorUtility.class.getResourceAsStream("/" + "Templates/summarytemplate.html")));
            String s;
            String totalStr = "";
            String listString = String.join("", summaryData);

            while ((s = br.readLine()) != null) {
                totalStr += s;
            }
             totalStr = totalStr.replaceAll("<!--LOGO.PATH-->",logoFilePath);
            totalStr = totalStr.replaceAll("<!--SUMARRY-->", listString);
            totalStr = totalStr.replaceAll("<!--PASSED.COUNT-->", passCount + "");
            totalStr = totalStr.replaceAll("<!--FAILED.COUNT-->", failCount + "");
            totalStr = totalStr.replaceAll("<!--INCONCLUSIVE.COUNT-->", inConclusiveCount + "");

            FileWriter fw = new FileWriter(result);
            fw.write(totalStr);
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
