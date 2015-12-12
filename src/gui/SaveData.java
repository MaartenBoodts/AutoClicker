package gui;

import org.w3c.dom.*;
import util.data.Operation;
import util.data.OperationTypeEnum;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class SaveData {

    private SaveData() {
    }

    public static void saveData(JFrame frame, ArrayList<Operation> actionsList) {
        JFileChooser chooser = new JFileChooser();
        Pattern bsacPattern = Pattern.compile(".*[.]bsac$");

        chooser.setFileFilter(new FileNameExtensionFilter("AutoClicker save file .bsac", "bsac"));
        int response = chooser.showSaveDialog(frame);

        if (response == JFileChooser.APPROVE_OPTION) {
            String filename = chooser.getSelectedFile().getAbsolutePath();

            if (!bsacPattern.matcher(filename).matches()) {
                filename = filename + ".bsac";
            }

            writeXmlFile(filename, actionsList);
        }
    }

    public static ArrayList<Operation> loadData(JFrame frame) throws NullPointerException {
        JFileChooser chooser = new JFileChooser();

        chooser.setFileFilter(new FileNameExtensionFilter("AutoClicker save file .bsac", "bsac"));
        int response = chooser.showOpenDialog(frame);

        if (response == JFileChooser.APPROVE_OPTION) {
            String filename = chooser.getSelectedFile().getAbsolutePath();

            return readXmlFile(filename);
        }
        return null;
    }

    private static void writeXmlFile(String fileName, ArrayList<Operation> actionsList) {

        try {
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document doc = build.newDocument();

            Element root = doc.createElement("Actions");
            doc.appendChild(root);

            for (Operation operation : actionsList) {

                Element actionElement = doc.createElement("Action");
                root.appendChild(actionElement);

                Attr attrType = doc.createAttribute("TypeId");
                attrType.setValue(String.valueOf(operation.getOperationTypeEnum().getTypeId()));
                actionElement.setAttributeNode(attrType);

                switch (operation.getOperationTypeEnum().getTypeId()) {
                    case 0:
                        Element delay = doc.createElement("Delay");
                        delay.appendChild(doc.createTextNode(String.valueOf(operation.getDelay())));
                        actionElement.appendChild(delay);
                        break;
                    case 1:
                        Element X = doc.createElement("X");
                        X.appendChild(doc.createTextNode(String.valueOf(operation.getX())));
                        actionElement.appendChild(X);

                        Element Y = doc.createElement("Y");
                        Y.appendChild(doc.createTextNode(String.valueOf(operation.getY())));
                        actionElement.appendChild(Y);
                        break;
                    case 2:
                        Element buttonCode = doc.createElement("ButtonCode");
                        buttonCode.appendChild(doc.createTextNode(String.valueOf(operation.getButtons())));
                        actionElement.appendChild(buttonCode);
                        break;
                    case 3:
                        buttonCode = doc.createElement("ButtonCode");
                        buttonCode.appendChild(doc.createTextNode(String.valueOf(operation.getButtons())));
                        actionElement.appendChild(buttonCode);
                        break;
                    case 4:
                        buttonCode = doc.createElement("ButtonCode");
                        buttonCode.appendChild(doc.createTextNode(String.valueOf(operation.getButtons())));
                        actionElement.appendChild(buttonCode);
                        break;
                    case 5:
                        Element wheelAmt = doc.createElement("WheelAmt");
                        wheelAmt.appendChild(doc.createTextNode(String.valueOf(operation.getWheelAmt())));
                        actionElement.appendChild(wheelAmt);
                        break;
                    case 6:
                        Element keyCode = doc.createElement("KeyCode");
                        keyCode.appendChild(doc.createTextNode(String.valueOf(operation.getKeyCode())));
                        actionElement.appendChild(keyCode);
                        break;
                    case 7:
                        keyCode = doc.createElement("KeyCode");
                        keyCode.appendChild(doc.createTextNode(String.valueOf(operation.getKeyCode())));
                        actionElement.appendChild(keyCode);
                        break;
                    case 8:
                        keyCode = doc.createElement("KeyCode");
                        keyCode.appendChild(doc.createTextNode(String.valueOf(operation.getKeyCode())));
                        actionElement.appendChild(keyCode);
                        break;
                    case 9:
                        Attr attrFullScreen = doc.createAttribute("FullScreen");
                        attrFullScreen.setValue(String.valueOf(operation.isCaptureFullScreen()));
                        actionElement.setAttributeNode(attrFullScreen);

                        if (!operation.isCaptureFullScreen()) {
                            Element startX = doc.createElement("StartX");
                            startX.appendChild(doc.createTextNode(String.valueOf(operation.getStartX())));
                            actionElement.appendChild(startX);

                            Element startY = doc.createElement("StartY");
                            startY.appendChild(doc.createTextNode(String.valueOf(operation.getStartY())));
                            actionElement.appendChild(startY);

                            Element endX = doc.createElement("EndX");
                            endX.appendChild(doc.createTextNode(String.valueOf(operation.getEndX())));
                            actionElement.appendChild(endX);

                            Element endY = doc.createElement("EndY");
                            endY.appendChild(doc.createTextNode(String.valueOf(operation.getEndY())));
                            actionElement.appendChild(endY);
                        }
                        break;
                }
            }


            // Save the document to the disk file
            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();

            // format the XML nicely
            aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");

            aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            try {
                // location and name of XML file you can change as per need
                FileWriter fos = new FileWriter(fileName);
                StreamResult result = new StreamResult(fos);
                aTransformer.transform(source, result);

            } catch (IOException e) {

                e.printStackTrace();
            }

        } catch (TransformerException ex) {
            System.out.println("Error outputting document");

        } catch (ParserConfigurationException ex) {
            System.out.println("Error building document");
        }
    }

    private static ArrayList<Operation> readXmlFile(String fileName) throws NullPointerException {

        ArrayList<Operation> actionsList = new ArrayList<>();

        try {
            File fXmlFile = new File(fileName);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("Action");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node xmlNode = nodeList.item(i);

                if (xmlNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    Element xmlElement = (Element) xmlNode;

                    String typeId = xmlElement.getAttribute("TypeId");
                    Operation operation = null;

                    switch (Integer.parseInt(typeId)) {
                        case 0:
                            operation = new Operation(OperationTypeEnum.DELAY);
                            operation.setDelay(Integer.parseInt(xmlElement.getElementsByTagName("Delay").item(0).getTextContent()));
                            break;
                        case 1:
                            operation = new Operation(OperationTypeEnum.MOUSEMOVE);
                            operation.setX(Integer.parseInt(xmlElement.getElementsByTagName("X").item(0).getTextContent()));
                            operation.setY(Integer.parseInt(xmlElement.getElementsByTagName("Y").item(0).getTextContent()));
                            break;
                        case 2:
                            operation = new Operation(OperationTypeEnum.MOUSECLICK);
                            operation.setButtons(Integer.parseInt(xmlElement.getElementsByTagName("ButtonCode").item(0).getTextContent()));
                            break;
                        case 3:
                            operation = new Operation(OperationTypeEnum.MOUSEPRESS);
                            operation.setButtons(Integer.parseInt(xmlElement.getElementsByTagName("ButtonCode").item(0).getTextContent()));
                            break;
                        case 4:
                            operation = new Operation(OperationTypeEnum.MOUSERELEASE);
                            operation.setButtons(Integer.parseInt(xmlElement.getElementsByTagName("ButtonCode").item(0).getTextContent()));
                            break;
                        case 5:
                            operation = new Operation(OperationTypeEnum.MOUSEWHEEL);
                            operation.setWheelAmt(Integer.parseInt(xmlElement.getElementsByTagName("WheelAmt").item(0).getTextContent()));
                            break;
                        case 6:
                            operation = new Operation(OperationTypeEnum.KEYCLICK);
                            operation.setKeyCode(Integer.parseInt(xmlElement.getElementsByTagName("KeyCode").item(0).getTextContent()));
                            break;
                        case 7:
                            operation = new Operation(OperationTypeEnum.KEYPRESS);
                            operation.setKeyCode(Integer.parseInt(xmlElement.getElementsByTagName("KeyCode").item(0).getTextContent()));
                            break;
                        case 8:
                            operation = new Operation(OperationTypeEnum.KEYRELEASE);
                            operation.setKeyCode(Integer.parseInt(xmlElement.getElementsByTagName("KeyCode").item(0).getTextContent()));
                            break;
                        case 9:
                            operation = new Operation(OperationTypeEnum.SCREENCAPTURE);
                            String screenCapture = xmlElement.getAttribute("FullScreen");

                            boolean fullScreen = Boolean.parseBoolean(screenCapture);
                            System.out.println(screenCapture);
                            System.out.println(fullScreen);

                            if (!fullScreen) {

                                operation.setStartX(Integer.parseInt(xmlElement.getElementsByTagName("StartX").item(0).getTextContent()));
                                operation.setStartY(Integer.parseInt(xmlElement.getElementsByTagName("StartY").item(0).getTextContent()));
                                operation.setEndX(Integer.parseInt(xmlElement.getElementsByTagName("EndX").item(0).getTextContent()));
                                operation.setEndY(Integer.parseInt(xmlElement.getElementsByTagName("EndY").item(0).getTextContent()));
                            }
                            break;
                    }
                    if (operation != null)
                        actionsList.add(operation);
                }
            }
            return actionsList;

        } catch (Exception ex) {

            ex.printStackTrace();
            System.out.println("Error reading XML file");
            return null;
        }
    }
}
