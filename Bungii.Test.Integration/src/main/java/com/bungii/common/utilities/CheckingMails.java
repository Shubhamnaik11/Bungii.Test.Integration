package com.bungii.common.utilities;

import com.sun.mail.imap.IMAPFolder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

public class CheckingMails {
    String host = "imap.googlemail.com";// change accordingly
    String user = "vishal.bagi.cci@gmail.com";// change accordingly
    String signUpEmailAddress = "vishal.bagi@creativecapsule.com";// change accordingly
    String password = "password";// change accordingly

    public String checkAndFindActivationURL( ) {
        String activationURL = null;
        //run the email checking code in a time loop
        long t = System.currentTimeMillis();
        long end = t + (5 * 60 * 1000);//run for 5 min 5*60*1000 in milli seconds
        boolean EmailwithLinkFound = false;
        String JAVA_HOME="C:\\Program Files\\Java\\jdk1.8.0_131";
        while ((System.currentTimeMillis() < end)) {

            try {

                Properties properties = new Properties();
                properties.setProperty("mail.store.protocol", "imaps");

                Session session = Session.getDefaultInstance(properties, null);//creating session

                Store store = session.getStore("imaps");

                store.connect(host, user, password);//connect to GMail using imap protocol
                //check folder list in GMAIL
                Folder[] f = store.getDefaultFolder().list();
                for (Folder fd : f)
                    System.out.println(">> " + fd.getName());

                //Read Mail from NogginRequestor folder
                Folder folder = (IMAPFolder) store.getFolder("INBOX");//get mails from inbox folder

                System.out.print("Time ::::" + System.currentTimeMillis() + "\n");


                if (!folder.isOpen())
                    folder.open(Folder.READ_WRITE);
                Message[] messages = folder.getMessages();
                System.out.println("No of Messages : " + folder.getMessageCount());
                System.out.println("No of Unread Messages : " + folder.getUnreadMessageCount());
                System.out.println(messages.length);


                for (int i = 0; i < messages.length; i++) {

                    System.out.println("*****************************************************************************");
                    System.out.println("MESSAGE " + (i + 1) + ":");
                    Message msg = messages[i];
                    //System.out.println(msg.getMessageNumber());
                    //Object String;
                    //System.out.println(folder.getUID(msg)

                    String subject = msg.getSubject();//important value

                    System.out.println("Subject: " + subject);
                    System.out.println("From: " + msg.getFrom()[0]);
                    System.out.println("To: " + msg.getAllRecipients()[0]);//important value
                    System.out.println("Date: " + msg.getReceivedDate());
                    System.out.println("Plain text: " + getText(msg));
                    System.out.println("Alrtnayove: " + getTextFromMessage(msg));
                    readLineByLineJava8("D:\\Bungii-QA-Automation\\Bungii.Test.Integration\\src\\main\\resources\\EmailTemplate\\BungiiReceipt.txt", getText(msg));
                    //System.out.println("Size: "+msg.getSize());
                    //System.out.println(msg.getFlags());

                    //if To Email address has same address as the address used during sign up and subject is same as "Activate Account"
                    if ((/*msg.getFrom()[0].toString().equals(signUpEmailAddress)) &&*/ (!subject.equals("Activate account")))) {
                        String EmailContent = msg.getContent().toString();
                        System.out.println("Email Found!!!\nEmail Content: \n" + EmailContent);//need to get extract link value from here
                        //Invoke jSoupHTMLToString object
                        Document drtata=Jsoup.parse(EmailContent);
                        if (activationURL != null) {
                            //System.out.println("Activation URL : "+ activationURL);
                            EmailwithLinkFound = true;//break the while loop when URL is extracted
                        } else {
                            System.out.println("Activation URL is Null!!!!! ");
                        }
                    }


                }


                store.close();//closing connect with GMail
                //if EmailwithLinkFound is true then break the while loop
                if (EmailwithLinkFound) {
                    break;
                }

                //sleeping for 30 secs
                Thread.sleep(30 * 1000);

            } catch (NoSuchProviderException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return activationURL;
    }

    private String getTextFromMessage(Message message) throws MessagingException, IOException {
        String result = "";
        if (message.isMimeType("text/plain")) {
            result = message.getContent().toString();
        } else if (message.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
            result = getTextFromMimeMultipart(mimeMultipart);
        }
        return result;
    }
    private String getTextFromMimeMultipart(
            MimeMultipart mimeMultipart)  throws MessagingException, IOException{
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
            } else if (bodyPart.getContent() instanceof MimeMultipart){
                result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
            }
        }
        return result;
    }



    ////////////////////////////////////////////////////////////////////////////ALL HTML including alternatvie
    private boolean textIsHtml = false;

    private String getText(Part p) throws MessagingException, IOException {
        if (p.isMimeType("text/*")) {
            String s = (String)p.getContent();
            textIsHtml = p.isMimeType("text/html");
            return s;
        }

        if (p.isMimeType("multipart/alternative")) {
            // prefer html text over plain text
            Multipart mp = (Multipart)p.getContent();
            String text = null;
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(i);
                if (bp.isMimeType("text/plain")) {
                    if (text == null)
                        text = getText(bp);
                    continue;
                } else if (bp.isMimeType("text/html")) {
                    String s = getText(bp);
                    if (s != null)
                        return s;
                } else {
                    return getText(bp);
                }
            }
            return text;
        } else if (p.isMimeType("multipart/*")) {
            Multipart mp = (Multipart)p.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                String s = getText(mp.getBodyPart(i));
                if (s != null)
                    return s;
            }
        }

        return null;
    }

    ////////////////////////
    private static String readLineByLineJava8(String filePath,String emailValue) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
            stream.forEach(s -> contentBuilder.append(s).append("\r\n"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        String fileValue=contentBuilder.toString();
        Path p1 = Paths.get(filePath);

        List<String> listF1 = Files.readAllLines(p1);
        List<String> listF2 = Arrays.asList(emailValue.split("\r\n"));
        if(listF1.size()==listF2.size())
        for(int i=0;i<listF1.size();i++){

            if(listF1.get(i).equals(listF2.get(i))){
                System.out.println("Subject: match found");

            }else{
                System.out.println(listF1.get(i));
                System.out.println(listF2.get(i));

            }
        }
        if ((listF1.equals(listF2)))
        {
            System.out.println("Subject: match found");

        }else{

            System.out.println("Subject: not found"+contentBuilder.toString().compareTo(emailValue));

        }
        return contentBuilder.toString();
    }

}
