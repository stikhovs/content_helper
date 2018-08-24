package com.sergio.contenthelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TablesNMC implements Initializable {

    @FXML
    private TextArea parseOut1, parseOut2, parseOut3, parseOut4, parseOut5;
//    @FXML
//    private Accordion accord;
//    @FXML
//    private TitledPane pane1,pane2,pane3,pane4,pane5;
    @FXML
    private AnchorPane textAnchor;

    //ArrayList<String> t = new ArrayList();
    ArrayList<String> chosenFiles = new ArrayList();
    ArrayList<String> tableNames = new ArrayList();
    ObservableList<TitledPane> panes = FXCollections.observableArrayList();
    List<File> selectedFiles = new ArrayList();
    List<TextArea> textAreas = new ArrayList();

    private final String table = "<table>\n";
    private final String _table = "</table>";
    private final String tr = "<tr>\n";
    private final String _tr = "</tr>\n";
    private final String td = "<td>";
    private final String _td = "</td>\n";
    private final String p = "<p style='text-align: center;'>";
    private final String _p = "</p>\n";
    private final String strong = "<strong>";
    private final String _strong = "</strong>";

    private String startPathFrom = "";
    private String savedPath = "";
    List<String> lines = new ArrayList<>();
    //Path path = Paths.get("startPath.txt");

    @FXML
    private void openFileAction(ActionEvent event) {
        chosenFiles.clear();
        panes.clear();
        tableNames.clear();
        FileChooser fc = new FileChooser();

        fc.setInitialDirectory(new File(System.getProperty("user.home")));

        if (!savedPath.equals("")) {
            fc.setInitialDirectory(new File(savedPath));
        }

        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("xlsx files", "*.xlsx"));
        selectedFiles = fc.showOpenMultipleDialog(null);
        //panes.addAll(accord.getPanes());
        if (selectedFiles != null) {
            for (int i = 0; i < selectedFiles.size(); i++) {
                chosenFiles.add(selectedFiles.get(i).getAbsolutePath());

            }
            savedPath = selectedFiles.get(0).getParent();
        } else {
            System.out.println("Файл не был выбран");
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

        textAnchor.getChildren().clear();
        Accordion accordion = new Accordion();

        for (int filePathNum = 0; filePathNum < chosenFiles.size(); filePathNum++) {

            List<String> htmlElements = new ArrayList();

            // Read XSL file
            FileInputStream inputStream = new FileInputStream(new File(chosenFiles.get(filePathNum)));
            //tableNames.add(selectedFiles.get(filePathNum).getName());
            //panes.get(filePathNum).setText(tableNames.get(filePathNum));
            //System.out.println(tableNames.get(filePathNum));

            // Get the workbook instance for XLS file
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

            // Get first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            // Get iterator to all the rows in current sheet
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {

                Row row = rowIterator.next();

                // Get iterator to all cells of current row
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    // Change to getCellType() if using POI 4.x
                    CellType cellType = cell.getCellTypeEnum();

                    if (cell.getColumnIndex() > 9) {
                        break;
                    }

                    if (cell.getColumnIndex() == 0 && cell.getRowIndex() == 7) {
                        htmlElements.add(table);
                    }

                    switch (cellType) {
                        case _NONE:
                            //System.out.print("");
                            //System.out.print("\t");
                            break;
                        case BOOLEAN:
                            //System.out.print(cell.getBooleanCellValue());
                            //System.out.print("\t");                    
                            break;
                        case BLANK:
                            //System.out.print("");
                            //System.out.print("\t");
                            if (cell.getRowIndex() < 8) {
                                htmlElements.add("");
                            } else if (cell.getColumnIndex() == 0) {
                                htmlElements.add(tr + td + _td + _tr);
                            } else {
                                htmlElements.add("<td></td>\n");
                            }
                            break;
                        case FORMULA:
                            // Formula
                            //System.out.print(cell.getCellFormula());
                            //System.out.print("\t");

                            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                            // Print out value evaluated by formula
                            //System.out.print(evaluator.evaluate(cell).getNumberValue());
                            break;
                        case NUMERIC:
                            //System.out.print(cell.getNumericCellValue());
                            //System.out.print("\t");

                            if (cell.getColumnIndex() == 0) {
                                htmlElements.add(tr + td + strong + (int) cell.getNumericCellValue() + _strong + _td);
                            } else if (cell.getColumnIndex() == 5) {
                                htmlElements.add(td + (int) cell.getNumericCellValue() + _td + _tr);
                            } else {
                                htmlElements.add(td + (int) cell.getNumericCellValue() + _td);
                            }

                            break;
                        case STRING:
                            //System.out.print(cell.getStringCellValue());
                            //System.out.print("\t");
                            if (cell.getColumnIndex() == 0 && cell.getRowIndex() == 0) {
                                htmlElements.add(p);
                                htmlElements.add(strong);
                                htmlElements.add(cell.getStringCellValue().trim());
                                htmlElements.add(_strong);
                                htmlElements.add(_p);
                            } else if (cell.getColumnIndex() == 0 && (cell.getRowIndex() == 2 || cell.getRowIndex() == 3)) {
                                htmlElements.add(p);
                                htmlElements.add(cell.getStringCellValue().trim());
                                htmlElements.add(_p);
                            } else if (cell.getColumnIndex() == 0 && (cell.getRowIndex() == 4 || cell.getRowIndex() == 5)) {
                                String str = cell.getStringCellValue().trim();
                                String[] newStr = str.split("занятий");
                                newStr[0] += "занятий";

                                htmlElements.add(p);
                                htmlElements.add(newStr[0]);
                                htmlElements.add(strong);
                                htmlElements.add(newStr[1].trim());
                                htmlElements.add(_strong);
                                htmlElements.add(_p);
                            } else if (cell.getColumnIndex() == 0) {
                                if (cell.getRowIndex() == 8) {
                                    htmlElements.add(tr + td + strong + cell.getStringCellValue().trim() + _strong + _td);
                                } else {
                                    htmlElements.add(tr + td + cell.getStringCellValue().trim() + _td + _tr);
                                }
                            } else if (cell.getColumnIndex() == 1 && cell.getRowIndex() > 8) {
                                htmlElements.add(td + strong + cell.getStringCellValue().trim() + _strong + _td);
                            } else if (cell.getColumnIndex() == 5) {
                                if (cell.getRowIndex() == 8) {
                                    htmlElements.add(td + strong + cell.getStringCellValue().trim() + _strong + _td + _tr);
                                } else {
                                    htmlElements.add(td + cell.getStringCellValue().trim() + _td + _tr);
                                }
                            } else if ((cell.getColumnIndex() > 0 || cell.getColumnIndex() < 5) && cell.getRowIndex() == 8) {
                                htmlElements.add(td + strong + cell.getStringCellValue().trim() + _strong + _td);
                            } else {
                                htmlElements.add(td + cell.getStringCellValue().trim() + _td);
                            }
                            break;
                        case ERROR:
                            //System.out.print("!");
                            //System.out.print("\t");
                            break;
                    }

                }
                //System.out.println("");
                //htmlElements.add("</tr>\n");
            }
            htmlElements.add(_table);
//      

            for (int i = 0; i < htmlElements.size(); i++) {
                if (htmlElements.get(i).contains("<td></td>\n") || htmlElements.get(i).contains("<td></td>")) {
                    if (htmlElements.get(i + 1).isEmpty()
                            || htmlElements.get(i + 1).contains("<td></td>")
                            || htmlElements.get(i + 1).contains("</table>")
                            || htmlElements.get(i + 1).contains("<tr>")) {
                        htmlElements.remove(i);
                        i--;
                        //System.out.println("Был удален номер: " + i);
                    }

                }
            }

            TitledPane tp = new TitledPane();
            tableNames.add(selectedFiles.get(filePathNum).getName());
            tp.setText(tableNames.get(filePathNum));
            tp.setFont(Font.font(14));
            TextArea t = new TextArea();
            String html = "";
            for (String s : htmlElements) {
                html += s;
            }
            t.setText(html);
            t.setFont(Font.font(14));
            tp.setContent(t);
            accordion.getPanes().addAll(tp);

            if (filePathNum == 0) {
                accordion.setExpandedPane(tp);
            }

//        String str = htmlElements.toString();
//        str = str.replace("[", "");
//        str = str.replace("]", "");
//        str = str.replace(",", "");
//        str = str.replace("<tr> </tr>", "");
//       
//        
//        
//     
//        switch(filePathNum){
//            case 0:
//                parseOut1.setText(str.trim());
//                htmlElements.clear();
//                break;
//            case 1:
//                parseOut2.setText(str.trim());
//                htmlElements.clear();
//                break;
//            case 2:
//                parseOut3.setText(str.trim());
//                htmlElements.clear();
//                break;
//            case 3:
//                parseOut4.setText(str.trim());
//                htmlElements.clear();
//                break;
//            case 4:
//                parseOut5.setText(str.trim());
//                htmlElements.clear();
//                break;            
//        }
        }

        AnchorPane.setTopAnchor(accordion, 0.0);
        AnchorPane.setRightAnchor(accordion, 0.0);
        AnchorPane.setBottomAnchor(accordion, 0.0);
        AnchorPane.setLeftAnchor(accordion, 0.0);
        
        textAnchor.getChildren().addAll(accordion);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
