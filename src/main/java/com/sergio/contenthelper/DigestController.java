
package com.sergio.contenthelper;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import static com.sergio.contenthelper.SlideMaker.newsLinkRus;

public class DigestController extends SlideMaker implements Initializable {
    @FXML
    TextArea textAreaRUS;
    @FXML
    TextArea textAreaENG;
    @FXML
    TextArea textAreaDE;
    @FXML
    TextArea textAreaESP;
    @FXML
    TextArea textAreaCH;
    @FXML
    TextArea textAreaAR;
    
    @FXML
    private Tab tabRUS;
    @FXML
    private Tab tabENG;
    @FXML
    private Tab tabDE;
    @FXML
    private Tab tabESP;
    @FXML
    private Tab tabCH;
    @FXML
    private Tab tabAR;
    
    public static String imageLinkDigest = "#";
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println("newsLinkRus " + newsLinkRus);
        
        String linkRUS = newsLinkRus;
        String linkENG = newsLinkRus.replaceAll("https://investmoscow", "https://en.investmoscow");
        if(linkRUS.isEmpty()) linkRUS = "#";
        if(linkENG.isEmpty()) linkENG = "#";
        
        String titleTextRUS = "Опубликован очередной выпуск Инвестиционного дайджеста Москвы";
        String subTitleTextRUS = "Ознакомиться с дайджестом на русском и английском языках можно в разделе «Медиа».";
        
        String titleTextENG = "The latest edition of the Moscow Investment Digest has come out";
        String subTitleTextENG = "The Digest is now available in Russian and English in the \"Media\" section.";
            
        String titleTextDE = "Es wurde die neue Ausgabe des Moskauer Digests für Investitionen veröffentlicht";
        String subTitleTextDE = "Sie finden das Digest in Russisch und in Englisch im Abschnitt „Media“.";
                    
        String titleTextESP = "Publicado el nuevo número del Resumen de los principales eventos de la inversión en Moscú";
        String subTitleTextESP = "El Resumen está disponible en los idiomas ruso e inglés en la sección \"Medios de comunicación\".";
            
        String titleTextCH = "莫斯科投资摘要最新专辑已刊登";
        String subTitleTextCH = "了解俄文和英文版摘要见“媒体”版面。";
                    
        String titleTextAR = "إصدار عدد جديد من نشرة الاستثمارات في موسكو";
        String subTitleTextAR = "يمكن الاطلاع على النشرة باللغتين الروسية والإنجليزية في قسم \"الميديا\"";
        
        String russianText = "<!-----------------------SLIDE------------------------------->\n" +
"    <li>\n" +
"        <div class=\"promo promo--shadow promo--first\">\n" +
"            <a href=\""+linkRUS+"\" class=\"promo__item\">\n" +
"                <div class=\"promo__content\">\n" +
"                    <div class=\"promo__content-text\">\n" +
"                        <span class=\"promo__title hidden-sm\">"+titleTextRUS+"</span>\n" +
"                        <p>"+subTitleTextRUS+"</p>\n" +
"                    </div>\n" +
"                </div>\n" +
"            </a>\n" +
"            <img src=\""+imageLinkDigest+"\" height=\"280\" alt=\"\" class=\"promo__pict\" style=\"height: 280px;\">\n" +
"        </div>\n" +
"    </li>\n" +
"    <!-----------------------/SLIDE------------------------------->";
        
        String englishText = "<!-----------------------SLIDE------------------------------->\n" +
"    <li>\n" +
"        <div class=\"promo promo--shadow promo--first\">\n" +
"            <a href=\""+linkENG+"\" class=\"promo__item\">\n" +
"                <div class=\"promo__content\">\n" +
"                    <div class=\"promo__content-text\">\n" +
"                        <span class=\"promo__title hidden-sm\">"+titleTextENG+"</span>\n" +
"                        <p>"+subTitleTextENG+"</p>\n" +
"                    </div>\n" +
"                </div>\n" +
"            </a>\n" +
"            <img src=\""+imageLinkDigest+"\" height=\"280\" alt=\"\" class=\"promo__pict\" style=\"height: 280px;\">\n" +
"        </div>\n" +
"    </li>\n" +
"    <!-----------------------/SLIDE------------------------------->";
        
        String germanText = "<!-----------------------SLIDE------------------------------->\n" +
"    <li>\n" +
"        <div class=\"promo promo--shadow promo--first\">\n" +
"            <a href=\""+linkENG+"\" class=\"promo__item\">\n" +
"                <div class=\"promo__content\">\n" +
"                    <div class=\"promo__content-text\">\n" +
"                        <span class=\"promo__title hidden-sm\">"+titleTextDE+"</span>\n" +
"                        <p>"+subTitleTextDE+"</p>\n" +
"                    </div>\n" +
"                </div>\n" +
"            </a>\n" +
"            <img src=\""+imageLinkDigest+"\" height=\"280\" alt=\"\" class=\"promo__pict\" style=\"height: 280px;\">\n" +
"        </div>\n" +
"    </li>\n" +
"    <!-----------------------/SLIDE------------------------------->";
        
        String spanishText = "<!-----------------------SLIDE------------------------------->\n" +
"    <li>\n" +
"        <div class=\"promo promo--shadow promo--first\">\n" +
"            <a href=\""+linkENG+"\" class=\"promo__item\">\n" +
"                <div class=\"promo__content\">\n" +
"                    <div class=\"promo__content-text\">\n" +
"                        <span class=\"promo__title hidden-sm\">"+titleTextESP+"</span>\n" +
"                        <p>"+subTitleTextESP+"</p>\n" +
"                    </div>\n" +
"                </div>\n" +
"            </a>\n" +
"            <img src=\""+imageLinkDigest+"\" height=\"280\" alt=\"\" class=\"promo__pict\" style=\"height: 280px;\">\n" +
"        </div>\n" +
"    </li>\n" +
"    <!-----------------------/SLIDE------------------------------->";
        
        String chineseText = "<!-----------------------SLIDE------------------------------->\n" +
"    <li>\n" +
"        <div class=\"promo promo--shadow promo--first\">\n" +
"            <a href=\""+linkENG+"\" class=\"promo__item\">\n" +
"                <div class=\"promo__content\">\n" +
"                    <div class=\"promo__content-text\">\n" +
"                        <span class=\"promo__title hidden-sm\">"+titleTextCH+"</span>\n" +
"                        <p>"+subTitleTextCH+"</p>\n" +
"                    </div>\n" +
"                </div>\n" +
"            </a>\n" +
"            <img src=\""+imageLinkDigest+"\" height=\"280\" alt=\"\" class=\"promo__pict\" style=\"height: 280px;\">\n" +
"        </div>\n" +
"    </li>\n" +
"    <!-----------------------/SLIDE------------------------------->";
        
        String arabicText = "<!-----------------------SLIDE------------------------------->\n" +
"    <li>\n" +
"        <div class=\"promo promo--shadow promo--first\">\n" +
"            <a href=\""+linkENG+"\" class=\"promo__item\">\n" +
"                <div class=\"promo__content\">\n" +
"                    <div class=\"promo__content-text\">\n" +
"                        <span class=\"promo__title hidden-sm\">"+titleTextAR+"</span>\n" +
"                        <p>"+subTitleTextAR+"</p>\n" +
"                    </div>\n" +
"                </div>\n" +
"            </a>\n" +
"            <img src=\""+imageLinkDigest+"\" height=\"280\" alt=\"\" class=\"promo__pict\" style=\"height: 280px;\">\n" +
"        </div>\n" +
"    </li>\n" +
"    <!-----------------------/SLIDE------------------------------->";
        
        textAreaRUS.textProperty().setValue(russianText);
        textAreaENG.textProperty().setValue(englishText);
        textAreaDE.textProperty().setValue(germanText);
        textAreaESP.textProperty().setValue(spanishText);
        textAreaCH.textProperty().setValue(chineseText);
        textAreaAR.textProperty().setValue(arabicText);
        
        String styleForTabs = "-fx-font-size: 16px;";
        tabRUS.setStyle(styleForTabs);
        tabENG.setStyle(styleForTabs);
        tabDE.setStyle(styleForTabs);
        tabESP.setStyle(styleForTabs);
        tabCH.setStyle(styleForTabs);
        tabAR.setStyle(styleForTabs);
        
    }
    
}
