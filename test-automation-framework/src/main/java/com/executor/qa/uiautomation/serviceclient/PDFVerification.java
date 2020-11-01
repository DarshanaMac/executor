//package com.executor.qa.automation.serviceclient;
//
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.util.PDFTextStripper;
//
//import java.io.IOException;
//
//public class PDFVerification {
//
//    public static void main(String[] args) {
//        try {
//            //load the pdf document
//            PDDocument pdDocument=PDDocument.load("C:\\Users\\Administrator\\Desktop\\abc.pdf");
//            //count total pages
//            int totalPages= pdDocument.getNumberOfPages();
//            //print out total page numbers
//            System.out.println("Total pages: " +totalPages);
//            //define a PDF file stripper object
//            PDFTextStripper stripper = new PDFTextStripper();
//            //set start page to 1
//            stripper.setStartPage( 1 );
//            //set end page to total pages
//            stripper.setEndPage( totalPages );
//            String extractedText= stripper.getText(pdDocument);
//            System.out.println(extractedText);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
